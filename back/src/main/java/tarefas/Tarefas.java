/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarefas;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Computação
 */
@Entity
@Table(name = "tarefas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tarefas.findAll", query = "SELECT t FROM Tarefas t"),
    @NamedQuery(name = "Tarefas.findById", query = "SELECT t FROM Tarefas t WHERE t.id = :id"),
    @NamedQuery(name = "Tarefas.findByDescricao", query = "SELECT t FROM Tarefas t WHERE t.descricao LIKE :descricao"),
    @NamedQuery(name = "Tarefas.findByConcluida", query = "SELECT t FROM Tarefas t WHERE t.concluida = :concluida")})
public class Tarefas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descricao")
    private String descricao;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "concluida")
    private boolean concluida;

    public Tarefas() {
    }

    public Tarefas(Integer id) {
        this.id = id;
    }

    public Tarefas(Integer id, String descricao, boolean concluida) {
        this.id = id;
        this.descricao = descricao;
        this.concluida = concluida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarefas)) {
            return false;
        }
        Tarefas other = (Tarefas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tarefas.Tarefas[ id=" + id + " ]";
    }
    
}
