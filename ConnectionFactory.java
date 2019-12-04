package br.isa.data.base;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private String database = "auth_users";
    private String user = "root";
    private String password = "root123";
    private static ConnectionFactory instance;
    private ConnectionFactory() {
    }
    public static ConnectionFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }
    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/" + this.database
                            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
                            + "&useLegacyDatetimeCode=false&serverTimezone=UTC"
                            + "&allowMultiQueries=true", this.user, this.password);
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}