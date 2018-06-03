package cc.momas.wesay.util;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * 这个类用来实现有长度限制的消息列表
 */
public class MsgList {

    // 消息列表内容
    transient Message[] messages;
    // 消息列表内容长度
    private int size;
    // 消息列表限制长度
    private int limitLength = 100;

    public MsgList(int limitLength) {
        this.limitLength = limitLength;
        messages = new Message[limitLength];
    }

    public static void main(String[] args) {
        MsgList list = new MsgList(5);
        list.add(new Message("1", "1"));
        list.add(new Message("2", "2"));
        list.add(new Message("3", "3"));
        list.add(new Message("4", "4"));
        list.add(new Message("5", "5"));
        list.add(new Message("6", "6"));
        System.out.println(list.toJson());
    }

    public synchronized boolean add(Message msg) {
        if (limitLength <= size) {
            // 使所有元素向前移动一位
            for (int i = 0; i < messages.length - 1; i++) {
                messages[i] = messages[i + 1];
            }
        } else {
            size++;
        }
        messages[size - 1] = msg;
        return true;
    }


    @Override
    public String toString() {
        return "MsgList{" +
                "messages=" + Arrays.toString(messages) +
                ", size=" + size +
                ", limitLength=" + limitLength +
                '}';
    }

    // 格式化成json
    public String toJson() {
        StringBuffer sb = new StringBuffer("[");
        for (int i = 0; i < messages.length; i++) {
            if (messages[i] == null) {
                break;
            }
            sb.append(messages[i].toJson());
            sb.append(",");
        }
        if(sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1); // 删除最后一个逗号
        }
        sb.append("]");
        return sb.toString();
    }


}
