package rs.libgen.zorro.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import rs.libgen.zorro.model.EmailDetails;
import rs.libgen.zorro.service.EmailService;
import rs.libgen.zorro.service.SearchService;

import java.io.IOException;

@RestController
@CrossOrigin
public class EmailController {
    @Autowired
    private EmailService emailService;

    @Autowired
    private SearchService searchService;

    @PostMapping("/sendMail")
    public String sendMailWithDetails(@RequestBody EmailDetails emailDetails){
        return emailService.sendMailWithAttachment(emailDetails);
    }

    @PostMapping("/send-to-kindle")
    public String sendToKindle(@RequestHeader String kindleEmail,@RequestHeader String resLink) throws IOException {
        String dLink=searchService.resolveGetLinkMirror1(resLink);
        String uniqFileName=searchService.downloadFileFromLink(dLink);
        EmailDetails emailDetails=new EmailDetails();
        emailDetails.setRecipient(kindleEmail);
        emailDetails.setAttachment(uniqFileName);
        return emailService.sendMailWithAttachment(emailDetails);
    }

}
