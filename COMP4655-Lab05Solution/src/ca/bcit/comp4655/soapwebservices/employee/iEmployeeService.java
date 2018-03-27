package ca.bcit.comp4655.soapwebservices.employee;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebParam.Mode;

import ca.bcit.comp4655.soapwebservices.domain.Employee;

@WebService
public interface iEmployeeService {
	
	@WebMethod
	Employee getEmployee(@WebParam(name="employeeID",mode=Mode.IN)int employeeId);

}
