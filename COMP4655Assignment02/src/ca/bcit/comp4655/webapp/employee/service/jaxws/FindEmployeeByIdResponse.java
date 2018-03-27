
package ca.bcit.comp4655.webapp.employee.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "findEmployeeByIdResponse", namespace = "http://service.employee.webapp.comp4655.bcit.ca/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findEmployeeByIdResponse", namespace = "http://service.employee.webapp.comp4655.bcit.ca/")
public class FindEmployeeByIdResponse {

    @XmlElement(name = "return", namespace = "")
    private ca.bcit.comp4655.webapp.employee.domain.Employee _return;

    /**
     * 
     * @return
     *     returns Employee
     */
    public ca.bcit.comp4655.webapp.employee.domain.Employee getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(ca.bcit.comp4655.webapp.employee.domain.Employee _return) {
        this._return = _return;
    }

}
