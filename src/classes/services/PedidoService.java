package classes.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import classes.models.Pedido;
import classes.models.Usuario;
import classes.utils.PedidoDTO;

public class PedidoService {

	private Pedido pedido = new Pedido();
	
	public int criar(Pedido pedido) {
		return pedido.save();
	}

	public ArrayList<PedidoDTO> listar() {

		ArrayList<PedidoDTO> pedidosList = new ArrayList<PedidoDTO>();
		ResultSet rs = pedido.listAll();
		
		try {
			while(rs.next()) {
				PedidoDTO pedido = new PedidoDTO();
				
				pedido.setIdPedido(rs.getInt("idPedido"));
				pedido.setNomeUsuario(getUsuario(rs.getInt("idUsuario")));
				pedido.setNomeItem(getProduto(rs.getInt("idProduto")));
				pedido.setDtPedido(rs.getString("dtPedido"));
				pedido.setDtPagamento(rs.getString("dtPagamento"));
				pedido.setNotaFiscal(rs.getString("notaFiscal"));
				pedido.setDtEnvio(rs.getString("dtEnvio"));
				pedido.setDtRecebimento(rs.getString("dtRecebimento"));
				pedido.setEntregaEndereco(
					rs.getString("entregaEndereco"),
					rs.getString("entregaNumero"),
					rs.getString("entregaBairro"),
					rs.getString("entregaCidade"),
					rs.getString("entregaUF")
				);
				pedido.setEntregaCEP(rs.getString("entregaCEP"));
				pedido.setEntregaTelefone(rs.getString("entregaTelefone"));
				pedido.setEntregaRefer(rs.getString("entregaRefer"));
				pedido.setValorTotal(rs.getString("valorTotal"));
				pedido.setQtdItems(rs.getInt("qtdItems"));
				
				pedidosList.add(pedido);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pedidosList;
	}

	public Pedido buscarPorId(int id) {
		System.out.println("\n > Pesquisando pedido pelo código " + id + ":");
		
		ResultSet rs = pedido.listBy("idPedido", id + "");
		
		try {
			if(rs.next()) {
				
				pedido.setIdPedido(rs.getInt("idPedido"));
				pedido.setIdUsuario(rs.getInt("idUsuario"));
				pedido.setDtPedido(rs.getString("dtPedido"));
				pedido.setDtPagamento(rs.getString("dtPagamento"));
				pedido.setDtNotaFiscal(rs.getString("dtNotaFiscal"));
				pedido.setNotaFiscal(rs.getString("notaFiscal"));
				pedido.setDtEnvio(rs.getString("dtEnvio"));
				pedido.setDtRecebimento(rs.getString("dtRecebimento"));
				pedido.setTipoFrete(rs.getInt("tipoFrete"));
				pedido.setRastreioFrete(rs.getString("rastreioFrete"));
				pedido.setEntregaendereco(rs.getString("entregaendereco"));
				pedido.setEntregaNumero(rs.getString("entregaNumero"));
				pedido.setEntregaBairro(rs.getString("entregaBairro"));
				pedido.setEntregaCidade(rs.getString("entregaCidade"));
				pedido.setEntregaUF(rs.getString("entregaUF"));
				pedido.setEntregaCEP(rs.getString("entregaCEP"));
				pedido.setEntregaTelefone(rs.getString("entregaTelefone"));
				pedido.setEntregaRefer(rs.getString("entregaRefer"));
				pedido.setValorTotal(rs.getString("valorTotal"));
				pedido.setQtdItems(rs.getInt("qtdItems"));
				
				return pedido;
			}
			else {
				System.out.println("\n [!] Código não encontrado!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<PedidoDTO> buscarPor(String coluna, String valor) {
		ArrayList<PedidoDTO> pedidosList = new ArrayList<PedidoDTO>();
		ResultSet rs = pedido.listBy(coluna, valor);
		
		try {
			if(rs.next()) {
				
				PedidoDTO pedido = new PedidoDTO();
				pedido.setIdPedido(rs.getInt("idPedido"));
				pedido.setNomeUsuario(getUsuario(rs.getInt("idUsuario")));
				pedido.setNomeItem(getProduto(rs.getInt("idProduto")));
				pedido.setDtPedido(rs.getString("dtPedido"));
				pedido.setDtPagamento(rs.getString("dtPagamento"));
				pedido.setNotaFiscal(rs.getString("notaFiscal"));
				pedido.setDtEnvio(rs.getString("dtEnvio"));
				pedido.setDtRecebimento(rs.getString("dtRecebimento"));
				pedido.setEntregaEndereco(
					rs.getString("entregaEndereco"),
					rs.getString("entregaNumero"),
					rs.getString("entregaBairro"),
					rs.getString("entregaCidade"),
					rs.getString("entregaUF")
				);
				pedido.setEntregaCEP(rs.getString("entregaCEP"));
				pedido.setEntregaTelefone(rs.getString("entregaTelefone"));
				pedido.setEntregaRefer(rs.getString("entregaRefer"));
				pedido.setValorTotal(rs.getString("valorTotal"));
				pedido.setQtdItems(rs.getInt("qtdItems"));
				
				System.out.println("\n\n >> " + pedido.getNomeUsuario());
				pedidosList.add(pedido);
			}
			else {
				System.out.println("\n [!] Código não encontrado!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pedidosList;
	}
	
	public int apagar(int id) {
		return pedido.delete(id);
	}
	public int atualizar(String valorAtualizado, String campo, int id) {
		return this.pedido.editar(valorAtualizado, campo, id);
	}
	private String getUsuario(int idUsuario) {
		UsuarioService usuario = new UsuarioService();
		return usuario.buscarPorId(idUsuario).getNome();
	}

	private String getProduto(int idProduto) {
		ProdutoService produto = new ProdutoService();
		return produto.buscarPorId(idProduto).getNome();
	}
	public ArrayList<PedidoDTO> searchByUser(int idUsuario) {
		if(idUsuario == 0) {
			return this.listar();
		}
		
		return this.buscarPor("idUsuario", idUsuario + "");
	}

	public ArrayList<PedidoDTO> searchByProduto(int idProduto) {
		if(idProduto == 0) {
			return this.listar();
		}
		
		return this.buscarPor("idProduto", idProduto + "");
	}
}
