package com.p_r_m_s.model;


import java.sql.Connection;




import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;


public class DataSource {
	public static Scanner scanner= new Scanner(System.in);



	private static final String Db_Name="P_R_M_S.db"; 
	private static final String Db_Connection="jdbc:sqlite:"+Db_Name;

	private static final String Table_Data="CREATE TABLE IF NOT EXISTS \"Patients_Data\" (\r\n" + 
			"	\"Patient_ID\"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\r\n" + 
			"	\"First_Name\"	TEXT,\r\n" + 
			"	\"Last_Name\"	TEXT,\r\n" + 
			"	\"Phone_number\"	INTEGER,\r\n" + 
			"	\"Date_of_registration\"	DATETIME DEFAULT CURRENT_TIMESTAMP,\r\n" + 
			"	\"Marital_Status\"	TEXT,\r\n" + 
			"	\"Health_Conditions\"	TEXT\r\n" + 
			")";
	private static final String Table_Activities="CREATE TABLE IF NOT EXISTS \"Patients_Activities\" (\r\n" + 
			"	\"Patients_ID\"	INTEGER NOT NULL,\r\n" + 
			"	\"Date\"	DATETIME DEFAULT CURRENT_TIMESTAMP,\r\n" + 
			"	\"Reason_For_Visit\"	TEXT,\r\n" + 
			"	\"Prescriptions\"	TEXT,\r\n" + 
			"	FOREIGN KEY(\"Patients_ID\") REFERENCES \"Patients_Data\"(\"Patient_ID\")\r\n" + 
			")";

	private static final String Patients_Data_Table="Patients_Data";
	private static final String Data_Patients_ID="Patient_ID";
	private static final String First_Name="First_Name";
	private static final String Last_Name="Last_Name";
	private static final String Phone_Number="Phone_Number";
	private static final String Date_Of_Registration="Date_Of_Registration";
	private static final String Marital_Status="Marital_Status";
	private static final String Health_Conditions="Health_Conditions";

	private final String Insert_Patient_Data=
			"INSERT INTO "+Patients_Data_Table+"('"+First_Name+"', '"+Last_Name+"', '"+
					Phone_Number+"', '"+
					Marital_Status+"', '"+Health_Conditions+"') VALUES (?, ?, ?, ?, ?)";

	private final String Insert_Patient_Activities="INSERT INTO "+Patients_Activities_Table+"('"+Patients_ID+"', '"+
			Reason_For_Visit+"', '"+Prescriptions+"') VALUES ( ?, ?, ?)";



	private final String Record_Patients_Data_Update= "UPDATE "+Patients_Data_Table+ " SET "+First_Name+"= ?, "+Last_Name+"= ?, "+
			Phone_Number+"= ?, "+Marital_Status+"= ?, "
			+Health_Conditions+"= ? WHERE "+Data_Patients_ID+"= ?";

	private final String Record_Patients_Activities_Update= "UPDATE "+Patients_Activities_Table+" SET "
			+Reason_For_Visit+"= ?, "+Prescriptions+"= ? WHERE "+Patients_ID+"= ? AND "+Date+"= ?";

	private final String Patients_Data_Column_First_Name= "UPDATE "+Patients_Data_Table+" SET "+First_Name+"= ? WHERE "
			+Data_Patients_ID+"= ?";
	private final String Patients_Data_Column_Last_Name= "UPDATE "+Patients_Data_Table+" SET "+Last_Name+"= ? WHERE "
			+Data_Patients_ID+"= ?";
	private final String Patients_Data_Column_Phone_Number= "UPDATE "+Patients_Data_Table+" SET "+Phone_Number+"= ? WHERE "
			+Data_Patients_ID+"= ?";
	private final String Patients_Data_Column_Marital_Status= "UPDATE "+Patients_Data_Table+" SET "+Marital_Status+"= ? WHERE "
			+Data_Patients_ID+"= ?";
	private final String Patients_Data_Column_Health_Conditions= "UPDATE "+Patients_Data_Table+" SET "+Health_Conditions+"= ? WHERE "
			+Data_Patients_ID+"= ?";
	
