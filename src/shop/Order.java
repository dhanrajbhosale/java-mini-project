package shop;

public class Order {

	int orderNo;
	int item_no;
	int  order_qty;
	String order_date;
	String custNo;
	
	
	
	public Order(int orderNo, int item_no, int order_qty, String order_date, String custNo) {
		super();
		this.orderNo = orderNo;
		this.item_no = item_no;
		this.order_qty = order_qty;
		this.order_date = order_date;
		this.custNo = custNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getItem_no() {
		return item_no;
	}
	public void setItem_no(int item_no) {
		this.item_no = item_no;
	}
	public int getOrder_qty() {
		return order_qty;
	}
	public void setOrder_qty(int order_qty) {
		this.order_qty = order_qty;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	
	
}

	    
