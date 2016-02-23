package wangzezhen.shopping.view;

import java.io.File;
import java.util.Scanner;

import wangzezhen.shopping.domain.Book;
import wangzezhen.shopping.domain.IBook;
import wangzezhen.shopping.domain.IShoppingCart;
import wangzezhen.shopping.domain.Order;
import wangzezhen.shopping.service.BookService;
import wangzezhen.shopping.service.ShoppingService;

public class ShopingView {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		int m = 0;
		IBook service = new BookService();
		IShoppingCart servicer =  new ShoppingService();
		//判断是否有历史记录
		File file = new File("order.ser");
		if(file.exists()){
			System.out.println("您有历史记录是否保留？ YES：y,NO:n");
			String str = in.next();
			if (str.equalsIgnoreCase("y")) {
				servicer.load();
			}else{
				file.delete();
			}
			
		}
		do{
			System.out.println("-----------------开心书店------------------");
			System.out.println("1 浏览图书  2 购买图书  3 查询图书  4 清空购物车  5结账  6修改订单 7删除订单 8保存订单   9退出");
			m = in.nextInt();
			switch (m) {
			case 1:
				service.showBooks();
				break;
			case 2:
				System.out.println("请输入要购买的图示的isbn");	
				String isbn = in.next();
				Book book = service.findByIsbn(isbn);
				if(book != null){
					System.out.println(book);
					//创建订单
					Order order = new Order(book,1);
					
					servicer.addShooping(order);
				}else{
					System.out.println("你输入的书不存在");
				}
			break;
			case 3:
				servicer.showOrders();
				break;
			case 4:
//				清空购物车
				servicer.clear();
				break;
				
			case 5:
				System.out.println("请交钱￥");
				int money = in.nextInt();
				servicer.pay(money);
				break;
				
			case 6:
				System.out.println("请输入要修改的订单编号：");
				int orderId=in.nextInt();
				servicer.modify(orderId);
				break;
			case 7:
				boolean flag = new ShoppingService().flag();
				if (flag) {
					System.out.println("请输入你要删除的订单号");
					int orderId1 = in.nextInt();
					servicer.delete(orderId1);
				} else {
					System.out.println("购物车还是空空的");
				
				}
				break;
			case 8:
				servicer.save();
				break;
			case 9:
				
				servicer.save();
				System.out.println("谢谢光临，欢迎再来！");
				System.exit(0);
				break;
			}
		}while(m<=9);
	}
}
