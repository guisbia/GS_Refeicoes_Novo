package GUIs;

import DAOs.*;
import DAOs.DAOFuncionario;
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
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import myUtil.CopiaImagem;
import tools.JDateTextField;

/**
 *
 * @author Bianca
 */
public class GUIFuncionario extends JDialog {

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
    private JTextField textFieldIdFuncionario = new JTextField(20);
    private JLabel labelIdFuncionario = new JLabel("Id");
    private JTextField textFieldNomeFuncionario = new JTextField(20);
    private JLabel labelNomeFuncionario = new JLabel("Nome");
    private JTextField textFieldSalarioFuncionario = new JTextField(20);
    private JLabel labelSalarioFuncionario = new JLabel("Salario");
    private JTextField textFieldTelefoneFuncionario = new JTextField(20);
    private JLabel labelTelefoneFuncionario = new JLabel("Telefone");
    private JTextField textFieldDataInicioFuncionario;
    private JLabel labelDataInicioFuncionario = new JLabel("Data de inicio");
    private JTextField textFieldNascimentoFuncionario;
    private JLabel labelNascimentoFuncionario = new JLabel("Nascimento");
    private JTextField textFieldStatusFuncionarioIdStatus = new JTextField(20);
    private JLabel labelStatusFuncionarioIdStatus = new JLabel("Status");

    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");

    String acao = "";//variavel para facilitar insert e update
    DAOFuncionario daoFuncionario = new DAOFuncionario();
    DAOStatusFuncionario daoStatusFuncionario = new DAOStatusFuncionario();
    SimpleDateFormat sdfDataInicio = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdfNascimento = new SimpleDateFormat("dd/MM/yyyy");
    MaskFormatter maskDataInicio;
    MaskFormatter maskNascimento;
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    Funcionario funcionario;

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
            boolean idFuncionario, boolean nomeFuncionario, boolean salarioFuncionario, boolean telefoneFuncionario, boolean dataInicioFuncionario, boolean nascimentoFuncionario, boolean statusFuncionarioIdStatus) {
        if (idFuncionario) {
            textFieldIdFuncionario.requestFocus();
            textFieldIdFuncionario.selectAll();
        }
        textFieldIdFuncionario.setEnabled(idFuncionario);
        textFieldIdFuncionario.setEditable(idFuncionario);
        textFieldNomeFuncionario.setEditable(nomeFuncionario);
        textFieldSalarioFuncionario.setEditable(salarioFuncionario);
        textFieldTelefoneFuncionario.setEditable(telefoneFuncionario);
        textFieldDataInicioFuncionario.setEditable(dataInicioFuncionario);
        textFieldNascimentoFuncionario.setEditable(nascimentoFuncionario);
        textFieldStatusFuncionarioIdStatus.setEditable(statusFuncionarioIdStatus);
    }

    public void zerarAtributos() {
        textFieldNomeFuncionario.setText("");
        textFieldSalarioFuncionario.setText("");
        textFieldTelefoneFuncionario.setText("");
        textFieldDataInicioFuncionario.setText("");
        textFieldNascimentoFuncionario.setText("");
        textFieldStatusFuncionarioIdStatus.setText("");
    }
    Color corPadrao = labelIdFuncionario.getBackground();

    public GUIFuncionario() {

        setTitle("CRUD - Funcionario");
        setSize(500, 500);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setLocationRelativeTo(null);
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes
        
        try {
            maskDataInicio = new MaskFormatter("##/##/####");
        } catch (ParseException ex) {
            Logger.getLogger(GUIFuncionario.class.getName()).log(Level.SEVERE, null, ex); //gerado automatico
        }
        textFieldDataInicioFuncionario = new JFormattedTextField(maskDataInicio);
        textFieldDataInicioFuncionario.setColumns(15);
        
        try {
            maskNascimento = new MaskFormatter("##/##/####");
        } catch (ParseException ex) {
            Logger.getLogger(GUIFuncionario.class.getName()).log(Level.SEVERE, null, ex); //gerado automatico
        }
        textFieldNascimentoFuncionario = new JFormattedTextField(maskNascimento);
        textFieldNascimentoFuncionario.setColumns(15);
        
        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false, false, false, false, false, false);
        btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar Toolbar1 = new JToolBar();
        Toolbar1.add(labelIdFuncionario);
        Toolbar1.add(textFieldIdFuncionario);
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
        centro.setLayout(new GridLayout(6, 2));
        centro.add(labelNomeFuncionario);
        centro.add(textFieldNomeFuncionario);
        centro.add(labelSalarioFuncionario);
        centro.add(textFieldSalarioFuncionario);
        centro.add(labelTelefoneFuncionario);
        centro.add(textFieldTelefoneFuncionario);
        centro.add(labelDataInicioFuncionario);
        centro.add(textFieldDataInicioFuncionario);
        centro.add(labelNascimentoFuncionario);
        centro.add(textFieldNascimentoFuncionario);
        centro.add(labelStatusFuncionarioIdStatus);
        centro.add(textFieldStatusFuncionarioIdStatus);
        pnAvisos.add(labelAviso);
        pnAvisos.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(pnAvisos, BorderLayout.SOUTH);
        textFieldIdFuncionario.requestFocus();
        textFieldIdFuncionario.selectAll();
        textFieldIdFuncionario.setBackground(Color.GREEN);
        labelAviso.setText("Digite um Idfuncionario e clic [Pesquisar]");
