package restaurant;

import java.util.ArrayList;
import java.util.List;

public class Table {
	private int tableNum;
	private List<Carte> orderList = new ArrayList<>();
	private int totalPrice;
	private int totalCal;
	
	public Table() {
		
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getTotalCal() {
		return totalCal;
	}
	public void setTotalCal(int totalCal) {
		this.totalCal = totalCal;
	}
	public Table(int tableNum) {
		this.tableNum = tableNum;
	}
	
	public int getTableNum() {
		return tableNum;
	}
	
	public void setTableNum(int num) {
		this.tableNum = num;
	}
	public List<Carte> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Carte> orderList) {
		this.orderList = orderList;
	}
	
	

}
