package DAOs;

import Entidades.PrecoProduto;
import Entidades.PrecoProdutoPK;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DAOPrecoProduto extends DAOGenerico<PrecoProduto> {

    private List<PrecoProduto> lista = new ArrayList<>();

    public DAOPrecoProduto() {
        super(PrecoProduto.class);
    }

    public PrecoProduto obter(PrecoProdutoPK precoProdutoPK) {
        return em.find(PrecoProduto.class, precoProdutoPK);
    }

    public List<PrecoProduto> listByNome(String nome) {
        return em.createQuery("SELECT e FROM PrecoProduto e WHERE e.tamanhoMarmita.nomeTamanhoMarmita) LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<PrecoProduto> listById(int id) {
        return em.createQuery("SELECT e FROM PrecoProduto e WHERE e.precoProdutoPK.tamanhoMarmitaIdTamanhoMarmita = :id").setParameter("id", id).getResultList();
    }

    public List<PrecoProduto> listInOrderNome() {
        return em.createQuery("SELECT e FROM PrecoProduto e ORDER BY e.tamanhoMarmita").getResultList();
    }

    public List<PrecoProduto> listInOrderId() {
        return em.createQuery("SELECT e FROM PrecoProduto e ORDER BY e.precoProdutoPK.tamanhoMarmitaIdTamanhoMarmita").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<PrecoProduto> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getPrecoProdutoPK().getTamanhoMarmitaIdTamanhoMarmita()+ "-"
                    + lf.get(i).getTamanhoMarmita().getNomeTamanhoMarmita()+ "-"
                    + sdf.format(lf.get(i).getPrecoProdutoPK().getDataPrecoProduto())
                    + "-" + lf.get(i).getPreco());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOPrecoProduto daoPrecoProduto = new DAOPrecoProduto();
        List<PrecoProduto> listaPrecoProduto = daoPrecoProduto.list();
        for (PrecoProduto precoProduto : listaPrecoProduto) {
            System.out.println(precoProduto.getPreco() + "-" + precoProduto.getTamanhoMarmita());
        }
    }
}
