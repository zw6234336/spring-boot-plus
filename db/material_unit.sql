CREATE TABLE `material_unit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `unit_code` varchar(32) NOT NULL COMMENT '单位编码',
  `unit_name` varchar(64) NOT NULL COMMENT '单位名称',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0-未删除 1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_unit_code` (`unit_code`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='物料单位表';

-- 插入一些基础数据
INSERT INTO `material_unit` (`unit_code`, `unit_name`) VALUES
('PCS', '个'),
('BOX', '箱'),
('KG', '千克'),
('M', '米'),
('L', '升');