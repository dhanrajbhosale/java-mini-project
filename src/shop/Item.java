package shop;

public class Item {
	
	int item_no;
	String description;
	String category;
	double price;
	int quantity;
	
	
	
	public Item() {
		super();
	}

	public Item(int item_no, String description, String category, double price, int quantity) {
		super();
		this.item_no = item_no;
		this.description = description;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
	}
	
	public int getItem_no() {
		return item_no;
	}
	public void setItem_no(int item_no) {
		this.item_no = item_no;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return item_no + "      " + category + "       " + description + "         " 
				+ price + "   " + quantity;
	}
	
}
