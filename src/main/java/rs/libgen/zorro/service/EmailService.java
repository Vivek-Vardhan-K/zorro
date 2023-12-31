package rs.libgen.zorro.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import rs.libgen.zorro.model.EmailDetails;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Objects;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendMailWithAttachment(EmailDetails details) {
        log.info("sending Mail with attachment started!");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        log.info(details.toString());
        try {
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                    details.getSubject());
            File file = new File(details.getAttachment());
            FileSystemResource fileSystemResource = new FileSystemResource(file);

            mimeMessageHelper.addAttachment(
                    Objects.requireNonNull(fileSystemResource.getFilename()), fileSystemResource);
            javaMailSender.send(mimeMessage);
            if (file.delete()) {
                log.info("temp file deleted successfully in local storage");
            }
            return "Mail sent Successfully";
        } catch (MessagingException e) {
            return "Error while sending mail!!!";
        }
    }
}