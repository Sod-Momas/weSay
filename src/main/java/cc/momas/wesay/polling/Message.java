package cc.momas.wesay.polling;

/**
 * 消息
 *
 * @author Sod-Momas
 */
public class Message {

    private String username;
    private String content;

    public Message(String username, String content) {
        this.username = username;
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("{\"content\":\"%s\", \"username\":\"%s\"}",
                content,
                username);
    }
}
