package classes.seeders;

public class Seeder {
	
	public static void carregarDados() {
		
		CategoriaDBSeeder.importar();
		ProdutoDBSeeder.importar();
		//NivelUsuarioDBSeeder.importar(); // Já está no BD
		InstitucionalDBSeeder.importar();
		EstoqueDBSeeder.importar();
		NivelUsuarioDBSeeder.importar();
		PedidoDBSeeder.importar();
		UsuarioDBSeeder.importar();
		ItensPedidoDBSeeder.importar();
		
	}

}
