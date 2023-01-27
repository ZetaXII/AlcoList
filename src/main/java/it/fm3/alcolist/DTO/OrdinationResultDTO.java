
package it.fm3.alcolist.DTO;

import java.util.List;

import it.fm3.alcolist.entity.Ordination;

public class OrdinationResultDTO {

	public long totalResult;
	public long startIndex;
	public long itemsPerPage;
	public List<Ordination> ordinations;

}
