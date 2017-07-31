package com.eunion.manage.serviceimpl.role;

import com.eunion.manage.entity.sysrole.Account;
import com.eunion.manage.repository.role.AccountRepository;
import com.eunion.manage.service.role.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
	@Resource
	private AccountRepository accountRepository;

	@Transactional
	public void add(Account account){
		accountRepository.save(account);
	}

	public List<Account> listUser() {
		return null;
	}


}
