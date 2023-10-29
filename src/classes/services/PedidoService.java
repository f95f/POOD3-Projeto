package classes.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import classes.models.Pedido;

public class PedidoService {

	private Pedido pedido = new Pedido();
	private Scanner scanner = new Scanner(System.in);
	
	public void criar() {
		
		pedido.setIdPedido(0);

		System.out.print("\n > Informe o código do usuário: ");
		pedido.setIdUsuario(Integer.parseInt(scanner.nextLine()));
		
		System.out.print("\n > Informe a data do pedido: ");
		pedido.setDtPedido(scanner.nextLine());
		
		System.out.print("\n > Informe a data do pagamento: ");
		pedido.setDtPagamento(scanner.nextLine());

		System.out.print("\n > Informe a data da nota fiscal: ");
		pedido.setDtNotaFiscal(scanner.nextLine());

		System.out.print("\n > Informe o código da nota fiscal: ");
		pedido.setNotaFiscal(scanner.nextLine());

		System.out.print("\n > Informe a data do envio: ");
		pedido.setDtEnvio(scanner.nextLine());

		System.out.print("\n > Informe a data do recebimento: ");
		pedido.setDtRecebimento(scanner.nextLine());

		System.out.print("\n > Informe o tipo de frete: ");
		pedido.setTipoFrete(Integer.parseInt(scanner.nextLine()));

		System.out.print("\n > Informe o código de rastreio: ");
		pedido.setRastreioFrete(scanner.nextLine());

		System.out.print("\n > Informe o endereço de entrega: ");
		pedido.setEntregaendereco(scanner.nextLine());

		System.out.print("\n > Informe o número: ");
		pedido.setEntregaNumero(scanner.nextLine());

		System.out.print("\n > Informe o complemento: ");
		pedido.setEntregaCompl(scanner.nextLine());

		System.out.print("\n > Informe o bairro: ");
		pedido.setEntregaBairro(scanner.nextLine());

		System.out.print("\n > Informe a cidade: ");
		pedido.setEntregaCidade(scanner.nextLine());

		System.out.print("\n > Informe o estado: ");
		pedido.setEntregaUF(scanner.nextLine());

		System.out.print("\n > Informe o CEP: ");
		pedido.setEntregaCEP(scanner.nextLine());

		System.out.print("\n > Informe o telefone: ");
		pedido.setEntregaTelefone(scanner.nextLine());

		System.out.print("\n > Informe a referência: ");
		pedido.setEntregaRefer(scanner.nextLine());

		System.out.print("\n > Informe o valor total: ");
		pedido.setValorTotal(scanner.nextLine());

		System.out.print("\n > Informe a quantidade de itens: ");
		pedido.setQtdItems(Integer.parseInt(scanner.nextLine()));

		System.out.print("\n > Informe a data de devolução: ");
		pedido.setDtDevolucao(scanner.nextLine());

		System.out.print("\n > Informe o motivo da devolução: ");
		pedido.setMotivoDevolucao(scanner.nextLine());
	
		int saveStatus = pedido.save();
		
		if(saveStatus != 0) {
			
			System.out.println("\n\n [i] Pedido salvo com sucesso!\n");
			
		}
		else {

			System.out.println("\n\n [!] Erro ao salvar!\n");

		}
	}

