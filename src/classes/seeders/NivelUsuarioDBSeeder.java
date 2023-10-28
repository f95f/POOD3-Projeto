package classes.seeders;

import classes.models.NivelUsuario;

public class NivelUsuarioDBSeeder {

	public void importar() {
		
		gravar("Cliente");
		gravar("Funcionário");
		gravar("Financeiro");
		gravar("Caixa");
		gravar("Gerente");
		gravar("Diretor");
		gravar("Administrador");
		
	}
	
	private void gravar(String nivel) {
		
		NivelUsuario n = new NivelUsuario(0, nivel);
		n.save();
	}
	
}