package wangzezhen.shopping.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车接口
 * @author 王泽振
 *2016年2月23日 下午1:34:49
 */
public interface IShoppingCart {

	//订单集合
	List<Order> ORDERS = new ArrayList<Order>();
	//集合知识
//	1 添加图书到购物车中
	void addShooping (Order order);
//	2 浏览购物车
	void showOrders();
//	3修改购物车
	void modify(int orderId);
//	4删除购物车
	void delete(int orderId);
//	清空购物车
	void clear();
//	5结账
	void pay(int money);
	//io操作
	//保存当前购物记录（序列化）
	void save()throws Exception;
//	6浏览历史纪录（反序列化）
	void load()throws Exception;
	
}
