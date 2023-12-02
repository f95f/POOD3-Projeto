package classes.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import classes.models.Categoria;
import classes.models.Produto;
import classes.utils.ProdutoDTO;

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

	public Produto buscar(int id) {
		
		System.out.println("\n > Pesquisando produtos pelo código " + id + ":");
		
		ResultSet rs = produto.listById(id + "");

		try {
			if(rs.next()) {
				System.out.println("\n Código | Nome                           | Fabricante                     | Marca                | Modelo               | Cód. Categoria | Descrição                                                    | Medida          | Largura      | Altura       | Profundidade   | Peso           | cor               ");

				produto.setIdProduto(rs.getInt("idProduto"));
				produto.setFabricante(rs.getString("fabricante"));
				produto.setNome(rs.getString("nome"));
				produto.setMarca(rs.getString("marca"));
				produto.setModelo(rs.getString("modelo"));
				produto.setIdCategoria(rs.getInt("idCategoria"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setUnidadeMedida(rs.getString("unidadeMedida"));
				produto.setLargura(rs.getDouble("largura"));
				produto.setAltura(rs.getDouble("altura"));
				produto.setProfundidade(rs.getDouble("profundidade"));
				produto.setPeso(rs.getDouble("peso"));
				produto.setCor(rs.getString("cor"));
				
//				A linha abaixo formata cada linha da tabela para que estas mantenham o mesmo tamanho. 
				System.out.printf("\n %6d | %-30s | %-30s | %-20s | %-20s | %-14d | %-60s | %-15s | %6.3f       | %8.3f       | %6.3f       | %8.3f       | %-15s ",
						produto.getIdProduto(),
						produto.getNome(),
						produto.getFabricante(),
						produto.getMarca(),
						produto.getModelo(),
						produto.getIdCategoria(),
						produto.getDescricao(),
						produto.getUnidadeMedida(),
						produto.getLargura(),
						produto.getAltura(),
						produto.getProfundidade(),
						produto.getPeso(),
						produto.getCor()
				);
				return produto;
			}
			else {
				System.out.println("\n [!] Código não encontrado!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public void buscar(String nome) {
		nome.toLowerCase();
		System.out.println("\n > Pesquisando produtos por \"" + nome + "\":");
		
		ResultSet rs = produto.listByName(nome);
		System.out.println("\n Código | Nome                           | Fabricante                     | Marca                | Modelo               | Cód. Categoria | Descrição                                                    | Medida          | Largura      | Altura       | Profundidade   | Peso           | cor               ");
		
		try {
			if(rs.next()) {
				do {
					
					produto.setIdProduto(rs.getInt("idProduto"));
					produto.setFabricante(rs.getString("fabricante"));
					produto.setNome(rs.getString("nome"));
					produto.setMarca(rs.getString("marca"));
					produto.setModelo(rs.getString("modelo"));
					produto.setIdCategoria(rs.getInt("idCategoria"));
					produto.setDescricao(rs.getString("descricao"));
					produto.setUnidadeMedida(rs.getString("unidadeMedida"));
					produto.setLargura(rs.getDouble("largura"));
					produto.setAltura(rs.getDouble("altura"));
					produto.setProfundidade(rs.getDouble("profundidade"));
					produto.setPeso(rs.getDouble("peso"));
					produto.setCor(rs.getString("cor"));
					
	//				A linha abaixo formata cada linha da tabela para que estas mantenham o mesmo tamanho. 
					System.out.printf("\n %6d | %-30s | %-30s | %-20s | %-20s | %-14d | %-60s | %-15s | %6.3f       | %8.3f       | %6.3f       | %8.3f       | %-15s ",
							produto.getIdProduto(),
							produto.getNome(),
							produto.getFabricante(),
							produto.getMarca(),
							produto.getModelo(),
							produto.getIdCategoria(),
							produto.getDescricao(),
							produto.getUnidadeMedida(),
							produto.getLargura(),
							produto.getAltura(),
							produto.getProfundidade(),
							produto.getPeso(),
							produto.getCor()
					);
				} while(rs.next()); 
			}
			else {
				System.out.println("\n [!] Nenhum produto encontrado!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void buscarPorCategoria(int idCategoria) {
		
		System.out.println("\n > Pesquisando produtos pela categoria cód. " + idCategoria + ":");
		
		System.out.println("\n Código | Nome                           | Fabricante                     | Marca                | Modelo               | Cód. Categoria | Descrição                                                    | Medida          | Largura      | Altura       | Profundidade   | Peso           | cor               ");

		ResultSet rs = produto.listByCategoria(idCategoria);
		
		try {
			while(rs.next()) {
				
				produto.setIdProduto(rs.getInt("idProduto"));
				produto.setFabricante(rs.getString("fabricante"));
				produto.setNome(rs.getString("nome"));
				produto.setMarca(rs.getString("marca"));
				produto.setModelo(rs.getString("modelo"));
				produto.setIdCategoria(rs.getInt("idCategoria"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setUnidadeMedida(rs.getString("unidadeMedida"));
				produto.setLargura(rs.getDouble("largura"));
				produto.setAltura(rs.getDouble("altura"));
				produto.setProfundidade(rs.getDouble("profundidade"));
				produto.setPeso(rs.getDouble("peso"));
				produto.setCor(rs.getString("cor"));
				
//				A linha abaixo formata cada linha da tabela para que estas mantenham o mesmo tamanho. 
				System.out.printf("\n %6d | %-30s | %-30s | %-20s | %-20s | %-14d | %-60s | %-15s | %6.3f       | %8.3f       | %6.3f       | %8.3f       | %-15s ",
						produto.getIdProduto(),
						produto.getNome(),
						produto.getFabricante(),
						produto.getMarca(),
						produto.getModelo(),
						produto.getIdCategoria(),
						produto.getDescricao(),
						produto.getUnidadeMedida(),
						produto.getLargura(),
						produto.getAltura(),
						produto.getProfundidade(),
						produto.getPeso(),
						produto.getCor()
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int excluir(int id) {
		return this.produto.delete(id);
		
	}

	public int atualizar(String valorAtualizado, String campo, int id) {
		return this.produto.editar(valorAtualizado, campo, id);
	}

}
