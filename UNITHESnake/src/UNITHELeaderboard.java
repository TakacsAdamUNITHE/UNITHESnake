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
    int size = 300; // A panel mérete
    setSize(size, size); // Négyzet alakú panel létrehozása
    setLayout(new BorderLayout());

    leaderboardArea = new JTextArea(20, 30);
    leaderboardArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
    leaderboardArea.setBackground(Color.LIGHT_GRAY);
    leaderboardArea.setEditable(false);  // Letiltjuk a szerkesztést
    add(new JScrollPane(leaderboardArea), BorderLayout.CENTER);

    refreshTimer = new Timer(5000, e -> refreshLeaderboard());
    refreshTimer.start();
}

    private void refreshLeaderboard() {
    leaderboardArea.setText("");

    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT Username, MAX(Score) as MaxScore FROM users GROUP BY Username ORDER BY MaxScore DESC")) {
        while (rs.next()) {
            String username = rs.getString("Username");
            int score = rs.getInt("MaxScore");
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

