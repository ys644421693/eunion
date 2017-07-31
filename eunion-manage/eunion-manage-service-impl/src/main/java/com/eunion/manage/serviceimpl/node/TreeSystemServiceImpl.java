package com.eunion.manage.serviceimpl.node;

import com.eunion.manage.common.util.PulicClass;
import com.eunion.manage.dto.BaseResponse;
import com.eunion.manage.dto.node.NodeResponse;
import com.eunion.manage.entity.tree.TreeSystem;
import com.eunion.manage.repository.node.TreeSystemRepository;
import com.eunion.manage.service.node.TreeSystemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * Created by ys on 2016/4/27.
 */
@Service("treeListService")
public class TreeSystemServiceImpl implements TreeSystemService {

    @Resource
    private TreeSystemRepository treeSystemRepository;

    public List<TreeSystem> getNodeListByRole() {
        return treeSystemRepository.findAll();
    }

    public Object save(TreeSystem treeSystem) {
        NodeResponse baseResponse = new NodeResponse();
        TreeSystem t = null;
        try{
            t = treeSystemRepository.save(treeSystem);
            baseResponse.setMsg("操作成功！");
            baseResponse.setStatus("000000");
            baseResponse.setTreeSystem(t);
        }catch (ConstraintViolationException e) {
            baseResponse.setMsg(PulicClass.getErrorMessage(e.getMessage()));
            baseResponse.setStatus("000001");
            return baseResponse;
        }
        return t;
    }

    public void deleteNode(TreeSystem treeSystem) {
        treeSystemRepository.delete(Long.parseLong(treeSystem.getId()+""));
    }
}
