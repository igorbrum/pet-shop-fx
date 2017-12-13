package petshop.dao;

import java.util.List;
import petshop.model.Cliente;

/**
 *
 * @author Igor Brum
 */
public interface ClienteDAO extends DAO<Cliente>{
    public Cliente procurarPorRG(String rg);
    public List<Cliente> listarPorNome(String nome); 
}
