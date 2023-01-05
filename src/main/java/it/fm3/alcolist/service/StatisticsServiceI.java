package it.fm3.alcolist.service;

public interface StatisticsServiceI {
	Integer getByNumbersOfUsers (String role);
	Integer getNumbersOfCreatedByUser(String UserUuid) throws Exception;
	Integer getNumbersOfDeliveredByUser(String UserUuid) throws Exception;
	Integer getNumbersOfExecutedByUser(String UserUuid) throws Exception;
	
	//cocktail più venduti divisi
		// categoria
		// cocktail
		// numero totale di cocktail
	//prodotti che stanno per finire
}