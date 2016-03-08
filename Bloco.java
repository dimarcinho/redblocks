
package tetrisfirst;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


public class Bloco extends JFrame implements Runnable {

    int k, k_preview;   //tipo do bloco
    int rot;            //posição rotacional
   
    int x, y, xdir, ydir;
    int level = 1;
    int timespeed = 500;
    int nextLevel = 5000;
    
    Matriz mb = new Matriz();
    Score pts = new Score();
    
    boolean moving = true;
    boolean paused = false;
    boolean rotation = true;
    
    boolean wait, fastdown;
    boolean leftEdge, rightEdge;
    
    Rectangle r1 = new Rectangle(0, 0, 0, 0);
    Rectangle r2 = new Rectangle(0, 0, 0, 0);
    Rectangle r3 = new Rectangle(0, 0, 0, 0);
    Rectangle r4 = new Rectangle(0, 0, 0, 0);
    Rectangle pr1 = new Rectangle(0, 0, 0, 0);
    Rectangle pr2 = new Rectangle(0, 0, 0, 0);
    Rectangle pr3 = new Rectangle(0, 0, 0, 0);
    Rectangle pr4 = new Rectangle(0, 0, 0, 0);
    
    public Bloco(){
        
        Random rnd = new Random();
        int rndTipo = rnd.nextInt(7)+ 1;
        k = rndTipo;        
        rot = 1;
        this.setTipo(rndTipo);
        
        k_preview = rnd.nextInt(7) + 1;
        this.setPreview(k_preview);
        
        setYdir(30);
        
        mb.clearAll();
        
    }
    
    public void pause(){
        if(!paused && !mb.gameOver && TetrisFirst.startGame){
            paused = true;
        } else {
            paused = false;
        }
    }
    
    public void rebuild(){
        k = k_preview;
        this.setTipo(k);
                
        Random rnd = new Random();
        int rndTipo = rnd.nextInt(7)+ 1;
        k_preview = rndTipo;
        this.setPreview(k_preview);
        rot = 1;
        
        moving = true;
        
        setYdir(30);
    }
    
    public void restart(){
        
        //zera a pontuação do jogo
        level = 1;
        pts.points = 0;
        pts.linhas = 0;
        timespeed = 500;
        nextLevel = 5000;
        
        //chama a rotina pra zerar a matriz do jogo
        mb.clearAll();
        
        //recomeça o jogo
        rebuild();
    }
    
    public void draw(Graphics g){

        //peça do jogo
        g.setColor(Color.red);
        g.fillRect(r1.x, r1.y, r1.width, r1.height);        
        g.fillRect(r2.x, r2.y, r2.width, r2.height);        
        g.fillRect(r3.x, r3.y, r3.width, r3.height);        
        g.fillRect(r4.x, r4.y, r4.width, r4.height);
        
        g.setColor(Color.yellow);
        g.drawRect(r1.x, r1.y, r1.width, r1.height);        
        g.drawRect(r2.x, r2.y, r2.width, r2.height);        
        g.drawRect(r3.x, r3.y, r3.width, r3.height);        
        g.drawRect(r4.x, r4.y, r4.width, r4.height);
        
        //peça preview
        g.setColor(Color.red);
        g.fillRect(pr1.x, pr1.y, pr1.width, pr1.height);        
        g.fillRect(pr2.x, pr2.y, pr2.width, pr2.height);        
        g.fillRect(pr3.x, pr3.y, pr3.width, pr3.height);        
        g.fillRect(pr4.x, pr4.y, pr4.width, pr4.height);
        
        g.setColor(Color.yellow);
        g.drawRect(pr1.x, pr1.y, pr1.width, pr1.height);        
        g.drawRect(pr2.x, pr2.y, pr2.width, pr2.height);        
        g.drawRect(pr3.x, pr3.y, pr3.width, pr3.height);        
        g.drawRect(pr4.x, pr4.y, pr4.width, pr4.height);       
        
    }
    
