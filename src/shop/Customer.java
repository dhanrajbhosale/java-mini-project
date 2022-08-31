package shop;

public class Customer {
	String custNo;
	String custName;
	String address;
	
	
	public Customer(String custNo, String custName, String address) {
		super();
		this.custNo = custNo;
		this.custName = custName;
		this.address = address;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	

}
