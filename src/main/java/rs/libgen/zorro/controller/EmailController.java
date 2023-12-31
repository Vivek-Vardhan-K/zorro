package rs.libgen.zorro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.libgen.zorro.model.EmailDetails;
import rs.libgen.zorro.model.KindleDevice;
import rs.libgen.zorro.repository.KindleDeviceRepository;
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

    @Autowired
    private KindleDeviceRepository kindleDeviceRepository;

    @PostMapping("/sendMail")
    public String sendMailWithDetails(@RequestBody EmailDetails emailDetails) {
        return emailService.sendMailWithAttachment(emailDetails);
    }

    @PostMapping("/send-to-kindle")
    public String sendToKindle(@RequestHeader Long deviceId, @RequestHeader String resLink,@RequestHeader String bookName) throws IOException {
        KindleDevice kindleDevice = kindleDeviceRepository.findByDeviceId(deviceId);
        String dLink = searchService.resolveGetLinkMirror1(resLink);
        String uniqFileName = searchService.downloadFileFromLink(dLink);
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(kindleDevice.getKindleEmail());
        emailDetails.setAttachment(uniqFileName);
        emailDetails.setBookName(bookName);
        return emailService.sendMailWithAttachment(emailDetails);
    }

}