    public void setTipo(int k){   
        
        if(k == 1){
            r1.setBounds(145, 50, 30, 30);
            r2.setBounds(115, 50, 30, 30);
            r3.setBounds(175, 50, 30, 30);
            r4.setBounds(205, 50, 30, 30);
        }
        if(k == 2){
            r1.setBounds(145, 50, 30, 30);
            r2.setBounds(175, 50, 30, 30);
            r3.setBounds(145, 80, 30, 30);
            r4.setBounds(175, 80, 30, 30);
        }
        if(k == 3){
            r1.setBounds(145, 80, 30, 30);
            r2.setBounds(145, 110, 30, 30);
            r3.setBounds(115, 80, 30, 30);
            r4.setBounds(115, 50, 30, 30);
        }
        if(k == 4){
            r1.setBounds(145, 80, 30, 30);
            r2.setBounds(145, 50, 30, 30);
            r3.setBounds(115, 80, 30, 30);
            r4.setBounds(115, 110, 30, 30);
        }
        if(k == 5){
            r1.setBounds(145, 80, 30, 30);
            r2.setBounds(115, 80, 30, 30);
            r3.setBounds(145, 50, 30, 30);            
            r4.setBounds(175, 80, 30, 30);
        }
        if(k == 6){
            r1.setBounds(145, 50, 30, 30);
            r2.setBounds(115, 50, 30, 30);
            r3.setBounds(175, 50, 30, 30);
            r4.setBounds(175, 80, 30, 30);
        }
        if(k == 7){
            r1.setBounds(145, 50, 30, 30);
            r2.setBounds(175, 50, 30, 30);
            r3.setBounds(115, 50, 30, 30);
            r4.setBounds(115, 80, 30, 30);
        }
    }
    
    public int getTipo(){
        return k;
    }
    
    public void setPreview(int k){   
        
        if(k == 1){
            pr1.setBounds(410, 150, 30, 30);
            pr2.setBounds(380, 150, 30, 30);
            pr3.setBounds(440, 150, 30, 30);
            pr4.setBounds(470, 150, 30, 30);
        }
        if(k == 2){
            pr1.setBounds(410, 150, 30, 30);
            pr2.setBounds(440, 150, 30, 30);
            pr3.setBounds(410, 180, 30, 30);
            pr4.setBounds(440, 180, 30, 30);
        }
        if(k == 3){
            pr1.setBounds(410, 180, 30, 30);
            pr2.setBounds(410, 210, 30, 30);
            pr3.setBounds(380, 180, 30, 30);
            pr4.setBounds(380, 150, 30, 30);
        }
        if(k == 4){
            pr1.setBounds(410, 180, 30, 30);
            pr2.setBounds(410, 150, 30, 30);
            pr3.setBounds(380, 180, 30, 30);
            pr4.setBounds(380, 210, 30, 30);
        }
        if(k == 5){
            pr1.setBounds(410, 180, 30, 30);
            pr2.setBounds(380, 180, 30, 30);
            pr3.setBounds(410, 150, 30, 30);            
            pr4.setBounds(440, 180, 30, 30);
        }
        if(k == 6){
            pr1.setBounds(410, 150, 30, 30);
            pr2.setBounds(380, 150, 30, 30);
            pr3.setBounds(440, 150, 30, 30);
            pr4.setBounds(440, 180, 30, 30);
        }
        if(k == 7){
            pr1.setBounds(410, 150, 30, 30);
            pr2.setBounds(440, 150, 30, 30);
            pr3.setBounds(380, 150, 30, 30);
            pr4.setBounds(380, 180, 30, 30);
        }
    }
    
