package za.ac.cput.calendar;

/**Blocks.java
 * Project 3 Assignment
 * @author Anicka Schouw 217284183
 * October 2021
 */

import java.awt.*;
import java.util.Date;
import javax.swing.*;


public class Blocks extends JButton {

    private Date date;
    private boolean day;
    private boolean toDay;

    public Blocks(){
        setContentAreaFilled(false);
        setBorder(null);
        setHorizontalAlignment(JLabel.CENTER);
    }
    public void asDay(){
        day = true;
    }

    public boolean isDay(){
        return day;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public void currentMonth(boolean act){
        if(act){
            setForeground(new Color(75,75,75));
        } else {
            setForeground(new Color(170,170,170));
        }
    }

    public void setAsDay(){
        toDay = true;
        setForeground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g){
        if(day){
            g.setColor(new Color(212,212,212));
            g.drawLine(0, getHeight()-1, getWidth(), getHeight()-1);
        }
        if(toDay){
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(250,134,0));
            int w = getWidth()/ 2 - 17;
            int h = getHeight()/ 2 - 17;
            g2.fillRoundRect(w,h, 35, 35, 100, 100);
        }
        super.paintComponent(g);
    }

}
