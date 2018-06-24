package GUIs;

import static com.sun.glass.ui.Cursor.setVisible;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
    JMenuItem cadastroStatusFuncionario = new JMenuItem("Status Funcionário");
    JMenuItem cadastroFuncionario = new JMenuItem("Funcionario");
    JMenuItem cadastroCliente = new JMenuItem("Cliente");
    JMenuItem cadastroMarmita = new JMenuItem("Marmita");

    JMenu menu2 = new JMenu("Listar");
    JMenuItem listaPratoPrincipal = new JMenuItem("Prato Principal");
    JMenuItem listaTamanhoMarmita = new JMenuItem("Tamanho Marmita");
    JMenuItem listaPrecoTamanho = new JMenuItem("Preço Tamanho Marmita");
    JMenuItem listaStatusFuncionario = new JMenuItem("Status Funcionário");
    JMenuItem listaFuncionario = new JMenuItem("Funcionário");
    JMenuItem listaCliente = new JMenuItem("Cliente");
    JMenuItem listaMarmita = new JMenuItem("Marmita");


    ImageIcon img = new ImageIcon("ref.jpg");
    JLabel lbImg = new JLabel(img);

    public GUIMenu() {
        setIcon();
        
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
        menu1.add(cadastroStatusFuncionario);
        menu1.add(cadastroFuncionario);
        menu1.add(cadastroCliente);
        menu1.add(cadastroMarmita);

        barra.add(menu2);
        menu2.add(listaPratoPrincipal);
        menu2.add(listaTamanhoMarmita);
        menu2.add(listaPrecoTamanho);
        menu2.add(listaStatusFuncionario);
        menu2.add(listaFuncionario);
        menu2.add(listaCliente);
        menu2.add(listaMarmita);

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
        
        cadastroStatusFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIStatusFucionario guiStatusFucionario = new GUIStatusFucionario();
            }
        });
        
        cadastroFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIFuncionario guiFuncionario = new GUIFuncionario();
            }
        });
        cadastroCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUICliente guiCliente = new GUICliente();
            }
        });
        cadastroMarmita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIMarmita guiMarmita = new GUIMarmita();
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
        
        listaStatusFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DAOs.DAOStatusFuncionario daoStatusFuncionario = new DAOs.DAOStatusFuncionario();
                GUIStatusFuncionarioListagem guiStatusFuncionarioListagem = new GUIStatusFuncionarioListagem(daoStatusFuncionario.listInOrderId());
            }
        });
        
        listaFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                DAOs.DAOFuncionario daoFuncionario = new DAOs.DAOFuncionario();
                GUIFuncionarioListagem guiFuncionarioListagem = new GUIFuncionarioListagem(daoFuncionario.listInOrderId());
            }
        });
        
        listaCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                DAOs.DAOCliente daoCliente = new DAOs.DAOCliente();
                GUIClienteListagem guiClienteListagem = new GUIClienteListagem(daoCliente.listInOrderId());
            }
        });
        
        listaMarmita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                DAOs.DAOMarmita daoMarmita = new DAOs.DAOMarmita();
                GUIMarmitaListagem guiMarmitaListagem = new GUIMarmitaListagem(daoMarmita.listInOrderId());
            }
        });
        
        setResizable(false);
        setVisible(true);
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
    }
}
