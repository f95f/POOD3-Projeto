package classes.views.usuarios;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import classes.models.Usuario;
import classes.services.UsuarioService;
import classes.utils.AuthenticatedUser;

public class CreateUser {
	
	private JFrame frame;
	private JLabel nomeUsuarioLogado = new JLabel();
	private Insets padding = new Insets(5, 10, 5, 10);
	private EmptyBorder margem = new EmptyBorder(16, 24, 16, 24);
	private Color corEscura = new Color(31, 71, 102);
	private Color corClara = Color.WHITE;
	private Color corMedia = new Color(131, 152, 168);
	private Color corTexto = Color.WHITE;
	private AuthenticatedUser usuarioLogado;
	private Dimension formSize = new Dimension(300, 80);
	
	Font accentFont = new Font("Verdana", Font.PLAIN, 22);
	Font contentFont = new Font("Arial", Font.PLAIN, 14);
	
	ArrayList<JTextField> textFields = new ArrayList<JTextField>();
	
	public CreateUser(AuthenticatedUser usuario){
		
		this.nomeUsuarioLogado.setText("Cadastrar Usuários");
		this.usuarioLogado = usuario;
		render();
		
	}
	private void render() {

		frame = new JFrame();

		this.frame.setTitle("Lojinha - Adicionar Usuários");
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setSize(800, 600);
		this.frame.setResizable(false);
		this.frame.setLocationRelativeTo(null);
		
		JPanel head = new JPanel(new BorderLayout());
		JPanel body = new JPanel(new BorderLayout());
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
				
		JPanel controlButtonsPannel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton btnNovoUsuario = new JButton("Novo");
		btnNovoUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { salvarUsuario(); }
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { goBack(); }
		});
		
		JPanel form = new JPanel(new GridLayout(0, 3));
		form.setBorder(margem);
		form.setBackground(corClara);
		placeForm(form);
		body.add(form, BorderLayout.NORTH);
		
		controlButtonsPannel.setBorder(margem);
		controlButtonsPannel.add(btnNovoUsuario);
		controlButtonsPannel.add(btnCancelar);
		controlButtonsPannel.setBackground(corMedia);
		body.add(controlButtonsPannel, BorderLayout.SOUTH);
				
		JLabel footer = new JLabel("<html><body style='font-size: 10px'> © 2023 </body></html>");
		footer.setFont(accentFont);
		footer.setForeground(corTexto);
		footer.setHorizontalAlignment(JLabel.CENTER);
		foot.add(footer);
		
		head.setBorder(margem);
		foot.setBorder(margem);
		
		frame.add(head, BorderLayout.NORTH);
		frame.add(body, BorderLayout.CENTER);
		frame.add(foot, BorderLayout.SOUTH);
		
		this.frame.setVisible(true);
		
	}

	private void salvarUsuario() {
		
		Usuario usuario = new Usuario();
		UsuarioService service = new UsuarioService();
		
		usuario.setNome(textFields.get(0).getText());
		usuario.setEmail(textFields.get(1).getText());
		usuario.setTelefone(textFields.get(2).getText());
		usuario.setCpf(textFields.get(3).getText());
		usuario.setEndereco(textFields.get(4).getText());
		usuario.setBairro(textFields.get(5).getText());
		usuario.setCidade(textFields.get(6).getText());
		usuario.setUf(textFields.get(7).getText());
		usuario.setCep(textFields.get(8).getText());
		usuario.setIdNivelUsuario(Integer.parseInt(textFields.get(9).getText()));
		usuario.setAtivo(textFields.get(0).getText());
		
		service.criar(usuario);
		
	}
	
	private void placeForm(JPanel body) {
		
		ArrayList<JPanel> inputContainers = new ArrayList<JPanel>();
		String[] labelValues = {"Nome:", "Email:", "Telefone:", "CPF:", "Endereço:", "Bairro:", "Cidade:", "Estado:", "CEP:", "Nível:", "Ativar Conta?"};
		String[] toolTipValues = {
			"Informe o nome",
			"Informe o email",
			"Informe o telefone",
			"Informe o CPF",
			"Informe a rua e o número",
			"Informe o bairro",
			"Informe a cidade",
			"Selecione o estado",
			"Informe o CEP",
			"Selecione um Nível de acesso",
			"Selecione para ativar a conta"
		};

		for(int i = 0; i < labelValues.length; i++) {
			
			JLabel label = new JLabel(labelValues[i]);
			label.setFont(contentFont);
			JTextField textField = new JTextField(30);
			textField.setToolTipText(toolTipValues[i]);
			textField.setMargin(new Insets(5, 10, 5, 10));
			textField.setBorder(new LineBorder(corEscura, 1));
			textFields.add(textField);
			
	        Border internalPadding = BorderFactory.createEmptyBorder(5, 10, 5, 10);
	        Border existingBorder = textField.getBorder();
	        
	        if (existingBorder != null) {
	            textField.setBorder(BorderFactory.createCompoundBorder(existingBorder, internalPadding));
	        } else {
	            textField.setBorder(internalPadding); 
	        }
			
			JPanel container = new JPanel(new BorderLayout());		
			container.add(label, BorderLayout.NORTH);
			container.add(textField, BorderLayout.SOUTH);
			container.setPreferredSize(formSize);
			container.setBorder(margem);
			container.setBackground(corClara);
			
			body.add(container);
		}
		
	}
	
	private void goBack(){
		
		ListUsers listUsers = new ListUsers(usuarioLogado);
		frame.dispose();

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
