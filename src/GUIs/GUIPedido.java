package GUIs;

import DAOs.*;
import Entidades.*;
import java.util.List;
import java.awt.Point;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import myUtil.JanelaPesquisar;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.AbstractCellEditor;
import javax.swing.ActionMap;
import javax.swing.DefaultCellEditor;
import javax.swing.InputMap;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SpinnerDateModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import tools.JDateTextField;

/**
 *
 * @author Bianca
 */
public class GUIPedido extends JDialog {

    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));
    JButton btnCreate = new JButton(iconeCreate);
    JButton btnRetrieve = new JButton(iconeRetrieve);
    JButton btnUpdate = new JButton(iconeUpdate);
    JButton btnDelete = new JButton(iconeDelete);
    JButton btnSave = new JButton(iconeSave);
    JButton btnCancel = new JButton(iconeCancel);
    JButton btnList = new JButton(iconeListar);
    private JTextField textFieldIdPedido = new JTextField(20);
    private JLabel labelIdPedido = new JLabel("IdPedido");
    private JTextField textFieldFuncionarioIdFuncionario = new JTextField(20);
    private JLabel labelFuncionarioIdFuncionario = new JLabel("Funcionario");
    private JTextField textFieldClienteIdCliente = new JTextField(20);
    private JLabel labelClienteIdCliente = new JLabel("Cliente");
    private JDateTextField textFieldDataPedido = new JDateTextField();
    private JLabel labelDataPedido = new JLabel("DataPedido");

    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");

    JPanel painelItensPedido = new JPanel();
    JPanel painelPedidoGeral = new JPanel();

    private final JPanel painelAvisosItensPedido = new JPanel();
    private final JButton btnAddItensPedido = new JButton("Adicionar");
    private final JButton btnRemItensPedido = new JButton("Remover");
    private final JButton btnCarregarItensPedido = new JButton("Carregar dados");

    private JTable table = new JTable();
    private PedidoHasMarmitaTableModel tableModel;

    DAOPedidoHasMarmita daoPedidoHasMarmita = new DAOPedidoHasMarmita();

    String acao = "";//variavel para facilitar insert e update
    DAOPedido daoPedido = new DAOPedido();
    DAOFuncionario daoFuncionario = new DAOFuncionario();
    DAOCliente daoCliente = new DAOCliente();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    Pedido pedido;

    List<Marmita> listaMarmitasDisponiveis = new ArrayList<>();

    int idDoPedido = 0;

    private void atvBotoes(boolean c, boolean r, boolean u, boolean d) {
        btnCreate.setEnabled(c);
        btnRetrieve.setEnabled(r);
        btnUpdate.setEnabled(u);
        btnDelete.setEnabled(d);
        btnList.setEnabled(r);
    }

    public void mostrarBotoes(boolean visivel) {
        btnCreate.setVisible(visivel);
        btnRetrieve.setVisible(visivel);
        btnUpdate.setVisible(visivel);
        btnDelete.setVisible(visivel);
        btnList.setVisible(visivel);
        btnSave.setVisible(!visivel);
        btnCancel.setVisible(!visivel);
    }

    private void habilitarAtributos(boolean idPedido, boolean funcionarioIdFuncionario, boolean clienteIdCliente, boolean dataPedido) {
        if (idPedido) {
            textFieldIdPedido.requestFocus();
            textFieldIdPedido.selectAll();
        }
        textFieldIdPedido.setEnabled(idPedido);
        textFieldIdPedido.setEditable(idPedido);
        textFieldFuncionarioIdFuncionario.setEditable(funcionarioIdFuncionario);
        textFieldClienteIdCliente.setEditable(clienteIdCliente);
        textFieldDataPedido.setEditable(dataPedido);
    }

    public void zerarAtributos() {
        textFieldFuncionarioIdFuncionario.setText("");
        textFieldClienteIdCliente.setText("");
        textFieldDataPedido.setText("");
    }
    Color corPadrao = labelIdPedido.getBackground();

    public GUIPedido() {

        setTitle("CRUD - Pedido");
        setSize(600, 500);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setLocationRelativeTo(null);
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false, false, false);
        btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar Toolbar1 = new JToolBar();
        Toolbar1.add(labelIdPedido);
        Toolbar1.add(textFieldIdPedido);
        Toolbar1.add(btnRetrieve);
        Toolbar1.add(btnCreate);
        Toolbar1.add(btnUpdate);
        Toolbar1.add(btnDelete);
        Toolbar1.add(btnSave);
        Toolbar1.add(btnCancel);
        Toolbar1.add(btnList);

        btnSave.setVisible(false);
        btnCancel.setVisible(false);  //atributos
        JPanel painelPedido = new JPanel();
        painelPedido.setLayout(new GridLayout(3, 2));
        painelPedido.add(labelFuncionarioIdFuncionario);
        painelPedido.add(textFieldFuncionarioIdFuncionario);
        painelPedido.add(labelClienteIdCliente);
        painelPedido.add(textFieldClienteIdCliente);
        painelPedido.add(labelDataPedido);
        painelPedido.add(textFieldDataPedido);
        pnAvisos.add(labelAviso);
        pnAvisos.setBackground(Color.yellow);
