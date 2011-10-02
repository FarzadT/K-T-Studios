package main;

import server.myServer;

public class Main 
{
	public static void main(String[] args)
	{
		myServer server = new myServer();
		
		try 
		{
			server.start();
		} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
