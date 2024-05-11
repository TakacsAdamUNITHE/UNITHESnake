/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Göblyös Bence, Takács Ádám
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;


public class UNITHESnake extends JPanel implements KeyListener, ActionListener {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;//a palya meretei
    private static final int EGYSEG = 20;
    private final int [] x = new int[EGYSEG];
    private final int [] y = new int[EGYSEG];
    private boolean running = false; //a jatak helyzetenek a szamontartasa
    private boolean startKepernyo = true; //a kezdo kepernyo TODO: kesobbi game over kepernyot ugyan így
    private Timer timer; //lepeshez szukseges idozito (javax swing)
    private JButton startButton; //start gomb
    private JButton restartButton; //jatek ujra inditasa
    private JTextField nevMezo; //az adatbazishoz nev bekero mezo
    private char irany = 'R'; //mozgasi irany
    private int bogyoX;
    private int bogyoY; //bogyo kordinatak
    private int hossz = 3; // a kezdo hossz
    private int pontok = 0;
    
    public UNITHESnake(){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                startKepernyo = false;
                startButton.setVisible(false);
                startGame();
            }
        });
        add(startButton);
        restartButton = new JButton("Újra");
        restartButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e) {
        for (int i=0;i<=0;i++){
            x[i]=0;
            y[i]=0;
        }    
        hossz=3;
        pontok=0;
        irany = 'R';
        nevMezo.setText("");
        running = true;
        restartButton.setVisible(false);
        nevMezo.setVisible(false);
        

        startGame();
    }
});

        nevMezo = new JTextField(10);
        nevMezo.setFont(new Font("Arial", Font.PLAIN, 20));
        nevMezo.setHorizontalAlignment(JTextField.CENTER);
        nevMezo.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String playerName = nevMezo.getText();
        System.out.println("Player name: " + playerName);
        nevMezo.setVisible(false);

        // Adatbázis kapcsolat létrehozása
       try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/snakegame", "root", "");
            Statement stmt = conn.createStatement();

            // SQL utasítás előkészítése
           String sql = "INSERT INTO users (Username, Score) VALUES ('" + playerName + "', " + pontok + ") ON DUPLICATE KEY UPDATE Score = VALUES(Score);";


            // SQL utasítás végrehajtásaStatement stmt = conn.createStatement();
            stmt.execute(sql);

            // Kapcsolat bezárása
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
});
add(nevMezo);
nevMezo.setVisible(false);

    }

    //start-hoz szukseges dolgok elkeszitese
    public void startGame(){
        newBogyo();
        running = true;
        timer = new Timer(100,this);
        timer.start();
        pontok=0;
        
    }
    
    //start screen
    public void startScreen(Graphics g) {
        //a jatek nevenek kirajzolasa
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD,30));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("UNITHE Snake",(WIDTH - metrics.stringWidth("UNITHE Snake")) / 2, HEIGHT / 2 - 50);
        //start gomb
        startButton.setBounds((WIDTH - 100) / 2, HEIGHT / 2, 100, 40);
    }
    
    //maga a jatek
    public void gameScreen (Graphics g){
        g.setColor(Color.RED);
        //bogyo megrajzolasa
        g.fillOval(bogyoX,bogyoY,EGYSEG,EGYSEG);
        //kigyo rajzolasa
        for (int i = 0;i<hossz;i++){
            if (i == 0) {
                g.setColor(Color.green);
            } else {
                g.setColor(new Color(45, 180, 0));
            }
            g.fillRect(x[i], y[i], EGYSEG, EGYSEG);
        }
        //pontok kirajzolasa
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: " + pontok, (WIDTH - metrics.stringWidth("Score: " + pontok)) / 2, g.getFont().getSize());
        
        
    }
    
    //game over kepernyo
    public void gameOver(Graphics g){
    //game over szoveg kirajzolasa
    g.setColor(Color.red);
    g.setFont(new Font("Arial", Font.BOLD, 40));
    FontMetrics metrics1 = getFontMetrics(g.getFont());
    g.drawString("Vége a játéknak!", (WIDTH - metrics1.stringWidth("Vége a játéknak!")) / 2, (HEIGHT-200) / 2);
    //vegso pontszam kirajzolasa
    g.setColor(Color.red);
    g.setFont(new Font("Arial", Font.BOLD, 25));
    FontMetrics metrics = getFontMetrics(g.getFont());
    g.drawString("Score: " + pontok, (WIDTH - metrics.stringWidth("Score: " + pontok)) / 2, g.getFont().getSize());
    add(restartButton);
    restartButton.setBounds((WIDTH - 100) / 2, (HEIGHT+50) / 2, 100, 40);
    restartButton.setVisible(true);
    g.setFont(new Font("Arial", Font.BOLD, 20));
    FontMetrics metrics3 = getFontMetrics(g.getFont());
    g.drawString("Írd be a nevedet:", (WIDTH - metrics3.stringWidth("Írd be a nevedet:")) / 2, (HEIGHT-200) / 2 + 50);
    nevMezo.setBounds((WIDTH - 200) / 2, (HEIGHT-200) / 2 + 80, 200, 30);
    
    //try {
       //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/snakegame", "root", "");
       //Statement stmt = conn.createStatement();

         //SQL utasítás előkészítése
        //String sql = "UPDATE users SET Score = " + pontok + " WHERE Username = '" + nevMezo.getText() + "';";

         //SQL utasítás végrehajtása
        //stmt.execute(sql);

       // Kapcsolat bezárása
        //stmt.close();
        //conn.close();
    //}catch (Exception ex) {
        //System.out.println(ex.getMessage());
    //}

    
    
   SwingUtilities.invokeLater(() -> {
    if (UNITHELeaderboard.currentInstance == null) {
        UNITHELeaderboard leaderboard = new UNITHELeaderboard();
        leaderboard.setVisible(true);
        UNITHELeaderboard.currentInstance = leaderboard;
    }
});

}
    
    //a kepernyo valtasi vezerlo
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (startKepernyo){
            startScreen(g);
        }
        else if (running){
            gameScreen(g);
        }
        else {
            gameOver(g);
        }
    }
    
    //---a jatek---
    
    //bogyo elhelyezese
    public void newBogyo() {
        bogyoX = (int) (Math.random() * (WIDTH / EGYSEG)) * EGYSEG;
        bogyoY = (int) (Math.random() * (HEIGHT / EGYSEG)) * EGYSEG;
    }
    
    //kigyo mozgasa
    public void mozgas(){
        for (int i = hossz; i>0; i--){
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        switch (irany) {
            case 'U' -> y[0]=y[0] - EGYSEG;
            case 'D' -> y[0]=y[0] + EGYSEG;
            case 'L' -> x[0]=x[0] - EGYSEG;
            case 'R' -> x[0]=x[0] + EGYSEG;
        }
    }
    //bogyo elenzorzese
    public void bogyoCheck() {
        if (x[0]==bogyoX && y[0]==bogyoY){
            hossz++;
            pontok++;
            newBogyo();
        }
    }
    //game over helyzetek figyelese
    public void colisionCheck(){
        //testbe utkozes
        for (int i=hossz;i>0;i--){
            if (x[0]==x[i]&&y[0]==y[i])
                running=false;  
        }
        //falakba valo utkozes
        if (x[0]<0 || x[0]>=WIDTH){
            running = false;
        }
        if (y[0]<0 || y[0]>=HEIGHT){
            running = false;
        }
        if (!running){
            timer.stop();
            nevMezo.setVisible(true);
        }
    }
    
    //--- override-ok ---
    
    @Override
    public void actionPerformed(ActionEvent e){
        if (running){
            mozgas();
            colisionCheck();
            bogyoCheck();
        }
        repaint();
    }
    @Override
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()){
            //bal
            case KeyEvent.VK_LEFT ->{
                if (irany != 'R'){
                    irany = 'L';
                } 
            }
            //job
            case KeyEvent.VK_RIGHT ->{
                if (irany != 'L'){
                    irany = 'R';
                } 
            }
            //fel
            case KeyEvent.VK_UP ->{
                if (irany != 'D'){
                    irany = 'U';
                } 
            }
            //le
            case KeyEvent.VK_DOWN ->{
                if (irany != 'U'){
                    irany = 'D';
                } 
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e){
    }
    @Override
    public void keyTyped(KeyEvent e){
    }
    
   public static void main(String[] args) {
       try {
            // Létrehozzuk a kapcsolatot az adatbázissal
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/snakegame", "root", "");

            // Létrehozzuk az SQL utasítást
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users " +
                         "(Username VARCHAR(255) NOT NULL, " +
                         " Score INT NOT NULL DEFAULT 0);";

            // Végrehajtjuk az SQL utasítást
            stmt.execute(sql);

            // Bezárjuk a kapcsolatot
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JFrame frame = new JFrame("Snake Game");
        UNITHESnake game = new UNITHESnake();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
