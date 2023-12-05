package classes.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import classes.models.Usuario;
import classes.services.UsuarioService;
import classes.utils.AuthenticatedUser;

public class TrocarSenha {

	private UsuarioService usuarioService = new UsuarioService();
	private AuthenticatedUser usuarioLogado = new AuthenticatedUser();
	
	private Color corEscura = new Color(31, 71, 102);
	private Color corClara = Color.WHITE;
	private Color corTexto = Color.WHITE;
	private Dimension formSize = new Dimension(180, 70);
	
	private JLabel nomeUsuarioLogado = new JLabel();
	
	private JFrame frame;
	
	private JPanel head;
	private JPanel body;
	private JPanel foot;
	
	private JLabel title;
	private JLabel errorMessage;
	private JLabel footer;
	
	JPasswordField txtSenhaAtual;
	JPasswordField txtSenhaNova;
	JPasswordField txtConfirmarSenha;
	
	private Font accentFont;
	private Font contentFont;
	
	private String email = "", senha = "";
	
	public TrocarSenha(AuthenticatedUser usuario){
		
		this.nomeUsuarioLogado.setText("Olá, " + usuario.getUserName() + "!");
		this.usuarioLogado = usuario;
		render();
		
	}
	
	private void render() {
		accentFont = new Font("Verdana", Font.PLAIN, 36);
		contentFont = new Font("Arial", Font.PLAIN, 16);
		
		frame = new JFrame();

		this.frame.setTitle("Lojinha - Alterar Senha");
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setSize(300, 500);
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
		
		title = new JLabel("Alterar Senha");
		
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(contentFont);
		title.setForeground(corClara);
		head.add(title);

		JButton btnTrocarSenha;
		
		JLabel lblSenhaAtual = new JLabel("Senha atual");
		JLabel lblSenhaNova = new JLabel("Senha nova:");
		JLabel lblConfirmarSenha = new JLabel("Confirme a senha:");
		
		txtSenhaAtual = new JPasswordField(18);
		txtSenhaAtual.setToolTipText("Informe a senha atual.");
		txtSenhaAtual.setMargin(new Insets(5, 10, 5, 10));

		txtSenhaNova = new JPasswordField(18);
		txtSenhaNova.setToolTipText("Informe a senha nova.");
		txtSenhaNova.setMargin(new Insets(5, 10, 5, 10));
		
		txtConfirmarSenha = new JPasswordField(18);
		txtConfirmarSenha.setToolTipText("Confirme a senha.");
		txtConfirmarSenha.setMargin(new Insets(5, 10, 5, 10));
		
		JPanel senhaAtualContainer = new JPanel(new BorderLayout());
		senhaAtualContainer.add(lblSenhaAtual, BorderLayout.NORTH);
		senhaAtualContainer.add(txtSenhaAtual, BorderLayout.SOUTH);
		senhaAtualContainer.setBorder(new EmptyBorder(0, 0, 20, 0));
		senhaAtualContainer.setPreferredSize(formSize);
		senhaAtualContainer.setBackground(corClara);

		JPanel senhaNovaContainer = new JPanel(new BorderLayout());
		senhaNovaContainer.add(lblSenhaNova, BorderLayout.NORTH);
		senhaNovaContainer.add(txtSenhaNova, BorderLayout.SOUTH);
		senhaNovaContainer.setBorder(new EmptyBorder(0, 0, 20, 0));
		senhaNovaContainer.setPreferredSize(formSize);
		senhaNovaContainer.setBackground(corClara);
		
		JPanel confirmarSenhaContainer = new JPanel(new BorderLayout());
		confirmarSenhaContainer.add(lblConfirmarSenha, BorderLayout.NORTH);
		confirmarSenhaContainer.add(txtConfirmarSenha, BorderLayout.SOUTH);
		confirmarSenhaContainer.setBorder(new EmptyBorder(0, 0, 20, 0));
		confirmarSenhaContainer.setPreferredSize(formSize);
		confirmarSenhaContainer.setBackground(corClara);
		
		JPanel buttonContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnTrocarSenha = new JButton("Alterar");
		btnTrocarSenha.setPreferredSize(new Dimension(180, 36));
		btnTrocarSenha.setBackground(corEscura);
		btnTrocarSenha.setForeground(corTexto);
		btnTrocarSenha.setFont(contentFont);
		
		btnTrocarSenha.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnTrocarSenha.setBackground(new Color(8, 13, 99));
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnTrocarSenha.setBackground(corEscura);
            }
        });
		addTrocarSenhaAction(btnTrocarSenha);
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		buttonContainer.add(btnTrocarSenha);
		buttonContainer.add(errorMessage);
		buttonContainer.setPreferredSize(formSize);
		buttonContainer.setBackground(corClara);	
		
		body.add(senhaAtualContainer);
		body.add(senhaNovaContainer);
		body.add(confirmarSenhaContainer);
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

	public void trocarSenha() {
		
		String senhaAtual = txtSenhaAtual.getText();
		String senhaNova = txtSenhaNova.getText();
		String confirmarSenha = txtConfirmarSenha.getText();
		
		UsuarioService usuario = new UsuarioService();
		int status = usuario.trocarSenha(usuarioLogado.getIdUsuario(), senhaAtual, senhaNova, confirmarSenha);
		
		if(status == 0) {
			errorMessage.setText("Erro ao trocar senha.");
		}
		else if(status == 2){
			errorMessage.setText("A senha atual está incorreta.");
		}
		else if(status == 3){
			errorMessage.setText("As senhas não coincidem.");
		}
		else {
			errorMessage.setText("");
			JOptionPane.showMessageDialog(
				null,
				"Senha Alterada.",
				"Alterar Senha", 
				JOptionPane.INFORMATION_MESSAGE 
			);
			Painel painel = new Painel(usuarioLogado);
			this.frame.dispose();
		}		
	}
	
	public void addTrocarSenhaAction(JButton button) {	
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				trocarSenha();
				
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