    public void rotate(){       
        
        if(rotation){
            if(rot == 4)
                rot = 1;
            else
                rot++;

            if(k == 1){
                if(rot == 2 || rot == 4){
                    int[] cx = {-30, +30, +60};
                    int[] cy = {0, 0 ,0};
                    if(checkRot(cx, cy)){
                        r2.setBounds(r1.x-30, r1.y, 30, 30);
                        r3.setBounds(r1.x+30, r1.y, 30, 30);
                        r4.setBounds(r1.x+60, r1.y, 30, 30);                        
                    }
                } else {
                    int[] cx = {0, 0, 0};
                    int[] cy = {-30, -60, -90};
                    if(checkRot(cx, cy)){
                        r2.setBounds(r1.x, r1.y-30, 30, 30);
                        r3.setBounds(r1.x, r1.y-60, 30, 30);
                        r4.setBounds(r1.x, r1.y-90, 30, 30);
                    }                    
                }

            }
            if(k == 2){
                //mesma posição em todos os sentidos
            }
            if(k == 3){
                if(rot == 2 || rot == 4){
                    int[] cx = {-30, 0, +30};
                    int[] cy = {0, -30, -30};
                    if(checkRot(cx, cy)){
                        r2.setBounds(r1.x-30, r1.y, 30, 30);
                        r3.setBounds(r1.x, r1.y-30, 30, 30);                
                        r4.setBounds(r1.x+30, r1.y-30, 30, 30);                        
                    }
                } else {
                   int[] cx = {0, -30, -30};
                   int[] cy = {+30, 0, -30};
                   if(checkRot(cx, cy)){
                        r2.setBounds(r1.x, r1.y+30, 30, 30);
                        r3.setBounds(r1.x-30, r1.y, 30, 30);                
                        r4.setBounds(r1.x-30, r1.y-30, 30, 30);                        
                    }
                      
                }
            }
            if(k == 4){
                if(rot == 2 || rot == 4){
                    int[] cx = {30, 0 , -30};
                    int[] cy = {0, -30 , -30};
                    if(checkRot(cx, cy)){
                        r2.setBounds(r1.x+30, r1.y, 30, 30);
                        r3.setBounds(r1.x, r1.y-30, 30, 30);                
                        r4.setBounds(r1.x-30, r1.y-30, 30, 30);                        
                    }
                       
                } else {
                    int[] cx = {0, -30 , -30};
                    int[] cy = {-30, 0 , +30};
                    if(checkRot(cx, cy)){
                        r2.setBounds(r1.x, r1.y-30, 30, 30);
                        r3.setBounds(r1.x-30, r1.y, 30, 30);                
                        r4.setBounds(r1.x-30, r1.y+30, 30, 30);                        
                    }
                        
                }
            }
            if(k == 5){
                switch(rot){
                    case 2:
                       int[] cx = {0, +30 , 0};
                       int[] cy = {-30, 0 , +30};
                       if(checkRot(cx, cy)){
                           r2.setBounds(r1.x, r1.y-30, 30, 30);
                           r3.setBounds(r1.x+30, r1.y, 30, 30);                
                           r4.setBounds(r1.x, r1.y+30, 30, 30);                       
                       }                       
                       break;
                    case 3:
                       int[] cx1 = {+30, 0 , -30};
                       int[] cy1 = {0, +30 , 0};
                       if(checkRot(cx1, cy1)){
                           r2.setBounds(r1.x+30, r1.y, 30, 30);
                           r3.setBounds(r1.x, r1.y+30, 30, 30);                
                           r4.setBounds(r1.x-30, r1.y, 30, 30);                       
                       }                        
                       break;
                    case 4:
                       int[] cx2 = {0, -30, 0};
                       int[] cy2 = {+30, 0, -30};
                       if(checkRot(cx2, cy2)){
                           r2.setBounds(r1.x, r1.y+30, 30, 30);
                           r3.setBounds(r1.x-30, r1.y, 30, 30);                
                           r4.setBounds(r1.x, r1.y-30, 30, 30);
                       }
                       break;
                    case 1:
                       int[] cx3 = {-30, 0, +30};
                       int[] cy3 = {0, -30, 0};
                       if(checkRot(cx3, cy3)){
                           r2.setBounds(r1.x-30, r1.y, 30, 30);
                           r3.setBounds(r1.x, r1.y-30, 30, 30);                
                           r4.setBounds(r1.x+30, r1.y, 30, 30);
                       }
                       break;
                }
            }
            if(k == 6){
                switch(rot){
                    case 2:
                       int[] cx = {0, 0, -30};
                       int[] cy = {-30, +30, +30};
                       if(checkRot(cx, cy)){
                           r2.setBounds(r1.x, r1.y-30, 30, 30);
                           r3.setBounds(r1.x, r1.y+30, 30, 30);                
                           r4.setBounds(r1.x-30, r1.y+30, 30, 30);
                       }
                       break;
                    case 3:
                       int[] cx1 = {+30, -30, -30};
                       int[] cy1 = {0, 0, -30};
                       if(checkRot(cx1, cy1)){
                           r2.setBounds(r1.x+30, r1.y, 30, 30);
                           r3.setBounds(r1.x-30, r1.y, 30, 30);                
                           r4.setBounds(r1.x-30, r1.y-30, 30, 30);
                       }
                       break;
                    case 4:
                       int[] cx2 = {0, 0, +30};
                       int[] cy2 = {+30, -30, -30};
                       if(checkRot(cx2, cy2)){
                           r2.setBounds(r1.x, r1.y+30, 30, 30);
                           r3.setBounds(r1.x, r1.y-30, 30, 30);                
                           r4.setBounds(r1.x+30, r1.y-30, 30, 30);
                       }
                       break;
                    case 1:
                       int[] cx3 = {-30, +30, +30};
                       int[] cy3 = {0, 0, +30};
                       if(checkRot(cx3, cy3)){
                           r2.setBounds(r1.x-30, r1.y, 30, 30);
                           r3.setBounds(r1.x+30, r1.y, 30, 30);                
                           r4.setBounds(r1.x+30, r1.y+30, 30, 30);
                       }
                       break;
                }
            }
            if(k == 7){
                switch(rot){
                    case 2:
                       int[] cx = {0, 0, -30};
                       int[] cy = {+30, -30, -30};
                       if(checkRot(cx, cy)){
                           r2.setBounds(r1.x, r1.y+30, 30, 30);
                           r3.setBounds(r1.x, r1.y-30, 30, 30);                
                           r4.setBounds(r1.x-30, r1.y-30, 30, 30);
                       }
                       break;
                    case 3:
                       int[] cx1 = {-30, +30, +30};
                       int[] cy1 = {0, 0, -30};
                       if(checkRot(cx1, cy1)){
                           r2.setBounds(r1.x-30, r1.y, 30, 30);
                           r3.setBounds(r1.x+30, r1.y, 30, 30);                
                           r4.setBounds(r1.x+30, r1.y-30, 30, 30);
                       }
                       break;
                    case 4:
                       int[] cx2 = {0, 0, +30};
                       int[] cy2 = {-30, +30, +30};
                       if(checkRot(cx2, cy2)){
                           r2.setBounds(r1.x, r1.y-30, 30, 30);
                           r3.setBounds(r1.x, r1.y+30, 30, 30);                
                           r4.setBounds(r1.x+30, r1.y+30, 30, 30);    
                       }
                       break;
                    case 1:
                       int[] cx3 = {+30, -30, -30};
                       int[] cy3 = {0, 0, +30};
                       if(checkRot(cx3, cy3)){
                           r2.setBounds(r1.x+30, r1.y, 30, 30);
                           r3.setBounds(r1.x-30, r1.y, 30, 30);                
                           r4.setBounds(r1.x-30, r1.y+30, 30, 30);
                       }
                       break;
                }
            }
        }        
    }    
    
