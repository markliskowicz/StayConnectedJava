package com.example.demo;

import java.util.Iterator;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import edu.stayConnected.FBO.Account;
import edu.stayConnected.FBO.RegistrationForm;

@Repository
public class AccountDaoImpl implements AccountDao {

	@Autowired
	private DataSourceTransactionManager transactionManager;

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}

	@Override
	public void createAccount(Account account) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			String SQL = "INSERT INTO account (rnumber, email) " + " values (?, ?)";
			jdbcTemplate.update(SQL, account.getRnumber(), account.getEmail());

			SQL = "insert into authority(email, role, code, registered) " + "values (?, ?, ?, ?)";
			jdbcTemplate.update(SQL, account.getEmail(), account.getRole(), account.getCode(), false);

			transactionManager.commit(status);

		} catch (DataAccessException e) {
			System.out.println("Error in creating account record, rolling back");
			transactionManager.rollback(status);
			throw e;
		}
	}

	@Override
	public boolean validateAccount(RegistrationForm regiForm) {
		boolean result = false;
		String code = "";
		boolean registered = true;

		try {
			String SQL = "select code from authority where email = ?";
			code = jdbcTemplate.queryForObject(SQL, new Object[] { regiForm.getEmail() }, String.class);

		} catch (DataAccessException e) {
			System.out.println("exception");
		}

		try {
			String SQL = "select registered from authority where email = ?";
			registered = jdbcTemplate.queryForObject(SQL, new Object[] { regiForm.getEmail() }, boolean.class);

		} catch (DataAccessException e) {
			System.out.println("exception");
		}

		if (code.equals(regiForm.getCode()) && registered == false) {
			setAccountActive(regiForm.getEmail());
			result = true;
		}

		return result;
	}

	private void setAccountActive(String email) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			String SQL = "UPDATE authority SET registered = true WHERE email = ? ";
			jdbcTemplate.update(SQL, email);

			transactionManager.commit(status);

		} catch (DataAccessException e) {
			transactionManager.rollback(status);
			throw e;
		}
		
	}
<<<<<<< HEAD
	
=======

>>>>>>> refs/remotes/origin/master
}
