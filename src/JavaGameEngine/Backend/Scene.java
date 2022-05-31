package JavaGameEngine.Backend;

import JavaGameEngine.Backend.Input.Input;
import JavaGameEngine.Backend.Input.Keys;
import JavaGameEngine.Components.Component;
import JavaGameEngine.JavaGameEngine;
import JavaGameEngine.msc.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Scene extends JPanel{
    /*
        This is where the main game world where we draw everything
        we also gets all the inpus from here
     */
    public static LinkedList<Component> layerList = new LinkedList<>();
    public LinkedList<Component> components = new LinkedList<>();

    public static String fps = "0";
    private long last;

    public int id=120313;
    private boolean active = false;

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void start(){
             /*
          Key keyboard inputs
         */
        setActive(true);
        setBackground(new Color(44, 157, 228));

        for(Component a : components){
            a.start();
        }
        UpdateThread.camera.setPosition(new Vector2(0,0));

    }

    float fpsecund = 0;

    /**
     * Here is the main drawing function
     * */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawComponents(g);
        if(System.nanoTime()-last>1000000000){
            fps = Float.toString(fpsecund);
            fpsecund = 0;
            last = System.nanoTime();
        }
        //System.out.println(id);
        fpsecund+=1;
    }
    private void drawUi(Graphics g){

    }
    private void drawComponents(Graphics g){
        List<Component> list = components;
        Collections.sort(list, new Comparator<Component>() {
            @Override
            public int compare(Component o1, Component o2) {
                return o1.getLayer() - o2.getLayer();
            }
        });

        for(Component c : list){
            (c).draw(g);
            g.drawString(fps,10,20);
        }
    }
}