package GUIs;

import DAOs.DAOPratoPrincipal;
import Entidades.PratoPrincipal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
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
public class GUIPratoPrincipalListagem extends JDialog {
    
    JPanel painelTa = new JPanel();
    JScrollPane scroll = new JScrollPane();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    
    public GUIPratoPrincipalListagem(List<PratoPrincipal> texto) {
        setTitle("Listagem de PratoPrincipal");
        setSize(600, 300);//tamanho da janela
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);//libera ao sair (tira da memÃ³ria a classe
        setLayout(new BorderLayout());//informa qual gerenciador de layout serÃ¡ usado
        setBackground(Color.CYAN);//cor do fundo da janela
        setModal(true);
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        JToolBar toolBar = new JToolBar();
        String[] colunas = new String[]{
            "idPratoPrincipal", "nomePratoPrincipal", "status", "fotoPratoPrincipal"};
        
        String[][] dados = new String[0][3];
        
        DefaultTableModel model = new DefaultTableModel(dados, colunas);
        JTable tabela = new JTable(model);
        tabela.setEnabled(false);
        scroll.setViewportView(tabela);
        
        for (int i = 0; i < texto.size(); i++) {
            String[] linha = new String[]{
                String.valueOf(texto.get(i).getIdPratoPrincipal()), String.valueOf(texto.get(i).getNomePratoPrincipal()), String.valueOf(texto.get(i).getStatus()), String.valueOf(texto.get(i).getFotoPratoPrincipal())};
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
        DAOPratoPrincipal daoPratoPrincipal = new        DAOPratoPrincipal();
        new GUIPratoPrincipalListagem( daoPratoPrincipal.listInOrderId());
    }    
}
