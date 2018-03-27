
package ca.bcit.comp4655.webapp.employee.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "addEmployee", namespace = "http://service.employee.webapp.comp4655.bcit.ca/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addEmployee", namespace = "http://service.employee.webapp.comp4655.bcit.ca/")
public class AddEmployee {

    @XmlElement(name = "arg0", namespace = "")
    private ca.bcit.comp4655.webapp.employee.domain.Employee arg0;

    /**
     * 
     * @return
     *     returns Employee
     */
    public ca.bcit.comp4655.webapp.employee.domain.Employee getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(ca.bcit.comp4655.webapp.employee.domain.Employee arg0) {
        this.arg0 = arg0;
    }

}
