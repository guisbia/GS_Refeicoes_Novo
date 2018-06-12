package GUIs;

import static com.sun.glass.ui.Cursor.setVisible;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * @author Bianca
 */
public class GUIMenu extends JFrame {

    Container cp;

    JMenuBar barra = new JMenuBar();

    JMenu menu1 = new JMenu("Cadastrar");
    JMenuItem cadastroPratoPrincipal = new JMenuItem("Prato Principal");
    JMenuItem cadastroTamanhoMarmita = new JMenuItem("Tamanho Marmita");
    JMenuItem cadastroPrecoTamanho = new JMenuItem("Preço Tamanho Marmita");
    JMenuItem item4Menu1 = new JMenuItem("x");
    JMenuItem item5Menu1 = new JMenuItem("x");
    JMenuItem item6Menu1 = new JMenuItem("x");
    //JMenuItem itemSistemaMenu1 = new JMenuItem("Sistema Operacional");

    JMenu menu2 = new JMenu("Listar");
    JMenuItem listaPratoPrincipal = new JMenuItem("Prato Principal");
    JMenuItem listaTamanhoMarmita = new JMenuItem("Tamanho Marmita");
    JMenuItem listaPrecoTamanho = new JMenuItem("Preço Tamanho Marmita");

    JMenu menu3 = new JMenu("Sair");
    JMenuItem itemSimMenu3 = new JMenuItem("Sim");
    JMenuItem itemNãoMenu3 = new JMenuItem("Não");

    ImageIcon img = new ImageIcon("ref.jpg");
    JLabel lbImg = new JLabel(img);

    public GUIMenu() {
        setTitle("G.S. Refeições");
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new FlowLayout());
        setJMenuBar(barra);
        barra.add(menu1);
        menu1.add(cadastroPratoPrincipal);
        menu1.add(cadastroTamanhoMarmita);
        menu1.add(cadastroPrecoTamanho);
        menu1.add(item4Menu1);
        menu1.add(item5Menu1);
        menu1.add(item6Menu1);

        barra.add(menu2);
        menu2.add(listaPratoPrincipal);
        menu2.add(listaTamanhoMarmita);
        menu2.add(listaPrecoTamanho);

        barra.add(menu3);
        menu3.add(itemSimMenu3);
        menu3.add(itemNãoMenu3);

        cp.add(lbImg);

        cadastroPratoPrincipal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIPratoPrincipal guiPratoPrincipal = new GUIPratoPrincipal();
            }
        });
        
        cadastroTamanhoMarmita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUITamanhoMarmita guiTamanhoMarmita = new GUITamanhoMarmita();
            }
        });
        
        cadastroPrecoTamanho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIPrecoProdutoPK guiPrecoProdutoPK = new GUIPrecoProdutoPK();
            }
        });
        
        listaPratoPrincipal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DAOs.DAOPratoPrincipal daoPratoPrincipal = new DAOs.DAOPratoPrincipal();
                GUIPratoPrincipalListagem guiPratoPrincipalListagem = new GUIPratoPrincipalListagem(daoPratoPrincipal.listInOrderId());
            }
        });

        listaTamanhoMarmita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DAOs.DAOTamanhoMarmita daoTamanhoMarmita = new DAOs.DAOTamanhoMarmita();
                 GUITamanhoMarmitaListagem guiTamanhoMarmitaListagem = new GUITamanhoMarmitaListagem(daoTamanhoMarmita.listInOrderId());
            }
        });
        
        listaPrecoTamanho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DAOs.DAOPrecoProduto daoPrecoProduto = new DAOs.DAOPrecoProduto();
                 GUIPrecoProdutoPKListagem guiPrecoProdutoPKListagem = new GUIPrecoProdutoPKListagem(daoPrecoProduto.listInOrderId());
            }
        });
        
        itemSimMenu3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        setResizable(false);
        setVisible(true);
    }
}
