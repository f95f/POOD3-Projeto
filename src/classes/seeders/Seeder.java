package classes.seeders;

import classes.services.CategoriaService;

public class Seeder {
	
	public static void carregarDados() {
		
		CategoriaDBSeeder dbCategoria = new CategoriaDBSeeder();
		ProdutoDBSeeder dbProduto = new ProdutoDBSeeder();

		dbCategoria.importar();
		dbProduto.importar();
	}

}
