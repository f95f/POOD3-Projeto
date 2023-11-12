package classes.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import classes.utils.AuthenticatedUser;

public class Painel {
	
	private JFrame frame;
	private JLabel nomeUsuarioLogado = new JLabel();
	private EmptyBorder margem = new EmptyBorder(16, 24, 16, 24);
//	private Color corEscura = Color.BLUE;
	private Color corEscura = new Color(20, 63, 181);
	private Color corClara = Color.WHITE;
	private Color corTexto = Color.WHITE;
	private AuthenticatedUser usuarioLogado;
	
	Font accentFont = new Font("Verdana", Font.PLAIN, 22);
	Font contentFont = new Font("Arial", Font.PLAIN, 14);
	
	public Painel(AuthenticatedUser usuario){
		
		this.nomeUsuarioLogado.setText("Olá, " + usuario.getUserName() + "!");
		this.usuarioLogado = usuario;
		render();
		
	}
	
	private void render() {

		frame = new JFrame();

		this.frame.setTitle("Lojinha - Painel Principal");
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setSize(800, 600);
		this.frame.setResizable(false);
		this.frame.setLocationRelativeTo(null);
		
		JPanel head = new JPanel(new BorderLayout());
		JPanel body = new JPanel(new FlowLayout());
		JPanel foot = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		head.setBackground(corEscura);
		body.setBackground(corClara);
		foot.setBackground(corEscura);
		
		nomeUsuarioLogado.setFont(accentFont);
		nomeUsuarioLogado.setForeground(corTexto);
		
		JButton btnLogout = new JButton("Sair");
		setButtonStyle(btnLogout);
		addLogoutAction(btnLogout);
		
		head.add(nomeUsuarioLogado, BorderLayout.WEST);
		head.add(btnLogout, BorderLayout.EAST);

		JPanel menuItems = new JPanel(new FlowLayout(FlowLayout.CENTER));
		//menuItems.setBorder(new EmptyBorder(8, 8, 8, 8));
		//menuItems.setBackground(new Color(0, 200, 255, 50));
		
		criarMenu(menuItems);
		body.add(menuItems);
		
		JLabel footer = new JLabel("<html><body style='font-size: 10px'> © 2023 </body></html>");
		footer.setFont(accentFont);
		footer.setForeground(corTexto);
		footer.setHorizontalAlignment(JLabel.CENTER);
		foot.add(footer);
		
		head.setBorder(margem);
		body.setBorder(margem);
		foot.setBorder(margem);
		
		frame.add(head, BorderLayout.NORTH);
		frame.add(body, BorderLayout.CENTER);
		frame.add(foot, BorderLayout.SOUTH);
		
		this.frame.setVisible(true);
		
	}
	
	private void criarMenu(JPanel menuContainer) {
		
		JPanel pedidoMenu = new JPanel(new BorderLayout());
		JLabel pedidoMenuLabel = new JLabel("Pedidos");
		pedidoMenuLabel.setFont(accentFont);
		
		JButton btnNovoPedido = new JButton("Novo Pedido");
		JButton btnVerificarPedidos = new JButton("Verificar Pedidos");
		
		setMenuItemStyle(btnNovoPedido);
		setMenuItemStyle(btnVerificarPedidos);
		
		pedidoMenu.add(pedidoMenuLabel, BorderLayout.NORTH);
		pedidoMenu.add(btnNovoPedido);
		pedidoMenu.add(btnVerificarPedidos, BorderLayout.SOUTH);
		
		JPanel produtoMenu = new JPanel(new BorderLayout());
		JLabel produtoMenuLabel = new JLabel("Produtos");
		produtoMenuLabel.setFont(accentFont);
		
		JButton btnNovoProduto = new JButton("Novo Produto");
		JButton btnVerificarProdutos = new JButton("Buscar Produtos");
		JButton btnVerificarEstoque = new JButton("Verificar Estoque");
		JButton btnCategorias = new JButton("Categorias");
		
		setMenuItemStyle(btnNovoProduto);
		setMenuItemStyle(btnVerificarProdutos);
		setMenuItemStyle(btnVerificarEstoque);
		setMenuItemStyle(btnCategorias);
		
		produtoMenu.add(produtoMenuLabel, BorderLayout.NORTH);
		produtoMenu.add(btnNovoProduto);
		produtoMenu.add(btnVerificarProdutos);
		produtoMenu.add(btnVerificarEstoque);
		produtoMenu.add(btnCategorias, BorderLayout.SOUTH);
		
		JPanel usuariosMenu = new JPanel(new BorderLayout());
		JLabel usuariosMenuLabel = new JLabel("Usuarios");
		usuariosMenuLabel.setFont(accentFont);
		
		JButton btnNovoUsuario = new JButton("Novo Usuário");
		JButton btnListarUsuarios = new JButton("Listar Usuários");
		
		setMenuItemStyle(btnNovoUsuario);
		setMenuItemStyle(btnListarUsuarios);
		
		usuariosMenu.add(usuariosMenuLabel, BorderLayout.NORTH);
		usuariosMenu.add(btnNovoUsuario);
		usuariosMenu.add(btnListarUsuarios, BorderLayout.SOUTH);
		
		JPanel institucionalMenu = new JPanel(new BorderLayout());
		JLabel institucionalMenuLabel = new JLabel("Institucional");
		institucionalMenuLabel.setFont(accentFont);
		
		JButton btnVer = new JButton("Ver");
		
		setMenuItemStyle(btnVer);
		institucionalMenu.add(institucionalMenuLabel, BorderLayout.NORTH);
		institucionalMenu.add(btnVer, BorderLayout.SOUTH);
		
		menuContainer.add(pedidoMenu);
		menuContainer.add(produtoMenu);
		menuContainer.add(usuariosMenu);
		menuContainer.add(institucionalMenu);
	
	}
	
	private void setMenuItemStyle(JButton button){
		
		button.setBackground(corEscura);
		button.setForeground(corTexto);
		button.setFont(contentFont);
		//button.setMnemonic(KeyEvent.VK_S);
		button.setPreferredSize(new Dimension(150, 30));
		button.setFocusable(false);
		button.setVerticalTextPosition(SwingConstants.CENTER);
		button.setHorizontalTextPosition(SwingConstants.CENTER);


		//ImageIcon logoutIcon = new ImageIcon("icon.png");
		
		button.addMouseListener(new MouseAdapter() {
		
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(0,0,0, 100));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(corEscura);
            }
        });
		
	}
	
	private void addLogoutAction(JButton button) {	
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				usuarioLogado = null;
				frame.dispose();
					
			}
		});		
	}
	
	private void setButtonStyle(JButton button) {
		
		button.setBackground(corEscura);
		button.setForeground(corTexto);
		button.setFont(contentFont);
		button.setMnemonic(KeyEvent.VK_S);
		button.setPreferredSize(new Dimension(100, 36));
		button.setFocusable(false);
		button.setBorder(new LineBorder(corClara));

		ImageIcon logoutIcon = new ImageIcon("icon.png");
		
		button.addMouseListener(new MouseAdapter() {
		
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(0,0,0, 100));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(corEscura);
            }
        });
		
	}
}
