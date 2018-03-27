package ca.bcit.comp4655.ejb.stateless.client;

import java.io.IOException;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.bcit.comp4655.ejb.statelss.calculator.Calculator;
import ca.bcit.comp4655.ejb.statelss.calculator.CalculatorLocal;
import ca.bcit.comp4655.ejb.statelss.calculator.CalculatorRemote;




/**
 * 
 * CalcServlet creates a web client for <code>CalculatorBean</code>.
 */
public class CalcServlet extends HttpServlet
{
	//Using dependency injection to obtain a reference to CalculatorBean
//	@EJB( mappedName="CalculatorEJB", beanInterface=CalculatorLocal.class)
//	Calculator calc;
	
	private static final long serialVersionUID = -1936970755491232742L;
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		
		/*
		 * In addition to dependency injection using @EJB, We can also use JNDI binding for the remote and local interfaces.
		 *  By default, JBoss will use "ejbName/local" and "ejbName/remote" for local and remote interfaces, respectively.
		 */
		
		
		ResponseCode responseCode = new ResponseCode();

        try {
        	Calculator calc = getCalculatorBean();
        	
	            String operandOne = request.getParameter( "number1" );
	            String operandTwo = request.getParameter( "number2" );
	            String operation = request.getParameter("operation");
	            
	            
	            if ( operandOne!=null && 
	            	 operandTwo!=null &&
	            	 operandOne.length()>0 &&
	            	 operandTwo.length()>0 )
	            {
	            	int operand1 = Integer.parseInt( operandOne );
		            int operand2 = Integer.parseInt( operandTwo );
		            
		            String descString = "Number1 = "+operandOne+", Number2 = "+operandTwo+", Operation="+operation;
		            int code = 0;
		            
		            if(operation.equals("add")) {
		            	code = calc.add( operand1, operand2 );	
		            } else  if(operation.equals("sub")) {
		            	code = calc.sub( operand1, operand2 );
		            } else  if(operation.equals("mul")) {
		            	code = calc.mul( operand1, operand2 );
		            } else  if(operation.equals("divide")) {
		            	code = calc.div( operand1, operand2 );
		            }
		            
		            responseCode.setDesc( descString);
		            responseCode.setCode(Integer.toString(code));
		            request.setAttribute( "addResponseCode", responseCode);
	            }
	            else
	            {
	            	responseCode.setCode( "error." +"null operands"+ ".code" );
	    			responseCode.setDesc( "error." +"null operands" + ".desc" );
	    			request.setAttribute( "addResponseCode", responseCode);
	            }
        }
        catch ( NumberFormatException e )
        {
        	responseCode.setCode( "error." + e.getMessage() + ".code" );
			responseCode.setDesc( "error." + e.getMessage() + ".desc" );
			request.setAttribute( "addResponseCode", responseCode);
        } 
			catch (NamingException e) {
        	e.printStackTrace();
        	responseCode.setCode( "error." + e.getMessage() + ".code" );
			responseCode.setDesc( "error." + e.getMessage() + ".desc" );
			request.setAttribute( "addResponseCode", responseCode);
		} 
        finally 
        {
        	gotoPage("/index.jsp", request, response);
        }
	}
	
	private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		System.out.println("address: "+address);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher( address );
		dispatcher.forward( request, response );
	}
	
	@Override
	protected void doPost( HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doGet(request, response);
	}
	
	private Calculator getCalculatorBean() throws NamingException
	{
		InitialContext context = new InitialContext();
		Calculator calc = ( Calculator) context.lookup( "java:module/CalculatorBean!ca.bcit.comp4655.ejb.statelss.calculator.CalculatorLocal" );
		
		return calc;
	}
}
