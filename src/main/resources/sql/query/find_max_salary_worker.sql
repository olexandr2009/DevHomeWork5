SELECT * FROM workers
WHERE salary = (SELECT MAX(salary) FROM workers);