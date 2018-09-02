package Main;

import DAOs.DAOPedidoHasMarmita;
import DAOs.DAOPedido;
import DAOs.DAOMarmita;
import Entidades.PedidoHasMarmita;
import Entidades.PedidoHasMarmitaPK;
import Entidades.Pedido;
import Entidades.Marmita;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import myUtil.StringTools;

/**
 * @author Radames J Halmeman - rjhalmeman@gmail.com
 */
public class AdicionarItemPedido { //pedidoHasMarmita

    public static void main(String[] args) {
        Pedido pedido = new Pedido();
        DAOPedido daoPedido = new DAOPedido();

        Marmita marmita = new Marmita();
        DAOMarmita daoMarmita = new DAOMarmita();

        PedidoHasMarmita pedidoHasMarmita = new PedidoHasMarmita();
        PedidoHasMarmitaPK pedidoHasMarmitaPK = new PedidoHasMarmitaPK();

        DAOPedidoHasMarmita daoPedidoHasMarmita = new DAOPedidoHasMarmita();

        /*
        O quero fazer? Adicionar 2 marmitas em 1 pedido.
         */
        //localizar o pedido
        pedido = daoPedido.obter(1);
        if (pedido != null) {
            System.out.println("Pedido: " + pedido.getIdPedido()
                    + "\nCliente:" + pedido.getClienteIdCliente().getNomeCliente()
                    + "\nData:" + new SimpleDateFormat("dd/MM/yyyy").format(pedido.getDataPedido())
            );
        }

        double valorEtiqueta = 0;
        //obter o primeiro marmita
        marmita = daoMarmita.obter(1);
        if (marmita != null) {
            System.out.println("Produto: " + marmita.getIdMarmita()
                    + "\nPrato Principal:" + marmita.getPratoPrincipalIdPratoPrincipal().getNomePratoPrincipal()
                    + "\nTamanho:" + marmita.getTamanhoMarmitaIdTamanhoMarmita().getNomeTamanhoMarmita()
                    + "\nStatus:" + marmita.getStatus()
                    + "\nValor:" + marmita.getTamanhoMarmitaIdTamanhoMarmita().getPrecoProdutoList().get(marmita.getTamanhoMarmitaIdTamanhoMarmita().getPrecoProdutoList().size() - 1).getPreco()
            );
            valorEtiqueta = marmita.getTamanhoMarmitaIdTamanhoMarmita().getPrecoProdutoList().get(marmita.getTamanhoMarmitaIdTamanhoMarmita().getPrecoProdutoList().size() - 1).getPreco();
        }

        //tenho o pedido e tenho o marmita
        pedidoHasMarmitaPK = new PedidoHasMarmitaPK(pedido.getIdPedido(), marmita.getIdMarmita());
        pedidoHasMarmita = daoPedidoHasMarmita.obter(pedidoHasMarmitaPK);//procura se o item do pedido já foi adicionado anteriormente

        double valorUnitario = 8.25;
        int quantidade = 2;
        pedidoHasMarmita = new PedidoHasMarmita(pedidoHasMarmitaPK, quantidade, valorUnitario);
        pedidoHasMarmita.setDesconto(valorEtiqueta - valorUnitario);

        if (pedidoHasMarmita == null) {//não está na tabela de itens
            daoPedidoHasMarmita.inserir(pedidoHasMarmita);
        } else { // já está na tabela, então atualizar
            daoPedidoHasMarmita.atualizar(pedidoHasMarmita);
        }

        //repetir o processo para o próximo marmita
        valorEtiqueta = 0;
        //obter o segundo marmita
        marmita = daoMarmita.obter(2);
        if (marmita != null) {
            System.out.println("Produto: " + marmita.getIdMarmita()
                    + "\nPrato Principal:" + marmita.getPratoPrincipalIdPratoPrincipal().getNomePratoPrincipal()
                    + "\nTamanho:" + marmita.getTamanhoMarmitaIdTamanhoMarmita().getNomeTamanhoMarmita()
                    + "\nStatus:" + marmita.getStatus()
                    + "\nValor:" + marmita.getTamanhoMarmitaIdTamanhoMarmita().getPrecoProdutoList().get(marmita.getTamanhoMarmitaIdTamanhoMarmita().getPrecoProdutoList().size() - 1).getPreco()//ultimo preço cadastrado
            );
            valorEtiqueta = marmita.getTamanhoMarmitaIdTamanhoMarmita().getPrecoProdutoList().get(marmita.getTamanhoMarmitaIdTamanhoMarmita().getPrecoProdutoList().size() - 1).getPreco();
        }

        //tenho o pedido e tenho o marmita
        pedidoHasMarmitaPK = new PedidoHasMarmitaPK(pedido.getIdPedido(), marmita.getIdMarmita());
        pedidoHasMarmita = daoPedidoHasMarmita.obter(pedidoHasMarmitaPK);//procura se o item do pedido já foi adicionado anteriormente

        valorUnitario = 14.90;
        quantidade = 2;
        pedidoHasMarmita = new PedidoHasMarmita(pedidoHasMarmitaPK, quantidade, valorUnitario);
        pedidoHasMarmita.setDesconto(valorEtiqueta - valorUnitario);

        if (pedidoHasMarmita == null) {//não está na tabela de itens
            daoPedidoHasMarmita.inserir(pedidoHasMarmita);
        } else { // já está na tabela, então atualizar
            daoPedidoHasMarmita.atualizar(pedidoHasMarmita);
        }

        //Listar o pedido e calcular o total
        pedido = daoPedido.obter(1);//obter o pedido já atualizado com os itens

        System.out.println("----------------------------------\n\n"
                + "Pedido: " + pedido.getIdPedido()
                + "\nCliente:" + pedido.getClienteIdCliente().getNomeCliente()
                + "\nData:" + new SimpleDateFormat("dd/MM/yyyy").format(pedido.getDataPedido())
        );
        int idPedido = pedido.getIdPedido();
        StringTools st = new StringTools();
        System.out.println("Itens");
        System.out.print(st.centralizaString("idPedido", 15));
        System.out.print(st.centralizaString("idProduto", 15));
        System.out.print(st.ajustaLargura("nomeProduto", 40));
        System.out.print(st.centralizaString("Quant", 10));
        System.out.print(st.centralizaString("valorUni", 10));
        System.out.print(st.centralizaString("desconto", 10));
        System.out.println(st.alinhaDireita("subtotal", 10));

        List<PedidoHasMarmita> listaItens = daoPedidoHasMarmita.listById(idPedido);
        double total = 0;
        for (PedidoHasMarmita item : listaItens) {
            double subTotal = item.getQtde() * item.getValor();
            System.out.print(st.centralizaString(String.valueOf(item.getPedidoHasMarmitaPK().getPedidoIdPedido()), 15));
            System.out.print(st.centralizaString(String.valueOf(item.getPedidoHasMarmitaPK().getMarmitaIdMarmita()), 15));
            System.out.print(st.ajustaLargura(daoMarmita.obter(item.getPedidoHasMarmitaPK().getMarmitaIdMarmita()).getTamanhoMarmitaIdTamanhoMarmita().getNomeTamanhoMarmita() + " - " + daoMarmita.obter(item.getPedidoHasMarmitaPK().getMarmitaIdMarmita()).getPratoPrincipalIdPratoPrincipal().getNomePratoPrincipal(), 40));
            System.out.print(st.centralizaString(String.valueOf(item.getQtde()), 10));
            System.out.print(st.centralizaString(String.valueOf(new DecimalFormat("###,###,##0.00").format(item.getValor())), 10));
            System.out.print(st.centralizaString(new DecimalFormat("###,###,##0.00").format(item.getDesconto()), 10));
            System.out.println(st.alinhaDireita(String.valueOf(new DecimalFormat("###,###,##0.00").format(subTotal)), 10));
            total += subTotal;
        }
        System.out.println(st.alinhaDireita("-----------", 110));
        System.out.println(st.alinhaDireita("Total " + new DecimalFormat("###,###,##0.00").format(total), 110));

    }

}
