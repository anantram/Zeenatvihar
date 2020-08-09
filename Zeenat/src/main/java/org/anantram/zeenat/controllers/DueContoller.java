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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path="/dues") // This means URL's start with /demo (after Application path)
public class DueContoller {
  
	private DueService ds;
		  
	@Autowired
	public DueContoller(final DueService dueRepo) {
		this.ds = dueRepo;
	}

	/**
	 * JSON format {
		    "dueMonth":"MAY",
			"dueYear": 2021,
			"dueAmount": 1.1,
			"fine": 10
		}
	 * @param currMonth
	 * @return
	 * 
	 */
	@PostMapping(path="/triggerDues")
	public @ResponseBody String
		triggerDue(@RequestBody Due dueObject) {
			ds.triggerDue(dueObject);
			return "Done";
	}
	  
	@GetMapping(path="/getDues")
	public @ResponseBody Iterable<DueDetails> 
		getDues() {
			return ds.getDues();
		}
	
	/**
	 * localhost:8080/dues/getDues/FF21
	 * @param flatNumber
	 * @return
	 */
	@GetMapping(path="/getDues/{fNumber}")
	public @ResponseBody Iterable<DueDetails> 
		getDues(@PathVariable("fNumber") String flatNumber) {
			return ds.getDues(flatNumber);
		}
	
	/**
	 * localhost:8080/dues/totalDue/FF21
	 * @param flatNumber
	 * @return
	 */
	//https://stackoverflow.com/questions/59577705/spring-boot-using-pathvariable-to-get-hold-of-an-id-from-url
	@GetMapping(path="/totaldue/{fNumber}")
	public @ResponseBody double
	getDue(@PathVariable("fNumber") String flatNumber) {
		return ds.getTotalDue(flatNumber);
	} 
	
	/**
	 * localhost:8080/dues/payDue?FlatNumber=F44&DueYear=202&DueMonth=April
	 */
	@PostMapping(path="/payDue")
	public @ResponseBody String
		PayDue(@RequestParam("FlatNumber") String flatNumber,
				@RequestParam("DueYear") int dueYear,
				@RequestParam("DueMonth") String dueMonth) {
			return ds.payDue(flatNumber, dueMonth, dueYear);
	}
	
	/**
	 * localhost:8080/dues/editDue?DueYear=2020&DueMonth=May&DueAmount=82.5
	 * @param dueYear
	 * @param dueMonth
	 * @param dueAmount
	 * @return
	 */
	@PostMapping(path="/editDue")
	public @ResponseBody String
		editDue(
				@RequestParam("DueYear") int dueYear,
				@RequestParam("DueMonth") String dueMonth,
				@RequestParam("DueAmount") double dueAmount) {
			return ds.updateDue(dueMonth, dueYear, dueAmount);
	}
}
