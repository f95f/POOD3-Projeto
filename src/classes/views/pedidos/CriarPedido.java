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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

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
import classes.models.Pedido;
import classes.models.Produto;
import classes.services.PedidoService;
import classes.services.ProdutoService;
import classes.services.UsuarioService;
import classes.utils.AuthenticatedUser;
import classes.utils.ProdutoDTO;
import classes.utils.UserDTO;
import classes.views.Painel;

public class CriarPedido {
	
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
	JComboBox<UserDTO> clientesList = new JComboBox<UserDTO>();
	JComboBox<ProdutoDTO> produtosList = new JComboBox<ProdutoDTO>();
	JComboBox<Pedido> pedidosList = new JComboBox<>();
	JComboBox<String> estadosList = new JComboBox<String>();
	PedidoService service = new PedidoService();
	
	public CriarPedido(AuthenticatedUser usuario){
		
		this.nomeUsuarioLogado.setText("Novo Pedido");
		this.usuarioLogado = usuario;
		render();
		
	}
	private void render() {

		frame = new JFrame();

		this.frame.setTitle("Lojinha - Registrar Pedidos");
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setSize(800, 680);
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
		
		JButton btnNovoPedido = new JButton("Adicionar");
		setButtonStyle(btnNovoPedido, corEscura);
		btnNovoPedido.addActionListener(new ActionListener() {
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
		controlButtonsPannel.add(btnNovoPedido);
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
		
		Pedido pedido = new Pedido();
		
		pedido.setIdUsuario(((UserDTO) clientesList.getSelectedItem()).getIdUsuario());
		pedido.setIdProduto(((ProdutoDTO) produtosList.getSelectedItem()).getIdProduto());
		pedido.setDtPedido(LocalDateTime.now().toString());
		pedido.setDtPagamento(LocalDateTime.now().plusDays(30).toString());
		pedido.setDtNotaFiscal(LocalDateTime.now().plusDays(5).toString());
		pedido.setNotaFiscal(textFields.get(0).getText());
		pedido.setDtEnvio(LocalDateTime.now().plusDays(15).toString());
		pedido.setTipoFrete(1);
		pedido.setDtRecebimento(LocalDateTime.now().plusDays(18).toString());
		pedido.setRastreioFrete("000");
		pedido.setEntregaendereco(textFields.get(1).getText());
		pedido.setEntregaNumero(textFields.get(2).getText());
		pedido.setEntregaBairro(textFields.get(3).getText());
		pedido.setEntregaCidade(textFields.get(4).getText());
		pedido.setEntregaUF((String) estadosList.getSelectedItem());
		pedido.setEntregaCEP(textFields.get(5).getText());
		pedido.setEntregaTelefone(textFields.get(6).getText());
		pedido.setEntregaRefer(textFields.get(7).getText());
		pedido.setValorTotal(textFields.get(8).getText());
		pedido.setQtdItems(Integer.parseInt(textFields.get(9).getText()));
		
		this.statusMessage.setText("Pedido registrado com sucesso.");
		service.criar(pedido);
	}
	
	private void placeForm(JPanel body) {
		String[] estados = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
		estadosList = new JComboBox<String>(estados);

		UsuarioService cliente = new UsuarioService();
		ArrayList<UserDTO> clientes = cliente.getClientes();
		for (UserDTO user : clientes) { clientesList.addItem(user); }
		
		ProdutoService produto = new ProdutoService();
		ArrayList<ProdutoDTO> produtos = produto.listar();
		for (ProdutoDTO item: produtos) { produtosList.addItem(item); }
		
		ArrayList<JPanel> inputContainers = new ArrayList<JPanel>();
		String[] labelValues = {"Cliente:", "Produto:", "Nota Fiscal:", "Rua:", "Número:", "Bairro:", "Cidade:", "Estado:", "CEP:", "Telefone:", "Referência:", "Valor Total:", "Quantidade de itens:"};
		String[] toolTipValues = {
			"Selecione o cliente",
			"Selecione o produto",
			"Informe a nota fiscal",
			"Informe a rua",
			"Informe o número",
			"Informe a bairro",
			"Informe a cidade",
			"Selecione o estado",
			"Informe o cep",
			"Informe um telefone",
			"Informe uma referência",
			"Informe o valor total do pedido",
			"Informe a quantidade de itens",
		};

		for(int i = 0; i < labelValues.length; i++) {
			
			JLabel label = new JLabel(labelValues[i]);
			label.setFont(contentFont);
			JTextField textField = new JTextField(30);
			
			if(i == 0) {
				clientesList.setToolTipText(toolTipValues[i]);
				clientesList.setBorder(new LineBorder(corEscura, 1));
			}
			else if(i == 1) {
				produtosList.setToolTipText(toolTipValues[i]);
				produtosList.setBorder(new LineBorder(corEscura, 1));
			}
			else if(i == 7) {
				estadosList.setToolTipText(toolTipValues[i]);
				estadosList.setBorder(new LineBorder(corEscura, 1));
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
			if( i == 0 ) {
				container.add(clientesList, BorderLayout.SOUTH);
			}
			else if( i == 1 ) {
				container.add(produtosList, BorderLayout.SOUTH);
			}
			else if( i == 7 ) {
				container.add(estadosList, BorderLayout.SOUTH);
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
