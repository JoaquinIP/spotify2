package es.ulpgc.spotify.downloader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    public static void main(String[] args) {
            String dbPath = "C:\\Users\\joaqu\\IdeaProjects\\spotify2\\test.db";
        try(Connection connection = connect(dbPath)) {
            Statement statement = connection.createStatement();
            createTable(statement);
            insert(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void createTable(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS artists (" +
                "name TEXT NOT NULL,\n" +
                ");");
    }

    private static boolean insert(Statement statement) throws SQLException {
        return statement.execute("INSERT INTO artists (name)\n" +
                "VALUES('hibike'), ('orbea');");
    }


    public static Connection connect(String dbPath) {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:" + dbPath;
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
