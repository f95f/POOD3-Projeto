package classes.models;

import java.sql.ResultSet;

import classes.database.DBQuery;

public class Institucional {

	private int idInstituicao;
	private String nome;
	private String cpf_cnpj;
	private char tipoPessoa;
	private String endereco;
	private String bairro;
	private String cidade;
	private String uf;
	private String cep;
	private String telefone;
	private String email;
	private String logo;


	// --- Conexão com o BD ---------------------
	
	String tableName = "lojinha.institucional";
	String fieldsName = "idInstituicao,nome,cpf_cnpj,tipoPessoa,endereco,bairro,cidade,uf,cep,telefone,email,logo";
	String fieldKey = "idInstituicao";
	
	private DBQuery dbQuery = new DBQuery( tableName, fieldsName, fieldKey);
	

	// --- Operações no BD ---------------------
	
	public int save() {
		
		if(this.getIdInstituicao() > 0) {
			return this.dbQuery.update(this.toArray());
		}
		else {
			return this.dbQuery.insert(this.toArray());
		}
		
	}
	
	public ResultSet list() {
		
		return this.dbQuery.select("");
		
	}
	// --- toArray -------------------------
	
	private String[] toArray(){
		
		return new String[] {
				
			 this.getIdInstituicao() + "",
			 this.getNome(),
			 this.getCpf_cnpj(),
			 String.valueOf(this.getTipoPessoa()),
			 this.getEndereco(),
			 this.getBairro(),
			 this.getCidade(),
			 this.getUf(),
			 this.getCep(),
			 this.getTelefone(),
			 this.getEmail(),
			 this.getLogo(),
		};
	}	
	// --- Construtores -------------------------
	
	public Institucional(){};
	
	public Institucional(int idInstituicao, String nome, String cpf_cnpj, char tipoPessoa, String endereco, String bairro,
			String cidade, String uf, String cep, String telefone, String email, String logo) {
		super();
		this.idInstituicao = idInstituicao;
		this.nome = nome;
		this.cpf_cnpj = cpf_cnpj;
		this.tipoPessoa = tipoPessoa;
		this.endereco = endereco;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.cep = cep;
		this.telefone = telefone;
		this.email = email;
		this.logo = logo;
	}

	
	// --- Getters e Setters -------------------------
	
	public int getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(int idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public char getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(char tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
}
