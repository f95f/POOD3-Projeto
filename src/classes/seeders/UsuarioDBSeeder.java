package classes.seeders;

import classes.models.Usuario;

public class UsuarioDBSeeder {

	public void importar() {
		gravar("user123@localhost","pass123",2,"John Doe","1234567890","123 Main St","Apt 4B","City","XY","54321","123-456-7890","","S");
		gravar("jane.smith@localhost","password",3,"Jane Smith","0987654321","456 Elm St","Suite 7C","Town","YZ","98765","987-654-3210","","S");
		gravar("testuser@localhost","testpass",4,"Test User","1112233445","789 Oak St","Unit 12","Village","ZW","67890","111-222-3333","","S");
		gravar("newuser@localhost","newpass",5,"New User","5556667777","101 Pine St","Apt 3D","Hamlet","XY","54321","999-888-7777","","S");
	}
	
	private void gravar(String email, String senha, int idNivelUsuario, String nome, String cpf,
			String endereco, String bairro, String cidade, String uf, String cep, String telefone, String foto,
			String ativo) {
		
		Usuario u = new Usuario(0, email, senha, idNivelUsuario, nome, cpf,
				 endereco, bairro, cidade, uf, cep, telefone, foto, ativo);
		u.save();
		
	}
}
