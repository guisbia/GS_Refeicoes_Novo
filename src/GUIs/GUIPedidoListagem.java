package GUIs;

import DAOs.DAOCliente;
import DAOs.DAOFuncionario;
import Entidades.Pedido;
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
public class GUIPedidoListagem extends JDialog {

    JPanel painelTa = new JPanel();
    JScrollPane scroll = new JScrollPane();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    DAOCliente daoCliente = new DAOCliente();
    DAOFuncionario daoFuncionario = new DAOFuncionario();

    public GUIPedidoListagem(List<Pedido> texto) {
        setTitle("Listagem de Pedido");
        setSize(600, 300);//tamanho da janela
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);//libera ao sair (tira da memÃ³ria a classe
        setLayout(new BorderLayout());//informa qual gerenciador de layout serÃ¡ usado
        setBackground(Color.CYAN);//cor do fundo da janela
        setModal(true);
        
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        JToolBar toolBar = new JToolBar();
        String[] colunas = new String[]{
            "idPedido", "funcionarioIdFuncionario", "clienteIdCliente", "dataPedido"};

        String[][] dados = new String[0][3];

        DefaultTableModel model = new DefaultTableModel(dados, colunas);
        JTable tabela = new JTable(model);
        tabela.setEnabled(false);
        scroll.setViewportView(tabela);

        for (int i = 0; i < texto.size(); i++) {
            String funcionario = texto.get(i).getFuncionarioIdFuncionario().getNomeFuncionario();
            String cliente = texto.get(i).getClienteIdCliente().getNomeCliente();
            String[] linha = new String[]{
                String.valueOf(texto.get(i).getIdPedido()), funcionario, cliente, sdf.format(texto.get(i).getDataPedido())};
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
        DAOs.DAOPedido daoPedido = new DAOs.DAOPedido();
        new GUIPedidoListagem(daoPedido.listInOrderId());
    }
}
