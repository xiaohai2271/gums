drop table if exists dums_login_history;
drop table if exists dums_permission;
drop table if exists dums_role;
drop table if exists dums_role_permission;
drop table if exists dums_mservice;
drop table if exists dums_service_cfg;
drop table if exists dums_user;
drop table if exists dums_user_role;

create table dums_service_cfg
(
    id         serial primary key,
    service_id varchar,
    cfg_key    varchar,
    cfg_value  varchar,
    create_dt  date not null,
    update_dt  date    default null,
    remark     varchar default null
);

comment on table dums_service_cfg is '服务配置信息表';
comment on column dums_service_cfg.id is '主键';
comment on column dums_service_cfg.service_id is '服务表id';
comment on column dums_service_cfg.cfg_key is '配置信息主键';
comment on column dums_service_cfg.cfg_value is '配置信息值';
comment on column dums_service_cfg.create_dt is '创建日期';
comment on column dums_service_cfg.update_dt is '更新日期';
comment on column dums_service_cfg.remark is '备注';


create table dums_mservice
(
    id           serial primary key,
    service_name varchar,
    create_dt    date not null,
    update_dt    date    default null,
    remark       varchar default null
);
comment on table dums_mservice is '服务信息表';
comment on column dums_mservice.id is '主键';
comment on column dums_mservice.service_name is '服务名称';
comment on column dums_mservice.create_dt is '创建日期';
comment on column dums_mservice.update_dt is '更新日期';
comment on column dums_mservice.remark is '备注';

create table dums_user
(
    id           serial primary key,
    username     varchar,
    phone        varchar(11),
    phone_status bool    default false,
    email        varchar,
    password     varchar,
    service_id   int  not null,
    create_dt    date not null,
    update_dt    date    default null,
    remark       varchar default null
);
comment on table dums_user is '用户信息表';
comment on column dums_user.id is '主键';
comment on column dums_user.username is '用户名';
comment on column dums_user.phone is '手机号';
comment on column dums_user.email is '邮箱';
comment on column dums_user.password is '密码';
comment on column dums_user.service_id is '关联服务id';
comment on column dums_user.create_dt is '创建日期';
comment on column dums_user.update_dt is '更新日期';
comment on column dums_user.remark is '备注';

create table dums_permission
(
    id              serial primary key,
    permission_code varchar not null,
    permission_name varchar,
    service_id      int     not null,
    create_dt       date    not null,
    remark          varchar default null
);
comment on table dums_permission is '权限表';
comment on column dums_permission.id is '主键';
comment on column dums_permission.permission_code is '权限编码';
comment on column dums_permission.permission_name is '权限名称';
comment on column dums_permission.create_dt is '创建日期';
comment on column dums_permission.remark is '备注';

create table dums_role
(
    id         serial primary key,
    role_code  varchar not null,
    role_name  varchar,
    p_role_id  int,
    service_id int     not null,
    create_dt  date    not null,
    update_dt  date    default null,
    remark     varchar default null
);
comment on table dums_role is '服务配置信息表';
comment on column dums_role.id is '主键';
comment on column dums_role.role_code is '角色编码';
comment on column dums_role.role_name is '角色名称';
comment on column dums_role.p_role_id is '父角色id';
comment on column dums_role.service_id is '服务id';
comment on column dums_role.create_dt is '创建日期';
comment on column dums_role.update_dt is '更新日期';
comment on column dums_role.remark is '备注';
create table dums_user_role
(
    id      serial primary key,
    role_id int not null,
    user_id int not null
);
comment on table dums_user_role is '用户角色关联表';
comment on column dums_user_role.id is '主键';
comment on column dums_user_role.role_id is '角色id';
comment on column dums_user_role.user_id is '用户id';

create table dums_role_permission
(
    id      serial primary key,
    role_id int not null,
    prm_id  int not null
);
comment on table dums_role_permission is '角色权限关联表';
comment on column dums_role_permission.id is '主键';
comment on column dums_role_permission.role_id is '角色id';
comment on column dums_role_permission.prm_id is '权限id';

create table dums_login_history
(
    id         serial primary key,
    user_id    int  not null,
    service_id int  not null,
    platform   varchar,
    ip         varchar,
    browser    varchar,
    create_dt  date not null,
    remark     varchar default null
);
comment on table dums_login_history is '登录信息表';
comment on column dums_login_history.id is '主键';
comment on column dums_login_history.user_id is '用户id';
comment on column dums_login_history.service_id is '服务id';
comment on column dums_login_history.platform is '登录平台';
comment on column dums_login_history.ip is '登录ip';
comment on column dums_login_history.browser is '登录浏览器';
comment on column dums_login_history.create_dt is '创建日期';
comment on column dums_login_history.remark is '备注';