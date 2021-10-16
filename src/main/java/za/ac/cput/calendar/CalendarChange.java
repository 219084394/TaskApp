package za.ac.cput.calendar;

/**CalendarChange.java
 *Project 3 Assignment
 * @author Anicka Schouw 217284183
 * October 2021
 */


import za.ac.cput.Slide.CalendarSlide;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarChange extends javax.swing.JPanel {

    private int month ;
    private int year ;

    public CalendarChange() {
        initComponents();
        thisMonth();
        transition.show(new DayDate(5,2021), CalendarSlide.AnimateType.TO_RIGHT);
        displayMonthYear();
        new Thread(new Runnable(){
            @Override
            public void run(){
                while(true){
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        System.err.println(e);
                    }
                    Date d = new Date();
                    SimpleDateFormat tf = new SimpleDateFormat("hh:mm:ss aa");
                    SimpleDateFormat df = new SimpleDateFormat("EEEE, dd/MM-yyyy");
                    String t =tf.format(d);
                    lbTime.setText(t.split(" ")[0]);
                    lbPmAm.setText(t.split(" ")[1]);
                    lbDayDate.setText(df.format(d));
                }
            }
        }).start();

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        transition = new za.ac.cput.Slide.CalendarSlide();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        Next = new javax.swing.JButton();
        Previous = new javax.swing.JButton();
        LabelMthYr = new javax.swing.JLabel();
        lbTime = new javax.swing.JLabel();
        lbPmAm = new javax.swing.JLabel();
        lbDayDate = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setBackground(new java.awt.Color(250, 134, 0));

        transition.setBackground(new java.awt.Color(255, 255, 255));

        Next.setText(">>");
        Next.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Next.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }
        });

        Previous.setText("<<");
        Previous.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Previous.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreviousActionPerformed(evt);
            }
        });

        LabelMthYr.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LabelMthYr.setForeground(new java.awt.Color(255, 255, 255));
        LabelMthYr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelMthYr.setText("Month - Year");

        jLayeredPane1.setLayer(Next, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(Previous, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(LabelMthYr, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
                jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(LabelMthYr, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(Next, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
                jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Next, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(LabelMthYr)))
        );

        lbTime.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbTime.setText("8:22:00");

        lbPmAm.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbPmAm.setText("AM");

        lbDayDate.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbDayDate.setText("Tuesday 12/10/2021");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(transition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(61, 61, 61)
                                                                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(265, 265, 265)
                                                                .addComponent(lbTime)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(lbPmAm))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(178, 178, 178)
                                                                .addComponent(lbDayDate)))
                                                .addGap(0, 53, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(transition, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbTime, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbPmAm))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbDayDate)
                                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextActionPerformed
        if(month == 12){
            month = 1;
            year++;
        }else{
            month++;
        }
        transition.show(new DayDate(month, year),CalendarSlide.AnimateType.TO_LEFT);
        displayMonthYear();
    }//GEN-LAST:event_NextActionPerformed

    private void PreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreviousActionPerformed
        if(month == 1){
            month = 12;
            year --;
        }else{
            month--;
        }
        transition.show(new DayDate(month, year), CalendarSlide.AnimateType.TO_RIGHT);
        displayMonthYear();
    }//GEN-LAST:event_PreviousActionPerformed

    private void thisMonth(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        month = c.get(Calendar.MONTH) + 1;
        year = c.get(Calendar.YEAR);
    }

    private void displayMonthYear(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, month -1);
        c.set(Calendar.YEAR, year);
        c.set(Calendar.DATE,1);
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM-yyyy");
        LabelMthYr.setText(sdf.format(c.getTime()));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelMthYr;
    private javax.swing.JButton Next;
    private javax.swing.JButton Previous;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel lbDayDate;
    private javax.swing.JLabel lbPmAm;
    private javax.swing.JLabel lbTime;
    private za.ac.cput.Slide.CalendarSlide transition;
    // End of variables declaration//GEN-END:variables
}
