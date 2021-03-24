import java.sql.*;

public class Test{
    static class Conn {
        static final private String DRIVER = "org.sqlite.JDBC";
        static final private String URL = "jdbc:sqlite:C:\\java\\Test\\1\\src\\DB\\Logins.db";
        static Connection open() {
            Connection conn = null;
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                conn = DriverManager.getConnection(URL);
                conn.setAutoCommit(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return conn;
        }
        static void close(Connection conn) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Connection conn = null;
        conn = Conn.open();

        int rowCount;
        Statement sqlQuery;
        PreparedStatement sqlPrepQuery;
        ResultSet dataQuery;

        try {
            sqlQuery = conn.createStatement();
            sqlPrepQuery = conn.prepareStatement("INSERT INTO TAB_LOGIN(LOGIN, NICK, PASSWORD) VALUES('?', '?', '?');");

            rowCount = sqlQuery.executeUpdate("DELETE FROM TAB_LOGIN;");
            System.out.println(rowCount);

            sqlQuery.executeUpdate("INSERT INTO TAB_LOGIN(LOGIN, NICK, PASSWORD) VALUES('LOGIN1', 'NICK1', 'PASS1');");

            sqlQuery.execute("INSERT INTO TAB_LOGIN(LOGIN, NICK, PASSWORD) VALUES('LOGIN2', 'NICK2', 'PASS2');");

            sqlPrepQuery.setString(1, "LOGIN3");
            sqlPrepQuery.setString(2, "NICK3");
            sqlPrepQuery.setString(3, "PASS3");
            sqlPrepQuery.execute();
            conn.commit();
            dataQuery = sqlQuery.executeQuery("SELECT * FROM TAB_LOGIN;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Conn.close(conn);
    }
}
