package com.eunion.manage.serviceimpl.article;

import com.eunion.manage.entity.sysrole.Account;
import com.eunion.manage.repository.role.AccountRepository;
import com.eunion.manage.service.article.ArticleAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ys on 2016/4/22.
 */
@Service("articleAccountService")
public class ArticleAccountServiceImpl implements ArticleAccountService {

    @Resource
    public AccountRepository accountRepository;


    public List<Account> getAllArticleAccount() {
        System.err.println("ok---------------------------------------------------------------------");

        return accountRepository.findAll();

    }
}
