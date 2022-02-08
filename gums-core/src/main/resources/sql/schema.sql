drop table if exists gums_login_history;
drop table if exists gums_permission;
drop table if exists gums_role;
drop table if exists gums_role_permission;
drop table if exists gums_mservice;
drop table if exists gums_service_cfg;
drop table if exists gums_user;
drop table if exists gums_user_role;

create table gums_service_cfg
(
    id         serial primary key,
    service_id int,
    cfg_key    varchar,
    cfg_value  varchar,
    create_dt  timestamp not null,
    update_dt  timestamp default null,
    remark     varchar   default null
);

comment on table gums_service_cfg is '服务配置信息表';
comment on column gums_service_cfg.id is '主键';
comment on column gums_service_cfg.service_id is '服务表id';
comment on column gums_service_cfg.cfg_key is '配置信息主键';
comment on column gums_service_cfg.cfg_value is '配置信息值';
comment on column gums_service_cfg.create_dt is '创建日期';
comment on column gums_service_cfg.update_dt is '更新日期';
comment on column gums_service_cfg.remark is '备注';


create table gums_mservice
(
    id           serial primary key,
    service_name varchar,
    redirect_uri varchar,
    data_url     varchar,
    create_dt    timestamp not null,
    update_dt    timestamp default null,
    remark       varchar   default null
);
comment on table gums_mservice is '服务信息表';
comment on column gums_mservice.id is '主键';
comment on column gums_mservice.service_name is '服务名称';
comment on column gums_mservice.redirect_uri is '登录成功后重定向页面到应用的地址';
comment on column gums_mservice.data_url is '数据推送地址';
comment on column gums_mservice.create_dt is '创建日期';
comment on column gums_mservice.update_dt is '更新日期';
comment on column gums_mservice.remark is '备注';

create table gums_user
(
    id           serial primary key,
    username     varchar,
    phone        varchar(11),
    phone_status bool      default false,
    email        varchar,
    email_status bool      default false,
    password     varchar,
    data         jsonb,
    service_id   int       not null,
    create_dt    timestamp not null,
    update_dt    timestamp default null,
    remark       varchar   default null
);
comment on table gums_user is '用户信息表';
comment on column gums_user.id is '主键';
comment on column gums_user.username is '用户名';
comment on column gums_user.phone is '手机号';
comment on column gums_user.phone_status is '手机号验证状态';
comment on column gums_user.email is '邮箱';
comment on column gums_user.email_status is '邮箱验证状态';
comment on column gums_user.password is '密码';
comment on column gums_user.data is '用户详细信息，由服务决定其格式';
comment on column gums_user.service_id is '关联服务id';
comment on column gums_user.create_dt is '创建日期';
comment on column gums_user.update_dt is '更新日期';
comment on column gums_user.remark is '备注';

create table gums_permission
(
    id              serial primary key,
    permission_code varchar   not null,
    permission_name varchar,
    service_id      int       not null,
    create_dt       timestamp not null,
    remark          varchar default null
);
comment on table gums_permission is '权限表';
comment on column gums_permission.id is '主键';
comment on column gums_permission.permission_code is '权限编码';
comment on column gums_permission.permission_name is '权限名称';
comment on column gums_permission.create_dt is '创建日期';
comment on column gums_permission.remark is '备注';

create table gums_role
(
    id         serial primary key,
    role_code  varchar   not null,
    role_name  varchar,
    p_role_id  int,
    service_id int       not null,
    create_dt  timestamp not null,
    update_dt  timestamp default null,
    remark     varchar   default null
);
comment on table gums_role is '服务配置信息表';
comment on column gums_role.id is '主键';
comment on column gums_role.role_code is '角色编码';
comment on column gums_role.role_name is '角色名称';
comment on column gums_role.p_role_id is '父角色id';
comment on column gums_role.service_id is '服务id';
comment on column gums_role.create_dt is '创建日期';
comment on column gums_role.update_dt is '更新日期';
comment on column gums_role.remark is '备注';
create table gums_user_role
(
    id      serial primary key,
    role_id int not null,
    user_id int not null
);
comment on table gums_user_role is '用户角色关联表';
comment on column gums_user_role.id is '主键';
comment on column gums_user_role.role_id is '角色id';
comment on column gums_user_role.user_id is '用户id';

create table gums_role_permission
(
    id      serial primary key,
    role_id int not null,
    prm_id  int not null
);
comment on table gums_role_permission is '角色权限关联表';
comment on column gums_role_permission.id is '主键';
comment on column gums_role_permission.role_id is '角色id';
comment on column gums_role_permission.prm_id is '权限id';

create table gums_login_history
(
    id         serial primary key,
    user_id    int       not null,
    service_id int       not null,
    platform   varchar,
    ip         varchar,
    browser    varchar,
    create_dt  timestamp not null,
    remark     varchar default null
);
comment on table gums_login_history is '登录信息表';
comment on column gums_login_history.id is '主键';
comment on column gums_login_history.user_id is '用户id';
comment on column gums_login_history.service_id is '服务id';
comment on column gums_login_history.platform is '登录平台';
comment on column gums_login_history.ip is '登录ip';
comment on column gums_login_history.browser is '登录浏览器';
comment on column gums_login_history.create_dt is '创建日期';
comment on column gums_login_history.remark is '备注';