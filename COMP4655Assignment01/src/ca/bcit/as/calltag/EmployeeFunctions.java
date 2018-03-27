package ca.bcit.as.calltag;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import ca.bcit.as.beans.Employee;

public class EmployeeFunctions {
	
	private static  File employeeFile = new File(EmployeeFunctions.class.getResource("/Employees.txt").getFile());
	private static String regex="\\t";
	private static List<Employee> employeeList;
	
	public static List<Employee> getEmployees() {
		
		employeeList = new ArrayList<Employee>(); 
		try {
			
			Scanner inFile1 = new Scanner(employeeFile);
			while(inFile1.hasNext()) {
				String alineString = inFile1.nextLine();
				String[] columnData = alineString.split(regex);
				Employee record = new Employee();
				record.setId(columnData[0]);
				record.setFirstName(columnData[1]);
				record.setLastName(columnData[2]);
				record.setDob(columnData[3]);
				employeeList.add(record);
			}
			inFile1.close();
			
           
			} catch (FileNotFoundException e) {
				System.err.println("FileStreamsRead: " + e);
			}
		return employeeList;
		
	}
	
	public static Employee findEmployee(String id) {
		
		if(employeeList == null || employeeList.isEmpty()) {
			getEmployees();
		}
		if(id != null) {
			
			for (Employee employee : employeeList) {
				if(employee.getId().equals(id)) {
					return employee;
				}
			}
			return null;
		}
		return null;

	}
	
	public static String putEmployee(Employee employee) {
		
		if(findEmployee(employee.getId()) == null) {
			employeeList.add(employee);
			rewrite();
			return "Adding Success";
		} else {
			return "Sorry Employee Exists";
		}
	}
	
	public static void deleteEmployee(String[] ids) {
		
		if(employeeList == null || employeeList.isEmpty()) {
			getEmployees();
		}
		for (String id : ids) {
			System.out.println("id received: "+id);
			int location = Integer.parseInt(id);
			if(!employeeList.isEmpty() && employeeList.size() >= location ) {
				employeeList.remove(location-1);
				System.out.println("location deleted: "+(location-1));
			} else {
				System.out.println("location not deleted: "+(location-1));
			}
		}
		rewrite();
		getEmployees();
	}
	
	private static void rewrite() {
		try {

			FileWriter out = new FileWriter(employeeFile);
			if(!employeeList.isEmpty()) {
				Iterator<Employee> iter = employeeList.iterator();
				while(iter.hasNext()) {
				    Employee employee = iter.next();
				    String csq = employee.getId()+"\t"+employee.getFirstName()+"\t"+employee.getLastName()+"\t"+employee.getDob()+"\n";
					out.write(csq);
				}
			}
			
			out.close();
			
		} catch (FileNotFoundException e) {
			 System.err.println("FileStreamsWrite: " + e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
