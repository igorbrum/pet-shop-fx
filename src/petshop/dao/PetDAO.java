package petshop.dao;

import petshop.model.Pet;

/**
 *
 * @author ibrum
 */
public interface PetDAO extends DAO<Pet> {

    public Pet procurarPorNome(String nome);
    
}
