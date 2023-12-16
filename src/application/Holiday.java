package application;

import java.io.FileInputStream;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Holiday extends Application{
		
    int working_day;
    int non_working_day;
    String Level;
    String Cnic;
	
	
    BorderPane logo=new BorderPane();
	RadioButton Eid=new RadioButton("Eid");
	
	RadioButton Muharram=new RadioButton("Muharram");
	RadioButton Ramzan=new RadioButton("Ramzan");
	RadioButton Diwali=new RadioButton("Diwali");
	RadioButton Holi=new RadioButton("Holi");
	RadioButton Friday=new RadioButton("Friday");
	RadioButton another=new RadioButton("another");
	ToggleGroup group=new ToggleGroup();
	HBox hr=new HBox();
	VBox button_layout=new VBox();
    Button submit=new Button("Submit");
	VBox root=new VBox();
	Label success_msg=new Label();
    Label or=new Label("OR");
    VBox showopt=new VBox();
    Button btn2=new Button("Special Holiday");
	Button btn1=new Button("Common Holiday");
	Stage current;
	Label h1=new Label("Chosse One From Them.....!");
	BorderPane heading =new BorderPane();
	VBox sub=new VBox();
	
	
	 Holiday(String str,String str2,String lev,String Cnic){
		working_day=Integer.parseInt(str);
		non_working_day=Integer.parseInt(str2);
		Level=lev;
		this.Cnic=Cnic;
	}
	
	@Override
	public void start(Stage Holiday_stg)  {
	try {
		h1.setFont(Font.font("italic",FontWeight.EXTRA_BOLD,25));
		h1.setTextFill(Color.GREEN);
	    heading.setCenter(h1);
		btn1.setStyle("-fx-background-color:skyblue");
		btn1.setFont(Font.font("italic",FontWeight.BOLD,14));
		btn1.setOnAction(this::holiday);
		btn2.setStyle("-fx-background-color:skyblue");
		btn2.setFont(Font.font("italic",FontWeight.BOLD,14));
		btn2.setOnAction(this::holiday);
		
		
		FileInputStream f1=new FileInputStream("E:\\Holiday2.png");
		Image img=new Image(f1);
		ImageView View=new ImageView(img);
		View.setFitWidth(250);
		View.setFitHeight(150);
		logo.setCenter(View);
		
		or.setFont(Font.font("",FontWeight.BOLD,15));
		TilePane buttons=new TilePane();
		buttons.getChildren().addAll(btn1,or,btn2);
		buttons.setAlignment(Pos.CENTER);
		Eid.setToggleGroup(group);
		Muharram.setToggleGroup(group);
		Ramzan.setToggleGroup(group);
		Holi.setToggleGroup(group);
		Diwali.setToggleGroup(group);
		Friday.setToggleGroup(group);
		another.setToggleGroup(group);
		Friday.setPadding(new Insets(5));
		another.setPadding(new Insets(5));
		Diwali.setPadding(new Insets(5));
		Holi.setPadding(new Insets(5));
		Ramzan.setPadding(new Insets(5));
		Muharram.setPadding(new Insets(5));
		Eid.setPadding(new Insets(5));
		
		Diwali.setFont(Font.font("ITALIC",FontWeight.BOLD,16));
		Ramzan.setFont(Font.font("ITALIC",FontWeight.BOLD,16));
		Eid.setFont(Font.font("ITALIC",FontWeight.BOLD,16));
		Muharram.setFont(Font.font("ITALIC",FontWeight.BOLD,16));
		Holi.setFont(Font.font("ITALIC",FontWeight.BOLD,16));
		another.setFont(Font.font("ITALIC",FontWeight.BOLD,16));
		Friday.setFont(Font.font("ITALIC",FontWeight.BOLD,16));
        
		submit.setStyle("-fx-background-color:black");
		submit.setTextFill(Color.WHITE);
		submit.setFont(Font.font("Italic",FontWeight.BOLD,13));
		sub.getChildren().add(submit);
		
		submit.setOnAction(this::check_level);
	    success_msg.setFont(Font.font(20));
	    success_msg.setTextFill(Color.GREEN);
	    success_msg.setTextAlignment(TextAlignment.CENTER);
		success_msg.setWrapText(true);
		success_msg.setPadding(new Insets(20));
		root.getChildren().addAll(logo,buttons);
		root.setStyle("-fx-background-color:white");
		Scene sc=new Scene(root);
		Holiday_stg.setScene(sc);
		Holiday_stg.setHeight(420);
		Holiday_stg.setWidth(450);
		current=Holiday_stg;
		Holiday_stg.show();
			
	}
	catch(Exception e) {
		System.out.println(e);
	}
		
	}
	
//	public static void main(String args[]) {
//		launch(args);
//	}

	public void holiday(ActionEvent e) {
		String txt=((Button)e.getSource()).getText();
		root.getChildren().clear();
	    Button btn=(Button)e.getSource();
	    btn.setOnAction(null);
	    current.setWidth(400);
		if(txt.equalsIgnoreCase("common holiday")) {
			showopt.getChildren().addAll(Friday,another,sub);		
			
		}
		else {
			current.setHeight(500);
			showopt.getChildren().addAll(Eid,Muharram,Holi,Diwali,Ramzan,sub);
		
		}
		showopt.setPadding(new Insets(10));
		sub.setPadding(new Insets(6));
		root.getChildren().addAll(logo,heading,showopt);
		root.setPadding(new Insets(6));
		
	}

//	public static void main(String args[]) {
//		launch(args);
//	}
	public void check_level(ActionEvent e) {
		if(Eid.isSelected() || Muharram.isSelected() || Ramzan.isSelected() 
			|| Friday.isSelected() || another.isSelected()|| Diwali.isSelected()  || Holi.isSelected()  ) {
			
		      if(Level.equalsIgnoreCase("skilled")) {
			    giveSalary(500);
		      }
		      else {
			    giveSalary(400);
		      }
		
		  }
		else {
			System.out.println("chosee any one of them ");
		} 

		
	}
	
	public void giveSalary(int amount) {
		String selected=((RadioButton)group.getSelectedToggle()).getText();
		if(selected.equals("Eid") || selected.equals("Ramzan") || selected.equals("Holi")
				|| selected.equals("Diwali") || selected.equals("Muharram")) {
				
			if(working_day>=180) {
				root.getChildren().clear();
				success_msg.setText("Send you "+amount+"rs of allowene on "+selected+" event ");
//				root.getChildren().add(success_msg);
			}
			else {
				success_msg.setText("beacause you are new we Send you "+ (amount-200) + "as a allowene on "+selected+" event ");
				
			}
			root.getChildren().add(success_msg);
			
		 }
		
		else{
			
			if(selected.equals("Friday")) {
				success_msg.setText("successfully sended you 100rs on"+selected+" day");	
			}
			else {
			   success_msg.setText("Ok");
			   start_Working obj=new start_Working();
			   obj.add_working_and_non_working_days(Cnic,"non_working_day",non_working_day);
			   
			}
			root.getChildren().add(success_msg);
		}
		
//		root.setCenter(success_msg);
		
	}
	
	
	

}
