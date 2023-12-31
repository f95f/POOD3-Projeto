package classes.models;

import java.sql.ResultSet;

import classes.database.DBQuery;

public class Usuario {

	private int idUsuario;
	private String email;
	private String senha;
	private int idNivelUsuario;
	private String nome;
	private String cpf;
	private String endereco;
	private String bairro;
	private String cidade;
	private String uf;
	private String cep;
	private String telefone;
	private String foto;
	private String ativo;


	// --- Conex�o com o BD ---------------------
	
	String tableName = "lojinha.usuarios";
	String fieldsName = "idUsuario,email,senha,idNivelUsuario,nome,cpf,endereco,bairro,cidade,uf,cep,telefone,foto,ativo";
	String fieldKey = "idUsuario";
	
	private DBQuery dbQuery = new DBQuery( tableName, fieldsName, fieldKey);
	
	
	// --- Opera��es no BD -------------------------
	
	public ResultSet listAll() {
		
		return this.dbQuery.select("");
		
	}
	
	public ResultSet listById(String id) {
		
		return this.dbQuery.select(("idUsuario = " + id));
		
	}
	
	public ResultSet listByName(String name) {
		
		// A fun��o LOWER() do MySql � utilizada aqui para incluir 
		// na busca ambos termos com letras mai�sculas e min�sculas.
		return this.dbQuery.select(("LOWER(nome) like LOWER('%" + name + "%')"));
		
	}
	
	public ResultSet listByFields(String campo, String name) {

		return this.dbQuery.select(("LOWER(" + campo + ") like LOWER('%" + name + "%')"));
		
	}
	
	public int save() {
		
		if(this.getIdUsuario() > 0) {
			return this.dbQuery.update(this.toArray());
		}
		else {
			return this.dbQuery.insert(this.toArray());
		}
		
	}
	
	public int delete(int id){	
		
		return this.dbQuery.execute("delete from lojinha.usuarios where idUsuario = " + id + ";");

	}
	public int editar(String valor, String campo, int id) {
		System.out.println("\n\n > " + "update lojinha.usuarios set " + campo + " = '" + valor + "' where idUsuario = " + id + ";");
		return this.dbQuery.execute(
			"update lojinha.usuarios set " + campo + " = '" + valor + "' where idUsuario = " + id + ";"
		);
	}
	
	// --- toArray -------------------------
	
	private String[] toArray(){
		
		return new String[] {
				
			this.getIdUsuario() + "",
			this.getEmail(),
			this.getSenha(),
			this.getIdNivelUsuario() + "",
			this.getNome(),
			this.getCpf(),
			this.getEndereco(),
			this.getBairro(),
			this.getCidade(),
			this.getUf(),
			this.getCep(),
			this.getTelefone(),
			this.getFoto(),
			this.getAtivo()
		};
	}
	
	
	// --- Construtores -------------------------
	
	public Usuario() {}
	
	public Usuario(int idUsuario, String email, String senha, int idNivelUsuario, String nome, String cpf,
			String endereco, String bairro, String cidade, String uf, String cep, String telefone, String foto,
			String ativo) {
		super();
		this.idUsuario = idUsuario;
		this.email = email;
		this.senha = senha;
		this.idNivelUsuario = idNivelUsuario;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.cep = cep;
		this.telefone = telefone;
		this.foto = foto;
		this.ativo = ativo;
	}

	
	// --- Getters e Setters -------------------------
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getIdNivelUsuario() {
		return idNivelUsuario;
	}

	public void setIdNivelUsuario(int idNivelUsuario) {
		this.idNivelUsuario = idNivelUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

}
