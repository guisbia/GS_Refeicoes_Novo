package GUIs;

//import DAOs.DAOPrecoProdutoPK;
import tools.CentroDoMonitorMaior;
import DAOs.DAOStatusFuncionario;
import Entidades.StatusFuncionario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;

public class GUIStatusFucionario extends JDialog {

    private final Container cp;
    private final JPanel painelAvisos = new JPanel();
    
    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeCarregar = new ImageIcon(getClass().getResource("/icones/list.png"));
    
    
    private final JButton btnAdd = new JButton(iconeCreate);
    private final JButton btnRem = new JButton(iconeDelete);
    private final JButton btnCarregar = new JButton(iconeCarregar);

    private JTable table = new JTable();
    private StatusFuncionarioTableModel tableModel;

    private DAOStatusFuncionario daoStatusFuncionario = new DAOStatusFuncionario();

    public GUIStatusFucionario() {

        setTitle("Status Funcionário");
        setLayout(new FlowLayout());
        setSize(300, 200);

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(BorderLayout.NORTH, painelAvisos);

        List<StatusFuncionario> lista = new ArrayList<>();
        tableModel = new StatusFuncionarioTableModel(lista);
        table.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        cp.add(scrollPane);

        painelAvisos.add(btnAdd);
        painelAvisos.add(btnCarregar);
        painelAvisos.add(btnRem);

        table.setDefaultEditor(Date.class, new DateEditor());
        table.setDefaultRenderer(Date.class, new DateRenderer());

        // É necessário clicar antes na tabela para o código funcionar
        InputMap im = table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap actionMap = table.getActionMap();


        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                dispose();
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StatusFuncionario statusFuncionario = new StatusFuncionario();

                statusFuncionario.setIdStatus(daoStatusFuncionario.autoIdStatusFuncionario());
                statusFuncionario.setNomeStatus("nova");

                try {
                    StatusFuncionario c = daoStatusFuncionario.obter(statusFuncionario.getIdStatus());
                    if (c == null) {
                        daoStatusFuncionario.inserir(statusFuncionario);
                        tableModel.onAdd(statusFuncionario);
                        tableModel.fireTableDataChanged();
                    } else {
                        JOptionPane.showMessageDialog(cp, "Já existe esse tipo conta");
                    }
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(cp, "Erro ao inserir a conta =>" + statusFuncionario.getIdStatus()
                            + " com o erro=>" + err.getMessage());
                }

            }
        });

        btnRem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1 && table.getSelectedRow() < tableModel.getRowCount()) {
                    StatusFuncionario c = tableModel.getValue(table.getSelectedRow());
                    daoStatusFuncionario.remover(c);
                    tableModel.onRemove(table.getSelectedRows());

                } else {
                    JOptionPane.showMessageDialog(cp, "Escolha na tabela o status a ser excluído");
                    table.requestFocus();
                }
                tableModel.fireTableDataChanged();
            }
        });

        btnCarregar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DAOStatusFuncionario daoStatus = new DAOStatusFuncionario();
                try {
                    List<StatusFuncionario> lc = daoStatusFuncionario.list();

                    tableModel.setDados(lc);
                    tableModel.fireTableDataChanged();

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(cp, "Erro ao carregar os dados..." + ex.getMessage());
                }
            }

        });

        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // if (tableModel.mudou) {
                if (table.getSelectedRow() != -1 && table.getSelectedRow() < tableModel.getRowCount()) {
                    StatusFuncionario c = tableModel.getValue(table.getSelectedRow());
                    daoStatusFuncionario.atualizar(c);
                }
                //}
            }
        }
        );

        CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();

        setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));
        btnCarregar.doClick();//carrega os dados 

        setModal(true);
        setVisible(true);

    } //fim do construtor da GUI

    private static class DateRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (!(value instanceof Date)) {
                return this;
            }
            DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
            setText(DATE_FORMAT.format((Date) value));
            return this;
        }
    }

    private static class DateEditor extends AbstractCellEditor implements TableCellEditor {

        private static final long serialVersionUID = 1L;
        private final JSpinner theSpinner;
        private Object value;

        DateEditor() {
            theSpinner = new JSpinner(new SpinnerDateModel());
            theSpinner.setOpaque(true);
            theSpinner.setEditor(new JSpinner.DateEditor(theSpinner, "dd/MM/yyyy"));
        }

        @Override
        public Object getCellEditorValue() {
            return theSpinner.getValue();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            theSpinner.setValue(value);
            if (isSelected) {
                theSpinner.setBackground(table.getSelectionBackground());
            } else {
                theSpinner.setBackground(table.getBackground());
            }
            return theSpinner;
        }

        public static void main(String[] args) {
            new GUIStatusFucionario();
        }
    }

}
