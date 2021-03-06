package com.edoras.expenses.expensesService.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import org.springframework.stereotype.Service;

import com.edoras.expenses.expensesService.model.Expense;


@Service("expensesService")
public class ExpensesServiceImpl implements ExpensesService{

	static Connection conn;
	static Statement statement;

	public List<Expense> findAllExpenses() {
		ResultSet rs;
		List<Expense> expenses = new ArrayList<Expense>();
		try{
			createConnection();
			rs = ExpensesServiceImpl.statement.executeQuery("select id, description, value, expensedate from EXPENSES");
			while (rs.next()) {
				Expense expense = new Expense(rs.getLong("id"),rs.getString("description"), rs.getDouble("value"), rs.getDate("expensedate"));
				expenses.add(expense);
			}
		} catch (Exception e) {
			//TODO
			//logger.error("Error finding all expenses");
		}
		return expenses;
	}

	public void saveExpense(Expense expense) {
		//The id is an increment in the table, it should be not inserted
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = df.format(expense.getDate());
		try {
			String query = "insert into EXPENSES (description, value, expensedate) values('"+ expense.getDescription()+"', "+expense.getValue()+" , parsedatetime('" + strDate+"', 'dd-MM-yyyy'))";
			ExpensesServiceImpl.statement.execute(query);
		} catch (Exception e) {
			//TODO
			//logger.error("Error saving Expense : {}", expense)
		}
	}

	public void updateExpense(Expense expense) {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = df.format(expense.getDate());
		try {
			String query = "update EXPENSES set description = '"+ expense.getDescription()+"', value= "+expense.getValue()+" , expensedate= parsedatetime('" +
					strDate+"', 'dd-MM-yyyy') where id = "+expense.getId()+" ";
			ExpensesServiceImpl.statement.execute(query);
		} catch (Exception e) {
			//TODO
			//logger.error("Error updating Expense : {}", expense)
		}
	}

	public void deleteExpenseById(long id) {
		try{
			ExpensesServiceImpl.statement.execute("delete from EXPENSES where id = " + id);
		} catch (Exception e) {
			//TODO
			//logger.error("Error deleting Expense with id : {}", id);
		}
	}


	public void deleteAllExpenses(){
		try{
			ExpensesServiceImpl.statement.execute("delete from EXPENSES");
		} catch (Exception e) {
			//TODO
			//logger.error("Error deleting all Expenses");
		}
	}

	/*
       Functions to create and close connections to the DB.
  	*/
	private static void closeConnection() throws SQLException {
		statement.close();
		conn.close();
	}

	private static void createConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		conn = DriverManager.getConnection("jdbc:h2:mem:test", "sa", "");
                statement = conn.createStatement();
                ExpensesServiceImpl.statement.execute("create table IF NOT EXISTS expenses (id bigint auto_increment, description varchar(255),value double,expensedate date)");
	}

}
