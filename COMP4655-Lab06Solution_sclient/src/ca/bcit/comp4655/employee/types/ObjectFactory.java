
package ca.bcit.comp4655.employee.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ca.bcit.comp4655.employee.types package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Employee_QNAME = new QName("http://www.bcit.ca/comp4655/employee/types/", "employee");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ca.bcit.comp4655.employee.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ResponseCode }
     * 
     */
    public ResponseCode createResponseCode() {
        return new ResponseCode();
    }

    /**
     * Create an instance of {@link FindEmployeeByIdResponse }
     * 
     */
    public FindEmployeeByIdResponse createFindEmployeeByIdResponse() {
        return new FindEmployeeByIdResponse();
    }

    /**
     * Create an instance of {@link Employee }
     * 
     */
    public Employee createEmployee() {
        return new Employee();
    }

    /**
     * Create an instance of {@link GetEmployeeList }
     * 
     */
    public GetEmployeeList createGetEmployeeList() {
        return new GetEmployeeList();
    }

    /**
     * Create an instance of {@link GetEmployeeListResponse }
     * 
     */
    public GetEmployeeListResponse createGetEmployeeListResponse() {
        return new GetEmployeeListResponse();
    }

    /**
     * Create an instance of {@link EmployeeServerException }
     * 
     */
    public EmployeeServerException createEmployeeServerException() {
        return new EmployeeServerException();
    }

    /**
     * Create an instance of {@link FindEmployeeById }
     * 
     */
    public FindEmployeeById createFindEmployeeById() {
        return new FindEmployeeById();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Employee }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcit.ca/comp4655/employee/types/", name = "employee")
    public JAXBElement<Employee> createEmployee(Employee value) {
        return new JAXBElement<Employee>(_Employee_QNAME, Employee.class, null, value);
    }

}
