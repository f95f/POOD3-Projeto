package classes.utils;

public class PedidoDTO {
	private int idPedido;
	private String nomeUsuario;
	private String dtPedido;
	private String dtPagamento;
	private String notaFiscal;
	private String dtEnvio;
	private String dtRecebimento;
	private String entregaendereco;
	private String entregaCEP;
	private String entregaTelefone;
	private String entregaRefer;
	private String valorTotal;
	private int qtdItems;
	private String nomeItem;
	
	public PedidoDTO() {
		super();
	}

	public PedidoDTO(int idPedido, String nomeUsuario, String dtPedido, String dtPagamento, String notaFiscal,
			String dtEnvio, String dtRecebimento, String entregaendereco, String entregaCEP, String entregaTelefone,
			String entregaRefer, String valorTotal, int qtdItems, String nomeItem) {
		super();
		this.idPedido = idPedido;
		this.nomeUsuario = nomeUsuario;
		this.dtPedido = dtPedido;
		this.dtPagamento = dtPagamento;
		this.notaFiscal = notaFiscal;
		this.dtEnvio = dtEnvio;
		this.dtRecebimento = dtRecebimento;
		this.entregaendereco = entregaendereco;
		this.entregaCEP = entregaCEP;
		this.entregaTelefone = entregaTelefone;
		this.entregaRefer = entregaRefer;
		this.valorTotal = valorTotal;
		this.qtdItems = qtdItems;
		this.nomeItem = nomeItem;
	}

	public String getNomeItem() {
		return nomeItem;
	}

	public void setNomeItem(String nomeItem) {
		this.nomeItem = nomeItem;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
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

	public String getEntregaEndereco() {
		return this.entregaendereco; 
	}

	public void setEntregaEndereco(String rua, String numero, String bairro, String cidade, String uf) {
		this.entregaendereco = "Rua " + rua + ", " + numero + " - " + bairro + " - " + cidade + ", " + uf + ".";
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

	
}
