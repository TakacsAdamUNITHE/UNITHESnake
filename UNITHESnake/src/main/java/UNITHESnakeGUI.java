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
        super("Snake Game");
        int x = starter.getx();
        int y = starter.gety();
        GridLayout gridLayout = new GridLayout(x,y);
        JPanel panel = new JPanel(gridLayout);
    }
    this.add(panel)
   this.SetSize(300,400)
   this.setVisible(true)
}
