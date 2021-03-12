package com.test.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.cj.xdevapi.PreparableStatement;

import java.sql.ResultSet;
//import com.mysql.cj.protocol.Resultset;
//import com.mysql.cj.xdevapi.Result;
//import com.mysql.cj.xdevapi.Statement;

public class DaoDemo {
	static Scanner sc= new Scanner(System.in);
	static boolean exit=false;
	static Connection conneection = null;
	static  Statement statement=null;
	public static void getConnection() {
		String jdbcUrl="jdbc:mysql://localhost:3306/employee_payroll?useSSL=false";
		String username="root";
		String password="Ankita@9713";
		
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			System.out.println("Connecting to database "+jdbcUrl);
			conneection=DriverManager.getConnection(jdbcUrl,username,password);
			System.out.println("don!!!");
			
			
		}
		catch(ClassNotFoundException | SQLException  e)
		{
			//throw new IllegalStateException("cannot find driver");
			e.printStackTrace();
		}
		
	}

	 public static void getDisplay() throws SQLException
	 {
		 new DaoDemo().getConnection();
		
		 try {
				
				statement= conneection.createStatement();
				ResultSet result = statement.executeQuery("select * from employee_system");
				while(result.next())
				{
					System.out.println("Id: " + result.getInt("id"));
			        System.out.println("Name: " + result.getString("name"));
			        System.out.println("Gender : " +result.getString("gender"));
			        System.out.println("Department: " + result.getDouble("salary"));
			        System.out.println("addess  : "  +result.getString("adress"));
			        System.out.println("contact no " +result.getString("phone_no"));
			        System.out.println("Start Date: " + result.getDate("date_join"));
			        
			        System.out.println();
			        System.out.println("----------------------------------------------------------------------------------");
			        System.out.println();
				}
				
			}
			catch(SQLException  e)
			{
				//throw new IllegalStateException("cannot find driver");
				e.printStackTrace();
			}
			
	      
	 }
	 
	public static void insertValues() throws SQLException, ClassNotFoundException
	{
		 new DaoDemo().getConnection();
		 
		 try {
		 System.out.println("inserting into the table ");
		 statement= conneection.createStatement();
		  
		// String sql="insert into employee_system(name,gender,salary,adress,phone_no,date_join)\r\n" + 
		 //		"value('liza','female',900000,'california,USA','347654456','2003-11-10')";
		 
		 
		   //statement.executeUpdate(sql);
		     String sql="insert into employee_system(name,gender,salary,adress,phone_no,date_join)\r\n" + 
			 		"value('abhaya','male',920000,'dknl,odisha','70777754456','1993-11-09')";
		   
		    statement.executeUpdate(sql);
		   System.out.println();
		   System.out.println("inserted sucessfully");
		   System.out.println("--------------------------------------------------------------------------------------------");
		 }
		 
		 
		 catch(SQLException  e)
			{
				//throw new IllegalStateException("cannot find driver");
				e.printStackTrace();
			}
		 
		
	}
	
	public static void updateValues() throws SQLException
	{
		 new DaoDemo().getConnection();
		
		
		try 
		{
			
			System.out.println("updating in table");
			statement= conneection.createStatement();
			//String sql = "UPDATE  employee_system set salary='230000' where name='katta';";
            //statement.executeUpdate(sql);
            
           String  sql = "UPDATE  employee_system set adress='Usa' where name='john';";
            statement.executeUpdate(sql);
            
            System.out.println();
            System.out.println("updated successfully");
            System.out.println("-------------------------------------------------------------------------------------");
		}
		
		catch(SQLException  e)
		{
			//throw new IllegalStateException("cannot find driver");
			e.printStackTrace();
		}
		
		
	}
	 
	 static void end()
     {
         	System.out.println("thank you");
			exit=true;
     }

	 public static void  OtherOperation() throws SQLException
	 
	 {
		 new DaoDemo().getConnection();
		 
		 try {
			 System.out.println("making the changes in table");
			 statement = conneection.createStatement();
			 System.out.println("enter your comand");
			 String sql="select * from employee_system where date_join between '2012-01-01' and '2014-12-31'";

			 
			 ResultSet result=statement.executeQuery(sql);
			 
			 while(result.next())
				{
	                System.out.println("--------------------------------------------------------------------------------");			 
					System.out.println("Id: " + result.getInt("id"));
			        System.out.println("Name: " + result.getString("name"));
			        System.out.println("Gender : " +result.getString("gender"));
			        System.out.println("Department: " + result.getDouble("salary"));
			        System.out.println("addess  : "  +result.getString("adress"));
			        System.out.println("contact no " +result.getString("phone_no"));
			        System.out.println("Start Date: " + result.getDate("date_join"));
			        
			         System.out.println("-----------------------------------------------------------------------------------");
             		
				}
			 
			       System.out.println("value gave out accordingly the table accordingly!!!!");
			       System.out.println("--------------------------------------------------------------------------------------");
		 }
		 
		 catch (SQLException e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		 
		 
	 }
	 
	 public static void AlterOperation() throws SQLException, NullPointerException
	 {
		 
		 DaoDemo.getConnection();
		 try 
		 {
		   System.out.println("Alteration  of Table");
		   statement=conneection.createStatement();
		   String sql ="ALTER TABLE employee_system drop COLUMN service_period;";
		   statement.executeUpdate(sql);
		   
		   ResultSet result=statement.executeQuery(sql);
		
		   System.out.println("tabele foramt changed");
		   System.out.println("----------------------------------------------------------------------------------");
		   
		 }
		 catch (SQLException e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		 
		 catch (NullPointerException ex) {
			 ex.printStackTrace();
			// TODO: handle exception
		}
        		 
	 }
	 
	 public static void joinOperation()  throws SQLException
	 {
		 DaoDemo.getConnection();
		 try 
		 {
		   System.out.println("extracting avalues from different tables");
		   statement=conneection.createStatement();
		   String sql ="SELECT employee_system.id, employee_system.name, employee_system.salary,employee_system.date_join ,department.dept_name\r\n" + 
		   		       "from department\r\n" + 
		   		       "INNER JOIN employee_system  ON department.id=employee_system.id\r\n" + 
		   		       "order by department.dept_name;";
		   
		   
		   ResultSet result=statement.executeQuery(sql);
		   
		   while(result.next())
			{
                System.out.println("--------------------------------------------------------------------------------");			 
				System.out.println("Id: " + result.getInt("id"));
		        System.out.println("Name: " + result.getString("name"));
		        System.out.println("salary : " + result.getDouble("salary"));
		        System.out.println("Start Date: " + result.getDate("date_join"));
		        System.out.println("Department :" +result.getString("dept_name"));
		        
		         System.out.println("-----------------------------------------------------------------------------------");
        		
			}
		
		   System.out.println("contains shown  from table");
		   System.out.println("----------------------------------------------------------------------------------");
		   
		 }
		 catch (SQLException e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		 
	 }
	 
	 public static void  DeleteContent() throws SQLException
	 {
		 DaoDemo.getConnection();
		 try 
		 {
		   System.out.println("Alteration  of Table");
		   statement=conneection.createStatement();
		   String sql ="DELETE from employee_system where name='liza'";
		   statement.executeUpdate(sql);
		   
		
		   System.out.println("contains deleted  from table");
		   System.out.println("----------------------------------------------------------------------------------");
		   
		 }
		 catch (SQLException e) {
			// TODO: handle exception
			 e.printStackTrace();
		} 
	 }
	 
	 public static void closeConnect() throws SQLException
	 
	 { 
		 if(statement!=null)
			{
				statement.close();
				System.out.println("the statement is closed");
			}
		
		
		if(conneection != null)
		{
			conneection.close();
			System.out.println("the conection is closed");
		}
	 }
	 
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		while(!exit) {
		System.out.println("welcome to employee payroll system");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("enter your choice");
		System.out.println("[0] for closing the connection");
		System.out.println("[1] for exit");
		System.out.println("[2] for display");
		System.out.println("[3] for inserting the value");
		System.out.println("[4] for updating the values");
		System.out.println("[5] for OTHER UPDATION");
		System.out.println("[6] for altering the table");
		System.out.println("[7] for deleteing contains in table");
		System.out.println("[8] for joinig the table execute");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("enter the choice of operation");
		
		
		int choice = sc.nextInt();
		
		switch(choice)
		{
		
		case 0:
			new DaoDemo().closeConnect();
			break;
			
		case 1 :
			new DaoDemo().end();
			break;
		
			
		case 2 :
			new DaoDemo().getDisplay();
			break;
			
		case 3 :
			new DaoDemo().insertValues();
			break;
			
		case 4 :
			new DaoDemo().updateValues();
			break;
			
		case 5 :
			new DaoDemo().OtherOperation();
			break;
			
		case 6 :
			new DaoDemo().AlterOperation();
			break;
			
		case 7 :
			new DaoDemo().DeleteContent();
			break;
			
		case 8 :
			new DaoDemo().joinOperation();
			break;
		}
		}
	
	}

}
