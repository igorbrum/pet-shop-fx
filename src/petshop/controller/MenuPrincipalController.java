package petshop.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Igor Brum
 */
public class MenuPrincipalController implements Initializable {
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    public void cadastrarCliente(ActionEvent event) throws IOException {
        Parent root;
        Stage stage = new Stage();
        root = FXMLLoader.load(petshop.PetShop.class.getResource("view/cliente/CadastrarCliente.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
    @FXML
    public void listarCliente(ActionEvent event) throws IOException{
        Parent root;
        Stage stage = new Stage();
        root = FXMLLoader.load(petshop.PetShop.class.getResource("view/cliente/ListarClientes.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
    @FXML
    public void cadastrarPet(ActionEvent event) throws IOException {
        Parent root;
        Stage stage = new Stage();
        root = FXMLLoader.load(petshop.PetShop.class.getResource("view/pet/CadastrarPet.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
    @FXML
    public void listarPet(ActionEvent event) throws IOException {
        Parent root;
        Stage stage = new Stage();
        root = FXMLLoader.load(petshop.PetShop.class.getResource("view/pet/ListarPets.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
