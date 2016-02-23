package wangzezhen.shopping.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import wangzezhen.shopping.domain.Book;
import wangzezhen.shopping.domain.IShoppingCart;
import wangzezhen.shopping.domain.Order;

/**
 * 
 * @author 王泽振
 *2016年2月23日 下午1:43:57
 */
public class ShoppingService implements IShoppingCart{

	@Override
	public void addShooping(Order order) {
		//添加订单到购物车
		/**
		 * 1  新的购物   创建新的订单并保存到购物车中
		 * 2  已经存在的  修改数量		
		 * 对订单集合迭代，判断
		**/
		boolean flag = false;
		Order temp = null;
		for(Iterator<Order> it = ORDERS.iterator();it.hasNext();){
			temp = it.next();
			if(order.getBook().getIsbn().equals
					(temp.getBook().getIsbn())){
				flag = true;
				break;
			}
		}
		if(flag){
			temp.setCount(temp.getCount()+1);
		}else{
			ORDERS.add(order);
		}
		
		
	}

	public boolean flag(){
		if(!ORDERS.isEmpty()) return true;
		return false;
		
	}
	@Override
	public void showOrders() {
		int sum = 0;
		int n = 0;
		// 浏览购物车
		if(!ORDERS.isEmpty()){
			for(Order o :ORDERS){
				Book book = o.getBook();
				System.out.println("订单编号"+o.getId());
				System.out.println("图书名称：《"+book.getTitle()
						+"》，单价:￥ "+book.getPrice() +", 数量: "+o.getCount());
				n = book.getPrice()*o.getCount();
				System.out.println("小计：￥"+n+"元");
				
				System.out.println("----------------------");
				//计算总价
				sum += n;
				
			}
			System.out.println("您共计消费￥"+sum+"元");
		}else{
			System.out.println("购物车中空空的呢！！！");
		}
		
	}

	private  Order findOrderById(int orderId) {
		//迭代
			
			for( Order o : ORDERS){
				if(o.getId()== orderId){
					return o;
				}
				
			}
			
		return null;
	}
	
	@Override
	public void modify(int orderId) {
			//		3修改购物车
		Scanner in=new Scanner(System.in);
        Order order=this.findOrderById(orderId);
        if(order!=null){
       	 System.out.println("请输入新的数量：");
       	 int num=in.nextInt();
       	 if(num<=0){
       		System.out.println("数量不能小于0 。。"); 
       	 }else{
       	   order.setCount(num);
       	 }
       	 
        }else{
       	 System.out.println("不存在输入的订单编号");
        }
	}

	@Override
	public void delete(int orderId) {
		// 删除购物车
		Order order = this.findOrderById(orderId);
		if (order != null) {
			Scanner in = new Scanner(System.in);
			System.out.println("确认要删除订单吗？ YES：y,NO:n");
			String str = in.next();
			ORDERS.remove(order);
//			try {
//				this.save();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		} else{
			System.out.println("您输入的订单号不存在");
		}
	}

	@Override
	public void clear() {
		// 清空购物车
		if (!ORDERS.isEmpty()) {
			ORDERS.clear();
			File file = new File("order.ser");
			if(file.exists()){
				file.delete();
			}
		} else {
			System.out.println("购物车还是空空的");
		}
		
	}

	private void print(int money) throws Exception{//打印小票
		int sum = 0;
		int n = 0;
		FileWriter fw = new FileWriter("小票"+new Date().getTime()+".txt");
		for(Order o :ORDERS){
				Book book = o.getBook();
				fw.write("订单编号"+o.getId());
				fw.write("\n图书名称：《"+book.getTitle()
						+"》，单价:￥ "+book.getPrice() +", 数量: "+o.getCount());
				n = book.getPrice()*o.getCount();
				fw.write("\n小计：￥"+n+"元\n");
				
				fw.write("----------------------");
				//计算总价
				sum += n;
			}
		fw.write("\n您共计消费￥"+sum+"元");
		fw.write("开心书店收您￥"+money+"元");
		fw.write("找零￥"+(money - sum)+"元");
		fw.close();
	}
	@Override
	public void pay(int money) {
		// 结账
		/**
		 * 打印订单的总价
		 * 得到用户输入的金额 
		 * 判断用户的金额是否大于等于总金额（找零）
		 * 清空购物车
		 * 打印购物小票
		 */
		int sum = 0;
		int n = 0;
		// 浏览购物车
		if(!ORDERS.isEmpty()){
			for(Order o :ORDERS){
				Book book = o.getBook();
				n = book.getPrice()*o.getCount();
				System.out.println("----------------------");
				//计算总价
				sum += n;
			}
			if(money >= sum){//结账成功
				System.out.println("找零￥"+(money - sum)+"元");
				try {
					this.print(money);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				ORDERS.clear();
				//清空序列化的
				File file = new File("order.ser");
				if(file.exists()){
					file.delete();
				}
			}else{//结账失败
				System.out.println("金额不足，结账失败");
			}
		}
		
	}

	@Override
	public void save() throws Exception {
		// 序列化
		File file = new File("order.ser");
		if(!ORDERS.isEmpty()){
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(ORDERS);
			out.close();
			System.out.println("保存记录成功！");
		}else{
			System.out.println("没有订单信息，保存失败！");
		}
		
	}

	@Override
	public void load() throws Exception {
		// 反序列号
		File file = new File("order.ser");
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		ORDERS.addAll((List<Order>) in.readObject());
		in.close();
	}

}
