package cc.momas.wesay.util;

import java.util.LinkedList;

/**
 * 原本用来实现可限制长度的列表，现在废弃，使用更轻量级的实现
 *
 * @param <E> 泛型
 * @author Sod-Momas
 * @see MessageList
 */
@Deprecated
public class LimitedList<E> {
    private int length;
    private LinkedList<E> list = new LinkedList<E>();

    public void add(E e) {
        if (this.list.size() >= length) {
            list.removeFirst();
        }
        list.addLast(e);
    }

    public LimitedList(int length) {
        super();
        if (length < 1) {
            this.length = 10;
        }
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public LinkedList<E> getList() {
        return list;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setList(LinkedList<E> list) {
        this.list = list;
    }

}
