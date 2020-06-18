package com.example.demo;



import javax.validation.Valid;

import edu.stayConnected.FBO.Account;
import edu.stayConnected.FBO.RegistrationForm;

public interface AccountDao {
	public @Valid void createAccount(@Valid Account account);
	public boolean validateAccount(RegistrationForm regiForm);
	
}
