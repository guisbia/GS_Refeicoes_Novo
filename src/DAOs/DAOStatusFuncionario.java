package DAOs;

import Entidades.StatusFuncionario;
import java.util.ArrayList;
import java.util.List;

public class DAOStatusFuncionario extends DAOGenerico<StatusFuncionario> {

private List<StatusFuncionario> lista = new ArrayList<>();    public DAOStatusFuncionario(){
        super(StatusFuncionario.class);
    }

    public int autoIdStatusFuncionario() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idStatus) FROM StatusFuncionario e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<StatusFuncionario> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Produto e WHERE e.idStatus) LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<StatusFuncionario> listById(int id) {
        return em.createQuery("SELECT e FROM StatusFuncionario + e WHERE e.nomeStatus= :id").setParameter("id", id).getResultList();
    }

    public List<StatusFuncionario> listInOrderNome() {
        return em.createQuery("SELECT e FROM StatusFuncionario e ORDER BY e.nomeStatus").getResultList();
    }

    public List<StatusFuncionario> listInOrderId() {
        return em.createQuery("SELECT e FROM StatusFuncionario e ORDER BY e.idStatus").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<StatusFuncionario> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdStatus()+ "-" + lf.get(i).getNomeStatus());
        }
        return ls;
    }


public static void main(String[] args) {
        DAOStatusFuncionario daoStatusFuncionario = new DAOStatusFuncionario();
        List<StatusFuncionario> listaStatusFuncionario = daoStatusFuncionario.list();
        for (StatusFuncionario x : listaStatusFuncionario) {
            System.out.println(x.getIdStatus()+"-"+x.getNomeStatus());
        }
    }}
