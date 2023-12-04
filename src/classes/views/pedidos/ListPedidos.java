package classes.views.pedidos;

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
import classes.models.Pedido;
import classes.models.Usuario;
import classes.services.PedidoService;
import classes.services.ProdutoService;
import classes.services.UsuarioService;
import classes.utils.AuthenticatedUser;
import classes.utils.PedidoDTO;
import classes.utils.ProdutoDTO;
import classes.utils.UserDTO;
import classes.views.Painel;
import classes.views.produtos.CreateProduto;

public class ListPedidos {

	private JFrame frame;
	private JLabel nomeusuarioLogado = new JLabel();
	private EmptyBorder margem = new EmptyBorder(16, 24, 16, 24);
	private Color corEscura = new Color(31, 71, 102);
	private Color corMedia = new Color(131, 152, 168);
	private Color corClara = Color.WHITE;
	private Color corTexto = Color.WHITE;
	private AuthenticatedUser usuarioLogado;
	
	private JPanel head = new JPanel(new BorderLayout());
	private JPanel body = new JPanel(new BorderLayout());
	private JPanel foot = new JPanel(new FlowLayout(FlowLayout.CENTER));
	
	private DefaultTableModel model = new DefaultTableModel();
	private JTable table = new JTable(model);
	
	private Font accentFont = new Font("Verdana", Font.PLAIN, 22);
	private Font contentFont = new Font("Arial", Font.PLAIN, 14);
	
	private PedidoService service = new PedidoService();
	private Pedido pedido = new Pedido();

	public ListPedidos(AuthenticatedUser Produto){	
		this.nomeusuarioLogado.setText("Olá, " + Produto.getUserName() + "!");
		this.usuarioLogado = Produto;
		render();
	}

	private void render() {

		frame = new JFrame();

		this.frame.setTitle("Lojinha - Listar Pedidos");
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setSize(1200, 690);
		this.frame.setResizable(false);
		this.frame.setLocationRelativeTo(null);

		head.setBackground(corEscura);
		body.setBackground(corClara);
		foot.setBackground(corEscura);
		
		nomeusuarioLogado.setFont(accentFont);
		nomeusuarioLogado.setForeground(corTexto);
		
		JButton btnLogout = new JButton("Voltar");
		setButtonStyle(btnLogout);
		addVoltarAction(btnLogout);
		
		head.add(nomeusuarioLogado, BorderLayout.WEST);
		head.add(btnLogout, BorderLayout.EAST);
		
		JPanel menuPedidos = new JPanel(new GridLayout(0, 2));
		
		JPanel controlButtonsPannel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton btnNovoPedido = new JButton("Novo");
		JButton btnEditarPedido = new JButton("Editar");
		JButton btnExcluirPedido = new JButton("Excluir");
		
		setButtonStyle(btnNovoPedido);
		setButtonStyle(btnEditarPedido);
		setButtonStyle(btnExcluirPedido);
		
		addCreatePedidoAction(btnNovoPedido);
		addExcluirPedidoAction(btnExcluirPedido);
		addEditarPedidoAction(btnEditarPedido);
		
		controlButtonsPannel.setBorder(margem);
		controlButtonsPannel.setBackground(corMedia);
		controlButtonsPannel.add(btnNovoPedido);
		controlButtonsPannel.add(btnEditarPedido);
		controlButtonsPannel.add(btnExcluirPedido);

		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel lblSelecionarUsuario = new JLabel("Listar por Usuários: ");
		JComboBox<UserDTO> btnSelecionarUsuario = listUsers();

		btnSelecionarUsuario.setBorder(new LineBorder(corEscura, 1));
		
        lblSelecionarUsuario.setForeground(corClara);
		addSearchAction(btnSelecionarUsuario);
		
		searchPanel.setBorder(margem);
		searchPanel.setBackground(corMedia);
		searchPanel.add(lblSelecionarUsuario);
		searchPanel.add(btnSelecionarUsuario);
		
		menuPedidos.add(controlButtonsPannel);
		menuPedidos.add(searchPanel);
		body.add(menuPedidos, BorderLayout.NORTH);

		renderTable(getPedidosList());
		
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
	
	private JComboBox<UserDTO> listUsers() {

		UsuarioService cliente = new UsuarioService();
		ArrayList<UserDTO> users = cliente.getClientes();
		JComboBox<UserDTO> usersList = new JComboBox<>();
		
		usersList.addItem(new UserDTO(0, null, null, null, "Todos", null, null, null, null, null));
		for (UserDTO item : users) { 
			usersList.addItem(item); 
		}
		return usersList;
	}

	private void renderTable(ArrayList<PedidoDTO> dataSource) {

		Object[] columns = {"ID", "Cliente", "Produto", "Data do Pedido", "Data de Pagamento", "Nota Fiscal", "Data de Envio", "Data de Recebimento", "Endereço de Entrega", "CEP", "Telefone", "Valor", "Itens"};
		
		model.setRowCount(0);
		model.setColumnIdentifiers(columns);
		model.fireTableDataChanged();	
		for (PedidoDTO pedido : dataSource) {
			
		    Object[] row = {
		    	pedido.getIdPedido(),
		    	pedido.getNomeUsuario(),
		    	pedido.getNomeItem(),
		    	pedido.getDtPedido(),
		    	pedido.getDtPagamento(),
		    	pedido.getNotaFiscal(),
		    	pedido.getDtEnvio(),
		    	pedido.getDtRecebimento(),
		    	pedido.getEntregaEndereco(),
		    	pedido.getEntregaCEP(),
		    	pedido.getEntregaTelefone(),
		    	pedido.getValorTotal(),
		    	pedido.getQtdItems()
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
		TableColumn clienteColumn = table.getColumnModel().getColumn(1);	
		TableColumn itemColumn = table.getColumnModel().getColumn(2);
		TableColumn addressColumn = table.getColumnModel().getColumn(8);	
		TableColumn qtdColumn = table.getColumnModel().getColumn(12);		

		idColumn.setMaxWidth(30);
		clienteColumn.setMaxWidth(80);
		itemColumn.setMinWidth(140);
		addressColumn.setMinWidth(140);
		qtdColumn.setMaxWidth(40);
	}

	private void addSearchAction(JComboBox<UserDTO> btnSelecionarUsuario) {
		
		btnSelecionarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	UserDTO selectedOption = (UserDTO) btnSelecionarUsuario.getSelectedItem();
                searchByUser(selectedOption.getIdUsuario());
            }

        });
	}

