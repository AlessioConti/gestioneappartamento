package it.prova.gestioneappartamento.test;

import java.util.Date;
import java.util.List;


import it.prova.gestioneappartamento.dao.AppartamentoDAO;
import it.prova.gestioneappartamento.model.Appartamento;

public class TestAppartamento {

	public static void main(String[] args) {
		
		AppartamentoDAO appartamentoDAOInstance = new AppartamentoDAO();
		/*
		testInserimentoAppartamento(appartamentoDAOInstance);
		
		testUpdateAppartamento(appartamentoDAOInstance);
		
		testDeleteAppartamento(appartamentoDAOInstance);
		*/
		testFindById(appartamentoDAOInstance);
		
	}
	
	public static void testInserimentoAppartamento(AppartamentoDAO appartamentoDAOInstance) {
		System.out.println("testInserimentoAppartamento inizializzato.....");
		int appartamentiInseriti = appartamentoDAOInstance.insert(new Appartamento(6L, "Magliana", 100, 189000, new Date("2022/05/16")));
		if(appartamentiInseriti <1)
			throw new RuntimeException("testInserimentoAppartamento FALLITO");
		System.out.println("testInserimentoAppartamento concluso.....");
	}
	
	public static void testUpdateAppartamento(AppartamentoDAO appartamentoDAOInstance) {
		System.out.println("testUpdateAppartamento inizializzato.....");
		Appartamento appartamentoUpdate = new Appartamento(6L, "Magliana", 120, 189000, new Date("2022/05/16"));
		int quantiAppartamentiModificati = appartamentoDAOInstance.update(appartamentoUpdate);
		System.out.println("Sono stati modificati " + quantiAppartamentiModificati+ " appartamenti");
		System.out.println("testUpdateAppartamento concluso.....");
	}
	
	public static void testDeleteAppartamento(AppartamentoDAO appartamentoDAOInstance) {
		System.out.println("testDeleteAppartamento inizializzato.....");
		Appartamento appartamentoDelete = new Appartamento();
		appartamentoDelete.setId(5L);
		int quantiAppartamentiCancellati = appartamentoDAOInstance.delete(appartamentoDelete);
		System.out.println("Sono stati cancellati " +quantiAppartamentiCancellati+ " appartamenti");
		System.out.println("testDeleteAppartamento concluso........");
		
	}
	
	public static void testFindById(AppartamentoDAO appartamentoDAOInstance) {
		System.out.println("testFindById inizializzato......");
		List<Appartamento> elencoAppartamenti = appartamentoDAOInstance.list();
		if(elencoAppartamenti.size() < 1)
			throw new RuntimeException("testFindById FALLITO: non ci sono elementi");
		Appartamento appartamentoProva = elencoAppartamenti.get(0);
		
		Appartamento appartamentoDaCercareConDAO = appartamentoDAOInstance.findByID(appartamentoProva.getId());
		if(appartamentoDaCercareConDAO == null || !appartamentoDaCercareConDAO.getQuartiere().equals(appartamentoProva.getQuartiere()))
			throw new RuntimeException("testFindById FALLITO: i nomi non corrispondono");
		System.out.println("testFindById concluso.....");
		
	}

}