//        cp.add(Toolbar1, BorderLayout.NORTH);
//        cp.add(painelPedido, BorderLayout.CENTER);
//        cp.add(pnAvisos, BorderLayout.SOUTH);

        painelPedidoGeral.setLayout(new BorderLayout());
        painelPedidoGeral.add(Toolbar1, BorderLayout.NORTH);
        painelPedidoGeral.add(painelPedido, BorderLayout.CENTER);
        painelPedidoGeral.add(pnAvisos, BorderLayout.SOUTH);

        painelItensPedido.setLayout(new BorderLayout());
        painelItensPedido.add(BorderLayout.NORTH, painelAvisosItensPedido);

        cp.add(painelPedidoGeral, BorderLayout.NORTH);
        cp.add(painelItensPedido, BorderLayout.CENTER);
        textFieldIdPedido.requestFocus();
        textFieldIdPedido.selectAll();
        textFieldIdPedido.setBackground(Color.GREEN);
        labelAviso.setText("Digite um IdPedido e clic [Pesquisar]");

        List<PedidoHasMarmita> listaPedidoHasMarmita = new ArrayList<>();
        tableModel = new PedidoHasMarmitaTableModel(listaPedidoHasMarmita);
        table.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        painelItensPedido.add(scrollPane);

        painelAvisosItensPedido.add(new JLabel("Tecla INS = Insere novo registro"));
        painelAvisosItensPedido.add(new JLabel("   --   "));
        painelAvisosItensPedido.add(new JLabel("Tecla DEL = Exclui registro selecionado"));
        painelAvisosItensPedido.add(btnAddItensPedido);
        painelAvisosItensPedido.setBackground(Color.cyan);

        table.setDefaultEditor(Date.class, new DateEditor());
        table.setDefaultRenderer(Date.class, new DateRenderer());

        table.setEnabled(false);

        btnAddItensPedido.setEnabled(false);

//-------------- FK Cliente ------------------------
//        TableColumn tipoColumn0 = table.getColumnModel().getColumn(1);
//        JComboBox comboBox0 = new JComboBox();
//        DAOMarmita daoMarmita = new DAOMarmita();
//        List<PedidoHasMarmita> listaPedidosId = new ArrayList<>();
//        List<Marmita> listaMarmitasUsadas = new ArrayList<>();
//        listaPedidosId = daoPedidoHasMarmita.listById(Integer.valueOf(textFieldIdPedido.getText()));
//        for (int i = 0; i < listaPedidosId.size(); i++) {
//            Marmita marmitaAdd = daoMarmita.obter(listaPedidosId.get(i).getPedidoHasMarmitaPK().getMarmitaIdMarmita());
//            listaMarmitasUsadas.add(marmitaAdd);
//        }
//        List<Marmita> ltc0 = listarMarmitasDisponíveis(listaMarmitasUsadas, Integer.valueOf(textFieldIdPedido.getText()));
//        for (int i = 0; i < ltc0.size(); i++) {
//            comboBox0.addItem(ltc0.get(i).getIdMarmita());;
//        }
//        tipoColumn0.setCellEditor(new DefaultCellEditor(comboBox0));
////        
//--------------inserir---------------
        InputMap im = table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap actionMap = table.getActionMap();

        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0);
        im.put(enterKey, "Action.insert");

        actionMap.put("Action.insert", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnAddItensPedido.doClick();
            }
        });

