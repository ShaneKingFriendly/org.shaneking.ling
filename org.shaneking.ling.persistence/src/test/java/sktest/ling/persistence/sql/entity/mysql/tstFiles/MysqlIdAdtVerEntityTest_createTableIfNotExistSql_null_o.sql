drop procedure if exists p_sktest1_table_create;
delimiter
$$
create procedure p_sktest1_table_create()
begin
if not exists (select * from information_schema.tables where table_schema = 'sktest1_schema' and table_name = 'sktest1_table')
then

create table `sktest1_schema`.`sktest1_table`
(
    `version`               int          not null comment 'Version for optimistic locking',
    `id`                    varchar(40)  not null comment 'Uniquely identifies',
    `invalid`               varchar(1) comment 'The invalid status of record {Y:invalid,N:valid(default)}',
    `last_modify_date_time` varchar(20) comment 'The last modification date time of record',
    `last_modify_user_id`   varchar(40) comment 'The last modified user of record',
    `has_length`            varchar(10) comment '',
    `no_get_method`         varchar(255) comment '',
    `not_null_col`          varchar(255) not null comment '',
    `unique_col`            varchar(255) comment '',
    `re_name_col`           varchar(255) comment '',
    `long_text`             longtext comment '',
    primary key (`id`)
);

alter table `sktest1_schema`.`sktest1_table`
    add unique index `u_idx_has_length_not_null_col` (`has_length` asc, `not_null_col` asc);
alter table `sktest1_schema`.`sktest1_table`
    add unique index `u_idx_unique_col` (`unique_col` asc);

end if;
end;
$$
delimiter ;
call p_sktest1_table_create();
drop procedure if exists p_sktest1_table_create;
