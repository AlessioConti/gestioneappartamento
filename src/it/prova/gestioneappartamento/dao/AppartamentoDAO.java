package it.prova.gestioneappartamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import it.prova.gestioneappartamento.connection.MyConnection;
import it.prova.gestioneappartamento.model.Appartamento;

public class AppartamentoDAO {

	public Appartamento findByID(Long idInput) {
		if (idInput == null || idInput < 1)
			throw new RuntimeException("Impossibile recuperare appartamento");

		Appartamento result = null;
		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("select * from appartamento a where a.id =?;")) {
			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Appartamento();
					result.setQuartiere(rs.getString("quartiere"));
					result.setMetriQuadri(rs.getInt("metriquadri"));
					result.setPrezzo(rs.getInt("prezzo"));
					result.setDataCostruzione(rs.getDate("datacostruzione"));
				} else {
					return null;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return result;
	}

	public int insert(Appartamento appartamentoInput) {
		if (appartamentoInput == null || appartamentoInput.getId() < 1)
			throw new RuntimeException("Impossibile recuperare appartamento");

		int result = 0;
		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c.prepareStatement(
						"insert into appartamento(quartiere, metriquadri, prezzo, datacostruzione) values(?, ?, ?, ?);")) {
			ps.setString(1, appartamentoInput.getQuartiere());
			ps.setInt(2, appartamentoInput.getMetriQuadri());
			ps.setInt(3, appartamentoInput.getPrezzo());
			ps.setDate(4, new java.sql.Date(appartamentoInput.getDataCostruzione().getTime()));
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return result;
	}

	public int update(Appartamento appartamentoInput) {
		if (appartamentoInput == null || appartamentoInput.getId() < 1)
			throw new RuntimeException("Impossibile recuperare appartamento");

		int result = 0;

		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c.prepareStatement(
						"update appartamento set quartiere=?, metriquadri=?, prezzo=?, datacostruzione=? where id=?;")) {
			ps.setString(1, appartamentoInput.getQuartiere());
			ps.setInt(2, appartamentoInput.getMetriQuadri());
			ps.setInt(3, appartamentoInput.getPrezzo());
			ps.setDate(4, new java.sql.Date(appartamentoInput.getDataCostruzione().getTime()));
			ps.setLong(5, appartamentoInput.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return result;
	}
	
	public int delete(Appartamento appartamentoInput) {
		if (appartamentoInput == null || appartamentoInput.getId() < 1)
			throw new RuntimeException("Impossibile recuperare appartamento");

		int result = 0;
		try(Connection c = MyConnection.getConnection(); PreparedStatement ps = c.prepareStatement("delete from appartamento where id=?;")){
			ps.setLong(1, appartamentoInput.getId());
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return result;
	}

}
