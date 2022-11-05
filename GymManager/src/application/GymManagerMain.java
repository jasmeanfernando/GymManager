package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * MVC Pattern: Model File.
 * @author JasmeanFernando
 */
public class GymManagerMain extends Application
{
	@Override
	public void start(Stage primaryStage) throws IOException
	{
		FXMLLoader fxmlLoader = new FXMLLoader(GymManagerMain.class.getResource("GymManagerView.fxml"));
		Scene scene = new Scene(fxmlLoader.load(),600,600);
		primaryStage.setTitle("- GYM MANAGER -");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}