package classes.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import classes.utils.AuthenticatedUser;
import classes.views.pedidos.CriarPedido;
import classes.views.pedidos.ListPedidos;
import classes.views.produtos.CreateProduto;
import classes.views.produtos.ListProdutos;
import classes.views.usuarios.CreateUser;
import classes.views.usuarios.ListUsers;

public class Painel {
	
	private JFrame frame;
	private JLabel nomeUsuarioLogado = new JLabel();
	private EmptyBorder margem = new EmptyBorder(16, 24, 16, 24);
	private Color corEscura = new Color(31, 71, 102);
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

		JPanel menuItems = new JPanel(new GridLayout(0, 3));
		menuItems.setBorder(new EmptyBorder(8, 8, 8, 8));
		menuItems.setBackground(new Color(0, 200, 255, 50));
		
		JLabel menuLabel = new JLabel("Menu Principal                                       ");
		menuLabel.setForeground(corEscura);
		menuLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
		menuLabel.setFont(accentFont);
		
		criarMenu(menuItems);
		body.add(menuLabel);
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

		ArrayList<JButton> buttonList = new ArrayList<JButton>();
		
		JButton novoPedidoButton = new JButton("Registrar Pedido");
		novoPedidoButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {

				CriarPedido criarPedidos= new CriarPedido(usuarioLogado);
				frame.dispose();
				
			}
		});
		buttonList.add(novoPedidoButton);
		
		JButton verPedidoButton = new JButton("Verificar Pedidos");
		verPedidoButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				ListPedidos listPedidos = new ListPedidos(usuarioLogado);
				frame.dispose();
				
			}
		});
		buttonList.add(verPedidoButton);
		
		JButton novoProdutoButton = new JButton("Novo Produto");
		novoProdutoButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				
				CreateProduto criarProdutos = new CreateProduto(usuarioLogado);
				frame.dispose();
				
			}
		});
		buttonList.add(novoProdutoButton);
		
		JButton listProdutoButton = new JButton("Buscar Produtos");
		listProdutoButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ListProdutos listProdutos = new ListProdutos(usuarioLogado);
				frame.dispose();
				
			}
		});
		buttonList.add(listProdutoButton);
		
		JButton verEstoqueButton = new JButton("Verificar Estoque");
		verEstoqueButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ListUsers listarUsuarios = new ListUsers(usuarioLogado);
				frame.dispose();
				
			}
		});
		buttonList.add(verEstoqueButton);
		
		JButton categoriasButton = new JButton("Categorias");
		categoriasButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				ListCategorias listCategorias = new ListCategorias(usuarioLogado);
				frame.dispose();
				
			}
		});
		buttonList.add(categoriasButton);
		
		JButton newUserButton = new JButton("Novo Usuário");
		newUserButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!usuarioTemAcesso()) { return; }
				CreateUser criarUsuarios = new CreateUser(usuarioLogado);
				frame.dispose();
				
			}
		});
		buttonList.add(newUserButton);
		
		JButton listUserButton = new JButton("Listar Usuários");
		listUserButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!usuarioTemAcesso()) { return; }
				ListUsers listUserButton = new ListUsers(usuarioLogado);
				frame.dispose();
				
			}
		});
		buttonList.add(listUserButton);
		
		JButton trocarSenhaButton = new JButton("Trocar Senha");
		trocarSenhaButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				
				TrocarSenha trocarSenha = new TrocarSenha(usuarioLogado);
				frame.dispose();
				
			}
		});
		buttonList.add(trocarSenhaButton);
		
		for(int i = 0; i < buttonList.size(); i++) {
			
			JButton currentButton = buttonList.get(i);
			setMenuItemStyle(currentButton);

			JPanel buttonPanel = new JPanel(new BorderLayout());
			buttonPanel.add(currentButton, BorderLayout.CENTER);
			buttonPanel.setBackground(null);
			buttonPanel.setOpaque(false);
			buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

			menuContainer.add(buttonPanel);
		}
	}
	
	private boolean usuarioTemAcesso(){
		
		if(usuarioLogado.getIdNivelUsuario() > 1) {
			return true;
		}
		JOptionPane.showMessageDialog(
			null,
			"Você não tem permissão para realizar esta ação.",
			"Sem Autorização", 
			JOptionPane.OK_OPTION
		);
		return false;
	}
	
	private void setMenuItemStyle(JButton button){
		
		button.setBackground(corEscura);
		button.setForeground(corTexto);
		button.setFont(contentFont);
		button.setPreferredSize(new Dimension(150, 90));
		button.setFocusable(false);
		button.setVerticalTextPosition(SwingConstants.CENTER);
		button.setHorizontalTextPosition(SwingConstants.CENTER);

		button.addMouseListener(new MouseAdapter() {
		
            @Override
            public void mouseEntered(MouseEvent e) {
            	button.setBackground(new Color(8, 13, 99));
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
		button.setPreferredSize(new Dimension(100, 32));
		button.setFocusable(false);
		button.setBorder(new LineBorder(corClara));
		
		ImageIcon logoutIcon = new ImageIcon("icon.png");
		
		button.addMouseListener(new MouseAdapter() {
		
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(8, 13, 99));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(corEscura);
            }
        });
		
	}
}
