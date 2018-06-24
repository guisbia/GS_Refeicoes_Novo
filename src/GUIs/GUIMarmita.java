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
public class GUIMarmita extends JDialog {

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
    private JTextField textFieldIdMarmita = new JTextField(20);
    private JLabel labelIdMarmita = new JLabel("Id");
    private JTextField textFieldPratoPrincipalIdPratoPrincipal = new JTextField(20);
    private JLabel labelPratoPrincipalIdPratoPrincipal = new JLabel("Prato Principal");
    private JTextField textFieldTamanhoMarmitaIdTamanhoMarmita = new JTextField(20);
    private JLabel labelTamanhoMarmitaIdTamanhoMarmita = new JLabel("Tamanho Marmita");
    private JCheckBox cbStatus = new JCheckBox("Ativo");
    private JLabel labelStatus = new JLabel("Status");

    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");

    String acao = "";//variavel para facilitar insert e update
    DAOMarmita daoMarmita = new DAOMarmita();
    DAOTamanhoMarmita daoTamanhoMarmita = new DAOTamanhoMarmita();
    DAOPratoPrincipal daoPratoPrincipal = new DAOPratoPrincipal();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    Marmita marmita;

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
            boolean idMarmita, boolean PratoPrincipalIdPratoPrincipal, boolean TamanhoMarmitaIdTamanhoMarmita, boolean status) {
        if (idMarmita) {
            textFieldIdMarmita.requestFocus();
            textFieldIdMarmita.selectAll();
        }
        textFieldIdMarmita.setEnabled(idMarmita);
        textFieldIdMarmita.setEditable(idMarmita);
        textFieldPratoPrincipalIdPratoPrincipal.setEditable(PratoPrincipalIdPratoPrincipal);
        textFieldTamanhoMarmitaIdTamanhoMarmita.setEditable(TamanhoMarmitaIdTamanhoMarmita);
        cbStatus.setEnabled(status);
    }

    public void zerarAtributos() {
        textFieldPratoPrincipalIdPratoPrincipal.setText("");
        textFieldTamanhoMarmitaIdTamanhoMarmita.setText("");
        cbStatus.setSelected(false);
    }
    Color corPadrao = labelIdMarmita.getBackground();

    public GUIMarmita() {

        setTitle("CRUD - Marmita");
        setSize(500, 500);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setLocationRelativeTo(null);
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

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
        Toolbar1.add(labelIdMarmita);
        Toolbar1.add(textFieldIdMarmita);
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
        centro.add(labelPratoPrincipalIdPratoPrincipal);
        centro.add(textFieldPratoPrincipalIdPratoPrincipal);
        centro.add(labelTamanhoMarmitaIdTamanhoMarmita);
        centro.add(textFieldTamanhoMarmitaIdTamanhoMarmita);
        centro.add(labelStatus);
        centro.add(cbStatus);
        pnAvisos.add(labelAviso);
        pnAvisos.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(pnAvisos, BorderLayout.SOUTH);
        textFieldIdMarmita.requestFocus();
        textFieldIdMarmita.selectAll();
        textFieldIdMarmita.setBackground(Color.GREEN);
        labelAviso.setText("Digite um Idmarmita e clic [Pesquisar]");
//--------------- listeners ----------------- 
        textFieldIdMarmita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.doClick();
            }
        });

        textFieldPratoPrincipalIdPratoPrincipal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (btnCancel.isVisible()) {
                    List<String> listaAuxiliar = daoPratoPrincipal.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnCancel.getLocationOnScreen();
                        //Point lc = new Point(7,8);
                        lc.x = lc.x + btnCancel.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldPratoPrincipalIdPratoPrincipal.setText(aux[0] + "-" + aux[1]);
                        } else {
                            textFieldPratoPrincipalIdPratoPrincipal.requestFocus();
                            textFieldPratoPrincipalIdPratoPrincipal.selectAll();
                        }
                    }
                }
            }
        });

        textFieldTamanhoMarmitaIdTamanhoMarmita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (btnCancel.isVisible()) {
                    List<String> listaAuxiliar = daoTamanhoMarmita.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnCancel.getLocationOnScreen();
                        //Point lc = new Point(7,8);
                        lc.x = lc.x + btnCancel.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldTamanhoMarmitaIdTamanhoMarmita.setText(aux[0] + "-" + aux[1]);
                        } else {
                            textFieldTamanhoMarmitaIdTamanhoMarmita.requestFocus();
                            textFieldTamanhoMarmitaIdTamanhoMarmita.selectAll();
                        }
                    }
                }
            }
        });
