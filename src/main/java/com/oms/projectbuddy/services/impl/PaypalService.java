package com.oms.projectbuddy.services.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oms.projectbuddy.dto.paypal.*;
import com.oms.projectbuddy.exception.SourceablyCustomeException;
import com.oms.projectbuddy.model.CompanyRegistration;
import com.oms.projectbuddy.model.MembershipPlanSelection;
import com.oms.projectbuddy.model.PaypalPaymentDetail;
import com.oms.projectbuddy.repository.MembershipPlanSelectionRepository;
import com.oms.projectbuddy.repository.PaypalPaymentDetailRepository;
import com.oms.projectbuddy.services.IPaypalService;
import com.oms.projectbuddy.services.ISessionService;
import com.oms.projectbuddy.utility.SmsEmailIntegration;
import com.oms.projectbuddy.utils.ExceptionUtils;
import com.oms.projectbuddy.utils.SubscriptionStatus;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static com.oms.projectbuddy.utils.Constants.*;


@Service
public class PaypalService implements IPaypalService {

    @Inject
    private RestTemplate restTemplate;

    @Value("${paypal.api.oauthtoken.url}")
    private String paypalOAuthTokenUrl;

    @Value("${paypal.api.clientid}")
    private String paypalClientId;

    @Value("${paypal.api.clientsecret}")
    private String paypalClientSecret;

    @Value("${paypal.api.plan.url}")
    private String paypalPlanUrl;

    @Value("${paypal.api.subscription.url}")
    private String paypalSubscriptionUrl;

    @Value("${paypal.api.product.url}")
    private String paypalProductUrl;

    @Value("${paypal.pb.product.id}")
    private String paypalPBProductId;

    @Inject
    private ISessionService sessionService;

    @Inject
    private MembershipPlanSelectionRepository membershipPlanSelectionRepository;

    @Inject
    private PaypalPaymentDetailRepository paypalPaymentDetailRepository;

    @Inject
    private SmsEmailIntegration smsEmailIntegration;

