/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisfirst;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class Botao {
    
    Rectangle btnMenu = new Rectangle();
    String btnTexto;    
    boolean focus, pressed;
    
    public Botao(int x0, int y0, String a){
        
        btnMenu.x = x0;
        btnMenu.y = y0;
        btnMenu.width = 100;
        btnMenu.height = 30;
        btnTexto = a;        
    }
    
    public void draw(Graphics g){
        
        g.setColor(Color.gray);
        g.fillRect(btnMenu.x, btnMenu.y, btnMenu.width, btnMenu.height);
        g.setColor(Color.black);
        g.drawRect(btnMenu.x+5, btnMenu.y+5, btnMenu.width-10, btnMenu.height-10);
        
        int x_polygon_bottom[] = {btnMenu.x, btnMenu.x+5, btnMenu.x+btnMenu.width-5, btnMenu.x+btnMenu.width};
        int y_polygon_bottom[] = {btnMenu.y+btnMenu.height, btnMenu.y+btnMenu.height-5, btnMenu.y+btnMenu.height-5, btnMenu.y+btnMenu.height};
        g.setColor(Color.darkGray);
        g.fillPolygon(x_polygon_bottom, y_polygon_bottom, 4);
        g.setColor(Color.black);
        g.drawPolygon(x_polygon_bottom, y_polygon_bottom, 4);
        
        int x2_polygon_bottom[] = {btnMenu.x+btnMenu.width, btnMenu.x+btnMenu.width-5, btnMenu.x+btnMenu.width-5, btnMenu.x+btnMenu.width};
        int y2_polygon_bottom[] = {btnMenu.y, btnMenu.y+5, btnMenu.y+btnMenu.height-5, btnMenu.y+btnMenu.height};
        g.setColor(Color.darkGray);
        g.fillPolygon(x2_polygon_bottom, y2_polygon_bottom, 4);
        g.setColor(Color.black);
        g.drawPolygon(x2_polygon_bottom, y2_polygon_bottom, 4);
        
        g.setColor(Color.black);
        g.drawLine(btnMenu.x, btnMenu.y, btnMenu.x+5, btnMenu.y+5);
        g.drawRect(btnMenu.x, btnMenu.y, btnMenu.width, btnMenu.height);
        g.drawRect(btnMenu.x+5, btnMenu.y+5, btnMenu.width-10, btnMenu.height-10);
        
        if(!focus)
            g.setColor(Color.black);
        else
            g.setColor(Color.white);       
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString(btnTexto, btnMenu.x + 12, btnMenu.y+20);        
    }
    
    public void pressed(boolean d){
        this.pressed = d;
    }
    
    public void focus(boolean d){
        this.focus = d;
    }
  
    public void mouseMoved(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();
        
        if(mx > btnMenu.x && mx < btnMenu.x + btnMenu.width && 
            my > btnMenu.y && my < btnMenu.y + btnMenu.height){
            this.focus(true);
        }else{
            this.focus(false);
        }            
    }
    
    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();
        
        if(mx > btnMenu.x && mx < btnMenu.x + btnMenu.width && 
            my > btnMenu.y && my < btnMenu.y + btnMenu.height){
            this.pressed(true);            
        }   
    }
    public void mouseReleased(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();
        
        if(mx > btnMenu.x && mx < btnMenu.x + btnMenu.width && 
            my > btnMenu.y && my < btnMenu.y + btnMenu.height){
            this.pressed(false);
        }   
    }
}
