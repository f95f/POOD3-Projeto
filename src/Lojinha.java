import classes.seeders.CategoriaDBSeeder;
import classes.seeders.ProdutoDBSeeder;
import classes.services.CategoriaService;
import classes.services.ProdutoService;

public class Lojinha {

	public static void main(String[] args) {
		
		CategoriaService categoria = new CategoriaService();
		ProdutoService produto = new ProdutoService();
		CategoriaDBSeeder dbCategoria = new CategoriaDBSeeder();
		ProdutoDBSeeder dbProduto = new ProdutoDBSeeder();
		
		//dbCategoria.importar();
		//dbProduto.importar();
		//categoria.criar();	
		//categoria.listar();
		//produto.listar();
		
		//categoria.buscar(2);
		//categoria.buscar("CATEG");
		
		//categoria.apagar(4);
		//categoria.editar(3);
		
		//produto.criar();
		//produto.listar();
		//produto.buscar(62);
		//produto.buscar("CARRO");
		
		produto.buscarPorCategoria(5);
		produto.apagar(73);
		
	}

}
