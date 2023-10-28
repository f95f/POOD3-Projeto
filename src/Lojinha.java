import classes.seeders.CategoriaDBSeeder;
import classes.seeders.ProdutoDBSeeder;
import classes.seeders.Seeder;
import classes.services.CategoriaService;
import classes.services.ProdutoService;

public class Lojinha {

	public static void main(String[] args) {
		
		Seeder.carregarDados(); // Salva dados para teste no banco de dados automaticamente.

		ProdutoService produto = new ProdutoService();
		CategoriaService categoria = new CategoriaService();
		
		
		/* ~~ CRUD com categorias: */
		//categoria.criar();	
		//categoria.listar();
		//categoria.buscar(2);
		//categoria.buscar("CATEG");
		//categoria.apagar(4);
		//categoria.editar(3);
		
		/* ~~ CRUD com produtos: */
		//produto.criar();
		//produto.listar();
		//produto.buscar(62);
		//produto.buscar("CARRO");
		//produto.buscarPorCategoria(5);
		//produto.apagar(73);
		
	}

}
