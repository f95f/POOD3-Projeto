package classes.views;

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
import classes.utils.AuthenticatedUser;

public class ListCategorias {
	
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

	Categoria service = new Categoria();
	
	public ListCategorias(AuthenticatedUser Produto){
		
		this.nomeUsuarioLogado.setText("Olá, " + Produto.getUserName() + "!");
		this.usuarioLogado = Produto;
		render();
	}
	
	private void render() {

		frame = new JFrame();

		this.frame.setTitle("Lojinha - Categorias");
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

		JPanel menuCategorias = new JPanel(new GridLayout(0, 2));
		
		JPanel controlButtonsPannel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton btnNovaCategoria = new JButton("Novo");
		JButton btnEditarCategoria = new JButton("Editar");
		JButton btnExcluirCategoria = new JButton("Excluir");
		
		setButtonStyle(btnNovaCategoria);
		setButtonStyle(btnEditarCategoria);
		setButtonStyle(btnExcluirCategoria);
		
		addCreateCategoriaAction(btnNovaCategoria);
		addExcluirCategoriaAction(btnExcluirCategoria);
		addEditarCategoriaAction(btnEditarCategoria);
		
		controlButtonsPannel.setBorder(margem);
		controlButtonsPannel.setBackground(corMedia);
		controlButtonsPannel.add(btnNovaCategoria);
		controlButtonsPannel.add(btnEditarCategoria);
		controlButtonsPannel.add(btnExcluirCategoria);

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
		
		menuCategorias.add(controlButtonsPannel);
		menuCategorias.add(searchPanel);
		body.add(menuCategorias, BorderLayout.NORTH);
		
		renderTable(getCategoriasList());
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
	
	private void addEditarCategoriaAction(JButton button){
	
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if(!usuarioTemAcesso()) { return; }
				if(table.getSelectedRow() != -1 && table.getSelectedColumn() > 0) {

					int id = (int) table.getValueAt(table.getSelectedRow(), 0);	
					String campoSelecionado = "descricao";
					Object cellValue = table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
					String valorSelecionado = "";
					int confirmEdit = 0;
				    
				    if (cellValue != null) {
				        valorSelecionado = cellValue.toString();
				    } else {
				    	valorSelecionado = "Indefinido";
				    }		
				    
			        JPanel panel = new JPanel(new GridLayout(0, 1));
			        panel.setBorder(margem);
			        
					JTextField textValue = new JTextField(10);
					textValue.setText(valorSelecionado);
					setTextFieldStyle(textValue);
					
			        panel.add(new JLabel("Informe o novo nome da categoria:"));
			        panel.add(textValue);
			        
			        confirmEdit = JOptionPane.showConfirmDialog(null, panel, "Atualizar categoria", JOptionPane.OK_CANCEL_OPTION);
			        if (confirmEdit == JOptionPane.OK_OPTION) {
			            String valorAtualizado = textValue.getText();
			            atualizar(valorAtualizado, campoSelecionado, id);	
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

			private void atualizar(String valorAtualizado, String campoSelecionado, int id) {
				
				int status = service.editar(valorAtualizado, campoSelecionado, id);    					
			
				if(status == 1) { 
					renderTable(getCategoriasList());
					JOptionPane.showMessageDialog(
						null,
						"Categoria Atualizada.",
						"Atualizar Categoria", 
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
	private void addCreateCategoriaAction(JButton button) {
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				Categoria novaCategoria = new Categoria();
				int confirmEdit = 0;
		        JPanel panel = new JPanel(new GridLayout(0, 1));
		        panel.setBorder(margem);
		        
				JTextField textValue = new JTextField(10);
				setTextFieldStyle(textValue);
				
		        panel.add(new JLabel("Informe o nome da categoria:"));
		        panel.add(textValue);
		        
		        confirmEdit = JOptionPane.showConfirmDialog(null, panel, "Criar categoria", JOptionPane.OK_CANCEL_OPTION);
		        if (confirmEdit == JOptionPane.OK_OPTION) {
		            String valorAtualizado = textValue.getText();
		            novaCategoria.setDescricao(valorAtualizado);
		            int saveStatus = novaCategoria.save();
					
					if(saveStatus != 0) {
						System.out.println("\n\n [i] Usuário salvo com sucesso!\n");
						renderTable(getCategoriasList());
					}
					else {
						System.out.println("\n\n [!] Erro ao salvar!\n");
					}
		        }
				
			}
		});
	}
	
	private void addExcluirCategoriaAction(JButton button) {
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if(!usuarioTemAcesso()) { return; }
				if(table.getSelectedRow() != -1) {
					
					int confirmarExclusão = JOptionPane.showConfirmDialog(
						null,
						"Deseja excluir permanentemente a categoria?",
						"Confirmar Exclusão", 
						JOptionPane.YES_NO_OPTION
					);
					
					if(confirmarExclusão == 0) { 
						int id = (int) table.getValueAt(table.getSelectedRow(), 0);	
						if(existeDadosAssociados(id)){
							JOptionPane.showMessageDialog(
								null,
								"Existem produtos associados à esta categoria.",
								"Impossível Excluir", 
								JOptionPane.OK_OPTION
							);
							return;
						}
						int status = service.delete(id);
						
						if(status == 1) {
							renderTable(getCategoriasList());
							JOptionPane.showMessageDialog(
								null,
								"Categoria Excluída.",
								"Confirmar Exclusão", 
								JOptionPane.INFORMATION_MESSAGE 
							);
						}
						else {
							JOptionPane.showMessageDialog(
								null,
								"Erro ao excluir Categoria.",
								"Confirmar Exclusão", 
								JOptionPane.OK_OPTION
							);
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(
						null,
						"Selecione uma categoria na tabela!",
						"Excluir Produto", 
						JOptionPane.OK_OPTION
					);
				}
			}

		});
	}
	private boolean existeDadosAssociados(int id) {
		return this.service.findProducts(id);
		
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
		ArrayList<Categoria> dataSource = this.service.searchBy(text);
		renderTable(dataSource);
	}
	
	private void renderTable(ArrayList<Categoria> dataSource) {
	
		Object[] columns = {"ID", "Descrição"};

		model.setRowCount(0);
		model.setColumnIdentifiers(columns);
		model.fireTableDataChanged();	

		for (Categoria categoria : dataSource) {
		    Object[] row = {
	    		categoria.getIdCategoria(),
	    		categoria.getDescricao(),
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
		idColumn.setMaxWidth(30);
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
	private ArrayList<Categoria> getCategoriasList() {
		
		ArrayList<Categoria> categoriasList = new ArrayList<Categoria>();
		categoriasList = service.listAll();
		
		return categoriasList;
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
}
