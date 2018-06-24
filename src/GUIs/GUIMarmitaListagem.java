package GUIs;

import Entidades.Marmita;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

// @author Radames
public class GUIMarmitaListagem extends JDialog {

    JPanel painelTa = new JPanel();
    JScrollPane scroll = new JScrollPane();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");

    public GUIMarmitaListagem(List<Marmita> texto) {
        setTitle("Listagem de Marmita");
        setSize(600, 200);//tamanho da janela
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);//libera ao sair (tira da memÃ³ria a classe
        setLayout(new BorderLayout());//informa qual gerenciador de layout serÃ¡ usado
        setBackground(Color.CYAN);//cor do fundo da janela
        setModal(true);
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        JToolBar toolBar = new JToolBar();
        String[] colunas = new String[]{
            "Id", "Prato Principal", "Tamanho Marmita", "Status"};

        String[][] dados = new String[0][3];

        DefaultTableModel model = new DefaultTableModel(dados, colunas);
        JTable tabela = new JTable(model);
        tabela.setEnabled(false);
        scroll.setViewportView(tabela);

        for (int i = 0; i < texto.size(); i++) {
            String nomePratoPrincipal = texto.get(i).getPratoPrincipalIdPratoPrincipal().getNomePratoPrincipal();
            String tamanhoMarmita = texto.get(i).getTamanhoMarmitaIdTamanhoMarmita().getNomeTamanhoMarmita();
            String[] linha = new String[]{
                String.valueOf(texto.get(i).getIdMarmita()), nomePratoPrincipal, tamanhoMarmita, String.valueOf(texto.get(i).getStatus())};
            model.addRow(linha);
        }

        // scroll.add(ta);
        painelTa.add(scroll);

        cp.add(toolBar, BorderLayout.NORTH);
        cp.add(scroll, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);//faz a janela ficar visÃ­vel        
    }

    public static void main(String[] args) {
        DAOs.DAOMarmita daoMarmita = new DAOs.DAOMarmita();
        new GUIMarmitaListagem(daoMarmita.listInOrderId());
    }
}
