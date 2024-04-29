/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Göblyös Bence, Takács Ádám
 */
package UNITHESnake;
//import java.util.Random;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class UNITHESnake {
    //a korabbi valtoztatasok miat szerintem nem igazan lesz szukseg maga a palya szamon tartasara
    //ezert csak a pont x es y kordinatajat fogom szamon tartani
    //a pont szerzets meg ugy lehetlesz megoldani hogy elenorizuk hogy a pont valtozoban levo elemek
    //benne vannak-e a snakecord-ban
    int [] pont;
    int [][] snakecord;
    int x,y,area,v;

    //a tomb alap helyzetbe allitasa
    UNITHESnake (int x, int y){
        this.x=x;
        this.y=y;
        //snakemap = new boolean [x] [y];
        //for (int i=0;i<x;i++){
        //    for (int j = 0;j<y;j++){
        //        snakemap[i][j]=false;
        //    }
        //        
        //}

        pont = new int [2];
        pont[0]=4; //x kordinata
        pont[1]=3;   //y kordinata
        
        
        //a kigyo kordonatainak tarolasa
        //area: a palya teruletenek kiszamitasa
        area=x*y;
        snakecord = new int [area][1];
        snakecord[0][0]=3;
        snakecord[0][1]=3;
        for (int i=1;i<area;i++){
            for (int j=1;j<v;j++){
               snakecord[i][j] = 0;
            }
        }
        
    }
    
    public int getx(){
        return this.x;
    }
    
    public int gety(){
        return this.y;
    }
   
    //veletlen szam az uj bogyo megjelenesehez
    //Random rand = new Random();
    //int randomX = rand.nextInt(x);
    //int randomY = rand.nextInt(y);
    
 // Adatbázis lekérdezés elkészítése, a getConnection segítségével elérjük az adatbázist, a megadott adatokkal.    
public class Adatbazislekerdezes {
    private void queryData() {
        try {
            Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/unithesnake", "root", "");

            Statement myStmt = myConnection.createStatement();

            ResultSet myRs = myStmt.executeQuery("select * from jatekosok");

            while (myRs.next()) {
                System.out.println(myRs.getString("Nev") + ", " + myRs.getString("Helyezes") + ", " + myRs.getString("Pontszam"));
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}

    public static void main(String[] args){
        
        UNITHESnake game = new UNITHESnake(5,5);
        UNITHESnakeGUI gamegui = new UNITHESnakeGUI(game);
        gamegui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //boolean gameOver = false;
        //while (gameOver == false){
        //    System.out.println("hello world!");
        //    gameOver=true;
        //}
    }    
}
