package com.oms.projectbuddy.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.mail.internet.MimeMessage;
import java.net.URLEncoder;


@Component
public class SmsEmailIntegration {

    private static final Logger LOG = LoggerFactory.getLogger(SmsEmailIntegration.class);

    @Inject
    private JavaMailSender mailSender;

//@Inject
//private SpringTemplateEngine templateEngine;

    @Inject
    private RestTemplate restTemplate;


    @Value("${spring.mail.from}")
    private String from;

    public void sendEmail(String to, String subject, String message) {

        try {
            /*
            SimpleMailMessage mailMessage=new SimpleMailMessage();
            mailMessage.setTo(to);
            mailMessage.setFrom(from);
            mailMessage.setSubject(subject);
            mailMessage.setText(message);
            LOG.info("Message "+message+" sent to"+to);
            mailSender.send(mailMessage);

             */
            if (StringUtils.isEmpty(to)) {
                LOG.info("Email cant be send because to email is null");
                return;
            }
            MimeMessage messages = mailSender.createMimeMessage();
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(messages, true);//true indicates multipart message
            helper.setFrom(from != null ? from : "support@sourceably.com"); // <--- THIS IS IMPORTANT
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(message, true);//true indicates body is html
            mailSender.send(messages);
            LOG.info("Mail send successfully from api" + to + "By ");

        } catch (Exception e) {
            e.printStackTrace();
            LOG.info("In Email Cant Send The Email To " + to + "By " + e.getMessage());
        }
    }


    public void sendVerificationEmail(String to, String subject, String message) {
        try {
            MimeMessage mime = mailSender.createMimeMessage();
            MimeMessageHelper mail = new MimeMessageHelper(mime);
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(message, true);
            mail.setFrom(from);
            mailSender.send(mime);
        } catch (Exception e) {
            LOG.info("In Email Cant Send The Email To " + to + "By " + e.getMessage());
        }
    }

    public void sendSms(String mobileNumber, String message) {
        try {

            String url = "http://sms-cp.otptech.in/api2/send/?apikey=Y2U4MTBhMTg5MGMxYWRkMGM3ZjUyMWI4NTczNDY3MDg=&numbers=" + mobileNumber + "&sender=OMSTXN&message=" + URLEncoder.encode(message);
            //  String url = "http://sms-cp.otptech.in/api2/send/?apikey=Y2U4MTBhMTg5MGMxYWRkMGM3ZjUyMWI4NTczNDY3MDg=&numbers="+email+"&sender=OMSTXN&message="+ URLEncoder.encode(message);
            // String url ="http://sms-cp.otptech.in/api2/send/?apikey=Y2U4MTBhMTg5MGMxYWRkMGM3ZjUyMWI4NTczNDY3MDg=&numbers=91".$mobile."&sender=OMSTXN&message=".urlencode($message);;
            LOG.info("In Sms Integration For User Mobile Number" + mobileNumber);
            String result = restTemplate.getForObject(url, String.class);
            LOG.info("Response Of Sms Api " + result);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void sendSmsUS(String mobileNumber, String message) {
        try {

            String url = "https://api.txtlocal.com/send/?apiKey=MzY0YjQ4NzI0ZDQ0Mzg3NDM0NDQzNzM5NDgzNDcwMzc=&sender=sourceably&numbers=" + mobileNumber + "&message=" + URLEncoder.encode(message);
            //  String url = "http://sms-cp.otptech.in/api2/send/?apikey=Y2U4MTBhMTg5MGMxYWRkMGM3ZjUyMWI4NTczNDY3MDg=&numbers="+email+"&sender=OMSTXN&message="+ URLEncoder.encode(message);
            // String url ="http://sms-cp.otptech.in/api2/send/?apikey=Y2U4MTBhMTg5MGMxYWRkMGM3ZjUyMWI4NTczNDY3MDg=&numbers=91".$mobile."&sender=OMSTXN&message=".urlencode($message);;
            LOG.info("In Sms Integration For User Mobile Number" + mobileNumber);
            String result = restTemplate.getForObject(url, String.class);
            LOG.info("Response Of Sms Api " + result);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
/*    public void sendEmail(Mail mail) throws  IOException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        helper.addAttachment("template-cover.png", new ClassPathResource("javabydeveloper-email.PNG"));
        Context context = new Context();
        context.setVariables(mail.getProps());

        String html = templateEngine.process("newsletter-template", context);
        helper.setTo(mail.getMailTo());
        helper.setText(html,  true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());
        mailSender.send(message);
    }*/


}

