import classes.services.CategoriaService;

public class Lojinha {

	public static void main(String[] args) {
		
		CategoriaService categoria = new CategoriaService();
		
		//categoria.criar();	
		categoria.listar();
		
		/* A fun��o de buscar implementa polimorfismo: 
		 * Se a fun��o receber um inteiro por par�metro, buscar� por id.
		 * Se receber uma string, buscar� por esta string no campo "descri��o".*/
		//categoria.buscar(2);
		//categoria.buscar("CATEG");
		//categoria.apagar(4);
		//categoria.editar(3);
		
	}

}
