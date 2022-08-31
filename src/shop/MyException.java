package shop;

public class MyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	   * User Defined Exception
	   */
	public MyException(String message){
		super(message);
		//System.out.println(message);
	}
}
 