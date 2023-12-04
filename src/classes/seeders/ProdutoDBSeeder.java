package classes.seeders;

import classes.models.Produto;

public class ProdutoDBSeeder {

	public static void importar() {

		gravar("Samsung", "Smart TV", 1, "Samsung", "Modelo XYZ", "Smart TV de �ltima gera��o", "unidade", 120, 80, 10, 15, "preto");
		gravar("Nestl�", "Chocolate", 5, "Nestl�", "Modelo ABC", "Chocolate ao leite", "unidade", 5, 2, 1, 0.1, "marrom");
		gravar("Sony", "Smartphone Xperia", 3, "Sony", "Xperia XZ3", "Smartphone premium com tela OLED", "unidade", 7, 15, 1, 0.2, "preto");
		gravar("Electrolux", "M�quina de Lavar Roupas", 2, "Electrolux", "EW6S", "M�quina de lavar com capacidade para 10kg", "unidade", 60, 85, 70, 50, "branco");
		gravar("Dell", "Notebook Inspiron", 4, "Dell", "Inspiron 15", "Notebook com processador Intel Core i5", "unidade", 38, 26, 2, 2, "prata");
		gravar("Nestl�", "Cereal Matinal", 5, "Nestl�", "Cereal Crunchy", "Cereal matinal crocante e saboroso", "pacote", 20, 30, 5, 0.5, "amarelo");
		gravar("Coca-Cola", "Refrigerante", 6, "Coca-Cola", "Coca-Cola Tradicional", "Refrigerante carbonatado", "garrafa", 7, 24, 7, 0.3, "vermelho");
		gravar("Nike", "T�nis Esportivo", 7, "Nike", "Air Zoom Pegasus 38", "T�nis de corrida leve e confort�vel", "par", 10, 20, 30, 0.8, "azul");
		gravar("Bosch", "Bateria de Carro", 8, "Bosch", "S4", "Bateria para ve�culos de passeio", "unidade", 20, 18, 17, 12, "preto");
		gravar("IKEA", "Sof� de Couro", 9, "IKEA", "S�derhamn", "Sof� de couro para sala de estar", "unidade", 80, 35, 30, 25, "marrom");
		gravar("Editora XYZ", "Livro de Fic��o Cient�fica", 10, "Autor Desconhecido", "T�tulo Fant�stico", "Livro emocionante cheio de aventuras", "unidade", 14, 21, 3, 0.7, "verde");
		gravar("Nintendo", "Video Game", 11, "Nintendo", "Switch", "Console de videogame para jogos em casa e port�teis", "unidade", 10, 17, 2, 0.5, "vermelho");
		gravar("Universal Music", "�lbum de Vinil", 12, "Artista Famoso", "T�tulo do �lbum", "Edi��o limitada em vinil", "unidade", 31, 31, 0.5, 0.3, "preto");
		gravar("Warner Bros.", "Blu-ray", 13, "Diretor Renomado", "T�tulo do Filme", "Edi��o especial em alta defini��o", "unidade", 13, 17, 0.2, 0.1, "azul");
		gravar("LEGO", "Conjunto de Blocos", 14, "LEGO", "Classic Creative Box", "Conjunto de blocos para constru��es criativas", "caixa", 25, 19, 7, 0.8, "colorido");
		gravar("Party City", "Pacote de Bal�es", 15, "Party City", "Bal�es Coloridos", "Bal�es para festas e celebra��es", "pacote", 10, 15, 2, 0.1, "v�rias cores");
		gravar("Faber-Castell", "Canetas Coloridas", 16, "Faber-Castell", "Color GRIP", "Canetas coloridas de qualidade premium", "conjunto", 2, 15, 1, 0.1, "v�rias cores");
		gravar("Nike", "Bola de Futebol", 17, "Nike", "Ordem V", "Bola oficial para partidas profissionais", "unidade", 22, 22, 22, 0.4, "branco");
		gravar("Colgate", "Creme Dental", 18, "Colgate", "Total 12", "Creme dental para higiene bucal", "unidade",5, 15, 3, 0.15, "branco");
		gravar("Johnson & Johnson", "Term�metro Digital", 19, "Johnson & Johnson", "Digital Thermometer", "Term�metro para medi��o de temperatura corporal", "unidade",1, 10, 0.5, 0.03, "branco");
		gravar("Leroy Merlin", "Tinta para Parede", 20, "Leroy Merlin", "Premium White", "Tinta lav�vel para pinturas internas", "gal�o",25, 30, 15, 18, "branco");
		gravar("Trussardi", "Jogo de Len��is", 21, "Trussardi", "Egyptian Cotton", "Len��is de alta qualidade para cama de casal", "conjunto", 240, 260, 0.2, 2, "branco");
		gravar("Apple", "iPhone 13 Pro", 3, "Apple", "iPhone 13 Pro", "Smartphone premium com c�mera avan�ada", "unidade", 7, 15, 0.8, 0.2, "ouro");
		gravar("LG", "Ar Condicionado Split", 2, "LG", "Inverter", "Ar condicionado com tecnologia inverter", "unidade", 90, 30, 20, 12, "branco");
		gravar("Microsoft", "Surface Laptop 4", 4, "Microsoft", "Surface Laptop 4", "Notebook com tela sens�vel ao toque", "unidade", 30, 22, 1.5, 1.3, "platinum");
		gravar("Nestl�", "Kit Kat", 5, "Nestl�", "Kit Kat", "Chocolate crocante", "pacote", 10, 5, 1, 0.05, "marrom");
		gravar("Starbucks", "Caf� Espresso", 6, "Starbucks", "Espresso Roast", "Caf� espresso premium", "pacote", 5, 10, 0.5, 0.1, "marrom");
		gravar("Gucci", "Vestido de Gala", 7, "Gucci", "Evening Gown", "Vestido de gala elegante", "unidade", 40, 160, 5, 0.8, "preto");
		gravar("Toyota", "Carro Sedan", 8, "Toyota", "Camry", "Carro sedan confort�vel e econ�mico", "unidade", 190, 58, 57, 1200, "prata");
		gravar("IKEA", "Cama King Size", 9, "IKEA", "Malm", "Cama king size com gavetas para armazenamento", "unidade", 195, 100, 40, 60, "branco");
		gravar("HarperCollins", "Livro de Romance", 10, "Autor Famoso", "T�tulo do Romance", "Romance emocionante e envolvente", "unidade", 14, 21, 2, 0.5, "vermelho");
		gravar("Sony", "PlayStation 5", 11, "Sony", "PlayStation 5", "Console de videogame de �ltima gera��o", "unidade", 40, 10, 30, 4.5, "preto");
		gravar("Universal Music", "�lbum em CD", 12, "Artista Famoso", "T�tulo do �lbum", "Edi��o especial em CD", "unidade", 14, 12, 1, 0.1, "prata");
		gravar("Paramount Pictures", "DVD de Filme", 13, "Diretor Renomado", "T�tulo do Filme", "Edi��o especial em DVD", "unidade", 13, 19, 0.7, 0.08, "preto");
		gravar("LEGO", "Conjunto de Constru��o", 14, "LEGO", "Architecture Skyline", "Conjunto para construir famosos marcos arquitet�nicos", "caixa", 25, 20, 6, 0.9, "colorido");
		gravar("Party City", "Decora��o de Festa", 15, "Party City", "Party Decor Set", "Kit completo para decora��o de festas", "pacote", 15, 25, 3, 0.2, "v�rias cores");
		gravar("Staedtler", "Conjunto de Canetas Art�sticas", 16, "Staedtler", "Pigment Liner Set", "Canetas para desenhos detalhados", "conjunto", 1, 17, 0.5, 0.03, "v�rias cores");
		gravar("Adidas", "T�nis de Corrida", 17, "Adidas", "Ultraboost", "T�nis de corrida com amortecimento responsivo", "par", 11, 28, 8, 0.6, "preto");
		gravar("Dove", "Sabonete L�quido", 18, "Dove", "Deeply Nourishing", "Sabonete l�quido para pele macia", "frasco", 7, 18, 4, 0.3, "azul");
		gravar("Omron", "Monitor de Press�o Arterial", 19, "Omron", "BP742N", "Monitor digital para medi��o da press�o arterial", "unidade", 10, 15, 2, 0.2, "branco");
		gravar("Sherwin-Williams", "Tinta para Exterior", 20, "Sherwin-Williams", "Duration", "Tinta resistente para pinturas externas", "gal�o", 25, 35, 15, 20, "verde");
		gravar("Ralph Lauren", "Jogo de Toalhas", 21, "Ralph Lauren", "Player Towel Set", "Toalhas de algod�o premium", "conjunto", 70, 140, 2, 1, "azul");
		gravar("Marca Desconhecida", "Produto Gen�rico", 22, "Desconhecido", "Modelo Desconhecido", "Descri��o gen�rica do produto", "unidade", 10, 10, 10, 1, "cinza");
		gravar("Nestl�", "Barras de Cereal", 5, "Nestl�", "Energy Bar", "Barras de cereal energ�ticas", "pacote",  12, 5, 1, 0.1, "amarelo");
		gravar("Kellogg's", "Cereal Matinal", 5, "Kellogg's", "Corn Flakes", "Cereal de milho crocante e saud�vel", "pacote",  20, 25, 5, 0.3, "laranja");
		gravar("Heinz", "Ketchup", 5, "Heinz", "Tomato Ketchup", "Molho de tomate para condimentar", "frasco",  7, 15, 5, 0.6, "vermelho");
		gravar("McCormick", "Tempero em P�", 5, "McCormick", "Season All", "Tempero em p� para diversas receitas", "pacote",  5, 10, 3, 0.2, "verde");
		gravar("Ben & Jerry's", "Sorvete", 5, "Ben & Jerry's", "Cookie Dough", "Sorvete sabor cookie dough com peda�os de chocolate", "pote",  10, 15, 7, 0.8, "marrom");
		gravar("Lay's", "Batatas Chips", 5, "Lay's", "Classic Flavor", "Batatas chips salgadas e crocantes", "pacote", 8, 12, 2, 0.15, "amarelo");
		gravar("Bauducco", "Biscoitos", 5, "Bauducco", "Cookies", "Biscoitos de chocolate macios e deliciosos", "pacote", 10, 8, 1.5, 0.2, "marrom");
		gravar("Campbell's", "Sopa Enlatada", 5, "Campbell's", "Chicken Noodle Soup", "Sopa de frango com macarr�o", "lata", 8, 10, 5, 0.5, "vermelho");
		gravar("Nestl�", "Leite Condensado", 5, "Nestl�", "Mo�a", "Leite condensado cremoso", "lata", 7, 12, 4, 0.4, "branco");
		gravar("Green Giant", "Legumes Enlatados", 5, "Green Giant", "Mixed Vegetables", "Mistura de vegetais enlatados", "lata", 10, 10, 6, 0.3, "verde");
		gravar("Coca-Cola", "Refrigerante Diet", 6, "Coca-Cola", "Diet Coke", "Refrigerante sem a��car", "lata", 6, 12, 6, 0.35, "prata");
		gravar("Starbucks", "Frappuccino", 6, "Starbucks", "Mocha Frappuccino", "Bebida gelada de caf� e chocolate", "garrafa", 7, 15, 5, 0.6, "marrom");
		gravar("Tropicana", "Suco de Laranja", 6, "Tropicana", "Pure Premium", "Suco de laranja fresco e natural", "garrafa", 8, 20, 7, 0.9, "laranja");
		gravar("Red Bull", "Energ�tico", 6, "Red Bull", "Original", "Bebida energ�tica para aumentar o foco e a energia", "lata", 6, 15, 5, 0.25, "azul");
		gravar("Nescaf�", "Caf� Sol�vel", 6, "Nescaf�", "Classic", "Caf� sol�vel para preparo r�pido", "pote", 10, 10, 7, 0.4, "marrom");
		gravar("George Orwell", "1984", 10, "Editora XYZ", "1984", "Romance dist�pico sobre controle totalit�rio", "unidade", 14, 21, 2, 0.5, "vermelho");
		gravar("J.K. Rowling", "Harry Potter e a Pedra Filosofal", 10, "Editora ABC", "Harry Potter", "Aventura m�gica para jovens leitores", "unidade", 13, 20, 2, 0.6, "azul");
		gravar("Harper Lee", "O Sol � para Todos", 10, "Editora DEF", "To Kill a Mockingbird", "Cl�ssico da literatura sobre justi�a e preconceito", "unidade", 14, 21, 3, 0.7, "verde");
		gravar("F. Scott Fitzgerald", "O Grande Gatsby", 10, "Editora GHI", "The Great Gatsby", "Explora��o do sonho americano nos anos 1920", "unidade", 14, 21, 2, 0.55, "dourado");
		gravar("Jane Austen", "Orgulho e Preconceito", 10, "Editora JKL", "Pride and Prejudice", "Romance cl�ssico sobre amor e sociedade", "unidade", 13, 20, 2, 0.5, "rosa");
		gravar("Colgate", "Creme Dental", 18, "Colgate", "Total 12", "Creme dental para higiene bucal", "unidade", 5, 15, 3, 0.15, "branco");
		gravar("Dove", "Sabonete L�quido", 18, "Dove", "Deeply Nourishing", "Sabonete l�quido para pele macia", "frasco", 7, 18, 4, 0.3, "azul");
		gravar("Lysol", "Desinfetante", 18, "Lysol", "All-Purpose Cleaner", "Desinfetante multiuso para limpeza", "garrafa", 8, 25, 7, 0.6, "verde");
		gravar("Swiffer", "Mop de Limpeza", 18, "Swiffer", "WetJet", "Mop descart�vel para limpeza eficaz", "unidade", 10, 20, 2, 0.4, "roxo");
		gravar("Mr. Muscle", "Limpa Vidros", 18, "Mr. Muscle", "Glass Cleaner", "Produto para limpeza de vidros e superf�cies", "spray", 5, 20, 4, 0.35, "azul");
		gravar("Fitbit", "Smartwatch", 19, "Fitbit", "Versa 3", "Smartwatch com monitor de atividade f�sica", "unidade", 4, 4, 1, 0.05, "preto");
		gravar("Vitaminas", "Suplemento Vitam�nico", 19, "Vitaminas Inc.", "VitaBoost", "Suplemento para sa�de e imunidade", "frasco", 6, 12, 2, 0.1, "laranja");
		gravar("M�scara de Dormir", "M�scara de Dormir", 19, "SleepWell", "Comfort Sleep Mask", "M�scara para uma boa noite de sono", "unidade", 8, 4, 0.2, 0.03, "azul");
		gravar("Aromaterapia", "�leo Essencial", 19, "AromaZen", "Lavanda", "�leo essencial para relaxamento", "frasco", 3, 9, 0.1, 0.05, "violeta");
		gravar("Samsung", "Smart TV", 1, "Samsung", "QLED 4K TV", "Smart TV com resolu��o 4K e tecnologia QLED", "unidade", 75, 42, 1.5, 22, "preto");
		gravar("Apple", "iPad Pro", 1, "Apple", "iPad Pro 12.9", "Tablet poderoso com tela Retina e Apple Pencil", "unidade", 11, 9, 0.5, 0.64, "prata");
		gravar("Sony", "PlayStation 5", 1, "Sony", "PlayStation 5", "Console de videogame de �ltima gera��o", "unidade", 40, 10, 30, 4.5, "preto");
		gravar("Canon", "C�mera DSLR", 1, "Canon", "EOS 5D Mark IV", "C�mera profissional com sensor Full Frame", "unidade", 15, 11, 4, 0.95, "preto");
		gravar("Bose", "Fones de Ouvido", 1, "Bose", "QuietComfort 35 II", "Fones de ouvido com cancelamento de ru�do", "unidade", 7, 7, 3, 0.24, "prata");
	
	}
	
	private static void gravar(String fabricante, String nome, int idCategoria, String marca, String modelo,
			String descricao, String unidadeMedida, double largura, double altura, double profundidade, double peso,
			String cor) {
		
		Produto p = new Produto(0, fabricante, nome, marca, modelo, idCategoria, descricao, unidadeMedida, largura, altura, profundidade, peso, cor);
		p.save();
		
	}
	
}
