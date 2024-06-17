-- ----------------------------
-- 1、活动模块账号管理表
-- ----------------------------
DROP TABLE IF EXISTS `active_user`;
CREATE TABLE `active_user`
(
    `user_id`     INT          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `real_name`   VARCHAR(64)  NOT NULL COMMENT '姓名',
    `school_name` VARCHAR(128) NOT NULL COMMENT '学校',
    `user_type`   CHAR(1)     DEFAULT '2' COMMENT '账号身份（1评委 2教师）',
    `group_name`  CHAR(1)      NULL COMMENT '报名组别(1:思政课程组、2:公共基础课程组(不含思政)、3:专业技能课程一组、4:专业技能课程二组)',
    `judge_scope` CHAR(1)      NULL COMMENT '评选范围(1:思政课程组、2:公共基础课程组(不含思政)、3:专业技能课程一组、4:专业技能课程二组)',
    `login_name`  VARCHAR(64)  NOT NULL COMMENT '账号名',
    `password`    VARCHAR(128) NOT NULL COMMENT '密码（应用层需进行哈希处理后存储）',
    `create_by`   VARCHAR(32) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   VARCHAR(32) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`user_id`),
    INDEX `idx_user_type` (`user_type`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 200 COMMENT = '账号表';

-- ----------------------------
-- 2、作品信息表
-- ----------------------------
DROP TABLE IF EXISTS `active_works`;
CREATE TABLE `active_works`
(
    `work_id`       INT          NOT NULL AUTO_INCREMENT COMMENT '作品ID',
    `work_name`     VARCHAR(128) NOT NULL COMMENT '作品名称',
    `school_name`   VARCHAR(128) NOT NULL COMMENT '学校名称',
    `participant`   VARCHAR(255) NOT NULL COMMENT '参赛老师姓名',
    `contact_info`  VARCHAR(64)  NOT NULL COMMENT '联系方式',
    `average_score` DECIMAL(5, 2) DEFAULT NULL COMMENT '实时平均分',
    `group_name`    CHAR(1)      NOT NULL COMMENT '报名组别(1:思政课程组、2:公共基础课程组(不含思政)、3:专业技能课程一组、4:专业技能课程二组)',
    `view_count`    INT           DEFAULT 0 COMMENT '浏览次数',
    `create_time`   DATETIME      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`work_id`)
) ENGINE = InnoDB COMMENT ='作品表';

-- ----------------------------
-- 2、作品评价表
-- ----------------------------
DROP TABLE IF EXISTS `active_evaluation`;
CREATE TABLE `active_evaluation`
(
    `eva_id`                        INT           NOT NULL AUTO_INCREMENT COMMENT '评价ID',
    `work_id`                       INT           NOT NULL COMMENT '作品ID',
    `judge_name`                    VARCHAR(64)   NOT NULL COMMENT '评委姓名',
    `score_teaching_implementation` DECIMAL(5, 2) NOT NULL COMMENT '教学实施得分',
    `score_lesson_plan`             DECIMAL(5, 2) NOT NULL COMMENT '教案得分',
    `score_video_material`          DECIMAL(5, 2) NOT NULL COMMENT '视频材料得分',
    `score_talent_training`         DECIMAL(5, 2) NOT NULL COMMENT '人才培养得分',
    `score_course_standard`         DECIMAL(5, 2) NOT NULL COMMENT '课程标准得分',
    `score_teaching_material`       DECIMAL(5, 2) NOT NULL COMMENT '教学材料得分',
    `final_score`                   DECIMAL(5, 2) NOT NULL COMMENT '总得分',
    `evaluated_at`                  DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '评价时间',
    PRIMARY KEY (`eva_id`),
    INDEX `idx_work_id_eva_id` (`work_id`, `eva_id`),
    CONSTRAINT `fk_active_evaluation_works` FOREIGN KEY (`work_id`) REFERENCES `active_works` (`work_id`) ON DELETE CASCADE
) ENGINE = InnoDB COMMENT ='作品评价表';

-- ----------------------------
-- 2、文件管理表
-- ----------------------------
DROP TABLE IF EXISTS `active_file`;
CREATE TABLE `active_file`
(
    `file_id`           INT          NOT NULL AUTO_INCREMENT COMMENT '文件ID',
    `work_id`           INT          NOT NULL COMMENT '作品ID',
    `file_category`     VARCHAR(64)  NOT NULL COMMENT '文件类别',
    `file_name`         VARCHAR(255) NOT NULL COMMENT '文件名',
    `file_storage_path` VARCHAR(255) NOT NULL COMMENT '文件存储路径',
    `uploaded_at`       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
    PRIMARY KEY (`file_id`),
    INDEX `idx_work_id_file_id` (`work_id`, `file_id`),
    CONSTRAINT `fk_active_file_works` FOREIGN KEY (`work_id`) REFERENCES `active_works` (`work_id`) ON DELETE CASCADE
) ENGINE = InnoDB COMMENT ='作品相关文件表';

