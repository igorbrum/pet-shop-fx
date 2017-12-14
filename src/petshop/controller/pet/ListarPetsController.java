package petshop.controller.pet;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import petshop.model.Pet;
import petshop.negocio.ClienteNegocio;
import petshop.negocio.NegocioException;
import petshop.negocio.PetNegocio;

/**
 *
 * @author Igor Brum
 */
public class ListarPetsController implements Initializable{
    private PetNegocio petNegocio;
    private ClienteNegocio clienteNegocio;
    private List<Pet> listaPets;
    private Pet petSelecionado;
    private ObservableList<Pet> observableListaPets;
    
    @FXML private VBox painelPet;
    @FXML private TableView<Pet> tableViewPet;
    //@FXML private TableColumn<Pet, String> tableColumnCliente;
    @FXML private TableColumn<Pet, Integer> tableColumnCliente = new TableColumn<>("id_cliente");
    @FXML private TableColumn<Pet, String> tableColumnNome;
    @FXML private TableColumn<Pet, String> tableColumnTipoPet;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        petNegocio = new PetNegocio();
        listarPets();
    }
    
    @FXML
    public void listarPets() {
        String teste = new PropertyValueFactory<Pet, Integer>("id").toString();
        System.out.println(teste);
        tableColumnCliente.setCellValueFactory(new PropertyValueFactory<Pet, Integer>("id"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnTipoPet.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        
        listaPets = petNegocio.listar();
        
        observableListaPets = FXCollections.observableArrayList(listaPets);
        tableViewPet.setItems(observableListaPets);
    }
    
    @FXML
    public void cadastrarPet() throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(petshop.PetShop.class.getResource("view/pet/CadastrarPet.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(painelPet.getScene().getWindow());
        stage.showAndWait();
    }
    
    @FXML
    public void editarDados(){
        petSelecionado = tableViewPet.getSelectionModel().getSelectedItem();
        
        if (petSelecionado != null) {
            try {
                FXMLLoader loader = new FXMLLoader(petshop.PetShop.class.getResource("view/pet/CadastrarPet.fxml"));
                Parent root = (Parent) loader.load();
                
                CadastrarPetController controller = (CadastrarPetController) loader.getController();
                controller.setPetSelecionado(petSelecionado);
                
                Stage dialogStage = new Stage();
                dialogStage.setScene(new Scene(root));
                dialogStage.initModality(Modality.APPLICATION_MODAL);
                dialogStage.initOwner(painelPet.getScene().getWindow());
                dialogStage.showAndWait();
                this.listarPets();
            } catch (IOException ex) {
                Logger.getLogger(ListarPetsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    public void deletarPet(ActionEvent event) throws IOException {
        petSelecionado = tableViewPet.getSelectionModel().getSelectedItem();
        
        if (petSelecionado != null) {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Você deseja realmente excluir essa entrada?",ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                try {
                    petNegocio.deletar(petSelecionado);
                    this.listarPets();
                } catch (NegocioException ex) {
                    Logger.getLogger(ListarPetsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
