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
@Table(name = "status_funcionario")
@NamedQueries({
    @NamedQuery(name = "StatusFuncionario.findAll", query = "SELECT s FROM StatusFuncionario s")})
public class StatusFuncionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_status")
    private Integer idStatus;
    @Basic(optional = false)
    @Column(name = "nome_status")
    private String nomeStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusFuncionarioIdStatus")
    private List<Funcionario> funcionarioList;

    public StatusFuncionario() {
    }

    public StatusFuncionario(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public StatusFuncionario(Integer idStatus, String nomeStatus) {
        this.idStatus = idStatus;
        this.nomeStatus = nomeStatus;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public String getNomeStatus() {
        return nomeStatus;
    }

    public void setNomeStatus(String nomeStatus) {
        this.nomeStatus = nomeStatus;
    }

    public List<Funcionario> getFuncionarioList() {
        return funcionarioList;
    }

    public void setFuncionarioList(List<Funcionario> funcionarioList) {
        this.funcionarioList = funcionarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStatus != null ? idStatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusFuncionario)) {
            return false;
        }
        StatusFuncionario other = (StatusFuncionario) object;
        if ((this.idStatus == null && other.idStatus != null) || (this.idStatus != null && !this.idStatus.equals(other.idStatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.StatusFuncionario[ idStatus=" + idStatus + " ]";
    }
    
}
