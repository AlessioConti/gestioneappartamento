package it.prova.gestioneappartamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

	public List<Appartamento> list() {
		List<Appartamento> result = new ArrayList<Appartamento>();
		Appartamento appartamentoTemp = null;

		try (Connection c = MyConnection.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery("select * from appartamento;")) {
			while (rs.next()) {
				appartamentoTemp = new Appartamento();
				appartamentoTemp.setId(rs.getLong("id"));
				appartamentoTemp.setQuartiere(rs.getString("quartiere"));
				appartamentoTemp.setMetriQuadri(rs.getInt("metriquadri"));
				appartamentoTemp.setPrezzo(rs.getInt("prezzo"));
				appartamentoTemp.setDataCostruzione(rs.getDate("datacostruzione"));
				result.add(appartamentoTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
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
		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("delete from appartamento where id=?;")) {
			ps.setLong(1, appartamentoInput.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return result;
	}

	public List<Appartamento> findByExample(Appartamento example) {

		if (example == null || example.getId() < 1)
			throw new RuntimeException("Impossibile recuperare appartamento");

		List<Appartamento> listaAppartamenti = new ArrayList<Appartamento>();
		Appartamento appartamentoTemp = null;

		String query = "select * from appartamento a where a.id>1";

		if (example.getQuartiere() != null)
			query += " and a.quartiere = '" + example.getQuartiere() + "'";
		if (example.getMetriQuadri() != 0)
			query += " and a.metriquadri = " + example.getMetriQuadri();
		if (example.getPrezzo() != 0)
			query += " and a.prezzo = " + example.getPrezzo();
		if (example.getDataCostruzione() != null)
			query += " and a.datacostruzione = '" + example.getDataCostruzione() + "'";

		query += ";";

		try (Connection c = MyConnection.getConnection(); PreparedStatement ps = c.prepareStatement(query)) {

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					appartamentoTemp = new Appartamento();
					appartamentoTemp.setId(rs.getLong("id"));
					if (example.getQuartiere() != null)
						appartamentoTemp.setQuartiere(rs.getString("quartiere"));
					if (example.getMetriQuadri() != 0)
						appartamentoTemp.setMetriQuadri(rs.getInt("metriquadri"));
					if (example.getPrezzo() != 0)
						appartamentoTemp.setPrezzo(rs.getInt("prezzo"));
					if (example.getDataCostruzione() != null)
						appartamentoTemp.setDataCostruzione(rs.getDate("datacostruzione"));
					listaAppartamenti.add(appartamentoTemp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return listaAppartamenti;

	}

}