//---------------------------------- button delete -----------------------------
        KeyStroke delKey = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0);
        im.put(delKey, "Action.delete");

        actionMap.put("Action.delete", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (table.getSelectedRow() >= 0) {
                    PedidoHasMarmitaPK pedidoHasMarmitaPK = new PedidoHasMarmitaPK();
                    pedidoHasMarmitaPK.setPedidoIdPedido(tableModel.getValue(table.getSelectedRow()).getPedidoHasMarmitaPK().getPedidoIdPedido());
                    pedidoHasMarmitaPK.setMarmitaIdMarmita(tableModel.getValue(table.getSelectedRow()).getPedidoHasMarmitaPK().getMarmitaIdMarmita());
                    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(cp,
                            "Confirma a exclusão do item [" + pedidoHasMarmitaPK.getPedidoIdPedido() + "- " + pedidoHasMarmitaPK.getMarmitaIdMarmita() + "]?", "Confirm",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                        btnRemItensPedido.doClick();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Escolha na tabela a Status a ser excluída");
                }
            }
        });

//========================================== botão add ============================================
        btnAddItensPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PedidoHasMarmita pedidoHasMarmita = new PedidoHasMarmita();
                PedidoHasMarmitaPK pedidoHasMarmitaPK = new PedidoHasMarmitaPK();
                pedidoHasMarmitaPK.setPedidoIdPedido(Integer.valueOf(textFieldIdPedido.getText()));
                List<Marmita> listaMarmita = new DAOMarmita().list();
                Marmita marmita;
                List<Marmita> listaMarmitasUsadas = new ArrayList<>();
                List<PedidoHasMarmita> listaPedidosId = new ArrayList<>();
                DAOMarmita daoMarmita = new DAOMarmita();

                if (listaMarmita.size() > 0) { //se tivver alguma marmita cadastrada
                    listaPedidosId = daoPedidoHasMarmita.listById(Integer.valueOf(textFieldIdPedido.getText()));
                    for (int i = 0; i < listaPedidosId.size(); i++) {
                        Marmita marmitaAdd = daoMarmita.obter(listaPedidosId.get(i).getPedidoHasMarmitaPK().getMarmitaIdMarmita());
                        listaMarmitasUsadas.add(marmitaAdd);
                    }
                    listaMarmitasDisponiveis = listarMarmitasDisponíveis(listaMarmitasUsadas, Integer.valueOf(textFieldIdPedido.getText()));
                    if (listaMarmitasDisponiveis.size() > 0) {
                        List<String> listaAuxiliar = new ArrayList<>();
                        Point lc = btnCancel.getLocationOnScreen();
                        lc.x = lc.x + btnCancel.getWidth();
                        for (int i = 0; i < listaMarmitasDisponiveis.size(); i++) {
                            listaAuxiliar.add(listaMarmitasDisponiveis.get(i).getIdMarmita() + "-" + listaMarmitasDisponiveis.get(i).getPratoPrincipalIdPratoPrincipal().getNomePratoPrincipal() + " " + listaMarmitasDisponiveis.get(i).getTamanhoMarmitaIdTamanhoMarmita().getNomeTamanhoMarmita());
                        }
                        String selectedItem = new JanelaPesquisar(listaAuxiliar, lc.x, lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            //textFieldClienteIdCliente.setText(aux[0] + "-" + aux[1]);
                            marmita = daoMarmita.obter(Integer.valueOf(aux[0]));
                            pedidoHasMarmitaPK.setMarmitaIdMarmita(marmita.getIdMarmita());
                            pedidoHasMarmita.setPedidoHasMarmitaPK(pedidoHasMarmitaPK);
                            pedidoHasMarmita.setQtde(0);
                            pedidoHasMarmita.setValor(0.0);
                            pedidoHasMarmita.setDesconto(0.0);
                            daoPedidoHasMarmita.inserir(pedidoHasMarmita);
                            tableModel.onAdd(pedidoHasMarmita);
                            tableModel.fireTableDataChanged();

                        } else {
//                            textFieldClienteIdCliente.requestFocus();
//                            textFieldClienteIdCliente.selectAll();
                            JOptionPane.showMessageDialog(cp, "Escolha uma marmita.");
                        }

//                        marmita = listaMarmitasDisponiveis.get(0);
//                        pedidoHasMarmitaPK.setMarmitaIdMarmita(marmita.getIdMarmita());
//                        pedidoHasMarmita.setPedidoHasMarmitaPK(pedidoHasMarmitaPK);
//                        pedidoHasMarmita.setQtde(0);
//                        pedidoHasMarmita.setValor(0.0);
//                        pedidoHasMarmita.setDesconto(0.0);
//                        daoPedidoHasMarmita.inserir(pedidoHasMarmita);
//                        tableModel.onAdd(pedidoHasMarmita);
//                        tableModel.fireTableDataChanged();
                    } else {
                        JOptionPane.showMessageDialog(cp, "Não há mais Marmitas para usar, altere as já existentes ou cadastre novas.");
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há Marmitas cadastradas, pedido depende da marmita. Cadastre.");
                }
                listaPedidosId = daoPedidoHasMarmita.listById(Integer.valueOf(textFieldIdPedido.getText()));
                listaMarmitasDisponiveis.clear();
                listaMarmitasUsadas.clear();
            }
        }
        );

