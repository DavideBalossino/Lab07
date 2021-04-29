package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;

import it.polito.tdp.poweroutages.model.Nerc;

public class TestPowerOutagesDAO {

	public static void main(String[] args) {
		
		try {
			Connection connection = ConnectDB.getConnection();
			connection.close();
			System.out.println("Connection Test PASSED");
			
			PowerOutageDAO dao = new PowerOutageDAO() ;
			
			Nerc n=dao.getNercList().get(8);
			System.out.println(n);
			int b=0;
			while(b<10) {
			System.out.println(dao.listaGuastiPerNerc(n).get(b)) ;
			b++;
			}
		} catch (Exception e) {
			System.err.println("Test FAILED");
		}

	}

}
