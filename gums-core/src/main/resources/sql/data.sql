INSERT INTO gums_permission (id, permission_code, permission_name, service_id, create_dt, remark)
VALUES (1, 'queryUserList', '查询用户列表', -1, '2022-01-20', null);
INSERT INTO gums_permission (id, permission_code, permission_name, service_id, create_dt, remark)
VALUES (2, 'deleteUser', '删除用户', -1, '2022-01-20', null);
INSERT INTO gums_permission (id, permission_code, permission_name, service_id, create_dt, remark)
VALUES (3, 'updateUser', '更新用户信息', -1, '2022-01-20', null);
INSERT INTO gums_permission (id, permission_code, permission_name, service_id, create_dt, remark)
VALUES (4, 'lockUser', '锁定用户', -1, '2022-01-20', null);


INSERT INTO gums_role (id, role_code, role_name, p_role_id, service_id, create_dt, update_dt, remark)
VALUES (2, 'user', '普通用户', null, -1, '2022-01-20', null, '普通用户');
INSERT INTO gums_role (id, role_code, role_name, p_role_id, service_id, create_dt, update_dt, remark)
VALUES (3, 'admin', '管理员', null, -1, '2022-01-20', null, null);



INSERT INTO gums_role_permission (id, role_id, prm_id)
VALUES (1, 2, 1);
INSERT INTO gums_role_permission (id, role_id, prm_id)
VALUES (2, 2, 2);
INSERT INTO gums_role_permission (id, role_id, prm_id)
VALUES (3, 2, 3);
INSERT INTO gums_role_permission (id, role_id, prm_id)
VALUES (4, 3, 2);
INSERT INTO gums_role_permission (id, role_id, prm_id)
VALUES (5, 3, 3);
INSERT INTO gums_role_permission (id, role_id, prm_id)
VALUES (6, 3, 4);



INSERT INTO gums_user (id, username, phone, email, password, service_id, create_dt, update_dt, remark)
VALUES (1, 'zhenghai', '13300000000', 'a@celess.cn', 'df75040e67dfdf213daadd6f55eef0ad', -1, '2022-01-20', null, null);



INSERT INTO gums_user_role (id, role_id, user_id)
VALUES (2, 3, 1);
INSERT INTO gums_user_role (id, role_id, user_id)
VALUES (1, 2, 1);