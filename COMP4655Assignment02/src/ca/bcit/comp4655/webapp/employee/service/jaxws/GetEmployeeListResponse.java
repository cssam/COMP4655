
package ca.bcit.comp4655.webapp.employee.service.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getEmployeeListResponse", namespace = "http://service.employee.webapp.comp4655.bcit.ca/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getEmployeeListResponse", namespace = "http://service.employee.webapp.comp4655.bcit.ca/")
public class GetEmployeeListResponse {

    @XmlElement(name = "return", namespace = "")
    private List<ca.bcit.comp4655.webapp.employee.domain.Employee> _return;

    /**
     * 
     * @return
     *     returns List<Employee>
     */
    public List<ca.bcit.comp4655.webapp.employee.domain.Employee> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<ca.bcit.comp4655.webapp.employee.domain.Employee> _return) {
        this._return = _return;
    }

}