//============================================ botao remover =======================================================
        btnRemItensPedido.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (table.getSelectedRow() != -1 && table.getSelectedRow() < tableModel.getRowCount()) {
                    PedidoHasMarmitaPK pedidoHasMarmitaPK = new PedidoHasMarmitaPK(1, 4);
                    pedidoHasMarmitaPK.setPedidoIdPedido(tableModel.getValue(table.getSelectedRow()).getPedidoHasMarmitaPK().getPedidoIdPedido());
                    pedidoHasMarmitaPK.setMarmitaIdMarmita(tableModel.getValue(table.getSelectedRow()).getPedidoHasMarmitaPK().getMarmitaIdMarmita());
                    PedidoHasMarmita pedido = daoPedidoHasMarmita.obter(pedidoHasMarmitaPK);

                    daoPedidoHasMarmita.remover(pedido);

                    tableModel.onRemove(table.getSelectedRows());
                    List<Marmita> listaMarmitasUsadas = new ArrayList<>();
                    List<PedidoHasMarmita> listaPedidosId = new ArrayList<>();
                    listaPedidosId = daoPedidoHasMarmita.listById(Integer.valueOf(textFieldIdPedido.getText()));
                    for (int i = 0; i < listaPedidosId.size(); i++) {
                        listaMarmitasUsadas.add(listaPedidosId.get(i).getMarmita());
                    }
                    listaMarmitasDisponiveis = listarMarmitasDisponíveis(listaMarmitasUsadas, Integer.valueOf(textFieldIdPedido.getText()));

                } else {
                    JOptionPane.showMessageDialog(cp, "Escolha na tabela a conta a ser excluída");
                    table.requestFocus();
                }
                tableModel.fireTableDataChanged();
            }
        }
        );

