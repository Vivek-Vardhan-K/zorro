package rs.libgen.zorro.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "KINDLE_DEVICE")
public class KindleDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_id_gen")
    @SequenceGenerator(name = "device_id_gen", sequenceName = "device_id_gen", allocationSize = 1)
    @Column(name = "DEVICE_ID")
    private Long deviceId;

    @Column(name = "KINDLE_EMAIL")
    private String kindleEmail;

    @Column(name = "ACCOUNT_ID")
    private Long accountId;

}
