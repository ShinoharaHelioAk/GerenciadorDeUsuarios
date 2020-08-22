package br.com.helio.enums;

public enum StatusUsuarioEnum {
	ATIVO("ativo"),
	BLOQUEADO("bloqueado"),
	INATIVO("inativo")
	;

	private final String statusUsuario;
	StatusUsuarioEnum(String i) {
		statusUsuario = i;
	}
	
	public String getStatusUsuario() {
		return statusUsuario;
	}
	
	
}
