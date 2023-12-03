package classes.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import classes.models.Categoria;

public class CategoriaService {

	private Categoria categoria = new Categoria();
	private Scanner scanner = new Scanner(System.in);
	
	public int criar() {
		String nomeCategoria = "";
		
		do {
					
			System.out.print(" > Informe o nome da categoria: ");
			nomeCategoria = scanner.nextLine();
		
			if(nomeCategoria == "") {
				System.out.println("\n [!] Nome inválido! \n");
			}
		}
		while(nomeCategoria == "");
		
		categoria.setIdCategoria(0);
		categoria.setDescricao(nomeCategoria);
		
		int saveStatus = categoria.save();
		
		if(saveStatus != 0) {
			
			System.out.println("\n\n [i] Categoria salva com sucesso!\n");
			
		}
		else {

			System.out.println("\n\n [!] Erro ao salvar categoria!\n");

		}
		return saveStatus;
	
	}
	
	public ArrayList<Categoria> listar() {
		
		ArrayList<Categoria> categoriasList = new ArrayList<Categoria>();
		
		ResultSet rs = this.categoria.listAll();
		
		try {
			while(rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setIdCategoria(rs.getInt("IdCategoria"));
				categoria.setDescricao(rs.getString("descricao"));
				categoriasList.add(categoria);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categoriasList;
	}
	
	public Categoria buscar(int id) {
		System.out.println("\n > Pesquisando categorias pelo código " + id + ":");
		
		ResultSet rs = categoria.listById(id + "");
		categoria.setIdCategoria(0);
		categoria.setDescricao("");
		try {
			if(rs.next()) {
				System.out.println("\n Código | Descrição ");
				categoria.setIdCategoria(rs.getInt("IdCategoria"));
				categoria.setDescricao(rs.getString("descricao"));
				
				System.out.printf("\n %6d | %s", categoria.getIdCategoria(), categoria.getDescricao());
				return categoria;
			}
			else {
				System.out.println("\n [!] Código não encontrado!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void buscar(String nome) {
		nome.toLowerCase();
		System.out.println("\n > Pesquisando categorias por \"" + nome + "\":");
		
		ResultSet rs = categoria.listByName(nome);
		System.out.println("\n Código | Descrição ");
		
		try {
			if(rs.next()) {
				do {
					categoria.setIdCategoria(rs.getInt("IdCategoria"));
					categoria.setDescricao(rs.getString("descricao"));
					
					System.out.printf("\n %6d | %s", categoria.getIdCategoria(), categoria.getDescricao());
				} while(rs.next()); 
			}
			else {
				System.out.println("\n [!] Nenhuma categoria encontrada!");								
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void editar(int id) {
		
		System.out.println("\n > Editando categoria");
		categoria = this.buscar(id);
			
		if(categoria == null) { return;	}
		
		String novoNome = "";
		
		do {
			System.out.println("\n\n > Informe o novo nome: ");
			 novoNome = scanner.next();
		
			if(novoNome == "") {
				System.out.println("\n [!] Nome inválido! \n");
			}
		}
		while(novoNome == "");
		
		String resposta = "";
		
		do {
			System.out.println("\n > Nome atual: " + categoria.getDescricao());
			System.out.println("\n > Nome novo: " + novoNome);
			System.out.println("\n\n > Confirmar Alteração? [sim/não]: ");
			resposta = scanner.next().toLowerCase();
			
			if(resposta.equals("sim") || resposta.equals("s")) {
				categoria.setDescricao(novoNome);
				int alterarStatus = categoria.save();
				
				if(alterarStatus == 1) {
					System.out.println("\n [i] Nome da Categoria alterado!");
				}
				else {
					System.out.println("\n [!] Erro ao alterar!\n");	
				}
				break;
			}
			if(resposta.equals("não") || resposta.equals("n") || resposta.equals("nao")) {
				System.out.println("\n [i] Nada foi alterado.\n");
				break;
			}
		}
		while(true);
	}
	
	public void apagar(int id) {
		
		System.out.println("\n > Apagando categoria");
		categoria = this.buscar(id);
			
		if(categoria == null) { return;	}
		
		String resposta = "";
		do {
			System.out.println("\n\n > Confirmar exclusão? [sim/não]: ");
			resposta = scanner.next().toLowerCase();
			if(resposta.equals("sim") || resposta.equals("s")) {
				
				int deleteStatus = categoria.delete();
				
				if(deleteStatus == 1) {
					System.out.println("\n [i] Categoria apagada!");
				}
				else {
					System.out.println("\n [!] Erro ao apagar!\n");	
				}
				break;
			}
			if(resposta.equals("não") || resposta.equals("n") || resposta.equals("nao")) {
				System.out.println("\n [i] Nenhuma categoria apagada.\n");
				break;
			}
		}
		while(true);
	}

}
