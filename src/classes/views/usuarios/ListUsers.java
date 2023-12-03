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
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import classes.models.Categoria;
import classes.models.Usuario;
import classes.services.UsuarioService;
import classes.utils.AuthenticatedUser;
import classes.utils.ProdutoDTO;
import classes.utils.UserDTO;
import classes.views.Painel;

public class ListUsers {
	private JFrame frame;
	private JLabel nomeUsuarioLogado = new JLabel();
	private EmptyBorder margem = new EmptyBorder(16, 24, 16, 24);
	private Color corEscura = new Color(31, 71, 102);
	private Color corMedia = new Color(131, 152, 168);
	private Color corClara = Color.WHITE;
	private Color corTexto = Color.WHITE;
	private AuthenticatedUser usuarioLogado;
	
	private DefaultTableModel model = new DefaultTableModel();
	private JTable table = new JTable(model);
	
	Font accentFont = new Font("Verdana", Font.PLAIN, 22);
	Font contentFont = new Font("Arial", Font.PLAIN, 14);

	UsuarioService service = new UsuarioService();
	
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
		
		JButton btnLogout = new JButton("Voltar");
		setButtonStyle(btnLogout);
		addVoltarAction(btnLogout);
		
		head.add(nomeUsuarioLogado, BorderLayout.WEST);
		head.add(btnLogout, BorderLayout.EAST);

		JPanel menuProdutos = new JPanel(new GridLayout(0, 2));
		
		JPanel controlButtonsPannel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton btnNovoProduto = new JButton("Novo");
		JButton btnEditarProduto = new JButton("Editar");
		JButton btnExcluirProduto = new JButton("Excluir");
		
		setButtonStyle(btnNovoProduto);
		setButtonStyle(btnEditarProduto);
		setButtonStyle(btnExcluirProduto);
		
		addCreateUserAction(btnNovoProduto);
		addExcluirUserAction(btnExcluirProduto);
		addEditarUserAction(btnEditarProduto);
		
		controlButtonsPannel.setBorder(margem);
		controlButtonsPannel.setBackground(corMedia);
		controlButtonsPannel.add(btnNovoProduto);
		controlButtonsPannel.add(btnEditarProduto);
		controlButtonsPannel.add(btnExcluirProduto);

		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel lblPesquisar = new JLabel("Pesquisar: ");
		JTextField txtPesquisar = new JTextField(15);

		txtPesquisar.setMargin(new Insets(5, 10, 5, 10));
		txtPesquisar.setBorder(new LineBorder(corEscura, 1));
		
		Border internalPadding = BorderFactory.createEmptyBorder(5, 10, 5, 10);
        Border existingBorder = txtPesquisar.getBorder();
        
        if (existingBorder != null) {
        	txtPesquisar.setBorder(BorderFactory.createCompoundBorder(existingBorder, internalPadding));
        } 
        else {
        	txtPesquisar.setBorder(internalPadding); 
        }
		
		lblPesquisar.setForeground(corClara);
		addSearchAction(txtPesquisar);
		
		searchPanel.setBorder(margem);
		searchPanel.setBackground(corMedia);
		searchPanel.add(lblPesquisar);
		searchPanel.add(txtPesquisar);
		
		menuProdutos.add(controlButtonsPannel);
		menuProdutos.add(searchPanel);
		body.add(menuProdutos, BorderLayout.NORTH);
		
		renderTable(getUsersList());
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
	
