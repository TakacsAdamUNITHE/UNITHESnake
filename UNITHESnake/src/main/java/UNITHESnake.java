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
    //false: a mezo ures
    //true: bogyo
    //a snakecord tomb a kigyo helyzetenek a szamontartasara szolgal
    //a mozgas szimulalasa erdekeben a tomb elso elemet elvesszuk es a vegera egy ujat rakunkbe
    //boolean [] [] snakemap;
    
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
        v=1;
        snakecord = new int [area][v];
        for (int i=0;i<area;i++){
            for (int j=0;j<v;j++){
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
    
}
