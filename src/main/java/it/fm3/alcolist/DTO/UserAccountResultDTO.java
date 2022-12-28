package it.fm3.alcolist.DTO;

import java.util.List;

import it.fm3.alcolist.entity.UserAccount;

public class UserAccountResultDTO {

	public long totalResult;
	public long startIndex;
	public long itemsPerPage;
	public List<UserAccount> userAccounts;

}