	private final String Patients_Activities_Column_PID_Date= "UPDATE "+Patients_Activities_Table+" SET "+Patients_ID+"= ? WHERE "
			+Date+"= ?";
	private final String Patients_Activities_Column_PID_RFV= "UPDATE "+Patients_Activities_Table+" SET "+Patients_ID+"= ? WHERE "
			+Reason_For_Visit+"= ? AND "+Date+"= ?";
	private final String Patients_Activities_Column_PID_Prescription= "UPDATE "+Patients_Activities_Table+" SET "+Patients_ID+"= ? WHERE "
			+Prescriptions+"= ? AND "+Date+"= ?";
	private final String Patients_Activities_Column_Reason_For_Visit= "UPDATE "+Patients_Activities_Table+" SET "+Reason_For_Visit+"= ? WHERE "
			+Patients_ID+"= ? AND "+Date+"= ?";
	private final String Patients_Activities_Column_Prescriptions= "UPDATE "+Patients_Activities_Table+" SET "+Prescriptions+"= ? WHERE "
			+Patients_ID+"= ? AND "+Date+"= ?";

	private static final String Patients_Activities_Table="Patients_Activities";
	private static final String Patients_ID="Patients_ID";
	private static final String Date="Date";
	private static final String Reason_For_Visit="Reason_For_Visit";
	private static final String Prescriptions="Prescriptions";

	private final String Search_Patient_Data_Record= "SELECT * FROM "+Patients_Data_Table+" WHERE "+Data_Patients_ID+"= ?";
	private final String Search_PD_First_Name= "SELECT "+First_Name+" FROM "+Patients_Data_Table+" WHERE "+Data_Patients_ID+"= ?";
	private final String Search_PD_Last_Name= "SELECT "+Last_Name+" FROM "+Patients_Data_Table+" WHERE "+Data_Patients_ID+"= ?";
	private final String Search_PD_Phone_Number= "SELECT "+Phone_Number+" FROM "+Patients_Data_Table+" WHERE "+Data_Patients_ID+"= ?";
	private final String Search_PD_Date_Of_Registration= "SELECT "+Date_Of_Registration+" FROM "+Patients_Data_Table+" WHERE "+Data_Patients_ID+"= ?";
	private final String Search_PD_Marital_Status= "SELECT "+Marital_Status+" FROM "+Patients_Data_Table+" WHERE "+Data_Patients_ID+"= ?";
	private final String Search_PD_Health_Conditions= "SELECT "+Health_Conditions+" FROM "+Patients_Data_Table+" WHERE "+Data_Patients_ID+"= ?";

	private final String Search_Patient_Activities_Record= "SELECT * FROM "+Patients_Activities_Table+" WHERE "+Patients_ID+"= ?";
	private final String Search_PA_Date= "SELECT "+Date+" FROM "+Patients_Activities_Table+" WHERE "+Patients_ID+"= ?";
	private final String Search_PA_Reason_For_Visit= "SELECT "+Reason_For_Visit+","+Date+" FROM "+Patients_Activities_Table+" WHERE "+Patients_ID+"= ?";
	private final String Search_PA_Prescriptions= "SELECT "+Prescriptions+","+Date+" FROM "+Patients_Activities_Table+" WHERE "+Patients_ID+"= ?";

	private final String Delete_PD_Table= "DELETE FROM "+Patients_Data_Table;
	private final String Delete_PD= "DELETE FROM "+Patients_Data_Table+" WHERE "+Data_Patients_ID+"= ?";
	private final String Delete_All_PA_Table= "DELETE FROM "+Patients_Activities_Table;
	private final String Delete_All_PA= "DELETE FROM "+Patients_Activities_Table+" WHERE "+Patients_ID+"= ?";
	private final String Delete_PA= "DELETE FROM "+Patients_Activities_Table+" WHERE "+Patients_ID+"= ? AND "+Date+"= ?";
	
	private final String Display_PD= "SELECT * FROM "+Patients_Data_Table;
	private final String Display_PA= "SELECT * FROM "+Patients_Activities_Table;
	



	private static  Connection conn; 
	private static PreparedStatement statement;
	private static PreparedStatement statement2;
	



