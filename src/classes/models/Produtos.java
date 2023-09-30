package classes.models;

import classes.database.DBQuery;

public class Produtos {

	private int idProduto;
	private String fabricante;
	private String nome;
	private String marca;
	private String modelo;
	private int idCategoria;
	private String descricao;
	private String unidadeMedida;
	private double largura;
	private double altura;
	private double profundidade;
	private double peso;
	private String cor;

	
	// --- Conex�o com o BD ---------------------
	
	String tableName = "lojinha.produtos";
	String fieldsName = "idProduto,fabricante,nome,marca,modelo,idCategoria,descricao,unidadeMedida,largura,altura,profundidade,peso,cor";
	String fieldKey = "idProduto";
	
	private DBQuery dbQuery = new DBQuery( tableName, fieldsName, fieldKey);
	
	
	// --- Construtores -------------------------
	
	public Produtos() {}

	public Produtos(int idProduto, String fabricante, String nome, String marca, String modelo, int idCategoria,
			String descricao, String unidadeMedida, double largura, double altura, double profundidade, double peso,
			String cor) {
		super();
		this.idProduto = idProduto;
		this.fabricante = fabricante;
		this.nome = nome;
		this.marca = marca;
		this.modelo = modelo;
		this.idCategoria = idCategoria;
		this.descricao = descricao;
		this.unidadeMedida = unidadeMedida;
		this.largura = largura;
		this.altura = altura;
		this.profundidade = profundidade;
		this.peso = peso;
		this.cor = cor;
	}

	
	// --- Getters e Setters -------------------------
	
	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

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

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public double getLargura() {
		return largura;
	}

	public void setLargura(double largura) {
		this.largura = largura;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(double profundidade) {
		this.profundidade = profundidade;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
	
}
