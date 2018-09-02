/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author bianc
 */
@Entity
@Table(name = "funcionario")
@NamedQueries({
    @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f")})
public class Funcionario implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionarioIdFuncionario")
    private List<Pedido> pedidoList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_funcionario")
    private Integer idFuncionario;
    @Basic(optional = false)
    @Column(name = "nome_funcionario")
    private String nomeFuncionario;
    @Basic(optional = false)
    @Column(name = "salario_funcionario")
    private double salarioFuncionario;
    @Basic(optional = false)
    @Column(name = "telefone_funcionario")
    private String telefoneFuncionario;
    @Basic(optional = false)
    @Column(name = "data_inicio_funcionario")
    @Temporal(TemporalType.DATE)
    private Date dataInicioFuncionario;
    @Basic(optional = false)
    @Column(name = "nascimento_funcionario")
    @Temporal(TemporalType.DATE)
    private Date nascimentoFuncionario;
    @JoinColumn(name = "status_funcionario_id_status", referencedColumnName = "id_status")
    @ManyToOne(optional = false)
    private StatusFuncionario statusFuncionarioIdStatus;

    public Funcionario() {
    }

    public Funcionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Funcionario(Integer idFuncionario, String nomeFuncionario, double salarioFuncionario, String telefoneFuncionario, Date dataInicioFuncionario, Date nascimentoFuncionario) {
        this.idFuncionario = idFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.salarioFuncionario = salarioFuncionario;
        this.telefoneFuncionario = telefoneFuncionario;
        this.dataInicioFuncionario = dataInicioFuncionario;
        this.nascimentoFuncionario = nascimentoFuncionario;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public double getSalarioFuncionario() {
        return salarioFuncionario;
    }

    public void setSalarioFuncionario(double salarioFuncionario) {
        this.salarioFuncionario = salarioFuncionario;
    }

    public String getTelefoneFuncionario() {
        return telefoneFuncionario;
    }

    public void setTelefoneFuncionario(String telefoneFuncionario) {
        this.telefoneFuncionario = telefoneFuncionario;
    }

    public Date getDataInicioFuncionario() {
        return dataInicioFuncionario;
    }

    public void setDataInicioFuncionario(Date dataInicioFuncionario) {
        this.dataInicioFuncionario = dataInicioFuncionario;
    }

    public Date getNascimentoFuncionario() {
        return nascimentoFuncionario;
    }

    public void setNascimentoFuncionario(Date nascimentoFuncionario) {
        this.nascimentoFuncionario = nascimentoFuncionario;
    }

    public StatusFuncionario getStatusFuncionarioIdStatus() {
        return statusFuncionarioIdStatus;
    }

    public void setStatusFuncionarioIdStatus(StatusFuncionario statusFuncionarioIdStatus) {
        this.statusFuncionarioIdStatus = statusFuncionarioIdStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFuncionario != null ? idFuncionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.idFuncionario == null && other.idFuncionario != null) || (this.idFuncionario != null && !this.idFuncionario.equals(other.idFuncionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Funcionario[ idFuncionario=" + idFuncionario + " ]";
    }

    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }
    
}
