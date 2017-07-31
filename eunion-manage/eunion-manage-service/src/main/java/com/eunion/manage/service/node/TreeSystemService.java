package com.eunion.manage.service.node;

import com.eunion.manage.entity.tree.TreeSystem;

import java.util.List;

/**
 * Created by ys on 2016/4/27.
 */
public interface TreeSystemService {

    List<TreeSystem> getNodeListByRole();

    Object save(TreeSystem treeSystem);

    void deleteNode(TreeSystem treeSystem);
}
