package GUIs;

import DAOs.*;
import Entidades.*;
import java.util.List;
import java.awt.Point;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import tools.JDateTextField;

/**
 *
 * @author Bianca
 */
public class GUIPedido_Modo2 extends JDialog {

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
    private JLabel labelFuncionarioIdFuncionario = new JLabel("FuncionarioIdFuncionario");
    private JTextField textFieldClienteIdCliente = new JTextField(20);
    private JLabel labelClienteIdCliente = new JLabel("ClienteIdCliente");
    private JDateTextField textFieldDataPedido = new JDateTextField();
    private JLabel labelDataPedido = new JLabel("DataPedido");
    JButton btItensPedido = new JButton("Itens do Pedido");
    
    
    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");

    String acao = "";//variavel para facilitar insert e update
    DAOPedido daoPedido = new DAOPedido();
    DAOFuncionario daoFuncionario = new DAOFuncionario();
    DAOCliente daoCliente = new DAOCliente();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    Pedido pedido;
    
    
    
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

    private void habilitarAtributos(
            boolean idPedido, boolean funcionarioIdFuncionario, boolean clienteIdCliente, boolean dataPedido) {
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

    public GUIPedido_Modo2() {

        setTitle("CRUD - Pedido");
        setSize(500, 500);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setLocationRelativeTo(null);
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes
        btItensPedido.setEnabled(false);
        atvBotoes(false, true, false, false);
        habilitarAtributos(true,
                false, false, false);
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
        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(3, 2));
        centro.add(labelFuncionarioIdFuncionario);
        centro.add(textFieldFuncionarioIdFuncionario);
        centro.add(labelClienteIdCliente);
        centro.add(textFieldClienteIdCliente);
        centro.add(labelDataPedido);
        centro.add(textFieldDataPedido);
        JPanel painelBtItensPedido = new JPanel();
        painelBtItensPedido.setLayout(new FlowLayout(FlowLayout.CENTER));
        painelBtItensPedido.add(btItensPedido);
        pnAvisos.add(labelAviso);
        pnAvisos.setBackground(Color.yellow);
        JPanel painelSul = new JPanel();
        painelSul.setLayout(new GridLayout(2,1));
        painelSul.add(painelBtItensPedido);
        painelSul.add(pnAvisos);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(painelSul, BorderLayout.SOUTH);
        textFieldIdPedido.requestFocus();
        textFieldIdPedido.selectAll();
        textFieldIdPedido.setBackground(Color.GREEN);
        labelAviso.setText("Digite um IdPedido e clic [Pesquisar]");

//------------------Janela Pesquisar ------------------------
        textFieldFuncionarioIdFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
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
        });

        textFieldClienteIdCliente.addActionListener(new ActionListener() {
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
        });

//--------------- listeners ----------------- 
        textFieldIdPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.doClick();
            }
        });

//-----------------------------  btnRetrieve ------------------------------------------
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
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
                            textFieldFuncionarioIdFuncionario.setText(String.valueOf(pedido.getFuncionarioIdFuncionario().getIdFuncionario() + "-" + pedido.getFuncionarioIdFuncionario().getNomeFuncionario()));
                            textFieldClienteIdCliente.setText(String.valueOf(pedido.getClienteIdCliente().getIdCliente() + "-" + pedido.getClienteIdCliente().getNomeCliente()));
                            textFieldDataPedido.setText(sdf.format(pedido.getDataPedido()));
                            atvBotoes(false, true, true, true);
                            habilitarAtributos(true, false, false, false);
                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                            acao = "encontrouS";
                        } else {
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
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                habilitarAtributos(false,
                        true, true, true
                );
                textFieldFuncionarioIdFuncionario.requestFocus();
                mostrarBotoes(false);

                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean deuRuim = false;
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
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                atvBotoes(false, true, false, false);
                habilitarAtributos(true,
                        false, false, false
                );
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                GUIPedidoListagem guiPedidoListagem = new GUIPedidoListagem(daoPedido.listInOrderNome());
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);

                habilitarAtributos(false,
                        true, true, true
                );
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
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
        });
        textFieldFuncionarioIdFuncionario.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldFuncionarioIdFuncionario.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldFuncionarioIdFuncionario.setBackground(corPadrao);
            }
        });
        textFieldClienteIdCliente.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldClienteIdCliente.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldClienteIdCliente.setBackground(corPadrao);
            }
        });
        textFieldDataPedido.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldDataPedido.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldDataPedido.setBackground(corPadrao);
            }
        });
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
        new GUIPedido_Modo2();
    }
}
