package classes.views.produtos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TrayIcon.MessageType;
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
import classes.models.Produto;
import classes.services.CategoriaService;
import classes.services.ProdutoService;
import classes.utils.AuthenticatedUser;
import classes.utils.ProdutoDTO;
import classes.views.Painel;

public class ListProdutos {

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
	
	private ProdutoService service = new ProdutoService();
	private CategoriaService categoriaService = new CategoriaService();
	//ArrayList<ProdutoDTO> dataSource;
	
	public ListProdutos(AuthenticatedUser Produto){
		
		this.nomeusuarioLogado.setText("Olá, " + Produto.getUserName() + "!");
		this.usuarioLogado = Produto;
		render();
		
	}
	
	private void render() {

		frame = new JFrame();

		this.frame.setTitle("Lojinha - Listar Produtos");
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
		
		JPanel menuProdutos = new JPanel(new GridLayout(0, 3));
		
		JPanel controlButtonsPannel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton btnNovoProduto = new JButton("Novo");
		JButton btnEditarProduto = new JButton("Editar");
		JButton btnExcluirProduto = new JButton("Excluir");
		
		addCreateProductAction(btnNovoProduto);
		addExcluirProdutoAction(btnExcluirProduto);
		addEditarProdutoAction(btnEditarProduto);
		
		controlButtonsPannel.setBorder(margem);
		controlButtonsPannel.setBackground(corMedia);
		controlButtonsPannel.add(btnNovoProduto);
		controlButtonsPannel.add(btnEditarProduto);
		controlButtonsPannel.add(btnExcluirProduto);

		JPanel selectCategoriaPannel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblSelecionarCategoria = new JLabel("Listar por Categoria: ");
		JComboBox<Categoria> btnSelecionarCategoria = listCategorias();
		
		lblSelecionarCategoria.setForeground(corClara);
		
		addSearchAction(btnSelecionarCategoria);
		
		selectCategoriaPannel.setBorder(margem);
		selectCategoriaPannel.setBackground(corMedia);
		selectCategoriaPannel.add(lblSelecionarCategoria);
		selectCategoriaPannel.add(btnSelecionarCategoria);
		
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel lblPesquisar = new JLabel("Pesquisar: ");
		JTextField txtPesquisar = new JTextField(15);

		txtPesquisar.setMargin(new Insets(5, 10, 5, 10));
		txtPesquisar.setBorder(new LineBorder(corEscura, 1));
		
		Border internalPadding = BorderFactory.createEmptyBorder(5, 10, 5, 10);
        Border existingBorder = txtPesquisar.getBorder();
        
        if (existingBorder != null) {
        	txtPesquisar.setBorder(BorderFactory.createCompoundBorder(existingBorder, internalPadding));
        } else {
        	txtPesquisar.setBorder(internalPadding); 
        }
		
		lblPesquisar.setForeground(corClara);
		addSearchAction(txtPesquisar);
		
		searchPanel.setBorder(margem);
		searchPanel.setBackground(corMedia);
		searchPanel.add(lblPesquisar);
		searchPanel.add(txtPesquisar);
		
		menuProdutos.add(controlButtonsPannel);
		menuProdutos.add(selectCategoriaPannel);
		menuProdutos.add(searchPanel);
		body.add(menuProdutos, BorderLayout.NORTH);

		renderTable(getProductsList());
		
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

	private void renderTable(ArrayList<ProdutoDTO> dataSource) {

		Object[] columns = {"ID", "Nome", "Fabricante", "Marca", "Modelo", "Categoria", "Descrição", "Unidade", "Dimensões", "Peso", "Cor"};
		
		model.setRowCount(0);
		model.setColumnIdentifiers(columns);
		model.fireTableDataChanged();	
		for (ProdutoDTO produto : dataSource) {
			
		    Object[] row = {
	    		produto.getIdProduto(),
	    		produto.getNome(),
	    		produto.getFabricante(),
	    		produto.getMarca(),
	    		produto.getModelo(),	
	    		produto.getCategoria(),
	    		produto.getDescricao(),
	    		produto.getUnidadeMedida(),
	    		produto.getDimensoes(),
	    		produto.getPeso(),
	    		produto.getCor()
    		};
		    model.addRow(row);
		}
		
		definirTamanhoDasColunas(table);
	
		table.setRowHeight(30);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoCreateRowSorter(true);
	
	}

	private void addSearchAction(JComboBox<Categoria> btnSelecionarCategoria) {
		
		btnSelecionarCategoria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                Categoria selectedOption = (Categoria) btnSelecionarCategoria.getSelectedItem();
                searchByCategoria(selectedOption.getIdCategoria(), "idCategoria");
            }

        });
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
		
		ArrayList<ProdutoDTO> dataSource = this.service.searchBy("nome", text);

		renderTable(dataSource);
		
	}
	
	private void searchByCategoria(int id, String field) {
		
		ArrayList<ProdutoDTO> dataSource = this.service.searchByCategoria(id);

		renderTable(dataSource);
	}
	
	private void definirTamanhoDasColunas(JTable table) {
		
		TableColumn idColumn = table.getColumnModel().getColumn(0);	
		TableColumn nomeColumn = table.getColumnModel().getColumn(1);	
		TableColumn categoriaColumn = table.getColumnModel().getColumn(5);
		TableColumn descriptionColumn = table.getColumnModel().getColumn(6);	
		TableColumn medidaColumn = table.getColumnModel().getColumn(7);	
		TableColumn pesoColumn = table.getColumnModel().getColumn(9);
		TableColumn corColumn = table.getColumnModel().getColumn(10);		

		idColumn.setMaxWidth(30);
		nomeColumn.setMinWidth(120);
		categoriaColumn.setMinWidth(100);
		descriptionColumn.setMinWidth(150);
		medidaColumn.setMaxWidth(80);
		pesoColumn.setMaxWidth(60);
		corColumn.setMaxWidth(60);
	}
	
	private void addCreateProductAction(JButton button) {
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				CreateProduto CreateProduct = new CreateProduto(usuarioLogado);
				frame.dispose();
			}
		});
		
	}
	
	private void addExcluirProdutoAction(JButton button) {
		
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
							JOptionPane.showMessageDialog(
								null,
								"Produto Excluído.",
								"Confirmar Exclusão", 
								JOptionPane.OK_OPTION
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
	
	private void addEditarProdutoAction(JButton button){
		
		String[] fields = {"idProduto", "fabricante", "nome", "marca", "modelo", "categoria", "descricao", "unidadeMedida", "dimensoes", "peso", "cor"};
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRow() != -1 && table.getSelectedColumn() > 0) {

					int id = (int) table.getValueAt(table.getSelectedRow(), 0);	
					String campoSelecionado = fields[table.getSelectedColumn()];
					String valorSelecionado = (String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString();							
								        
			        switch (campoSelecionado) {
						case "categoria": {
							mostrarEditorCategoria(id);
					        break;
							
						}
						case "dimensoes": {
							mostrarEditorDimensoes(id);
					        break;
						}
						case "peso": {
							mostrarEditorPeso(id, campoSelecionado);
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
						"Atualizar Produto", 
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

			private void mostrarEditorPeso(int id, String campoSelecionado) {
				
				int confirmEdit = 0;
		        JPanel panel = new JPanel(new GridLayout(0, 1));
		        panel.setBorder(margem);
		        
				JTextField textValue = new JTextField(10);
				setTextFieldStyle(textValue);
				
		        panel.add(new JLabel("Informe o novo peso:"));
		        panel.add(textValue);
		        confirmEdit = JOptionPane.showConfirmDialog(null, panel, "Atualizar Item", JOptionPane.OK_CANCEL_OPTION);
		        
		        if (confirmEdit == JOptionPane.OK_OPTION) {
		            String valorAtualizado = textValue.getText();
		            atualizar(new String[] {valorAtualizado}, new String[] {campoSelecionado}, id);   
		        }
				
			}

			private void mostrarEditorDimensoes(int id) {
				
				int confirmEdit = 0;
		        JPanel panel = new JPanel(new GridLayout(0, 1));
		        panel.setBorder(margem);
		        
				JTextField altura = new JTextField(10);
				JTextField largura = new JTextField(10);
				JTextField profundidade = new JTextField(10);
				JTextField[] txtValores = new JTextField[] {altura, largura, profundidade};
				
				JLabel lblAltura = new JLabel("Altura: ");
				JLabel lblLargura = new JLabel("Largura: ");
				JLabel lblProfundidade = new JLabel("Profundidade: ");
				JLabel[] lblValores = new JLabel[] {lblAltura, lblLargura, lblProfundidade};
				
				panel.add(new JLabel("Informe os novos valores:"));
				for(int i = 0; i < 3; i++) {
					setTextFieldStyle(txtValores[i]);
					panel.add(lblValores[i]);
					panel.add(txtValores[i]);
				}
		        
		        confirmEdit = JOptionPane.showConfirmDialog(null, panel, "Atualizar Item", JOptionPane.OK_CANCEL_OPTION);
		        
		        if (confirmEdit == JOptionPane.OK_OPTION) {
		        	String[] valores = new String[3];
		        	for(int i = 0; i < 3; i++) {
		        		valores[i] = txtValores[i].getText();
		        	}
		            atualizar(valores, new String[] {"altura", "largura", "profundidade"}, id);
		        }
				
			}

			private void mostrarEditorCategoria(int id) {
				
				int confirmEdit = 0;
		        JPanel panel = new JPanel(new GridLayout(0, 1));
		        panel.setBorder(margem);
		        
				ArrayList<Categoria> categorias = categoriaService.listar();
				JComboBox<Categoria> categoriasList = new JComboBox<>();
				for (Categoria item : categorias) { 
					categoriasList.addItem(new Categoria(item.getIdCategoria(), item.getDescricao())); 
				}
				
		        panel.add(new JLabel("Selecione o novo valor:"));
		        panel.add(categoriasList);
		        confirmEdit = JOptionPane.showConfirmDialog(null, panel, "Atualizar Item", JOptionPane.OK_CANCEL_OPTION);
		        
		        if (confirmEdit == JOptionPane.OK_OPTION) {
		            Categoria valorAtualizado = (Categoria) categoriasList.getSelectedItem();
		            atualizar(
	            		new String[] {valorAtualizado.getIdCategoria() + ""},
	            		new String[] {"idCategoria"},
	            		id
            		);   
		        }
			}

			private void atualizar(String valorAtualizado[], String campoSelecionado[], int id) {
				int status = 0;
				for(int i = 0; i < valorAtualizado.length; i++) {
					status += service.atualizar(valorAtualizado[i], campoSelecionado[i], id);    					
				}
				System.out.println("\n\n " + (status != valorAtualizado.length));
				System.out.println("\n\n " + (valorAtualizado.length));
				System.out.println("\n\n " + status);
				if(status == valorAtualizado.length) { 
					JOptionPane.showMessageDialog(
						null,
						"Produto Atualizado.",
						"Atualizar Item", 
						JOptionPane.OK_OPTION
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
	
	private ArrayList<ProdutoDTO> getProductsList() {
		
		ArrayList<ProdutoDTO> productsList = new ArrayList<ProdutoDTO>();
		
		productsList = service.listar();
		
		return productsList;
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
	
	private JComboBox<Categoria> listCategorias(){
		
		ArrayList<Categoria> categorias = categoriaService.listar();
		JComboBox<Categoria> categoriasList = new JComboBox<>();
		categoriasList.addItem(new Categoria(0, "Todas"));
		for (Categoria item : categorias) { 
			categoriasList.addItem(new Categoria(item.getIdCategoria(), item.getDescricao())); 
		}
		return categoriasList;
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
