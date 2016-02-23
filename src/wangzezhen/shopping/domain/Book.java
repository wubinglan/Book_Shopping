package wangzezhen.shopping.domain;

import java.io.Serializable;

/**
 * 书本类
 * @author 王泽振
 *2016年2月23日 上午11:28:24
 */
public class Book implements Serializable{

	/**
	 * 序列化标识  用于软件升级时区别对象的对象
	 */
	private static final long serialVersionUID = -8090219868695951836L;
	private String isbn;
	private String title;
	private  int price;
	private String author;
	/**
	 * 构造
	 */
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String isbn, String title, int price, String author) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.price = price;
		this.author = author;
	}
	/**
	 * 封装
	 * @return
	 */
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", price=" + price
				+ ", author=" + author + "]";
	}
	
	
	
	
	
	
	
}
