package classes.models;

import java.sql.ResultSet;

import classes.database.DBQuery;

public class Categoria {

	private int idCategoria;
	private String descricao;
	
	// --- Conexão com o BD ---------------------
	
	String tableName = "lojinha.categorias";
	String fieldsName = "idCategoria,descricao";
	String fieldKey = "idCategoria";
	
	private DBQuery dbQuery = new DBQuery(tableName, fieldsName, fieldKey);
	
	
	// --- Operações no BD -------------------------
	
	public ResultSet listAll() {
		
		return this.dbQuery.select("");
		
	}
	
	public int save() {
		
		if(this.getIdCategoria() > 0) {
			return this.dbQuery.update(this.toArray());
		}
		else {
			return this.dbQuery.insert(this.toArray());
		}
		
	}
	
	public int delete() {
		
		if(this.getIdCategoria() > 0) {
			return this.dbQuery.delete(this.toArray());
		}
		return 0;
		
	}
	
	
	// --- toArray -------------------------
	
	private String[] toArray(){
		
		return new String[] {
				
			this.getIdCategoria() + "",
			this.getDescricao()
		};
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
	};
	
	
	
	
}