	public void listar() {
		
		System.out.println("\n > Listando pedidos: ");
//		System.out.println("\n Código | Nome                           | Fabricante                     | Marca                | Modelo               | Cód. Categoria | Descrição                                                    | Medida          | Largura      | Altura       | Profundidade   | Peso           | cor               ");

		ResultSet rs = pedido.listAll();
		
		try {
			while(rs.next()) {
				
				pedido.setIdPedido(rs.getInt("idPedido"));
				pedido.setIdUsuario(rs.getInt("idUsuario"));
				pedido.setDtPedido(rs.getString("dtPedido"));
				pedido.setDtPagamento(rs.getString("dtPagamento"));
				pedido.setDtNotaFiscal(rs.getString("dtNotaFiscal"));
				pedido.setNotaFiscal(rs.getString("notaFiscal"));
				pedido.setDtEnvio(rs.getString("dtEnvio"));
				pedido.setDtRecebimento(rs.getString("dtRecebimento"));
				pedido.setTipoFrete(rs.getInt("tipoFrete"));
				pedido.setRastreioFrete(rs.getString("rastreioFrete"));
				pedido.setEntregaendereco(rs.getString("entregaendereco"));
				pedido.setEntregaNumero(rs.getString("entregaNumero"));
				pedido.setEntregaCompl(rs.getString("entregaCompl"));
				pedido.setEntregaBairro(rs.getString("entregaBairro"));
				pedido.setEntregaCidade(rs.getString("entregaCidade"));
				pedido.setEntregaUF(rs.getString("entregaUF"));
				pedido.setEntregaCEP(rs.getString("entregaCEP"));
				pedido.setEntregaTelefone(rs.getString("entregaTelefone"));
				pedido.setEntregaRefer(rs.getString("entregaRefer"));
				pedido.setValorTotal(rs.getString("valorTotal"));
				pedido.setQtdItems(rs.getInt("qtdItems"));
				pedido.setDtDevolucao(rs.getString("dtDevolucao"));
				pedido.setMotivoDevolucao(rs.getString("motivoDevolucao"));
						
//				A linha abaixo formata cada linha da tabela para que estas mantenham o mesmo tamanho. 
				System.out.printf("\n %6d | %6d  | %-10s | %-10s | %-10s |"
								+ " %-20s | %-10s | %-10s | %s | %-20s |"
								+ " %-30s | %-10s | %-20s | %-20s | %-20s |"
								+ " %-2s | %-10s | %-15s | %-20s | %-20s |"
								+ " %-6d | %-10s | %-30s" + 
						pedido.getIdPedido(),
						pedido.getIdUsuario(),
						pedido.getDtPedido(),
						pedido.getDtPagamento(),
						pedido.getDtNotaFiscal(),
						
						pedido.getNotaFiscal(),
						pedido.getDtEnvio(),
						pedido.getDtRecebimento(),
						pedido.getTipoFrete(),
						pedido.getRastreioFrete(),
						
						pedido.getEntregaendereco(),
						pedido.getEntregaNumero(),
						pedido.getEntregaCompl(),
						pedido.getEntregaBairro(),
						pedido.getEntregaCidade(),
						
						pedido.getEntregaUF(),
						pedido.getEntregaCEP(),
						pedido.getEntregaTelefone(),
						pedido.getEntregaRefer(),
						pedido.getValorTotal(),
						
						pedido.getQtdItems(),
						pedido.getDtDevolucao(),
						pedido.getMotivoDevolucao()	
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Pedido buscarPorId(String coluna, int id) {
		System.out.println("\n > Pesquisando pedido pelo código " + id + ":");
		
		ResultSet rs = pedido.listById(coluna, id + "");
		
		try {
			if(rs.next()) {
				
				pedido.setIdPedido(rs.getInt("idPedido"));
				pedido.setIdUsuario(rs.getInt("idUsuario"));
				pedido.setDtPedido(rs.getString("dtPedido"));
				pedido.setDtPagamento(rs.getString("dtPagamento"));
				pedido.setDtNotaFiscal(rs.getString("dtNotaFiscal"));
				pedido.setNotaFiscal(rs.getString("notaFiscal"));
				pedido.setDtEnvio(rs.getString("dtEnvio"));
				pedido.setDtRecebimento(rs.getString("dtRecebimento"));
				pedido.setTipoFrete(rs.getInt("tipoFrete"));
				pedido.setRastreioFrete(rs.getString("rastreioFrete"));
				pedido.setEntregaendereco(rs.getString("entregaendereco"));
				pedido.setEntregaNumero(rs.getString("entregaNumero"));
				pedido.setEntregaCompl(rs.getString("entregaCompl"));
				pedido.setEntregaBairro(rs.getString("entregaBairro"));
				pedido.setEntregaCidade(rs.getString("entregaCidade"));
				pedido.setEntregaUF(rs.getString("entregaUF"));
				pedido.setEntregaCEP(rs.getString("entregaCEP"));
				pedido.setEntregaTelefone(rs.getString("entregaTelefone"));
				pedido.setEntregaRefer(rs.getString("entregaRefer"));
				pedido.setValorTotal(rs.getString("valorTotal"));
				pedido.setQtdItems(rs.getInt("qtdItems"));
				pedido.setDtDevolucao(rs.getString("dtDevolucao"));
				pedido.setMotivoDevolucao(rs.getString("motivoDevolucao"));
						
//				A linha abaixo formata cada linha da tabela para que estas mantenham o mesmo tamanho. 
				System.out.printf("\n %6d | %6d  | %-10s | %-10s | %-10s |"
								+ " %-20s | %-10s | %-10s | %s | %-20s |"
								+ " %-30s | %-10s | %-20s | %-20s | %-20s |"
								+ " %-2s | %-10s | %-15s | %-20s | %-20s |"
								+ " %-6d | %-10s | %-30s" + 
						pedido.getIdPedido(),
						pedido.getIdUsuario(),
						pedido.getDtPedido(),
						pedido.getDtPagamento(),
						pedido.getDtNotaFiscal(),
						
						pedido.getNotaFiscal(),
						pedido.getDtEnvio(),
						pedido.getDtRecebimento(),
						pedido.getTipoFrete(),
						pedido.getRastreioFrete(),
						
						pedido.getEntregaendereco(),
						pedido.getEntregaNumero(),
						pedido.getEntregaCompl(),
						pedido.getEntregaBairro(),
						pedido.getEntregaCidade(),
						
						pedido.getEntregaUF(),
						pedido.getEntregaCEP(),
						pedido.getEntregaTelefone(),
						pedido.getEntregaRefer(),
						pedido.getValorTotal(),
						
						pedido.getQtdItems(),
						pedido.getDtDevolucao(),
						pedido.getMotivoDevolucao()	
				);
				
				return pedido;
			}
			else {
				System.out.println("\n [!] Código não encontrado!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void apagar(int id) {
		
		System.out.println("\n > Apagando pedido");
		pedido = this.buscarPorId("idPedido", id);
			
		if(pedido == null) { return; }
		
		String resposta = "";
		do {
			System.out.println("\n\n > Confirmar exclusão? [sim/não]: ");
			resposta = scanner.next().toLowerCase();
			if(resposta.equals("sim") || resposta.equals("s")) {
				
				int deleteStatus = pedido.delete();
				
				if(deleteStatus == 1) {
					System.out.println("\n [i] Pedido apagado!");
				}
				else {
					System.out.println("\n [!] Erro ao apagar!\n");	
				}
				break;
			}
			if(resposta.equals("não") || resposta.equals("n") || resposta.equals("nao")) {
				System.out.println("\n [i] Nenhum pedido apagado.\n");
				break;
			}
		}
		while(true);
	}
}
