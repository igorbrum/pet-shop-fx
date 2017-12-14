package petshop.negocio;

import petshop.dao.PetDAO;
import petshop.dao.db.PetDAOBD;
import java.util.List;
import petshop.model.Pet;

/**
 *
 * @author ibrum
 */
public class PetNegocio {
    private PetDAO petDAO;
    
    public PetNegocio(){
        this.petDAO = new PetDAOBD();
    }
    
    public void salvar(Pet pet) throws NegocioException {
        this.validarCamposObrigatorios(pet);
        this.petDAO.salvar(pet);
    }
    
    public void deletar(Pet pet) throws NegocioException{
        if (pet == null || pet.getNome() == null) {
            throw new NegocioException("Pet nao existe!");
        }
        this.petDAO.deletar(pet);
    }
    
    public void atualizar(Pet pet) throws NegocioException{
        if (pet == null || pet.getNome() == null) {
            throw new NegocioException("Pet nao existe!");
        }
        this.petDAO.atualizar(pet);
    }
    
    public List<Pet> listar() {
        return (petDAO.listar());
    }
    
    private void validarCamposObrigatorios(Pet pet) throws NegocioException{
        if (pet.getNome() == null || pet.getNome().isEmpty()) {
            throw new NegocioException("Nome nao informado");
        }
    }
    
    public Pet procurarPorNome(String nome) throws NegocioException {
        if (nome == null || nome.isEmpty()) {
            throw new NegocioException("Nome nao informado");
        }
        
        Pet pet = petDAO.procurarPorNome(nome);
        
        if (pet == null) {
            throw new NegocioException("Pet nao encontrado");
        }
        return (pet);
    }
}
