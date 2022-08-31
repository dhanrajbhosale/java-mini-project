package shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UseItem {

	/**
	   * This is method to view all Items
	   * @return Item List
	   * @exception MyException
	   */
	public List<Item> viewAllItems() throws MyException
	{
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		List<Item> ItemList=null;	
		try 
		{
			new MyConnection();
			connection = MyConnection.setConnection();
			ItemList=new ArrayList<Item>();

			if (connection != null) 
			{
				String query="SELECT * FROM Item_xxxx";
				preparedStatement = connection.prepareStatement(query);
				resultSet=preparedStatement.executeQuery();
				while(resultSet.next())
				{
					Item Item=new Item(resultSet.getInt("item_no"),resultSet.getString("description"), resultSet.getString("category"), resultSet.getDouble("price"), resultSet.getInt("qty"));
					ItemList.add(Item);
				}

			}

		}
		catch(SQLException se){
			throw new MyException(se.getMessage()+" Error in connecting");
		}

		return ItemList;
	}

	 /**
	   * This is to search item by item number
	   * @param isearch item number to search
	   * @return item List
	   * @exception MyException
	   */
	public List<Item> searchItem(String isearch) throws MyException
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
				try {
					int isno=Integer.parseInt(isearch);
					query="select * FROM Item_xxxx WHERE item_no="+isno;
				}
				catch(NumberFormatException ex){  
					query="select * FROM Item_xxxx WHERE CATEGORY='"+isearch+"'";
				}  
				preparedStatement = connection.prepareStatement(query);
				int rowCount=preparedStatement.executeUpdate();
				if(rowCount>0)
				{
					List<Item> ItemList1=new ArrayList<Item>();	
					resultSet=preparedStatement.executeQuery();
					while(resultSet.next())
					{
						Item Item=new Item(resultSet.getInt("item_no"),resultSet.getString("description"), resultSet.getString("category"), resultSet.getDouble("price"), resultSet.getInt("qty"));
						ItemList1.add(Item);
					}
					return ItemList1;
				}
				else 
				{
					throw new MyException("Item: "+isearch+" does not exist");
				}

			}
		}
		catch(SQLException se){

			throw new MyException(se.getMessage()+" Error in connecting");
		}
		return null;
	}

	 /**
	   * This is to modify Item description 
	   * @param str Description string
	   * @param imod item number to modify
	   * @return boolean true if modified successfully 
	   * @exception MyException
	   */
	public boolean modifyItemDescription(String str,int imod) throws MyException
	{
		boolean status=false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			new MyConnection();
			connection = MyConnection.setConnection();
			if (null != connection) 
			{
				String query=null;
				query="update item_xxxx set description='"+str+"' where item_no="+imod;
				preparedStatement = connection.prepareStatement(query);
				int rowCount=preparedStatement.executeUpdate();
				if(rowCount>0)
					status = true;
				else {
					status = false;
				}
			}
		}
		catch(SQLException se){
			throw new MyException(se.getMessage()+" Error in connecting");
		}
		return status;
	}
	
	/**
	   * This is to modify Item price 
	   * @param prc double price to modify
	   * @param imod item number to modify
	   * @return boolean true if modified successfully 
	   * @exception MyException
	   */
	public boolean modifyItemPrice(double prc,int imod) throws MyException
	{
		boolean status=false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			new MyConnection();
			connection = MyConnection.setConnection();
			if (null != connection) 
			{
				String query=null;
				query="update item_xxxx set price='"+prc+"' where item_no="+imod;
				preparedStatement = connection.prepareStatement(query);
				int rowCount=preparedStatement.executeUpdate();
				if(rowCount>0)
					status = true;
				else {
					status = false;
				}
			}
		}
		catch(SQLException se){
			throw new MyException(se.getMessage());
		}

		return status;
	}
	
	/**
	   * This is to add items i.e. to increase quantity
	   * @param iqty quantity number
	   * @param imod item number to add quantity
	   * @return boolean true if modified successfully 
	   * @exception MyException
	   */
	public boolean addItemQuantity(int iqty ,String iadd) throws MyException
	{
		boolean status=false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			new MyConnection();
			connection = MyConnection.setConnection();
			if (null != connection) 
			{
				String query=null;
				try {
					int iaddno=Integer.parseInt(iadd);
					query="update item_xxxx set qty=qty+'"+iqty+"' where item_no="+iaddno;
				}
				catch(NumberFormatException ex){  
					query="update Item_xxxx set qty=qty+'"+iqty+"' WHERE CATEGORY='"+iadd+"'";
				}  
				preparedStatement = connection.prepareStatement(query);
				int rowCount=preparedStatement.executeUpdate();
				if(rowCount>0)
					status = true;
				else {
					status = false;
				}
			}
		}
		catch(SQLException se){
			throw new MyException(se.getMessage());
		}

		return status;
	}
	

	/**
	   * This returns true if order quantity is 
	   * less than or equal to available quantity
	   * @param isearch item number to search
	   * @param rqnt required order quantity
	   * @return boolean
	   * @exception MyException
	   */
public boolean isQuant(int isearch,int rqnt) throws MyException
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
			query="select qty FROM Item_xxxx WHERE Item_no="+isearch;  
			preparedStatement = connection.prepareStatement(query);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				if(resultSet.getInt("qty")>=rqnt)
					return true;
					return false;
			}

		}
	}
	catch(SQLException se){
		throw new MyException(se.getMessage()+" Error in connecting");
	}
	return false;
}
	
/**
 * This returns true if item exist in database
 * @param isearch item number to search
 * @return boolean 
 * @exception MyException
 */
public boolean isItem(int isearch) throws MyException
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
			query="select * FROM Item_xxxx WHERE Item_no="+isearch;  
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

/**
 * This is to delete item from database
 * @param Item_no Item number to delete
 * @return boolean returns true if deleted successfully
 * @exception MyException
 */
	public boolean deleteItem(int Item_no) throws MyException
	{
		boolean status=false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			new MyConnection();
			connection = MyConnection.setConnection();
			if (null != connection) {
				String query="DELETE FROM Item_xxxx WHERE Item_no=?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1,Item_no);
				int rowCount=preparedStatement.executeUpdate();
				if(rowCount>0)
					status = true;
				else {
					status = false;
				}
			}
		}
		catch(SQLException se){
			throw new MyException(se.getMessage()+" Error in connecting");
		}
		return status;
	}


}
