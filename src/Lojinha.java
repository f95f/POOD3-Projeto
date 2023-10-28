
import classes.seeders.InstitucionalDBSeeder;
import classes.seeders.Seeder;
import classes.services.CategoriaService;
import classes.services.InstitucionalService;
import classes.services.ProdutoService;

public class Lojinha {

	public static void main(String[] args) {
		
		// O método carregarDados() Salva dados para teste 
		// no banco de dados automaticamente.
		// Deve ser chamado apenas uma vez.
		
		//Seeder.carregarDados(); 

		ProdutoService produto = new ProdutoService();
		CategoriaService categoria = new CategoriaService();
		InstitucionalService instituicao = new InstitucionalService();
		
		
		// ~~ Mostrar dados institucionais 
		instituicao.listar();
		
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
