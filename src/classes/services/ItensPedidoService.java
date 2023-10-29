package classes.services;

import java.util.Scanner;

import classes.models.ItensPedido;

public class ItensPedidoService {

	private ItensPedido itensPedido = new ItensPedido();
	private Scanner scanner = new Scanner(System.in);
	
	public void criar() {
		
		itensPedido.setIdEstoque(0);
		
		System.out.print("\n > Informe a ordem: ");
		itensPedido.setOrdem(Integer.parseInt(scanner.nextLine()));
		
		System.out.print("\n > Informe o código do pedido: ");
		itensPedido.setIdPedido(Integer.parseInt(scanner.nextLine()));
		
		System.out.print("\n > Informe o código do estoque: ");
		itensPedido.setIdEstoque(Integer.parseInt(scanner.nextLine()));
		
		System.out.print("\n > Informe a quantidade de itens: ");
		itensPedido.setQtdItem(Integer.parseInt(scanner.nextLine()));
		
		System.out.print("\n > Informe a data de devolução: ");
		itensPedido.setDtDevolucao(scanner.nextLine());
		
		System.out.print("\n > Informe o motivo da devolução: ");
		itensPedido.setMotivoDevolucao(scanner.nextLine());
		
		
		int saveStatus = itensPedido.save();
		
		if(saveStatus != 0) {
			
			System.out.println("\n\n [i] Registro salvo com sucesso!\n");
			
		}
		else {

			System.out.println("\n\n [!] Erro ao salvar!\n");

		}
	}
	
}
