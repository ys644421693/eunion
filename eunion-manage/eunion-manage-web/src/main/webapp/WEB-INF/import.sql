insert into t_role(category,descript,roleName) values('admin','管理员','admin');

insert into t_tree_system(identify,treeName,parentId) values('nodeMange','节点管理',0);
insert into t_tree_system(identify,treeName,parentId) values('roleManage','角色管理',0);
insert into t_tree_system(identify,treeName,parentId) values('urlManage','url管理',0);
insert into t_tree_role(tree_id,role_id) values(1,1);
insert into t_tree_role(tree_id,role_id) values(2,1);
insert into t_tree_role(tree_id,role_id) values(3,1);

insert  into t_account(email,password,userName) values ('3@qq.com','21232f297a57a5a743894a0e4a801fc3','admin');
insert  into t_system_url(descript,url,urlName) values ('role','/system/data/nodelist','权限'),('node','/system/data/getallrole','角色');
insert  into url_role(url_id,role_id) values (1,1),(2,1);
insert  into account_role(account_id,role_id) values (1,1);