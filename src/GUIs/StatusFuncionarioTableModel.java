package GUIs;


import Entidades.StatusFuncionario;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class StatusFuncionarioTableModel extends AbstractTableModel {

    private final Class classes[] = new Class[]{Integer.class, String.class};
    private final String colunas[] = new String[]{"id", "Nome"};
    private List<StatusFuncionario> dados;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    //private final SimpleDateFormat timeformat = new SimpleDateFormat("h:mm a");
  

    public StatusFuncionarioTableModel(List<StatusFuncionario> dados) {
        this.dados = dados;
    }

    public void setDados(List<StatusFuncionario> dados) {
        this.dados = dados;
    }

    public List<StatusFuncionario> getDados() {
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

        StatusFuncionario s = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return s.getIdStatus();
            case 1:
                return s.getNomeStatus();            
            default:
                throw new IndexOutOfBoundsException("Coluna Inválida!");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex==0) {
            return false;
        }
        return true;
    }

  
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

      //  mudou = true;
        StatusFuncionario s = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:              
                    s.setIdStatus((Integer) aValue);                
                break;
            case 1:
                s.setNomeStatus((String) aValue);
                break;          
            default:
                throw new IndexOutOfBoundsException("Coluna Inválida!!!");
        }
        fireTableDataChanged();
    }

    public StatusFuncionario getValue(int rowIndex) {
        return dados.get(rowIndex);
    }

    public int indexOf(StatusFuncionario tc) {
        return dados.indexOf(tc);
    }

    public void onAdd(StatusFuncionario statusFuncionario) {
        dados.add(statusFuncionario);
        fireTableRowsInserted(indexOf(statusFuncionario), indexOf(statusFuncionario));
    }

    public void onRemove(int[] rowIndex) {
        int x;
        for (x = rowIndex.length - 1; x >= 0; x--) {
            dados.remove(rowIndex[x]);
            fireTableRowsDeleted(rowIndex[x], rowIndex[x]);
        }
    }
}
