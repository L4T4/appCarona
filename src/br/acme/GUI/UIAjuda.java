package br.acme.GUI;

import javax.swing.JOptionPane;

abstract public class UIAjuda {
	
	public static void telaAjuda()
	{	JOptionPane.showMessageDialog(null, "-No app Carona o usuário(solicitante) pode solicitar uma carona ao motorista a um preço definido \n "
			+ "-O motorista deve aceitar a carona para começar a carona \n "
			+ "-O cadastro do gerente e motoristas só podem ser feitas pelo gerente \n "
			+ "-O cadastro do usuário pode ser feito por qualquer pessoa \n"
			+ "\n\nAproveite a CARONA !!!! \n ", "AJUDA",JOptionPane.INFORMATION_MESSAGE);		
	}
}
