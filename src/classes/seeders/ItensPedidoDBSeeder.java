package classes.seeders;

import classes.models.ItensPedido;

public class ItensPedidoDBSeeder {
	
	public void importar() {
		
		gravar(1, 1, 3, 2, "", "");
		gravar(2, 2, 2, 1, "", "");
		gravar(3, 3, 5, 1, "", "");
		gravar(4, 4, 1, 1, "2023/10/12", "Produto Danificado");
		gravar(5, 5, 2, 2, "", "");
		gravar(6, 6, 4, 1, "2023/10/26", "Desistência");
		
	}
	private void gravar(int ordem, int idPedido, int idEstoque, int qtdItem, String dtDevolucao, String motivoDevolucao) {
	
		
		ItensPedido i = new ItensPedido(0, ordem, idPedido, idEstoque, qtdItem, dtDevolucao, motivoDevolucao);
		i.save();
		
	}
}
