package DAOs;

import Entidades.PedidoHasMarmita;
import Entidades.PedidoHasMarmitaPK;
import java.util.ArrayList;
import java.util.List;

public class DAOPedidoHasMarmita extends DAOGenerico<PedidoHasMarmita> {

    private List<PedidoHasMarmita> lista = new ArrayList<>();

    public DAOPedidoHasMarmita() {
        super(PedidoHasMarmita.class);
    }

    public int autoIdPedidoHasMarmita() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idPedidoHasMarmita) FROM PedidoHasMarmita) e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }
    
     public PedidoHasMarmita obter(PedidoHasMarmitaPK pedidoHasMarmitaPK) {
        return em.find(PedidoHasMarmita.class, pedidoHasMarmitaPK);
    }

    public List<PedidoHasMarmita> listByNome(String nome) {
        return em.createQuery("SELECT e FROM PedidoHasMarmita e WHERE e.qtde) LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<PedidoHasMarmita> listById(int id) {
        return em.createQuery("SELECT e FROM PedidoHasMarmita e WHERE e.pedido.idPedido= :id").setParameter("id", id).getResultList();
    }

    public List<PedidoHasMarmita> listInOrderNome() {
        return em.createQuery("SELECT e FROM PedidoHasMarmita e ORDER BY e.valor").getResultList();
    }

    public List<PedidoHasMarmita> listInOrderId() {
        return em.createQuery("SELECT e FROM PedidoHasMarmita e ORDER BY e.pedido.idPedido").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<PedidoHasMarmita> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getQtde() + "-" + lf.get(i).getValor());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOPedidoHasMarmita daoPedidoHasMarmita = new DAOPedidoHasMarmita();
        List<PedidoHasMarmita> listaPedidoHasMarmita = daoPedidoHasMarmita.listById(1);
        for (PedidoHasMarmita x : listaPedidoHasMarmita) {
            System.out.println(x.getPedidoHasMarmitaPK().getPedidoIdPedido()+ " - "+x.getPedidoHasMarmitaPK().getMarmitaIdMarmita()+ " - "+ x.getQtde() + "-" + x.getValor() + " - "+ x.getDesconto());
        }
    }
}