# 插入示例数据
INSERT INTO `active_user` (`real_name`, `school_name`, `user_type`, `group_name`, `judge_scope`, `login_name`,
                           `password`, `create_by`)
VALUES ('张三', '北京大学', '1', '', '2', 'zhangsan', 'hashed_password1', 'system'),
       ('李四', '清华大学', '2', '2', '', 'lisi', 'hashed_password2', 'system'),
       ('王五', '复旦大学', '2', '3', '', 'wangwu', 'hashed_password3', 'system'),
       ('赵六', '浙江大学', '1', '', '3', 'zhaoliu', 'hashed_password4', 'system');
INSERT INTO `active_works` (`work_name`, `school_name`, `participant`, `contact_info`, `group_name`)
VALUES ('数据挖掘技术', '清华大学', '李四', '23456789012', '2'),
       ('机器学习算法', '复旦大学', '王五', '34567890123', '3');
INSERT INTO `active_evaluation` (`work_id`, `judge_name`, `score_teaching_implementation`, `score_lesson_plan`,
                                 `score_video_material`,
                                 `score_talent_training`, `score_course_standard`, `score_teaching_material`,
                                 `final_score`)
VALUES (2, '张三', 85.00, 88.00, 87.00, 86.00, 89.00, 90.00, 87.40),
       (3, '赵六', 78.00, 80.00, 79.00, 81.00, 82.00, 83.00, 80.00);
INSERT INTO `active_file` (`work_id`, `file_category`, `file_name`, `file_storage_path`)
VALUES (2, '作品图片', '作品图片.png',
        'http://blog.huzhihao.top/blog-imgs/deploy/docker/attachments/68eb696d4a771101f1b4fe2bf4645511.png'),
       (2, '视频', '视频1.mkv', 'https://www.w3schools.com/html/movie.mp4'),
       (2, '视频', '视频2.mkv', 'https://www.w3schools.com/html/movie.mp4'),
       (2, '教案', '教案.docx', 'D:/Code/Study/RuoYi-Vue/uploadPath/active/2/教案.docx'),
       (2, '教学实施报告', '教学实施报告.pdf', 'http://www.leomay.com/upload/file/mmo-20170707165001.pdf'),
       (2, '专业人才培养方案', '专业人才培养方案.docx',
        'D:/Code/Study/RuoYi-Vue/uploadPath/active/2/专业人才培养方案.docx'),
       (2, '课程标准', '课程标准.pdf', 'http://www.leomay.com/upload/file/mmo-20170707165001.pdf'),
       (2, '其他附件', '其他附件1.pdf', 'http://www.leomay.com/upload/file/mmo-20170707165001.pdf'),

       (3, '作品图片', '作品图片.png',
        'http://blog.huzhihao.top/blog-imgs/deploy/docker/attachments/68eb696d4a771101f1b4fe2bf4645511.png'),
       (3, '视频', '视频1.mkv', 'https://www.w3schools.com/html/movie.mp4'),
       (3, '视频', '视频2.mkv', 'https://www.w3schools.com/html/movie.mp4'),
       (3, '教案', '教案.docx', 'D:/Code/Study/RuoYi-Vue/uploadPath/active/2/教案.docx'),
       (3, '教学实施报告', '教学实施报告.pdf', 'http://www.leomay.com/upload/file/mmo-20170707165001.pdf'),
       (3, '专业人才培养方案', '专业人才培养方案.docx',
        'D:/Code/Study/RuoYi-Vue/uploadPath/active/2/专业人才培养方案.docx'),
       (3, '课程标准', '课程标准.pdf', 'http://www.leomay.com/upload/file/mmo-20170707165001.pdf'),
       (3, '其他附件', '其他附件1.pdf', 'http://www.leomay.com/upload/file/mmo-20170707165001.pdf');


-- ----------------------------
-- 若依生成: 账号管理模块sql
-- ----------------------------
-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status,
                      perms, icon, create_by, create_time, update_by, update_time, remark)
