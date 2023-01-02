package it.fm3.alcolist.utils;
public enum FlavourEnum {
	DOLCE ("Sweet"),
	ACIDO ("Sour"), 
	AMARO ("Bitter"), 
	SECCO ("Dry");
	
	private String flavour;
	
	public String getFlavour() {
		return flavour;
	}
	
	FlavourEnum(String flavour) {
		this.flavour = flavour;
	}
}