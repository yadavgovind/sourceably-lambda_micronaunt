package com.oms.projectbuddy.services.impl;

import com.oms.projectbuddy.model.request.ProviderMailRequest;
import jakarta.inject.Singleton;

import java.util.Map;

@Singleton
public class EmailService {
//    @Inject
//    private JavaMailSender mailSender;
//    @Inject
//    private Configuration config;

    public String sendEmail(ProviderMailRequest request, Map<String,Object> model){
//        MimeMessage message=mailSender.createMimeMessage();
//        try{
//            MimeMessageHelper helper=new MimeMessageHelper(message,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
//                    StandardCharsets.UTF_8.name());
//            //helper.addAttachment("logo.png",new ClassPathResource("logo.png"));
//            Template t=config.getTemplate("email-template.ftl");
//            String html= FreeMarkerTemplateUtils.processTemplateIntoString(t,model);
//            helper.setTo(request.getTo());
//            helper.setText(html,true);
//            helper.setFrom(request.getContactEmail());
//            mailSender.send(message);
//            return "mail sent successfully";
//
//        }catch(MessagingException | IOException | TemplateException e){
//            System.out.println(e.getMessage());
//              return "mail cannot be sent";
//        }
        return "coming soon with mirconaunt";
    }

}
