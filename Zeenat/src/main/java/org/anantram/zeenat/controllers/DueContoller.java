package org.anantram.zeenat.controllers;

import org.anantram.zeenat.domain.DueDetails;
import org.anantram.zeenat.domain.entities.Due;
import org.anantram.zeenat.services.DueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path="/dues") // This means URL's start with /demo (after Application path)
public class DueContoller {
  
	private DueService ds;
		  
	@Autowired
	public DueContoller(final DueService dueRepo) {
		this.ds = dueRepo;
	}

	@PostMapping(path="/triggerDues")
	public @ResponseBody String
		triggerDue(@RequestBody Due currMonth) {
			ds.triggerDue(currMonth);
			return "Done";
	}
	  
	@GetMapping(path="/getDues")
	public @ResponseBody Iterable<DueDetails> 
		getDues() {
			return ds.getDues();
		}
	
	@GetMapping(path="/getDues/{fNumber}")
	public @ResponseBody Iterable<DueDetails> 
		getDues(@PathVariable("fNumber") String flatNumber) {
			return ds.getDues(flatNumber);
		}
	
	@GetMapping(path="/totaldue/{fNumber}")
	public @ResponseBody double
	getDue(@PathVariable("fNumber") String flatNumber) {
		return ds.getTotalDue(flatNumber);
	} 
}
