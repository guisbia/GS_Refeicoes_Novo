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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author bianc
 */
@Entity
@Table(name = "marmita")
@NamedQueries({
    @NamedQuery(name = "Marmita.findAll", query = "SELECT m FROM Marmita m")})
public class Marmita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_marmita")
    private Integer idMarmita;
    @Column(name = "status")
    private Short status;
    @JoinColumn(name = "prato_principal_id_prato_principal", referencedColumnName = "id_prato_principal")
    @ManyToOne(optional = false)
    private PratoPrincipal pratoPrincipalIdPratoPrincipal;
    @JoinColumn(name = "tamanho_marmita_id_tamanho_marmita", referencedColumnName = "id_tamanho_marmita")
    @ManyToOne(optional = false)
    private TamanhoMarmita tamanhoMarmitaIdTamanhoMarmita;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "marmita")
    private List<PedidoHasMarmita> pedidoHasMarmitaList;

    public Marmita() {
    }

    public Marmita(Integer idMarmita) {
        this.idMarmita = idMarmita;
    }

    public Integer getIdMarmita() {
        return idMarmita;
    }

    public void setIdMarmita(Integer idMarmita) {
        this.idMarmita = idMarmita;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public PratoPrincipal getPratoPrincipalIdPratoPrincipal() {
        return pratoPrincipalIdPratoPrincipal;
    }

    public void setPratoPrincipalIdPratoPrincipal(PratoPrincipal pratoPrincipalIdPratoPrincipal) {
        this.pratoPrincipalIdPratoPrincipal = pratoPrincipalIdPratoPrincipal;
    }

    public TamanhoMarmita getTamanhoMarmitaIdTamanhoMarmita() {
        return tamanhoMarmitaIdTamanhoMarmita;
    }

    public void setTamanhoMarmitaIdTamanhoMarmita(TamanhoMarmita tamanhoMarmitaIdTamanhoMarmita) {
        this.tamanhoMarmitaIdTamanhoMarmita = tamanhoMarmitaIdTamanhoMarmita;
    }

    public List<PedidoHasMarmita> getPedidoHasMarmitaList() {
        return pedidoHasMarmitaList;
    }

    public void setPedidoHasMarmitaList(List<PedidoHasMarmita> pedidoHasMarmitaList) {
        this.pedidoHasMarmitaList = pedidoHasMarmitaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMarmita != null ? idMarmita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marmita)) {
            return false;
        }
        Marmita other = (Marmita) object;
        if ((this.idMarmita == null && other.idMarmita != null) || (this.idMarmita != null && !this.idMarmita.equals(other.idMarmita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Marmita[ idMarmita=" + idMarmita + " ]";
    }
    
}
