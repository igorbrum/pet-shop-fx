package petshop.controller.pet;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import petshop.model.Cliente;
import petshop.model.Pet;
import petshop.negocio.ClienteNegocio;
import petshop.negocio.NegocioException;
import petshop.negocio.PetNegocio;

/**
 *
 * @author Igor Brum
 */
public class CadastrarPetController implements Initializable {
    private PetNegocio petNegocio;
    private Pet petSelecionado;
    
    private List<Cliente> listaClientes;
    private ClienteNegocio clienteNegocio;
    private ObservableList<Cliente> observableListaPacientes;
    private Cliente clienteSelecionado;
    
    @FXML private Button btnSalvar;
    @FXML private TextField textCliente;
    @FXML private TextField textNomePet;
    @FXML private TextField textTipoPet;
    @FXML private TextField textPesquisarCliente;
    @FXML private TableView<Cliente> tableViewCliente;
    @FXML private TableColumn<Cliente, String> tableColumnNome;
    @FXML private TableColumn<Cliente, String> tableColumnTelefone;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        petNegocio = new PetNegocio();
        clienteNegocio = new ClienteNegocio();
    }
    
    @FXML
    public void salvarDados(){
        Stage stage = (Stage) btnSalvar.getScene().getWindow();
        
        if (petSelecionado == null) {
            String tipoOpcao = "cadastrar";
            if (confirmarAcao(tipoOpcao)) {
                try {
                    petNegocio.salvar(new Pet(textNomePet.getText(), textTipoPet.getText(), clienteSelecionado));
                    stage.close();
                } catch (Exception e) {
                    System.out.println("Errou");
                }
            }
        }
    }
    
    @FXML
    public void getPesquisarNomeCliente(){
        try {
            tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
            
            listaClientes = clienteNegocio.listarPorNome(textPesquisarCliente.getText());
            
            observableListaPacientes = FXCollections.observableArrayList(listaClientes);
            tableViewCliente.setItems(observableListaPacientes);
        } catch (NegocioException ex) {
            Logger.getLogger(CadastrarPetController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Cliente getCliente(){
        clienteSelecionado = tableViewCliente.getSelectionModel().getSelectedItem();
        if (clienteSelecionado != null && petSelecionado == null) {
            textCliente.setText(clienteSelecionado.getNome());
            return clienteSelecionado;
        }
        return null;
    }
    
    public boolean confirmarAcao(String tipo){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Você deseja realmente "+ tipo +" essa entrada?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        
        if (alert.getResult() == ButtonType.YES) {
            return true;
        }
        return false;
    }

    public void setPetSelecionado(Pet petSelecionado) {
        this.petSelecionado = petSelecionado;
        textCliente.setText("1");
        textNomePet.setText(petSelecionado.getNome());
        textTipoPet.setText(petSelecionado.getTipo());
    }
}
