package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Bank_detail extends Application{
	Label h1=new Label("Enter Your Account Details ");
	Label h2=new Label("Chosse Any One OF Them ");
    Label EasyPaisa=new Label();
    Label Paypall=new Label();
    Label JazzCash=new Label();
    Label Bank_Account=new Label();
    String Cnic;

    Label error=new Label();
    TilePane tp=new TilePane();
    
    Label lbl1=new Label();
    Label lbl2=new Label();
    Label lbl3=new Label();
    TextField account_no=new TextField();
    TextField Account_name=new TextField();
    TextField Bank_name=new TextField();
    Button submit=new Button("Submit");
    BorderPane heading=new BorderPane();
    VBox root=new VBox();
	Stage current_stg=new Stage();
	Stage nextstg=new Stage();
	  success_msg msg_window=new success_msg();
	public Bank_detail(String Cnic) {
		this.Cnic=Cnic;
	}
	
	public void start(Stage primarystage)  {
		try {
			current_stg=primarystage;
			account_no.setMaxWidth(220);
			Account_name.setMaxWidth(220);
			Bank_name.setMaxWidth(220);
			
		error.setTextFill(Color.RED);	
		submit.setPrefWidth(90);
		submit.setOnAction(this::check_submit);
		submit.setTextFill(Color.WHITE);
		h1.setTextFill(Color.BLACK);
		h1.setFont(new Font("Times new Roman",25));
		h2.setFont(Font.font(18));
		h2.setFont(new Font("Arial",20));
        h2.setTextFill(Color.PURPLE);			

		BorderPane subheading=new BorderPane();	
		subheading.setCenter(h2);
		
		tp.setMaxWidth(250);
		tp.setAlignment(Pos.CENTER);
		tp.setPrefTileHeight(70);
		tp.setPrefTileWidth(100);
			
		EasyPaisa.setGraphic(view("E:\\easypiasa.png"));
		JazzCash.setGraphic(view("E:\\jazzcash.png"));
		Paypall.setGraphic(view("E:\\paypal.png"));
		Bank_Account.setGraphic(view("E:\\bankbg.png"));
		
		tp.getChildren().addAll(EasyPaisa,btns("EasyPaisa"),JazzCash,btns("JazzCash"),Paypall,btns("Paypall"),Bank_Account,btns("Bank_Account"));
	
		
		heading.setCenter(h1);
		heading.setPadding(new Insets(20));
		root.getChildren().addAll(heading,subheading,tp);
		root.setStyle("-fx-background-color:white");
		Scene sc=new Scene(root);
		primarystage.setHeight(450);
		primarystage.setWidth(400);
		primarystage.setScene(sc);
        primarystage.show();
        
		}
		catch(Exception e) {
		System.out.println(e);	
		}
		
	}
	
	public  Button btns(String txt) {
		Button btn=new Button(txt);
		if(txt.equals("EasyPaisa")) {
			btn.setStyle("-fx-background-color:green");
		}
		else if(txt.equals("JazzCash")){
			btn.setStyle("-fx-background-color:red");
		}
		else if(txt.equals("Paypall")){
			btn.setStyle("-fx-background-color:blue");
		}
		else {
			btn.setStyle("-fx-background-color:black");
		}
		
		btn.setFont(Font.font("Italic", FontWeight.BOLD, 13));
		btn.setTextFill(Color.WHITE);
		btn.setPrefWidth(100);
		btn.setPadding(new Insets(5));
		btn.setOnAction(this::show_fields);
		return btn;
	}
	
	public void show_fields(ActionEvent e) {
		String data=((Button)e.getSource()).getText();
	
		lbl3.setText("Enter Bank Name :");
		lbl1.setText("Enter your username :");
		lbl2.setText("Enter Account no :");
		tp.getChildren().clear();
		root.getChildren().clear();
		
		VBox field_layout=new VBox();
		HBox tags_show=new HBox();
		tags_show.setPadding(new Insets(20));
		heading.setPadding(new Insets(0));
		root.getChildren().addAll(heading,field_layout);
		field_layout.getChildren().addAll(tags_show);
		EasyPaisa.setPadding(new Insets(-10));
		Paypall.setPadding(new Insets(-10));
		JazzCash.setPadding(new Insets(-10));
		Bank_Account.setPadding(new Insets(-10));
		 field_layout.setPadding(new Insets(30));
		tags_show.setSpacing(20);
		 field_layout.setPadding(new Insets(20));
		 
		 
		if(data.equalsIgnoreCase("EasyPaisa")){
			submit.setStyle("-fx-background-color:green");
			tags_show.getChildren().addAll(EasyPaisa,btns(data));
			field_layout.getChildren().addAll(tags("Enter user name :"),Account_name,tags("Enter account no :"),account_no,error,submit);
		}
		else if( data.equalsIgnoreCase("Paypall"))
		{
			 submit.setStyle("-fx-background-color:blue");
		tags_show.getChildren().addAll(Paypall,btns(data));
		field_layout.getChildren().addAll(tags("Enter user name :"),Account_name,tags("Enter account no :"),account_no,error,submit);
		}
		else if( data.equalsIgnoreCase("Jazzcash")) 
		{
			 submit.setStyle("-fx-background-color:red");
		 tags_show.getChildren().addAll(JazzCash,btns(data)); 
			field_layout.getChildren().addAll(tags("Enter user name :"),Account_name,tags("Enter account no :"),account_no,error,submit);
		}
		else {
			 tags_show.getChildren().addAll(Bank_Account,btns(data)); 
			 field_layout.setPadding(new Insets(20));
			 submit.setStyle("-fx-background-color:black");
		    field_layout.getChildren().addAll(tags("Enter Bank name "),Bank_name,tags("Enter user name :"),Account_name,tags("Enter account no :"),account_no,error,submit);
		}
		
		
	}
	
	public void check_submit(ActionEvent e) {
		String check=((Button)e.getSource()).getStyle();
		if(check.contains("green") || check.contains("red") || check.contains("blue")) {
			checkfill_or_not(2,check);
		}
		else {
			checkfill_or_not(3,check);
		}
		
	}
	
	public void checkfill_or_not(int i,String color) {
		boolean flg=true;
		String data[]=new String[3];
		if(i==2) {
			System.out.println("hello");
			if(!Account_name.getText().equals("") && !account_no.getText().equals("")) {
				flg=validateAccount_no();
				if(flg) {
					
					   data[0]=Account_name.getText();
  					   data[1]=account_no.getText();
  					   if(color.equals("green")) {
  						 data[2]="Easy paisa";
  					   }
  					   else if(color.equals("blue")) {
  						   data[2]="Paypall";
  					   }
  					   else {
  						 data[2]="JazzCash";
  					   }
					   insert_in_DB(data);
					   error.setText("");
					   txt_remove();
					 
					 
					   msg_window.start(nextstg);
					   current_stg.close();
		
				}
				else {
					error.setText("Error enter the account no in valid form");
				}
			}
			
			else {
				error.setText("plzz Fill Form Completely");
			}
		}
		else {
               if(!Account_name.getText().equals("") && !account_no.getText().equals("") && !Bank_name.getText().equals("")) {
            	   flg=validateAccount_no();
            	   if(flg) {
            		   data[0]=Account_name.getText();
   					   data[1]=account_no.getText();
   					   data[2]=Bank_name.getText();
   					   insert_in_DB(data);
   					   error.setText("");
   					   txt_remove();
   					   msg_window.start(nextstg);
					   current_stg.close();
   					   
   					   
   				  }
   				else {
   					error.setText("Error enter the account no in valid form");
   				}
            	   
		     	}
               else {
            	    error.setText("plzz Fill Form Completely");
               }
			
		}
		
	}
	
	
	public void txt_remove() {
		account_no.setText("");
		Account_name.setText("");
		Bank_name.setText("");	
	}
	
	
	public boolean validateAccount_no() {
		Register_Mazdoor obj=new Register_Mazdoor();
		int get=obj.check_form(account_no.getText());
		boolean flag=false;
		if(get==1) {
		   flag=true;
		}
		return flag;
	}
	
	public Label tags(String txt) {
		Label lbl=new Label(txt);
		lbl.setFont(Font.font(18));
        lbl.setPadding(new Insets(7));
	    
		return lbl;
	}


   public void insert_in_DB(String data[]) {
   try {
	
	String col_names[]= {"Bank_user_name","Account_no","payment_platform"}; 
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/register_labour","root","215020");
	for (int i=0;i<data.length;i++) {
		String queery="update Mazdoor_data set "+col_names[i]+"="+"\'"+data[i]+"\'"+" where Cnic="+"\'"+this.Cnic+"\'";
		PreparedStatement st=cn.prepareStatement(queery);
		st.executeUpdate();
	}
	
	cn.close();	
	
   } 
   
   catch (Exception e) {
	System.out.println(e);
   }
   
	   
   }
	
	public ImageView view(String path)  {
		ImageView v=new ImageView();
		try {
		FileInputStream file=new FileInputStream(path);
		Image img=new Image(file);
		v.setFitWidth(50);
		v.setFitHeight(50);
		v.setImage(img);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return v;
	}
	
	
	
	
	

}
