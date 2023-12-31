package rs.libgen.zorro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.libgen.zorro.model.MainAccount;
import rs.libgen.zorro.service.AccountService;

import java.util.List;

@RestController
@CrossOrigin
public class MainAccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/get-all-accounts")
    public List<MainAccount> getAllMainAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/check-user")
    public Boolean checkIfUserExistsByEmail(@RequestHeader String email) {
        return (accountService.findAccountByEmailId(email) != null);
    }

    @GetMapping("/get-account-by-email")
    public MainAccount getAccountByEmail(@RequestHeader String email) {
        return accountService.findAccountByEmailId(email);
    }

    @PostMapping(path = "/create-account")
    public MainAccount createMainAccount(@RequestHeader String gmail) {
        return accountService.registerAccount(gmail);
    }
}
