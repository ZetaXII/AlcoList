package it.fm3.alcolist.utils;

public enum OrdinationStatusEnum {
	CREATED("Creata",true,false),
	PENDING("In Attesa",true,false),
	SENTBACK("Rinviata",true,true),
	WORK_IN_PROGRESS("In Elaborazione",false,false),
	COMPLETED("Da consegnare",false,false),
	DELIVERED("Consegnata",false,false),
	ENDED("Terminata",false,false)
	;
	
	
	private final String label;
    private final boolean editable;
    private final boolean requireMessage;
    
	private OrdinationStatusEnum(String label, boolean editable, boolean requireMessage) {
		this.label = label;
		this.editable = editable;
		this.requireMessage = requireMessage;
	}
	
	public String getLabel() {
		return label;
	}
	public boolean isEditable() {
		return editable;
	}
	public boolean isRequireMessage() {
		return requireMessage;
	}
    
    
    
}


/*
 GESTIONE ORDINI
 creazione comanda 
 modifica comanda in base all stato
 cancellazione in base allo stato
 modifica stato di un ordinazione (funzione privata vedere come gestirla)
 Gestire l'esecuzione di una comanda BISOGNA CAPIRE COME VERRA' VISUALIZZATA
 QUANDO DEVO SOTTRARRE LA QUANTITA' DEI PRODOTTI?
 
 
 
 RICERCA CON PAGINAZIONE
 cocktail, prodotti?
 
 DEFINIRE COME GFESTIRE LA CANCELLAZIONE DELLE VARIE ENTITA'
 

 */