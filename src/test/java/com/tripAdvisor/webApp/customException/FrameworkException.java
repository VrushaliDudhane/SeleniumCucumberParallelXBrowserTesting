/**
 * 
 */
package com.tripAdvisor.webApp.customException;

/**
 * This is the custome framework exception 
 * @author Vrushali
 *
 */
public class FrameworkException extends RuntimeException{
	public FrameworkException()
	{
		super();
	}
	
	public FrameworkException(String message)
	{
		super(message);
	}

}
