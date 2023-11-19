package classes.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import classes.models.Usuario;
import classes.services.UsuarioService;
import classes.utils.AuthenticatedUser;

public class Login{
	
	private UsuarioService usuarioService = new UsuarioService();
	private Usuario usuario = new Usuario();
	private AuthenticatedUser usuarioLogado = new AuthenticatedUser();
	
	private Color corEscura = new Color(31, 71, 102);
	private Color corClara = Color.WHITE;
	private Color corTexto = Color.WHITE;
	private Dimension formSize = new Dimension(180, 70);
	
	private JFrame frame;
	
	private JPanel head;
	private JPanel body;
	private JPanel foot;

	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JButton btnLogin;
	
	private JLabel lblEmail;
	private JLabel lblSenha;
	
	private JLabel title;
	private JLabel errorMessage;
	private JLabel footer;

	private Font accentFont;
	private Font contentFont;
	
	private String email = "", senha = "";
	
	public Login() {
		render();
		
	}
	
	private void render() {
		//listarFontes();
		accentFont = new Font("Verdana", Font.PLAIN, 36);
		contentFont = new Font("Arial", Font.PLAIN, 16);
		
		frame = new JFrame();

		this.frame.setTitle("Lojinha - Entrar");
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setSize(300, 470);
		this.frame.setResizable(false);
		this.frame.setLocationRelativeTo(null);
		
		head = new JPanel(new FlowLayout());
		body = new JPanel(new FlowLayout(FlowLayout.CENTER));
		foot = new JPanel(new FlowLayout());
		
		head.setBackground(corEscura);
		body.setBackground(corClara);
		foot.setBackground(corEscura);
		
		head.setBorder(new EmptyBorder(10, 0, 20, 0));
		body.setBorder(new EmptyBorder(20, 0, 0, 0));
		foot.setBorder(new EmptyBorder(10, 0, 10, 0));
		
		
		title = new JLabel(
				"<html>"
				+ "<body style='text-align:center; font-size: 16px'>"
				+ 	"<h1 style='font-size: 24px'> Olá!</h1>"
				+ 	"Identifique-se:"
				+ "</body>"
		  	  + "<html>"
		);
		
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(accentFont);
		title.setForeground(corClara);
		head.add(title);
		
		lblEmail = new JLabel("Email:");
		lblSenha = new JLabel("Senha:");
		
		txtLogin = new JTextField(18);
		txtLogin.setToolTipText("Informe o seu nome de usuário.");
		txtLogin.setMargin(new Insets(5, 10, 5, 10));

		JPanel emailContainer = new JPanel(new BorderLayout());
		emailContainer.add(lblEmail, BorderLayout.NORTH);
		emailContainer.add(txtLogin, BorderLayout.SOUTH);
		emailContainer.setBorder(new EmptyBorder(0, 0, 20, 0));
		emailContainer.setPreferredSize(formSize);
		emailContainer.setBackground(corClara);
		
		txtSenha = new JPasswordField(18);
		txtSenha.setToolTipText("Informe a sua senha.");
		txtSenha.setMargin(new Insets(5, 10, 5, 10));

		JPanel senhaContainer = new JPanel(new BorderLayout());
		senhaContainer.add(lblSenha, BorderLayout.NORTH);
		senhaContainer.add(txtSenha, BorderLayout.SOUTH);
		senhaContainer.setBorder(new EmptyBorder(0, 0, 20, 0));
		senhaContainer.setPreferredSize(formSize);
		senhaContainer.setBackground(corClara);
		
		JPanel buttonContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnLogin = new JButton("Entrar");
		btnLogin.setPreferredSize(new Dimension(180, 36));
		btnLogin.setBackground(corEscura);
		btnLogin.setForeground(corTexto);
		btnLogin.setFont(contentFont);
		btnLogin.addMouseListener(new MouseAdapter() {
		
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnLogin.setBackground(new Color(8, 13, 99));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnLogin.setBackground(corEscura);
            }
            
        });
		addLoginAction(btnLogin);
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		buttonContainer.add(btnLogin);
		buttonContainer.add(errorMessage);
		buttonContainer.setPreferredSize(formSize);
		buttonContainer.setBackground(corClara);	
		
		body.add(emailContainer);
		body.add(senhaContainer);
		body.add(buttonContainer);
		body.setForeground(Color.LIGHT_GRAY);
		
		footer = new JLabel("<html><body style='font-size: 10px'> © 2023 </body></html>");
		footer.setFont(accentFont);
		footer.setForeground(corClara);
		footer.setHorizontalAlignment(JLabel.CENTER);
		foot.add(footer);
		
		frame.add(head, BorderLayout.NORTH);
		frame.add(body, BorderLayout.CENTER);
		frame.add(foot, BorderLayout.SOUTH);
		this.frame.setVisible(true);
		
	}

	public void login() {

		email = txtLogin.getText();
		senha = txtSenha.getText();
		usuario = usuarioService.login(email, senha);
		
		if(usuario == null) {
			errorMessage.setText("Usuário ou senha incorretos.");
			
		}
		else {
			errorMessage.setText("Usuário " + usuario.getNome() + " autenticado com sucesso.");
			
			usuarioLogado.setUserName(usuario.getNome());
			usuarioLogado.setUserLogin(usuario.getIdUsuario());
			usuarioLogado.setIdNivelUsuario(usuario.getIdNivelUsuario());
			usuarioLogado.setToken("1");
			
			Painel painel = new Painel(usuarioLogado);
			this.frame.dispose();
		}		
	}
	
	public void addLoginAction(JButton button) {	
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				login();
				
			}
		});		
	}
	
	private void listarFontes() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();

        System.out.println("Available Fonts:");
        for (String fontName : fontNames) {
            System.out.println(fontName);
        }
	}
	
}

