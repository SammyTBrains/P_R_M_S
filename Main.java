package com.p_r_m_s;

import com.p_r_m_s.model.DataSource;

public class Main {
	
	 static DataSource DS;
	public static void main(String[] args) {
		try{
			DS= new DataSource();
		DS.openConnection();
	
		if(DS!= null) {
				DS.closeConnetion();
				
			}
		}catch(Exception e) {
			System.out.println("Something went wrong: " +e.getMessage());
			e.printStackTrace();
	}
	}
	
	
	


}
