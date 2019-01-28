package cc.momas.wesay.util;

/**
 * 消息
 *
 * @author Sod-Momas
 */
public class Message {
    private String username;
    private String content;

    public Message(String username, String content) {
        this.username = CommonHelper.urlDecode(username);
        this.content = CommonHelper.urlDecode(content);
    }

    public String toJson() {
        return String.format("{\"content\":\"%s\", \"username\":\"%s\"}",
                CommonHelper.urlEncode(content),
                CommonHelper.urlEncode(username));
    }
}
