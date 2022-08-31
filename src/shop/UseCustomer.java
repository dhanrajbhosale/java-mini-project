package shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UseCustomer {
	
	  /**
	   * This is method to view all customers
	   * @return Customer List
	   * @exception MyException
	   */
	public List<Customer> viewAllCustomers() throws MyException
	{
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		List<Customer> CustomerList=null;	
		try 
		{
			new MyConnection();
			connection = MyConnection.setConnection();
			CustomerList=new ArrayList<Customer>();

			if (connection != null) 
			{
				String query="SELECT * FROM Customer_xxxx";
				preparedStatement = connection.prepareStatement(query);
				resultSet=preparedStatement.executeQuery();
				while(resultSet.next())
				{
					Customer Customer=new Customer(resultSet.getString("custno"),resultSet.getString("custname"), resultSet.getString("address"));
					CustomerList.add(Customer);
				}
			}

		}
		catch(SQLException se){
			throw new MyException(se.getMessage()+" Error in connecting");
		}

		return CustomerList;
	}
	
	 /**
	   * This is to search customer by customer number
	   * @param csearch customer number to search
	   * @return customer List
	   * @exception MyException
	   */
	public List<Customer> searchCustomer(int csearch) throws MyException
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
				query="select * FROM Customer_xxxx WHERE Custno="+csearch;  
				preparedStatement = connection.prepareStatement(query);
				int rowCount=preparedStatement.executeUpdate();
				if(rowCount>0)
				{
					List<Customer> CustomerList1=new ArrayList<Customer>();	
					resultSet=preparedStatement.executeQuery();
					while(resultSet.next())
					{
						Customer Customer=new Customer(resultSet.getString("custno"),resultSet.getString("custname"), resultSet.getString("address"));
						CustomerList1.add(Customer);
					}
					return CustomerList1;
				}
				else 
				{
					throw new MyException("Customer: "+csearch+" does not exist");
				}
			}
		}
		catch(SQLException se){
			throw new MyException(se.getMessage()+" Error in connecting");
		}
		return null;
	}
	
	 /**
	   * This returns true if customer exist in database
	   * @param csearch customer number to search
	   * @return boolean 
	   * @exception MyException
	   */
	public boolean isCustomer(int csearch) throws MyException
	{
		Connection connection = null;
		boolean status=false;
		PreparedStatement preparedStatement = null;
		try {
			new MyConnection();
			connection = MyConnection.setConnection();
			if (null != connection) 
			{
				String query=null;
				query="select * FROM Customer_xxxx WHERE Custno="+csearch;  
				preparedStatement = connection.prepareStatement(query);
				int rowCount=preparedStatement.executeUpdate();
				if(rowCount>0)
				{
					return status=true;
				}
			}
		}
		catch(SQLException se){
			throw new MyException(se.getMessage()+" Error in connecting");
		}
		return status;
	}

}
