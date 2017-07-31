package com.eunion.manage.node;

import com.eunion.manage.dto.node.NodeResponse;
import com.eunion.manage.entity.tree.TreeSystem;
import com.eunion.manage.service.node.TreeSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ys on 2016/4/27.
 */
@Controller
@RequestMapping("/data")
public class TreeListController {

    @Autowired
    private TreeSystemService treeSystemService;

    @RequestMapping(value = "/nodeList")
    @ResponseBody
    public Object getAllNodeByRole(){
        return treeSystemService.getNodeListByRole();
    }

    @RequestMapping(value = "/addNewNode",method = RequestMethod.POST)
    @ResponseBody
    public Object addNewNode(@RequestBody TreeSystem treeSystem){
        return  treeSystemService.save(treeSystem);
    }

    @RequestMapping(value = "/deleteNode",method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public Object deleteNode(TreeSystem treeSystem){
        treeSystemService.deleteNode(treeSystem);
        NodeResponse nodeResponse = new NodeResponse();
        nodeResponse.setMsg("保存成功！");
        nodeResponse.setStatus("000000");
        return nodeResponse;
    }
}
