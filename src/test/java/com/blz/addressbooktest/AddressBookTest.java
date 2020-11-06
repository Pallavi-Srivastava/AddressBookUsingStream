package com.blz.addressbooktest;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import com.blz.addressbook.AddressBook;
import com.blz.addressbook.AddressBookFileIOService;
import com.blz.addressbook.UserDetails;

public class AddressBookTest {

	static AddressBook addressBook;

	@BeforeClass
	public static void createAddressBookObj() {
		addressBook = new AddressBook(null);
	}

	@Test
	public void given2EmloyeesDetailsWrittenToFileShouldMatchWithEntries() {
		UserDetails[] arrayOfEmps = {
				new UserDetails("Pallavi", "Srivastava", "Btm", "Blor", "Karnataka", "897600", "9988776622",
						"pal@gmail.com"),
				new UserDetails("Prags", "Srivastava", "Ether", "Blor", "Karnataka", "845600", "9982345622",
						"pra@gmail.com") };
		addressBook = new AddressBook(Arrays.asList(arrayOfEmps));
		AddressBook.writeAddressBookData(com.blz.addressbook.AddressBook.IOService.FILE_IO);
		long entries = AddressBookFileIOService.countEntries(com.blz.addressbook.AddressBook.IOService.FILE_IO);
		Assert.assertEquals(2, entries);
	}

	@Test
	public void readAddressBookFile() {
		addressBook.readDataFromFile();
		long entries = AddressBookFileIOService.countEntries(com.blz.addressbook.AddressBook.IOService.FILE_IO);
		Assert.assertEquals(2, entries);
	}
}
