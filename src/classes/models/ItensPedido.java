package classes.models;

import java.sql.ResultSet;

import classes.database.DBQuery;

public class ItensPedido {
	
	private int idItemPedido;
	private int ordem;
	private int idPedido;
	private int idEstoque;
	private int qtdItem;
	private String dtDevolucao;
	private String motivoDevolucao;
	
	
	// --- Conexão com o BD ---------------------
	
	String tableName = "lojinha.itemspedido";
	String fieldsName = "idItemPedido,ordem,idPedido,idEstoque,qtdItem,dtDevolucao,motivoDevolucao";
	String fieldKey = "idItemPedido";
	
	private DBQuery dbQuery = new DBQuery(tableName, fieldsName, fieldKey);

	
	// --- Operações no BD -------------------------
	
	public ResultSet listAll() {
		
		return this.dbQuery.select("");
	}
	
	public int save() {
		
		if(this.getIdItemPedido() > 0) {
			return this.dbQuery.update(this.toArray());
		}
		else{
			return this.dbQuery.insert(this.toArray());
		}
	}
	
	public int delete(){	
		
		if(this.getIdItemPedido() > 0) {
			return this.dbQuery.delete(this.toArray());
		}
		return 0;
	}
	
	
	// --- toArray -------------------------
	
	private String[] toArray() {
		
		return new String[] {
				
			this.getIdItemPedido() + "",
			this.getOrdem() + "",
			this.getIdPedido() + "",
			this.getIdEstoque() + "",
			this.getQtdItem() + "",
			this.getDtDevolucao(),
			this.getMotivoDevolucao()
				
		};
	}
	
	
	// --- Construtores -------------------------

	public ItensPedido() {}
	
	public ItensPedido(int idItemPedido, int ordem, int idPedido, int idEstoque, int qtdItem, String dtDevolucao,
			String motivoDevolucao) {
		super();
		this.idItemPedido = idItemPedido;
		this.ordem = ordem;
		this.idPedido = idPedido;
		this.idEstoque = idEstoque;
		this.qtdItem = qtdItem;
		this.dtDevolucao = dtDevolucao;
		this.motivoDevolucao = motivoDevolucao;
	}

	
	// --- Getters e Setters -------------------------
	
	public int getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(int idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getIdEstoque() {
		return idEstoque;
	}

	public void setIdEstoque(int idEstoque) {
		this.idEstoque = idEstoque;
	}

	public int getQtdItem() {
		return qtdItem;
	}

	public void setQtdItem(int qtdItem) {
		this.qtdItem = qtdItem;
	}

	public String getDtDevolucao() {
		return dtDevolucao;
	}

	public void setDtDevolucao(String dtDevolucao) {
		this.dtDevolucao = dtDevolucao;
	}

	public String getMotivoDevolucao() {
		return motivoDevolucao;
	}

	public void setMotivoDevolucao(String motivoDevolucao) {
		this.motivoDevolucao = motivoDevolucao;
	}
	
	
}
