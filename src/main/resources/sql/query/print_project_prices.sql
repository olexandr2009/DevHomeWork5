drop sequence  if exists alphabet_sequence;

create sequence  alphabet_sequence
start with 1
increment by 1
minvalue 1
maxvalue 26
cycle;

SELECT
    'Project ' || chr(Cast( 64 + nextval('alphabet_sequence') as integer) ) AS NAME,
    PRICE
FROM
    (SELECT
        p.project_id,
        SUM(w.salary) * (EXTRACT(MONTH FROM p.finish_date) - EXTRACT(MONTH FROM p.start_date) + 12 * (EXTRACT(YEAR FROM p.finish_date) - EXTRACT(YEAR FROM p.start_date))) AS PRICE
    FROM
        projects p
    JOIN
        project_workers pw ON p.project_id = pw.project_id
    JOIN
        workers w ON pw.worker_id = w.worker_id
    GROUP BY
        p.project_id, p.start_date, p.finish_date
   ORDER BY
      PRICE DESC
) AS PRICE;