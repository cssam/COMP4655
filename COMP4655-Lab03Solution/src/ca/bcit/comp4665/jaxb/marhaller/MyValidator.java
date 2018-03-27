package ca.bcit.comp4665.jaxb.marhaller;

import java.io.File;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import ca.bcit.comp4655.jaxb.helloworld.GreetingListType;
import ca.bcit.comp4655.jaxb.helloworld.GreetingType;

public class MyValidator {

public static void main(String[] args) {
		
		try {
			JAXBContext jc = JAXBContext.newInstance("ca.bcit.comp4655.jaxb.helloworld");
			
			Unmarshaller u = jc.createUnmarshaller();
			
			SchemaFactory sFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			sFactory.newSchema(new File("src/greeting.xsd"));
			
			Schema mySchema = sFactory.newSchema(new File("src/greeting.xsd"));
			u.setSchema(mySchema);
			
			JAXBElement<GreetingListType> element = (JAXBElement<GreetingListType>)u.unmarshal(new File("src/greeting2.xml"));
		
			GreetingListType greetingListType = element.getValue();
			
	
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
