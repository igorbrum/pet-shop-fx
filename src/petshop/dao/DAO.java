package petshop.dao;

import java.util.List;

/**
 *
 * @author Igor Brum
 */
public interface DAO<T> {
    public void salvar(T dominio);
    public void deletar(T dominio);
    public void atualizar(T dominio);
    public List<T> listar();
    public T procurarPorId(int id);
    
}
