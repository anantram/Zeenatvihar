package org.anantram.zeenat.controllers;


import org.anantram.zeenat.domain.entities.Account;
import org.anantram.zeenat.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller // This means that this class is a Controller
@RequestMapping(path="/account") // This means URL's start with /demo (after Application path)
public class AccountController {
  private AccountService as;
  
  @Autowired
  public AccountController(final AccountService accRepo)
  {
	  this.as = accRepo;
  }

  @PostMapping(path="/add") // Map ONLY POST Requests
  public @ResponseBody String addNewUser (@RequestBody String flatNumber) 
  {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request
	  return as.createAccount(flatNumber);
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<Account> 
  getAllUsers() {
	  return as.getAccounts();
  }
}