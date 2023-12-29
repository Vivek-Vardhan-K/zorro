package rs.libgen.zorro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.libgen.zorro.model.MainAccount;

@Repository
public interface MainAccountRepository extends JpaRepository<MainAccount,Long> {
    MainAccount findMainAccountsByGmailId(String gmailId);
}
