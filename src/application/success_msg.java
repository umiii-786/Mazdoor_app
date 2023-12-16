package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class success_msg extends Application{

	@Override
	public void start(Stage stg)
	{
		try {
			
	
	        Label Success_msg=new Label();
	        
	        Success_msg.setText("Submitted Successfuly");
	        Success_msg.setFont(new Font("Arial",22));
	        Success_msg.setTextFill(Color.GREEN);
	        BorderPane root=new BorderPane();
	        root.setCenter(Success_msg);
	        root.setStyle("-fx-background-color:white");
	        Scene sc=new Scene(root);
	        stg.setTitle("Submitted");
	        
	        stg.setScene(sc);
	        stg.setHeight(400);
	        stg.setWidth(400);
	        stg.show();
		
	}
		catch(Exception e) {
			
		}
	}
	
}