    public void moveX(){
        detectEdges();
        if(!leftEdge && xdir < 0){
            r1.setBounds(r1.x+xdir, r1.y, 30, 30);
            r2.setBounds(r2.x+xdir, r2.y, 30, 30);
            r3.setBounds(r3.x+xdir, r3.y, 30, 30);
            r4.setBounds(r4.x+xdir, r4.y, 30, 30);
        }
        if(!rightEdge && xdir > 0){
            r1.setBounds(r1.x+xdir, r1.y, 30, 30);
            r2.setBounds(r2.x+xdir, r2.y, 30, 30);
            r3.setBounds(r3.x+xdir, r3.y, 30, 30);
            r4.setBounds(r4.x+xdir, r4.y, 30, 30);
        }
    }
    
    public void moveY(){        
        r1.setBounds(r1.x, r1.y+ydir, 30, 30);
        r2.setBounds(r2.x, r2.y+ydir, 30, 30);
        r3.setBounds(r3.x, r3.y+ydir, 30, 30);
        r4.setBounds(r4.x, r4.y+ydir, 30, 30);
        if(r1.y >= 730 || r2.y >= 730 || r3.y >= 730 || r4.y >= 730){            
            stopBloco();
        }
    }
    
    public void detectEdges(){
        
        if (r1.x >= 295 || r2.x >= 295  || r3.x >= 295 || r4.x >= 295){
            rightEdge = true;
        } else {
            rightEdge = false;
        }
            
        if (r1.x <= 54 || r2.x <= 54  || r3.x <= 54 || r4.x <= 54){
            leftEdge = true;
        } else{
            leftEdge = false;
        }
            
    }
    
