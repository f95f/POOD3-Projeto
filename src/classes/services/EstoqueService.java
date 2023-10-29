package classes.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import classes.models.Estoque;

public class EstoqueService {

	private Estoque estoque = new Estoque();
	private Scanner scanner = new Scanner(System.in);
	
	public void criar() {
		
		estoque.setIdEstoque(0);
		
		System.out.print("\n > Informe o produto: ");
		estoque.setIdProduto(Integer.parseInt(scanner.nextLine()));
		
		System.out.print("\n > Informe a data de entrada: ");
		estoque.setDtEntrada(scanner.nextLine());
		
		System.out.print("\n > Informe a quantidade: ");
		estoque.setQuantidade(Integer.parseInt(scanner.nextLine()));
		
		System.out.print("\n > Informe a data de fabricação: ");
		estoque.setDtFabricacao(scanner.nextLine());
		
		System.out.print("\n > Informe a data de vencimento: ");
		estoque.setDtVencimento(scanner.nextLine());
		
		System.out.print("\n > Informe a nota fiscal de compra: ");
		estoque.setNfCompra(scanner.nextLine());
		
		System.out.print("\n > Informe o preço de compra: ");
		estoque.setPrecoCompra(Double.parseDouble(scanner.nextLine().trim()));
		
		System.out.print("\n > Informe o ICMS de compra: ");
		estoque.setIcmsCompra(Double.parseDouble(scanner.nextLine().trim()));

		System.out.print("\n > Informe o preço de venda: ");
		estoque.setPrecoVenda(Double.parseDouble(scanner.nextLine().trim()));

		System.out.print("\n > Informe a quantidade vendida: ");
		estoque.setQtdVendida(Integer.parseInt(scanner.nextLine()));
		
		System.out.print("\n > Informe a quantidade de ocorrência: ");
		estoque.setQtdOcorrencia(Integer.parseInt(scanner.nextLine()));
		
		System.out.print("\n > Informe o motivo da ocorrência: ");
		estoque.setOcorrencia(scanner.nextLine());
		
		int saveStatus = estoque.save();
		
		if(saveStatus != 0) {
			
			System.out.println("\n\n [i] Registro salvo com sucesso!\n");
			
		}
		else {

			System.out.println("\n\n [!] Erro ao salvar!\n");

		}
	}
	
	public void listar() {
		
		System.out.println("\n > Listando estoque: ");
//		System.out.println("\n Código | Nome                           | Fabricante                     | Marca                | Modelo               | Cód. Categoria | Descrição                                                    | Medida          | Largura      | Altura       | Profundidade   | Peso           | cor               ");

		ResultSet rs = estoque.listAll();
		
		try {
			while(rs.next()) {
				
				estoque.setIdEstoque(rs.getInt("idEstoque"));
				estoque.setIdProduto(rs.getInt("idProduto"));
				estoque.setDtEntrada(rs.getString("dtEntrada"));
				estoque.setQuantidade(rs.getInt("quantidade"));
				estoque.setDtFabricacao(rs.getString("dtFabricacao"));
				estoque.setDtVencimento(rs.getString("dtVencimento"));
				estoque.setNfCompra(rs.getString("nfCompra"));
				estoque.setPrecoCompra(rs.getDouble("precoCompra"));
				estoque.setIcmsCompra(rs.getDouble("icmsCompra"));
				estoque.setPrecoVenda(rs.getDouble("precoVenda"));
				estoque.setQtdVendida(rs.getInt("qtdVendida"));
				estoque.setQtdOcorrencia(rs.getInt("qtdOcorrencia"));
				estoque.setOcorrencia(rs.getString("ocorrencia"));
				
//				A linha abaixo formata cada linha da tabela para que estas mantenham o mesmo tamanho. 
				System.out.printf("\n %6d | %6d  | %-10s | %-6d | %-10s | %-10d | %-20s | %-10f | %-10f | %-10f | %-6d| %s" + 
					estoque.getIdEstoque(),
					estoque.getIdProduto(),
					estoque.getDtEntrada(),
					estoque.getQuantidade(),
					estoque.getDtFabricacao(),
					estoque.getDtVencimento(),
					estoque.getNfCompra(),
					estoque.getPrecoCompra(),
					estoque.getIcmsCompra(),
					estoque.getPrecoVenda(),
					estoque.getQtdVendida(),
					estoque.getQtdOcorrencia(),
					estoque.getOcorrencia()
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	public Estoque buscarPorId(String coluna, int id) {
		System.out.println("\n > Pesquisando estoque pelo código " + id + ":");
		
		ResultSet rs = estoque.listById(coluna, id + "");
		
		try {
			if(rs.next()) {
				
				estoque.setIdEstoque(rs.getInt("idEstoque"));
				estoque.setIdProduto(rs.getInt("idProduto"));
				estoque.setDtEntrada(rs.getString("dtEntrada"));
				estoque.setQuantidade(rs.getInt("quantidade"));
				estoque.setDtFabricacao(rs.getString("dtFabricacao"));
				estoque.setDtVencimento(rs.getString("dtVencimento"));
				estoque.setNfCompra(rs.getString("nfCompra"));
				estoque.setPrecoCompra(rs.getDouble("precoCompra"));
				estoque.setIcmsCompra(rs.getDouble("icmsCompra"));
				estoque.setPrecoVenda(rs.getDouble("precoVenda"));
				estoque.setQtdVendida(rs.getInt("qtdVendida"));
				estoque.setQtdOcorrencia(rs.getInt("qtdOcorrencia"));
				estoque.setOcorrencia(rs.getString("ocorrencia"));

//				A linha abaixo formata cada linha da tabela para que estas mantenham o mesmo tamanho. 
				System.out.printf("\n %6d | %6d  | %-10s | %-6d | %-10s | %-10d | %-20s | %-10f | %-10f | %-10f | %-6d| %s" + 
					estoque.getIdEstoque(),
					estoque.getIdProduto(),
					estoque.getDtEntrada(),
					estoque.getQuantidade(),
					estoque.getDtFabricacao(),
					estoque.getDtVencimento(),
					estoque.getNfCompra(),
					estoque.getPrecoCompra(),
					estoque.getIcmsCompra(),
					estoque.getPrecoVenda(),
					estoque.getQtdVendida(),
					estoque.getQtdOcorrencia(),
					estoque.getOcorrencia()
				);
				
				return estoque;
			}
			else {
				System.out.println("\n [!] Código não encontrado!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void apagar(int id) {
		
		System.out.println("\n > Apagando registro de estoque");
		estoque = this.buscarPorId("idEstoque", id);
			
		if(estoque == null) { return;	}
		
		String resposta = "";
		do {
			System.out.println("\n\n > Confirmar exclusão? [sim/não]: ");
			resposta = scanner.next().toLowerCase();
			if(resposta.equals("sim") || resposta.equals("s")) {
				
				int deleteStatus = estoque.delete();
				
				if(deleteStatus == 1) {
					System.out.println("\n [i] Registro apagado!");
				}
				else {
					System.out.println("\n [!] Erro ao apagar!\n");	
				}
				break;
			}
			if(resposta.equals("não") || resposta.equals("n") || resposta.equals("nao")) {
				System.out.println("\n [i] Nenhum registro apagado.\n");
				break;
			}
		}
		while(true);
	}
}
