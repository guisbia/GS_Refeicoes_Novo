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
@Table(name = "prato_principal")
@NamedQueries({
    @NamedQuery(name = "PratoPrincipal.findAll", query = "SELECT p FROM PratoPrincipal p")})
public class PratoPrincipal implements Serializable {

    @Column(name = "status")
    private boolean status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pratoPrincipalIdPratoPrincipal")
    private List<Marmita> marmitaList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_prato_principal")
    private Integer idPratoPrincipal;
    @Basic(optional = false)
    @Column(name = "nome_prato_principal")
    private String nomePratoPrincipal;
    @Column(name = "foto_prato_principal")
    private String fotoPratoPrincipal;

    public PratoPrincipal() {
    }

    public PratoPrincipal(Integer idPratoPrincipal) {
        this.idPratoPrincipal = idPratoPrincipal;
    }

    public PratoPrincipal(Integer idPratoPrincipal, String nomePratoPrincipal) {
        this.idPratoPrincipal = idPratoPrincipal;
        this.nomePratoPrincipal = nomePratoPrincipal;
    }

    public Integer getIdPratoPrincipal() {
        return idPratoPrincipal;
    }

    public void setIdPratoPrincipal(Integer idPratoPrincipal) {
        this.idPratoPrincipal = idPratoPrincipal;
    }

    public String getNomePratoPrincipal() {
        return nomePratoPrincipal;
    }

    public void setNomePratoPrincipal(String nomePratoPrincipal) {
        this.nomePratoPrincipal = nomePratoPrincipal;
    }


    public String getFotoPratoPrincipal() {
        return fotoPratoPrincipal;
    }

    public void setFotoPratoPrincipal(String fotoPratoPrincipal) {
        this.fotoPratoPrincipal = fotoPratoPrincipal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPratoPrincipal != null ? idPratoPrincipal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PratoPrincipal)) {
            return false;
        }
        PratoPrincipal other = (PratoPrincipal) object;
        if ((this.idPratoPrincipal == null && other.idPratoPrincipal != null) || (this.idPratoPrincipal != null && !this.idPratoPrincipal.equals(other.idPratoPrincipal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.PratoPrincipal[ idPratoPrincipal=" + idPratoPrincipal + " ]";
    }

    public List<Marmita> getMarmitaList() {
        return marmitaList;
    }

    public void setMarmitaList(List<Marmita> marmitaList) {
        this.marmitaList = marmitaList;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
