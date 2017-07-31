package com.eunion.manage.article;

import com.eunion.manage.service.article.ArticleAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ys on 2016/4/22.
 */
@Controller
@RequestMapping("/data")
public class ArticleAccountController {

    @Autowired
    private ArticleAccountService articleAccountService;

    @ResponseBody
    @RequestMapping(value = "/getAllArticle",method = RequestMethod.GET)
    public Object getAllArticle(){
        return articleAccountService.getAllArticleAccount();
    }
}
