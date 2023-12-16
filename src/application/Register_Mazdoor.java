package application;

import java.io.FileInputStream;
import java.sql.*;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;

import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Register_Mazdoor extends Application {
	
	
	private Label lbl1=new Label("REGISTRATION!");
	Line line = new Line();
	private Label lbl2=new Label("Name: ");
	private Label lbl4=new Label("Age: ");
	private Label lbl6=new Label("CNIC: ");
	private Label lbl7=new Label("Phone No: ");
	private Label lbl8=new Label("Address : ");
	private Label lbl5=new Label("Password: ");
	private Label lbl9=new Label("Your Level");
	private Label error=new Label();
	private Stage pr_stage;
	
	TextField name=new TextField();
	TextField age=new TextField();
	TextField Cnic=new TextField();
	PasswordField pass=new PasswordField();
	TextField phone=new TextField();
	TextField address=new TextField();

	ToggleGroup group=new ToggleGroup();
	RadioButton opt1=new RadioButton("Skilled   ");
	RadioButton opt2=new RadioButton("UnSkilled");
	Button submit=new Button();

	
	@Override
	public void start(Stage stage) {
		try {
			
	    pr_stage=stage;
		lbl1.setAlignment(Pos.TOP_CENTER);
		lbl1.setPadding(new Insets(10));
		lbl1.setTextFill(Color.PURPLE);
		lbl1.setFont(Font.font("Italic", FontWeight.BOLD, 30));
		lbl1.setMaxWidth(500);
		lbl1.setMaxHeight(400);
		
		FileInputStream f1=new FileInputStream("E:\\rgslogo2hd.jpg");
		Image img1=new Image(f1,100,100,true,true);
		ImageView view1=new ImageView(img1);
		submit.setText("Next --->");
		
		BorderPane heading=new BorderPane();
		heading.setCenter(lbl1);
		
		BorderPane logo=new BorderPane();
		logo.setCenter(view1);
		
	      line.setStartX(60);
	      line.setStartY(65);
	      line.setEndX(460);
	      line.setEndY(65);
	      
		VBox vb=new VBox();
		vb.getChildren().addAll(logo,heading,line);
		
		lbl2.setFont(Font.font(15));
		lbl4.setFont(Font.font(15));
		lbl5.setFont(Font.font(15));
		lbl6.setFont(Font.font(15));
		lbl7.setFont(Font.font(15));
		lbl8.setFont(Font.font(15));
		lbl9.setFont(Font.font(15));
		
		name.setPrefWidth(210);
		pass.setPrefWidth(210);
		Cnic.setPrefWidth(210);
		address.setPrefWidth(210);
		age.setPrefWidth(210);
		phone.setPrefWidth(210);

		


		
		opt1.setToggleGroup(group);
		opt2.setToggleGroup(group);
		HBox hb=new HBox();
		hb.getChildren().addAll(opt1, opt2);
		
		error.setTextFill(Color.RED);
		
		submit.setTextFill(Color.WHITE);
		submit.setStyle("-fx-background-color: purple;");
		submit.setOnAction(this::check);
		submit.setMaxWidth(80);
	
		VBox error_submit=new VBox();
		error_submit.getChildren().addAll(error,submit);
		GridPane rt=new GridPane();
		rt.add(lbl2, 0, 0);
		rt.add(name, 1, 0);
		rt.add(lbl4, 0, 1);
		rt.add(age, 1, 1);
		rt.add(lbl5, 0, 2);
		rt.add(pass, 1, 2);
		rt.add(lbl6, 0, 3);
		rt.add(Cnic, 1, 3);
		rt.add(lbl7, 0, 4);
		rt.add(phone, 1, 4);
		
		rt.add(lbl8, 0, 5);
		rt.add(address, 1, 5);
		rt.add(lbl9, 0, 6);
		rt.add(hb, 1, 6);
		
	
		rt.add(error_submit, 0, 7);
		rt.setColumnSpan(error_submit,2);
		
		rt.setHgap(15);
		rt.setVgap(15);
	
		rt.setPadding(new Insets(40));

		BorderPane bp=new BorderPane();
		bp.setTop(vb);
		bp.setCenter(rt);
		bp.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY,Insets.EMPTY)));
		
		Scene sc=new Scene(bp, 400, 600);
		stage.setScene(sc);
		stage.setTitle("Registration");
		stage.setResizable(false);
		stage.show();
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public int check_form(String ag) {
		
		char ch;
		int count=0;
		boolean check=false;
		int i=0;
		while(i<ag.length()) {
			ch=ag.charAt(i);
			if(ch>='0' && ch<='9') {
				check=true;	
				count=1;
				
			}
			else {
				count=0;
				check=false;
				break;
			}
			i=i+1;	
		}
	   return count;
		
	}
	
	public boolean communicate_with_DB(String purpose,String checking_item,String text,String Database_name) {
		boolean check=false;
		try {	
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/register_labour","root","215020");
		String queery;
		
		
		
		if(purpose.equalsIgnoreCase("insert")) {
			String skill="";
			if(opt1.isSelected()) {
				skill=opt1.getText();
			}
			else {
				skill=opt2.getText();
			}
			queery="insert into "+Database_name+" (name, age, Cnic, pass, phone, address, skill, working_day,non_working_day,state) "+"values (?,?,?,?,?,?,?,?,?,?)";
	        PreparedStatement st=cn.prepareStatement(queery);
			
	        String []data = {name.getText(),age.getText(),Cnic.getText(),pass.getText(),phone.getText(),address.getText(),skill,"0","0","free"};
	        
	        for(int i=0;i<data.length;i++) {
	        	st.setString(i+1, data[i]);
	        }
	        	    
			int status=st.executeUpdate();		
			
		}
		
		else {
			queery=" Select "+checking_item+" from "+Database_name+" where "+ checking_item+"="+  "\'"+ text+ "\'" ;
			System.out.println(queery);
			Statement st=cn.createStatement();
			ResultSet rs=st.executeQuery(queery);
			try {
			    rs.next();	
				String str=rs.getString(1);
				check=true;
			}
			catch(Exception e) {
				check=false;
			}
			
		}
		
		cn.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return check;
		
	}
	
	
	public void removeText() {
		name.setText("");
		age.setText("");
		Cnic.setText("");
		pass.setText("");
		phone.setText("");
		address.setText("");
		group.selectToggle(null);
		error.setText("");
	}

	public void check(ActionEvent e) {
		int count=0;
		int check=1;
		if(opt1.isSelected() || opt2.isSelected()) {	
			check=check+1;
		}
		if(check==2 && !name.getText().equals("")  && !age.getText().equals("")  &&
		    !Cnic.getText().equals("")  && !pass.getText().equals("")  && !phone.getText().equals("")  && 
		    !address.getText().equals(""))
		
		{
			count+=check_form(Cnic.getText());
			count+=check_form(age.getText());
			count+=check_form(phone.getText());
			
		    String age_str=age.getText();
			int age =Integer.parseInt(age_str);
			
			if(count==3){
			  if(age>=18 && age<=50) {
				  boolean desc=communicate_with_DB("check","Cnic",Cnic.getText(),"Mazdoor_data");
				  if(desc) {
					  error.setText("this Cnic is already registered ");  
					  System.out.println(desc);
				  }
				  
				  else {
					  desc=communicate_with_DB("check","pass",pass.getText(),"Mazdoor_data"); 
					  if(desc) {
						  error.setText("Enter another password its already registered ");
					  }
					  else {
						  
						    Bank_detail obj=new Bank_detail(Cnic.getText());
						         desc=communicate_with_DB("Insert","","","Mazdoor_data"); 
							     removeText();
							     Stage st=new Stage(); 
							     obj.start(st);
							     pr_stage.close();
							    	     
					  }
					  
			  
				  }
			  }
			  
			  else {
				  error.setText("age must be in range of 18 and 50");
			  }
			}
			  
			else {
				error.setText("plz enter cnic or phone or age in valid form ");
			}
			
		}
		else {
           error.setText("plzz fill the form before submiting ");
		}
	}

}
