/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author bianc
 */
@Entity
@Table(name = "pedido_has_marmita")
@NamedQueries({
    @NamedQuery(name = "PedidoHasMarmita.findAll", query = "SELECT p FROM PedidoHasMarmita p")})
public class PedidoHasMarmita implements Serializable {

    @Column(name = "desconto")
    private Double desconto;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PedidoHasMarmitaPK pedidoHasMarmitaPK;
    @Basic(optional = false)
    @Column(name = "qtde")
    private int qtde;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Double valor;
    @JoinColumn(name = "marmita_id_marmita", referencedColumnName = "id_marmita", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Marmita marmita;
    @JoinColumn(name = "pedido_id_pedido", referencedColumnName = "id_pedido", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedido pedido;

    public PedidoHasMarmita() {
    }

    public PedidoHasMarmita(PedidoHasMarmitaPK pedidoHasMarmitaPK) {
        this.pedidoHasMarmitaPK = pedidoHasMarmitaPK;
    }

    public PedidoHasMarmita(PedidoHasMarmitaPK pedidoHasMarmitaPK, int qtde, double valor) {
        this.pedidoHasMarmitaPK = pedidoHasMarmitaPK;
        this.qtde = qtde;
        this.valor = valor;
    }

    public PedidoHasMarmita(int pedidoIdPedido, int marmitaIdMarmita) {
        this.pedidoHasMarmitaPK = new PedidoHasMarmitaPK(pedidoIdPedido, marmitaIdMarmita);
    }

    public PedidoHasMarmitaPK getPedidoHasMarmitaPK() {
        return pedidoHasMarmitaPK;
    }

    public void setPedidoHasMarmitaPK(PedidoHasMarmitaPK pedidoHasMarmitaPK) {
        this.pedidoHasMarmitaPK = pedidoHasMarmitaPK;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Marmita getMarmita() {
        return marmita;
    }

    public void setMarmita(Marmita marmita) {
        this.marmita = marmita;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedidoHasMarmitaPK != null ? pedidoHasMarmitaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoHasMarmita)) {
            return false;
        }
        PedidoHasMarmita other = (PedidoHasMarmita) object;
        if ((this.pedidoHasMarmitaPK == null && other.pedidoHasMarmitaPK != null) || (this.pedidoHasMarmitaPK != null && !this.pedidoHasMarmitaPK.equals(other.pedidoHasMarmitaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.PedidoHasMarmita[ pedidoHasMarmitaPK=" + pedidoHasMarmitaPK + " ]";
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
    
}
