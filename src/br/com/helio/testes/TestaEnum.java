package br.com.helio.testes;

import javax.swing.JOptionPane;

import br.com.helio.enums.StatusUsuarioEnum;

public class TestaEnum {

	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "Valor do Enum: " + StatusUsuarioEnum.BLOQUEADO.getStatusUsuario());
	}

}
