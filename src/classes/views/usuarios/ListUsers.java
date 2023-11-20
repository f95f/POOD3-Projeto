package classes.views.usuarios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import classes.models.Usuario;
import classes.services.UsuarioService;
import classes.utils.AuthenticatedUser;

public class ListUsers {
	private JFrame frame;
	private JLabel nomeUsuarioLogado = new JLabel();
	private EmptyBorder margem = new EmptyBorder(16, 24, 16, 24);
	private Color corEscura = new Color(31, 71, 102);
	private Color corMedia = new Color(131, 152, 168);
	private Color corClara = Color.WHITE;
	private Color corTexto = Color.WHITE;
	private AuthenticatedUser usuarioLogado;
	
	Font accentFont = new Font("Verdana", Font.PLAIN, 22);
	Font contentFont = new Font("Arial", Font.PLAIN, 14);
	
	public ListUsers(AuthenticatedUser usuario){
		
		this.nomeUsuarioLogado.setText("Olá, " + usuario.getUserName() + "!");
		this.usuarioLogado = usuario;
		render();
		
	}
	
	private void render() {

		frame = new JFrame();

		this.frame.setTitle("Lojinha - Listar Usuários");
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setSize(840, 690);
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
		JButton btnEditarUsuario = new JButton("Editar");
		JButton btnExcluirUsuario = new JButton("Excluir");
		
		addCreateUserAction(btnNovoUsuario);
		
		controlButtonsPannel.setBorder(margem);
		controlButtonsPannel.add(btnNovoUsuario);
		controlButtonsPannel.add(btnEditarUsuario);
		controlButtonsPannel.add(btnExcluirUsuario);
		controlButtonsPannel.setBackground(corMedia);
		body.add(controlButtonsPannel, BorderLayout.NORTH);
		
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);

		Object[] columns = {"ID", "Nome", "Email", "Nível", "Telefone", "Endereço", "Conta"};
		ArrayList<Usuario> userList  = getUsersList();

		model.setColumnIdentifiers(columns);
		for (Usuario user : userList) {
		    Object[] row = {
	    		user.getIdUsuario(),
	    		user.getNome(),
	    		user.getEmail(),
	    		user.getIdNivelUsuario(),
	    		user.getTelefone(),	
	    		user.getEndereco(),
	    		user.getAtivo()
    		};
		    model.addRow(row);
		}
		JScrollPane scrollPane = new JScrollPane(table);
		table.setRowHeight(30);
		table.setAutoCreateRowSorter(true);

		JScrollPane pane = new JScrollPane(table);

		pane.setBorder(margem);
		body.add(pane, BorderLayout.SOUTH);
		
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

	private void addCreateUserAction(JButton button) {
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				CreateUser createUser = new CreateUser(usuarioLogado);
				frame.dispose();
			}
		});
		
	}
	
	private ArrayList<Usuario> getUsersList() {
		
		ArrayList<Usuario> usersList = new ArrayList<Usuario>();
		UsuarioService service = new UsuarioService();
		
		usersList = service.listar();
		
		return usersList;
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
