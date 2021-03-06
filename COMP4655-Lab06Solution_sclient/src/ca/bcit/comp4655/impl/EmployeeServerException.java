
package ca.bcit.comp4655.impl;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "EmployeeServerException", targetNamespace = "http://www.bcit.ca/comp4655/employee/types/")
public class EmployeeServerException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ca.bcit.comp4655.employee.types.EmployeeServerException faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public EmployeeServerException(String message, ca.bcit.comp4655.employee.types.EmployeeServerException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public EmployeeServerException(String message, ca.bcit.comp4655.employee.types.EmployeeServerException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: ca.bcit.comp4655.employee.types.EmployeeServerException
     */
    public ca.bcit.comp4655.employee.types.EmployeeServerException getFaultInfo() {
        return faultInfo;
    }

}
