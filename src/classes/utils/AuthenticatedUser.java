package classes.utils;

public class AuthenticatedUser {

	private Integer userLoginId;
	private String userName;
	private Integer idNivelUsuario;
	private String token;
	public Integer getUserLogin() {
		return userLoginId;
	}
	public void setUserLogin(Integer userLogin) {
		this.userLoginId = userLogin;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getIdNivelUsuario() {
		return idNivelUsuario;
	}
	public void setIdNivelUsuario(Integer idNivelUsuario) {
		this.idNivelUsuario = idNivelUsuario;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public AuthenticatedUser(Integer userLogin, String userName, Integer idNivelUsuario, String token) {
		super();
		this.userLoginId = userLogin;
		this.userName = userName;
		this.idNivelUsuario = idNivelUsuario;
		this.token = token;
	}
	public AuthenticatedUser() {
		super();
	}
		
}
