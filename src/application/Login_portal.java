package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Login_portal extends Application {
	
	Label H1=new Label("Login");
	Label lbl1=new Label("Enter Cnic :");
	Label lbl2=new Label("Enter password  :");
	TextField Cnic=new TextField();
	TextField password=new TextField();
    Button btn=new Button("Submit");
    Label error=new Label();
    Stage prstage=new Stage();
    Label front_logo=new Label();
	Register_Mazdoor obj=new Register_Mazdoor();
	String Category;
	Label image1=new Label();
    
	public Login_portal(String category) {
		this.Category=category;
	}
     
	@Override
	public void start(Stage primarystage)  {
	  try {
		  
		  error.setTextFill(Color.RED);
		  lbl1.setFont(Font.font ("Italic", FontWeight.BOLD,17));
		  lbl2.setFont(Font.font ("Italic", FontWeight.BOLD,17));
		  
		  lbl1.setPadding(new Insets(6));
		  lbl2.setPadding(new Insets(6));
		 
		  btn.setStyle("-fx-background-color:purple");
		  btn.setTextFill(Color.WHITE);
		  lbl2.setTextFill(Color.PURPLE);
		  lbl1.setTextFill(Color.PURPLE);
		 FileInputStream f1=new FileInputStream("E:\\loginlogo2.png");
		 Image img=new Image(f1,150,150,true,true);
		 ImageView view=new ImageView(img);
		 
		 
		 Cnic.setPadding(new Insets(6));
		 password.setPadding(new Insets(6));
		 Cnic.setPrefWidth(200);
		 password.setPrefWidth(210);
		 	
		 VBox error_btn=new VBox();
		 error_btn.getChildren().addAll(error,btn);
		 error_btn.setPadding(new Insets(10));
		 
		 GridPane labels=new GridPane();
		 
		 labels.add(layout(lbl1,"E:\\Cniclogo1.png",Cnic), 0, 0);
		 labels.add(layout(lbl2,"E:\\lockpic1.png",password), 0, 1);
		 labels.add(error_btn, 0, 2);
		 labels.setColumnSpan(error_btn, 2);
		 labels.setPadding( new Insets(20));
		 
		
		 BorderPane logo =new BorderPane();
		  logo.setCenter(view);
          BorderPane heading=new BorderPane();
          H1.setFont(Font.font ("Italic", FontWeight.BOLD,20));
          H1.setTextFill(Color.PURPLE);
          heading.setCenter(H1);
		  
		  
		  prstage=primarystage;
			VBox root=new VBox();
			root.getChildren().addAll(logo,labels);
		    root.setStyle("-fx-background:white");  
			btn.setOnAction(this::submit);
			Scene sc=new Scene(root);
			primarystage.setScene(sc);
			primarystage.setHeight(500);
			primarystage.setWidth(400);
			
			primarystage.show();
			
	  }
	  catch(Exception e) {
		System.out.println(e);
	  }
		
	}
	
	
	
	public ImageView view(String str) {
		 FileInputStream f2;
		 ImageView view1=null;
		 Image img1=null;
		try {
			f2 = new FileInputStream(str);
			if(str.contains("lock")) {
			 img1=new Image(f2,33,33,true,true);				
			}
			else {				
				img1=new Image(f2,40,40,true,true);
			}
			 view1=new ImageView(img1);
		}
		catch (Exception e) {
		    System.out.println(e);
		} 
	 
		 return view1;
		
	}
	
	public VBox layout(Label lbl,String url,TextField f) {
		ImageView v= view(url);
		Label image=new Label();
		image.setGraphic(v);
		image.setPadding(new Insets(-1));
		HBox Cnic_img=new HBox(); 
		Cnic_img.getChildren().addAll(v,f);
		Cnic_img.setSpacing(3);
		
		VBox Cnic_and_lbl=new VBox();
		Cnic_and_lbl.getChildren().addAll(lbl,Cnic_img);
		Cnic_and_lbl.setPadding(new Insets(10));
		
		return Cnic_and_lbl;
		
	}
	public void submit(ActionEvent e) {
		Stage st=new Stage();
		boolean flag;
		if(!Cnic.getText().equals("") && !password.getText().equals("")) {
		
			int check=obj.check_form(Cnic.getText());
			if(check!=1) {
				error.setText("plzz enter the Cnic in valid form ");
			}
			else {
				if(Category.equals("Client")) {
					System.out.println("client");
				    flag=show_next_window("Mazdoor_data");
				    if(flag) {
				    	Show_portal portal=new Show_portal(Cnic.getText());
				    	portal.start(st);
				    	prstage.close();
				    }

				}
				else {
					
					System.out.println("admin");
					flag=show_next_window("Admin_data");
					if(flag) {
						Admin_Portal portal=new Admin_Portal(Cnic.getText());
						portal.start(st);						
						prstage.close();
					}

				}

			
			}
		}
		else {
			error.setText("plzz fill the form before submitting ");
		}
	
		
	}
	
	
	public boolean show_next_window(String dataBase_name) {
		boolean check_in_DB;
		System.out.println("hello from 2");
		check_in_DB=obj.communicate_with_DB("check","Cnic",Cnic.getText(),dataBase_name);
		System.out.println(check_in_DB);
		if(check_in_DB) {
		      
			check_in_DB=obj.communicate_with_DB("check","pass",password.getText(),dataBase_name);
			System.out.println(check_in_DB);
			if(check_in_DB) {
				error.setText("submitted success fully ");
			}
			else {
				error.setText("invalid Password ");
				
			}
			
			
		}
		else {
			error.setText("invalid Cnic number ");
		}
		return check_in_DB;
		
	}

}
