package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

public class myServer 
{
	private static String HOST = "localhost";
	private static int PORT = 9090;
	
	private static int TPOOL_MIN = 5;
	private static int TPOOL_MAX = 50;
	
	private static Server _server;
	private static ContextHandlerCollection _contexts;
	
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
		_server.setHandler(_contexts);
		
		QueuedThreadPool qtp = new QueuedThreadPool();
		qtp.setMinThreads(TPOOL_MIN);
		qtp.setMaxThreads(TPOOL_MAX);
		
		_server.setThreadPool(qtp);
		
		initContexts();
	}
		
	private void initContexts() 
	{
		initResourceHandler();	
	}

	/**
	 * Method used to initialize all of the resource handlers
	 */
	private void initResourceHandler() 
	{
		//Add the HTML resource handler
		addResourceHandler("./site/html/", "/html", true);
		
		//Add the CSS Resource Handler
		addResourceHandler("./site/css/", "/css", true);
		
		//Add the images resource handler
		addResourceHandler("./site/images/", "/images", true);
		
		//Add the javascript resource handler
		addResourceHandler("./site/js/", "/js", true);
	}
	
	/**
	 * 
	 * @param pResourceBase
	 * @param pContext
	 * @param pListDirectories
	 */
	private void addResourceHandler(String pResourceBase, String pContext, boolean pListDirectories)
	{
		ResourceHandler rh = new ResourceHandler();
		rh.setDirectoriesListed(pListDirectories);
		rh.setResourceBase(pResourceBase);
		
		ContextHandler context = new ContextHandler(_contexts, pContext);
		context.setHandler(rh);
	}

	/**
	 * 
	 * @throws Exception
	 */
	public void start() throws Exception
	{
		_server.start();
		_server.join();
	}
	/*
	 * FOR TESTING/DEBUGGING
	 */
	public static void main(String[] args) throws Exception
	{
		
	}
}
