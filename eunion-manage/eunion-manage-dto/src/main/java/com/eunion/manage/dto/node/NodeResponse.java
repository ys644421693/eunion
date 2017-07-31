package com.eunion.manage.dto.node;


import com.eunion.manage.dto.BaseResponse;
import com.eunion.manage.entity.tree.TreeSystem;

/**
 * Created by ys on 2016/5/2.
 */
public class NodeResponse extends BaseResponse {

    private TreeSystem treeSystem;

    public TreeSystem getTreeSystem() {
        return treeSystem;
    }

    public void setTreeSystem(TreeSystem treeSystem) {
        this.treeSystem = treeSystem;
    }
}
