package rs.libgen.zorro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.libgen.zorro.model.KindleDevice;

import java.util.List;

@Repository
public interface KindleDeviceRepository extends JpaRepository<KindleDevice, Long> {
    List<KindleDevice> findAllByAccountId(Long accountId);

    KindleDevice findByDeviceId(Long deviceId);

}
