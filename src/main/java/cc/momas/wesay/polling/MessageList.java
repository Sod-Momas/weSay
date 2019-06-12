package cc.momas.wesay.polling;

import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * 这个类用来实现有长度限制的消息列表
 *
 * @author Sod-Momas
 */
public class MessageList extends LinkedList<Message> {

    /**
     * 消息列表限制长度
     */
    private int limit = 100;

    public static MessageList getInstance() {
        return new MessageList();
    }

    @Override
    public boolean add(Message msg) {
        if (this.size() >= limit) {
            removeFirst();
        }
        addLast(msg);
        return true;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * 格式化成json
     *
     * @return 对象数组json
     */
    @Override
    public String toString() {
        // 转换成流
        return this.stream()
                // 调用toString方法,使对象全变成Json字符串
                .map(Message::toString)
                // 把所有对象拼接起来, 分割符是',',前缀是 '[',后缀是']',也就是json数组的格式
                .collect(Collectors.joining(",", "[", "]"));
    }
}
