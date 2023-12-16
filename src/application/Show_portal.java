package application;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Calendar;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;



public class Show_portal extends Application{
   	
	private String data[]=new String[13];
	String receive_cnic="";
	Label Time=new Label();
	Button work=new Button();
	Button Holyday=new Button("Holiday!");
	BorderPane header=new BorderPane();
	Stage next_stg=new Stage();
	Stage curr_stg;
	
	
	Show_portal(String Cnic) {
		receive_cnic=Cnic;
	}

	public void read_data_from_database() {
	 try {
		 
		String queery="Select * from Mazdoor_data where Cnic="+"\'"+receive_cnic+"\'";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/register_labour","root","215020");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(queery);
		rs.next();
		for(int i=0;i<data.length;i++) {
			data[i]=rs.getString(i+1);
			System.out.println(data[i]);
		}
		st.close();
		con.close();
	 }
	 catch (Exception e) {
		System.out.println(e);
	 }
  } 

	@Override
	public void start(Stage stg)  {
		try {
			curr_stg=stg;
			Time.setText("hello world ");
			read_data_from_database();
			
			
			Label image=new Label();
			Label name=new Label(data[0]);
			name.setPadding(new Insets(-4));
			name.setFont(Font.font("italic",FontWeight.BOLD,25));
			name.setTextFill(Color.PURPLE);
			FileInputStream f1=new FileInputStream("E:\\adminlogo4.png");
			Image img=new Image(f1,150,150,true,true);
			ImageView view=new ImageView(img);
			
			image.setGraphic(view);
			GridPane basic_info=new GridPane();
			
			basic_info.add(Tag("Address :"), 0, 1);
			basic_info.add(putData(data[5]), 1, 1);
			
			basic_info.add(Tag("Phone no :"), 0, 2);
			basic_info.add(putData(data[4]), 1, 2);
			
			basic_info.add(Tag("Age :"), 0, 3);
			basic_info.add(putData(data[1]), 1, 3);
			
			basic_info.setPadding(new Insets(-5));
			basic_info.setVgap(10);
			
			
			
			HBox all_data=new HBox();
		
			GridPane intro=new GridPane();
			BorderPane logo=new BorderPane();
			logo.setCenter(image);
			intro.add(logo, 0, 0);
			intro.setColumnSpan(logo, 2);
			intro.add(name, 0, 1);
			intro.add(basic_info, 0, 2);
            intro.setVgap(10);
			intro.setPadding(new Insets(20));  
			
			
		
			VBox leve=new VBox();
			HBox skill=new HBox();
			skill.getChildren().addAll(Tag("Level :"),putData(data[6]));
			skill.setSpacing(10);
			leve.getChildren().addAll(subheading("Skill Level :"),skill);
			
			GridPane security=new GridPane();
			security.add(subheading("Private Info :"), 0, 0);
			security.add(Tag("Cnic :"), 0, 1);
			security.add(putData(data[2]), 1, 1);
			security.add(Tag("Password :"), 0, 2);
			security.add(putData(data[3]), 1, 2);
			
			
			GridPane working_non_working_day=new GridPane();
			working_non_working_day.add(subheading("Availability :"), 0, 0);
			working_non_working_day.add(Tag("Working Days :"), 0, 1);
			working_non_working_day.add(putData(data[10]), 1, 1);
			working_non_working_day.add(Tag("Non Working Days :"), 0, 2);
			working_non_working_day.add(putData(data[11]), 1, 2);
			
			GridPane Bank_detail=new GridPane();
			Bank_detail.add(subheading("Account Details :"), 0, 0);
			Bank_detail.add(Tag("Platform Name :"), 0, 1);
			Bank_detail.add(putData(data[9]), 1, 1);
			Bank_detail.add(Tag("UserName :"), 0, 2);
			Bank_detail.add(putData(data[8]), 1, 2);
			Bank_detail.add(Tag("Account no :"), 0, 3);
			Bank_detail.add(putData(data[9]), 1, 3);
			Bank_detail.setPadding(new Insets(20));
			
			GridPane remaining_info=new GridPane();
			remaining_info.add(leve, 0, 0);
			remaining_info.add(security, 0,1);
			remaining_info.add(working_non_working_day, 0,2);
			remaining_info.setVgap(40);
			remaining_info.setPadding(new Insets(20));
			all_data.getChildren().addAll(intro,remaining_info,Bank_detail);
			all_data.setSpacing(20);

			FileInputStream f2=new FileInputStream("E:\\labour5bg.png");
			Image img2=new Image(f2,70,70,true,true);
			ImageView view2=new ImageView(img2);
		
			Label h1=new Label("");
			h1.setGraphic(view2);
			h1.setPadding(new Insets(5));
			h1.setTextAlignment(TextAlignment.CENTER);
			h1.setWrapText(true);
			
			header.setLeft(h1);
			h1.setTextFill(Color.BLACK);
			
			header.setStyle("-fx-background-color:skyblue");
			header.setMinHeight(70);
		    h1.setFont(Font.font("Italic", FontWeight.BOLD, 28));
		 
		    
		    work.setStyle("-fx-background-color:skyblue");
		    Holyday.setStyle("-fx-background-color:skyblue");
		    work.setPrefWidth(120);
		    Holyday.setPrefWidth(120);
		    work.setFont(Font.font("italic",FontWeight.BOLD,14));
		    Holyday.setFont(Font.font("italic",FontWeight.BOLD,14));
		    Holyday.setOnAction(this::HolidayCheck);
		    work.setOnAction(this::start_working);
		    
			HBox buttons=new HBox(); 
			buttons.getChildren().addAll(work,Holyday);
			buttons.setSpacing(10);
			buttons.setPadding(new Insets(10));
			VBox root=new VBox();
			root.getChildren().addAll(header,all_data,buttons);
			root.setStyle("-fx-background-color:white");
			BorderPane newroot=new BorderPane();
			newroot.setCenter(root);
			Label footer=new Label("");
			footer.setPrefHeight(20);
			footer.setMaxWidth(900);
			footer.setStyle("-fx-background-color:black");
			newroot.setBottom(footer);
			Scene sc=new Scene(newroot);
			
			stg.setScene(sc);
			stg.setHeight(600);
			stg.setWidth(900);
			stg.setTitle("Portfolio");
			stg.setResizable(false);;
			stg.show();
			System.out.println(data[12]);
			if(data[12].equalsIgnoreCase("working")){
				System.out.println("hi in data[13] checking ");
				work.setText("Finshed Work");
			}
			else {
				System.out.println("hi in data[13] checked and false ");
				work.setText("Start Working");	
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	public Label subheading(String str) {
		Label h2=new Label(str);
		h2.setFont(Font.font("Italic",FontWeight.BOLD,20));
		h2.setTextFill(Color.PURPLE);
		return h2;
	}
	
	
	public void HolidayCheck(ActionEvent e) {
	
			Stage st=new Stage();
			Holiday obj=new Holiday(data[10],data[11],data[6],data[2]);
			obj.start(st);
			curr_stg.close();
		}
		
			
	
	
	
	public void start_working(ActionEvent e) {
		start_Working obj=new start_Working("start",data[2]);
		
		if(work.getText().equalsIgnoreCase("Start working")){
		  work.setText("Finshed Work");
		  obj.start(next_stg);
		  curr_stg.close();
	  }
        else {
        	
        	start_Working obj2=new start_Working("stop",data[2],data[10]);
        	work.setText("Start Working");
        	obj2.setState(data[2],"state","free");
        	obj2.start(next_stg);
        	curr_stg.close();
			System.out.println("Ok you finnished the work");
			
		}
	}

	public Label putData(String name) {
		Label label=new Label(name);
		label.setFont(Font.font(17));
		label.setWrapText(true);
		return label;
	}
	
	public Label Tag(String heading ) {
		Label h1=new Label(heading);
	    h1.setFont(Font.font("",FontWeight.findByWeight(14), 17));
		h1.setWrapText(true);
		h1.setTextAlignment(TextAlignment.CENTER);
		return h1;
	}
	
	


}
