package classes.utils;

public class UserDTO {
	
	private int idUsuario;
	private String email;
	private String senha;
	private String nivelUsuario;
	private String nome;
	private String cpf;
	private String endereco;
	private String cep;
	private String telefone;
	private String ativo;
		
	public UserDTO() {
		super();
	}
	public UserDTO(int idUsuario, String email, String senha, String nivelUsuario, String nome, String cpf,
			String endereco, String cep, String telefone, String ativo) {
		super();
		this.idUsuario = idUsuario;
		this.email = email;
		this.senha = senha;
		this.nivelUsuario = nivelUsuario;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.cep = cep;
		this.telefone = telefone;
		this.ativo = ativo;
	}
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
	public String getNivelUsuario() {	
		return nivelUsuario;
	}
	public void setNivelUsuario(int idNivelUsuario) {	
		String nivelUsuario = "";
		switch(idNivelUsuario) {
			case 1: nivelUsuario = "Cliente"; break;
			case 2: nivelUsuario = "Funcionário"; break;
			case 3: nivelUsuario = "Caixa"; break;
			case 4: nivelUsuario = "Financeiro"; break;
			case 5: nivelUsuario = "Gerente"; break;
			case 6: nivelUsuario = "Diretor"; break;
			case 7: nivelUsuario = "Administrador"; break;
		}
		this.nivelUsuario = nivelUsuario;
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
	public void setEndereco(String rua, String bairro, String cidade, String uf) {
		this.endereco = "Rua " + rua + ", " + bairro + " - " + cidade + ", " + uf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getAtivo() {
		return this.ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo =ativo;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	@Override
	public String toString() {
		return this.nome;
	}
}
