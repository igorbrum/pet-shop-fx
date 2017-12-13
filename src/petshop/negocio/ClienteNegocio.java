package petshop.negocio;

import petshop.dao.ClienteDAO;
import petshop.dao.db.ClienteDAOBD;
import java.util.List;
import petshop.model.Cliente;

/**
 *
 * @author ibrum
 */
public class ClienteNegocio {
    private ClienteDAO clienteDAO;
    
    public ClienteNegocio(){
        this.clienteDAO = new ClienteDAOBD();
    }
    
    public void salvar(Cliente cliente) throws NegocioException {
        this.validarCamposObrigatorios(cliente);
        this.validarRGExistente(cliente);
        clienteDAO.salvar(cliente);
    }
    
    public List<Cliente> listar(){
        return (clienteDAO.listar());
    }
    
    public void deletar(Cliente cliente) throws NegocioException {
        if (cliente == null || cliente.getRg() == null) {
            throw new NegocioException("Cliente nao existe");
        }
        clienteDAO.deletar(cliente);
    }
    
    public void atualizar(Cliente cliente) throws NegocioException {
        if (cliente == null || cliente.getRg() == null) {
            throw new NegocioException("Cliente nao existe");
        }
        clienteDAO.atualizar(cliente);
    }
    public Cliente procurarPorID(int id) throws NegocioException {
        Cliente cliente = clienteDAO.procurarPorId(id);
        
        if (cliente == null) {
            throw new NegocioException("Cliente nao encontrado");
        }
        return (cliente);
    }
    public Cliente procurarPorRG(String rg) throws NegocioException {
        if (rg == null || rg.isEmpty()) {
            throw new NegocioException("RG nao informado!");
        }
        
        Cliente cliente = clienteDAO.procurarPorRG(rg);
        
        if (cliente == null) {
            throw new NegocioException("Cliente nao encontrado!");
        }
        return (cliente);
    }
    
    public List<Cliente> listarPorNome(String nome) throws NegocioException{
        if (nome == null || nome.isEmpty()) {
            throw new NegocioException("Nome nao informado!");
        }
        return(clienteDAO.listarPorNome(nome));
    }
    
    public boolean clienteExiste(String rg) {
        Cliente cliente = clienteDAO.procurarPorRG(rg);
        return (cliente != null);
    }
    
    private void validarCamposObrigatorios(Cliente cliente) throws NegocioException {
        if (cliente.getRg() == null || cliente.getRg().isEmpty()) {
            throw new NegocioException("RG nao informado!");
        }
        
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new NegocioException("Nome nao informado!");
        }
    }
    
    private void validarRGExistente(Cliente cliente) throws NegocioException {
        if (clienteExiste(cliente.getRg())) {
            throw new NegocioException("RG ja existe");
        }
    }
}
