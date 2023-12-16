package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Make_des extends Application{
	Stage Current_stg;
	Stage window_stage=new Stage();

	@Override
	public void start(Stage stg) {
		
		Current_stg=stg;
		Button register=new Button();
		Button Login=new Button("Login");
		Label or=new Label(" OR ");
		or.setFont(Font.font(20));
		TilePane layout=new TilePane();
	    layout.getChildren().addAll(btn("Register"),or,btn("Login"));
	    layout.setAlignment(Pos.CENTER);
		BorderPane root=new BorderPane();
	    root.setCenter(layout);
	    root.setStyle("-fx-background-color:white");
		Scene sc=new Scene(root);
		stg.setScene(sc);
		stg.setHeight(400);
		stg.setWidth(400);
		stg.show();
		
		
		
	}
	public static void main(String argss[]) {
		launch(argss);
	}
	
	public Button btn(String txt) {
		Button btn=new Button(txt);
	    btn.setStyle("-fx-background-color: Purple");
	    btn.setTextFill(Color.WHITE);
		btn.setFont(new Font("Arial",15));
		btn.setPrefWidth(100);
		btn.setPrefHeight(15);
		btn.setOnAction(this::check_register_login);
		return btn;
	}
	
	public void check_register_login(ActionEvent e) {
		String data=((Button)e.getSource()).getText();
		if(data.equals("Register")) {
			
			Register_Mazdoor obj=new Register_Mazdoor();
			obj.start(window_stage);
			Current_stg.close();
		}
		else {
			System.out.println("login");
			Login_portal obj=new Login_portal("Client");
			obj.start(window_stage);
			Current_stg.close();
		}
		
	}
}