    public void setXdir(int d){
        xdir = d;
    }
    
    public void setYdir(int d){
        ydir = d;
    }
    
    public void stopBloco(){
        moving = false;
        setXdir(0);
        setYdir(0);        
        fastdown = false;
        try{
            Thread.sleep(150);
        }catch(Exception e){System.out.println(e);}

        mb.setTrue(r1.x, r1.y);
        mb.setTrue(r2.x, r2.y);
        mb.setTrue(r3.x, r3.y);
        mb.setTrue(r4.x, r4.y);
        
        mb.checkFullLine();
        pts.addPoints(mb.contLines);
        mb.checkStatus();
        this.changeLevel();
        
        
        if(!mb.gameOver){
            rebuild();
        } else {            
            r1.setBounds(0, 0, 0, 0);
            r2.setBounds(0, 0, 0, 0);
            r3.setBounds(0, 0, 0, 0);
            r4.setBounds(0, 0, 0, 0);
            //aviso de game over e salva a pontuação
            //TetrisFirst.tBloco.stop();
            this.moving = false;
            mb.gameOver = true;
            
        }

    }
    
    public void changeLevel(){
        if(pts.points > nextLevel){
            level++;
            timespeed -= 100;
            if(timespeed <= 0) 
                timespeed = 50;
            nextLevel += 10000;
        }
    }
    
    public void checkRoom(){
        
        boolean chk1, chk2, chk3, chk4; //checa se há espaço em baixo
        
        this.checkRoomOnLeft();
        this.checkRoomOnRight();
        
        chk1 = mb.getDataXY(r1.x, r1.y+30);
        chk2 = mb.getDataXY(r2.x, r2.y+30);
        chk3 = mb.getDataXY(r3.x, r3.y+30);
        chk4 = mb.getDataXY(r4.x, r4.y+30);
        
        if(chk1 || chk2 || chk3 || chk4){
            this.setYdir(0);
            this.stopBloco();
        }
    }
    
    public void checkRoomOnLeft(){
        
        boolean chk1, chk2, chk3, chk4; //checa se há espaço à esquerda
        
        chk1 = mb.getDataXY(r1.x-30, r1.y);
        chk2 = mb.getDataXY(r2.x-30, r2.y);
        chk3 = mb.getDataXY(r3.x-30, r3.y);
        chk4 = mb.getDataXY(r4.x-30, r4.y);
        
        if(chk1 || chk2 || chk3 || chk4){
            this.setXdir(0);            
        }
    }
    
    public void checkRoomOnRight(){
        
        boolean chk1, chk2, chk3, chk4; //checa se há espaço à direita
        
        chk1 = mb.getDataXY(r1.x+30, r1.y);
        chk2 = mb.getDataXY(r2.x+30, r2.y);
        chk3 = mb.getDataXY(r3.x+30, r3.y);
        chk4 = mb.getDataXY(r4.x+30, r4.y);
        
        if(chk1 || chk2 || chk3 || chk4){
            this.setXdir(0);            
        }
    }

