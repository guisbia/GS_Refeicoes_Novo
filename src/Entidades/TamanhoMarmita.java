/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author bianc
 */
@Entity
@Table(name = "tamanho_marmita")
@NamedQueries({
    @NamedQuery(name = "TamanhoMarmita.findAll", query = "SELECT t FROM TamanhoMarmita t")})
public class TamanhoMarmita implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tamanhoMarmita")
    private List<PrecoProduto> precoProdutoList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tamanho_marmita")
    private Integer idTamanhoMarmita;
    @Basic(optional = false)
    @Column(name = "nome_tamanho_marmita")
    private String nomeTamanhoMarmita;

    public TamanhoMarmita() {
    }

    public TamanhoMarmita(Integer idTamanhoMarmita) {
        this.idTamanhoMarmita = idTamanhoMarmita;
    }

    public TamanhoMarmita(Integer idTamanhoMarmita, String nomeTamanhoMarmita) {
        this.idTamanhoMarmita = idTamanhoMarmita;
        this.nomeTamanhoMarmita = nomeTamanhoMarmita;
    }

    public Integer getIdTamanhoMarmita() {
        return idTamanhoMarmita;
    }

    public void setIdTamanhoMarmita(Integer idTamanhoMarmita) {
        this.idTamanhoMarmita = idTamanhoMarmita;
    }

    public String getNomeTamanhoMarmita() {
        return nomeTamanhoMarmita;
    }

    public void setNomeTamanhoMarmita(String nomeTamanhoMarmita) {
        this.nomeTamanhoMarmita = nomeTamanhoMarmita;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTamanhoMarmita != null ? idTamanhoMarmita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TamanhoMarmita)) {
            return false;
        }
        TamanhoMarmita other = (TamanhoMarmita) object;
        if ((this.idTamanhoMarmita == null && other.idTamanhoMarmita != null) || (this.idTamanhoMarmita != null && !this.idTamanhoMarmita.equals(other.idTamanhoMarmita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.TamanhoMarmita[ idTamanhoMarmita=" + idTamanhoMarmita + " ]";
    }

    public List<PrecoProduto> getPrecoProdutoList() {
        return precoProdutoList;
    }

    public void setPrecoProdutoList(List<PrecoProduto> precoProdutoList) {
        this.precoProdutoList = precoProdutoList;
    }
    
}
