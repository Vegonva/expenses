package com.edoras.expenses.expensesService;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;

import com.edoras.expenses.expensesService.model.Expense;
import com.edoras.expenses.expensesService.service.ExpensesService;


@RestController
@RequestMapping("/api")
public class ExpensesServiceController {

	@Autowired
	ExpensesService expensesService;

	/*
       Retrieve All Expenses
  	*/
	@RequestMapping(value = "/expenses/", method = RequestMethod.GET)
	public List<Expense> listAllExpenses() {
		List<Expense> expenses = expensesService.findAllExpenses();
		if (expenses.isEmpty()) {
			//TODO
			//logger.error("No expenses found");
		}
		return expenses;
	}

	/*
       Create a Expense
  	*/
	@RequestMapping(value = "/expenses/", method = RequestMethod.POST)
	public void createExpense(@RequestBody Expense expense, UriComponentsBuilder ucBuilder) {
		//logger.info("Creating Expense : {}", expense);
		expensesService.saveExpense(expense);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/expenses/{id}").buildAndExpand(expense.getId()).toUri());
	}

	/*
       Update a Expense
  	*/
	@RequestMapping(value = "/expenses/{id}", method = RequestMethod.PUT)
	public void updateExpense(@PathVariable("id") long id, @RequestBody Expense expense) {
		//logger.info("Updating Expense : {}", expense);
		expensesService.updateExpense(expense);
	}

	/*
       Delete a Expense
  	*/
	@RequestMapping(value = "/expenses/{id}", method = RequestMethod.DELETE)
	public void deleteExpense(@PathVariable("id") long id) {
		//logger.info("Deleting Expense with id : {}", id);
		expensesService.deleteExpenseById(id);
	}

	/*
       Delete all Expenses
  	*/
	@RequestMapping(value = "/expenses/", method = RequestMethod.DELETE)
	public void deleteAllExpenses() {
		expensesService.deleteAllExpenses();
	}


}