    public boolean checkRot(int [] x, int[] y){
        boolean chk1, chk2, chk3, chk4, check;
        chk1 = false;
        chk2 = false;
        chk3 = false;        
        chk4 = false;
        this.rotation = true;
        check = true;
        
        //checa se já existem blocos ocupando o espaço para a rotação
        chk1 = mb.getDataXY(r1.x + x[0], r1.y + y[0]);
        chk2 = mb.getDataXY(r1.x + x[1], r1.y + y[1]);
        chk3 = mb.getDataXY(r1.x + x[2], r1.y + y[2]);
        chk4 = mb.getDataXY(r1.x, r1.y);        
        
        if(chk1 || chk2 || chk3 || chk4){
            check = false;
            this.setXdir(0);            
        }
        
        //checa se a rotação ainda fica dentro do limite do gameplay
        for(int i = 0; i < x.length; i++){
            if(r1.x + x[i] >= 325 || r1.x + x[i] < 25 || r1.y + y[i] < 50){
                check = false;
                this.setXdir(0);                
            }
        }
        return check;
                
    }
    
    
    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        if (keyCode == e.VK_LEFT){
            if(moving){
                setXdir(-30);                
                detectEdges();
                this.checkRoomOnLeft();
                if(!leftEdge){
                    r1.setBounds(r1.x+xdir, r1.y, 30, 30);
                    r2.setBounds(r2.x+xdir, r2.y, 30, 30);
                    r3.setBounds(r3.x+xdir, r3.y, 30, 30);
                    r4.setBounds(r4.x+xdir, r4.y, 30, 30);
                }
            }
        }

        if (keyCode == e.VK_RIGHT){
            if(moving){
                setXdir(+30);
                detectEdges();
                this.checkRoomOnRight();
                if(!rightEdge){
                    r1.setBounds(r1.x+xdir, r1.y, 30, 30);
                    r2.setBounds(r2.x+xdir, r2.y, 30, 30);
                    r3.setBounds(r3.x+xdir, r3.y, 30, 30);
                    r4.setBounds(r4.x+xdir, r4.y, 30, 30);
                }
            }              
        }
        if (keyCode == e.VK_UP){
            if(moving)
                rotate();
        }

        if (keyCode == e.VK_DOWN){
            if(moving && !fastdown){                
                fastdown = true;                
                r1.setBounds(r1.x, r1.y+ydir, 30, 30);
                r2.setBounds(r2.x, r2.y+ydir, 30, 30);
                r3.setBounds(r3.x, r3.y+ydir, 30, 30);
                r4.setBounds(r4.x, r4.y+ydir, 30, 30);                
            }
        }
        
        if (keyCode == e.VK_SPACE){
            this.pause();
        }
    }

    public void keyReleased(KeyEvent e){
        int keyCode = e.getKeyCode();
        if (keyCode == e.VK_LEFT){
            setXdir(0);
        }

        if (keyCode == e.VK_RIGHT){
            setXdir(0);
        }

    }
    
    @Override
    public void run(){
        mb.clearAll();
        boolean wait = true;        
        try{            
            while(true){
                if(!paused){
                    this.checkRoom();
                    Thread.sleep(2);
                    moveX();                    
                    while(fastdown){
                        this.checkRoom();
                        Thread.sleep(2);
                        moveY();
                        Thread.sleep(20);                    
                    }                   
                    if(!wait){
                        long start = System.currentTimeMillis();
                        long end = start + timespeed;
                        while(System.currentTimeMillis() < end){
                            this.checkRoom();
                            moveY();
                            Thread.sleep(timespeed);
                        }
                        wait = true;
                    } else {              
                        Thread.sleep(timespeed);
                        wait = false;                    
                    }                
                }
            }
        }        
        catch(Exception e){System.out.println("System error: "+e);}
    }
}
