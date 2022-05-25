package GamePakage;

import java.sql.*;
import java.util.ArrayList;

public class SQLiteDB {
    private Connection connection=null;
    private Statement stmt=null;

    public void UpdateScore(int score){
        try {
            stmt = connection.createStatement();

            String sql = "INSERT INTO HighScore (Score) " +
                    "VALUES ("+score+");";
            stmt.executeUpdate(sql);
            connection.commit();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void UpdateSettings(boolean music)
    {
        try {
            stmt = connection.createStatement();
            int i=music?1:0;
            String sql = "UPDATE Settings set Music = "+i+";";
            stmt.executeUpdate(sql);
            connection.commit();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public boolean GetSettings()
    {
        boolean music=false;
        int i=0;
        try {
            stmt = connection.createStatement();
            String sql = "SELECT Music FROM Settings;";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                i = rs.getInt("Music");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        if(i==1)
            music=true;
        return music;
    }
    public void Clear()
    {
        try {
            stmt = connection.createStatement();
            String sql = "DELETE FROM HighScore ;";
            stmt.executeUpdate(sql);
            connection.commit();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public ArrayList<String> Show() {
        ArrayList<String> ScoreBord = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            String sql = "SELECT * FROM HighScore ORDER BY Score DESC";
            ResultSet rs = stmt.executeQuery(sql);
            int i = 1;
            while (rs.next()) {
                ScoreBord.add((i++)+". "+rs.getString("Score"));
            }
            rs.close();
            stmt.close();
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return ScoreBord;
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