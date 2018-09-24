package GUIs;

import DAOs.DAOMarmita;
import Entidades.Marmita;
import Entidades.PedidoHasMarmita;
import Entidades.PedidoHasMarmitaPK;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PedidoHasMarmitaTableModel extends AbstractTableModel {

    private final Class classes[] = new Class[]{Integer.class, Integer.class, Integer.class, Double.class, Double.class, Double.class};
    private final String colunas[] = new String[]{"idPedido", "idMarmita", "qtde", "valor", "desconto", "subtotal"};
    private List<PedidoHasMarmita> dados;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    //private final SimpleDateFormat timeformat = new SimpleDateFormat("h:mm a");

    public PedidoHasMarmitaTableModel(List<PedidoHasMarmita> dados) {
        this.dados = dados;
    }

    public void setDados(List<PedidoHasMarmita> dados) {
        this.dados = dados;
    }

    public List<PedidoHasMarmita> getDados() {
        return this.dados;
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return classes[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        PedidoHasMarmita p = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getPedidoHasMarmitaPK().getPedidoIdPedido();
            case 1:
                return p.getPedidoHasMarmitaPK().getMarmitaIdMarmita();
            case 2:
                return p.getQtde();
            case 3:
                return p.getValor();
            case 4:
                return p.getDesconto();
            case 5:
                return p.getQtde() * p.getValor();
            default:
                throw new IndexOutOfBoundsException("Coluna Inválida!");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0 | columnIndex == 1) {
            return false;
        }
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        //  mudou = true;
        PedidoHasMarmita p = dados.get(rowIndex);
        PedidoHasMarmitaPK pedidoHasMarmitaPK = new PedidoHasMarmitaPK();
        switch (columnIndex) {
            case 0:
                pedidoHasMarmitaPK.setPedidoIdPedido((int) aValue);
                p.setPedidoHasMarmitaPK(pedidoHasMarmitaPK);
                break;
            case 1:
                //p.setQtde((String) aValue);
                pedidoHasMarmitaPK.setMarmitaIdMarmita((int) aValue);
                p.setPedidoHasMarmitaPK(pedidoHasMarmitaPK);
                break;
            case 2:
                p.setQtde((int) aValue);
                break;
            case 3:
                p.setValor((double) aValue);
                break;
            case 4:
                DAOMarmita daoMarmita = new DAOMarmita();
               // Marmita marmita = daoMarmita.obter(p.getMarmita().getIdMarmita());
                Marmita marmita = daoMarmita.obter(p.getPedidoHasMarmitaPK().getMarmitaIdMarmita());

                double valorEtiqueta = marmita.getTamanhoMarmitaIdTamanhoMarmita().getPrecoProdutoList().get(marmita.getTamanhoMarmitaIdTamanhoMarmita().getPrecoProdutoList().size() - 1).getPreco();
                double y = valorEtiqueta - p.getValor();
                p.setDesconto((double) y);
                break;
            case 5:
                double x = p.getQtde() * p.getValor();
            default:
                throw new IndexOutOfBoundsException("Coluna Inválida!!!");
        }
        fireTableDataChanged();
    }

    public PedidoHasMarmita getValue(int rowIndex) {
        return dados.get(rowIndex);
    }

    public int indexOf(PedidoHasMarmita tc) {
        return dados.indexOf(tc);
    }

    public void onAdd(PedidoHasMarmita pedidoHasMarmita) {
        dados.add(pedidoHasMarmita);
        fireTableRowsInserted(indexOf(pedidoHasMarmita), indexOf(pedidoHasMarmita));
    }

    public void onRemove(int[] rowIndex) {
        int x;
        for (x = rowIndex.length - 1; x >= 0; x--) {
            dados.remove(rowIndex[x]);
            fireTableRowsDeleted(rowIndex[x], rowIndex[x]);
        }
    }
}
