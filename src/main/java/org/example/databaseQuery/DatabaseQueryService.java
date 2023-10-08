package org.example.databaseQuery;

import org.example.database.Database;
import org.example.databaseQuery.queryClasses.*;


import java.nio.file.*;
import java.sql.*;
import java.util.*;

public class DatabaseQueryService {
    private final Database DATABASE;
    private static final Map<String, List<String>> COLUMN_NAMES = new HashMap<>();
    public static final String FIND_MAX_PROJECTS_CLIENT_SQL = "./src/main/resources/sql/query/find_max_projects_client.sql";
    public static final String FIND_LONGEST_PROJECT_SQL = "./src/main/resources/sql/query/find_longest_project.sql";
    public static final String FIND_MAX_SALARY_WORKER_SQL = "./src/main/resources/sql/query/find_max_salary_worker.sql";
    public static final String FIND_YOUNGEST_ELDEST_WORKERS_SQL = "./src/main/resources/sql/query/find_youngest_oldest_workers.sql";
    public static final String GET_PROJECTS_PRICES_SQL = "./src/main/resources/sql/query/print_project_prices.sql";
    public static final String FIND_WORKER_BY_ID_SQL = "./src/main/resources/sql/query/find_worker_by_id.sql";
    public static final String FIND_WORKERS_BY_NAME_SQL = "./src/main/resources/sql/query/find_worker_by_name.sql";
    public static final String FIND_CLIENT_BY_ID_SQL = "./src/main/resources/sql/query/find_client_by_id.sql";
    public static final String FIND_CLIENTS_BY_NAME_SQL = "./src/main/resources/sql/query/find_clients_by_name.sql";

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
        COLUMN_NAMES.put(FIND_YOUNGEST_ELDEST_WORKERS_SQL, List.of("TYPE", "WORKER_NAME", "BIRTHDAY"));
        COLUMN_NAMES.put(GET_PROJECTS_PRICES_SQL, List.of("NAME", "PRICE"));
        List<String> workerColumns = List.of("WORKER_ID", "BIRTHDAY", "LEVEL", "SALARY", "WORKER_NAME");
        COLUMN_NAMES.put(FIND_MAX_SALARY_WORKER_SQL, workerColumns);
        COLUMN_NAMES.put(FIND_WORKER_BY_ID_SQL, workerColumns);
        COLUMN_NAMES.put(FIND_WORKERS_BY_NAME_SQL, workerColumns);
        List<String> clientColumns = List.of("CLIENT_ID", "CLIENT_NAME");
        COLUMN_NAMES.put(FIND_CLIENT_BY_ID_SQL,clientColumns);
        COLUMN_NAMES.put(FIND_CLIENTS_BY_NAME_SQL,clientColumns);
    }


    public List<Client> findClientsByName(String name) {
        List<Client> clients = new ArrayList<>();
        try {
            String sqlQuery = Files.readString(Path.of(FIND_CLIENTS_BY_NAME_SQL));
            PreparedStatement ps = DATABASE.getPreparedStatement(sqlQuery);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            List<String> columns = (COLUMN_NAMES.get(FIND_CLIENTS_BY_NAME_SQL));

            while (rs.next()) {
                clients.add(new Client(rs.getInt(columns.get(0)),
                        rs.getString(columns.get(1))));
            }
            if (clients.size() != 0){
                return clients;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public Client findClientByID(int id) {
        try {
            String sqlQuery = Files.readString(Path.of(FIND_CLIENT_BY_ID_SQL));
            PreparedStatement ps = DATABASE.getPreparedStatement(sqlQuery);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List<String> columns = (COLUMN_NAMES.get(FIND_CLIENT_BY_ID_SQL));

            if (rs.next()) {
                return new Client(rs.getInt(columns.get(0)),
                        rs.getString(columns.get(1)));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public List<Worker> findWorkersByName(String name) {
        List<Worker> workers = new ArrayList<>();
        try {
            String sqlQuery = Files.readString(Path.of(FIND_WORKERS_BY_NAME_SQL));
            PreparedStatement ps = DATABASE.getPreparedStatement(sqlQuery);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            List<String> columns = (COLUMN_NAMES.get(FIND_WORKERS_BY_NAME_SQL));

            while (rs.next()) {
                 workers.add(new Worker(rs.getInt(columns.get(0)),
                        rs.getDate(columns.get(1)),
                        Worker.Level.valueOf(rs.getString(columns.get(2))),
                        rs.getInt(columns.get(3)),
                        rs.getString(columns.get(4))));
            }
            if (workers.size() != 0){
                return workers;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public Worker findWorkerByID(int id) {
        try {
            String sqlQuery = Files.readString(Path.of(FIND_WORKER_BY_ID_SQL));
            PreparedStatement ps = DATABASE.getPreparedStatement(sqlQuery);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List<String> columns = (COLUMN_NAMES.get(FIND_WORKER_BY_ID_SQL));

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

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> maxProjectCountClients = new ArrayList<>();

        try {
            String sqlQuery = Files.readString(Path.of(FIND_MAX_PROJECTS_CLIENT_SQL));
            ResultSet rs = DATABASE.getPreparedStatement(sqlQuery).executeQuery();
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
            ResultSet rs = DATABASE.getPreparedStatement(sqlQuery).executeQuery();
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
            ResultSet rs = DATABASE.getPreparedStatement(sqlQuery).executeQuery();
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
            ResultSet rs = DATABASE.getPreparedStatement(sqlQuery).executeQuery();
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

            ResultSet rs = DATABASE.getPreparedStatement(sqlScript[2]).executeQuery();
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

