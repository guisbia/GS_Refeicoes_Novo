package GUIs;

import DAOs.*;
import Entidades.*;
import java.awt.Dimension;
import java.util.List;
import java.awt.Point;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import myUtil.JanelaPesquisar;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import myUtil.CopiaImagem;

/**
 *
 * @author Bianca
 */
public class GUIPratoPrincipal extends JDialog {

    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));
    //ImageIcon iconeAbrirImg = new ImageIcon(getClass().getResource("/icones/aa.png"));
    JButton btnCreate = new JButton(iconeCreate);
    JButton btnRetrieve = new JButton(iconeRetrieve);
    JButton btnUpdate = new JButton(iconeUpdate);
    JButton btnDelete = new JButton(iconeDelete);
    JButton btnSave = new JButton(iconeSave);
    JButton btnCancel = new JButton(iconeCancel);
    JButton btnList = new JButton(iconeListar);
    private JButton btAbrirImagem = new JButton("Escolher imagem");
    private JTextField textFieldIdPratoPrincipal = new JTextField(20);
    private JLabel labelIdPratoPrincipal = new JLabel("Id   ");
    private JTextField textFieldNomePratoPrincipal = new JTextField(20);
    private JLabel labelNomePratoPrincipal = new JLabel("   Nome");
    private JCheckBox cbStatus = new JCheckBox("Ativo");
    private JLabel labelStatus = new JLabel("   Status");
    private JLabel labelFotoPratoPrincipal = new JLabel();

    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");

    String acao = "";//variavel para facilitar insert e update
    DAOPratoPrincipal daoPratoPrincipal = new DAOPratoPrincipal();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    PratoPrincipal pratoPrincipal;

    private void atvBotoes(boolean c, boolean r, boolean u, boolean d) {
        btnCreate.setEnabled(c);
        btnRetrieve.setEnabled(r);
        btnUpdate.setEnabled(u);
        btnDelete.setEnabled(d);
        btnList.setEnabled(r);
        // btAbrirImagem.setEnabled(c);
    }

    public void mostrarBotoes(boolean visivel) {
        btnCreate.setVisible(visivel);
        btnRetrieve.setVisible(visivel);
        btnUpdate.setVisible(visivel);
        btnDelete.setVisible(visivel);
        btnList.setVisible(visivel);
        btnSave.setVisible(!visivel);
        btnCancel.setVisible(!visivel);
        //     btAbrirImagem.setVisible(!visivel);
    }

    private void habilitarAtributos(boolean idPratoPrincipal, boolean nomePratoPrincipal, boolean status, boolean fotoPratoPrincipal) {
        if (idPratoPrincipal) {
            textFieldIdPratoPrincipal.requestFocus();
            textFieldIdPratoPrincipal.selectAll();
        }
        textFieldIdPratoPrincipal.setEnabled(idPratoPrincipal);
        textFieldIdPratoPrincipal.setEditable(idPratoPrincipal);
        textFieldNomePratoPrincipal.setEditable(nomePratoPrincipal);
        cbStatus.setEnabled(status);
    }

    public void zerarAtributos() {
        textFieldNomePratoPrincipal.setText("");
        cbStatus.setSelected(false);
        try {    // ALTERAÇÃO DAQUI
            String caminho = "/imagens/desconhecido.jpg";
            Image imagemAux;
            ImageIcon icone = new ImageIcon(getClass().getResource(caminho));
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
            labelFotoPratoPrincipal.setIcon(icone);
        } catch (Exception err) {
            System.out.println("erro " + err.getLocalizedMessage());
        }

    }
    Color corPadrao = labelIdPratoPrincipal.getBackground();

    public GUIPratoPrincipal() {
        try {
            //carrega uma imagem e ajusta seu tamanho
            String caminho = "/imagens/desconhecido.jpg";
            Image imagemAux;
            ImageIcon icone = new ImageIcon(getClass().getResource(caminho));
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
            labelFotoPratoPrincipal.setIcon(icone);
        } catch (Exception err) {
            System.out.println("erro " + err.getLocalizedMessage());
        }

        setTitle("Prato Principal");
        setSize(600,450);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes
        setLocationRelativeTo(null);
        atvBotoes(false, true, false, false);
        btAbrirImagem.setVisible(false);
        habilitarAtributos(true, false, false, false);
        btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar Toolbar1 = new JToolBar();
        Toolbar1.add(labelIdPratoPrincipal);
        Toolbar1.add(textFieldIdPratoPrincipal);
        Toolbar1.add(btnRetrieve);
        Toolbar1.add(btnCreate);
        Toolbar1.add(btnUpdate);
        Toolbar1.add(btnDelete);
        Toolbar1.add(btnSave);
        Toolbar1.add(btnCancel);
        Toolbar1.add(btnList);
//        Toolbar1.add(btAbrirImagem);

        //cp.setLayout(new BorderLayout()); 
        btnSave.setVisible(false);
        btnCancel.setVisible(false);  //atributos
        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(1, 2));
        JPanel painelCentroEsquerda = new JPanel();
        JPanel painelCentroDireita = new JPanel();
        painelCentroEsquerda.setLayout(new GridLayout(2, 2));
        centro.add(painelCentroEsquerda);
        centro.add(painelCentroDireita);
        painelCentroEsquerda.add(labelNomePratoPrincipal);
        painelCentroEsquerda.add(textFieldNomePratoPrincipal);
        painelCentroEsquerda.add(labelStatus);
        painelCentroEsquerda.add(cbStatus);
        painelCentroDireita.add(labelFotoPratoPrincipal);
        painelCentroDireita.add(btAbrirImagem);
        pnAvisos.add(labelAviso);
        pnAvisos.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(pnAvisos, BorderLayout.SOUTH);
        textFieldIdPratoPrincipal.requestFocus();
        textFieldIdPratoPrincipal.selectAll();
        textFieldIdPratoPrincipal.setBackground(Color.GREEN);
        labelAviso.setText("Digite um Id e clic [Pesquisar]");
