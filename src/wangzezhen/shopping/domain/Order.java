package wangzezhen.shopping.domain;

import java.io.Serializable;
import java.util.Random;

/**
 * 订单类
 * @author 王泽振
 *2016年2月23日 上午11:33:52
 */
public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7631919177618216694L;
	private Book book;//图书
	private int id;//订单号 自动生成
	private int count;//数量
	
	/**
	 * 构造
	 */
	public Order() {
		super();
		this.id = this.createId();
	}
	public Order(Book book, int count) {
		this();
		this.book = book;
		this.count = count;
	}
	/**
	 * 封装
	 * @return
	 */
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int getId() {
		return id;
	}
	
	private int createId() {
		Random ran = new Random();
		return	ran.nextInt(10000)+1;
	}
	
	@Override
	public String toString() {
		return "Order [book=" + book + ", id=" + id + ", count=" + count + "]";
	}
	
}
