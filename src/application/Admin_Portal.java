package application;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

public class Admin_Portal extends Application{
	BorderPane header=new BorderPane();
	String Cnic;
	Label h1=new Label("ID: ");
	Label id=new Label(); 
	HBox id_and_h1=new HBox();
	Label logo=new Label();
	VBox workingpersons=new VBox();
	VBox freepersons=new VBox();
	private int i=0;
    ArrayList<String> data=new ArrayList<String>();
	
    Stage curr;
	Label h2_for_working=new Label("Working Persons ");
	public Admin_Portal(String Cnic) {
		this.Cnic=Cnic;
	}
	
	public void getperson_from_database(String str) {
		
		try {
			String queery;
			if(str.equalsIgnoreCase("Working")) {
				 queery="select name,Cnic,comed_at_time from Mazdoor_data where state="+"\'"+str+"\' ;";
			}
			else {
				 queery="select name,Cnic,non_working_day from Mazdoor_data where state="+"\'"+str+"\' ;";
			 }
			System.out.println(queery);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/register_labour","root","215020");
			Statement st=cn.createStatement();
			ResultSet rs=st.executeQuery(queery);
			
			while(rs.next()){
				
				BorderPane_for_each_person(rs.getString(1),rs.getString(2),rs.getString(3),str,i);
				data.add(rs.getString(2));
				this.i=this.i+1;
				
			}
			cn.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public Label h2(String str) {
		Label l=new Label(str);
		l.setFont(Font.font("",FontWeight.BOLD,18));
		l.setTextFill(Color.DARKBLUE);
		l.setPadding(new Insets(3));
		return l;
	}
	
	public void BorderPane_for_each_person(String name,String Cnic,String time,String pr,int id) {
		BorderPane b=new BorderPane();
		
		HBox name_frame=new HBox();
		name_frame.getChildren().addAll(firstInfo("Name: "),h2(name));
		name_frame.setSpacing(5);
		
		HBox Cnic_frame=new HBox();
		Cnic_frame.getChildren().addAll(firstInfo("Cnic: "),h2(Cnic));
		Cnic_frame.setSpacing(5);
		
		Button ViewProfile=new Button("View Profile");
		ViewProfile.setOnAction(this::showProfile);
		ViewProfile.setId(Integer.toString(id));
		ViewProfile.setFont(Font.font("",FontWeight.BOLD,13));
		ViewProfile.setTextFill(Color.WHITE);
		
		ViewProfile.setStyle("-fx-background-color:green");
		HBox button_and_extra=new HBox();
		button_and_extra.setSpacing(10);
		button_and_extra.setMargin(ViewProfile, new Insets(3));
		HBox h=new HBox();
		h.getChildren().addAll(name_frame,Cnic_frame);
		h.setSpacing(30);
		b.setLeft(h);
		b.setStyle("-fx-background-color:lightgray");
		if(pr.equals("Working")) {
			button_and_extra.getChildren().addAll(timeshow("Comed at: "+time),ViewProfile);
			b.setRight(button_and_extra);
			workingpersons.getChildren().add(b);
			workingpersons.setMargin(b, new Insets(5));
		}
		else {
			button_and_extra.getChildren().addAll(timeshow("Holidays ="+time),ViewProfile);
			b.setRight(button_and_extra);
			freepersons.getChildren().add(b);
			freepersons.setMargin(b, new Insets(5));	
		}
		
	}
	
	public Label heading(String str) {
		Label l=new Label(str);
		l.setFont(Font.font("",FontWeight.EXTRA_BOLD,25));
		l.setTextFill(Color.GREEN);
		return l;
	}
	public void showProfile(ActionEvent e) {
		Stage stg=new Stage();
		int id=Integer.parseInt(((Button)e.getSource()).getId());
		String Cnic=data.get(id);
		Show_portal obj=new Show_portal(Cnic);
		obj.start(stg);
		curr.close();
		
	}
    
	public Label timeshow(String t) {
		Label l=new Label(t);
		l.setFont(Font.font("",FontWeight.BOLD,15));
		l.setTextFill(Color.DARKBLUE);
		l.setPadding(new Insets(3));
		return l;
	}
	public Label firstInfo(String st) {
		Label l=new Label(st);
		l.setFont(Font.font("",FontWeight.BOLD,20));
		l.setTextFill(Color.BLACK);
		return l;
	}
	
	
	@Override
	public void start(Stage stage) {
		try {
		curr=stage;	
		getperson_from_database("Working");
		getperson_from_database("free");
		System.out.println(data);
		
	    FileInputStream f=new FileInputStream("E:\\managerrm.png");
	    Image img=new Image(f,70,70,true,true);
	    ImageView view=new ImageView(img);
	    logo.setGraphic(view);
	    logo.setPadding(new Insets(10));
	    id_and_h1.setPadding(new Insets(30));
		h1.setTextFill(Color.BLACK);
		h1.setTextFill(Color.YELLOW);
		h1.setFont(Font.font("ITALIC",FontWeight.BOLD, 28));
		id.setText(this.Cnic+" Dashboard");
		id.setFont(Font.font("ITALIC",FontWeight.BOLD, 22));
		id.setTextFill(Color.WHITE);
		id.setPadding(new Insets(4));
	
		
	    header.setStyle("-fx-background-color:black");
        id_and_h1.getChildren().addAll(h1,id);
        header.setLeft(id_and_h1);
        header.setRight(logo);
     
        
        VBox maindata=new VBox();
        
        maindata.getChildren().addAll(heading("Working Persons"),workingpersons,heading("Free Persons"),freepersons);
        
        maindata.setPadding(new Insets(20));
        
		VBox root=new VBox();
		root.getChildren().addAll(header,maindata);
		root.setStyle("-fx-background-color:white");
		Scene sc=new Scene(root);	
		stage.setScene(sc);
		stage.setWidth(900);
		stage.setHeight(600);
		stage.show();
		
        
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
	

}
