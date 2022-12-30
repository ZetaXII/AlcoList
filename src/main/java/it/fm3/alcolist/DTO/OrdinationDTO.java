package it.fm3.alcolist.DTO;

import java.util.ArrayList;
import java.util.Date;

public class OrdinationDTO {
	public ArrayList<String> cocktailUuidList;
	public String tableUuid;
	public OrdinationStatusEnum status;
	public String cereatedByUserUuid;
	public Date dateLastModified;
	public Date dateCreation;
	public String uuid;
	public Double total;
	public String createdByUserUuid;
	public String deliveredBy;
	public String executedBy;
	
	
	public Integer page;
	public Integer size;
}