//--------------- listeners ----------------- 
        textFieldIdFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.doClick();
            }
        });

        textFieldStatusFuncionarioIdStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (btnCancel.isVisible()) {
                    List<String> listaAuxiliar = daoStatusFuncionario.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnCancel.getLocationOnScreen();
                        //Point lc = new Point(7,8);
                        lc.x = lc.x + btnCancel.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldStatusFuncionarioIdStatus.setText(aux[0] + "-" + aux[1]);
                        } else {
                            textFieldStatusFuncionarioIdStatus.requestFocus();
                            textFieldStatusFuncionarioIdStatus.selectAll();
                        }
                    }
                }
            }
        });

//-----------------------------  btnRetrieve ------------------------------------------
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                funcionario = new Funcionario();
                textFieldIdFuncionario.setText(textFieldIdFuncionario.getText().trim());//caso tenham sido digitados espaços

                if (textFieldIdFuncionario.getText().equals("")) {
                    List<String> listaAuxiliar = daoFuncionario.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldIdFuncionario.setText(aux[0]);
                            btnRetrieve.doClick();
                        } else {
                            textFieldIdFuncionario.requestFocus();
                            textFieldIdFuncionario.selectAll();
                        }
                    }

                    textFieldIdFuncionario.requestFocus();
                    textFieldIdFuncionario.selectAll();
                } else {
                    try {
                        funcionario.setIdFuncionario(Integer.valueOf(textFieldIdFuncionario.getText()));
                        funcionario = daoFuncionario.obter(funcionario.getIdFuncionario());
                        if (funcionario != null) { //se encontrou na lista
                            textFieldNomeFuncionario.setText((funcionario.getNomeFuncionario()));
                            textFieldSalarioFuncionario.setText(decimalFormat.format(funcionario.getSalarioFuncionario()));
                            textFieldTelefoneFuncionario.setText((funcionario.getTelefoneFuncionario()));
                            textFieldDataInicioFuncionario.setText(sdfDataInicio.format(funcionario.getDataInicioFuncionario()));
                            textFieldNascimentoFuncionario.setText(sdfNascimento.format(funcionario.getNascimentoFuncionario()));
                            textFieldStatusFuncionarioIdStatus.setText((funcionario.getStatusFuncionarioIdStatus().getIdStatus() + "-" + funcionario.getStatusFuncionarioIdStatus().getNomeStatus()));
                            atvBotoes(false, true, true, true);
                            habilitarAtributos(true,
                                    false, false, false, false, false, false
                            );
                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                            acao = "encontrou";
                        } else {
                            atvBotoes(true, true, false, false);
                            zerarAtributos();
                            labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                        }
                        textFieldIdFuncionario.setBackground(Color.green);
                    } catch (Exception x) {
                        textFieldIdFuncionario.setOpaque(true);
                        textFieldIdFuncionario.selectAll();
                        textFieldIdFuncionario.requestFocus();
                        textFieldIdFuncionario.setBackground(Color.red);
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
                        true, true, true, true, true, true
                );
                textFieldNomeFuncionario.requestFocus();
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
                    funcionario = new Funcionario();
                }
                try {
                    funcionario.setIdFuncionario(Integer.valueOf((textFieldIdFuncionario.getText())));
                } catch (Exception erro0) {
                    deuRuim = true;
                    textFieldIdFuncionario.setBackground(Color.red);
                }
                funcionario.setNomeFuncionario(String.valueOf(textFieldNomeFuncionario.getText()));
                try {
                    String valor = textFieldSalarioFuncionario.getText();
                    valor = valor.replace(".", "");
                    valor = valor.replace(",", ".");
                    textFieldSalarioFuncionario.setText(valor);
                    funcionario.setSalarioFuncionario(Double.valueOf((textFieldSalarioFuncionario.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldSalarioFuncionario.setBackground(Color.red);
                }
                funcionario.setTelefoneFuncionario(String.valueOf(textFieldTelefoneFuncionario.getText()));
                try {
                    sdfDataInicio.setLenient(false);
                    funcionario.setDataInicioFuncionario(sdfDataInicio.parse((textFieldDataInicioFuncionario.getText())));
                } catch (Exception erro4) {
                    deuRuim = true;
                    textFieldDataInicioFuncionario.setBackground(Color.red);
                }
                try {
                    sdfNascimento.setLenient(false);
                    funcionario.setNascimentoFuncionario(sdfNascimento.parse((textFieldNascimentoFuncionario.getText())));
                } catch (Exception erro5) {
                    deuRuim = true;
                    textFieldNascimentoFuncionario.setBackground(Color.red);
                }
                try {

                    funcionario.setStatusFuncionarioIdStatus(daoStatusFuncionario.obter(Integer.valueOf((textFieldStatusFuncionarioIdStatus.getText().split("-")[0]))));
                } catch (Exception erro6) {
                    deuRuim = true;
                    textFieldStatusFuncionarioIdStatus.setBackground(Color.red);
                }
                if (!deuRuim) {
                    if (acao.equals("insert")) {
                        daoFuncionario.inserir(funcionario);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoFuncionario.atualizar(funcionario);
                        labelAviso.setText("Registro alterado.");
                    }
                    habilitarAtributos(true,
                            false, false, false, false, false, false);
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
                        false, false, false, false, false, false
                );
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                GUIFuncionarioListagem guiFuncionarioListagem = new GUIFuncionarioListagem(daoFuncionario.listInOrderId());
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);

                habilitarAtributos(false,
                        true, true, true, true, true, true
                );
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + funcionario.getNomeFuncionario() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoFuncionario.remover(funcionario);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    textFieldNomeFuncionario.requestFocus();
                    textFieldNomeFuncionario.selectAll();
                }
            }
        });
        textFieldNomeFuncionario.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldNomeFuncionario.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldNomeFuncionario.setBackground(corPadrao);
            }
        });
        textFieldSalarioFuncionario.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldSalarioFuncionario.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldSalarioFuncionario.setBackground(corPadrao);
            }
        });
        textFieldTelefoneFuncionario.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldTelefoneFuncionario.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldTelefoneFuncionario.setBackground(corPadrao);
            }
        });
        textFieldDataInicioFuncionario.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldDataInicioFuncionario.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldDataInicioFuncionario.setBackground(corPadrao);
            }
        });
        textFieldNascimentoFuncionario.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldNascimentoFuncionario.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldNascimentoFuncionario.setBackground(corPadrao);
            }
        });
        textFieldStatusFuncionarioIdStatus.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldStatusFuncionarioIdStatus.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldStatusFuncionarioIdStatus.setBackground(corPadrao);
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
        new GUIFuncionario();
    }
}
