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

public class GUITamanhoMarmita extends JDialog {

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
    private JTextField textFieldIdtamanhomarmita = new JTextField(20);
    private JLabel labelIdtamanhomarmita = new JLabel(" Id   ");
    private JTextField textFieldNometamanhomarmita = new JTextField(20);
    private JLabel labelNometamanhomarmita = new JLabel("Nome");

    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");

    String acao = "";//variavel para facilitar insert e update
    DAOTamanhoMarmita daoTamanhoMarmita = new DAOTamanhoMarmita();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    TamanhoMarmita tamanhoMarmita;

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
            boolean idTamanhoMarmita, boolean nomeTamanhoMarmita) {
        if (idTamanhoMarmita) {
            textFieldIdtamanhomarmita.requestFocus();
            textFieldIdtamanhomarmita.selectAll();
        }
        textFieldIdtamanhomarmita.setEnabled(idTamanhoMarmita);
        textFieldIdtamanhomarmita.setEditable(idTamanhoMarmita);
        textFieldNometamanhomarmita.setEditable(nomeTamanhoMarmita);
    }

    public void zerarAtributos() {
        textFieldNometamanhomarmita.setText("");
    }
    Color corPadrao = labelIdtamanhomarmita.getBackground();

    public GUITamanhoMarmita() {

        setTitle("Tamanho Marmita");
        setSize(450,180);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true,false);
        btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar Toolbar1 = new JToolBar();
        Toolbar1.add(labelIdtamanhomarmita);
        Toolbar1.add(textFieldIdtamanhomarmita);
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
        centro.setLayout(new GridLayout(1, 2));
        centro.add(labelNometamanhomarmita);
        centro.add(textFieldNometamanhomarmita);
        pnAvisos.add(labelAviso);
        pnAvisos.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(pnAvisos, BorderLayout.SOUTH);
        textFieldIdtamanhomarmita.requestFocus();
        textFieldIdtamanhomarmita.selectAll();
        textFieldIdtamanhomarmita.setBackground(Color.GREEN);
        labelAviso.setText("Digite um Id e clic [Pesquisar]");
//--------------- listeners ----------------- 
        textFieldIdtamanhomarmita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.doClick();
            }
        });

//-----------------------------  btnRetrieve ------------------------------------------
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tamanhoMarmita = new TamanhoMarmita();
                textFieldIdtamanhomarmita.setText(textFieldIdtamanhomarmita.getText().trim());//caso tenham sido digitados espaços
                if (textFieldIdtamanhomarmita.getText().equals("")) {
                    List<String> listaAuxiliar = daoTamanhoMarmita.listInOrderNomeStrings("nome");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldIdtamanhomarmita.setText(aux[0]);
                            btnRetrieve.doClick();
                        } else {
                            textFieldIdtamanhomarmita.requestFocus();
                            textFieldIdtamanhomarmita.selectAll();
                        }
                    }

                    textFieldIdtamanhomarmita.requestFocus();
                    textFieldIdtamanhomarmita.selectAll();
                } else {
                    try {
                        tamanhoMarmita.setIdTamanhoMarmita(Integer.valueOf(textFieldIdtamanhomarmita.getText()));
                        tamanhoMarmita = daoTamanhoMarmita.obter(tamanhoMarmita.getIdTamanhoMarmita());
                        if (tamanhoMarmita != null) { //se encontrou na lista
                            textFieldNometamanhomarmita.setText((tamanhoMarmita.getNomeTamanhoMarmita()));
                            atvBotoes(false, true, true, true);
                            habilitarAtributos(true,
                                    false
                            );
                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                            acao = "encontrou";
                        } else {
                            atvBotoes(true, true, false, false);
                            zerarAtributos();
                            labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                        }
                        textFieldIdtamanhomarmita.setBackground(Color.green);
                    } catch (Exception x) {
                        textFieldIdtamanhomarmita.setOpaque(true);
                        textFieldIdtamanhomarmita.selectAll();
                        textFieldIdtamanhomarmita.requestFocus();
                        textFieldIdtamanhomarmita.setBackground(Color.red);
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
                        true
                );
                textFieldNometamanhomarmita.requestFocus();
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
                    tamanhoMarmita = new TamanhoMarmita();
                }
                try {
                    tamanhoMarmita.setIdTamanhoMarmita(Integer.valueOf((textFieldIdtamanhomarmita.getText())));
                } catch (Exception erro0) {
                    deuRuim = true;
                    textFieldIdtamanhomarmita.setBackground(Color.red);
                }
                tamanhoMarmita.setNomeTamanhoMarmita(String.valueOf(textFieldNometamanhomarmita.getText()));
                if (!deuRuim) {
                    if (acao.equals("insert")) {
                        daoTamanhoMarmita.inserir(tamanhoMarmita);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoTamanhoMarmita.atualizar(tamanhoMarmita);
                        labelAviso.setText("Registro alterado.");
                    }
                    habilitarAtributos(true,
                            false);
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
                        false
                );
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                GUITamanhoMarmitaListagem guiTamanhoMarmitaListagem = new GUITamanhoMarmitaListagem(daoTamanhoMarmita.listInOrderId());
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);
                habilitarAtributos(false,true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + tamanhoMarmita.getNomeTamanhoMarmita() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoTamanhoMarmita.remover(tamanhoMarmita);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    textFieldNometamanhomarmita.requestFocus();
                    textFieldNometamanhomarmita.selectAll();
                }
            }
        });
        textFieldNometamanhomarmita.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldNometamanhomarmita.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldNometamanhomarmita.setBackground(corPadrao);
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
        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);//faz a janela ficar visível  
        
    }

    public static void main(String[] args) {
        new GUITamanhoMarmita();
    }
}
