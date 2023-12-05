package classes.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.database.DBQuery;
import classes.services.ProdutoService;
import classes.utils.ProdutoDTO;

public class Categoria {

	private int idCategoria;
	private String descricao;
	
	// --- Conexão com o BD ---------------------
	
	String tableName = "lojinha.categorias";
	String fieldsName = "idCategoria,descricao";
	String fieldKey = "idCategoria";
	
	private DBQuery dbQuery = new DBQuery(tableName, fieldsName, fieldKey);
	
	// --- Operações no BD -------------------------
	
	public ResultSet listById(String id) {	
		return this.dbQuery.select(("idCategoria = " + id));	
	}
	
	public ArrayList<Categoria> listAll() {	
		ArrayList<Categoria> categoriasList = new ArrayList<Categoria>();
		ResultSet rs = this.dbQuery.select("");
		try {
			while(rs.next()) {
				
				Categoria categoria = new Categoria();
						
				categoria.setIdCategoria(rs.getInt("idCategoria"));
				categoria.setDescricao(rs.getString("descricao"));
				
				categoriasList.add(categoria);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoriasList;	
	}
	
	public boolean findProducts(int id) {	
		ProdutoService service = new ProdutoService();
		ArrayList<ProdutoDTO> list = service.searchByCategoria(id);
		return (list.size() != 0);
		
	}
	
	public int save() {
		
		if(this.getIdCategoria() > 0) {
			return this.dbQuery.update(this.toArray());
		}
		else {
			return this.dbQuery.insert(this.toArray());
		}
		
	}
	
	public int delete(int id){	
		
		return this.dbQuery.execute("delete from lojinha.categorias where idCategoria = " + id + ";");

	}
	
	public int editar(String valor, String campo, int id) {
		return this.dbQuery.execute(
			"update lojinha.categorias set " + campo + " = '" + valor + "' where idCategoria = " + id + ";"
		);
	}
	
	public ArrayList<Categoria> searchBy(String searchTerm) {
		
		ArrayList<Categoria> categoriasList = new ArrayList<Categoria>();
		searchTerm.toLowerCase();
		ResultSet rs = this.dbQuery.select(("LOWER(descricao) like LOWER('%" + searchTerm + "%')"));
		try {
			while(rs.next()) {
				
				Categoria categoria = new Categoria();
						
				categoria.setIdCategoria(rs.getInt("idCategoria"));
				categoria.setDescricao(rs.getString("descricao"));
				
				categoriasList.add(categoria);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoriasList;
	}
	
	// --- toArray -------------------------
	
	private String[] toArray(){
		
		return new String[] {
				
			this.getIdCategoria() + "",
			this.getDescricao()
		};
	}
	@Override
	public String toString() {
	    return this.getDescricao();
	}
	
	// --- Construtores -------------------------
	
	public Categoria() {}

	public Categoria(int idCategoria, String descricao) {
		super();
		this.idCategoria = idCategoria;
		this.descricao = descricao;
	}

	
	// --- Getters e Setters -------------------------
	
	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}


