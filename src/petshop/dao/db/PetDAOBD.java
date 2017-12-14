package petshop.dao.db;

import petshop.dao.db.ClienteDAOBD;
import petshop.dao.PetDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import petshop.model.Cliente;
import petshop.model.Pet;

/**
 *
 * @author ibrum
 */
public class PetDAOBD extends DAOBD<Pet> implements PetDAO{
    
    @Override
    public void salvar(Pet pet) {
        int id = 0;
        try {
            String sql = "INSERT INTO pets (nome, tipo, id_cliente)"
                        +"VALUES (?, ? , ?)";
            
            conectarObtendoID(sql);
            
            comando.setString(1, pet.getNome());
            comando.setString(2, pet.getTipo());
            comando.setInt(3, pet.getDono().getId());
            
            comando.executeUpdate();
            
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                id = resultado.getInt(1);
                pet.setId(id);
            } else {
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar o pet no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(Pet pet) {
        try {
            String sql = "DELETE FROM pets WHERE id = ?";
            
            conectar(sql);
            comando.setInt(1, pet.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar cliente no Banco de Dados!");
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void atualizar(Pet pet) {
        try {
            String sql = "UPDATE pets SET nome=?, tipo=?, id_cliente=? "
                    + "WHERE id=?";
            conectar(sql);
            
            comando.setString(1, pet.getNome());
            comando.setString(2, pet.getTipo());
            comando.setInt(3, pet.getDono().getId());
            comando.setInt(4, pet.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar o pet no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Pet> listar() {
        List<Pet> listaPets = new ArrayList<>();
        
        String sql = "SELECT * FROM pets";
        
        try {
            conectar(sql);
            
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                String tipo = resultado.getString("tipo");
                int id_cliente =  resultado.getInt("id_cliente");
                
                Cliente dono = new ClienteDAOBD().procurarPorId(id_cliente);
                Pet pet = new Pet(id, nome, tipo, dono);
                
                listaPets.add(pet);
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os pets do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaPets);
    }

    @Override
    public Pet procurarPorId(int id) {
        String sql = "SELECT * FROM pets WHERE id = ?";
        
        try {
            conectar(sql);
            comando.setInt(1, id);
            
            ResultSet resultado = comando.executeQuery();
            
            if (resultado.next()) {
                String nome = resultado.getString("nome");
                String tipo = resultado.getString("tipo");
                int id_cliente = resultado.getInt("id_cliente");
                Cliente dono = new ClienteDAOBD().procurarPorId(id_cliente);
                
                Pet pet = new Pet(id, nome, tipo, dono);
                
                return pet;
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o tipo de servico pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }

    @Override
    public Pet procurarPorNome(String nome) {
        String slq = "SELECT * FROM pets WHERE nome = ?";
        
        try {
            conectar(slq);
            comando.setString(1, nome);
            
            ResultSet resultado = comando.executeQuery();
            
            if (resultado.next()) {
                String nome_pet = resultado.getString("nome");
                String tipo = resultado.getString("tipo");
                int id = resultado.getInt("id");
                
                Cliente dono = new ClienteDAOBD().procurarPorId(id);
                
                Pet pet = new Pet(id, nome_pet, tipo, dono);
                return pet;
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o tipo de servico pelo nome no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }
    
}
