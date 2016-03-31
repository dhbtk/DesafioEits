select setval('hibernate_sequence', (select max(id) from (
	select max(id) as id from users union all
	select max(id) as id from categories union all 
	select max(id) as id from products union all 
	select max(id) as id from batches) as my_tables));