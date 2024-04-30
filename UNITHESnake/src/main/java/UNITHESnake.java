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
    }

    //start-hoz szukseges dolgok elkeszitese
    public void startGame(){
        newBogyo();
        running = true;
        timer = new Timer(100,this);
        timer.start();
        
    }
    
    //start screen
    public void startScreen(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD,30));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("UNI THE Snake Game",(WIDTH - metrics.stringWidth("Snake Game")) / 2, HEIGHT / 2 - 50);
        startButton.setBounds((WIDTH - 100) / 2, HEIGHT / 2, 100, 40);
    }
    
    //maga a jatek
    public void gameScreen (Graphics g){
        g.setColor(Color.RED);
        g.fillOval(bogyoX,bogyoY,EGYSEG,EGYSEG);
        
        for (int i = 0;i<hossz;i++){
            if (i == 0) {
                g.setColor(Color.green);
            } else {
                g.setColor(new Color(45, 180, 0));
            }
            g.fillRect(x[i], y[i], EGYSEG, EGYSEG);
        }
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: " + pontok, (WIDTH - metrics.stringWidth("Score: " + pontok)) / 2, g.getFont().getSize());
    }
    
    
    
    //a kepernyo valtasi vezerlo
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (startKepernyo){
            drawStart(g);
        }
    }
    
    public void drawStart(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Snake Game", (WIDTH - metrics.stringWidth("Snake Game")) / 2, HEIGHT / 2 - 50);
        startButton.setBounds((WIDTH - 100) / 2, HEIGHT / 2, 100, 40);
    }
    
    
    //---a jatek---
    
    //bogyo elhelyezese
    public void newBogyo() {
        bogyoX = (int) (Math.random() * (WIDTH / EGYSEG)) * EGYSEG;
        bogyoY = (int) (Math.random() * (HEIGHT / EGYSEG)) * EGYSEG;
    }
    
    
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
    
    
    //--- override-ok ---
    
    @Override
    public void actionPerformed(ActionEvent e){
        if (running){
            mozgas();
            
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
        JFrame frame = new JFrame("Snake Game");
        UNITHESnake game = new UNITHESnake();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
