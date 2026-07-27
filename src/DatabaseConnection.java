import java.sql.*;
import java.util.*;

public class DatabaseConnection {

    // Data Base Credentials
    private static final String URL = "jdbc:mysql://localhost:3306/gym_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static Connection connection = null;
    private static boolean databaseAvailable = false;

    // Establish Database Connection once when the program starts.
    public static boolean connect() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            databaseAvailable = true;
        } catch (SQLException e) {
            databaseAvailable = false;
            System.out.println("Cannot connect to the database.");
        }
        return databaseAvailable;
    }

    // Load all data from DB
    public static List<Member> loadAllMembers() {
        List<Member> members = new ArrayList<>();
        if (!databaseAvailable) return members;

        String sql = "SELECT m.member_id, m.name, m.email, m.phone, m.plan_id, p.plan_name "
                + "FROM members m JOIN plans p ON m.plan_id = p.plan_id";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Member m = new Member(
                        rs.getInt("member_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getInt("plan_id"),
                        rs.getString("plan_name")
                );
                members.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Could not load members from the database.");
        }
        return members;
    }

    // save data in DB
    public static void saveAllMembers(Member[] members) {
        if (!databaseAvailable) {
            System.out.println("Database is not connected - nothing was saved to MySQL.");
            return;
        }

        try (Statement st = connection.createStatement()) {
            st.execute("DELETE FROM members");
        } catch (SQLException e) {
            System.out.println("Save failed: could not clear old data.");
            return;
        }

        String sql = "INSERT INTO members (member_id, name, email, phone, plan_id) "
                + "VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (Member m : members) {
                ps.setInt(1, m.getMemberID());
                ps.setString(2, m.getName());
                ps.setString(3, m.getEmail());
                ps.setString(4, m.getPhone());
                ps.setInt(5, m.getPlanID());
                ps.addBatch();
            }
            ps.executeBatch();
            System.out.println("Saved " + members.length + " member(s) to the database.");
        } catch (SQLException e) {
            System.out.println("Save failed.");
            System.out.println("Reason: " + e.getMessage());
        }
    }

    // check DB availability and make connection
    public static boolean isDatabaseAvailable() {
        return databaseAvailable;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("[Database] Connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("[Database] Error closing connection: " + e.getMessage());
        }
    }
}
