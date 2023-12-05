package classes.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import classes.models.Usuario;
import classes.utils.ProdutoDTO;
import classes.utils.UserDTO;

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

	public ArrayList<UserDTO> listar() {
		
		ArrayList<UserDTO> usersList = new ArrayList<UserDTO>();
		ResultSet rs = usuario.listAll();
		
		try {
			while(rs.next()) {
				UserDTO usuarioDTO = new UserDTO();
				
				usuarioDTO.setIdUsuario(rs.getInt("idUsuario"));
				usuarioDTO.setEmail(rs.getString("email"));
				usuarioDTO.setSenha(rs.getString("senha"));
				usuarioDTO.setNivelUsuario(rs.getInt("idNivelUsuario"));
				usuarioDTO.setNome(rs.getString("nome"));
				usuarioDTO.setCpf(rs.getString("cpf"));
				usuarioDTO.setEndereco(
					rs.getString("endereco"),
					rs.getString("bairro"),
					rs.getString("cidade"),
					rs.getString("uf")
				);
				usuarioDTO.setCep(rs.getString("cep"));
				usuarioDTO.setTelefone(rs.getString("telefone"));
				String status = "";
				usuarioDTO.setAtivo((rs.getString("ativo").equals("N"))? "Não" : "Sim");
				
				usersList.add(usuarioDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersList;
	}
	
	public Usuario buscarPorId(int id) {
		
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

	public int excluir(int id) {
		return this.usuario.delete(id);
	}

	public int atualizar(String valorAtualizado, String campo, int id) {
		return this.usuario.editar(valorAtualizado, campo, id);
	}
	
	public ArrayList<UserDTO> searchBy(String field, String searchTerm) {

		ArrayList<UserDTO> usuariosList = new ArrayList<UserDTO>();
		searchTerm.toLowerCase();
		ResultSet rs = usuario.listByFields(field, searchTerm);
		try {
			while(rs.next()) {
				
				UserDTO usuarioDTO = new UserDTO();
						
				usuarioDTO.setIdUsuario(rs.getInt("idUsuario"));
				usuarioDTO.setEmail(rs.getString("email"));
				usuarioDTO.setSenha(rs.getString("senha"));
				usuarioDTO.setNivelUsuario(rs.getInt("idNivelUsuario"));
				usuarioDTO.setNome(rs.getString("nome"));
				usuarioDTO.setCpf(rs.getString("cpf"));
				usuarioDTO.setEndereco(
					rs.getString("endereco"),
					rs.getString("bairro"),
					rs.getString("cidade"),
					rs.getString("uf")
				);
				usuarioDTO.setCep(rs.getString("cep"));
				usuarioDTO.setTelefone(rs.getString("telefone"));
				usuarioDTO.setAtivo(rs.getString("ativo"));
				System.out.println("\n\n - " + usuarioDTO.getNome());
				usuariosList.add(usuarioDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuariosList;
	}

	public ArrayList<UserDTO> getClientes() {
		
		return this.searchBy("idNivelUsuario", 1 + "");
	}

	public int trocarSenha(int userId, String senhaAtual, String senhaNova, String confirmarSenha) {

		Usuario usuarioEncontrado = this.buscarPorId(userId);
		if(!usuarioEncontrado.getSenha().equals(senhaAtual)) {
			return 2;
		}
		else if(!senhaNova.equals(confirmarSenha)) {
			return 3;
		}
		else {
			return this.atualizar(senhaNova, "senha", userId);
		}
	}
}
