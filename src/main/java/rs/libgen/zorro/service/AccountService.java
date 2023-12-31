package rs.libgen.zorro.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import rs.libgen.zorro.model.MainAccount;
import rs.libgen.zorro.repository.MainAccountRepository;

import java.util.List;


@Service
public class AccountService {

    @Autowired
    private MainAccountRepository mainAccountRepository;

    public List<MainAccount> getAllAccounts() {
        return mainAccountRepository.findAll();
    }

    public MainAccount registerAccount(String gmail) {
        MainAccount mainAccount = new MainAccount();
        mainAccount.setGmailId(gmail);
        if (mainAccountRepository.findMainAccountsByGmailId(gmail) != null) {
            throw new DuplicateKeyException("Gmail id " + gmail + " is already registered");
        }
        mainAccountRepository.save(mainAccount);
        return mainAccount;
    }

    public MainAccount findAccountByEmailId(String gmail) {
        return mainAccountRepository.findMainAccountsByGmailId(gmail);
    }

}
