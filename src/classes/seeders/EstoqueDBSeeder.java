package classes.seeders;

import classes.models.Estoque;

public class EstoqueDBSeeder {
	public void importar() {
		gravar(1,"2023/10/10",20,"2023/10/02","2023/12/29","NF00000123456", 2400.00, 0.0, 0.0, 0, 0,"");
		gravar(2,"2023/10/10",10,"2023/10/02","2023/12/29","NF00000123456", 2400.00, 0.0, 0.0, 0, 0,"");
		gravar(3,"2023/10/10",40,"2023/10/02","2023/12/29","NF00000123456", 2400.00, 0.0, 0.0, 0, 0,"");
		gravar(4,"2023/10/10",26,"2023/10/02","2023/12/29","NF00000123456", 2400.00, 0.0, 0.0, 0, 0,"");
		gravar(5,"2023/10/10",20,"2023/10/02","2023/12/29","NF00000123456", 2400.00, 0.0, 0.0, 0, 0,"");
		gravar(6,"2023/10/10",8,"2023/10/02","2023/12/29","NF00000123456", 2400.00, 0.0, 0.0, 0, 0,"");
		gravar(7,"2023/10/10",5,"2023/10/02","2023/12/29","NF00000123456", 2400.00, 0.0, 0.0, 0, 0,"");
		gravar(8,"2023/10/10",35,"2023/10/02","2023/12/29","NF00000123456", 2400.00, 0.0, 0.0, 0, 0,"");
		
	}
	
	private void gravar(int idProduto, String dtEntrada, int quantidade, String dtFabricacao,
			String dtVencimento, String nfCompra, Double precoCompra, Double icmsCompra, Double precoVenda,
			int qtdVendida, int qtdOcorrencia, String ocorrencia) {
		
		Estoque e = new Estoque(0, idProduto, dtEntrada, quantidade, dtFabricacao,
				dtVencimento, nfCompra, precoCompra, icmsCompra, precoVenda,
				qtdVendida, qtdOcorrencia, ocorrencia);
		e.save();
		
	}
}
