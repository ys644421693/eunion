package com.eunion.manage.repository.role;

import com.eunion.manage.entity.sysrole.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yangshuo on 16/2/9.
 */
@Repository("accountRepository")
public interface AccountRepository extends CrudRepository<Account,Long> {

    Account findById(int id);

    Account findByUserName(String userName);

    List<Account> findAll();
}
