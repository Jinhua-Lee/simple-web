-- 头条表创建
create table if not exists `tb_head_line`
(
    line_id        int(100)      not null auto_increment comment '头条ID',
    line_name      varchar(100)           default null comment '头条名称',
    line_link      varchar(200)  not null comment '头条链接',
    line_img       varchar(2000) not null comment '头条图片地址',
    priority       int(2)                 default null comment '展示的优先级',
    enable_status  int(2)        not null default 0 comment '可用状态',
    create_time    datetime               default null comment '创建时间',
    last_edit_time datetime               default null comment '最近修改时间',
    primary key (line_id)
) engine = innodb
    auto_increment = 1
    default charset utf8;

-- 店铺类型表创建
create table if not exists `tb_shop_category`
(
    shop_category_id   int(11)      not null auto_increment comment '店铺类别ID',
    shop_category_name varchar(100) not null default '' comment '店铺类别名称',
    shop_category_desc varchar(1000)         default '' comment '店铺类别描述',
    shop_category_img  varchar(2000)         default null comment '店铺类别图片地址',
    priority           int(2)       not null default 0 comment '店铺类别展示优先级',
    create_time        datetime              default null comment '创建时间',
    last_edit_time     datetime              default null comment '最近修一次改时间',
    parent_id          int(11)               default null comment '店铺类别的父类别',
    primary key (shop_category_id)
) engine = innodb
    auto_increment = 1
    default charset utf8;