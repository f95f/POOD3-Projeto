package classes.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
				
				if(usuario.getSenha().equals(senha)){
					return usuario;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
		
	public void criar(Usuario user) {
		
		user.setIdUsuario(0);
		user.setSenha("12345");
		user.setFoto("");
		int saveStatus = user.save();
		
		if(saveStatus != 0) {
			
			System.out.println("\n\n [i] Usuário salvo com sucesso!\n");
			
		}
		else {

			System.out.println("\n\n [!] Erro ao salvar!\n");

		}
	}

	public ArrayList<Usuario> listar() {
		
		ArrayList<Usuario> usersList = new ArrayList<Usuario>();
		ResultSet rs = usuario.listAll();
		
		try {
			while(rs.next()) {
				
				Usuario usuarioSimples = new Usuario();
				
				usuarioSimples.setIdUsuario(rs.getInt("idUsuario"));
				usuarioSimples.setEmail(rs.getString("email"));
				usuarioSimples.setIdNivelUsuario(rs.getInt("idNivelUsuario"));
				usuarioSimples.setNome(rs.getString("nome"));
				usuarioSimples.setEndereco(
					rs.getString("endereco") + ", " +
					rs.getString("bairro") + " - " + 
					rs.getString("cidade") + ", " + 
					rs.getString("uf")
				);
				usuarioSimples.setTelefone(rs.getString("telefone"));
				usuarioSimples.setAtivo( (rs.getString("ativo").equals("N"))? "Inativo" : "Ativo");
				usersList.add(usuarioSimples);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersList;
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
