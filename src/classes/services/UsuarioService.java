package classes.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import classes.models.Usuario;

public class UsuarioService {

	private Usuario usuario = new Usuario();
	private Scanner scanner = new Scanner(System.in);
	
	public Usuario login(String email, String senha) {
		
		ResultSet rs = usuario.listByFields("email", email);
		
		try {
			if(rs.next()) {
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setIdNivelUsuario(rs.getInt("idNivelUsuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setEndereco(rs.getString("endereco"));
				usuario.setBairro(rs.getString("bairro"));
				usuario.setCidade(rs.getString("cidade"));
				usuario.setUf(rs.getString("uf"));
				usuario.setCep(rs.getString("cep"));
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setFoto(rs.getString("foto"));
				usuario.setAtivo(rs.getString("ativo"));
				
				System.out.println("\n\n >> " + usuario.getSenha() + "\n\n");
				System.out.println(" >> " + senha + "\n\n");
				if(usuario.getSenha().equals(senha)){
					return usuario;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
		
	public void criar() {
		
		usuario.setIdUsuario(0);

		System.out.print("\n > Informe o nome do usuario: ");
		usuario.setNome(scanner.nextLine());

		System.out.print("\n > Informe o email do usuário: ");
		usuario.setEmail(scanner.nextLine());
		
		System.out.print("\n > Informe a senha: ");
		usuario.setSenha(scanner.nextLine());
		
		System.out.print("\n > Informe o nível do usuário: ");
		usuario.setIdNivelUsuario(Integer.parseInt(scanner.nextLine()));

		System.out.print("\n > Informe o cpf: ");
		usuario.setCpf(scanner.nextLine());

		System.out.print("\n > Informe o endereço: ");
		usuario.setEndereco(scanner.nextLine());

		System.out.print("\n > Informe o bairro: ");
		usuario.setBairro(scanner.nextLine());

		System.out.print("\n > Informe a cidade: ");
		usuario.setCidade(scanner.nextLine());

		System.out.print("\n > Informe o estado: ");
		usuario.setUf(scanner.nextLine());

		System.out.print("\n > Informe o CEP: ");
		usuario.setCep(scanner.nextLine());

		System.out.print("\n > Informe o telefone: ");
		usuario.setTelefone(scanner.nextLine());

		System.out.print("\n > Informe a foto: ");
		usuario.setFoto(scanner.nextLine());

		System.out.print("\n > Informe o status do usuário: ");
		usuario.setAtivo(scanner.nextLine());

		int saveStatus = usuario.save();
		
		if(saveStatus != 0) {
			
			System.out.println("\n\n [i] Usuário salvo com sucesso!\n");
			
		}
		else {

			System.out.println("\n\n [!] Erro ao salvar!\n");

		}
	}

	public void listar() {
		
		System.out.println("\n > Listando usuários: ");
		//System.out.println("\n Código | Nome                           | Fabricante                     | Marca                | Modelo               | Cód. Categoria | Descrição                                                    | Medida          | Largura      | Altura       | Profundidade   | Peso           | cor               ");

		ResultSet rs = usuario.listAll();
		
		try {
			while(rs.next()) {
				
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setIdNivelUsuario(rs.getInt("idNivelUsuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setEndereco(rs.getString("endereco"));
				usuario.setBairro(rs.getString("bairro"));
				usuario.setCidade(rs.getString("cidade"));
				usuario.setUf(rs.getString("uf"));
				usuario.setCep(rs.getString("cep"));
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setFoto(rs.getString("foto"));
				usuario.setAtivo(rs.getString("ativo"));
				
//				A linha abaixo formata cada linha da tabela para que estas mantenham o mesmo tamanho. 
				System.out.printf("\n %6d | %-30s | %-30s | %-6d | %-30s |"
								+ " %-14s | %-30s | %-30s | %-30s | %-15s|"
								+ " %-10s | %-15s | %-30s | %-30s ",
						usuario.getIdUsuario(),
						usuario.getEmail(),
						usuario.getSenha(),
						usuario.getIdNivelUsuario(),
						usuario.getNome(),
						
						usuario.getCpf(),
						usuario.getEndereco(),
						usuario.getBairro(),
						usuario.getCidade(),
						usuario.getUf(),
						
						usuario.getCep(),
						usuario.getTelefone(),
						usuario.getFoto(),
						usuario.getAtivo()
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Usuario buscarPorId(int id) {
		System.out.println("\n > Pesquisando usuário pelo código " + id + ":");
		
		ResultSet rs = usuario.listById(id + "");
		
		try {
			if(rs.next()) {
				
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setIdNivelUsuario(rs.getInt("idNivelUsuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setEndereco(rs.getString("endereco"));
				usuario.setBairro(rs.getString("bairro"));
				usuario.setCidade(rs.getString("cidade"));
				usuario.setUf(rs.getString("uf"));
				usuario.setCep(rs.getString("cep"));
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setFoto(rs.getString("foto"));
				usuario.setAtivo(rs.getString("ativo"));
				
//				A linha abaixo formata cada linha da tabela para que estas mantenham o mesmo tamanho. 
				System.out.printf("\n %6d | %-30s | %-30s | %-6d | %-30s |"
								+ " %-14s | %-30s | %-30s | %-30s | %-15s|"
								+ " %-10s | %-15s | %-30s | %-30s ",
						usuario.getIdUsuario(),
						usuario.getEmail(),
						usuario.getSenha(),
						usuario.getIdNivelUsuario(),
						usuario.getNome(),
						
						usuario.getCpf(),
						usuario.getEndereco(),
						usuario.getBairro(),
						usuario.getCidade(),
						usuario.getUf(),
						
						usuario.getCep(),
						usuario.getTelefone(),
						usuario.getFoto(),
						usuario.getAtivo()
				);
				
				return usuario;
			}
			else {
				System.out.println("\n [!] Código não encontrado!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void apagar(int id) {
		
		System.out.println("\n > Apagando usuário");
		usuario = this.buscarPorId(id);
			
		if(usuario == null) { return; }
		
		String resposta = "";
		do {
			System.out.println("\n\n > Confirmar exclusão? [sim/não]: ");
			resposta = scanner.next().toLowerCase();
			if(resposta.equals("sim") || resposta.equals("s")) {
				
				int deleteStatus = usuario.delete();
				
				if(deleteStatus == 1) {
					System.out.println("\n [i] Pedido apagado!");
				}
				else {
					System.out.println("\n [!] Erro ao apagar!\n");	
				}
				break;
			}
			if(resposta.equals("não") || resposta.equals("n") || resposta.equals("nao")) {
				System.out.println("\n [i] Nenhum usuário apagado.\n");
				break;
			}
		}
		while(true);
	}
	
	
}
