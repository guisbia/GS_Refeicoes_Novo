package DAOs;

import Entidades.Marmita;
import java.util.ArrayList;
import java.util.List;

public class DAOMarmita extends DAOGenerico<Marmita> {

private List<Marmita> lista = new ArrayList<>();    public DAOMarmita(){
        super(Marmita.class);
    }

    public int autoIdMarmita() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idMarmita) FROM Marmita) e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }
    
    public Marmita obter(int id) {
        return em.find(Marmita.class, id);
    }


    public List<Marmita> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Marmita e WHERE e.idMarmita) LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Marmita> listById(int id) {
        return em.createQuery("SELECT e FROM Marmita e WHERE e.idMarmita= :id").setParameter("id", id).getResultList();
    }

    public List<Marmita> listInOrderNome() {
        return em.createQuery("SELECT e FROM Marmita e ORDER BY e.pratoPrincipalIdPratoPrincipal").getResultList();
    }

    public List<Marmita> listInOrderId() {
        return em.createQuery("SELECT e FROM Marmita e ORDER BY e.idMarmita").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Marmita> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdMarmita() + "-" + lf.get(i).getPratoPrincipalIdPratoPrincipal().getNomePratoPrincipal()+"-"+lf.get(i).getTamanhoMarmitaIdTamanhoMarmita().getNomeTamanhoMarmita());
        }
        return ls;
    }


public static void main(String[] args) {
        DAOMarmita daoMarmita = new DAOMarmita();
        List<Marmita> listaMarmita = daoMarmita.list();
        for (Marmita x : listaMarmita) {
            System.out.println(x.getIdMarmita()+"-"+x.getPratoPrincipalIdPratoPrincipal());
        }
    }}
