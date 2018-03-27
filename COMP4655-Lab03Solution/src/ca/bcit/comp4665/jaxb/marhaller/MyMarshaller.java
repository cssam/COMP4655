package ca.bcit.comp4665.jaxb.marhaller;

import java.io.File;
import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;


import ca.bcit.comp4655.jaxb.helloworld.GreetingListType;
import ca.bcit.comp4655.jaxb.helloworld.GreetingType;
import ca.bcit.comp4655.jaxb.helloworld.ObjectFactory;

public class MyMarshaller {
	
	public static void main(String[] args) {
		
			try {
				JAXBContext jc = JAXBContext.newInstance("ca.bcit.comp4655.jaxb.helloworld");

				ObjectFactory factory = new ObjectFactory();
				GreetingListType flist = factory.createGreetingListType();
				JAXBElement<GreetingListType> element = factory.createGreetings(flist);
				
				GreetingType sinhalaType = factory.createGreetingType();
				sinhalaType.setLanguage("SI");
				sinhalaType.setText("ආයුබෝවන්");
				
				flist.getGreeting().add(sinhalaType);
				
				GreetingType chineseType = factory.createGreetingType();
				chineseType.setLanguage("ZH");
				chineseType.setText("你好");
				
				flist.getGreeting().add(chineseType);
				
				Marshaller m = jc.createMarshaller();
				m.marshal(element, new File("src/greeting3.xml"));
				
				
//				SchemaFactory sFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//				sFactory.newSchema(new File("src/greeting.xsd"));
//				Schema mySchema = sFactory.newSchema(new File("src/greeting.xsd"));
//				m.setSchema(mySchema);
//				m.marshal(mySchema, new File("src/greeting3.xml"));
				
				
				
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
