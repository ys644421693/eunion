package com.eunion.manage.sysrole;
import com.eunion.manage.entity.sysrole.Role;
import com.eunion.manage.entity.sysrole.SystemUrl;
import com.eunion.manage.service.role.RoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ys on 2016/5/6.
 */
@Controller
@RequestMapping("/data")
public class RoleController {

    private Logger logger = Logger.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    @ResponseBody
    public Object addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }

    @RequestMapping(value = "/getAllRole",method = RequestMethod.GET)
    @ResponseBody
    public  Object getAllData(){
        logger.info("---------------getAllRole---------------");
        return roleService.getAllData();
    }

    @RequestMapping(value = "deleteRole",method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteRole(Role role){
        roleService.deleteRole(role);
    }

    @RequestMapping(value = "updateRole",method = RequestMethod.PUT)
    @ResponseBody
    public Object  updateRole(Role role){
        return roleService.addRole(role);
    }

    @RequestMapping(value = "/getRoleByUrl",method = RequestMethod.GET)
    @ResponseBody
    public Object getRoleByUrl(SystemUrl systemUrl){
        return roleService.getRoleBySystemUrls(systemUrl);
    }
}
