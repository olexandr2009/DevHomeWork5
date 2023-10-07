SELECT
  c.client_name AS NAME,
  COUNT(p.project_id) AS PROJECT_COUNT
FROM clients c
LEFT JOIN projects p ON c.client_id = p.client_id
GROUP BY c.client_name
HAVING COUNT(p.project_id) = (
  SELECT MAX(project_count)
  FROM (
    SELECT COUNT(project_id) AS project_count
    FROM projects
    GROUP BY client_id
  ) AS project_counts
);