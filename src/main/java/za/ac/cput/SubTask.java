package za.ac.cput;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SubTask {
    private JPanel header;
    private JPanel subtaskPanel;
    private JTextField srchTxt;
    private JButton searchButton;
    private JTextField taskNameTxt;
    private JTextField courseTxt;
    private JTable table2;
    private JTextField SubTaskTxtName;
    private JTextField subDueDateTxt;
    private JTextArea subDescTxt;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton addButton;
    private JTextArea descTxt;
    private JTextField dueTxt;
    private JTextField subSrchTxt;
    private JButton subSrchBtn;
    private JPanel findTaskPanel;
    private JPanel fTaskPanel;
    private JPanel subTaskHeadingPanel;
    private JPanel subTaskFieldPanel;
    private JPanel subTaskBtnPanel;
    private JLabel subTaskHead;
    private JButton SubClearBtn;
    private JPanel clearBtnPanel;
    private JButton TaskClrBtn;
    protected String x = Crud.sT;


    Connection con2;
    PreparedStatement pst;
    public void Connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con2 = DriverManager.getConnection("jdbc:mysql://localhost/tableinfo","root","");
            System.out.println("success");
        }catch (ClassNotFoundException ex){

        }catch (SQLException ex){

        }
    }

    void table_load(){
        try{
            pst = con2.prepareStatement("select * from subtask where TaskId='"+x+"'");
            ResultSet rs = pst.executeQuery();
            table2.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void setGUI(){
        JFrame frame = new JFrame("SubTask");
        frame.setContentPane(new SubTask().subtaskPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    public SubTask() {
        Connect();
        table_load();
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String id = srchTxt.getText();
                    pst = con2.prepareStatement("select TaskName,CourseCode,TaskDescription,DueDate from tasks where TaskId = ?");
                    pst.setString(1,id);
                    ResultSet rs = pst.executeQuery();
                    if(id.equals("")){
                        JOptionPane.showMessageDialog(null, "Task ID not entered");
                    }

                    else if(rs.next() == true)
                    {
                        String TaskName = rs.getString(1);
                        String Course = rs.getString(2);
                        String TaskDescription = rs.getString(3);
                        String DueDate = rs.getString(4);

                        taskNameTxt.setText(TaskName);
                        courseTxt.setText(Course);
                        descTxt.setText(TaskDescription);
                        dueTxt.setText(DueDate);
                        JOptionPane.showMessageDialog(null, "Task loaded");

                    }else {
                        taskNameTxt.setText("");
                        courseTxt.setText("");
                        descTxt.setText("");
                        dueTxt.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid Task ID");
                    }
                }catch(SQLException ex){
                    ex.printStackTrace();
                }

            }
        });
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
                        pst = con2.prepareStatement("insert into subtask(TaskId,SubTaskName,SubTaskDescription,DueDate)values('"+x+"',?,?,?)");
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

        subSrchBtn.addActionListener(new ActionListener() {
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
                    srchTxt.setText("");
                    SubTaskTxtName.requestFocus();
                }catch(SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
        SubClearBtn.addActionListener(new ActionListener() {
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
        TaskClrBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Clear")) {
                    taskNameTxt.setText("");
                    courseTxt.setText("");
                    descTxt.setText("");
                    srchTxt.setText("");
                    taskNameTxt.requestFocus();
                }
            }
        });
    }
    public static void main(String[] args) {
        new SubTask().setGUI();
    }

}