	private void searchByUser(int idUsuario) {
		ArrayList<PedidoDTO> dataSource = this.service.searchByUser(idUsuario);
		renderTable(dataSource);
	}
	
	private void searchByProduto(int idProduto) {
		ArrayList<PedidoDTO> dataSource = this.service.searchByProduto(idProduto);
		renderTable(dataSource);
	}
	
	private void search(String text) {
		
		ArrayList<PedidoDTO> dataSource = this.service.buscarPor("nome", text);

		renderTable(dataSource);
		
	}
	private ArrayList<PedidoDTO> getPedidosList() {
		
		ArrayList<PedidoDTO> pedidosList = new ArrayList<PedidoDTO>();
		
		pedidosList = service.listar();
		
		return pedidosList;
	}
	
	private void addCreatePedidoAction(JButton button) {
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				CriarPedido createPedido = new CriarPedido(usuarioLogado);
				frame.dispose();
			}
		});
		
	}
	
	private void addEditarPedidoAction(JButton button){
		
		String[] fields = {"id", "Cliente", "Produto", "dtPedido", "dtPagamento", "notaFiscal", "dtEnvio", "dtRecebimento", "Endereço", "entregaCEP", "entregaTelefone", "valorTotal", "qtdItens"};
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRow() != -1 && table.getSelectedColumn() > 0) {

					int id = (int) table.getValueAt(table.getSelectedRow(), 0);	
					String campoSelecionado = fields[table.getSelectedColumn()];
					String valorSelecionado = (String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString();							
								        
			        switch (campoSelecionado) {
				        case "Cliente": {
							mostrarEditorCliente(id);
					        break;
						}
				        case "Produto": {
							mostrarEditorProduto(id);
					        break;
						}
						case "Endereço": {
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
						"Atualizar Pedido", 
						JOptionPane.OK_OPTION
					);
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
			
			private void mostrarEditorCliente(int id) {
				UsuarioService cliente = new UsuarioService();
				ArrayList<UserDTO> clientes = cliente.getClientes();
				
				int confirmEdit = 0;
		        JPanel panel = new JPanel(new GridLayout(0, 1));
		        panel.setBorder(margem);

				JLabel lblCliente = new JLabel("Selecione o cliente:");
				JComboBox<UserDTO> clientesList = new JComboBox<UserDTO>();
				for (UserDTO user : clientes) { 
					clientesList.addItem(user); 
				}
				
				panel.add(lblCliente);
				panel.add(clientesList);
				
				confirmEdit = JOptionPane.showConfirmDialog(null, panel, "Atualizar Item", JOptionPane.OK_CANCEL_OPTION);
		        
		        if (confirmEdit == JOptionPane.OK_OPTION) {
		        	int selectedClienteId = ((UserDTO) clientesList.getSelectedItem()).getIdUsuario();
		            atualizar(new String[] {selectedClienteId + ""}, new String[] {"idUsuario"}, id);
		        }
			}
			
			private void mostrarEditorProduto(int id) {
				ProdutoService produto = new ProdutoService();
				ArrayList<ProdutoDTO> produtos = produto.listar();
				
				int confirmEdit = 0;
		        JPanel panel = new JPanel(new GridLayout(0, 1));
		        panel.setBorder(margem);

				JLabel lblProduto = new JLabel("Selecione o produto:");
				JComboBox<ProdutoDTO> produtosList = new JComboBox<ProdutoDTO>();
				for (ProdutoDTO item: produtos) { 
					produtosList.addItem(item); 
				}
				
				panel.add(lblProduto);
				panel.add(produtosList);
				
				confirmEdit = JOptionPane.showConfirmDialog(null, panel, "Atualizar Item", JOptionPane.OK_CANCEL_OPTION);
		        
		        if (confirmEdit == JOptionPane.OK_OPTION) {
		        	int selectedProdutoId = ((ProdutoDTO) produtosList.getSelectedItem()).getIdProduto();
		            atualizar(new String[] {selectedProdutoId + ""}, new String[] {"idProduto"}, id);
		        }
			}
			
			private void mostrarEditorEndereco(int id) {
				String[] estados = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
				Pedido tempPedido = service.buscarPorId(id);
				
				int confirmEdit = 0;
		        JPanel panel = new JPanel(new GridLayout(0, 1));
		        panel.setBorder(margem);
		        
				JTextField rua = new JTextField(10);
				JTextField numero = new JTextField(10);
				JTextField bairro = new JTextField(10);
				JTextField cidade = new JTextField(10);
				JTextField[] txtValores = new JTextField[] {rua, numero, bairro, cidade};
				
				rua.setText(tempPedido.getEntregaendereco());
				numero.setText(tempPedido.getEntregaNumero());
				bairro.setText(tempPedido.getEntregaBairro());
				cidade.setText(tempPedido.getEntregaCidade());
				
				JComboBox<String> estadosList = new JComboBox<>(estados);	
				
				JLabel lblRua = new JLabel("Rua: ");
				JLabel lblNumero = new JLabel("Número: ");
				JLabel lblBairro = new JLabel("Bairro: ");
				JLabel lblCidade = new JLabel("Cidade: ");
				JLabel lblEstado = new JLabel("Estado: ");
				JLabel[] lblValores = new JLabel[] {lblRua, lblNumero, lblBairro, lblCidade, lblEstado};
				
				panel.add(new JLabel("Informe os novos valores:"));
				for(int i = 0; i < 4; i++) {
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
		            atualizar(valores, new String[] {"entregaendereco", "entregaNumero", "entregaBairro", "entregaCidade", "entregaUf"}, id);
		        }			
			}
			private void atualizar(String valorAtualizado[], String campoSelecionado[], int id) {
				int status = 0;
				for(int i = 0; i < valorAtualizado.length; i++) {
					status += service.atualizar(valorAtualizado[i], campoSelecionado[i], id);    					
				}
				
				if(status == valorAtualizado.length) { 
					renderTable(getPedidosList());
					JOptionPane.showMessageDialog(
						null,
						"Pedido Atualizado.",
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
	private void addExcluirPedidoAction(JButton button) {
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRow() != -1) {
					
					int confirmarExclusão = JOptionPane.showConfirmDialog(
						null,
						"Deseja excluir permanentemente o pedido?",
						"Confirmar Exclusão", 
						JOptionPane.YES_NO_OPTION
					);
					
					if(confirmarExclusão == 0) { 
						int id = (int) table.getValueAt(table.getSelectedRow(), 0);					
						int status = service.apagar(id);
						
						if(status == 1) {
							renderTable(getPedidosList());
							JOptionPane.showMessageDialog(
								null,
								"Pedido Excluído.",
								"Confirmar Exclusão", 
								JOptionPane.INFORMATION_MESSAGE 
							);
						}
						else {
							JOptionPane.showMessageDialog(
								null,
								"Erro ao excluir pedido.",
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
						"Excluir pedido", 
						JOptionPane.OK_OPTION
					);
				}
			}
		});
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
