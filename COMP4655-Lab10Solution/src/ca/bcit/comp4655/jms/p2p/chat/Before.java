package ca.bcit.comp4655.jms.p2p.chat;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Before {

	public Before() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		before();
	}
	
    public static void before() {
        Set set = new TreeSet();
        set.add("2");
        set.add(3);
        set.add("1");
        Iterator it = set.iterator();
        while (it.hasNext())
        	System.out.print(it.next() + " ");

}

}
