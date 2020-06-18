package com.example.demo;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import edu.stayConnected.FBO.EmploymentOppForm;
@Repository
public class EmploymentOppDaoImpl implements EmploymentOppDao {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		//System.out.println(dataSource.toString());
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
		
	}
	
	@Override
	public void addOpportunity(EmploymentOppForm form, String userEmail){
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			String sql = "insert into posted_emp_opp(user_email, company_name, field, location, type, job_title, wage, start_date, end_date) ";
			sql = sql + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)" ; 
			jdbcTemplate.update(sql, userEmail, form.getCompany(), form.getField(),
					form.getLocation(), form.getType(), form.getJobTitle(),
					form.getWage(), form.getStartDate(), form.getEndDate());
			//logger.info("created product id = " + productForm.getId());
			transactionManager.commit(status);
		} catch (DataAccessException e){
			System.out.println("error in creating emp. opp. record, rolling back");
			transactionManager.rollback(status);
			throw e;
		}
	}
}
