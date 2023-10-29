package classes.models;

import java.sql.ResultSet;

import classes.database.DBQuery;

public class Pedido {

	private int idPedido;
	private int idUsuario;
	private String dtPedido;
	private String dtPagamento;
	private String dtNotaFiscal;
	private String notaFiscal;
	private String dtEnvio;
	private String dtRecebimento;
	private int tipoFrete;
	private String rastreioFrete;
	private String entregaendereco;
	private String entregaNumero;
	private String entregaCompl;
	private String entregaBairro;
	private String entregaCidade;
	private String entregaUF;
	private String entregaCEP;
	private String entregaTelefone;
	private String entregaRefer;
	private String valorTotal;
	private int qtdItems;
	private String dtDevolucao;
	private String motivoDevolucao;
	
	// --- Conexão com o BD ---------------------
	
	String tableName = "lojinha.pedidos";
	String fieldsName = "idPedido,idUsuario,dtPedido,dtPagamento,dtNotaFiscal,notaFiscal,dtEnvio,dtRecebimento,tipoFrete,rastreioFrete,entregaendereco,entregaNumero,entregaCompl,entregaBairro,entregaCidade,entregaUF,entregaCEP,entregaTelefone,entregaRefer,valorTotal,qtdItems,dtDevolucao,motivoDevolucao";
	String fieldKey = "idPedido";
	
	private DBQuery dbQuery = new DBQuery(tableName, fieldsName, fieldKey);
	
	
	// --- Operações no BD -------------------------
	
	public ResultSet listAll() {
		
		return this.dbQuery.select("");
		
	}
	
	public int save() {
		
		if(this.getIdPedido() > 0) {
			return this.dbQuery.update(this.toArray());
		}
		else{
			return this.dbQuery.insert(this.toArray());
		}
		
	}
	
	public int delete(){	
		
		if(this.getIdPedido() > 0) {
			return this.dbQuery.delete(this.toArray());
		}
		return 0;
	}

	public ResultSet listById(String id) {
		
		return this.dbQuery.select(("idUsuario = " + id));
		
	}
	
	public ResultSet listByName(String name) {
		
		// A função LOWER() do MySql é utilizada aqui para incluir 
		// na busca ambos termos com letras maiúsculas e minúsculas.
		return this.dbQuery.select(("LOWER(nome) like LOWER('%" + name + "%')"));
		
	}
	
	// --- toArray -------------------------
	
	private String[] toArray(){
		
		return new String[] {
				
			this.getIdPedido() + "",
			this.getIdUsuario() + "",
			this.getDtPedido(),
			this.getDtPagamento(),
			this.getDtNotaFiscal(),
			this.getNotaFiscal(),
			this.getDtEnvio(),
			this.getDtRecebimento(),
			this.getTipoFrete() + "",
			this.getRastreioFrete(),
			this.getEntregaendereco(),
			this.getEntregaNumero(),
			this.getEntregaCompl(),
			this.getEntregaBairro(),
			this.getEntregaCidade(),
			this.getEntregaUF(),
			this.getEntregaCEP(),
			this.getEntregaTelefone(),
			this.getEntregaRefer(),
			this.getValorTotal(),
			this.getQtdItems() + "",
			this.getDtDevolucao(),
			this.getMotivoDevolucao()		
			
		};
	}
	// --- Construtores -------------------------
	
	public Pedido() {}

