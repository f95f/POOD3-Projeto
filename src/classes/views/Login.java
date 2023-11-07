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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login implements ActionListener{
	
	private JFrame frame;
	
	private JPanel head;
	private JPanel body;
	private JPanel foot;

	private JTextField txtLogin, txtSenha;
	private JButton btnLogin;
	private JLabel title;
	private JLabel errorMessage;
	private JLabel footer;

	private Font accentFont;
	private Font contentFont;
	
	public Login() {
		render();
	}
	
	private void render() {
		listarFontes();
		accentFont = new Font("Verdana", Font.PLAIN, 36);
		contentFont = new Font("Arial", Font.PLAIN, 16);
		
		frame = new JFrame();

		this.frame.setTitle("Identifique-se");
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setSize(300, 450);
		this.frame.setResizable(false);
		this.frame.setLocationRelativeTo(null);
		
		head = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		body = new JPanel(new FlowLayout(FlowLayout.CENTER, 600, 30));
		foot = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		
		head.setBackground(Color.DARK_GRAY);
		body.setBackground(Color.LIGHT_GRAY);
		foot.setBackground(Color.DARK_GRAY);
		
		title = new JLabel("<html><body style='text-align: center; font-size: 12px'><h1 style='font-size: 24px'>Olá!</h1>Identifique-se:</body><html>");
		title.setFont(accentFont);
		title.setForeground(Color.LIGHT_GRAY);
		title.setHorizontalAlignment(JLabel.CENTER);
		head.add(title);
		
		txtLogin = new JTextField(14);
		txtLogin.setToolTipText("Informe o seu nome de usuário.");
		txtLogin.setMargin(new Insets(6, 8, 6, 8));
		txtSenha = new JTextField(14);
		txtSenha.setToolTipText("Informe a sua senha.");
		txtSenha.setMargin(new Insets(6, 8, 6, 8));
		btnLogin = new JButton("Entrar");
		btnLogin.setMargin(new Insets(6, 8, 6, 8));
		btnLogin.setPreferredSize(new Dimension(158, 30));
		body.add(txtLogin);
		body.add(txtSenha);
		body.add(btnLogin);
		
		footer = new JLabel("<html><body style='font-size: 10px'> © 2023 </body></html>");
		footer.setFont(accentFont);
		footer.setForeground(Color.LIGHT_GRAY);
		footer.setHorizontalAlignment(JLabel.CENTER);
		foot.add(footer);
		
		frame.add(head, BorderLayout.NORTH);
		frame.add(body, BorderLayout.CENTER);
		frame.add(foot, BorderLayout.SOUTH);
		this.frame.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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

