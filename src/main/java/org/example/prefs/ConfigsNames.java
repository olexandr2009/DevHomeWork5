package org.example.prefs;

public enum ConfigsNames{
    TEST_DB_URL("testDbURL"),
    TEST_DB_LOCATION_URL( "testDbLocationURL"),
    DB_URL("dbURL"),
    DB_LOCATION_URL("dbLocationUrl"),
    USER_NAME_CONFIG_NAME("user"),
    PASSWORD_CONFIG_NAME("password"),
    INIT_DB_SQL("initDbSql"),
    POPULATE_DB_SQL("populateDbSql");
    private final String name;
    ConfigsNames(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
