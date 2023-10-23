package classes.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import classes.models.Produto;

public class ProdutoService {

	private Produto produto = new Produto();
	private Scanner scanner = new Scanner(System.in);
	
	public void criar() {
		
		String nomeProduto = "";
		
		do {
			
			System.out.print(" > Informe o nome do produto: ");
			nomeProduto = scanner.nextLine();
		
			if(nomeProduto.trim() == "") {
				System.out.println("\n [!] Nome inválido! \n");
			}
		}
		while(nomeProduto == "");

		produto.setIdProduto(0);
		
		produto.setNome(nomeProduto);
		
		System.out.print("\n > Informe o fabricante do produto: ");
		produto.setFabricante(scanner.nextLine());
		
		System.out.print("\n > Informe a marca do produto: ");
		produto.setMarca(scanner.nextLine());
		
		System.out.print("\n > Informe o modelo do produto: ");
		produto.setModelo(scanner.nextLine());
		
		System.out.print("\n > Informe o código da categoria do produto: ");
		produto.setIdCategoria(Integer.parseInt(scanner.nextLine().trim()));	
		// TODO: Buscar id da categoria informado para confirmar sua existência no BD.
		
		System.out.print("\n > Informe a descrição do produto: ");
		produto.setDescricao(scanner.nextLine());
		
		System.out.print("\n > Informe a unidade de medida do produto: ");
		produto.setUnidadeMedida(scanner.nextLine());
		
		System.out.print("\n > Informe a largura do produto: ");
		produto.setLargura(Double.parseDouble(scanner.nextLine()));
		
		System.out.print("\n > Informe a altura do produto: ");
		produto.setAltura(Double.parseDouble(scanner.nextLine()));
		
		System.out.print("\n > Informe a profundidade do produto: ");
		produto.setProfundidade(Double.parseDouble(scanner.nextLine()));
		
		System.out.print("\n > Informe o peso do produto: ");
		produto.setPeso(Double.parseDouble(scanner.nextLine()));
		
		System.out.print("\n > Informe a cor do produto: ");
		produto.setCor(scanner.nextLine());
		
		int saveStatus = produto.save();
		
		if(saveStatus != 0) {
			
			System.out.println("\n\n [i] Produto salvo com sucesso!\n");
			
		}
		else {

			System.out.println("\n\n [!] Erro ao salvar produto!\n");

		}
	}

	public void listar() {
		
		System.out.println("\n > Listando produtos: ");
		System.out.println("\n Código | Nome            | Fabricante      | Marca           | Modelo          | Cód. Categoria | Descrição            | Medida          | Largura      | Altura       | Profundidade | Peso         | cor               ");

		ResultSet rs = produto.listAll();
		
		try {
			while(rs.next()) {
				
				String linha = "";
				
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
				System.out.printf("\n %6d | %-15s | %-15s | %-15s | %-15s | %-14d | %-20s | %-15s | %6.4f       | %8.4f       | %6.4f       | %6.4f       | %-15s ",
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
	
	
}
