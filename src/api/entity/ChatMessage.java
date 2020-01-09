package api.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = ChatMessage.tableName)
public class ChatMessage {
    public static final String tableName = "messages";

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Getter @Setter
    private String sender;

    @Getter @Setter
    private String receiver;

    @Getter @Setter
    private String text;

    @Getter @Setter
    private long time;

    @Getter @Setter
    private boolean seen;

}
