package ca.bcit.comp4655.jms.p2p.chat;

public class ToDos {
	
	 String day;
    
	 ToDos(String d) { 
    	 day = d; 
     }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToDos other = (ToDos) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		return true;
	}
     
//	 public boolean equals(Object o) {
//       return ((ToDos)o).day == this.day;
//     }
//     
//	 public int hashCode() { return 9; }
	 
	 

}
