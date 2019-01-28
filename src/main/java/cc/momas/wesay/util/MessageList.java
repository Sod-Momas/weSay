package cc.momas.wesay.util;

/**
 * 这个类用来实现有长度限制的消息列表
 *
 * @author Sod-Momas
 */
public class MessageList {

    /**
     * 消息列表内容
     */
    private transient Message[] messages;
    /**
     * 消息列表内容长度
     */
    private int size;
    /**
     * 消息列表限制长度
     */
    private int limitLength;

    public MessageList() {
        this.limitLength = 100;
        messages = new Message[100];
    }

    public synchronized void add(Message msg) {
        if (limitLength <= size) {
            // 使所有元素向前移动一位
            System.arraycopy(messages, 1, messages, 0, messages.length - 1);
//            for (int i = 0; i < messages.length - 1; i++) {
//                messages[i] = messages[i + 1];
//            }
        } else {
            size++;
        }
        messages[size - 1] = msg;
    }

    /**
     * 格式化成json
     *
     * @return 对象数组json
     */
    public String toJson() {
        StringBuilder sb = new StringBuilder("[");
        for (Message message : messages) {
            if (message == null) {
                continue;
            }
            sb.append(message.toJson());
            sb.append(",");
        }
        if (sb.length() > 1) {
            // 删除最后一个逗号
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }


}
