drop TABLE IF  EXISTS  SYS_DICTVALUE;     --如果存在就删除表

-- Create table
create table  if  not exists SYS_DICTVALUE
(
  dictitem    INTEGER default 0 not null,
  subitem     VARCHAR2(32) not null,
  subitemname VARCHAR2(64) default '',
  macro       VARCHAR2(32) default '',
  constraint pk_t2 primary key (dictitem,subitem)  --联合主键
);