/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Göblyös Bence, Takács Ádám
 */
public class UNITHESnake {
    //a palya adatainak szamontartasahoz szukseges dolgok
    //ertek magyarazat:
    //0: a mezo ures
    //1: a kigyo teste
    //2: bogyo
    int [] [] snakemap;
    int x,y;
    
    //a tomb alap helyzetbe allitasa
    UNITHESnake (int x, int y){
        this.x=x;
        this.y=y;
        snakemap = new int [x] [y];
        for (int i=0;i<x;i++){
            for (int j = 0;j<y;j++){
                snakemap[i][j]=0;
            }
                
        }
    }
    
    public int getx(){
        return this.x;
    }
    
    public int gety(){
        return this.y;
    }
    
}
