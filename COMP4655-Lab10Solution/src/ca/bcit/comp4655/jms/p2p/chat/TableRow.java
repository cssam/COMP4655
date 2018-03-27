package ca.bcit.comp4655.jms.p2p.chat;

import java.util.ArrayList;
import java.util.List;

public class TableRow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 List<ArrayList<Integer>> table = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= 10; i++) {
          	List<Integer> row = new ArrayList<Integer>();
          	for (int j = 0; j <= 10; j++)
            	row.add(i * j);
          	table.add(row);
        }
        for (List<Integer> row : table)
        		System.out.println(row);

	}

}