//--------------- listeners ----------------- 
        textFieldIdPratoPrincipal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.doClick();
            }
        });

        btAbrirImagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (fc.showOpenDialog(centro) == JFileChooser.APPROVE_OPTION) {
                    File img = fc.getSelectedFile();
                    String caminho = fc.getSelectedFile().getAbsolutePath();
                    pratoPrincipal = new PratoPrincipal();
                    pratoPrincipal.setFotoPratoPrincipal(caminho);
                    System.out.println("caminho " + pratoPrincipal.getFotoPratoPrincipal());
                    try {
                        ImageIcon icone = new javax.swing.ImageIcon(img.getAbsolutePath());
                        Image imagemAux;
                        imagemAux = icone.getImage();
                        icone.setImage(imagemAux.getScaledInstance(200, 200, Image.SCALE_FAST));
                        labelFotoPratoPrincipal.setIcon(icone);
                    } catch (Exception ex) {
                        System.out.println("Erro: " + ex.getMessage());
                    }
                }
            }
        });
//-----------------------------  btnRetrieve ------------------------------------------
        btnRetrieve.addActionListener(new ActionListener() {  //buscar
            @Override
            public void actionPerformed(ActionEvent ae) {
                pratoPrincipal = new PratoPrincipal();
                textFieldIdPratoPrincipal.setText(textFieldIdPratoPrincipal.getText().trim());//caso tenham sido digitados espaços
                btAbrirImagem.setVisible(false);
                if (textFieldIdPratoPrincipal.getText().equals("")) {
                    List<String> listaAuxiliar = daoPratoPrincipal.listInOrderNomeStrings("nome");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldIdPratoPrincipal.setText(aux[0]);
                            btnRetrieve.doClick();
                        } else {
                            textFieldIdPratoPrincipal.requestFocus();
                            textFieldIdPratoPrincipal.selectAll();
                        }
                    }

                    textFieldIdPratoPrincipal.requestFocus();
                    textFieldIdPratoPrincipal.selectAll();
                } else {
                    try {
                        pratoPrincipal.setIdPratoPrincipal(Integer.valueOf(textFieldIdPratoPrincipal.getText()));
                        pratoPrincipal = daoPratoPrincipal.obter(pratoPrincipal.getIdPratoPrincipal());
                        if (pratoPrincipal != null) { //se encontrou na lista
                            textFieldNomePratoPrincipal.setText((pratoPrincipal.getNomePratoPrincipal()));
                            cbStatus.setSelected(pratoPrincipal.getStatus());
                            try {
                                String caminho = "/imagens/" + textFieldIdPratoPrincipal.getText() + ".jpg";
                                Image imagemAux;
                                ImageIcon icone = new ImageIcon(getClass().getResource(caminho));
                                imagemAux = icone.getImage();
                                icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                                labelFotoPratoPrincipal.setIcon(icone);
                            } catch (Exception err) {
                                String caminho = "/imagens/desconhecido.jpg";
                                //System.out.println("erro" + err.getLocalizedMessage());
                                try {
                                    Image imagemAux;
                                    ImageIcon icone = new ImageIcon(getClass().getResource(caminho));
                                    imagemAux = icone.getImage();
                                    icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                                    labelFotoPratoPrincipal.setIcon(icone);
                                } catch (Exception err2) {
                                }
                            }
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
                        textFieldIdPratoPrincipal.setBackground(Color.green);
                    } catch (Exception x) {
                        textFieldIdPratoPrincipal.setOpaque(true);
                        textFieldIdPratoPrincipal.selectAll();
                        textFieldIdPratoPrincipal.requestFocus();
                        textFieldIdPratoPrincipal.setBackground(Color.red);
                        labelAviso.setText("Tipo errado - " + x.getMessage());
                    }
                }
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                habilitarAtributos(false, true, true, true);
                textFieldNomePratoPrincipal.requestFocus();
                mostrarBotoes(false);
                btAbrirImagem.setVisible(true);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean deuRuim = false;
                try {
                    pratoPrincipal.setIdPratoPrincipal(Integer.valueOf((textFieldIdPratoPrincipal.getText())));
                } catch (Exception erro0) {
                    deuRuim = true;
                    textFieldIdPratoPrincipal.setBackground(Color.red);
                }
                pratoPrincipal.setNomePratoPrincipal(String.valueOf(textFieldNomePratoPrincipal.getText()));
                try {
                    pratoPrincipal.setStatus(Boolean.valueOf((cbStatus.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    cbStatus.setBackground(Color.red);
                }
                try {
                    CopiaImagem copiaImagem = new CopiaImagem();
                    copiaImagem.copiar(pratoPrincipal.getFotoPratoPrincipal(), "C:/Users/bianc/Documents/NetBeansProjects/BiancaGuiSilva_GsRefeicoes_2/src/imagens/" + textFieldIdPratoPrincipal.getText() + ".jpg");
                } catch (Exception e) {
                    System.out.println("Deu ruim na img linha 345");
                    System.out.println(pratoPrincipal.getFotoPratoPrincipal());
                    deuRuim = true;
                }
                if (!deuRuim) {
                    if (acao.equals("insert")) {
                        daoPratoPrincipal.inserir(pratoPrincipal);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoPratoPrincipal.atualizar(pratoPrincipal);
                        labelAviso.setText("Registro alterado.");
                    }
                    habilitarAtributos(true,
                            false, false, false);
                    mostrarBotoes(true);
                    btAbrirImagem.setVisible(false);
                    atvBotoes(false, true, false, false);
                }//!deu ruim
                else {
                    labelAviso.setText("Erro nos dados - corrija ");
                    labelAviso.setBackground(Color.red);
                    btAbrirImagem.setVisible(true);
                }
                try {
                    String caminho = "/imagens/desconhecido.jpg";
                    Image imagemAux;
                    ImageIcon icone = new ImageIcon(getClass().getResource(caminho));
                    imagemAux = icone.getImage();
                    icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                    labelFotoPratoPrincipal.setIcon(icone);
                } catch (Exception err) {
                    System.out.println("erro " + err.getLocalizedMessage());
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
                GUIPratoPrincipalListagem guiPratoPrincipalListagem = new GUIPratoPrincipalListagem(daoPratoPrincipal.listInOrderId());
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);
                btAbrirImagem.setVisible(true);
                habilitarAtributos(false,
                        true, true, true
                );
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + pratoPrincipal.getNomePratoPrincipal() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoPratoPrincipal.remover(pratoPrincipal);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    textFieldNomePratoPrincipal.requestFocus();
                    textFieldNomePratoPrincipal.selectAll();
                }
            }
        });
        textFieldNomePratoPrincipal.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldNomePratoPrincipal.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldNomePratoPrincipal.setBackground(corPadrao);
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
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco
     /*   addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });*/
        // 

        setModal(true);
        setVisible(true);//faz a janela ficar visível  
    }

    public static void main(String[] args) {
        new GUIPratoPrincipal();
    }
}
