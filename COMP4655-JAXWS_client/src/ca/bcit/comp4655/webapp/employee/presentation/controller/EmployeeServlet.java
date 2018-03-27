package ca.bcit.comp4655.webapp.employee.presentation.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;

import javax.xml.ws.WebServiceRef;
import ca.bcit.comp4655.employee.xml.*;

/**
 * 
 * @author aghavami
 *
 */
public class EmployeeServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1380213578838938048L;
	private static final String COMMANDS_FILE = "/WEB-INF/classes/commands.properties";
//	private static final String COMMANDS_FILE = "/data/commands.properties";

	
	// Initial command
	private static final String SERVICE_INIT_ACTION = "service.getEmployees";
	
	private Logger logger = Logger.getLogger( EmployeeServlet.class );
	private static Properties commandsProps;
	
	@WebServiceRef( wsdlLocation="http://localhost:8181/employeeServices?wsdl")
	private static EmployeeServiceImplService service;

	@Override
	public void init() throws ServletException 
	{
		super.init();
		commandsProps = new Properties();
		try 
		{
			service = new EmployeeServiceImplService();
			
			String commandString = getServletContext().getRealPath( COMMANDS_FILE ) ;
			System.out.println(commandString);
			commandsProps.load( new FileInputStream(commandString) );
			System.out.println("commands: "+commandsProps.size());
			
		}
		catch (FileNotFoundException e) 
		{
			logger.fatal("Can't find " + COMMANDS_FILE, e );
			System.err.println(e);
		} 
		catch (IOException e) 
		{
			logger.fatal( "Problem loading " + COMMANDS_FILE , e );
			System.err.println(e);
		}
		catch ( Exception e )
		{
			logger.fatal( "something went wrong " + e );
			System.err.println(e);
		}
	};
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
												throws ServletException, IOException 
	{
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response ) 
														throws ServletException, IOException 
	{
		
		
		String action = request.getParameter( "action" );
		System.out.println("action: "+action);
		try 
		{	
			/* Locate the command and execute it */
			
			Command c = getCommand( action);
			c.execute(request, service.getEmployeeServiceImplPort() );
			response.setCharacterEncoding( "UTF-8" );
			gotoPage("/index.jsp", request, response);
			
		} 
		catch ( Exception e ) 
		{
			logger.error( "An exception occurred processing an action request : " + e );
			request.setAttribute("errorTitle", e );
			request.setAttribute( "errorMsg", e.getCause()!=null?e.getCause().getMessage():e.getMessage() );
			e.printStackTrace();
			gotoPage("/errors.jsp", request, response);
		}
	}
	
	private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) 
																	throws ServletException, IOException
	{
		System.out.println("address: "+address);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher( address );
		dispatcher.forward( request, response );
	}
	
	private Command getCommand(String action) throws Exception 
	{

		/* First time in, call initialisation command */
		if ( action == null || action.trim().equals("") ) 
		{
			action = SERVICE_INIT_ACTION;	
		} 
		
		/* Simple factory for the commands */
		String clazz = commandsProps.getProperty( action );
		System.out.println("clazz: "+clazz);
		Command command  = (Command) Class.forName( clazz ).newInstance();
		
		return command;
		
	}
}
