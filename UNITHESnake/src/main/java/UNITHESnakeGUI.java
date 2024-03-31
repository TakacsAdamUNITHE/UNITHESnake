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

public class UNITHESnakeGUI extends JFrame {
    {
     add(new Board()) ;
     setResizable(false);
     pack();
     setTitle(UNITHESnake);
     setLocationRelativeTo(null);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
    
public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new UNITHESnake();
            ex.setVisible(true);
        });
    }
}