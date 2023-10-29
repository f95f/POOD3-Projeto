package classes.seeders;

import classes.models.Categoria;

public class CategoriaDBSeeder {

	public static void importar() {
		
		gravar("Eletrônicos");
		gravar("Eletrodomésticos");
		gravar("Celulares e Smartphones");
		gravar("Informática");
		gravar("Alimentos");
		gravar("Bebidas");
		gravar("Vestuário e Acessórios");
		gravar("Automotivo");
		gravar("Móveis e Decoração");
		gravar("Livros");
		gravar("Jogos");
		gravar("Música");
		gravar("Filmes e Séries");
		gravar("Brinquedos");
		gravar("Artigos para Festa");
		gravar("Papelaria");
		gravar("Esporte");
		gravar("Higiene e Limpeza");
		gravar("Saúde e Bem-Estar");
		gravar("Casa e Construção");
		gravar("Cama, Mesa e Banho");
		gravar("Outros");
		
	}
	
	private static void gravar(String nomeCategoria) {
		
		Categoria c = new Categoria(0, nomeCategoria);
		c.save();
		
	}
}
