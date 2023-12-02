package classes.views.produtos;

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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import classes.models.Categoria;
import classes.models.Produto;
import classes.models.Usuario;
import classes.services.CategoriaService;
import classes.services.ProdutoService;
import classes.services.UsuarioService;
import classes.utils.AuthenticatedUser;
import classes.views.Painel;
import classes.views.usuarios.ListUsers;

public class CreateProduto {

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
	JComboBox<Categoria> categoriasList = new JComboBox<>();
	
	public CreateProduto(AuthenticatedUser usuario){
		
		this.nomeUsuarioLogado.setText("Cadastrar Produtos");
		this.usuarioLogado = usuario;
		render();
		
	}
	private void render() {

		frame = new JFrame();

		this.frame.setTitle("Lojinha - Adicionar Produtos");
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
		
		JButton btnNovoProduto = new JButton("Adicionar");
		setButtonStyle(btnNovoProduto, corEscura);
		btnNovoProduto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { salvarProduto(); }
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
		controlButtonsPannel.add(btnNovoProduto);
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

	private void salvarProduto() {
		
		Produto produto = new Produto();
		ProdutoService service = new ProdutoService();
		
		produto.setNome(textFields.get(0).getText());
		produto.setFabricante(textFields.get(1).getText());
		produto.setMarca(textFields.get(2).getText());
		produto.setModelo(textFields.get(3).getText());
		
		Categoria selectedCategoria = (Categoria) categoriasList.getSelectedItem();
		produto.setIdCategoria(selectedCategoria.getIdCategoria());
		produto.setDescricao(textFields.get(4).getText());
		produto.setUnidadeMedida(textFields.get(5).getText());
		produto.setLargura(Double.parseDouble(textFields.get(6).getText()));
		produto.setAltura(Double.parseDouble(textFields.get(7).getText()));
		produto.setProfundidade(Double.parseDouble(textFields.get(8).getText()));
		produto.setPeso(Double.parseDouble(textFields.get(9).getText()));
		produto.setCor(textFields.get(10).getText());
		
		this.statusMessage.setText("Produto adicionado por sucesso.");
		
		service.criar(produto);
	}
	
	private void placeForm(JPanel body) {
		CategoriaService service = new CategoriaService();
		
		ArrayList<Categoria> categorias = service.listar();

		for (Categoria item : categorias) { 
			categoriasList.addItem(new Categoria(item.getIdCategoria(), item.getDescricao())); 
		}
		
		ArrayList<JPanel> inputContainers = new ArrayList<JPanel>();
		String[] labelValues = {"Nome:", "Fabricante:", "Marca:", "Modelo:", "Categoria:", "Descrição:", "Medida:", "Largura:", "Altura:", "Profundidade:", "Peso:", "Cor:"};
		String[] toolTipValues = {
			"Informe o nome",
			"Informe o fabricante",
			"Informe a marca",
			"Informe o modelo",
			"Selecione a categoria",
			"Descreva o produto",
			"Unidade de medida do produto",
			"Informe a altura",
			"Informe a largura",
			"Informe a profundidade",
			"Informe o peso",
			"Informe a cor"
		};

		for(int i = 0; i < labelValues.length; i++) {
			
			JLabel label = new JLabel(labelValues[i]);
			label.setFont(contentFont);
			JTextField textField = new JTextField(30);

			if(i == 4) {
				categoriasList.setToolTipText(toolTipValues[i]);
				categoriasList.setBorder(new LineBorder(corEscura, 1));
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
			if( i == 4 ) {
				container.add(categoriasList, BorderLayout.SOUTH);
			}
			else {
				container.add(textField, BorderLayout.SOUTH);				
			}
			
			//container.add(textField, BorderLayout.SOUTH);				
			container.setPreferredSize(formSize);
			container.setBorder(margem);
			container.setBackground(corClara);
			
			body.add(container);
		}
	}
	
	private void voltar() {	
		frame.dispose();
		Painel painel = new Painel(usuarioLogado);	
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
