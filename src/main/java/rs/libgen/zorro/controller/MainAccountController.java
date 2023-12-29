package rs.libgen.zorro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import rs.libgen.zorro.model.MainAccount;
import rs.libgen.zorro.service.AccountService;

@RestController
@CrossOrigin
public class MainAccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(path = "/create-account")
    public MainAccount createMainAccount(@RequestHeader String gmail){
        return accountService.registerAccount(gmail);
    }
}
