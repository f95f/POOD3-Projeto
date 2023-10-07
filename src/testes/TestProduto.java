package testes;

import java.sql.ResultSet;
import java.sql.SQLException;

import classes.models.Produto;

public class TestProduto {

	private Produto produtoTeste = new Produto();

	public void testar() {
		
		System.out.println(" > Testando tabela de produtos: \n - Listar todos: \n\n");
		testarListAll();
		
		System.out.println("\n\n - Inserindo produto:\n");
		testarInsert();
		testarListAll();
		
		System.out.println("\n\n - Apagando produto:\n");
		testarRemove();
		testarListAll();
	
	}
	
	private void testarListAll() {
		
		ResultSet rs = produtoTeste.listAll();
		try {
			while(rs.next()) {
				String out = "";
				
				out += rs.getString("IdProduto");
				out += rs.getString("Fabricante");
				out += rs.getString("Nome");
				out += rs.getString("Marca");
				out +=  rs.getString("Modelo");
				out +=  rs.getString("IdCategoria");
				out +=  rs.getString("Descricao");
				out +=  rs.getString("UnidadeMedida");
				out +=  rs.getString("Largura");
				out +=  rs.getString("Altura");
				out +=  rs.getString("Profundidade");
				out +=  rs.getString("Peso");
				out +=  rs.getString("Cor");
				System.out.println(out);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void testarInsert() {
		
		this.produtoTeste = new Produto(0, "Fabricante1", "Laranja", "Lima", "Madura", 1, "Fruta kkkk", "g", .2, .2, .2, .5, "Amarelo");
		this.produtoTeste.save();
		
	}
	
	private void testarRemove() {
		
		this.produtoTeste.delete();
		
	}
	
}
