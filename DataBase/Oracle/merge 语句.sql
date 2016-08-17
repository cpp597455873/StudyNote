merge into PUB_FUND_EXT a
using (SELECT '000010' AS fundcode FROM dual) c
on (c.fundcode=a.fundcode)
when matched then
  update set a.manager_name = 'b' 
when not matched then
  insert (fundcode, manager_name) values ('000010', 'A')