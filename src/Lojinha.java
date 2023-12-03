import classes.seeders.Seeder;
import classes.services.InstitucionalService;
import classes.services.ProdutoService;
import classes.utils.AuthenticatedUser;
import classes.views.Login;

public class Lojinha {

	public static void main(String[] args) {
		
		/*private UsuarioLogado usuarioLogado = new UsuarioLogado()
		
		if(usuarioLogado == null){
			iniciarSecao();
		}
		private void iniciarSecao(){
			
		}
		// O método carregarDados() Salva dados para teste 
		// no banco de dados automaticamente.
		// Deve ser chamado apenas uma vez.
		
		//Seeder.carregarDados(); 

		ProdutoService produto = new ProdutoService();
		CategoriaService categoria = new CategoriaService();
		InstitucionalService instituicao = new InstitucionalService();
		
		
		// ~~ Mostrar dados institucionais 
		//instituicao.listar();
		
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
		
		//AuthenticatedUser usuarioLogado = new AuthenticatedUser();
		Login login = new Login();
	
		//usuarioLogado = login.getUserAuthentication();
	}

}
