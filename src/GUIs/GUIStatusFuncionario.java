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
import java.awt.event.WindowListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import myUtil.CopiaImagem;

/**
 *
 * @author Bianca
 */
public class GUIStatusFuncionario extends JDialog {

    JPanel painelTa = new JPanel();
    JScrollPane scroll = new JScrollPane();

    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));
    JButton btnCreate = new JButton(iconeCreate);
  //  JButton btnRetrieve = new JButton(iconeRetrieve);
  //  JButton btnUpdate = new JButton(iconeUpdate);
    JButton btnDelete = new JButton(iconeDelete);
  //  JButton btnSave = new JButton(iconeSave);
  //  JButton btnCancel = new JButton(iconeCancel);
  //  JButton btnList = new JButton(iconeListar);
    //private JTextField textFieldIdstatus = new JTextField(20);
    //private JLabel labelIdstatus = new JLabel("Idstatus");
    // private JTextField textFieldNomestatus = new JTextField(20);
    //private JLabel labelNomestatus = new JLabel("Nomestatus");

    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");

    String acao = "";//variavel para facilitar insert e update
    DAOStatusFuncionario daoStatusFuncionario = new DAOStatusFuncionario();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    StatusFuncionario statusFuncionario;

    private void atvBotoes(boolean c, boolean d) {
        btnCreate.setEnabled(c);
        btnDelete.setEnabled(d);
       }

    public void mostrarBotoes(boolean visivel) {
        btnCreate.setVisible(visivel);
        btnDelete.setVisible(visivel);
    }

    //Color corPadrao = labelIdstatus.getBackground();
    public GUIStatusFuncionario() {

        setTitle("Status Funcionario");
        setSize(500, 250);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(true, true);

        btnCreate.setToolTipText("Inserir novo registro");
        btnDelete.setToolTipText("Excluir");
        JToolBar Toolbar1 = new JToolBar();
        Toolbar1.add(btnCreate);
        Toolbar1.add(btnDelete);
        
        JToolBar toolBar = new JToolBar();
        String[] colunas = new String[]{
            "id Status", "nome Status"};

        String[][] dados = new String[0][3];

        DefaultTableModel model = new DefaultTableModel(dados, colunas);
        JTable tabela = new JTable(model);

        scroll.setViewportView(tabela);

        for (int i = 0; i < daoStatusFuncionario.listInOrderId().size(); i++) {
            String[] linha = new String[]{
                String.valueOf(daoStatusFuncionario.listInOrderId().get(i).getIdStatus()), String.valueOf(daoStatusFuncionario.listInOrderId().get(i).getNomeStatus())};
            model.addRow(linha);
        }
       // for (int i = 0; i < model.getRowCount(); i++) {
         //   model.isCellEditable(i, 1);
        //}
        
        pnAvisos.add(labelAviso);
        pnAvisos.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(scroll, BorderLayout.CENTER);
        cp.add(pnAvisos, BorderLayout.SOUTH);
        labelAviso.setText("Digite um Idstatus e clic [Pesquisar]");

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String[] linha = {String.valueOf(daoStatusFuncionario.autoIdStatusFuncionario()), ""};
                model.addRow(linha);
                
                labelAviso.setText("Preencha os campos");
                acao = "insert";
            }
        });
        
        //model.getDataVector();

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + statusFuncionario.getNomeStatus() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoStatusFuncionario.remover(statusFuncionario);
                    mostrarBotoes(true);
                    atvBotoes(true, true);
                }
            }
        });

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco
        
       /*addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Sai   
                boolean deuRuim = false;
                try {
                    statusFuncionario.setIdStatus(Integer.valueOf((*//*descobrir como pegar texto da linha aqui*//*));
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
                    System.out.println("Erro para salvar dados");
                }
            }
        });
        */
        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);//faz a janela ficar visível  
    }

    public static void main(String[] args) {
        new GUIStatusFuncionario();
    }
}
