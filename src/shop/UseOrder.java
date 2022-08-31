package shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UseOrder {
	
	  /**
	   * This is method to view all orders
	   * @return Order List
	   * @exception MyException
	   */
	public List<Order> viewAllOrders() throws MyException
	{
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		List<Order> OrderList=null;	
		try 
		{
			new MyConnection();
			connection = MyConnection.setConnection();
			OrderList=new ArrayList<Order>();

			if (connection != null) 
			{
				String query="SELECT * FROM Order_xxxx";
				preparedStatement = connection.prepareStatement(query);
				resultSet=preparedStatement.executeQuery();
				while(resultSet.next())
				{
					Order Order=new Order(resultSet.getInt("Orderno"),resultSet.getInt("item_no"), resultSet.getInt("ord_qty"), resultSet.getString("ord_date"), resultSet.getString("custno"));
					OrderList.add(Order);
				}

			}

		}
		catch(SQLException se){
			throw new MyException(se.getMessage()+" Error in connecting");
		}

		return OrderList;
	}
	
	  /**
	   * This is to search order by order number
	   * @param osearch order number to search
	   * @return Order List
	   * @exception MyException
	   */
	public List<Order> searchOrder(int osearch) throws MyException
	{
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {

			new MyConnection();
			connection = MyConnection.setConnection();

			if (null != connection) 
			{
				String query=null;
				query="select * FROM Order_xxxx WHERE Orderno="+osearch;  
				preparedStatement = connection.prepareStatement(query);
				int rowCount=preparedStatement.executeUpdate();
				if(rowCount>0)
				{
					List<Order> OrderList1=new ArrayList<Order>();	
					resultSet=preparedStatement.executeQuery();
					while(resultSet.next())
					{
						Order Order=new Order(resultSet.getInt("Orderno"),resultSet.getInt("item_no"), resultSet.getInt("ord_qty"), resultSet.getString("ord_date"), resultSet.getString("custno"));
						OrderList1.add(Order);
					}
					return OrderList1;
				}
				else 
				{
					throw new MyException("Order: "+osearch+" does not exist");
				}

			}
		}


		catch(SQLException se){

			throw new MyException(se.getMessage()+" Error in connecting");
		}

		return null;
	}
	
	  /**
	   * This is to place order. It will add order to order_xxxx table
	   * & at the same time it will decrease item quantity from item_xxxx table in database
	   * @param custno Customer number
	   * @param orderno Order number
	   * @param item_no item no 
	   * @param order_qty order quantity
	   * @return boolean return true if order placed
	   * @exception MyException
	   */
	public boolean placeOrder(int custno,int orderno,int item_no,int order_qty) throws MyException
	{
		boolean status=false;
		String date=LocalDate.now().toString();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		UseItem useItem =new UseItem();
		String itmno =Integer.toString(item_no);
		try {
			new MyConnection();
			connection = MyConnection.setConnection();
			if (null != connection) 
			{
				if(useItem.addItemQuantity(-order_qty,itmno))
				{
					String query=null;
					query="insert into order_xxxx values("+orderno+","+item_no+","+order_qty+",'"+date+"','"+custno+"')";
					preparedStatement = connection.prepareStatement(query);
					int rowCount=preparedStatement.executeUpdate();
					if(rowCount>0)
						status = true;
					else {
						status = false;
					}
				}
				
			}
		}
		catch(SQLException se){
			throw new MyException(se.getMessage()+" Error in connecting");
		}
		return status;
		
		
	}
	
	  /**
	   * This is to calculate total amount of perticular order
	   * @param orderno order number
	   * @return double Total Amount of that order
	   * @exception MyException
	   */
	public double calcTotal(int orderno) throws MyException{
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		int totalamt=0;
		try 
		{
			new MyConnection();
			connection = MyConnection.setConnection();
			if (connection != null) 
			{
				String query="SELECT sum(o.ord_qty*i.price) as TOTAL FROM item_xxxx i,order_xxxx o WHERE i.item_no=o.item_no and o.orderno="+orderno;
				preparedStatement = connection.prepareStatement(query);
				resultSet=preparedStatement.executeQuery();
				while(resultSet.next())
				{
					totalamt =resultSet.getInt("TOTAL");
					return totalamt;
				}
			}
		}
		catch(SQLException se){
			throw new MyException(se.getMessage()+" Error in connecting");
		}
		return totalamt;
	}
	
	
}
