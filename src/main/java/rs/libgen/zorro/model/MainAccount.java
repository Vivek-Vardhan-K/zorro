package rs.libgen.zorro.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "MAIN_ACCOUNT")
public class MainAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acc_seq_gen")
    @SequenceGenerator(name = "acc_seq_gen", sequenceName = "account_sequence", allocationSize = 1)
    @Column(name = "ACCOUNT_ID")
    private Long accountId;

    @Column(name = "GMAIL_ID")
    private String gmailId;
}
