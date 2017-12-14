package petshop.model;

import java.util.Objects;

/**
 *
 * @author ibrum
 */
public class Pet {
    private int id;
    private String nome, tipo;
    private Cliente dono;
    
    public Pet(int id, String nome, String tipo, Cliente dono) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.dono = dono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pet(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public Pet(String nome, String tipo, Cliente dono) {
        this.nome = nome;
        this.tipo = tipo;
        this.dono = dono;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public Cliente getDono() {
        return dono;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pet outro = (Pet) obj;
        if (!Objects.equals(this.nome, outro.nome)) {
            return false;
        }
        return true;
    }

    public void setNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setTipo(String tipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
