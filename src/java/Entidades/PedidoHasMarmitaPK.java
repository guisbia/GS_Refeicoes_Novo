/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author bianc
 */
@Embeddable
public class PedidoHasMarmitaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "pedido_id_pedido")
    private int pedidoIdPedido;
    @Basic(optional = false)
    @Column(name = "marmita_id_marmita")
    private int marmitaIdMarmita;

    public PedidoHasMarmitaPK() {
    }

    public PedidoHasMarmitaPK(int pedidoIdPedido, int marmitaIdMarmita) {
        this.pedidoIdPedido = pedidoIdPedido;
        this.marmitaIdMarmita = marmitaIdMarmita;
    }

    public int getPedidoIdPedido() {
        return pedidoIdPedido;
    }

    public void setPedidoIdPedido(int pedidoIdPedido) {
        this.pedidoIdPedido = pedidoIdPedido;
    }

    public int getMarmitaIdMarmita() {
        return marmitaIdMarmita;
    }

    public void setMarmitaIdMarmita(int marmitaIdMarmita) {
        this.marmitaIdMarmita = marmitaIdMarmita;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pedidoIdPedido;
        hash += (int) marmitaIdMarmita;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoHasMarmitaPK)) {
            return false;
        }
        PedidoHasMarmitaPK other = (PedidoHasMarmitaPK) object;
        if (this.pedidoIdPedido != other.pedidoIdPedido) {
            return false;
        }
        if (this.marmitaIdMarmita != other.marmitaIdMarmita) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.PedidoHasMarmitaPK[ pedidoIdPedido=" + pedidoIdPedido + ", marmitaIdMarmita=" + marmitaIdMarmita + " ]";
    }
    
}
