package classes.seeders;

public class Seeder {
	
	public static void carregarDados() {
		
		CategoriaDBSeeder dbCategoria = new CategoriaDBSeeder();
		ProdutoDBSeeder dbProduto = new ProdutoDBSeeder();
		NivelUsuarioDBSeeder dbNivelUsuario = new NivelUsuarioDBSeeder();
		InstitucionalDBSeeder dbInstitucional = new InstitucionalDBSeeder();
		
		dbCategoria.importar();
		dbProduto.importar();
		//dbNivelUsuario.importar(); // Já está no BD
		dbInstitucional.importar();
	}

}
