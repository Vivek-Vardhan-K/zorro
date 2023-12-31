package rs.libgen.zorro.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.libgen.zorro.model.KindleDevice;
import rs.libgen.zorro.model.MainAccount;
import rs.libgen.zorro.repository.KindleDeviceRepository;
import rs.libgen.zorro.repository.MainAccountRepository;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
public class KindleDeviceService {
    @Autowired
    private KindleDeviceRepository kindleDeviceRepository;

    @Autowired
    private MainAccountRepository mainAccountRepository;

    public List<KindleDevice> getAllKindleDevicesByAccountId(Long accountId) {
        return kindleDeviceRepository.findAllByAccountId(accountId);
    }

    public KindleDevice createKindleDeviceForUser(String kindleEmail, Long accountId, Short deviceType) throws AccountNotFoundException {
        MainAccount mainAccount = mainAccountRepository.findMainAccountsByAccountId(accountId);
        if (mainAccount == null) {
            throw new AccountNotFoundException("Account not found");
        }
        KindleDevice kindleDevice = new KindleDevice();
        kindleDevice.setKindleEmail(kindleEmail);
        kindleDevice.setAccountId(mainAccount.getAccountId());
        kindleDevice.setDeviceType(deviceType);
        kindleDeviceRepository.save(kindleDevice);
        return kindleDevice;
    }
}
