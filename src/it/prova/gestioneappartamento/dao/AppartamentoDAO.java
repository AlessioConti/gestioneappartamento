package it.prova.gestioneappartamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import it.prova.gestioneappartamento.connection.MyConnection;
import it.prova.gestioneappartamento.model.Appartamento;

public class AppartamentoDAO {
	
	public Appartamento findByID(Long idInput) {
		if(idInput == null || idInput < 1)
			return null;
		
		Appartamento result = null;
		try(Connection c = MyConnection.getConnection(); PreparedStatement ps = c.prepareStatement("select * from appartamento a where a.id =?;")){
			ps.setLong(1, idInput);
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					result = new Appartamento();
					result.setQuartiere(rs.getString("quartiere"));
					result.setMetriQuadri(rs.getInt("metriquadri"));
					result.setPrezzo(rs.getInt("prezzo"));
					result.setDataCostruzione(rs.getDate("datacostruzione"));
				}else {
					return null;
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return result;
	}
	
}
