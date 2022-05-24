package GamePakage;

import java.sql.*;

public class SQLiteDB {
    private Connection connection=null;
    private Statement stmt=null;

    public void UpdateScore(int score){
        try {
            stmt = connection.createStatement();

            String sql = "INSERT INTO HighScore (Score) " +
                    "VALUES ("+score+");";
            stmt.executeUpdate(sql);
            stmt.close();
            connection.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void UpdateSettings(boolean music)
    {
        try {
            stmt = connection.createStatement();
            String sql = "UPDATE Settings SET Music = "+music+";";
            stmt.executeUpdate(sql);
            stmt.close();
            connection.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void Clear()
    {
        try {
            stmt = connection.createStatement();
            String sql = "DELETE FROM HighScore ;";
            stmt.executeUpdate(sql);
            connection.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public String Show() {
        StringBuilder result = new StringBuilder();
        try {
            stmt = connection.createStatement();
            String sql = "SELECT * FROM HighScore ORDER BY Score DESC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                result.append(rs.getInt("Score")).append("\n");
                //System.out.println(rs.getInt("Score"));
            }
            stmt.close();
            connection.commit();
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return result.toString();
    }
    public void Connect()
    {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:rsc/DB.db");
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void Close()
    {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}