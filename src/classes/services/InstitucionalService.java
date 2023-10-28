package classes.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import classes.models.Institucional;

public class InstitucionalService {

	private Institucional institucional = new Institucional();
	private Scanner scanner = new Scanner(System.in);
	
	public void listar() {
		
		System.out.println("\n\n >> Listando dados institucionais: ");
		
		ResultSet rs = institucional.list();
		
		try {
			if(rs.next()) {
				do {
				
					institucional.setIdInstituicao(rs.getInt("idInstituicao"));
					institucional.setNome(rs.getString("nome"));
					institucional.setCpf_cnpj(rs.getString("cpf_cnpj"));
					institucional.setTipoPessoa(rs.getString("tipoPessoa").charAt(0));
					institucional.setEndereco(rs.getString("endereco"));
					institucional.setBairro(rs.getString("bairro"));
					institucional.setCidade(rs.getString("cidade"));
					institucional.setUf(rs.getString("uf"));
					institucional.setCep(rs.getString("cep"));
					institucional.setTelefone(rs.getString("telefone"));
					institucional.setEmail(rs.getString("email"));
					institucional.setLogo(rs.getString("logo"));
					
					System.out.printf(
							  " > Código: " +institucional.getIdInstituicao() +
							"\n > Nome: " +institucional.getNome() +
							"\n > CPF/CNPJ: " +institucional.getCpf_cnpj() +
							"\n > Tipo: " +String.valueOf(institucional.getTipoPessoa()) +
							"\n > Endereço: " +institucional.getEndereco() +
							"\n > Bairro: " +institucional.getBairro() +
							"\n > Cidade: " +institucional.getCidade() +
							"\n > UF: " +institucional.getUf() +
							"\n > Cep: " +institucional.getCep() +
							"\n > Telefone: " +institucional.getTelefone() +
							"\n > Email: " +institucional.getEmail() +
							"\n > Logo: " +institucional.getLogo() + "\n\n ---------"
					);
				} 
				while(rs.next()); 
			}
			else {
				System.out.println("\n [!] Nenhum dado cadastrado!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
