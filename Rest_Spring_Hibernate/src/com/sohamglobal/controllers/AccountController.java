package com.sohamglobal.controllers;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import myEntitites.*;
import myModels.*;

@RestController
@RequestMapping("/api")
public class AccountController {
	
	@Autowired
	private AccountOperations acco;
	
	@GetMapping("/getacc")
	public ArrayList<Account> getaccount() {
		ArrayList<Account> list = new ArrayList<Account>();
		
		list=acco.getAllAccount();
		return list;
	}
	
	@GetMapping("/getaccbyno/{id}")
	public Account getaccountbyno(@PathVariable(value = "id") String accno) {
		Account a=new Account();
		
		a=acco.getAccountinfobyno(accno);
		return a;
	}
	@GetMapping("/getaccbytype/{type}")
	public ArrayList<Account> getaccountbytype(@PathVariable(value = "type") String acctype) {
		ArrayList<Account> list = new ArrayList<Account>();
		
		list=acco.getAllAccountbytype(acctype);
		return list;
	}
	
	@DeleteMapping("/deleteacc/{id}")
	public String deleteAccount(@PathVariable(value = "id") String accno) {
		String Status="";
		
		Status=acco.deleteAccount(accno);
		return Status;
	}
	
	
	@PostMapping("/newacc")
	public String newAccounts(@RequestBody Account obj)
	{
		
		
		acco.createAccount(obj);
		return "Account added";
	}
	
	@PostMapping("/transferamount")
	public String Transferamount(@RequestBody Transactions obj)
	{
		
		
		String stat=acco.TransferAmount(obj);
		return stat;
	}
	

}
