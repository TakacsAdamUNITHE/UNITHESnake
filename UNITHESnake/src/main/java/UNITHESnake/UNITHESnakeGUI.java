/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Takács Ádám, Göblyös Bence
 */

package UNITHESnake;


import javax.swing.*;
import java.awt.*;

//public class UNITHESnakeGUI extends JFrame {
//    {
//     add(new Board()) ;
//     setResizable(false);
//     pack();
//     setTitle(UNITHESnake);
//     setLocationRelativeTo(null);
//     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//}
    
//public static void main(String[] args) {
//        EventQueue.invokeLater(() -> {
//            JFrame ex = new UNITHESnake();
//            ex.setVisible(true);
//        });
//    }
//}

public class UNITHESnakeGUI extends JFrame{
    UNITHESnake game;
    UNITHESnakeGUI(int x, int y){
        super("UNI THE Snake");
        GridLayout gridLayout = new GridLayout (x,y);
        JPanel panel = new JPanel (gridLayout);
        
        for (int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                JButton button = new JButton(""+i+","+j);
                if (snakecord[0][0]== i & snakecord[0][1]== y) {
                    button.setBackground(Color.GREEN);
                } else {
                    button.setBackground(Color.WHITE);
                }
                panel.add(button);
                
            }
        }
        this.add(panel);
        this.setSize(300,300);
        this.setVisible(true);
    }
    UNITHESnakeGUI(UNITHESnake starter){
        this(starter.getx(),starter.gety());
    }
}