    private String paypalAuthorizationTokenRequest() throws JsonProcessingException {
        String auth = paypalClientId + ":" + paypalClientSecret;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.ISO_8859_1));
        String authHeader = "Basic " + new String(encodedAuth);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set(HttpHeaders.AUTHORIZATION, authHeader);
        HttpEntity<?> requestEntity = new HttpEntity<>("grant_type=client_credentials", headers);
        String strResult = restTemplate.exchange(paypalOAuthTokenUrl, HttpMethod.POST, requestEntity, String.class).getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode result = objectMapper.readTree(strResult);
        return result.get("access_token").asText();
    }

    public String createProduct() throws JsonProcessingException {
        String authHeader = "Bearer " + paypalAuthorizationTokenRequest();
        Product product = new Product();
        product.setName("Project Buddy Subscription");
        product.setDescription("Project Buddy Membership Subscription Plan");
        product.setType("SERVICE");
        product.setCategory("SOFTWARE");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set(HttpHeaders.AUTHORIZATION, authHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Product> entity = new HttpEntity<Product>(product, headers);
        String strResponse = restTemplate.exchange(paypalProductUrl, HttpMethod.POST, entity, String.class).getBody();
        return strResponse;
    }

    private String createPaymentPlan(MembershipPlanSelection selectedPlan, CompanyRegistration companyRegistration) throws JsonProcessingException {
        String billingCyleFrequency = "";
        if (selectedPlan.getPaymentCycle().equals(MONTHLY))
            billingCyleFrequency = "MONTH";
        else if (selectedPlan.getPaymentCycle().equals(YEARLY))
            billingCyleFrequency = "YEAR";
        Plan plan = new Plan();
        plan.setProduct_id(paypalPBProductId);
        plan.setName("Project Buddy Subscription Plan for " + companyRegistration.getCompanyId());
        plan.setDescription("Membership Plan Type : " + selectedPlan.getMembershipPlanId() + " Billing Cycle Frequency :" + billingCyleFrequency);
        plan.setStatus(SubscriptionStatus.ACTIVE.name());
        BillingCycle billingCycle = new BillingCycle();
        Frequency frequency = new Frequency(billingCyleFrequency, 1);
        billingCycle.setFrequency(frequency);
        billingCycle.setTenure_type("REGULAR");
        billingCycle.setSequence(1);
        billingCycle.setTotal_cycles(0);
        PricingScheme pricingScheme = new PricingScheme();
        FixedPrice fixedPrice = new FixedPrice();
        fixedPrice.setCurrency_code(USD);
        fixedPrice.setValue(selectedPlan.getPlanCost().toString());
        pricingScheme.setFixed_price(fixedPrice);
        billingCycle.setPricing_scheme(pricingScheme);
        plan.setBilling_cycles(Arrays.asList(billingCycle));
        PaymentPreferences paymentPreferences = new PaymentPreferences();
        paymentPreferences.setAuto_bill_outstanding(true);
        SetupFee setupFee = new SetupFee();
        setupFee.setCurrency_code(USD);
        setupFee.setValue(selectedPlan.getPlanCost().toString());
        paymentPreferences.setSetup_fee(setupFee);
        paymentPreferences.setPayment_failure_threshold(3);
        plan.setPayment_preferences(paymentPreferences);
        plan.setTaxes(null);
        ObjectMapper mapper = new ObjectMapper();
        String authHeader = "Bearer " + paypalAuthorizationTokenRequest();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set(HttpHeaders.AUTHORIZATION, authHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Plan> entity = new HttpEntity<Plan>(plan, headers);
        String strResponse = restTemplate.exchange(paypalPlanUrl, HttpMethod.POST, entity, String.class).getBody();
        JsonNode response = mapper.readTree(strResponse);
        return response.get("id").asText();
    }

    public String createPaymentPlanAndSubscription(PaypalPaymentData paypalPaymentData) throws JsonProcessingException {
        CompanyRegistration companyRegistration = sessionService.getLoginCompanyRegistration();
        MembershipPlanSelection selectedPlan = membershipPlanSelectionRepository.findByUserIdAndPlanStatus(companyRegistration.getUserId(), false);
        ExceptionUtils.verifyDataNotExistThenThrowException(selectedPlan,"Membership Plan not found");
        String planId = createPaymentPlan(selectedPlan, companyRegistration);
        Subscription subscription = new Subscription();
        subscription.setPlan_id(planId);
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(tz);
        int monthsToBeAdded = 0;
        if (selectedPlan.getPaymentCycle().equals(MONTHLY))
            monthsToBeAdded = 1;
        else if (selectedPlan.getPaymentCycle().equals(YEARLY))
            monthsToBeAdded = 12;

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, monthsToBeAdded);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date agreementDate = calendar.getTime();
        String nowAsISO = df.format(agreementDate);
        subscription.setStart_time(nowAsISO);
        subscription.setQuantity("1");
        subscription.setShipping_amount(null);
        Subscriber subscriber = new Subscriber();
        subscriber.setEmail_address(companyRegistration.getEmail());
        Name name= new Name();
        name.setFull_name(companyRegistration.getFirstName() + companyRegistration.getLastName());
        subscription.setSubscriber(subscriber);
        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.setBrand_name(BRAND_NAME);
        applicationContext.setLocale("en-US");
        applicationContext.setUser_action("SUBSCRIBE_NOW");
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setPayer_selected("PAYPAL");
        paymentMethod.setPayee_preferred("IMMEDIATE_PAYMENT_REQUIRED");
        applicationContext.setPayment_method(paymentMethod);
        applicationContext.setReturn_url(paypalPaymentData.getPaypalReturnUrl());
        applicationContext.setCancel_url(paypalPaymentData.getPaypalCancelUrl());
        applicationContext.setShipping_preference("SET_PROVIDED_ADDRESS");
        subscription.setApplication_context(applicationContext);
        ObjectMapper mapper = new ObjectMapper();
        String authHeader = "Bearer " + paypalAuthorizationTokenRequest();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set(HttpHeaders.AUTHORIZATION, authHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Subscription> entity = new HttpEntity<>(subscription, headers);
        ResponseEntity<String> response = restTemplate.exchange(paypalSubscriptionUrl, HttpMethod.POST, entity, String.class);
        if(response.getStatusCode().is2xxSuccessful()){
            String strResponse = response.getBody();
            JsonNode jsonResponse = mapper.readTree(strResponse);
            String paypalSubscriptionId = jsonResponse.get("id").asText();
            String redirectLink = jsonResponse.get("links").get(0).get("href").asText();
            PaypalPaymentDetail paypalPaymentDetail = new PaypalPaymentDetail();
            paypalPaymentDetail.setPaypalPlanId(planId);
            paypalPaymentDetail.setPaypalEmail(companyRegistration.getEmail());
            paypalPaymentDetail.setSubscriptionId(selectedPlan.getId());
            paypalPaymentDetail.setPaypalSubscriptionId(paypalSubscriptionId);
            paypalPaymentDetailRepository.save(paypalPaymentDetail);
            String message="Your Payment Succesfully Credited..!!" + "Start Date :" + paypalPaymentDetail.getPaymentDate() + "Validity :" + selectedPlan.getPaymentCycle() +"UserId :" + selectedPlan.getUserId() ;
            smsEmailIntegration.sendEmail(paypalPaymentDetail.getPaypalEmail(), "About Payment Status",message);
            return redirectLink;
        }
        throw new SourceablyCustomeException("Error in Payment", HttpStatus.PAYMENT_REQUIRED);
    }
}
