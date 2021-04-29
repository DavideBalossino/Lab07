package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Guasti;
import it.polito.tdp.poweroutages.model.Nerc;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<Guasti> listaGuastiPerNerc(Nerc nerc){
		String sql="SELECT p.id,p.nerc_id,p.customers_affected,p.date_event_began,p.date_event_finished "
				+ "FROM poweroutages AS p "
				+ "WHERE p.nerc_id=?";
		List<Guasti> result=new ArrayList<Guasti>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, nerc.getId());
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Guasti g= new Guasti(rs.getInt("id"),nerc,rs.getInt("customers_affected"),rs.getTimestamp("date_event_began").toLocalDateTime(),rs.getTimestamp("date_event_finished").toLocalDateTime());
				result.add(g);
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	

}