//============================================ botao carregar =======================================================
        btnCarregarItensPedido.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e
            ) {
                DAOPedidoHasMarmita daoPedidoHasMarmita1 = new DAOPedidoHasMarmita();
                try {
                    List<PedidoHasMarmita> lc = daoPedidoHasMarmita1.listById(idDoPedido);
                    List<PedidoHasMarmita> lista = new ArrayList<>();
                    for (PedidoHasMarmita x : lc) {
                        PedidoHasMarmita p = new PedidoHasMarmita();
                        p.setPedidoHasMarmitaPK(x.getPedidoHasMarmitaPK());
                        p.setQtde(x.getQtde());
                        p.setValor(x.getValor());
                        p.setDesconto(x.getDesconto());
                        lista.add(p);
                    }
//                    for (PedidoHasMarmita x : lista) {
//                        System.out.println(x.getPedidoHasMarmitaPK().getPedidoIdPedido() + " - " + x.getPedidoHasMarmitaPK().getMarmitaIdMarmita() + " - " + x.getQtde() + "-" + x.getValor() + " - " + x.getDesconto());
//                    }
                    tableModel.setDados(lista);
                    tableModel.fireTableDataChanged();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(cp, "Erro ao carregar os dados..." + ex.getMessage());
                }
            }

        }
        );

//============================================ listener table =======================================================
        tableModel.addTableModelListener(
                new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // if (tableModel.mudou) {
                if (table.getSelectedRow() != -1 && table.getSelectedRow() < tableModel.getRowCount()) {
                    PedidoHasMarmitaPK pedidoHasMarmitaPK = new PedidoHasMarmitaPK();
                    pedidoHasMarmitaPK.setPedidoIdPedido(Integer.valueOf(textFieldIdPedido.getText()));
                    pedidoHasMarmitaPK.setMarmitaIdMarmita(tableModel.getValue(table.getSelectedRow()).getPedidoHasMarmitaPK().getMarmitaIdMarmita());
                    PedidoHasMarmita c = daoPedidoHasMarmita.obter(pedidoHasMarmitaPK);

                    if (c == null) {
//                        btnAddItensPedido.doClick();
                    } else {
                        c.setQtde(tableModel.getValue(table.getSelectedRow()).getQtde());
                        c.setValor(tableModel.getValue(table.getSelectedRow()).getValor());
                        c.setDesconto(tableModel.getValue(table.getSelectedRow()).getDesconto());
                        daoPedidoHasMarmita.atualizar(c);
//                        System.out.println("PEDIDO ID PEDIDO: " + c);
//                        System.out.println("PEDIDO ID PEDIDO: " + c.getQtde() + "- "+ c.getValor());
                    }

                    // PedidoHasMarmita c = tableModel.getValue(table.getSelectedRow());
                }
                //}
            }
        }
        );

//------------------Janela Pesquisar ------------------------
        textFieldFuncionarioIdFuncionario.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                if (btnCancel.isVisible()) {
                    List<String> listaAuxiliar = daoFuncionario.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnCancel.getLocationOnScreen();
                        //Point lc = new Point(7,8);
                        lc.x = lc.x + btnCancel.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldFuncionarioIdFuncionario.setText(aux[0] + "-" + aux[1]);
                        } else {
                            textFieldFuncionarioIdFuncionario.requestFocus();
                            textFieldFuncionarioIdFuncionario.selectAll();
                        }
                    }
                }
            }
        }
        );

        textFieldClienteIdCliente.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (btnCancel.isVisible()) {
                    List<String> listaAuxiliar = daoCliente.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnCancel.getLocationOnScreen();
                        //Point lc = new Point(7,8);
                        lc.x = lc.x + btnCancel.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldClienteIdCliente.setText(aux[0] + "-" + aux[1]);
                        } else {
                            textFieldClienteIdCliente.requestFocus();
                            textFieldClienteIdCliente.selectAll();
                        }
                    }
                }
            }
        }
        );

        JTableHeader header = table.getTableHeader();

        DAOMarmita daoMarmitaJP = new DAOMarmita();
        DAOPrecoProduto daoPrecoProduto = new DAOPrecoProduto();
        header.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                TableColumnModel tipoColumn = table.getColumnModel();
                int vColIndex = tipoColumn.getColumnIndexAtX(me.getX());
                if (vColIndex == 1) {
                    GUIMarmitaListagem guiMarmitaListagem = new GUIMarmitaListagem(daoMarmitaJP.listInOrderId());
                } else if (vColIndex == 3) {
                    GUIPrecoProdutoPKListagem guiPrecoProdutoPKListagem = new GUIPrecoProdutoPKListagem(daoPrecoProduto.listInOrderId());
                }

            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });

