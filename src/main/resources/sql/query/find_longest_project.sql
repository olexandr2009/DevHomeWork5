select * from projects
order by (finish_date - start_date) desc limit 1;