	public Connection openConnection() {
		try {
			conn= DriverManager.getConnection(Db_Connection);
			statement= conn.prepareStatement(Table_Data);

			statement2= conn.prepareStatement(Table_Activities);
			
			menu();
			return conn;
		}catch(SQLException e) {
			System.out.println("Couldn't connect to database: "+ e.getMessage());
			e.printStackTrace();
			return null;
		}catch(Exception e2) {
			System.out.println("Something went wrong: "+e2.getMessage());
			e2.printStackTrace();
			return null;
		}

	}
	private void menu() {
		System.out.println("Always input your date in this format: 2020-01-01, 2020-02-01 e.t.c");
		System.out.println("What would you like to do? \n1- Insert new record\n2- Update existing record\n3- Display all records in a table\n4- Search for record\n5- Delete a record");
		int CheckInput= scanner.nextInt();
		if(CheckInput== 1) {
			inputPatientData();
		}else if(CheckInput== 2) { 
			Update();
		}else if(CheckInput== 3) {
			 displayRecords();
		}else if(CheckInput== 4) {
			search();
		}else if(CheckInput== 5) {
			delete();
		}
	}
	public PreparedStatement DefaultInsert() {
		try{
			System.out.println("Type 1 to go to menu and 2 to input values(It's advisable to have inputed values before going to menu) \nNote: The app automatically "
					+ "adds a new ID when a new patient record is inserted so you can't add an ID yourself ");
			int CheckInput= scanner.nextInt();
			if(CheckInput== 1) {
				menu();

			}else if(CheckInput== 2) {
				System.out.println("Insert patient's data in this order: First Name, Last Name, "
						+ "Phone number, Marital Status, Health Conditions");
				statement= conn.prepareStatement(Insert_Patient_Data);


				statement.setString(1, scanner.nextLine());
				statement.setString(2, scanner.nextLine());
				statement.setString(3, scanner.nextLine());
				statement.setString(4, scanner.nextLine());
				statement.setString(5, scanner.nextLine());


				statement.executeUpdate();

				System.out.println("Inserted Successfully! \n");
				menu();
				statement.close();
				return statement;
			}

		}catch(Exception e) {
			System.out.println("Couldn't input data: "+e.getMessage());
			e.printStackTrace();
			return null;
		}
		return null;
	}
	public PreparedStatement inputPatientData() {

		try {
			System.out.println("What table do you want to insert your data:\n 1- Patients Data \n 2- Patients Activities"
					+ "\n3- To return to menu");
			int CheckTable= scanner.nextInt();
			scanner.nextLine();
			if(CheckTable== 1) {
				System.out.println("Insert patient's data in this order: First Name, Last Name, "
						+ "Phone number, Marital Status, Health Conditions");
				statement= conn.prepareStatement(Insert_Patient_Data);

				statement.setString(1, scanner.nextLine());
				statement.setString(2, scanner.nextLine());
				statement.setString(3, scanner.nextLine());
				statement.setString(4, scanner. nextLine());
				statement.setString(5, scanner.nextLine());

				statement.executeUpdate();
				System.out.println("Inserted Successfully! \n");
				menu();
				statement.close();
				return statement;

			}else if(CheckTable== 2) { System.out.println("Insert patient's activites in this order: Patient_ID, Reason for visit, Prescriptions");

			statement2= conn.prepareStatement(Insert_Patient_Activities);
			statement2.setInt(1, scanner.nextInt());
			scanner.nextLine();
			statement2.setString(2, scanner.nextLine());
			statement2.setString(3, scanner.nextLine());

			statement2.executeUpdate();
			System.out.println("Inserted Successfully! \n");
			menu();
			statement2.close();
			return statement2;
			}else if(CheckTable== 3) {
				menu();
			}

		}catch(Exception e) {
			System.out.println("Couldn't input data: "+e.getMessage());
			e.printStackTrace();

		}return null;
	}

