
package tetrisfirst;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.ImageIcon;
        

public class TetrisFirst extends JFrame {

    private Image dbImage;
    private Graphics dbg;
    
    private Image fundo_jogo; //background
    private Image fundo_game; //background do gameplay
    
    int mx, my;
    int i, j = 0;
    
    static boolean startGame = false;
    boolean gameOver = false;
    
    Botao btnIniciar = new Botao(380, 540, "        Inciar");
    Botao btnPausar = new Botao(380, 580, "      Pausar");
    Botao btnRecordes = new Botao(380, 620, "   Red Blocks");
    
    static Bloco b = new Bloco();
    static Thread tBloco = new Thread(b);
    
    public TetrisFirst() {
        
        int WIDTH, HEIGHT;
        WIDTH = 540;
        HEIGHT = 780;
        
        this.setLocation(600, 70);
        
        //Game properties        
        addKeyListener(new AL());
        addMouseMotionListener(new MouseHandler());
        addMouseListener(new MouseHandler());
        setTitle("Red Blocks v0.1");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setVisible(true);
        setBackground(Color.black);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

    }    
    
    public int getWIDTH(){
        return WIDTH;
    }
    public int getHEIGHT(){
        return HEIGHT;
    }
    
    public void mouseX(int d){
        mx = d;
    }
    public void mouseY(int d){
        my = d;
    }
    
    public void restart(){
        //tBloco.start();
        b.restart();
    }
    
    public void checarBotoes(){
        if(btnIniciar.pressed && !startGame){
            tBloco.start();
            startGame = true;
            btnIniciar.pressed(false);
            btnPausar.pressed(false);
            btnRecordes.pressed(false);
        }
        if(btnIniciar.pressed && b.mb.gameOver){
            restart();
            b.mb.gameOver = false;
            startGame = true;
            btnIniciar.pressed(false);
            btnPausar.pressed(false);
            btnRecordes.pressed(false);
        }
        if(btnPausar.pressed && !b.mb.gameOver && startGame){
            b.pause();
            btnIniciar.pressed(false);
            btnPausar.pressed(false);
            btnRecordes.pressed(false);
        }
        if(btnRecordes.pressed){
            JOptionPane.showMessageDialog(null, "Desenvolvido por Márcio Brito\ndimarcinho@gmail.com");
            btnIniciar.pressed(false);
            btnPausar.pressed(false);
            btnRecordes.pressed(false);
        }
    }
    
    @Override
    public void paint(Graphics g){
        dbImage = createImage(getWidth(),getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage,0,0,this);
    }
    
    public void paintComponent(Graphics g){

        tileSets(g);
        
        g.setColor(Color.yellow);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Nível: ", 350, 350);
        g.drawString(""+b.level, 415, 350);
        g.drawString("Pontos: ", 350, 380);
        g.drawString("Linhas: ", 350, 410);

        //coordenadas do mouse; não inserir no jogo final
        g.drawString("("+mx+","+my+")", 350, 760);        
        
        btnIniciar.draw(g);
        btnPausar.draw(g);
        btnRecordes.draw(g);
        
        if(!b.paused && startGame){
            b.draw(g);
            b.mb.draw(g);            
        }        
        b.pts.draw(g);
        
        g.setColor(Color.yellow);
        g.drawRect(25, 50, 300, 720); //divisória entre o fundo e o gameplay
        g.drawRect(350, 100, 150, 150); // fundo do preview
        
        if(b.paused && !b.mb.gameOver){
            g.setColor(Color.magenta);
            g.setFont(new Font("Arial", Font.BOLD, 60));
            g.drawString("Jogo", 105, 320);
            g.drawString("Pausado", 55, 450);
        }
        
        if(!startGame){
            
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 60));
            g.drawString("Red", 105, 320);
            g.drawString("Blocks", 75, 450);
        }
        
        if(b.mb.gameOver){
            
            g.setColor(Color.cyan);
            g.setFont(new Font("Arial", Font.BOLD, 60));
            g.drawString("GAME", 95, 320);
            g.drawString("OVER", 95, 450);
        }      
        
        repaint();
    }
    
    public void tileSets(Graphics g){
        
        //Colore o fundo do frame
        ImageIcon p = new ImageIcon("C:/Users/Marcio/Documents/NetBeansProjects/TetrisFirst/src/tetrisfirst/img/fundo_jogo.png");
        fundo_jogo = p.getImage();
        
        i=0;
        j=0;
        
        while(i < 600){
            while(j < 780){
                g.drawImage(fundo_jogo, i, j, 30, 30, null);
                j = j + 30;
            }
            i = i + 30;
            j = 0;
        }
        
        //Colore o fundo do gameplay
        ImageIcon q = new ImageIcon("C:/Users/Marcio/Documents/NetBeansProjects/TetrisFirst/src/tetrisfirst/img/fundo_game.jpg");
        fundo_game = q.getImage();
        
        i=25;
        j=50;
        
        //Colore o fundo do preview
        while(i < 325){
            while(j < 770){
                g.drawImage(fundo_game, i, j, 30, 30, null);
                j = j + 30;
            }
            i = i + 30;
            j = 50;
        }
        
        //Colore o fundo do preview
        ImageIcon r = new ImageIcon("C:/Users/Marcio/Documents/NetBeansProjects/TetrisFirst/src/tetrisfirst/img/fundo_preview.jpg");
        fundo_game = r.getImage();
        
        i=350;
        j=100;
        
        while(i < 500){
            while(j < 250){
                g.drawImage(fundo_game, i, j, 30, 30, null);
                j = j + 30;
            }
            i = i + 30;
            j = 100;
        }
        
    }
    
    public class AL extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            b.keyPressed(e);                        
        }
            
        @Override
        public void keyReleased(KeyEvent e){
            b.keyReleased(e);            
        }
    }
    
    public class MouseHandler extends MouseAdapter {
        
        @Override
        public void mouseMoved(MouseEvent e){
            mouseX(e.getX());
            mouseY(e.getY());
            btnIniciar.mouseMoved(e);
            btnPausar.mouseMoved(e);
            btnRecordes.mouseMoved(e);
        }
        @Override
        public void mousePressed(MouseEvent e){
            btnIniciar.mousePressed(e);
            btnPausar.mousePressed(e);
            btnRecordes.mousePressed(e);
            checarBotoes();
        }
        @Override
        public void mouseReleased(MouseEvent e){
            btnIniciar.mouseReleased(e);
            btnPausar.mouseReleased(e);
            btnRecordes.mouseReleased(e);
            if(btnIniciar.pressed){
                btnIniciar.pressed = false;
            }
            if(btnPausar.pressed){
                btnPausar.pressed = false;                
            }
            if(btnRecordes.pressed){
                btnRecordes.pressed = false;
            }
        }
    }
    
    public static void main(String[] args) {
        TetrisFirst jogo = new TetrisFirst();        
    }
}
