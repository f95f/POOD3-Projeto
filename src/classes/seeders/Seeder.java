package classes.seeders;

public class Seeder {
	
	public static void carregarDados() {
		
		CategoriaDBSeeder dbCategoria = new CategoriaDBSeeder();
		ProdutoDBSeeder dbProduto = new ProdutoDBSeeder();
		NivelUsuarioDBSeeder dbNivelUsuario = new NivelUsuarioDBSeeder();
		InstitucionalDBSeeder dbInstitucional = new InstitucionalDBSeeder();
		
		dbCategoria.importar();
		dbProduto.importar();
		//dbNivelUsuario.importar(); // J� est� no BD
		dbInstitucional.importar();
	}

}
