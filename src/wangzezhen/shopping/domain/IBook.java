package wangzezhen.shopping.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 图书接口
 * @author 王泽振
 *2016年2月23日 上午11:40:52
 */
public interface IBook {

	//创建一个图书集合
	Map<String, Book> BOOKS = new HashMap<String, Book>();
	
	//浏览所有图书
	void showBooks();
	
	//根据isbn名称获取图书详细信息
	Book findByIsbn(String isbn);
	
}
