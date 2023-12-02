package classes.utils;

import classes.models.Produto;

public class ProdutoDTO {

	private int idProduto;
	private String nome;
	private String fabricante;
	private String marca;
	private String modelo;
	private String categoria;
	private String descricao;
	private String unidadeMedida;
	private String dimensoes;
	private String peso;
	private String cor;
	
	private Produto produto;
	
	public ProdutoDTO() {};
	
	public ProdutoDTO(int idProduto, String nome, String fabricante, String marca, String modelo, String categoria,
			String descricao, String unidadeMedida, String dimensoes, String peso, String cor, Produto produto) {
		super();
		this.idProduto = idProduto;
		this.nome = nome;
		this.fabricante = fabricante;
		this.marca = marca;
		this.modelo = modelo;
		this.categoria = categoria;
		this.descricao = descricao;
		this.unidadeMedida = unidadeMedida;
		this.dimensoes = dimensoes;
		this.peso = peso;
		this.cor = cor;
		this.produto = produto;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
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

	public String getDimensoes() {
		return dimensoes;
	}

	public void setDimensoes(String dimensoes) {
		this.dimensoes = dimensoes;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
}
