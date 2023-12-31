package classes.seeders;

import classes.models.Pedido;

public class PedidoDBSeeder {

	public static void importar() {
		gravar(1, 50, "10/10/2023", "10/10/2023", "11/10/2023",
				"NF20231028012345", "11/10/2023", "11/10/2023", 1, "20231028012345",
				"Rua A","B","C","DE", "Guarulhos", "SP", "01234987","115555-5555", "Ref. null",
				"259.60", 2);

		gravar(3, 45, "10/09/2023", "12/09/2023", "16/09/2023",
				"NF20231028054321", "13/09/2023", "14/09/2023", 1, "20212348012345",
				"Rua 1","2B","3C","4DE", "Guarulhos", "SP", "01212345","115555-6666", "Ref. null",
				"1295.80", 12);
		
		gravar(4, 80, "23/09/2023", "23/09/2023", "23/09/2023",
				"NF20231020000111", "23/09/2023", "24/09/2023", 1, "20212348012345",
				"Rua 1","2B","3C","4DE", "Guarulhos", "SP", "01212345","115555-6666", "Ref. null",
				"1295.80", 12);
	
	}
	
	private static void gravar(int idUsuario, int idProduto, String dtPedido, String dtPagamento, String dtNotaFiscal,
			String notaFiscal, String dtEnvio, String dtRecebimento, int tipoFrete, String rastreioFrete,
			String entregaendereco, String entregaNumero, String entregaCompl, String entregaBairro,
			String entregaCidade, String entregaUF, String entregaCEP, String entregaTelefone, String entregaRefer,
			String valorTotal, int qtdItems
		) {
		
		Pedido p = new Pedido(0,idUsuario, idProduto, dtPedido, dtPagamento, dtNotaFiscal,
				notaFiscal, dtEnvio, dtRecebimento, tipoFrete, rastreioFrete,
				entregaendereco, entregaNumero, entregaBairro,
				entregaCidade, entregaUF, entregaCEP, entregaTelefone, entregaRefer,
				valorTotal, qtdItems);
		p.save();
		
	}
}