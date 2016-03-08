
package tetrisfirst;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Matriz {
    
    int x = 10, y = 25;
    int contLines = 0;
    
    public int xm, ym; //coordendas reduzidas; xm->10 e ym->25
    
    boolean gameOver = false;       
    
    Rectangle rtemp = new Rectangle(0,0,0,0);
    
    private boolean data[][] = new boolean[x][y];
        
    public Matriz(){           
        this.clearAll();
        /*
        for(int i=0; i < 10; i++){            
            this.setDataTrue(i, 0);
        }
         * 
         */
    }
    
    public void checkStatus(){
        if(this.data[4][1] || this.data[5][1]){
            this.gameOver = true;
            //System.out.println("Game Over");
        }
    }
    
    public void draw(Graphics g){
        
        int mx, my; //coordenadas para os retângulos
        
        for(int i=0; i < 10; i++){
            for(int j=0; j < 25; j++){
                if(data[i][j] == true){
                    mx = 30*i + 25;
                    my = 30*j + 50;
                    rtemp.setBounds(mx, my, 30, 30);
                    g.setColor(Color.red);
                    g.fillRect(rtemp.x, rtemp.y, rtemp.width, rtemp.height);
                    g.setColor(Color.yellow);
                    g.drawRect(rtemp.x, rtemp.y, rtemp.width, rtemp.height);                    
                }
            }
        }
        
    }
    
    public void clearAll(){
        for(int i=0; i < 10; i++){
            for(int j=0; j < 25; j++){
                if(data[i][j]){
                    this.setDataFalse(i, j);                    
                }
            }
        }
    }
    
    public void checkFullLine(){
        contLines = 0;
        int contCol = 0;
        for(int j = 24; j > -1; j--){
            for(int i = 0; i < 10; i++){
                if(this.data[i][j]){
                    contCol = contCol + 1;                    
                }
            }
            if(contCol == 10){
                for(int i = 0; i < 10; i++){                    
                    this.setDataFalse(i, j);                    
                }
                contLines++;
                this.WallDown(j);
                j++;
            }            
            contCol = 0;
        }
            
    }
    
    public void setTrue(int x, int y){
        xm =  (x-25)/30;
        ym = (y-50)/30;
        this.data[xm][ym] = true;        
    }
    
    public void setFalse(int x, int y){
        xm = (x-25)/30;
        ym = (y-50)/30;
        this.data[xm][ym] = false;
    }
    
    public int xm(int x){
        xm = (x-25)/30;
        return xm;
    }
    public int ym(int y){
        ym = (y-50)/30;
        return ym;
    }
    
    public void setDataTrue(int x, int y){        
        this.data[x][y] = true;        
    }
    
    public void setDataFalse(int x, int y){        
        this.data[x][y] = false;
    }
    public boolean getDataXY(int x, int y){
        boolean w;
        w = false;
        int i = (x-25)/30;
        int j = (y-50)/30;
        if(i>=0 && i <= 9 && j>0 && j<=24)
            w = this.data[i][j];
        if(i < 0 || i>9)
            w = false;       
        if(j < 0 || i>24)
            w = false;        
        return w;        
    }
    
    public void getDataIJ(int x, int y){

    }
    
    public void WallDown(int k){
        
        for(int j = k; j > 0; j--){            
            for(int i = 0; i < 10; i++){
                if(j == 0)
                  this.data[i][j] = false;
                else
                  this.data[i][j] = this.data[i][j-1];                
                }
        }
    }

}
