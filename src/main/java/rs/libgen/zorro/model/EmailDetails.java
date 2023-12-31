package rs.libgen.zorro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class EmailDetails {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
    private String bookName;

    public EmailDetails() {
        this.msgBody = "";
        this.subject = "";
    }
}