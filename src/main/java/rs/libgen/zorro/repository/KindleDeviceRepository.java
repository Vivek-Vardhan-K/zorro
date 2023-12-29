package rs.libgen.zorro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.libgen.zorro.model.KindleDevice;

@Repository
public interface KindleDeviceRepository extends JpaRepository<KindleDevice,Long> {
}
