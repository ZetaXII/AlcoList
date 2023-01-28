package it.fm3.alcolist.DTO;

import java.util.ArrayList;
import java.util.Date;

import it.fm3.alcolist.utils.OrdinationStatusEnum;

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
	
	public OrdinationDTO() {}
	
	
	public OrdinationDTO(ArrayList<String> cocktailUuidList, String tableUuid, OrdinationStatusEnum status,
			Date dateLastModified, Date dateCreation, String uuid, Double total, String createdByUserUuid) {
		super();
		this.cocktailUuidList = cocktailUuidList;
		this.tableUuid = tableUuid;
		this.status = status;
		this.dateLastModified = dateLastModified;
		this.dateCreation = dateCreation;
		this.uuid = uuid;
		this.total = total;
		this.createdByUserUuid = createdByUserUuid;
	}

	@Override
	public String toString() {
		return "OrdinationDTO [cocktailUuidList=" + cocktailUuidList + ", tableUuid=" + tableUuid + ", status=" + status
				+ ", dateLastModified=" + dateLastModified + ", dateCreation=" + dateCreation + ", uuid=" + uuid
				+ ", total=" + total + ", createdByUserUuid=" + createdByUserUuid + ", deliveredBy=" + deliveredBy
				+ ", executedBy=" + executedBy + ", page=" + page + ", size=" + size + "]";
	}
}