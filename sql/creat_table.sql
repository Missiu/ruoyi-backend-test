-- ----------------------------
-- 1、活动模块账号管理表
-- ----------------------------
drop table if exists active_user;
create table active_user
(
    user_id     INT          NOT NULL AUTO_INCREMENT COMMENT '主键',
    real_name   VARCHAR(64)  NOT NULL COMMENT '姓名',
    school_name VARCHAR(128) NOT NULL COMMENT '学校',
    user_type   CHAR(1)     DEFAULT '2' COMMENT '账号身份（1评委 2教师）',
    group_name  CHAR(1)      NULL COMMENT '报名组别（1思政课程组 2公共基础课程组（不含思政） 3专业技能课程一组 4专业技能课程二组）',
    judge_scope CHAR(1)      NULL COMMENT '评选范围（1思政课程组 2公共基础课程组（不含思政） 3专业技能课程一组 4专业技能课程二组）',
    login_name  VARCHAR(64)  NOT NULL COMMENT '账号名',
    password    VARCHAR(128) NOT NULL COMMENT '密码',
    create_by   VARCHAR(32) DEFAULT '' COMMENT '创建者',
    create_time DATETIME COMMENT '创建时间',
    update_by   VARCHAR(32) DEFAULT '' COMMENT '更新者',
    update_time DATETIME COMMENT '更新时间',
    PRIMARY KEY (user_id)
) engine = innodb
  auto_increment = 200 comment = '账号表';

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('账号', '2000', '1', 'user', 'active/user/index', 1, 0, 'C', '0', '0', 'active:user:list', '#', 'admin', sysdate(), '', null, '账号菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('账号查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'active:user:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('账号新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'active:user:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('账号修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'active:user:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('账号删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'active:user:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('账号导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'active:user:export',       '#', 'admin', sysdate(), '', null, '');