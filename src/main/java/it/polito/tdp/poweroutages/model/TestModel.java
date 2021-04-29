package it.polito.tdp.poweroutages.model;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
	//	System.out.println(model.getNercList());

		PowerOutageDAO dao= new PowerOutageDAO();
		
		Nerc n=dao.getNercList().get(3);
	//	Guasti g1=dao.listaGuastiPerNerc(n).get(0);
	//	Guasti g2=dao.listaGuastiPerNerc(n).get(5);
		
		
	//	System.out.println(g2.getInizio());
	//	System.out.println(g2.getFine());
	//	System.out.println((model.oreDisservizio(g2.getInizio(), g2.getFine()))); 
		
		model.worstCase(n, 4, 200);
		
	}

}