	public Pedido(int idPedido, int idUsuario, String dtPedido, String dtPagamento, String dtNotaFiscal,
			String notaFiscal, String dtEnvio, String dtRecebimento, int tipoFrete, String rastreioFrete,
			String entregaendereco, String entregaNumero, String entregaCompl, String entregaBairro,
			String entregaCidade, String entregaUF, String entregaCEP, String entregaTelefone, String entregaRefer,
			String valorTotal, int qtdItems, String dtDevolucao, String motivoDevolucao) {
		super();
		this.idPedido = idPedido;
		this.idUsuario = idUsuario;
		this.dtPedido = dtPedido;
		this.dtPagamento = dtPagamento;
		this.dtNotaFiscal = dtNotaFiscal;
		this.notaFiscal = notaFiscal;
		this.dtEnvio = dtEnvio;
		this.dtRecebimento = dtRecebimento;
		this.tipoFrete = tipoFrete;
		this.rastreioFrete = rastreioFrete;
		this.entregaendereco = entregaendereco;
		this.entregaNumero = entregaNumero;
		this.entregaCompl = entregaCompl;
		this.entregaBairro = entregaBairro;
		this.entregaCidade = entregaCidade;
		this.entregaUF = entregaUF;
		this.entregaCEP = entregaCEP;
		this.entregaTelefone = entregaTelefone;
		this.entregaRefer = entregaRefer;
		this.valorTotal = valorTotal;
		this.qtdItems = qtdItems;
		this.dtDevolucao = dtDevolucao;
		this.motivoDevolucao = motivoDevolucao;
	}

	
	// --- Getters e Setters -------------------------
	
	public int getIdPedido() {
		return idPedido;
	}
	
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDtPedido() {
		return dtPedido;
	}

	public void setDtPedido(String dtPedido) {
		this.dtPedido = dtPedido;
	}

	public String getDtPagamento() {
		return dtPagamento;
	}

	public void setDtPagamento(String dtPagamento) {
		this.dtPagamento = dtPagamento;
	}

	public String getDtNotaFiscal() {
		return dtNotaFiscal;
	}

	public void setDtNotaFiscal(String dtNotaFiscal) {
		this.dtNotaFiscal = dtNotaFiscal;
	}

	public String getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(String notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public String getDtEnvio() {
		return dtEnvio;
	}

	public void setDtEnvio(String dtEnvio) {
		this.dtEnvio = dtEnvio;
	}

	public String getDtRecebimento() {
		return dtRecebimento;
	}

	public void setDtRecebimento(String dtRecebimento) {
		this.dtRecebimento = dtRecebimento;
	}

	public int getTipoFrete() {
		return tipoFrete;
	}

	public void setTipoFrete(int tipoFrete) {
		this.tipoFrete = tipoFrete;
	}

	public String getRastreioFrete() {
		return rastreioFrete;
	}

	public void setRastreioFrete(String rastreioFrete) {
		this.rastreioFrete = rastreioFrete;
	}

	public String getEntregaendereco() {
		return entregaendereco;
	}

	public void setEntregaendereco(String entregaendereco) {
		this.entregaendereco = entregaendereco;
	}

	public String getEntregaNumero() {
		return entregaNumero;
	}

	public void setEntregaNumero(String entregaNumero) {
		this.entregaNumero = entregaNumero;
	}

	public String getEntregaCompl() {
		return entregaCompl;
	}

	public void setEntregaCompl(String entregaCompl) {
		this.entregaCompl = entregaCompl;
	}

	public String getEntregaBairro() {
		return entregaBairro;
	}

	public void setEntregaBairro(String entregaBairro) {
		this.entregaBairro = entregaBairro;
	}

	public String getEntregaCidade() {
		return entregaCidade;
	}

	public void setEntregaCidade(String entregaCidade) {
		this.entregaCidade = entregaCidade;
	}

	public String getEntregaUF() {
		return entregaUF;
	}

	public void setEntregaUF(String entregaUF) {
		this.entregaUF = entregaUF;
	}

	public String getEntregaCEP() {
		return entregaCEP;
	}

	public void setEntregaCEP(String entregaCEP) {
		this.entregaCEP = entregaCEP;
	}

	public String getEntregaTelefone() {
		return entregaTelefone;
	}

	public void setEntregaTelefone(String entregaTelefone) {
		this.entregaTelefone = entregaTelefone;
	}

	public String getEntregaRefer() {
		return entregaRefer;
	}

	public void setEntregaRefer(String entregaRefer) {
		this.entregaRefer = entregaRefer;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getQtdItems() {
		return qtdItems;
	}

	public void setQtdItems(int qtdItems) {
		this.qtdItems = qtdItems;
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
