package ca.bcit.comp4665.jaxb.marhaller;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


import ca.bcit.comp4655.jaxb.helloworld.GreetingListType;
import ca.bcit.comp4655.jaxb.helloworld.GreetingType;

public class MyUnMarshaller {

	public static void main(String[] args) {
		
		try {
			JAXBContext jc = JAXBContext.newInstance("ca.bcit.comp4655.jaxb.helloworld");
			Unmarshaller u = jc.createUnmarshaller();
			JAXBElement<GreetingListType> element = (JAXBElement<GreetingListType>)u.unmarshal(new File("src/greeting2.xml"));
		
			GreetingListType greetingListType = element.getValue();
			
			
			//GreetingListType greetingListType2 = (GreetingListType)u.unmarshal(new File("src/greeting.xml"));
			
			
			List<GreetingType> mylist = greetingListType.getGreeting();
			for(GreetingType greetingType : mylist) {
				System.out.println(greetingType.getLanguage()+":"+greetingType.getText());
			}
			
	
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
