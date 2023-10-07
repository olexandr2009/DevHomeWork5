package org.example.databaseQuery;

import org.example.database.Database;
import org.example.databaseQuery.queryClasses.*;


import java.nio.file.*;
import java.sql.*;
import java.util.*;

public class DatabaseQueryService {
    private Database DATABASE;
    private static final Map<String, List<String>> COLUMN_NAMES = new HashMap<>();
    public static final String FIND_MAX_PROJECTS_CLIENT_SQL = "./src/main/resources/sql/query/find_max_projects_client.sql";
    public static final String FIND_LONGEST_PROJECT_SQL = "./src/main/resources/sql/query/find_longest_project.sql";
    public static final String FIND_MAX_SALARY_WORKER_SQL = "./src/main/resources/sql/query/find_max_salary_worker.sql";
    public static final String FIND_YOUNGEST_ELDEST_WORKERS_SQL = "./src/main/resources/sql/query/find_youngest_oldest_workers.sql";
    public static final String GET_PROJECTS_PRICES_SQL = "./src/main/resources/sql/query/print_project_prices.sql";


    public DatabaseQueryService(Database database) {
        if (database == null) {
            throw new NullPointerException("database should not be null");
        }
        this.DATABASE = database;
        initColumn_Names();
    }

    private void initColumn_Names() {
        COLUMN_NAMES.put(FIND_MAX_PROJECTS_CLIENT_SQL, List.of("NAME", "PROJECT_COUNT"));
        COLUMN_NAMES.put(FIND_LONGEST_PROJECT_SQL, List.of("PROJECT_ID", "CLIENT_ID", "START_DATE", "FINISH_DATE"));
        COLUMN_NAMES.put(FIND_MAX_SALARY_WORKER_SQL, List.of("WORKER_ID", "BIRTHDAY", "LEVEL", "SALARY", "WORKER_NAME"));
        COLUMN_NAMES.put(FIND_YOUNGEST_ELDEST_WORKERS_SQL, List.of("TYPE", "WORKER_NAME", "BIRTHDAY"));
        COLUMN_NAMES.put(GET_PROJECTS_PRICES_SQL, List.of("NAME", "PRICE"));
    }


    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> maxProjectCountClients = new ArrayList<>();

        try {
            String sqlQuery = Files.readString(Path.of(FIND_MAX_PROJECTS_CLIENT_SQL));
            ResultSet rs = DATABASE.getStatement().executeQuery(sqlQuery);
            List<String> columns = (COLUMN_NAMES.get(FIND_MAX_PROJECTS_CLIENT_SQL));

            while (rs.next()) {
                maxProjectCountClients.add(new MaxProjectCountClient(
                        rs.getString(columns.get(0)),
                        rs.getInt(columns.get(1))));
            }

            return maxProjectCountClients;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Project findLongestProject() {
        try {
            String sqlQuery = Files.readString(Path.of(FIND_LONGEST_PROJECT_SQL));
            ResultSet rs = DATABASE.getStatement().executeQuery(sqlQuery);
            List<String> columns = COLUMN_NAMES.get(FIND_LONGEST_PROJECT_SQL);
            if (rs.next()) {
                return new Project(rs.getInt(columns.get(0)),
                        rs.getInt(columns.get(1)),
                        rs.getDate(columns.get(2)),
                        rs.getDate(columns.get(3)));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Worker findMaxSalaryWorker() {
        try {
            String sqlQuery = Files.readString(Path.of(FIND_MAX_SALARY_WORKER_SQL));
            ResultSet rs = DATABASE.getStatement().executeQuery(sqlQuery);
            List<String> columns = COLUMN_NAMES.get(FIND_MAX_SALARY_WORKER_SQL);
            if (rs.next()) {
                return new Worker(rs.getInt(columns.get(0)),
                        rs.getDate(columns.get(1)),
                        Worker.Level.valueOf(rs.getString(columns.get(2))),
                        rs.getInt(columns.get(3)),
                        rs.getString(columns.get(4)));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<AgeWorker> findYoungestOldestWorkers() {
        List<AgeWorker> youngestOldestWorkers = new ArrayList<>();
        try {
            String sqlQuery = Files.readString(Path.of(FIND_YOUNGEST_ELDEST_WORKERS_SQL));
            ResultSet rs = DATABASE.getStatement().executeQuery(sqlQuery);
            List<String> columns = COLUMN_NAMES.get(FIND_YOUNGEST_ELDEST_WORKERS_SQL);
            while (rs.next()) {
                youngestOldestWorkers.add(new AgeWorker(
                        AgeWorker.Type.valueOf(rs.getString(columns.get(0))),
                        rs.getString(columns.get(1)),
                        rs.getDate(columns.get(2))));
            }
            return youngestOldestWorkers;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<ProjectPrice> getProjectsPrices() {
        List<ProjectPrice> projectsPrices = new ArrayList<>();
        try {
            String[] sqlScript = Files.readString(Path.of(GET_PROJECTS_PRICES_SQL))
                    .split(";");

            for (int i = 0; i < sqlScript.length - 1; i++) {
                DATABASE.executeUpdate(sqlScript[i] + ";");
            }

            ResultSet rs = DATABASE.getStatement().executeQuery(sqlScript[2]);
            List<String> columns = COLUMN_NAMES.get(GET_PROJECTS_PRICES_SQL);
            while (rs.next()) {
                projectsPrices.add(new ProjectPrice(
                        rs.getString(columns.get(0)),
                        rs.getInt(columns.get(1))
                ));
            }
            return projectsPrices;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}

