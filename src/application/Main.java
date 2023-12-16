package application;
	
import java.io.FileInputStream;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class Main extends Application {
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
 		
			FileInputStream f2=new FileInputStream("E:\\labour4bg.png");
			Image img1=new Image(f2);
			ImageView view=new ImageView(img1);
			view.setFitWidth(100);
			view.setFitHeight(100);
			
			Label show=new Label("",view);
		    HBox btns=new HBox();
	        btns.setSpacing(10);
			
	        
			btns.getChildren().addAll(btn("Client"),btn("Admin"));
		    
		
			BorderPane Header = new BorderPane();
			Header.setStyle("-fx-background-color: Purple");
			Header.setLeft(show);
			Header.setRight(btns);
			Header.setPrefHeight(30);
			Header.setMargin(btns, new Insets(30));
		   
			Label h1=new Label("WELCOME TO MAZDOOR COMPANY");
			h1.setFont(new Font("Times New Roman",23));
			h1.setWrapText(true);
			h1.setTextFill(Color.GREEN);
			h1.setTextAlignment(TextAlignment.CENTER);
		    
			
		    BorderPane heading=new BorderPane();
			heading.setCenter(h1);
		
		    heading.setMargin(h1, new Insets(20));
		    heading.setStyle("-fx-background-color: White");
		    VBox about=new VBox();
		    about.getChildren().addAll(
		    		about("=> This is Mazdoor App."),
		    		about("=> This app providing you some extra help."),
		            about("=> This app providing you some extra help."),
		            about("=> This app providing you some extra help."),
		            about("=> This app providing you some extra help."));
		    about.setStyle("-fx-background-color: White");
		    
		    HBox Contacts_btn=new HBox();
		    Contacts_btn.setPadding(new Insets(30));
		    Contacts_btn.setSpacing(10);
		  
		    	
			Contacts_btn.getChildren().addAll(get_contact_btn("Contact us"),get_contact_btn("For Any Qerry"));
		    
		   
		    Label Footertxt=new Label("All right reserved @copy created by Muhammad Umair Jatt (22SW01)");
		    Footertxt.setTextFill(Color.WHITE);
		    Footertxt.setFont(Font.font(15));
		    Footertxt.setTextAlignment(TextAlignment.CENTER);
		    Footertxt.setWrapText(true);
		    BorderPane footer=new BorderPane(); 
		    footer.setCenter(Footertxt);
		    footer.setStyle("-fx-background-color:Black");
		    footer.setPadding(new Insets(8));
		    
		    
			VBox layout=new VBox();
			layout.getChildren().addAll(Header,heading,about,Contacts_btn);
		
			
			BorderPane newroot=new BorderPane();
			newroot.setTop(layout);
			newroot.setBottom(footer);
			newroot.setStyle("-fx-background-color:white");
			Scene scene = new Scene(newroot);
			scene.setFill(Color.AQUA);
		
			primaryStage.setScene(scene);
			primaryStage.setWidth(600);
			primaryStage.setMinHeight(600);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Button btn(String text) {
		Button btn=new Button(text);
		btn.setTextFill(Color.BLACK);
		btn.setFont(new Font("Arial",15));
		btn.setPrefWidth(70);
		btn.setPrefHeight(15);
		btn.setStyle("-fx-background-color: white");
		btn.setPadding(new Insets(5));
		btn.setOnAction(this::check_admin_client);
		return btn;
		
	}
	
	public void check_admin_client(ActionEvent e) {
		String data=((Button)e.getSource()).getText();
		Stage st=new Stage();
		if(data.equals("Client")) {
			Make_des obj=new Make_des();
			obj.start(st);
		}
		else {
			Login_portal obj=new Login_portal("Admin");
			obj.start(st);
		}
	}
	
	public Button get_contact_btn(String str) {
		 Button contact=new Button(str);
		    contact.setStyle("-fx-background-color: Purple");
		    contact.setTextFill(Color.WHITE);
			contact.setFont(new Font("Arial",15));
			contact.setPrefWidth(120);
			contact.setPrefHeight(15);
		return contact;
	}
	
	public Label about(String text) {
		Label points=new Label(text); 
		points.setPadding(new Insets(10));
		points.setFont(Font.font(17));
		points.setWrapText(true);
		points.setTextAlignment(TextAlignment.CENTER);
		
		return points;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

