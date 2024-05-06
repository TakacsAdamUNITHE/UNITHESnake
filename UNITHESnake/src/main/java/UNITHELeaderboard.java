/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class UNITHELeaderboard extends JFrame {
    public static UNITHELeaderboard currentInstance = null;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/snakegame";
    private static final String USER = "root";
    private static final String PASS = "";

    private JTextArea leaderboardArea;
    private Timer refreshTimer;

    public UNITHELeaderboard() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLayout(new FlowLayout());

        leaderboardArea = new JTextArea(20, 30);
        leaderboardArea.setEditable(false);  // Letiltjuk a szerkesztést
        add(new JScrollPane(leaderboardArea));

        refreshTimer = new Timer(5000, e -> refreshLeaderboard());
        refreshTimer.start();
    }

    private void refreshLeaderboard() {
        leaderboardArea.setText("");

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Users ORDER BY Score DESC")) {
            while (rs.next()) {
                String username = rs.getString("Username");
                int score = rs.getInt("Score");
                leaderboardArea.append(username + ": " + score + " pont\n");
            }
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

   public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            if (UNITHELeaderboard.currentInstance == null) {
                UNITHELeaderboard leaderboard = new UNITHELeaderboard();
                leaderboard.setVisible(true);
                UNITHELeaderboard.currentInstance = leaderboard;
            }
        });
    }
}