values ('账号', '2000', '1', 'user', 'active/user/index', 1, 0, 'C', '0', '0', 'active:user:list', '#', 'admin',
        sysdate(), '', null, '账号菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status,
                      perms, icon, create_by, create_time, update_by, update_time, remark)
values ('账号查询', @parentId, '1', '#', '', 1, 0, 'F', '0', '0', 'active:user:query', '#', 'admin', sysdate(), '',
        null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status,
                      perms, icon, create_by, create_time, update_by, update_time, remark)
values ('账号新增', @parentId, '2', '#', '', 1, 0, 'F', '0', '0', 'active:user:add', '#', 'admin', sysdate(), '', null,
        '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status,
                      perms, icon, create_by, create_time, update_by, update_time, remark)
values ('账号修改', @parentId, '3', '#', '', 1, 0, 'F', '0', '0', 'active:user:edit', '#', 'admin', sysdate(), '', null,
        '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status,
                      perms, icon, create_by, create_time, update_by, update_time, remark)
values ('账号删除', @parentId, '4', '#', '', 1, 0, 'F', '0', '0', 'active:user:remove', '#', 'admin', sysdate(), '',
        null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status,
                      perms, icon, create_by, create_time, update_by, update_time, remark)
values ('账号导出', @parentId, '5', '#', '', 1, 0, 'F', '0', '0', 'active:user:export', '#', 'admin', sysdate(), '',
        null, '');

-- ----------------------------
-- 若依生成: 作品信息模块sql
-- ----------------------------
-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status,
                      perms, icon, create_by, create_time, update_by, update_time, remark)
values ('作品', '2000', '1', 'works', 'active/works/index', 1, 0, 'C', '0', '0', 'active:works:list', '#', 'admin',
        sysdate(), '', null, '作品菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status,
                      perms, icon, create_by, create_time, update_by, update_time, remark)
values ('作品查询', @parentId, '1', '#', '', 1, 0, 'F', '0', '0', 'active:works:query', '#', 'admin', sysdate(), '',
        null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status,
                      perms, icon, create_by, create_time, update_by, update_time, remark)
values ('作品新增', @parentId, '2', '#', '', 1, 0, 'F', '0', '0', 'active:works:add', '#', 'admin', sysdate(), '', null,
        '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status,
                      perms, icon, create_by, create_time, update_by, update_time, remark)
values ('作品修改', @parentId, '3', '#', '', 1, 0, 'F', '0', '0', 'active:works:edit', '#', 'admin', sysdate(), '',
        null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status,
                      perms, icon, create_by, create_time, update_by, update_time, remark)
values ('作品删除', @parentId, '4', '#', '', 1, 0, 'F', '0', '0', 'active:works:remove', '#', 'admin', sysdate(), '',
        null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status,
                      perms, icon, create_by, create_time, update_by, update_time, remark)
values ('作品导出', @parentId, '5', '#', '', 1, 0, 'F', '0', '0', 'active:works:export', '#', 'admin', sysdate(), '',
        null, '');

-- ----------------------------
-- 若依生成: 评价信息模块sql
-- ----------------------------
-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status,
                      perms, icon, create_by, create_time, update_by, update_time, remark)
values ('作品评价', '2000', '1', 'evaluation', 'active/evaluation/index', 1, 0, 'C', '0', '0', 'active:evaluation:list',
        '#', 'admin', sysdate(), '', null, '作品评价菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status,
                      perms, icon, create_by, create_time, update_by, update_time, remark)
values ('作品评价查询', @parentId, '1', '#', '', 1, 0, 'F', '0', '0', 'active:evaluation:query', '#', 'admin',
        sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status,
                      perms, icon, create_by, create_time, update_by, update_time, remark)
values ('作品评价新增', @parentId, '2', '#', '', 1, 0, 'F', '0', '0', 'active:evaluation:add', '#', 'admin', sysdate(),
        '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status,
                      perms, icon, create_by, create_time, update_by, update_time, remark)
values ('作品评价修改', @parentId, '3', '#', '', 1, 0, 'F', '0', '0', 'active:evaluation:edit', '#', 'admin', sysdate(),
        '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status,
                      perms, icon, create_by, create_time, update_by, update_time, remark)
values ('作品评价删除', @parentId, '4', '#', '', 1, 0, 'F', '0', '0', 'active:evaluation:remove', '#', 'admin',
        sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status,
                      perms, icon, create_by, create_time, update_by, update_time, remark)
values ('作品评价导出', @parentId, '5', '#', '', 1, 0, 'F', '0', '0', 'active:evaluation:export', '#', 'admin',
        sysdate(), '', null, '');