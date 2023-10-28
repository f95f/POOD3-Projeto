package classes.seeders;

import classes.models.Institucional;

public class InstitucionalDBSeeder {
	
	public void importar() {
		gravar("Lojinha", "123.456.789-00", 'J', "Rua Principal, 123",
				"Bairro", "Cidade A", "UF", "12345-678", "(12) 3456-7890", "contato@empresa.com", "logo_empresa.png");		
	}
	
	private void gravar(String nome, String cpf_cnpj, char tipoPessoa, String endereco, String bairro,
			String cidade, String uf, String cep, String telefone, String email, String logo) {
		
		Institucional i = new Institucional(0, nome, cpf_cnpj, tipoPessoa, endereco, bairro, cidade, uf, cep, telefone, email, logo);
		i.save();
		
	}
}
