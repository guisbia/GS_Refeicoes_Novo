package DAOs;

import Entidades.TamanhoMarmita;
import java.util.ArrayList;
import java.util.List;

public class DAOTamanhoMarmita extends DAOGenerico<TamanhoMarmita> {

private List<TamanhoMarmita> lista = new ArrayList<>();    public DAOTamanhoMarmita(){
        super(TamanhoMarmita.class);
    }

    public int autoIdTamanhoMarmita() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idTamanhoMarmita) FROM TamanhoMarmita) e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<TamanhoMarmita> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Produto e WHERE e.idTamanhoMarmita) LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<TamanhoMarmita> listById(int id) {
        return em.createQuery("SELECT e FROM TamanhoMarmita + e WHERE e.nomeTamanhoMarmita= :id").setParameter("id", id).getResultList();
    }

    public List<TamanhoMarmita> listInOrderNome() {
        return em.createQuery("SELECT e FROM TamanhoMarmita e ORDER BY e.nomeTamanhoMarmita").getResultList();
    }

    public List<TamanhoMarmita> listInOrderId() {
        return em.createQuery("SELECT e FROM TamanhoMarmita e ORDER BY e.idTamanhoMarmita").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<TamanhoMarmita> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdTamanhoMarmita() + "-" + lf.get(i).getNomeTamanhoMarmita());
        }
        return ls;
    }


public static void main(String[] args) {
        DAOTamanhoMarmita daoTamanhoMarmita = new DAOTamanhoMarmita();
        List<TamanhoMarmita> listaTamanhoMarmita = daoTamanhoMarmita.list();
        for (TamanhoMarmita x : listaTamanhoMarmita) {
            System.out.println(x.getIdTamanhoMarmita()+"-"+x.getNomeTamanhoMarmita());
        }
    }}