//--------------- listeners ----------------- 
        textFieldIdPedido.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                btnRetrieve.doClick();
            }
        }
        );

//-----------------------------  btnRetrieve ------------------------------------------
        btnRetrieve.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                pedido = new Pedido();
                textFieldIdPedido.setText(textFieldIdPedido.getText().trim());//caso tenham sido digitados espaços

                if (textFieldIdPedido.getText().equals("")) {
                    List<String> listaAuxiliar = daoPedido.listInOrderNomeStrings("nome");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldIdPedido.setText(aux[0]);
                            btnRetrieve.doClick();
                        } else {
                            textFieldIdPedido.requestFocus();
                            textFieldIdPedido.selectAll();
                        }
                    }

                    textFieldIdPedido.requestFocus();
                    textFieldIdPedido.selectAll();
                } else {
                    try {
                        pedido.setIdPedido(Integer.valueOf(textFieldIdPedido.getText()));
                        pedido = daoPedido.obter(pedido.getIdPedido());
                        if (pedido != null) { //se encontrou na lista
                            idDoPedido = pedido.getIdPedido();
                            btnCarregarItensPedido.doClick();
                            textFieldFuncionarioIdFuncionario.setText(String.valueOf(pedido.getFuncionarioIdFuncionario().getIdFuncionario() + "-" + pedido.getFuncionarioIdFuncionario().getNomeFuncionario()));
                            textFieldClienteIdCliente.setText(String.valueOf(pedido.getClienteIdCliente().getIdCliente() + "-" + pedido.getClienteIdCliente().getNomeCliente()));
                            textFieldDataPedido.setText(sdf.format(pedido.getDataPedido()));
                            atvBotoes(false, true, true, true);
                            habilitarAtributos(true,
                                    false, false, false
                            );
                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                            acao = "encontrou";
                        } else {
                            idDoPedido = 0;
                            btnCarregarItensPedido.doClick();
                            atvBotoes(true, true, false, false);
                            zerarAtributos();
                            labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                        }
                        textFieldIdPedido.setBackground(Color.green);
                    } catch (Exception x) {
                        textFieldIdPedido.setOpaque(true);
                        textFieldIdPedido.selectAll();
                        textFieldIdPedido.requestFocus();
                        textFieldIdPedido.setBackground(Color.red);
                        labelAviso.setText("Tipo errado - " + x.getMessage());
                    }
                }
            }
        }
        );

