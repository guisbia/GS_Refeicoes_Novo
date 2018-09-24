package GUIs;

import Entidades.PedidoHasMarmita;
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
public class GUIPedidoHasMarmitaListagem extends JDialog {

    JPanel painelTa = new JPanel();
    JScrollPane scroll = new JScrollPane();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");

    public GUIPedidoHasMarmitaListagem(List<PedidoHasMarmita> texto) {
        setTitle("Listagem de Pedidohasmarmita");
        setSize(700, 300);//tamanho da janela
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);//libera ao sair (tira da memÃ³ria a classe
        setLayout(new BorderLayout());//informa qual gerenciador de layout serÃ¡ usado
        setBackground(Color.CYAN);//cor do fundo da janela
        setModal(true);
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        JToolBar toolBar = new JToolBar();
        String[] colunas = new String[]{
            "pedidoIdPedido", "marmitaIdMarmita", "qtde", "valor", "desconto"};

        String[][] dados = new String[0][3];

        DefaultTableModel model = new DefaultTableModel(dados, colunas);
        JTable tabela = new JTable(model);
        tabela.setEnabled(false);
        scroll.setViewportView(tabela);

        for (int i = 0; i < texto.size(); i++) {
            String[] linha = new String[]{
                String.valueOf(texto.get(i).getPedidoHasMarmitaPK().getPedidoIdPedido()), String.valueOf(texto.get(i).getPedidoHasMarmitaPK().getMarmitaIdMarmita()), String.valueOf(texto.get(i).getQtde()), decimalFormat.format(texto.get(i).getValor()), decimalFormat.format(texto.get(i).getDesconto())};
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
        DAOs.DAOPedidoHasMarmita daoPedidohasmarmita = new DAOs.DAOPedidoHasMarmita();
        new GUIPedidoHasMarmitaListagem(daoPedidohasmarmita.listInOrderId());
    }
}
