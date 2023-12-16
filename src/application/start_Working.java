package application;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;




public class start_Working extends Application{
	Label h1=new Label();
	String purpose;
	String Cnic;
	String text;
	int previous_day;

	public start_Working() {
	
	}
	public start_Working(String p,String cn) {
		this.purpose=p;
		this.Cnic=cn;
	}
	public start_Working(String p,String cn,String day) {
		this.purpose=p;
		this.Cnic=cn;
		this.previous_day=Integer.parseInt(day);	
	}
	@Override
	public void start(Stage primarystage)  {
		try {
			Calendar c=Calendar.getInstance();
			int noon=Calendar.AM;
			String type="";
			if(noon==0) {
				type="PM";
			}
			else {
				type="AM";
			}
			String time=c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE)+" "+type;

			
		    h1.setFont(Font.font(20));
		    h1.setTextFill(Color.GREEN);
		    h1.setTextAlignment(TextAlignment.CENTER);
			h1.setWrapText(true);
			h1.setPadding(new Insets(20));
			
			
			BorderPane root=new BorderPane();
			root.setCenter(h1);
			root.setStyle("-fx-background-color:white");
			Scene sc=new Scene(root);
		
			primarystage.setScene(sc);
			primarystage.setHeight(400);
			primarystage.setWidth(400);

			primarystage.show();
			
			if(purpose.equalsIgnoreCase("start")) {
				setState(Cnic,"state","Working");
				setState(Cnic,"comed_at_time",time);
				h1.setText("You Came To the Work "+time);
				
			    
			}
			else {
				setState(Cnic,"state","free");
				String init_time=get_Initial_Time();
				h1.setText("You Came at "+init_time+" and Finished at "+time+" So Successfully Transfered Money According to your Time");
				add_working_and_non_working_days(Cnic,"working_day",previous_day);
			    	
			}
			
		
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public String get_Initial_Time() {
		String txt="";
		try {
			String Queery="select comed_at_time from Mazdoor_data where Cnic="+"\'"+Cnic+"\'";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/register_labour","root","215020");
	       Statement st=cn.createStatement();
			ResultSet rs=st.executeQuery(Queery);
			rs.next();
			txt=rs.getString(1);
			System.out.println(txt);
			cn.close();	
		} 
		catch (Exception e) {
			System.out.println(e);
		}
		return txt;
		
	}
	public void setState(String cnic,String col,String txt) {
		String queery="update Mazdoor_data set "+col+"="+ "\'"+txt+"\'" + " where cnic="+"\'"+cnic+"\'";
		try {
			System.out.println(queery);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/register_labour","root","215020");
			PreparedStatement st=cn.prepareStatement(queery);
			int count =st.executeUpdate();
			cn.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	

	public void add_working_and_non_working_days(String Cnic,String type_of_work,int previous_day) {
		try {
			
			String queery="update Mazdoor_data set "+type_of_work+"="+(previous_day+1)+" where Cnic="+"\'"+Cnic+"\'";
			System.out.println(queery);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/register_labour","root","215020");
			PreparedStatement st=cn.prepareStatement(queery);
			int count=st.executeUpdate();
			cn.close();		
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}

}
