package org.openjfx.Maven;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import Models.Show;
import Service.ShowService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomePageProController implements Initializable {

	
	
	
	
	   @FXML
	    private HBox Hbox;
	
	  
	   
	@FXML
 private void SwithtoInterfaceProfil() throws IOException {
     App.setRoot("ProfileInterfacePro");
 }

	  @FXML
	    void SwithtoCon() throws IOException {
		  App.setRoot("Consultation");
	    }
	
	
	
	@FXML
	 private void SwithtoAjout() throws IOException {
	     App.setRoot("AjoutShow");
	 }
			 
	 
	 
	@Override
	public void initialize(URL location, java.util.ResourceBundle resources){
	  try {
		List<Show> show1 =ShowService.Findall2();
		for(Show show :show1){
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("image.fxml"));
		VBox image  = fxmlLoader.load();
		ImageController imageController = fxmlLoader.getController();
		if (imageController != null) {
		    imageController.setData(show);
		}
		//imageController.setData(show);
		Hbox.getChildren().add(image);
		
		}
	}catch(IOException | SQLException e) {
		e.printStackTrace();
	}
		
	
	}
}
