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
		
		/* A função de buscar implementa polimorfismo: 
		 * Se a função receber um inteiro por parâmetro, buscará por id.
		 * Se receber uma string, buscará por esta string no campo "descrição".*/
		//categoria.buscar(2);
		//categoria.buscar("CATEG");
		
		//categoria.apagar(4);
		//categoria.editar(3);
		
		produto.criar();
		//produto.listar();
	}

}