	public void closeConnetion() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("Couldn't close Database: "+ e.getMessage());
				e.printStackTrace();
			}
		}
	}
	public PreparedStatement Update() {
		System.out.println("what do you want to update? \n1- Update an entire record\n2- Update a data in a record\nNote: You "
				+ "can't change a Patient's ID \nType 3 to return to menu");
		int CheckUpdate= scanner.nextInt();
		scanner.nextLine();
		try {
			if(CheckUpdate== 1) {
				System.out.println("What table do you want to update? \n1- Patients Data \n2- Patients Activities");
				int CheckTableUpdate= scanner.nextInt();
				scanner.nextLine();
				if(CheckTableUpdate== 1) {
					System.out.println("Type in your new data in this order: First Name, Last Name, " + 
							"Phone number, Marital Status, Health Conditions ");
					PreparedStatement statement3= conn.prepareStatement(Record_Patients_Data_Update);
					statement3.setString(1, scanner.nextLine());
					statement3.setString(2, scanner.nextLine());
					statement3.setString(3, scanner.nextLine());
					statement3.setString(4, scanner.nextLine());
					statement3.setString(5, scanner.nextLine());


					System.out.println("Now Input patient's ID");
					statement3.setInt(6, scanner.nextInt());
					int row= statement3.executeUpdate();
					if(row!= 0) {
						System.out.println("Updated Successfully! \n");
					}else {
						System.out.println("Couldn't update! Record doesn't exist or your input is wrong\n");
					}
					menu();
					statement3.close();
					return statement3;
				}else if(CheckTableUpdate== 2) {
					System.out.println("Type in your new data in this order: Reason for visit, Prescriptions");
					PreparedStatement statement4= conn.prepareStatement(Record_Patients_Activities_Update);

					statement4.setString(1, scanner.nextLine());
					statement4.setString(2, scanner.nextLine());
					

					System.out.println("Now Input patient's ID");
					statement4.setInt(3, scanner.nextInt());
					scanner.nextLine();
					System.out.println("Input date and time of activity");
					statement4.setString(4, scanner.nextLine());
					int row= statement4.executeUpdate();
					if(row!= 0) {
						System.out.println("Updated Successfully! \n");
					}else {
						System.out.println("Couldn't update! Record doesn't exist or your input is wrong\n");
					}
					menu();
					statement4.close();
					return statement4;
				}
			}else if(CheckUpdate== 2) {
				System.out.println("What table's column do you want to update: \n1- Patients Data \n2- Patients Activities\nNote you can't "
						+ "update more than one column at a time");
				int CheckUpdate3= scanner.nextInt();
				if(CheckUpdate3== 1) {
					System.out.println("What column do you want to update: \n1- First Name\n2- Last Name\n3- Phone Number"
							+ "\n4- Marital Status\n5- Health Conditions");
					int CheckDataColumn= scanner.nextInt();
					scanner.nextLine();
					PreparedStatement statementDcolumn;
					if(CheckDataColumn== 1) {
						System.out.println("Input your update");
						statementDcolumn= conn.prepareStatement(Patients_Data_Column_First_Name);
						statementDcolumn.setString(1, scanner.nextLine());
						System.out.println("Now input your patient's ID");
						statementDcolumn.setInt(2, scanner.nextInt());
						int row= statementDcolumn.executeUpdate();
						if(row!= 0) {
							System.out.println("Updated Successfully! \n");
						}else {
							System.out.println("Couldn't update! Record doesn't exist or your input is wrong\n");
						}
						menu();
						statementDcolumn.close();
						return statementDcolumn;
					}else if(CheckDataColumn== 2) {
						System.out.println("Input your update");
						statementDcolumn = conn.prepareStatement(Patients_Data_Column_Last_Name);
						statementDcolumn.setString(1, scanner.nextLine());
						System.out.println("Now input your patient's ID");
						statementDcolumn.setInt(2, scanner.nextInt());
						int row= statementDcolumn.executeUpdate();
						if(row!= 0) {
							System.out.println("Updated Successfully! \n");
						}else {
							System.out.println("Couldn't update! Record doesn't exist or your input is wrong\n");
						}
						menu();
						statementDcolumn.close();
						return statementDcolumn;
					}else if(CheckDataColumn== 3) { 
						System.out.println("Input your update");
						statementDcolumn= conn.prepareStatement(Patients_Data_Column_Phone_Number);
						statementDcolumn.setString(1, scanner.nextLine());
						System.out.println("Now input your patient's ID");
						statementDcolumn.setInt(2, scanner.nextInt());
						int row= statementDcolumn.executeUpdate();
						if(row!= 0) {
							System.out.println("Updated Successfully! \n");
						}else {
							System.out.println("Couldn't update! Record doesn't exist or your input is wrong\n");
						}
						menu();
						statementDcolumn.close();
						return statementDcolumn;
					}else if(CheckDataColumn== 4) { 
						System.out.println("Input your update");
						statementDcolumn= conn.prepareStatement(Patients_Data_Column_Marital_Status);
						statementDcolumn.setString(1, scanner.nextLine());
						System.out.println("Now input your patient's ID");
						statementDcolumn.setInt(2, scanner.nextInt());
						int row= statementDcolumn.executeUpdate();
						if(row!= 0) {
							System.out.println("Updated Successfully! \n");
						}else {
							System.out.println("Couldn't update! Record doesn't exist or your input is wrong\n");
						}
						menu();
						statementDcolumn.close();
						return statementDcolumn;
					}else if(CheckDataColumn== 5) {
						System.out.println("Input your update");
						statementDcolumn= conn.prepareStatement(Patients_Data_Column_Health_Conditions);
						statementDcolumn.setString(1, scanner.nextLine());
						System.out.println("Now input your patient's ID");
						statementDcolumn.setInt(2, scanner.nextInt());
						int row= statementDcolumn.executeUpdate();
						if(row!= 0) {
							System.out.println("Updated Successfully! \n");
						}else {
							System.out.println("Couldn't update! Record doesn't exist or your input is wrong\n");
						}
						menu();
						statementDcolumn.close();
						return statementDcolumn;
					}
				}else if(CheckUpdate3== 2) {
					System.out.println("What column do you want to update: \n1- Patients ID\n2- Reason For Visit\n3- Prescription");
					int CheckActivitieColumn= scanner.nextInt();
					scanner.nextLine();
					PreparedStatement statementAcolumn;
					if(CheckActivitieColumn== 1) {
						System.out.println("Do you want\n1- Update with date and time of activity\n2- Reason for visit\n3- Prescriptions");
						int CheckUwith= scanner.nextInt();
						scanner.nextLine();
						if(CheckUwith== 1) {
							System.out.println("Input your update");
							statementAcolumn= conn.prepareStatement(Patients_Activities_Column_PID_Date);
							statementAcolumn.setInt(1, scanner.nextInt());
							scanner.nextLine();
							System.out.println("Input date and time of activity");
							statementAcolumn.setString(2, scanner.nextLine());
							int row= statementAcolumn.executeUpdate();
							if(row!= 0) {
								System.out.println("Updated Successfully! \n");

							}else {
								System.out.println("Couldn't update! Record doesn't exist or your input is wrong\n");
							}
							menu();
							statementAcolumn.close();
							return statementAcolumn;

						}else if(CheckUwith== 2){
							System.out.println("Input your update");
							statementAcolumn= conn.prepareStatement(Patients_Activities_Column_PID_RFV);
							statementAcolumn.setInt(1, scanner.nextInt());
							scanner.nextLine();
							System.out.println("Now input reason for visit");
							statementAcolumn.setString(2, scanner.nextLine());
							System.out.println("Input date and time of activity");
							statementAcolumn.setString(3, scanner.nextLine());
							int row= statementAcolumn.executeUpdate();
							if(row!= 0) {
								System.out.println("Updated Successfully! \n");
							}else {
								System.out.println("Couldn't update! Record doesn't exist or your input is wrong\n");
							}
							menu();
							statementAcolumn.close();
							return statementAcolumn;
						}else if(CheckUwith== 3) {
							System.out.println("Input your update");
							statementAcolumn= conn.prepareStatement(Patients_Activities_Column_PID_Prescription);
							statementAcolumn.setInt(1, scanner.nextInt());
							scanner.nextLine();
							System.out.println("Now input prescriptions");
							statementAcolumn.setString(2, scanner.nextLine());
							System.out.println("Input date and time of activity");
							statementAcolumn.setString(3, scanner.nextLine());
							int row= statementAcolumn.executeUpdate();
							if(row!= 0) {
								System.out.println("Updated Successfully! \n");
							}else {
								System.out.println("Couldn't update! Record doesn't exist or your input is wrong\n");
							}
							menu();
							statementAcolumn.close();
							return statementAcolumn;
						}
					}else if(CheckActivitieColumn== 2) {
						System.out.println("Input your update");
						statementAcolumn= conn.prepareStatement(Patients_Activities_Column_Reason_For_Visit);
						statementAcolumn.setString(1, scanner.nextLine());
						System.out.println("Now input your patient's ID");
						statementAcolumn.setInt(2, scanner.nextInt());
						scanner.nextLine();
						System.out.println("Input date and time of activity");
						statementAcolumn.setString(3, scanner.nextLine());
						int row= statementAcolumn.executeUpdate();
						if(row!= 0) {
							System.out.println("Updated Successfully! \n");
						}else {
							System.out.println("Couldn't update! Record doesn't exist or your input is wrong\n");
						}
						menu();
						statementAcolumn.close();
						return statementAcolumn;
					}else if(CheckActivitieColumn== 3) {
						System.out.println("Input your update");
						statementAcolumn= conn.prepareStatement(Patients_Activities_Column_Prescriptions);
						statementAcolumn.setString(1, scanner.nextLine());
						System.out.println("Now input your patient's ID");
						statementAcolumn.setInt(2, scanner.nextInt());
						scanner.nextLine();
						System.out.println("Input date and time of activity");
						statementAcolumn.setString(3, scanner.nextLine());
						int row= statementAcolumn.executeUpdate();
						if(row!= 0) {
							System.out.println("Updated Successfully! \n");
						}else {
							System.out.println("Couldn't update! Record doesn't exist or your input is wrong\n");
						}
						menu();
						statementAcolumn.close();
						return statementAcolumn;
					}
				}
			}else if(CheckUpdate== 3) {
				menu();
			}




		}catch(Exception e) {
			System.out.println("Couldn't update record: "+ e.getMessage());
			e.printStackTrace();
			return null;
		}
		return null;

	}
	public void displayRecords() {
		try {
			System.out.println("What table do you want to display \n1- Patients Data\n2- Patients Activities");
			int CheckTable= scanner.nextInt();
			if(CheckTable== 1) {
				PreparedStatement statementPD= conn.prepareStatement(Display_PD);
				ResultSet RS= statementPD.executeQuery();
				System.out.println("Search Successful! \n");
				while(RS.next()) {
					System.out.println(RS.getInt(Data_Patients_ID)+"|"+RS.getString(First_Name)+"|"+RS.getString(Last_Name)+"|"+RS.getString(Phone_Number)
					+"|"+RS.getString(Date_Of_Registration)+"|"+RS.getString(Marital_Status)+"|"+RS.getString(Health_Conditions));
				}System.out.println("\n");
				menu();
				statementPD.close();
			}else if(CheckTable== 2) {
				PreparedStatement statementPA= conn.prepareStatement(Display_PA);
				ResultSet RS= statementPA.executeQuery();
				System.out.println("Search Successful! \n");
				while(RS.next()) {
					System.out.println(RS.getInt(Patients_ID)+"|"+RS.getString(Date)+"|"+RS.getString(Reason_For_Visit)+"|"+RS.getString(Prescriptions));
				}System.out.println("\n");
				menu();
				statementPA.close();
			}
		}catch(Exception e) {
			
		}
	}
	public void search() {

		try {
			System.out.println("What do you want to search for? \n1- An entire record\n2- A particular data\n3- to return to menu");
			int CheckUReply= scanner.nextInt();
			if(CheckUReply== 1){
				System.out.println("In what table do you want to search? \n1- Patients Data\n2- Patients Activities");
				int CheckTable= scanner.nextInt();
				if(CheckTable== 1) {
					System.out.println("Input patient's ID");
					PreparedStatement statementPdata= conn.prepareStatement(Search_Patient_Data_Record);
					statementPdata.setInt(1, scanner.nextInt());
					ResultSet RS= statementPdata.executeQuery();
					while(RS.next()) {
						System.out.println("Search Successful! \n"+RS.getString(First_Name)+"|"+RS.getString(Last_Name)+"|"
								+RS.getString(Phone_Number)+"|"+
								RS.getString(Date_Of_Registration)+"|"+RS.getString(Marital_Status)+"|"+RS.getString(Health_Conditions)+" \n");

					}
					menu();
					statementPdata.close();

				}else if(CheckTable== 2) {
					System.out.println("Input patient's ID");
					PreparedStatement statementPactivities= conn.prepareStatement(Search_Patient_Activities_Record);
					statementPactivities.setInt(1, scanner.nextInt());
					ResultSet RS= statementPactivities.executeQuery();
					System.out.println("Search Successful! \n");
					while(RS.next()) {
						System.out.println(RS.getString(Date)+"|"+RS.getString(Reason_For_Visit)+"|"
								+RS.getString(Prescriptions));
					}
					menu();
					statementPactivities.close();

				}


			}else if(CheckUReply== 2) {
				System.out.println("In what table do you want to search? \n1- Patients Data\n2- Patients Activities");
				int CheckR= scanner.nextInt();
				if(CheckR== 1) {
					System.out.println("In what column do you want to search: \n1- First Name\n2- Last Name\n3- Phone Number\n4- Date Of Registration\n5- "
							+ "Marital Status\n6- Health Conditions");
					int CheckColumn= scanner.nextInt();
					if(CheckColumn== 1) {
						System.out.println("Input patient's ID");
						PreparedStatement statementPdata= conn.prepareStatement(Search_PD_First_Name);
						statementPdata.setInt(1, scanner.nextInt());
						ResultSet RS= statementPdata.executeQuery();
						while(RS.next()) {
							System.out.println("Search Successful! \n"+RS.getString(First_Name)+" \n");

						}
						menu();
						statementPdata.close();

					}else if(CheckColumn== 2){
						System.out.println("Input patient's ID");
						PreparedStatement statementPdata= conn.prepareStatement(Search_PD_Last_Name);
						statementPdata.setInt(1, scanner.nextInt());
						ResultSet RS= statementPdata.executeQuery();
						while(RS.next()) {
							System.out.println("Search Successful! \n"+RS.getString(Last_Name)+" \n");

						}
						menu();
						statementPdata.close();

					}else if(CheckColumn== 3){
						System.out.println("Input patient's ID");
						PreparedStatement statementPdata= conn.prepareStatement(Search_PD_Phone_Number);
						statementPdata.setInt(1, scanner.nextInt());
						ResultSet RS= statementPdata.executeQuery();
						while(RS.next()) {
							System.out.println("Search Successful! \n"+RS.getString(Phone_Number)+" \n");

						}
						menu();
						statementPdata.close();

					}else if(CheckColumn== 4){
						System.out.println("Input patient's ID");
						PreparedStatement statementPdata= conn.prepareStatement(Search_PD_Date_Of_Registration);
						statementPdata.setInt(1, scanner.nextInt());
						ResultSet RS= statementPdata.executeQuery();
						while(RS.next()) {
							System.out.println("Search Successful! \n"+RS.getString(Date_Of_Registration)+" \n");

						}
						menu();
						statementPdata.close();

					}else if(CheckColumn== 5){
						System.out.println("Input patient's ID");
						PreparedStatement statementPdata= conn.prepareStatement(Search_PD_Marital_Status);
						statementPdata.setInt(1, scanner.nextInt());
						ResultSet RS= statementPdata.executeQuery();
						while(RS.next()) {
							System.out.println("Search Successful! \n"+RS.getString(Marital_Status)+" \n");

						}
						menu();
						statementPdata.close();

					}else if(CheckColumn== 6){
						System.out.println("Input patient's ID");
						PreparedStatement statementPdata= conn.prepareStatement(Search_PD_Health_Conditions);
						statementPdata.setInt(1, scanner.nextInt());
						ResultSet RS= statementPdata.executeQuery();
						while(RS.next()) {
							System.out.println("Search Successful! \n"+RS.getString(Health_Conditions)+" \n");

						}
						menu();
						statementPdata.close();

					}

				}else if(CheckR== 2) {
					System.out.println("In what column do you want to search: \n1- Date\n2- Reason For Visit\n3- Prescription");
					int CheckCA= scanner.nextInt();
					if(CheckCA== 1) {
						System.out.println("Input patient's ID");
						PreparedStatement statementPactivities= conn.prepareStatement(Search_PA_Date);
						statementPactivities.setInt(1, scanner.nextInt());
						ResultSet RS= statementPactivities.executeQuery();
						System.out.println("Search Successful! ");
						while(RS.next()) {
							System.out.println(RS.getString(Date));

						}
						System.out.println("\n");
						menu();
						statementPactivities.close();
					}else if(CheckCA== 2) {
						System.out.println("Input patient's ID");
						PreparedStatement statementPactivities= conn.prepareStatement(Search_PA_Reason_For_Visit);
						statementPactivities.setInt(1, scanner.nextInt());
						ResultSet RS= statementPactivities.executeQuery();
						System.out.println("Search Successful! ");
						while(RS.next()) {
							System.out.println(RS.getString(Reason_For_Visit)+"|"+RS.getString(Date));

						}
						System.out.println("\n");
						menu();
						statementPactivities.close();
					}else if(CheckCA== 3) {
						System.out.println("Input patient's ID");
						PreparedStatement statementPactivities= conn.prepareStatement(Search_PA_Prescriptions);
						statementPactivities.setInt(1, scanner.nextInt());
						ResultSet RS= statementPactivities.executeQuery();
						System.out.println("Search Successful! ");
						while(RS.next()) {
							System.out.println(RS.getString(Prescriptions)+"|"+RS.getString(Date));

						}
						System.out.println("\n");
						menu();
						statementPactivities.close();
					}
				}
			}else if(CheckUReply== 3) {
				menu();
			}
		}catch(Exception e) {
			System.out.println("Couldn't search: "+e.getMessage());
			e.printStackTrace();

		}

	}
	public PreparedStatement delete() {
		System.out.println("Note: You can't delete a particular data you can only update or delete an entire record.\n You can also delete an activity");
		System.out.println("Warning you can't retrieve what as been deleted");
		System.out.println("In what table do you want to delete: \n1- Patients Data\n2- Patients Activities\n3- return to menu");
		int checkDelete= scanner.nextInt();
		try{
			if(checkDelete== 1) {
				System.out.println("1- Delete a particular record\n2- Delete all records in this table");
				int CheckPD= scanner.nextInt();
				if(CheckPD== 1) {
					PreparedStatement statementPDdelete= conn.prepareStatement(Delete_PD);
					System.out.println("Input Patient's ID");
					statementPDdelete.setInt(1, scanner.nextInt());
					int row= statementPDdelete.executeUpdate();
					if(row!= 0) {
						System.out.println("Deleted Successfully! \n");
					}else {
						System.out.println("Couldn't delete; Either record doesn't exist or your input is wrong\n");
					}
					menu();
					statementPDdelete.close();
					return statementPDdelete;

				}else if(CheckPD==2) {
					PreparedStatement statementPDdelete= conn.prepareStatement(Delete_PD_Table);
					int row= statementPDdelete.executeUpdate();
					if(row!= 0) {
						System.out.println("Deleted Successfully! \n");
					}else {
						System.out.println("Couldn't delete; Either record doesn't exist or your input is wrong\n");
					}
					DefaultInsert();
					statementPDdelete.close();
					return statementPDdelete;
				}
			}else if (checkDelete== 2) {
				PreparedStatement statementPAdelete;
				System.out.println("1- Delete a particular record\n2- Delete all records of a patient\n3- Delete all records in this table");
				int CheckPA= scanner.nextInt();
				if(CheckPA== 1) {
					statementPAdelete= conn.prepareStatement(Delete_PA);
					System.out.println("Input Patient's ID");
					statementPAdelete.setInt(1, scanner.nextInt());
					scanner.nextLine();
					System.out.println("Input date and time of activity");
					statementPAdelete.setString(2, scanner.nextLine());
					int row= statementPAdelete.executeUpdate();
					if(row!= 0) {
						System.out.println("Deleted Successfully! \n");
					}else {
						System.out.println("Couldn't delete; Either record doesn't exist or your input is wrong\n");
					}
					menu();
					statementPAdelete.close();
					return statementPAdelete;
				}else if(CheckPA== 2) {
					statementPAdelete= conn.prepareStatement(Delete_All_PA);
					System.out.println("Input Patient's ID");
					statementPAdelete.setInt(1, scanner.nextInt());
					int row= statementPAdelete.executeUpdate();
					if(row!= 0) {
						System.out.println("Deleted Successfully! \n");
					}else {
						System.out.println("Couldn't delete; Either record doesn't exist or your input is wrong\n");
					}
					menu();
					statementPAdelete.close();
				}else if(CheckPA== 3) {
					statementPAdelete= conn.prepareStatement(Delete_All_PA_Table);
					int row= statementPAdelete.executeUpdate();
					if(row!= 0) {
						System.out.println("Deleted Successfully! \n");
					}else {
						System.out.println("Couldn't delete; Either record doesn't exist or your input is wrong\n");
					}
					menu();
					statementPAdelete.close();
				}
			}else if(checkDelete== 3) {
				menu();
			}

		}catch(Exception e) {
			System.out.println("Couldn't delete: "+e.getMessage());
			e.printStackTrace();
			return null;
		}
		return null;
	}


}
