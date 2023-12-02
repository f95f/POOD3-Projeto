package classes.views.produtos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import classes.models.Produto;
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
	
	DefaultTableModel model = new DefaultTableModel();
	JTable table = new JTable(model);
	
	Font accentFont = new Font("Verdana", Font.PLAIN, 22);
	Font contentFont = new Font("Arial", Font.PLAIN, 14);
	
	ProdutoService service = new ProdutoService();
	
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
		
		JPanel head = new JPanel(new BorderLayout());
		JPanel body = new JPanel(new BorderLayout());
		JPanel foot = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
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
				
		JPanel controlButtonsPannel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton btnNovoProduto = new JButton("Novo");
		JButton btnEditarProduto = new JButton("Editar");
		JButton btnExcluirProduto = new JButton("Excluir");
		
		addCreateProductAction(btnNovoProduto);
		addExcluirProdutoAction(btnExcluirProduto);
		addEditarProdutoAction(btnEditarProduto);
		
		controlButtonsPannel.setBorder(margem);
		controlButtonsPannel.add(btnNovoProduto);
		controlButtonsPannel.add(btnEditarProduto);
		controlButtonsPannel.add(btnExcluirProduto);
		controlButtonsPannel.setBackground(corMedia);
		body.add(controlButtonsPannel, BorderLayout.NORTH);
		
		Object[] columns = {"ID", "Nome", "Fabricante", "Marca", "Modelo", "Categoria", "Descrição", "Unidade", "Dimensões", "Peso", "Cor"};
		ArrayList<ProdutoDTO> produtoList = getProductsList();
		
		model.setColumnIdentifiers(columns);
		for (ProdutoDTO produto : produtoList) {
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
	
		JScrollPane scrollPane = new JScrollPane(table);
		table.setRowHeight(30);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
		
		String[] fields = {"idProduto", "fabricante", "nome", "marca", "modelo", "idCategoria", "descricao", "unidadeMedida", "largura", "altura", "profundidade", "peso", "cor"};
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRow() != -1) {

					int id = (int) table.getValueAt(table.getSelectedRow(), 0);	
					int confirmEdit = 0;
					int status = 0;
					String campoSelecionado = fields[table.getSelectedColumn() +2];
					String valorSelecionado = (String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString();							

					System.out.println("\n\n" + table.getSelectedColumn() + "\n\n");
					System.out.println("\n\n" + campoSelecionado + "\n\n");
					System.out.println("\n\n" + fields[table.getSelectedColumn() +2] + "\n\n");
					
					JTextField textValue = new JTextField(20);
			        JPanel panel = new JPanel();
			        panel.add(new JLabel("Informe o novo valor:"));
			        panel.add(textValue);

			        confirmEdit = JOptionPane.showConfirmDialog(null, panel, "Atualizar Item", JOptionPane.OK_CANCEL_OPTION);
			        if (confirmEdit == JOptionPane.OK_OPTION) {
			            String valorAtualizado = textValue.getText();
			            status = service.atualizar(valorAtualizado, campoSelecionado, id);
			        
						if(status != 0) { 
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
				}
				else {
					JOptionPane.showMessageDialog(
						null,
						"Selecione um valor na tabela!",
						"Atualizar Produto", 
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
