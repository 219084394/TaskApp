package za.ac.cput;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import javax.swing.*;

public class Login extends JFrame implements ActionListener{

    public static String uN;
    private JPanel panelNorth, panelCenter, panelSouth;
    private JLabel lblUsername, lblPassword, lblImage;
    protected JTextField userName;
    private JPasswordField password;
    private JButton btnSubmit, btnSignUp;
    private ImageIcon image;
    private JLabel lblErrorOne, lblErrorTwo, lblBlank, lblBlankTwo, lblBlankThree, lblBlankFour, lblBlankFive;
    private JCheckBox checkBox;
    //protected static String uN;

    public Login(){
        super("Login");
        panelNorth = new JPanel();
        panelCenter = new JPanel();
        panelSouth = new JPanel();
        lblImage = new JLabel("" ,SwingConstants.CENTER);
        lblUsername = new JLabel("Student Number: ", SwingConstants.RIGHT);
        lblPassword = new JLabel("Password: ", SwingConstants.RIGHT);
        userName = new JTextField();
        password = new JPasswordField();
        btnSubmit = new JButton("Submit");
        btnSignUp = new JButton("Don't have an account? Register here.");
        image = new ImageIcon("LogIn.png");
        lblErrorOne = new JLabel("*Student Number Required");
        lblErrorTwo = new JLabel("*Password Required");
        lblBlank = new JLabel("");
        lblBlankTwo = new JLabel("");
        lblBlankThree = new JLabel("");
        lblBlankFour = new JLabel("");
        lblBlankFive = new JLabel("");
        checkBox = new JCheckBox("Show Password");
    }
    public void setLogin(){
        panelNorth.setLayout(new GridLayout(1,1));
        panelCenter.setLayout(new GridLayout(4,3, 3, 4));
        panelSouth.setLayout(new GridLayout(1,1));

        panelNorth.setBorder(BorderFactory.createEmptyBorder(25,15,5,15));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(50,15,50,15));
        panelSouth.setBorder(BorderFactory.createEmptyBorder(0,15,25,15));

        //make error messages red
        lblErrorOne.setForeground(Color.RED);
        lblErrorTwo.setForeground(Color.RED);

        //adding image to GUI
        lblImage.setIcon(image);

        lblUsername.setForeground(Color.white);
        lblPassword.setForeground(Color.white);
        checkBox.setForeground(Color.white);
        btnSignUp.setForeground(Color.white);

        //hides password
        password.setEchoChar('*');

        //remove border around button
        btnSignUp.setContentAreaFilled(false);
        btnSignUp.setBorder(null);
        btnSignUp.setBorderPainted(false);

        //remove border of show button
        checkBox.setContentAreaFilled(false);

        //setting the GUI Background Color
        panelNorth.setBackground(new Color(0,29,64));
        panelCenter.setBackground(new Color(0,29,64));
        panelSouth.setBackground(new Color(0,29,64));

        //Error Handling set to invisible
        lblErrorOne.setVisible(false);
        lblErrorTwo.setVisible(false);

        btnSubmit.setBackground(new Color(250, 134, 0));
        btnSubmit.setForeground(Color.white);

        //Adding necessary components to GUI
        panelNorth.add(lblImage);

        panelCenter.add(lblUsername);
        panelCenter.add(userName);
        panelCenter.add(lblErrorOne);
        panelCenter.add(lblPassword);
        panelCenter.add(password);
        panelCenter.add(lblErrorTwo);
        panelCenter.add(lblBlank);
        panelCenter.add(btnSubmit);
        panelCenter.add(lblBlankTwo);
        panelCenter.add(lblBlankThree);
        panelCenter.add(checkBox);

        panelSouth.add(lblBlankFour);
        panelSouth.add(btnSignUp);
        panelSouth.add(lblBlankFive);

        //Implementing the action listener, so that the buttons are responsive
        btnSubmit.addActionListener(this);
        btnSignUp.addActionListener(this);
        checkBox.addActionListener(this);

        this.add(panelNorth, BorderLayout.NORTH);
        this.add(panelCenter, BorderLayout.CENTER);
        this.add(panelSouth, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(checkBox.isSelected()){
            password.setEchoChar((char)0);
        }
        else{
            password.setEchoChar('*');
        }

        if (e.getActionCommand().equals("Submit")) {

            //if statement to project error's in case of empty fields
            //char value to return passwordField
            char[] input = password.getPassword();

            if(userName.getText().isEmpty() || input.length == 0){
                if(userName.getText().isEmpty()){
                    lblErrorOne.setVisible(true);
                }else{
                    lblErrorOne.setVisible(false);
                }
                if(input.length == 0){
                    lblErrorTwo.setVisible(true);
                }
                else{
                    lblErrorTwo.setVisible(false);
                }
                return;
            }
            if(input.length < 5){
                JOptionPane.showMessageDialog(null, "Password entered is too short");
            }
            try{
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tableinfo?autoReconnect=true&useSSL=false", "root", "");
                String username = userName.getText();
                String password3 = password.getText();
                uN = username;

                Statement stm = con.createStatement();

                String sql= "SELECT * FROM user WHERE Student_Number='"+ username+"' and Password='"+password3+"'";
                ResultSet rs = stm.executeQuery(sql);

                if(rs.next()){
                    dispose();
                    Home home = new Home();
                    home.setGUI();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please type in the correct username or password");
                    lblErrorOne.setVisible(false);
                    lblErrorTwo.setVisible(false);
                }
                con.close();
            }catch(Exception ev){
                System.out.println(ev.getMessage());
            }

        } else if (e.getActionCommand().equals("Don't have an account? Register here.")) {
            this.dispose();
            SignUp signup = new SignUp();
            signup.setSignUp();

        }
    }
    public static void main(String[] args) {

        new Login().setLogin();

    }
}
