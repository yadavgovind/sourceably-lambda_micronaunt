package com.oms.projectbuddy.services.impl;

import com.oms.projectbuddy.model.request.ProviderMailRequest;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Configuration config;

    public String sendEmail(ProviderMailRequest request, Map<String,Object> model){
        MimeMessage message=mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper=new MimeMessageHelper(message,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            //helper.addAttachment("logo.png",new ClassPathResource("logo.png"));
            Template t=config.getTemplate("email-template.ftl");
            String html= FreeMarkerTemplateUtils.processTemplateIntoString(t,model);
            helper.setTo(request.getTo());
            helper.setText(html,true);
            helper.setFrom(request.getContactEmail());
            mailSender.send(message);
            return "mail sent successfully";

        }catch(MessagingException | IOException | TemplateException e){
            System.out.println(e.getMessage());
              return "mail cannot be sent";
        }
    }

}
