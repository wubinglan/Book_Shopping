package wangzezhen.shopping.service;

import java.util.Iterator;

import wangzezhen.shopping.domain.Book;
import wangzezhen.shopping.domain.IBook;

public class BookService implements IBook{

	//静态快中初始化图书集合
	static{
		Book b1 = new Book("isbn-001","java编程",55,"damiv");
		Book b2 = new Book("isbn-002","java技术",45,"daeiv");
		Book b3 = new Book("isbn-003","java工程",78,"dfdfiv");
		BOOKS.put(b1.getIsbn(), b1);
		BOOKS.put(b2.getIsbn(), b2);
		BOOKS.put(b3.getIsbn(), b3);
	}
	@Override
	public void showBooks() {
		//迭代集合
		for(Iterator<Book> it = BOOKS.values().iterator();it.hasNext();){
			System.out.println(it.next());
		}
	}

	@Override
	public Book findByIsbn(String isbn) {
		if(!BOOKS.isEmpty()){
			return BOOKS.get(isbn);
		}else{
		return null;
		}
	}

}
