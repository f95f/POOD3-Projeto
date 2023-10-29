package classes.seeders;

import classes.models.Categoria;

public class CategoriaDBSeeder {

	public static void importar() {
		
		gravar("Eletr�nicos");
		gravar("Eletrodom�sticos");
		gravar("Celulares e Smartphones");
		gravar("Inform�tica");
		gravar("Alimentos");
		gravar("Bebidas");
		gravar("Vestu�rio e Acess�rios");
		gravar("Automotivo");
		gravar("M�veis e Decora��o");
		gravar("Livros");
		gravar("Jogos");
		gravar("M�sica");
		gravar("Filmes e S�ries");
		gravar("Brinquedos");
		gravar("Artigos para Festa");
		gravar("Papelaria");
		gravar("Esporte");
		gravar("Higiene e Limpeza");
		gravar("Sa�de e Bem-Estar");
		gravar("Casa e Constru��o");
		gravar("Cama, Mesa e Banho");
		gravar("Outros");
		
	}
	
	private static void gravar(String nomeCategoria) {
		
		Categoria c = new Categoria(0, nomeCategoria);
		c.save();
		
	}
}
