package com.edoras.expenses.expensesService.service;


import java.util.List;

import com.edoras.expenses.expensesService.model.Expense;

public interface ExpensesService {

	void saveExpense(Expense expense);

	void updateExpense(Expense expense);

	void deleteExpenseById(long id);

	List<Expense> findAllExpenses();

	void deleteAllExpenses();

}