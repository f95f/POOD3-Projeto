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
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	private JLabel statusMessage = new JLabel();

	Font accentFont = new Font("Verdana", Font.PLAIN, 22);
	Font contentFont = new Font("Arial", Font.PLAIN, 14);
	
	ArrayList<JTextField> textFields = new ArrayList<JTextField>();
	ArrayList<JComboBox> selectFields = new ArrayList<JComboBox>();
	
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
		
		JButton btnLogout = new JButton("Voltar");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { voltar(); }
		});
		setButtonStyle(btnLogout, corEscura);
		
		head.add(nomeUsuarioLogado, BorderLayout.WEST);
		head.add(btnLogout, BorderLayout.EAST);
		
		JPanel statusRow = new JPanel(new BorderLayout());
		JPanel controlButtonsPannel = new JPanel(new FlowLayout());
		JPanel statusPannel = new JPanel(new FlowLayout());
		
		JButton btnNovoUsuario = new JButton("Adicionar");
		setButtonStyle(btnNovoUsuario, corEscura);
		btnNovoUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { salvarUsuario(); }
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		setButtonStyle(btnCancelar, corMedia);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { voltar(); }
		});
		JPanel form = new JPanel(new GridLayout(0, 3));
		form.setBorder(margem);
		form.setBackground(corClara);
		placeForm(form);
		body.add(form, BorderLayout.NORTH);
		
		statusMessage.setFont(contentFont);
		statusMessage.setBorder(margem);
		statusMessage.setForeground(corClara);
		controlButtonsPannel.setBorder(margem);
		controlButtonsPannel.add(btnNovoUsuario);
		controlButtonsPannel.add(btnCancelar);
		controlButtonsPannel.setBackground(corMedia);
		statusPannel.setBackground(corMedia);
		statusPannel.add(statusMessage);
		statusRow.setBackground(corMedia);
		statusRow.add(controlButtonsPannel, BorderLayout.WEST);
		statusRow.add(statusMessage, BorderLayout.EAST);
		statusRow.add(statusPannel);
		body.add(statusRow, BorderLayout.SOUTH);
				
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
		usuario.setUf(selectFields.get(0).getSelectedItem().toString());
		usuario.setCep(textFields.get(7).getText());
		usuario.setIdNivelUsuario(selectFields.get(1).getSelectedIndex() + 1);
		usuario.setAtivo((selectFields.get(2).getSelectedItem().equals("Ativa"))? "S" : "N");
		
		this.statusMessage.setText("Usuário adicionado por sucesso.");
		
		service.criar(usuario);
	}
	
	private void placeForm(JPanel body) {
		
		String[] estados = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
		String[] niveis = {"Cliente", "Funcionário", "Caixa", "Financeiro", "Gerente", "Diretor", "Administrador"};
		
		selectFields.add(new JComboBox(estados)); 
		selectFields.add(new JComboBox(niveis)); 
		selectFields.add(new JComboBox(new String[]{ "Ativa", "Inativa" })); 
		
		ArrayList<JPanel> inputContainers = new ArrayList<JPanel>();
		String[] labelValues = {"Nome:", "Email:", "Telefone:", "CPF:", "Endereço:", "Bairro:", "Cidade:", "Estado:", "CEP:", "Nível:", "Estado da Conta"};
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
			"Selecione o estado da conta"
		};

		for(int i = 0; i < labelValues.length; i++) {
			
			JLabel label = new JLabel(labelValues[i]);
			label.setFont(contentFont);
			JTextField textField = new JTextField(30);

			if(i == 7) {
				selectFields.get(0).setToolTipText(toolTipValues[i]);
				selectFields.get(0).setBorder(new LineBorder(corEscura, 1));
			}
			else if(i == 9) {
				selectFields.get(1).setToolTipText(toolTipValues[i]);
				selectFields.get(1).setBorder(new LineBorder(corEscura, 1));
			}
			else if(i == 10) {
				selectFields.get(2).setToolTipText(toolTipValues[i]);
				selectFields.get(2).setBorder(new LineBorder(corEscura, 1));
			}
			else {
				textField.setToolTipText(toolTipValues[i]);
				textField.setMargin(new Insets(5, 10, 5, 10));
				textField.setBorder(new LineBorder(corEscura, 1));
				textFields.add(textField);	
			}
			
	        Border internalPadding = BorderFactory.createEmptyBorder(5, 10, 5, 10);
	        Border existingBorder = textField.getBorder();
	        
	        if (existingBorder != null) {
	            textField.setBorder(BorderFactory.createCompoundBorder(existingBorder, internalPadding));
	        } else {
	            textField.setBorder(internalPadding); 
	        }
			
			JPanel container = new JPanel(new BorderLayout());		
			container.add(label, BorderLayout.NORTH);
			if( i == 7 ) {
				container.add(selectFields.get(0), BorderLayout.SOUTH);
			}
			else if( i == 9 ) {
				container.add(selectFields.get(1), BorderLayout.SOUTH);
			}
			else if( i == 10 ) {
				container.add(selectFields.get(2), BorderLayout.SOUTH);
			}
			else {
				container.add(textField, BorderLayout.SOUTH);				
			}
			container.setPreferredSize(formSize);
			container.setBorder(margem);
			container.setBackground(corClara);
			
			body.add(container);
		}
	}
	
	private void voltar() {	
		ListUsers listUsers = new ListUsers(usuarioLogado);
		frame.dispose();
	}
	
	private void setButtonStyle(JButton button, Color cor) {
		
		button.setBackground(cor);
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
                button.setBackground(cor);
            }
        });
		
	}
}
