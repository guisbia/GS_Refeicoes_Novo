package DAOs;

import Entidades.PratoPrincipal;
import java.util.ArrayList;
import java.util.List;

public class DAOPratoPrincipal extends DAOGenerico<PratoPrincipal> {

private List<PratoPrincipal> lista = new ArrayList<>();    public DAOPratoPrincipal(){
        super(PratoPrincipal.class);
    }

    public int autoIdPratoPrincipal() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idPratoPrincipal) FROM PratoPrincipal) e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<PratoPrincipal> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Produto e WHERE e.idPratoPrincipal) LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<PratoPrincipal> listById(int id) {
        return em.createQuery("SELECT e FROM PratoPrincipal + e WHERE e.nomePratoPrincipal= :id").setParameter("id", id).getResultList();
    }

    public List<PratoPrincipal> listInOrderNome() {
        return em.createQuery("SELECT e FROM PratoPrincipal e ORDER BY e.nomePratoPrincipal").getResultList();
    }

    public List<PratoPrincipal> listInOrderId() {
        return em.createQuery("SELECT e FROM PratoPrincipal e ORDER BY e.idPratoPrincipal").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<PratoPrincipal> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdPratoPrincipal() + "-" + lf.get(i).getNomePratoPrincipal());
        }
        return ls;
    }


public static void main(String[] args) {
        DAOPratoPrincipal daoPratoPrincipal = new DAOPratoPrincipal();
        List<PratoPrincipal> listaPratoPrincipal = daoPratoPrincipal.list();
        for (PratoPrincipal x : listaPratoPrincipal) {
            System.out.println(x.getIdPratoPrincipal()+"-"+x.getNomePratoPrincipal());
        }
    }}
