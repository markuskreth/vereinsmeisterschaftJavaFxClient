package de.kreth.trampolinscore.gui;
	
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import de.kreth.trampolinscore.FactoryProductive;


public class Main extends Application {
   
	@Override
	public void start(Stage primaryStage) {

      final FactoryProductive factoryProductive = new FactoryProductive();
      
		try {
		   URLClassLoader contextClassLoader = (URLClassLoader) Thread.currentThread().getContextClassLoader();
		   for(URL url: contextClassLoader.getURLs()) {
		      System.out.println(url.getFile());
		   }
         System.out.println();
         System.out.println();

		   ResourceBundle mainBundle = ResourceBundle.getBundle(Main.class.getSimpleName(), Locale.getDefault(), getClass().getClassLoader());
		   
		   FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
		   loader.setResources(mainBundle);
		   BorderPane root = loader.load();
		   Scene scene = new Scene(root,900,600);
		   root.autosize();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			MainController controller = loader.getController();
			controller.setPrimaryStage(primaryStage);
		} catch(Exception e) {
			e.printStackTrace();
			factoryProductive.getPersister().close();
		}
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

         @Override
         public void handle(WindowEvent event) {
            factoryProductive.getPersister().close();
         }});
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
