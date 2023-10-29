package classes.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import classes.models.NivelUsuario;

public class NivelUsuarioService {
	
	private NivelUsuario nivelUsuario = new NivelUsuario();
	
	public void listar() {
		System.out.println("\n > Listando níveis de usuário: ");
		System.out.println("\n Código | Nível ");
		
		ResultSet rs = nivelUsuario.listAll();
		
		try {
			while(rs.next()) {
				nivelUsuario.setIdNivelUsuario(rs.getInt("IdNivelUsuario"));
				nivelUsuario.setNivel(rs.getString("descricao"));
				
				System.out.printf("\n %6d | %s", nivelUsuario.getIdNivelUsuario(), nivelUsuario.getNivel());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
