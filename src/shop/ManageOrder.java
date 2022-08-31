/**
* Create a Java application to store Item,
* Customer and Order data in the database.
*
* @author  Dhanraj Bhosale
* @version 1.0
* @since   2020-05-15 
*/
package shop;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class ManageOrder {
	static Logger logger = Logger.getLogger("MyLog");  
    static FileHandler fh;
	private static Scanner scan;
	private static Scanner scan2;
	private static Scanner scan3;
	private static Scanner scan4; 
    
	public static void main(String[] args)  {
		
		setMyLog();
		Scanner scan = new Scanner(System.in);
		logger.info("________## Session Stated ##_______");
		System.out.println("________________## !!WELCOME TO OUR APP!! ##________________");
		System.out.println();
		
		while(true){
			System.out.println("\n_________# Main Menu #_________");
			System.out.println();
			System.out.println("Item details,          Enter 1");
			System.out.println("Order details,         Enter 2");
			System.out.println("To Show Order details, Enter 3");
			System.out.println("Customer details,      Enter 4");
			System.out.println("To Exit,               Enter 0");
			System.out.println("_______________________________");
			System.out.println("\nEnter your choice:");

			try {
				int choice = Integer.parseInt(scan.next());
				switch(choice)
				{
				case 1: itemDetails();
				break;
				case 2: orderDetails();
				break;
				case 3: showOrderDetails();
				break;
				case 4: showCustomerDetails();
				break;
				case 0: System.out.println("_______________## !!THANK YOU FOR USING OUR APP!! ##_______________");
						logger.info("________## Session Ended ##_______\n");
						scan.close();
						System.exit(0);
				break;
				default: System.out.println("Incorrect input!!! Please re-enter choice from our menu");
				}
			}
			catch(NumberFormatException se){
				System.out.println("Incorrect input!!! Please enter numbers");
			} 

	}
	}
	
	//to place order
	public static void orderDetails(){
		try {
		scan = new Scanner(System.in);
		UseCustomer useCustomer = new UseCustomer();
		UseItem useItem = new UseItem();
		UseOrder useOrder = new UseOrder();
		System.out.println("\nEnter Customer No: ");
		int cno = Integer.parseInt(scan.next());
		if(useCustomer.isCustomer(cno)) {               //customer validation
			Random rand = new Random(); 
			int orderNo = rand.nextInt(99999);
			List<Order> OrderList1=null;
			List<Customer> CustomerList1=null;
			CustomerList1=useCustomer.searchCustomer(cno);
			printCustomersInFormat(CustomerList1,"Customer Details");
			while(true) {
				System.out.println("____________________________");
				System.out.println("\nEnter 0 to Main menu or ");
				System.out.println("Enter Item No: ");
				String ino = scan.next();
				if(Integer.parseInt(ino)==0) break;
				if(useItem.isItem(Integer.parseInt(ino)))               //item validation
				{
					System.out.println("\nDescription: "+useItem.searchItem(ino).get(0).getDescription());
					while(true)
					{
					System.out.println("____________________________");
					System.out.println("\nEnter 0 to change item or ");
					System.out.println("Enter Quantity: ");
					int qno = Integer.parseInt(scan.next());
					if(qno==0) break;
					if(useItem.isQuant(Integer.parseInt(ino), qno))                 //quantity check
					{
						if(useOrder.placeOrder(cno,orderNo,Integer.parseInt(ino),qno))
						{
							System.out.println("Order PLACED succesfully");
							logger.info("Order PLACED succesfully");
							OrderList1=useOrder.searchOrder(orderNo);
							printOrdersInFormat(OrderList1,"Total Orders Till Now");
							System.out.println("\n#Total Amount: "+useOrder.calcTotal(orderNo));
						}
						else {
							System.out.println("Order FAILED!!");
							logger.warning("Order FAILED!!");
						}		
					}
					else {
						System.out.println("Quantity Not Available !!");
						logger.warning("Quatity Not Available !!");
					}
				}
				}
				else {
					System.out.println("Item Does not Exist");
					
				}
			}
			
		}
		else {
			System.out.println("Customer Does not Exist");

		}
		
	}
		catch (MyException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//to show order details
public static void showOrderDetails(){
	try {   
		boolean f=true;
		UseOrder useOrder = new UseOrder();
		scan2 = new Scanner(System.in);
		while(f) {
			
		System.out.println("\n_________# Show Order Menu #___________");
		System.out.println("To show all orders,             Enter 1");
		System.out.println("To show order by order no,      Enter 2");
		System.out.println("Back to main menu,              Enter 0");
		System.out.println("_______________________________________");
		System.out.println("\nEnter your choice:");
		int choice = Integer.parseInt(scan2.next());
		switch(choice){
		case 1: List<Order> orderList=null;
				orderList=useOrder.viewAllOrders();
				printOrdersInFormat(orderList,"Order History");
				logger.info("Order History");
		break;
		case 2: System.out.println("\nEnter Order No: ");
				int osearch = Integer.parseInt(scan2.next());
				List<Order> orderList1=null;
				orderList1=useOrder.searchOrder(osearch);
				printOrdersInFormat(orderList1,"Search Reult");
				System.out.println("\n#Total Amount: "+useOrder.calcTotal(osearch));
				logger.info("Order by order number");
		break;
		case 0: f=false;
		break;
		default: System.out.println("Incorrect input!!! Please re-enter choice from our menu");
		}
		
		
	}
	}
		catch (MyException e) {

			System.out.println(e.getMessage());
		}
		
		
	}
	
	//to show item details & to modify items
	public static void itemDetails(){
		
		try
		{
			List<Item> itemList=null;
		boolean f=true;
		scan3 = new Scanner(System.in);
		System.out.println("\n_____# Item Details #____");
		UseItem useItem = new UseItem();
		itemList=useItem.viewAllItems();
		printItemInFormat(itemList,"Available Item");
		while(f){
			try
			{
			System.out.println("\n_______# Item Menu #_______");
			System.out.println("\nAdd Item,           Enter 1");
			System.out.println("Modify Item,        Enter 2");
			System.out.println("Delete Item,        Enter 3");
			System.out.println("Search Item,        Enter 4");
			System.out.println("Back to main menu,  Enter 0");
			System.out.println("____________________________");
			System.out.println("\nEnter your choice:");
			int choice = Integer.parseInt(scan3.next());

			switch(choice){
			case 1: System.out.println("\nEnter item to add (Enter Item Number or Category)");
					scan3.nextLine();
					String iadd = scan3.nextLine();
					List<Item> i2=null;
					i2=useItem.searchItem(iadd);
					printItemInFormat(i2,"Current Status");
					System.out.println("\nEnter Quantity to add: ");
					String iqty = scan3.nextLine();
					if(useItem.addItemQuantity(Integer.parseInt(iqty), iadd))
					{
						System.out.println("Item "+iadd+" Quantity added");
						logger.info("Item Added");
					}	
					else
						System.out.println("Item no "+iadd+" does not exist");
			break;
			case 2: System.out.println("\nEnter Item Number to Modify");
					scan3.nextLine(); 
					String imod = scan3.nextLine();
					List<Item> i=null;
					i=useItem.searchItem(imod);
					printItemInFormat(i,"Current Item");
					boolean f1=true;
					while(f1){
						try {
					System.out.println("\n_______# Modify Menu #_______");
					System.out.println("\nModify Description,  Enter 1");
					System.out.println("Modify Price,        Enter 2");
					System.out.println("Back to item menu,   Enter 0");
					System.out.println("____________________________");
					System.out.println("\nEnter your choice: ");
					int mchoice = Integer.parseInt(scan3.nextLine());
					switch(mchoice) {
					case 1: System.out.println("\nEnter Description: ");
							String moddes = scan3.nextLine();
							if(useItem.modifyItemDescription(moddes, Integer.parseInt(imod)))
							{
								System.out.println("Item no "+imod+" Description MODIFIED");
								logger.info("Description Modified");
							}
							break;
					case 2: System.out.println("\nEnter Price: ");
							scan3.nextLine();
							Double modprc = Double.parseDouble(scan3.nextLine());
							if(useItem.modifyItemPrice(modprc, Integer.parseInt(imod)))
							{
								System.out.println("Item no "+imod+" Price MODIFIED");
								logger.info("Price Modified");
							}
							break;
					case 0: f1=false;
					break;
					default: System.out.println("Incorrect input!!! Please re-enter choice from our menu");
					}
					}
						catch(Exception se){
							System.out.println(se.getMessage()+" Incorrect input!!! Please enter numbers");
						}
					}
					
					
			break;
			case 3: System.out.println("\nEnter Item Number to Delete");
					try {
					scan3.nextLine();
					int idel= Integer.parseInt(scan3.nextLine());
					if(useItem.deleteItem(idel))
					{
						System.out.println("Item no "+idel+" DELETED");
						logger.info("Item Deleted");
					}
					else
						System.out.println("Item no "+idel+" does not exist");
					}
					catch(Exception se){
						System.out.println("Incorrect input!!! Please enter numbers");
					}
					
			break;
			case 4: System.out.println("\nEnter item to search (Enter Number or Category)");
					scan3.nextLine();
					String isearch = scan3.nextLine();
					List<Item> i1=null;
					i1=useItem.searchItem(isearch);
					printItemInFormat(i1,"Search Result");
					logger.info("Item Search");
			break;
			case 0: f=false;
			break;
			default: System.out.println("Incorrect input!!! Please re-enter choice from our menu");
			}
		}
			catch(MyException se){
				System.out.println(se.getMessage());
			}
	}
	}
		catch(MyException se){
			System.out.println(se.getMessage());
		}
		
	}
	

  //to show customer details
public static void showCustomerDetails(){
	try {
		boolean f=true;
		UseCustomer useCustomer = new UseCustomer();
		scan4 = new Scanner(System.in);
		while(f) {
		System.out.println("\n_________# Show Customer Menu #___________");
		System.out.println("To show all Customers,             Enter 1");
		System.out.println("To show Customer by Customer no,   Enter 2");
		System.out.println("Back to main menu,                 Enter 0");
		System.out.println("__________________________________________");
		System.out.println("\nEnter your choice:");
		int choice = Integer.parseInt(scan4.next());
		switch(choice){
		case 1: List<Customer> CustomerList=null;
				CustomerList=useCustomer.viewAllCustomers();
				printCustomersInFormat(CustomerList,"Customer History");
				logger.info("Customer Details");
		break;
		case 2: System.out.println("\nEnter Customer No: ");
				int osearch = Integer.parseInt(scan4.next());
				List<Customer> CustomerList1=null;
				CustomerList1=useCustomer.searchCustomer(osearch);
				printCustomersInFormat(CustomerList1,"Search Reult");
				logger.info("Customer Details by no");
		break;
		case 0: f=false;
		break;
		default: System.out.println("Incorrect input!!! Please re-enter choice from our menu");
		}
	}
	}
		catch (MyException e) {
			System.out.println(e.getMessage());
		}	
	}

	//to print items in tabular format
public static void printItemInFormat(List<Item> itemList, String str){
	
	System.out.println("\n_______________________# "+str+" #________________________");
	System.out.format("%7s  %15s  %20s  %6s  %8s","Item No","Category","Description","Price","Quantity");
	System.out.println("\n________________________________________________________________");
	for(Item i: itemList)
	{
		System.out.format("%7d  %15s  %20s  %6.2f  %8d",i.getItem_no(),i.getCategory(),i.getDescription(),i.getPrice(),i.getQuantity());
		System.out.println();
	}
	System.out.println("________________________________________________________________");
	
}
	//to print orders in tabular format
public static void printOrdersInFormat(List<Order> orderList, String str){
	
	System.out.println("\n_______________________# "+str+" #________________________");
	System.out.format("%8s  %7s  %8s  %10s  %11s","Order No","Item No","Quantity","Date","Customer No");
	System.out.println("\n________________________________________________________________");
	for(Order i: orderList)
	{
		System.out.format("%8d  %7d  %8d  %10s  %11s",i.getOrderNo(),i.getItem_no(),i.getOrder_qty(),i.getOrder_date(),i.getCustNo());
		System.out.println();
	}
	System.out.println("________________________________________________________________");
	
}

//to print customers in tabular format
public static void printCustomersInFormat(List<Customer> CustomerList, String str){
	
	System.out.println("\n_______________________# "+str+" #________________________");
	System.out.format("%11s  %25s  %25s","Customer No","Customer Name","Address");
	System.out.println("\n________________________________________________________________");
	for(Customer i: CustomerList)
	{
		System.out.format("%11s  %25s  %25s",i.getCustNo(),i.getCustName(),i.getAddress());
		System.out.println();
	}
	System.out.println("________________________________________________________________");
	
}
   
public static void setMyLog()                  //initializing logger
{
	 
    try {  

        // This block configure the logger with handler and formatter  
        fh = new FileHandler("F:/MyLogFile.log",true);  
        logger.addHandler(fh);
        logger.setUseParentHandlers(false);
        SimpleFormatter formatter = new SimpleFormatter();  
        fh.setFormatter(formatter);   

    } catch (SecurityException e) {   
      
    } catch (IOException e) {  
       
    }  

}

}


