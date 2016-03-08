
package tetrisfirst;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.LinkedList;


public class Controller implements Runnable {
    
    //LinkedList<Rectangle> e = new LinkedList<Rectangle>();
    LinkedList<Bloco> e = new LinkedList<Bloco>();

    Bloco b;

    public Controller(){        
        this.addBloco(new Bloco());
    }
 
    public void draw(Graphics g){
        for(int i = 0; i < e.size(); i++){
            b = e.get(i);
            b.draw(g);
        }
    }
    
    public void updateX(){
        for(int i = 0; i < e.size(); i++){
            b = e.get(i);
            b.moveX();            
        }
    }
    
    public void updateY(){
        for(int i = 0; i < e.size(); i++){
            b = e.get(i);
            b.moveY();            
        }
    }
    
    public void addBloco(Bloco blk){
        e.add(blk);
    }
    
    public void delBloco(Bloco blk){
        e.remove(blk);
    }
    
    public void keyPressed(KeyEvent e){
        
        int keyCode = e.getKeyCode();
        
        if (keyCode == e.VK_LEFT){
            b.keyPressed(e);
        }
        if (keyCode == e.VK_RIGHT){
            b.keyPressed(e);
        }
        if (keyCode == e.VK_UP){
            b.keyPressed(e);            
        }
        if (keyCode == e.VK_DOWN){
            b.keyPressed(e);
        }
    }

    public void keyReleased(KeyEvent e){
        
        int keyCode = e.getKeyCode();
        
        if (keyCode == e.VK_LEFT){
            b.keyReleased(e);
        }
        if (keyCode == e.VK_RIGHT){
            b.keyReleased(e);
        }
        if (keyCode == e.VK_UP){
            b.keyReleased(e);
        }
        if (keyCode == e.VK_DOWN){
            b.keyReleased(e);
        }
    }
    
    public void run(){
        try{            
            while(true){
                /*
                if(!b.moving){
                    addBloco(new Bloco());
                    b.moving = true;
                }
                 * 
                 */
                updateX();
                updateY();
                if(b.fastdown){
                    Thread.sleep(5);
                } else {
                    Thread.sleep(1000);
                }
            }
        }         
        catch(Exception e){System.out.println(e);}  
        }
    }
