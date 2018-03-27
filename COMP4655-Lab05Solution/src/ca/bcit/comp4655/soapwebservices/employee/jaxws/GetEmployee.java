
package ca.bcit.comp4655.soapwebservices.employee.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getEmployee", namespace = "http://employee.soapwebservices.comp4655.bcit.ca/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getEmployee", namespace = "http://employee.soapwebservices.comp4655.bcit.ca/")
public class GetEmployee {

    @XmlElement(name = "employeeID", namespace = "")
    private int employeeID;

    /**
     * 
     * @return
     *     returns int
     */
    public int getEmployeeID() {
        return this.employeeID;
    }

    /**
     * 
     * @param employeeID
     *     the value for the employeeID property
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

}
