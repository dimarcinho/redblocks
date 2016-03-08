
package tetrisfirst;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Score {
    
    int points = 0;
    int linhas = 0;
    
    public Score(){
        
    }
    
    public void draw(Graphics g){
        g.setColor(Color.yellow);
        g.setFont(new Font("Arial", Font.BOLD, 20));        
        g.drawString(""+points, 435, 380);
        g.drawString(""+linhas, 435, 410);
    }
    
    public void addPoints(int lines){
        
        switch(lines){
            default:
                System.out.print("Erro no switch!");
            case 0:
                break;
            case 1:
                points += 1000;
                break;
            case 2:
                points += 3000;
                break;
            case 3:
                points += 6000;
                break;
            case 4:
                points += 10000;
                break;
        }
        linhas += lines;
    }
    
    public void saveHighScore(){
        //rotina para salvar os recordes de pontuação
    }
    
}
