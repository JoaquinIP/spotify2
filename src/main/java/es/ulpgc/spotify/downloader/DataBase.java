package es.ulpgc.spotify.downloader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    public DataBase() {
    }
    public void setConnect(String url){
        String dbPath = "jdbc:sqlite:" + url;
        try(Connection connection = connect(dbPath)) {
            Statement statement = connection.createStatement();
            createTable(statement);
            //insert(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void createTable(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS artists (" +
                "popularity INTEGER PRIMARY KEY,\n" +
                "name TEXT NOT NULL,\n" +
                "id TEXT NOT NULL\n" +
                ");");
        statement.execute("CREATE TABLE IF NOT EXISTS albums (" +
                "total_tracks INTEGER PRIMARY KEY,\n" +
                "name TEXT NOT NULL,\n" +
                "release_date TEXT NOT NULL\n" +
                ");");
        statement.execute("CREATE TABLE IF NOT EXISTS tracks (" +
                "duration_ms INTEGER PRIMARY KEY,\n" +
                "name TEXT NOT NULL,\n" +
                "explicit BOOLEAN NOT NULL CHECK (explicit IN (0, 1))\n" +
                ");");
    }

    /*private static boolean insert(Statement statement) throws SQLException {
        return statement.execute("INSERT INTO artists (name)\n" +
                "VALUES('hibike'), ('orbea');");
    }*/


    public static Connection connect(String dbPath) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbPath);
            System.out.println("Connection to SQLite has been established.");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
