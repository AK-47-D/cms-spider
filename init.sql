CREATE SCHEMA `cms_spider`
  DEFAULT CHARACTER SET utf8;
ALTER SCHEMA `cms_spider`
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_general_ci;

INSERT INTO `cms_spider`.`tech_article_tag` (`id`, `gmt_create`, `gmt_modified`, `tag_detail`, `tag_id`)
VALUES ('1', now(), now(), '编程技术', '1');

INSERT INTO `cms_spider`.`cron_trigger` (`cron`, `gmt_modify`, `gmt_create`, `is_deleted`, `task_id`)
VALUES ('0 */1 * * * ?', now(), now(), 0, '1');

INSERT INTO `cms_spider`.`cron_trigger` (`cron`, `gmt_modify`, `gmt_create`, `is_deleted`, `task_id`)
VALUES ('0 */1 * * * ?', now(), now(), 0, '2');

SELECT *
FROM cron_trigger;

SHOW TABLES;