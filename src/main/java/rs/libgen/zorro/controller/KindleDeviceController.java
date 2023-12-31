package rs.libgen.zorro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.libgen.zorro.model.KindleDevice;
import rs.libgen.zorro.service.KindleDeviceService;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@CrossOrigin
public class KindleDeviceController {

    @Autowired
    private KindleDeviceService kindleDeviceService;

    @GetMapping("/get-all-devices-for-user/{accountId}")
    public List<KindleDevice> getAllKindleDevicesForUser(@PathVariable Long accountId) {
        return kindleDeviceService.getAllKindleDevicesByAccountId(accountId);
    }

    @PostMapping("/create-device")
    public KindleDevice createDeviceForUser(@RequestHeader String kindleMail, @RequestHeader Long accountId, @RequestHeader Short deviceType) throws AccountNotFoundException {
        return kindleDeviceService.createKindleDeviceForUser(kindleMail, accountId, deviceType);
    }
}