	private void addSearchAction(JTextField textField) {
		
		textField.addKeyListener(new KeyListener() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = textField.getText();
                search(text);
            }
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
        });
	}
	
	private void search(String text) {
		ArrayList<UserDTO> dataSource = this.service.searchBy("nome", text);
		renderTable(dataSource);
	}
	
	private void renderTable(ArrayList<UserDTO> dataSource) {
	
		Object[] columns = {"ID", "Nome", "Email", "Nível", "Telefone", "Endereço", "CEP", "Status"};

		model.setRowCount(0);
		model.setColumnIdentifiers(columns);
		model.fireTableDataChanged();	

		for (UserDTO user : dataSource) {
		    Object[] row = {
	    		user.getIdUsuario(),
	    		user.getNome(),
	    		user.getEmail(),
	    		user.getNivelUsuario(),
	    		user.getTelefone(),	
	    		user.getEndereco(),
	    		user.getCep(),
	    		user.getAtivo()
    		};
		    model.addRow(row);
		}
		definirTamanhoDasColunas(table);
		
		table.setRowHeight(30);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoCreateRowSorter(true);
	}
	
	private void definirTamanhoDasColunas(JTable table) {
		
		TableColumn idColumn = table.getColumnModel().getColumn(0);	
		TableColumn emailColumn = table.getColumnModel().getColumn(2);
		TableColumn enderecoColumn = table.getColumnModel().getColumn(5);
		TableColumn contaColumn = table.getColumnModel().getColumn(6);		

		idColumn.setMaxWidth(30);
		emailColumn.setMinWidth(120);
		enderecoColumn.setMinWidth(220);
		contaColumn.setMaxWidth(60);
		
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
	
	private ArrayList<UserDTO> getUsersList() {
		
		ArrayList<UserDTO> usersList = new ArrayList<UserDTO>();
		usersList = service.listar();
		
		return usersList;
	}
	
	private void addVoltarAction(JButton button) {	
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				Painel painel = new Painel(usuarioLogado);	
			}
		});		
	}
	
	private void addExcluirUserAction(JButton button) {
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRow() != -1) {
					
					int confirmarExclusão = JOptionPane.showConfirmDialog(
						null,
						"Deseja excluir permanentemente o produto?",
						"Confirmar Exclusão", 
						JOptionPane.YES_NO_OPTION
					);
					
					if(confirmarExclusão == 0) { 
						int id = (int) table.getValueAt(table.getSelectedRow(), 0);					
						int status = service.excluir(id);
						
						if(status == 1) {
							renderTable(getUsersList());
							JOptionPane.showMessageDialog(
								null,
								"Produto Excluído.",
								"Confirmar Exclusão", 
								JOptionPane.INFORMATION_MESSAGE 
							);
						}
						else {
							JOptionPane.showMessageDialog(
								null,
								"Erro ao excluir produto.",
								"Confirmar Exclusão", 
								JOptionPane.OK_OPTION
							);
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(
						null,
						"Selecione um produto na tabela!",
						"Excluir Produto", 
						JOptionPane.OK_OPTION
					);
				}
			}
		});
	}
	
	private void addEditarUserAction(JButton button){
		
		String[] fields = {"idUsuario", "nome", "email", "nivel", "telefone", "endereço", "cep", "status"};
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRow() != -1 && table.getSelectedColumn() > 0) {

					int id = (int) table.getValueAt(table.getSelectedRow(), 0);	
					String campoSelecionado = fields[table.getSelectedColumn()];
					Object cellValue = table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
					String valorSelecionado = "";
				    
				    if (cellValue != null) {
				        valorSelecionado = cellValue.toString();
				    } else {
				    	valorSelecionado = "Indefinido";
				    }						
								        
			        switch (campoSelecionado) {
						case "nivel": {
							mostrarEditorNivel(id);
					        break;
						}
						case "status": {
							mostrarEditorStatus(id);
					        break;
						}
						case "endereço": {
							mostrarEditorEndereco(id);
					        break;
						}
						default:
							mostrarEditor(id, campoSelecionado, valorSelecionado);	
					}
			        
				}
				else {
					JOptionPane.showMessageDialog(
						null,
						"Selecione um valor válido na tabela!",
						"Atualizar Usuário", 
						JOptionPane.OK_OPTION
					);
				}
			}

			private void mostrarEditorEndereco(int id) {
				String[] estados = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
				Usuario tempUser = service.buscarPorId(id);
				
				int confirmEdit = 0;
		        JPanel panel = new JPanel(new GridLayout(0, 1));
		        panel.setBorder(margem);
		        
				JTextField rua = new JTextField(10);
				JTextField bairro = new JTextField(10);
				JTextField cidade = new JTextField(10);
				JTextField[] txtValores = new JTextField[] {rua, bairro, cidade};
				
				rua.setText(tempUser.getEndereco());
				bairro.setText(tempUser.getBairro());
				cidade.setText(tempUser.getCidade());
				
				JComboBox<String> estadosList = new JComboBox<>(estados);	
				
				JLabel lblRua = new JLabel("Rua: ");
				JLabel lblBairro = new JLabel("Bairro: ");
				JLabel lblCidade = new JLabel("Cidade: ");
				JLabel lblEstado = new JLabel("Estado: ");
				JLabel[] lblValores = new JLabel[] {lblRua, lblBairro, lblCidade, lblEstado};
				
				panel.add(new JLabel("Informe os novos valores:"));
				for(int i = 0; i < 3; i++) {
					setTextFieldStyle(txtValores[i]);
					panel.add(lblValores[i]);
					panel.add(txtValores[i]);
				}
		        
				panel.add(lblEstado);
				panel.add(estadosList);
				
		        confirmEdit = JOptionPane.showConfirmDialog(null, panel, "Atualizar Item", JOptionPane.OK_CANCEL_OPTION);
		        
		        if (confirmEdit == JOptionPane.OK_OPTION) {
		        	String[] valores = new String[4];
		        	for(int i = 0; i < 3; i++) {
		        		valores[i] = txtValores[i].getText();
		        	}
		        	valores[3] = (String) estadosList.getSelectedItem();
		            atualizar(valores, new String[] {"endereco", "bairro", "cidade", "uf"}, id);
		        }			
			}

			private void mostrarEditor(int id, String campoSelecionado, String valorSelecionado) {
				
				int confirmEdit = 0;
		        JPanel panel = new JPanel(new GridLayout(0, 1));
		        panel.setBorder(margem);
		        
				JTextField textValue = new JTextField(10);
				setTextFieldStyle(textValue);
				textValue.setText(valorSelecionado);
				
		        panel.add(new JLabel("Informe o novo valor:"));
		        panel.add(textValue);
		        confirmEdit = JOptionPane.showConfirmDialog(null, panel, "Atualizar Item", JOptionPane.OK_CANCEL_OPTION);
		        
		        if (confirmEdit == JOptionPane.OK_OPTION) {
		            String valorAtualizado = textValue.getText();
		            atualizar(new String[] {valorAtualizado}, new String[] {campoSelecionado}, id);   
		        }
			}

			private void mostrarEditorNivel(int idUsuario) {
				
				int confirmEdit = 0;
		        JPanel panel = new JPanel(new GridLayout(0, 1));
		        panel.setBorder(margem);
		        
				String[] niveis = {"Cliente", "Funcionário", "Caixa", "Financeiro", "Gerente", "Diretor", "Administrador"};
				JComboBox<Object> niveisList = new JComboBox<>(niveis);		
				
		        panel.add(new JLabel("Selecione o novo valor:"));
		        panel.add(niveisList);
		        confirmEdit = JOptionPane.showConfirmDialog(null, panel, "Atualizar Item", JOptionPane.OK_CANCEL_OPTION);
		        
		        if (confirmEdit == JOptionPane.OK_OPTION) {
		            int idNivelUsuarioSelecionado = niveisList.getSelectedIndex() + 1;
		            atualizar(
	            		new String[] {idNivelUsuarioSelecionado + ""},
	            		new String[] {"idNivelUsuario"},
	            		idUsuario
            		);   
		        }	
			}

			private void mostrarEditorStatus(int idUsuario) {
				
				int confirmEdit = 0;
		        JPanel panel = new JPanel(new GridLayout(0, 1));
		        panel.setBorder(margem);
		        
				String[] status = {"Ativado", "Desativado"};
				JComboBox<Object> statusList = new JComboBox<>(status);		
				
		        panel.add(new JLabel("Selecione o novo valor:"));
		        panel.add(statusList);
		        confirmEdit = JOptionPane.showConfirmDialog(null, panel, "Atualizar Item", JOptionPane.OK_CANCEL_OPTION);
		        
		        if (confirmEdit == JOptionPane.OK_OPTION) {
		            String statusSelecionado = (String) statusList.getSelectedItem();
		            String valorAtualizado = (statusSelecionado == "Ativado")? "S" : "N";
		            
		            atualizar(
	            		new String[] {valorAtualizado},
	            		new String[] {"ativo"},
	            		idUsuario
            		);   
		        }	
			}

			private void atualizar(String valorAtualizado[], String campoSelecionado[], int id) {
				int status = 0;
				for(int i = 0; i < valorAtualizado.length; i++) {
					status += service.atualizar(valorAtualizado[i], campoSelecionado[i], id);    					
				}
				
				if(status == valorAtualizado.length) { 
					renderTable(getUsersList());
					JOptionPane.showMessageDialog(
						null,
						"Usuário Atualizado.",
						"Atualizar Item", 
						JOptionPane.INFORMATION_MESSAGE 
					);
				}
				else {
					JOptionPane.showMessageDialog(
						null,
						"Erro ao atualizar.",
						"Atualizar Item", 
						JOptionPane.OK_OPTION
					);
				}
			}
		});
	}
	
	private void setTextFieldStyle(JTextField textField) {
		textField.setMargin(new Insets(5, 10, 5, 10));
		textField.setBorder(new LineBorder(corEscura, 1));
		Border internalPadding = BorderFactory.createEmptyBorder(5, 10, 5, 10);
        Border existingBorder = textField.getBorder();
        
        if (existingBorder != null) {
            textField.setBorder(BorderFactory.createCompoundBorder(existingBorder, internalPadding));
        } else {
            textField.setBorder(internalPadding); 
        }
		
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
