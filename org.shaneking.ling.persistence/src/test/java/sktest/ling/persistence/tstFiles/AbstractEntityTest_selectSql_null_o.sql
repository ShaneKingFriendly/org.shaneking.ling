[
select id,
       invalid,
       last_modify_date_time,
       last_modify_user_id,
       version,
       has_length,
       no_get_method,
       not_null_col,
       unique_col,
       re_name_col,
       long_text
from sktest1_schema.sktest1_table
where id = ?
  and invalid = ?
  and last_modify_date_time = ?
  and last_modify_user_id = ?
  and version = ?
  and has_length = ?
  and not_null_col = ?
  and unique_col = ?
  and re_name_col = ?
  and long_text = ? limit 30
offset 0,[1610866165373_KbTy6GDVwpB5rAYJjJb, N, 2021-01-16 14:49:25, 1610866165373_eXabaDd3OiEyivRv1GI, 0, hasLength, notNullCol, uniqueCol, reName, longText]]
