package classes.models;

import java.sql.ResultSet;

import classes.database.DBQuery;

public class Estoque {
	
	private int idEstoque;
	private int idProduto;
	private String dtEntrada;
	private int quantidade;
	private String dtFabricacao;
	private String dtVencimento;
	private String nfCompra;
	private Double precoCompra;
	private Double icmsCompra;
	private Double precoVenda;
	private int qtdVendida;
	private int qtdOcorrencia;
	private String ocorrencia;
	
	// --- Conexão com o BD ---------------------
	
	String tableName = "lojinha.estoque";
	String fieldsName = "idEstoque,idProduto,dtEntrada,quantidade,dtFabricacao,dtVencimento,nfCompra,precoCompra,icmsCompra,precoVenda,qtdVendida,qtdOcorrencia,ocorrencia";
	String fieldKey = "idEstoque";
	
	private DBQuery dbQuery = new DBQuery(tableName, fieldsName, fieldKey);
	
	
	// --- Operações no BD -------------------------
	
	public ResultSet listAll(){
		
		return this.dbQuery.select("");
		
	}
	
	public ResultSet listById(String coluna, String id) {
		
		return this.dbQuery.select((coluna + " = " + id));
		
	}
	
	public int save() {
		
		if(this.getIdEstoque() > 0) {
			return this.dbQuery.update(this.toArray());
		}
		else {
			return this.dbQuery.insert(this.toArray());
		}
	}
	
	public int delete() {
		
		if(this.getIdEstoque() > 0) {
			return this.dbQuery.delete(this.toArray());
		}
		return 0;
		
	}
	
	// --- toArray -------------------------
	
	private String[] toArray(){
		
		return new String[] {
				
			this.getIdEstoque() + "",
			this.getIdProduto() + "",
			this.getDtEntrada(),
			this.getQuantidade() + "",
			this.getDtFabricacao(),
			this.getDtVencimento(),
			this.getNfCompra(),
			this.getPrecoCompra() + "",
			this.getIcmsCompra() + "",
			this.getPrecoVenda() + "",
			this.getQtdVendida() + "",
			this.getQtdOcorrencia() + "",
			this.getOcorrencia()
		
		};
	}

	// --- Construtores -------------------------
	
	public Estoque(int idEstoque, int idProduto, String dtEntrada, int quantidade, String dtFabricacao,
			String dtVencimento, String nfCompra, Double precoCompra, Double icmsCompra, Double precoVenda,
			int qtdVendida, int qtdOcorrencia, String ocorrencia) {
		super();
		this.idEstoque = idEstoque;
		this.idProduto = idProduto;
		this.dtEntrada = dtEntrada;
		this.quantidade = quantidade;
		this.dtFabricacao = dtFabricacao;
		this.dtVencimento = dtVencimento;
		this.nfCompra = nfCompra;
		this.precoCompra = precoCompra;
		this.icmsCompra = icmsCompra;
		this.precoVenda = precoVenda;
		this.qtdVendida = qtdVendida;
		this.qtdOcorrencia = qtdOcorrencia;
		this.ocorrencia = ocorrencia;
	}
	
	// --- Getters e Setters ------------------------
	
	public Estoque() {}
	public int getIdEstoque() {
		return idEstoque;
	}

	public void setIdEstoque(int idEstoque) {
		this.idEstoque = idEstoque;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getDtEntrada() {
		return dtEntrada;
	}

	public void setDtEntrada(String dtEntrada) {
		this.dtEntrada = dtEntrada;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getDtFabricacao() {
		return dtFabricacao;
	}

	public void setDtFabricacao(String dtFabricacao) {
		this.dtFabricacao = dtFabricacao;
	}

	public String getDtVencimento() {
		return dtVencimento;
	}

	public void setDtVencimento(String dtVencimento) {
		this.dtVencimento = dtVencimento;
	}

	public String getNfCompra() {
		return nfCompra;
	}

	public void setNfCompra(String nfCompra) {
		this.nfCompra = nfCompra;
	}

	public Double getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(Double precoCompra) {
		this.precoCompra = precoCompra;
	}

	public Double getIcmsCompra() {
		return icmsCompra;
	}

	public void setIcmsCompra(Double icmsCompra) {
		this.icmsCompra = icmsCompra;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public int getQtdVendida() {
		return qtdVendida;
	}

	public void setQtdVendida(int qtdVendida) {
		this.qtdVendida = qtdVendida;
	}

	public int getQtdOcorrencia() {
		return qtdOcorrencia;
	}

	public void setQtdOcorrencia(int qtdOcorrencia) {
		this.qtdOcorrencia = qtdOcorrencia;
	}

	public String getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(String ocorrencia) {
		this.ocorrencia = ocorrencia;
	}



	
}