//-----------------------------  btnRetrieve ------------------------------------------
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                marmita = new Marmita();
                textFieldIdMarmita.setText(textFieldIdMarmita.getText().trim());//caso tenham sido digitados espaços

                if (textFieldIdMarmita.getText().equals("")) {
                    List<String> listaAuxiliar = daoMarmita.listInOrderNomeStrings("nome");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldIdMarmita.setText(aux[0]);
                            btnRetrieve.doClick();
                        } else {
                            textFieldIdMarmita.requestFocus();
                            textFieldIdMarmita.selectAll();
                        }
                    }

                    textFieldIdMarmita.requestFocus();
                    textFieldIdMarmita.selectAll();
                } else {
                    try {
                        marmita.setIdMarmita(Integer.valueOf(textFieldIdMarmita.getText()));
                        marmita = daoMarmita.obter(marmita.getIdMarmita());
                        if (marmita != null) { //se encontrou na lista
                            textFieldPratoPrincipalIdPratoPrincipal.setText((marmita.getPratoPrincipalIdPratoPrincipal().getIdPratoPrincipal() + "-" + marmita.getPratoPrincipalIdPratoPrincipal().getNomePratoPrincipal()));
                            textFieldTamanhoMarmitaIdTamanhoMarmita.setText((marmita.getTamanhoMarmitaIdTamanhoMarmita().getIdTamanhoMarmita() + "-" + marmita.getTamanhoMarmitaIdTamanhoMarmita().getNomeTamanhoMarmita()));
                            cbStatus.setSelected(marmita.getStatus());
                            atvBotoes(false, true, true, true);
                            habilitarAtributos(true, false, false, false);
                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                            acao = "encontrou";
                        } else {
                            atvBotoes(true, true, false, false);
                            zerarAtributos();
                            labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                        }
                        textFieldIdMarmita.setBackground(Color.green);
                    } catch (Exception x) {
                        textFieldIdMarmita.setOpaque(true);
                        textFieldIdMarmita.selectAll();
                        textFieldIdMarmita.requestFocus();
                        textFieldIdMarmita.setBackground(Color.red);
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
                textFieldPratoPrincipalIdPratoPrincipal.requestFocus();
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
                    marmita = new Marmita();
                }
                try {
                    marmita.setIdMarmita(Integer.valueOf((textFieldIdMarmita.getText())));
                } catch (Exception erro0) {
                    deuRuim = true;
                    textFieldIdMarmita.setBackground(Color.red);
                }
                try {
                    marmita.setPratoPrincipalIdPratoPrincipal(daoPratoPrincipal.obter(Integer.valueOf(textFieldPratoPrincipalIdPratoPrincipal.getText().split("-")[0])));
                } catch (Exception erro1) {
                    deuRuim = true;
                    textFieldPratoPrincipalIdPratoPrincipal.setBackground(Color.red);
                }
                try {
                    marmita.setTamanhoMarmitaIdTamanhoMarmita(daoTamanhoMarmita.obter(Integer.valueOf(textFieldTamanhoMarmitaIdTamanhoMarmita.getText().split("-")[0])));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldTamanhoMarmitaIdTamanhoMarmita.setBackground(Color.red);
                }
                try {
                    marmita.setStatus(Boolean.valueOf((cbStatus.isSelected())));
                    System.out.println(cbStatus.isSelected());
                } catch (Exception erro3) {
                    deuRuim = true;
                    cbStatus.setBackground(Color.red);
                }
                if (!deuRuim) {
                    if (acao.equals("insert")) {
                        daoMarmita.inserir(marmita);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoMarmita.atualizar(marmita);
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
                GUIMarmitaListagem guiMarmitaListagem = new GUIMarmitaListagem(daoMarmita.listInOrderNome());
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
                        "Confirma a exclusão do registro <ID = " + marmita.getPratoPrincipalIdPratoPrincipal() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoMarmita.remover(marmita);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    textFieldPratoPrincipalIdPratoPrincipal.requestFocus();
                    textFieldPratoPrincipalIdPratoPrincipal.selectAll();
                }
            }
        });
        textFieldPratoPrincipalIdPratoPrincipal.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldPratoPrincipalIdPratoPrincipal.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldPratoPrincipalIdPratoPrincipal.setBackground(corPadrao);
            }
        });
        textFieldTamanhoMarmitaIdTamanhoMarmita.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldTamanhoMarmitaIdTamanhoMarmita.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldTamanhoMarmitaIdTamanhoMarmita.setBackground(corPadrao);
            }
        });
        cbStatus.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                cbStatus.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                cbStatus.setBackground(corPadrao);
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
        new GUIMarmita();
    }
}
