package za.ac.cput.calendar;

/**DayDate.java
 *Project 3 Assignment
 * @author Anicka Schouw 217284183
 * October 2021
 */

import za.ac.cput.Factory.Task;

import java.awt.Component;
import java.util.Calendar;
import java.util.*;



public class DayDate extends javax.swing.JLayeredPane {

    private int month,year;

    public DayDate(int month,int year) {
        initComponents();
        this.month = month;
        this.year = year;
        init();
    }
    private void init(){
        mon.asDay();
        tue.asDay();
        wed.asDay();
        thu.asDay();
        fri.asDay();
        sat.asDay();
        sun.asDay();
        setDate();
    }

    private void setDate(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month -1); //January as 0 therefore start from 0
        c.set(Calendar.DATE, 1);
        int startDay = c.get(Calendar.DAY_OF_WEEK) -1; // -1 is to index, get day of the week.
        c.add(Calendar.DATE, -startDay);
        Day day = getDay();
        for(Component com : getComponents()){
            Blocks b = (Blocks) com;
            if(!b.isDay()){
                b.setText(c.get(Calendar.DATE)+ "");
                b.setDate(c.getTime());
                b.currentMonth(c.get(Calendar.MONTH) == month - 1);
                if(day.isDay(new Day(c.get(Calendar.DATE),c.get(Calendar.MONTH)+1,c.get(Calendar.YEAR)))){
                    b.setAsDay();
                }
                c.add(Calendar.DATE, 1);
            }
        }
    }

    private Day getDay(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH)+ 1, c.get(Calendar.YEAR));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">

    private void initComponents() {

        sun = new za.ac.cput.calendar.Blocks();
        mon = new za.ac.cput.calendar.Blocks();
        tue = new za.ac.cput.calendar.Blocks();
        wed = new za.ac.cput.calendar.Blocks();
        thu = new za.ac.cput.calendar.Blocks();
        fri = new za.ac.cput.calendar.Blocks();
        sat = new za.ac.cput.calendar.Blocks();
        blocks8 = new za.ac.cput.calendar.Blocks();
        blocks9 = new za.ac.cput.calendar.Blocks();
        blocks10 = new za.ac.cput.calendar.Blocks();
        blocks11 = new za.ac.cput.calendar.Blocks();
        blocks12 = new za.ac.cput.calendar.Blocks();
        blocks13 = new za.ac.cput.calendar.Blocks();
        blocks14 = new za.ac.cput.calendar.Blocks();
        blocks15 = new za.ac.cput.calendar.Blocks();
        blocks16 = new za.ac.cput.calendar.Blocks();
        blocks17 = new za.ac.cput.calendar.Blocks();
        blocks18 = new za.ac.cput.calendar.Blocks();
        blocks19 = new za.ac.cput.calendar.Blocks();
        blocks20 = new za.ac.cput.calendar.Blocks();
        blocks21 = new za.ac.cput.calendar.Blocks();
        blocks22 = new za.ac.cput.calendar.Blocks();
        blocks23 = new za.ac.cput.calendar.Blocks();
        blocks24 = new za.ac.cput.calendar.Blocks();
        blocks25 = new za.ac.cput.calendar.Blocks();
        blocks26 = new za.ac.cput.calendar.Blocks();
        blocks27 = new za.ac.cput.calendar.Blocks();
        blocks28 = new za.ac.cput.calendar.Blocks();
        blocks29 = new za.ac.cput.calendar.Blocks();
        blocks30 = new za.ac.cput.calendar.Blocks();
        blocks31 = new za.ac.cput.calendar.Blocks();
        blocks32 = new za.ac.cput.calendar.Blocks();
        blocks33 = new za.ac.cput.calendar.Blocks();
        blocks34 = new za.ac.cput.calendar.Blocks();
        blocks35 = new za.ac.cput.calendar.Blocks();
        blocks36 = new za.ac.cput.calendar.Blocks();
        blocks37 = new za.ac.cput.calendar.Blocks();
        blocks38 = new za.ac.cput.calendar.Blocks();
        blocks39 = new za.ac.cput.calendar.Blocks();
        blocks40 = new za.ac.cput.calendar.Blocks();
        blocks41 = new za.ac.cput.calendar.Blocks();
        blocks42 = new za.ac.cput.calendar.Blocks();
        blocks43 = new za.ac.cput.calendar.Blocks();
        blocks44 = new za.ac.cput.calendar.Blocks();
        blocks45 = new za.ac.cput.calendar.Blocks();
        blocks46 = new za.ac.cput.calendar.Blocks();
        blocks47 = new za.ac.cput.calendar.Blocks();
        blocks48 = new za.ac.cput.calendar.Blocks();
        blocks49 = new za.ac.cput.calendar.Blocks();

        setLayout(new java.awt.GridLayout(7, 7));

        sun.setBackground(new java.awt.Color(255, 255, 255));
        sun.setForeground(new java.awt.Color(255, 0, 0));
        sun.setText("Sunday");
        sun.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        add(sun);

        mon.setBackground(new java.awt.Color(255, 255, 255));
        mon.setText("Monday");
        mon.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        add(mon);

        tue.setBackground(new java.awt.Color(255, 255, 255));
        tue.setText("Tuesday");
        tue.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        add(tue);

        wed.setBackground(new java.awt.Color(255, 255, 255));
        wed.setText("Wednesday");
        wed.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        add(wed);

        thu.setBackground(new java.awt.Color(255, 255, 255));
        thu.setText("Thursday");
        thu.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        add(thu);

        fri.setBackground(new java.awt.Color(255, 255, 255));
        fri.setText("Friday");
        fri.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        add(fri);

        sat.setBackground(new java.awt.Color(255, 255, 255));
        sat.setForeground(new java.awt.Color(255, 0, 0));
        sat.setText("Saturday");
        sat.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        add(sat);

        blocks8.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks8);

        blocks9.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks9);

        blocks10.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks10);

        blocks11.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks11);

        blocks12.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks12);

        blocks13.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks13);

        blocks14.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks14);

        blocks15.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks15);

        blocks16.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks16);

        blocks17.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks17);

        blocks18.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks18);

        blocks19.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks19);

        blocks20.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks20);

        blocks21.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks21);

        blocks22.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks22);

        blocks23.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks23);

        blocks24.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks24);

        blocks25.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks25);

        blocks26.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks26);

        blocks27.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks27);

        blocks28.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks28);

        blocks29.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks29);

        blocks30.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks30);

        blocks31.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks31);

        blocks32.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks32);

        blocks33.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks33);

        blocks34.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks34);

        blocks35.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks35);

        blocks36.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks36);

        blocks37.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks37);

        blocks38.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks38);

        blocks39.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks39);

        blocks40.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks40);

        blocks41.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks41);

        blocks42.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks42);

        blocks43.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks43);

        blocks44.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks44);

        blocks45.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks45);

        blocks46.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks46);

        blocks47.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks47);

        blocks48.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks48);

        blocks49.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 12)); // NOI18N
        blocks49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blocks8ActionPerformed(evt);
            }
        });
        add(blocks49);
    }// </editor-fold>

    private void blocks8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blocks8ActionPerformed
        Task t = new Task();
        t.setGUI();
    }//GEN-LAST:event_blocks8ActionPerformed
    /**/

    // Variables declaration - do not modify
    private za.ac.cput.calendar.Blocks blocks10;
    private za.ac.cput.calendar.Blocks blocks11;
    private za.ac.cput.calendar.Blocks blocks12;
    private za.ac.cput.calendar.Blocks blocks13;
    private za.ac.cput.calendar.Blocks blocks14;
    private za.ac.cput.calendar.Blocks blocks15;
    private za.ac.cput.calendar.Blocks blocks16;
    private za.ac.cput.calendar.Blocks blocks17;
    private za.ac.cput.calendar.Blocks blocks18;
    private za.ac.cput.calendar.Blocks blocks19;
    private za.ac.cput.calendar.Blocks blocks20;
    private za.ac.cput.calendar.Blocks blocks21;
    private za.ac.cput.calendar.Blocks blocks22;
    private za.ac.cput.calendar.Blocks blocks23;
    private za.ac.cput.calendar.Blocks blocks24;
    private za.ac.cput.calendar.Blocks blocks25;
    private za.ac.cput.calendar.Blocks blocks26;
    private za.ac.cput.calendar.Blocks blocks27;
    private za.ac.cput.calendar.Blocks blocks28;
    private za.ac.cput.calendar.Blocks blocks29;
    private za.ac.cput.calendar.Blocks blocks30;
    private za.ac.cput.calendar.Blocks blocks31;
    private za.ac.cput.calendar.Blocks blocks32;
    private za.ac.cput.calendar.Blocks blocks33;
    private za.ac.cput.calendar.Blocks blocks34;
    private za.ac.cput.calendar.Blocks blocks35;
    private za.ac.cput.calendar.Blocks blocks36;
    private za.ac.cput.calendar.Blocks blocks37;
    private za.ac.cput.calendar.Blocks blocks38;
    private za.ac.cput.calendar.Blocks blocks39;
    private za.ac.cput.calendar.Blocks blocks40;
    private za.ac.cput.calendar.Blocks blocks41;
    private za.ac.cput.calendar.Blocks blocks42;
    private za.ac.cput.calendar.Blocks blocks43;
    private za.ac.cput.calendar.Blocks blocks44;
    private za.ac.cput.calendar.Blocks blocks45;
    private za.ac.cput.calendar.Blocks blocks46;
    private za.ac.cput.calendar.Blocks blocks47;
    private za.ac.cput.calendar.Blocks blocks48;
    private za.ac.cput.calendar.Blocks blocks49;
    private za.ac.cput.calendar.Blocks blocks8;
    private za.ac.cput.calendar.Blocks blocks9;
    private za.ac.cput.calendar.Blocks fri;
    private za.ac.cput.calendar.Blocks mon;
    private za.ac.cput.calendar.Blocks sat;
    private za.ac.cput.calendar.Blocks sun;
    private za.ac.cput.calendar.Blocks thu;
    private za.ac.cput.calendar.Blocks tue;
    private za.ac.cput.calendar.Blocks wed;
    // End of variables declaration
}
