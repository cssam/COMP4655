package ca.bcit.comp4655.employee.rest.client;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;


import ca.bcit.comp4655.employee.rest.bean.Employee;
import ca.bcit.comp4655.employee.rest.bean.ResponseCode;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;

public class RESTEmployeeClient {

	public static void main(String[] args) 
	{
		//Creating an instance of a client
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create( config );
		
		ResponseCode response;
		
		WebResource service = client.resource( getBaseURI() );
		
		// Get XML findEmployee
		Employee employee = service.path("/findEmployee/A01234599").get(Employee.class);
		System.out.println(employee.getFirstName());
		//service.path("/findEmployee/A01234599").accept(MediaType.TEXT_XML);

		//post XML updateEmployee
		Form form = new Form();
		form.add ( "firstname", "Chandima" );
		form.add ( "lastname", "Samaraweera" );
		form.add ( "id", "A00558231" );
		form.add ( "dob", "1966-01-06" );
		
		Employee oldemployee = new Employee();
		oldemployee.setFirstName("Homer");
		oldemployee.setLastName("Simpson");
		oldemployee.setId("A01234599");
		try {
			String string = "1974-05-12";
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(string);
			oldemployee.setDob(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response = service.path( "/employeeUpdate/"+oldemployee ).entity(form).post(ResponseCode.class);
		//post( Message.class, form );
		//System.out.println( response.getDesc() );
		service.path( "/employeeUpdate/Employe oldemployee" ).entity(form);
		
		// addEmployee			
		//put
		form = new Form();
		form.add ( "firstname", "Chan" );
		form.add ( "lastname", "Sam" );
		form.add ( "id", "A00558232" );
		form.add ( "dob", "1966-01-06" );
		
		response =  service.path( "/addEmployee" ).accept( MediaType.TEXT_XML ).entity(form).put(ResponseCode.class);
		System.out.println( response.getDesc() );
	}

	private static URI getBaseURI()
	{
		return UriBuilder.fromUri("http://localhost:8080/Lab07Solution_server/").build();
	}


}
