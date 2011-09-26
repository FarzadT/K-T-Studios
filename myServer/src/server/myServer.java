package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandler.Context;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.nio.SelectChannelConnector;

public class myServer 
{
	private static String HOST = "localhost";
	private static int PORT = 8080;
	
	private static int TPOOL_MIN = 5;
	private static int TPOOL_MAX = 50;
	
	private static Server _server;
	private static AbstractHandler _contexts;
	
	public myServer(String pHost, int pPort, 
					int pMinTPool, int pMaxTPool)
	{
		HOST = pHost;
		PORT = pPort;
		
		TPOOL_MIN = pMinTPool;
		TPOOL_MAX = pMaxTPool;
		
		init();
	}
	
	public myServer()
	{
		init();
	}
	
	private void init()
	{
		_server = new Server();
		
		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setHost(HOST);
		connector.setPort(PORT);
		
		_server.addConnector(connector);
		
		_contexts = new ContextHandlerCollection();
	}
		
	public void start()
	{
		
	}
	/*
	 * FOR TESTING/DEBUGGING
	 */
	public static void main(String[] args) throws Exception
	{
		
	}
}
