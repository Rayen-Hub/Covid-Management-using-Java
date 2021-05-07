package covid;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Patient {
	
	int aadhar;
	String pname;
	String state;
	String city;
	String pstatus;
	String pincode;

	Patient p=null;
	
	
	public Connection con =DBconnection.getConnection();
	
	
	
	
	public static  void Mainmenu() {
		
		System.out.println("COVID Cases Repo Management");
		System.out.println("********************************************************************");
		System.out.println("1.ADD  2.UPDATE  3.SEARCH  4.VIEW  5.DELETE");          
		System.out.println("********************************************************************");
		
		System.out.println("What would you like to do ?");
		System.out.println("Press 1 To add a patient");
		System.out.println("Press 2 To update status of an existing patient");
		System.out.println("Press 3 To search patient");
		System.out.println("Press 4 To view patient");
		System.out.println("Press 5 To delete a patient");
		System.out.println("Press 6 To Exit");
		
	}
	
	
	
	public boolean insert() throws Exception{
		
		Scanner input=new Scanner(System.in);
		
	
		PreparedStatement stmt=con.prepareStatement("insert into patient(aadharID,pname,state,city,pstatus,pincode) values(?,?,?,?,?,?)");
		System.out.print("Aadhar number : ");
		stmt.setInt(1, aadhar=Integer.parseInt(input.nextLine()));
		
		System.out.print("Patient Name  : ");
		stmt.setString(2, pname=input.nextLine());
		System.out.print("State : ");
		stmt.setString(3, state=input.nextLine());
		System.out.print("City : ");
		stmt.setString(4, city=input.nextLine());
		System.out.print("Status : ");
		stmt.setString(5, pstatus=input.nextLine());
		System.out.print("Pincode : ");
		stmt.setString(6, pincode=input.nextLine());
		
		int i = stmt.executeUpdate();
		if(i>0) {
			
			return true;
		}
		
		
		return false;
	}
	
	
	
	public void display() throws Exception {
		
		
		String query="select * from patient";
		Statement stmt1 = con.createStatement();
		ResultSet rs=stmt1.executeQuery(query);
		String data="";
		
	
		 if (rs.next() == false) {
		        System.out.println("No records to Display");
		      } else {

		        do {
		        	
		        	 System.out.printf("%10s %10s %8s %8s %10s %8s", "Aadhar Number", "Patient Name", "Sate", " City", " Status","Pincode");
		        	 System.out.println();
		        	 System.out.format("%10s %10s %15s %5s %5s %5s",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
		        	 
		        	 
		            //data=rs.getInt(1)+"    S          "+rs.getString(2)+"  "+rs.getString(3)+"   "+rs.getString(4)+"  "+rs.getString(5)+" "+rs.getString(6);
		          System.out.println();
		        } while (rs.next());
		      }
			
			
			
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();

	}
		
	
		

		

	
	
	
	
	public void update_status() throws Exception{
		
		Scanner input=new Scanner(System.in);
		System.out.print("Aadhar number : ");
		int aadhar1 =input.nextInt();
		input.nextLine();
		System.out.print("Status  : ");
		String status1=input.nextLine();
		
		
		
		PreparedStatement stmt=con.prepareStatement("update patient set pstatus=? where aadharID=?");
		
		
		stmt.setInt(2, aadhar1);
		stmt.setString(1, status1);
		
		int i = stmt.executeUpdate();
		if(i>0) {
			
			System.out.println("Record Updated successfully!!");
		}
		else {
			System.out.println("Error in updating record");
		}
		
		
		
		
		
	}
	
	
	
public void search()throws Exception {
	
	Scanner input=new Scanner(System.in);
	System.out.print("Aadhar Number : ");
	
	int aadhar1=input.nextInt();
	PreparedStatement stmt=con.prepareStatement("select * from patient where aadharID=?");
	stmt.setInt(1, aadhar1);
	ResultSet rs=stmt.executeQuery();
	
	
	String data="";
	 if (rs.next() == false) {
	        System.out.println("No records Found");
	      } else {

	        do {
	        	
	        	 System.out.printf("%10s %10s %8s %8s %10s %8s", "Aadhar Number", "Patient Name", "Sate", " City", " Status","Pincode");
	        	 System.out.println();
	        	 System.out.format("%10s %10s %15s %5s %5s %5s",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));

	        	System.out.println(data);
	        } while (rs.next());
	      }
		
		
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

}
	
	
	
	



public void Delete() throws Exception{
	
	Scanner input=new Scanner(System.in);
	System.out.print("Aadhar Number : ");
	int aadhar=input.nextInt();
	PreparedStatement stmt=con.prepareStatement("delete from patient where aadharID=?");
	stmt.setInt(1,aadhar);
	int i= stmt.executeUpdate();
	if(i>0) {
		
		System.out.println("Record Deleted Successfully!!!");
	}else {
		System.out.println("No record found to delete");
	}
	
	
	
	
}



	
	
	
public static void main(String args[]) {
		
	Patient p1=new Patient();
	
	while(true) {
Mainmenu();

Scanner input=new Scanner(System.in);
int choice =input.nextInt();

switch(choice) {

case 1:System.out.println("Enter Patient details");
try {
boolean isInsert=p1.insert();
if(isInsert==true) {
	System.out.println("Patient details has been added successfully!!!");
}else {
	System.out.println("Error in adding record");
}

}catch(Exception e) 
{
	System.out.println(e.getMessage());
	
		}

break;
case 2:System.out.println("Enter present status of patient");
try {
p1.update_status();
}catch(Exception e) {
	System.out.println(e.getMessage());
}

break;
case 3:System.out.println("Enter patient Aadhar number to search");

try {
p1.search();
}catch(Exception e) {
	
	System.out.println(e.getMessage());
}
break;
case 4:
try {
p1.display();
}catch(Exception e) {
	System.out.println(e.getMessage());
}
break;
case 5:System.out.println("Enter Patient Aadhar number to delete");

try {
	p1.Delete();
}catch(Exception e) {
	System.out.println(e.getMessage());
}
break;
case 6:
	
	return;

default:System.out.println("Invalid option!!!");



}
	}

}
}
		
	
	
	