//============================= btnCreate ===================================
        btnCreate.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                zerarAtributos();
                habilitarAtributos(false,true, true, true);
                textFieldFuncionarioIdFuncionario.requestFocus();
                mostrarBotoes(false);
             //   btnAddItensPedido.setEnabled(true);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        }
        );
        btnSave.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                boolean deuRuim = false;
                table.setEnabled(false);
                btnAddItensPedido.setEnabled(false);
                if (acao.equals("insert")) {
                    pedido = new Pedido();
                }
                try {
                    pedido.setIdPedido(Integer.valueOf((textFieldIdPedido.getText())));
                } catch (Exception erro0) {
                    deuRuim = true;
                    textFieldIdPedido.setBackground(Color.red);
                }
                try {
                    pedido.setFuncionarioIdFuncionario(daoFuncionario.obter(Integer.valueOf((textFieldFuncionarioIdFuncionario.getText().split("-")[0]))));
                } catch (Exception erro1) {
                    deuRuim = true;
                    textFieldFuncionarioIdFuncionario.setBackground(Color.red);
                }
                try {
                    pedido.setClienteIdCliente(daoCliente.obter(Integer.valueOf((textFieldClienteIdCliente.getText().split("-")[0]))));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldClienteIdCliente.setBackground(Color.red);
                }
                try {
                    sdf.setLenient(false);
                    pedido.setDataPedido(sdf.parse((textFieldDataPedido.getText())));
                } catch (Exception erro3) {
                    deuRuim = true;
                    textFieldDataPedido.setBackground(Color.red);
                }
                if (!deuRuim) {
                    if (acao.equals("insert")) {
                        daoPedido.inserir(pedido);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoPedido.atualizar(pedido);
                        labelAviso.setText("Registro alterado.");
                    }
                    habilitarAtributos(true,
                            false, false, false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                }//!deu ruim
                else {
                    labelAviso.setText("Erro nos dados - corrija");
                    labelAviso.setBackground(Color.red);
                }

            }
        }
        );
        btnCancel.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                zerarAtributos();
                atvBotoes(false, true, false, false);
                habilitarAtributos(true,
                        false, false, false
                );
                mostrarBotoes(true);
            }
        }
        );
        btnList.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {

                acao = "list";
                GUIPedidoListagem guiPedidoListagem = new GUIPedidoListagem(daoPedido.listInOrderId());
            }
        }
        );
        btnUpdate.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                acao = "update";
                mostrarBotoes(false);
                table.setEnabled(true);
                btnAddItensPedido.setEnabled(true);
                habilitarAtributos(false, true, true, true);
            }
        }
        );
        btnDelete.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + pedido.getFuncionarioIdFuncionario() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoPedido.remover(pedido);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    textFieldFuncionarioIdFuncionario.requestFocus();
                    textFieldFuncionarioIdFuncionario.selectAll();
                }
            }
        }
        );
        textFieldFuncionarioIdFuncionario.addFocusListener(
                new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe
            ) {
                textFieldFuncionarioIdFuncionario.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe
            ) { //ao perder o foco, fica branco
                textFieldFuncionarioIdFuncionario.setBackground(corPadrao);
            }
        }
        );
        textFieldClienteIdCliente.addFocusListener(
                new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe
            ) {
                textFieldClienteIdCliente.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe
            ) { //ao perder o foco, fica branco
                textFieldClienteIdCliente.setBackground(corPadrao);
            }
        }
        );
        textFieldDataPedido.addFocusListener(
                new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe
            ) {
                textFieldDataPedido.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe
            ) { //ao perder o foco, fica branco
                textFieldDataPedido.setBackground(corPadrao);
            }
        }
        );
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Sai   
                dispose();
            }
        });
        setModal(true);
        setVisible(true);//faz a janela ficar visível  
    }

    public static void main(String[] args) {
        new GUIPedido();
    }

    private static List carregarDadosTabela(int idPedido) {
        DAOPedidoHasMarmita daoPedidoHasMarmita1 = new DAOPedidoHasMarmita();
        List<PedidoHasMarmita> lc = daoPedidoHasMarmita1.listById(WIDTH);
        System.out.println("----------");
        System.out.println("PEDIDO HAS MARMITA LIST:" + lc);
        System.out.println("---------------");
        List<PedidoHasMarmita> lista = new ArrayList<>();
        for (int i = 0; i < lc.size(); i++) {
            if (lc.get(i).getPedidoHasMarmitaPK().getPedidoIdPedido() == idPedido) {
                lista.add(lc.get(i));
            }
        }
        return lista;
    }

    private static List listarMarmitasDisponíveis(List<Marmita> listaMarmitasUsadas, int id) {
        DAOMarmita daoMarmita = new DAOMarmita();
        List<Marmita> listaMarmita = daoMarmita.list();
        List<PedidoHasMarmita> listaPedidosId = new ArrayList<>();
        for (int i = 0; i < listaMarmita.size(); i++) {

            for (int j = 0; j < listaMarmitasUsadas.size(); j++) {
                if (listaMarmita.get(i).equals(listaMarmitasUsadas.get(j))) {
                    listaMarmita.remove(listaMarmitasUsadas.get(j));
                }
            }
        }
        return listaMarmita;
    }

    //============================================ date render =======================================================
    class DateRenderer extends DefaultTableCellRenderer {

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

//============================================ date editor =======================================================
    class DateEditor extends AbstractCellEditor implements TableCellEditor {

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
    }
}
