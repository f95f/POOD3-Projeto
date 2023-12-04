package classes.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import classes.models.Categoria;
import classes.models.Produto;
import classes.models.Usuario;
import classes.utils.ProdutoDTO;
import classes.utils.UserDTO;

public class ProdutoService {

	private Produto produto = new Produto();
	private Categoria categoria = new Categoria();
	private Scanner scanner = new Scanner(System.in);
	
	public int criar(Produto produtoCriado) {
		
		int saveStatus = produtoCriado.save();
		
		if(saveStatus != 0) {
			
			System.out.println("\n\n [i] Peoduto salvo com sucesso!\n");
			
		}
		else {

			System.out.println("\n\n [!] Erro ao salvar!\n");

		}
		return saveStatus;
		
	}

	public ArrayList<ProdutoDTO> listar() {
		
		ArrayList<ProdutoDTO> produtosList = new ArrayList<ProdutoDTO>();
		
		ResultSet rs = produto.listAll();
		
		try {
			while(rs.next()) {
				
				ProdutoDTO produtoDTO = new ProdutoDTO();
						
				produtoDTO.setIdProduto(rs.getInt("idproduto"));
				produtoDTO.setFabricante(rs.getString("fabricante"));
				produtoDTO.setNome(rs.getString("nome"));
				produtoDTO.setMarca(rs.getString("marca"));
				produtoDTO.setModelo(rs.getString("modelo"));
				produtoDTO.setCategoria(
						getCategoria(rs.getInt("idCategoria")));
				
				produtoDTO.setDescricao(rs.getString("descricao"));
				produtoDTO.setUnidadeMedida(rs.getString("unidadeMedida"));
				produtoDTO.setDimensoes(
						rs.getDouble("largura") + " x " + rs.getDouble("altura") + " x " + rs.getDouble("profundidade"));

				produtoDTO.setPeso(rs.getDouble("peso") + " Kg");
				produtoDTO.setCor(rs.getString("cor"));
				produtosList.add(produtoDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtosList;

	}
	
	private String getCategoria(int id) {
		
		String nomeCategoria = "Sem Categoria";
		ResultSet rs = categoria.listById(id + "");
		try {
			if(rs.next()) {
				nomeCategoria = rs.getString("descricao");			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nomeCategoria;	
	}

	public int excluir(int id) {
		return this.produto.delete(id);
		
	}

	public int atualizar(String valorAtualizado, String campo, int id) {
		return this.produto.editar(valorAtualizado, campo, id);
	}

	public ArrayList<ProdutoDTO> searchBy(String field, String searchTerm) {
		
		ArrayList<ProdutoDTO> produtosList = new ArrayList<ProdutoDTO>();
		searchTerm.toLowerCase();
		ResultSet rs = produto.listByName(searchTerm);
		try {
			while(rs.next()) {
				
				ProdutoDTO produtoDTO = new ProdutoDTO();
						
				produtoDTO.setIdProduto(rs.getInt("idproduto"));
				produtoDTO.setFabricante(rs.getString("fabricante"));
				produtoDTO.setNome(rs.getString("nome"));
				produtoDTO.setMarca(rs.getString("marca"));
				produtoDTO.setModelo(rs.getString("modelo"));
				produtoDTO.setCategoria(
						getCategoria(rs.getInt("idCategoria")));
				
				produtoDTO.setDescricao(rs.getString("descricao"));
				produtoDTO.setUnidadeMedida(rs.getString("unidadeMedida"));
				produtoDTO.setDimensoes(
						rs.getDouble("largura") + " x " + rs.getDouble("altura") + " x " + rs.getDouble("profundidade"));

				produtoDTO.setPeso(rs.getDouble("peso") + " Kg");
				produtoDTO.setCor(rs.getString("cor"));
				produtosList.add(produtoDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtosList;
	}

	public ArrayList<ProdutoDTO> searchByCategoria(int id) {
		ArrayList<ProdutoDTO> produtosList = new ArrayList<ProdutoDTO>();
		ResultSet rs = produto.listByCategoria(id);
		
		if(id == 0) {
			produtosList = this.listar();
		}
		else {
			try {
				while(rs.next()) {
					ProdutoDTO produtoDTO = new ProdutoDTO();
					produtoDTO.setIdProduto(rs.getInt("idproduto"));
					produtoDTO.setFabricante(rs.getString("fabricante"));
					produtoDTO.setNome(rs.getString("nome"));
					produtoDTO.setMarca(rs.getString("marca"));
					produtoDTO.setModelo(rs.getString("modelo"));
					produtoDTO.setCategoria(
							getCategoria(rs.getInt("idCategoria")));
					
					produtoDTO.setDescricao(rs.getString("descricao"));
					produtoDTO.setUnidadeMedida(rs.getString("unidadeMedida"));
					produtoDTO.setDimensoes(
							rs.getDouble("largura") + " x " + rs.getDouble("altura") + " x " + rs.getDouble("profundidade"));

					produtoDTO.setPeso(rs.getDouble("peso") + " Kg");
					produtoDTO.setCor(rs.getString("cor"));
					
					produtosList.add(produtoDTO);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return produtosList;
	}
	
	public ProdutoDTO buscarPorId(int id) {
		
		ResultSet rs = produto.listById(id + "");
		
		try {
			if(rs.next()) {
				
				ProdutoDTO produtoDTO = new ProdutoDTO();
				produtoDTO.setIdProduto(rs.getInt("idproduto"));
				produtoDTO.setFabricante(rs.getString("fabricante"));
				produtoDTO.setNome(rs.getString("nome"));
				produtoDTO.setMarca(rs.getString("marca"));
				produtoDTO.setModelo(rs.getString("modelo"));
				produtoDTO.setCategoria(
						getCategoria(rs.getInt("idCategoria")));
				
				produtoDTO.setDescricao(rs.getString("descricao"));
				produtoDTO.setUnidadeMedida(rs.getString("unidadeMedida"));
				produtoDTO.setDimensoes(
						rs.getDouble("largura") + " x " + rs.getDouble("altura") + " x " + rs.getDouble("profundidade"));

				produtoDTO.setPeso(rs.getDouble("peso") + " Kg");
				produtoDTO.setCor(rs.getString("cor"));
					
				return produtoDTO;
			}
			else {
				System.out.println("\n [!] Código não encontrado!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	} 
}
