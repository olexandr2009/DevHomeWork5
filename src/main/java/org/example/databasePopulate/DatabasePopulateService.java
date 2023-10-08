package org.example.databasePopulate;

import org.example.database.Database;
import org.example.prefs.ConfigsNames;
import org.example.prefs.Configurations;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class DatabasePopulateService {
    public static void populate_db(Database database) {
        try {
            String[] sqlScript = Files.readString(
                    Path.of(Configurations.Configs.getConfigAsString(ConfigsNames.POPULATE_DB_SQL)),
                    StandardCharsets.UTF_8
            ).split(";");
            for (String sql : sqlScript) {
                database.executeUpdate(sql + ";");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        populate_db(Database.getDefaultDB());
    }
}
