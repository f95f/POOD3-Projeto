package classes.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import classes.models.NivelUsuario;

public class NivelUsuarioService {
	
	private NivelUsuario nivelUsuario = new NivelUsuario();
	
	public void listar() {
		System.out.println("\n > Listando n�veis de usu�rio: ");
		System.out.println("\n C�digo | N�vel ");
		
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
