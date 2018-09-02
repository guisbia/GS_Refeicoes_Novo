package GUIs;

import DAOs.*;
import Entidades.*;
import java.awt.Dimension;
import java.util.List;
import java.awt.Point;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import myUtil.JanelaPesquisar;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import myUtil.CopiaImagem;

/**
 *
 * @author Bianca
 */
public class GUICliente extends JDialog {

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
    private JTextField textFieldIdCliente = new JTextField(20);
    private JLabel labelIdCliente = new JLabel("Id");
    private JTextField textFieldNomeCliente = new JTextField(20);
    private JLabel labelNomeCliente = new JLabel("Nome");
    private JTextField textFieldTelefoneCliente = new JTextField(20);
    private JLabel labelTelefoneCliente = new JLabel("Telefone");
    private JTextField textFieldEnderecoCliente = new JTextField(20);
    private JLabel labelEnderecoCliente = new JLabel("Endereço");

    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");

    String acao = "";//variavel para facilitar insert e update
    DAOCliente daoCliente = new DAOCliente();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    Cliente cliente;

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
            boolean idCliente, boolean nomeCliente, boolean telefoneCliente, boolean enderecoCliente) {
        if (idCliente) {
            textFieldIdCliente.requestFocus();
            textFieldIdCliente.selectAll();
        }
        textFieldIdCliente.setEnabled(idCliente);
        textFieldIdCliente.setEditable(idCliente);
        textFieldNomeCliente.setEditable(nomeCliente);
        textFieldTelefoneCliente.setEditable(telefoneCliente);
        textFieldEnderecoCliente.setEditable(enderecoCliente);
    }

    public void zerarAtributos() {
        textFieldNomeCliente.setText("");
        textFieldTelefoneCliente.setText("");
        textFieldEnderecoCliente.setText("");
    }
    Color corPadrao = labelIdCliente.getBackground();

    public GUICliente() {

        setTitle("CRUD - Cliente");
        setSize(500, 300);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setLocationRelativeTo(null);
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true,false, false, false);
        btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar Toolbar1 = new JToolBar();
        Toolbar1.add(labelIdCliente);
        Toolbar1.add(textFieldIdCliente);
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
        centro.add(labelNomeCliente);
        centro.add(textFieldNomeCliente);
        centro.add(labelTelefoneCliente);
        centro.add(textFieldTelefoneCliente);
        centro.add(labelEnderecoCliente);
        centro.add(textFieldEnderecoCliente);
        pnAvisos.add(labelAviso);
        pnAvisos.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(pnAvisos, BorderLayout.SOUTH);
        textFieldIdCliente.requestFocus();
        textFieldIdCliente.selectAll();
        textFieldIdCliente.setBackground(Color.GREEN);
        labelAviso.setText("Digite um Idcliente e clic [Pesquisar]");
//--------------- listeners ----------------- 
        textFieldIdCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.doClick();
            }
        });

//-----------------------------  btnRetrieve ------------------------------------------
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cliente = new Cliente();
                textFieldIdCliente.setText(textFieldIdCliente.getText().trim());//caso tenham sido digitados espaços

                if (textFieldIdCliente.getText().equals("")) {
                    List<String> listaAuxiliar = daoCliente.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldIdCliente.setText(aux[0]);
                            btnRetrieve.doClick();
                        } else {
                            textFieldIdCliente.requestFocus();
                            textFieldIdCliente.selectAll();
                        }
                    }

                    textFieldIdCliente.requestFocus();
                    textFieldIdCliente.selectAll();
                } else {
                    try {
                        cliente.setIdCliente(Integer.valueOf(textFieldIdCliente.getText()));
                        cliente = daoCliente.obter(cliente.getIdCliente());
                        if (cliente != null) { //se encontrou na lista
                            textFieldNomeCliente.setText((cliente.getNomeCliente()));
                            textFieldTelefoneCliente.setText((cliente.getTelefoneCliente()));
                            textFieldEnderecoCliente.setText((cliente.getEnderecoCliente()));
                            atvBotoes(false, true, true, true);
                            habilitarAtributos(true,
                                    false, false, false
                            );
                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                            acao = "encontrou";
                        } else {
                            atvBotoes(true, true, false, false);
                            zerarAtributos();
                            labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                        }
                        textFieldIdCliente.setBackground(Color.green);
                    } catch (Exception x) {
                        textFieldIdCliente.setOpaque(true);
                        textFieldIdCliente.selectAll();
                        textFieldIdCliente.requestFocus();
                        textFieldIdCliente.setBackground(Color.red);
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
                textFieldNomeCliente.requestFocus();
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
                    cliente = new Cliente();
                }
                try {
                    cliente.setIdCliente(Integer.valueOf((textFieldIdCliente.getText())));
                } catch (Exception erro0) {
                    deuRuim = true;
                    textFieldIdCliente.setBackground(Color.red);
                }
                cliente.setNomeCliente(String.valueOf(textFieldNomeCliente.getText()));
                cliente.setTelefoneCliente(String.valueOf(textFieldTelefoneCliente.getText()));
                cliente.setEnderecoCliente(String.valueOf(textFieldEnderecoCliente.getText()));
                if (!deuRuim) {
                    if (acao.equals("insert")) {
                        daoCliente.inserir(cliente);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoCliente.atualizar(cliente);
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
                GUIClienteListagem guiClienteListagem = new GUIClienteListagem(daoCliente.listInOrderId());
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
                        "Confirma a exclusão do registro <ID = " + cliente.getNomeCliente() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoCliente.remover(cliente);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    textFieldNomeCliente.requestFocus();
                    textFieldNomeCliente.selectAll();
                }
            }
        });
        textFieldNomeCliente.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldNomeCliente.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldNomeCliente.setBackground(corPadrao);
            }
        });
        textFieldTelefoneCliente.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldTelefoneCliente.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldTelefoneCliente.setBackground(corPadrao);
            }
        });
        textFieldEnderecoCliente.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldEnderecoCliente.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldEnderecoCliente.setBackground(corPadrao);
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
        new GUICliente();
    }
}
