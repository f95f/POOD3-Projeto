package classes.models;

import classes.database.DBQuery;

public class NivelUsuario {

	private int idNivelUsuario;
	private String nivel;


	// --- Conexão com o BD ---------------------
	
	String tableName = "lojinha.nivelUsuario";
	String fieldsName = "idNivelUsuario,nivel";
	String fieldKey = "idNivelUsuario";
	
	private DBQuery dbQuery = new DBQuery( tableName, fieldsName, fieldKey);
	
	
	// --- Construtores -------------------------
	
	public NivelUsuario() {}

	public NivelUsuario(int idNivelUsuario, String nivel) {
		super();
		this.idNivelUsuario = idNivelUsuario;
		this.nivel = nivel;
	}

	
	// --- Getters e Setters -------------------------

	public int getIdNivelUsuario() {
		return idNivelUsuario;
	}

	public void setIdNivelUsuario(int idNivelUsuario) {
		this.idNivelUsuario = idNivelUsuario;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
}
