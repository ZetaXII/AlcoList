package it.fm3.alcolist.DTO;

import java.util.ArrayList;
import java.util.Date;

public class OrdinationDTO {
	public ArrayList<String> cocktailUuidList;
	public String tableUuid;
	public OrdinationStatusEnum status;
	public Date dateLastModified;
	public Date dateCreation;
	public String uuid;
	public Double total;
	public String createdByUserUuid;
	public String deliveredBy;
	public String executedBy;
	
	
	public Integer page;
	public Integer size;
	@Override
	public String toString() {
		return "OrdinationDTO [cocktailUuidList=" + cocktailUuidList + ", tableUuid=" + tableUuid + ", status=" + status
				+ ", dateLastModified=" + dateLastModified + ", dateCreation=" + dateCreation + ", uuid=" + uuid
				+ ", total=" + total + ", createdByUserUuid=" + createdByUserUuid + ", deliveredBy=" + deliveredBy
				+ ", executedBy=" + executedBy + ", page=" + page + ", size=" + size + "]";
	}
	
	
	
	
}
