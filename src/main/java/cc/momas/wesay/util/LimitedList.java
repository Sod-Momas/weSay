package cc.momas.wesay.util;

import java.util.LinkedList;

public class LimitedList<E> {
	private int length;
	private LinkedList<E> list = new LinkedList<E>();

	public void add(E e) {
		if (this.list.size() >= length) {
			list.removeFirst();
		}
		list.addLast(e);
	}

	//constructor
	public LimitedList(int length) {
		super();
		if (length < 1) {
			this.length = 10;
		}
		this.length = length;
	}

	// getter and setter
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
