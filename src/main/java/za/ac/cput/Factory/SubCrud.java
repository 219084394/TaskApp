package za.ac.cput.Factory;

import net.proteanit.sql.DbUtils;
import za.ac.cput.Crud;
import za.ac.cput.SubTask;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SubCrud {
    private JPanel subPanel;
    private JTable table1;
    private JPanel subTaskFieldPanel;
    private JPanel subBtnPanel;
    private JTextField SubTaskTxtName;
    private JTextField subDueDateTxt;
    private JTextArea subDescTxt;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton addButton;
    private JButton clearButton;
    private JButton searchButton;
    private JTextField subSrchTxt;
    protected String y = Task.sT;

    Connection con2;
    PreparedStatement pst;
    public void Connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con2 = DriverManager.getConnection("jdbc:mysql://localhost/tableinfo","root","");
            System.out.println("success");
            System.out.println(y);
        }catch (ClassNotFoundException ex){

        }catch (SQLException ex){

        }
    }

    void table_load(){
        try{
            pst = con2.prepareStatement("select * from subtask where TaskId='"+y+"'");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void setGUI(){
        JFrame frame = new JFrame("SubTask");
        frame.setContentPane(new SubCrud().subPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    public SubCrud() {
        Connect();
        table_load();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskName,taskDescription, dueDate;
                taskName = SubTaskTxtName.getText();
                taskDescription = subDescTxt.getText();
                dueDate = subDueDateTxt.getText();


                try{
                    if(taskName.equals("")){
                        JOptionPane.showMessageDialog(null, "No fields added");
                    }else {
                        pst = con2.prepareStatement("insert into subtask(TaskId,SubTaskName,SubTaskDescription,DueDate)values('"+y+"',?,?,?)");
                        pst.setString(1,taskName);
                        pst.setString(2,taskDescription);
                        pst.setString(3,dueDate);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Task Added");
                        table_load();
                        SubTaskTxtName.setText("");
                        subDescTxt.setText("");
                        subDueDateTxt.setText("");
                        SubTaskTxtName.requestFocus();
                    }
                }catch(SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String id = subSrchTxt.getText();
                    pst = con2.prepareStatement("select SubTaskName,SubTaskDescription,DueDate from subtask where SubTaskId = ?");
                    pst.setString(1,id);
                    ResultSet rs = pst.executeQuery();
                    if(id.equals("")){
                        JOptionPane.showMessageDialog(null, "Task ID not entered");
                    }

                    else if(rs.next() == true)
                    {
                        String TaskName = rs.getString(1);
                        String TaskDescription = rs.getString(2);
                        String DueDate = rs.getString(3);

                        SubTaskTxtName.setText(TaskName);
                        subDescTxt.setText(TaskDescription);
                        subDueDateTxt.setText(DueDate);
                        JOptionPane.showMessageDialog(null, "Task loaded");

                    }else {
                        SubTaskTxtName.setText("");
                        subDescTxt.setText("");
                        subDueDateTxt.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid Task ID");
                    }
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Clear")) {
                    SubTaskTxtName.setText("");
                    subDescTxt.setText("");
                    subSrchTxt.setText("");
                    SubTaskTxtName.requestFocus();
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int taskID;
                String taskName, course, taskDescription, dueDate,taskId;
                taskName = SubTaskTxtName.getText();
                taskDescription = subDescTxt.getText();
                dueDate = subDueDateTxt.getText();
                taskId = subSrchTxt.getText();


                try{
                    pst = con2.prepareStatement("update subtask set SubTaskName = ?,SubTaskDescription = ?,DueDate = ? where SubTaskId = ?");
                    pst.setString(1,taskName);
                    pst.setString(2,taskDescription);
                    pst.setString(3,dueDate);
                    pst.setString(4,taskId);
                    pst.executeUpdate();
                    if(taskId.equals("")){
                        JOptionPane.showMessageDialog(null, "No task loaded");
                    }else {
                        JOptionPane.showMessageDialog(null, "Task Updated");
                    }
                    table_load();
                    SubTaskTxtName.setText("");
                    subDescTxt.setText("");
                    subDueDateTxt.setText("");
                    subSrchTxt.setText("");
                    subSrchTxt.requestFocus();
                }catch(SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskId;
                taskId = subSrchTxt.getText();

                try{
                    pst = con2.prepareStatement("delete from subtask where SubTaskId = ?");
                    pst.setString(1,taskId);
                    pst.executeUpdate();
                    if(taskId.equals("")){
                        JOptionPane.showMessageDialog(null, "No task loaded");
                    }else {
                        JOptionPane.showMessageDialog(null, "Task Deleted");
                    }
                    table_load();
                    SubTaskTxtName.setText("");
                    subDescTxt.setText("");
                    subDueDateTxt.setText("");
                    SubTaskTxtName.requestFocus();
                }catch(SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        new SubCrud().setGUI();
    }
}
