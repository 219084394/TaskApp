package za.ac.cput;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;
import javax.swing.*;

public class SignUp extends JFrame implements ActionListener{

    private JPanel panelNorth, panelCenter, panelSouth;
    private JLabel lblUsername, lblPassword, lblPasswordConfirm, lblImage, lblFirstname, lblLastname, lblEmail;
    private JTextField userName, firstName, lastName, email;
    private JPasswordField password, confirmPassword;
    private JButton btnSubmit, btnLogin;
    private ImageIcon image;
    private JLabel lblErrorOne, lblErrorTwo, lblErrorThree, lblBlank, lblBlankTwo, lblBlankThree, lblBlankFour, lblBlankFive, lblErrorFour,
            lblErrorFive, lblErrorSix;
    private JCheckBox checkBox;
    protected static String nUser;

    public SignUp(){
        super("Sign Up");
        panelNorth = new JPanel();
        panelCenter = new JPanel();
        panelSouth = new JPanel();
        lblImage = new JLabel("" ,SwingConstants.CENTER);
        lblUsername = new JLabel("Student Number: ", SwingConstants.RIGHT);
        lblPassword = new JLabel("Password: ", SwingConstants.RIGHT);
        lblPasswordConfirm = new JLabel("Confirm Password: ", SwingConstants.RIGHT);
        userName = new JTextField();
        password = new JPasswordField();
        confirmPassword = new JPasswordField();
        btnSubmit = new JButton("Submit");
        btnLogin = new JButton("Already have an account? Login here.");
        image = new ImageIcon("SignUp.png");
        lblErrorOne = new JLabel("*Student Number Required");
        lblErrorTwo = new JLabel("*Password Required");
        lblErrorThree = new JLabel("*Password Required");
        lblErrorFour = new JLabel("*First Name Required");
        lblErrorFive = new JLabel("*Last Name Required");
        lblErrorSix = new JLabel("*Email Required");
        lblBlank = new JLabel("");
        lblBlankTwo = new JLabel("");
        lblBlankThree = new JLabel("");
        lblBlankFour = new JLabel("");
        lblBlankFive = new JLabel("");
        checkBox = new JCheckBox("Show Password");
        firstName = new JTextField();
        lastName = new JTextField();
        email = new JTextField();
        lblFirstname = new JLabel("First Name: ", SwingConstants.RIGHT);
        lblLastname = new JLabel("Last Name: ", SwingConstants.RIGHT);
        lblEmail = new JLabel("Email: ", SwingConstants.RIGHT);
    }
    public void setSignUp(){
        panelNorth.setLayout(new GridLayout(1,1));
        panelCenter.setLayout(new GridLayout(8,3, 3, 4));
        panelSouth.setLayout(new GridLayout(1,1));

        panelNorth.setBorder(BorderFactory.createEmptyBorder(25,25,5,25));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(50,25,25,25));
        panelSouth.setBorder(BorderFactory.createEmptyBorder(0,25,25,25));

        //make error messages red
        lblErrorOne.setForeground(Color.RED);
        lblErrorTwo.setForeground(Color.RED);
        lblErrorThree.setForeground(Color.RED);
        lblErrorFour.setForeground(Color.RED);
        lblErrorFive.setForeground(Color.RED);
        lblErrorSix.setForeground(Color.RED);

        //remove border of show button
        checkBox.setContentAreaFilled(false);

        //hides password
        password.setEchoChar('*');
        confirmPassword.setEchoChar('*');

        //adding image to GUI
        lblImage.setIcon(image);

        //remove border around button
        btnLogin.setContentAreaFilled(false);
        btnLogin.setBorder(null);
        btnLogin.setBorderPainted(false);

        //setting the GUI Background Color
        panelNorth.setBackground(new Color(0,29,64));
        panelCenter.setBackground(new Color(0,29,64));
        panelSouth.setBackground(new Color(0,29,64));

        lblUsername.setForeground(Color.white);
        lblFirstname.setForeground(Color.white);
        lblLastname.setForeground(Color.white);
        lblEmail.setForeground(Color.white);
        lblPassword.setForeground(Color.white);
        lblPasswordConfirm.setForeground(Color.white);
        checkBox.setForeground(Color.white);
        btnLogin.setForeground(Color.white);

        btnSubmit.setBackground(new Color(250, 134, 0));
        btnSubmit.setForeground(Color.white);

        //Error Handling set to invisible
        lblErrorOne.setVisible(false);
        lblErrorTwo.setVisible(false);
        lblErrorThree.setVisible(false);
        lblErrorFour.setVisible(false);
        lblErrorFive.setVisible(false);
        lblErrorSix.setVisible(false);

        //Adding necessary components to GUI
        panelNorth.add(lblImage);

        panelCenter.add(lblUsername);
        panelCenter.add(userName);
        panelCenter.add(lblErrorOne);
        panelCenter.add(lblFirstname);
        panelCenter.add(firstName);
        panelCenter.add(lblErrorFour);
        panelCenter.add(lblLastname);
        panelCenter.add(lastName);
        panelCenter.add(lblErrorFive);
        panelCenter.add(lblEmail);
        panelCenter.add(email);
        panelCenter.add(lblErrorSix);
        panelCenter.add(lblPassword);
        panelCenter.add(password);
        panelCenter.add(lblErrorTwo);
        panelCenter.add(lblPasswordConfirm);
        panelCenter.add(confirmPassword);
        panelCenter.add(lblErrorThree);
        panelCenter.add(lblBlank);
        panelCenter.add(btnSubmit);
        panelCenter.add(lblBlankTwo);
        panelCenter.add(lblBlankThree);
        panelCenter.add(checkBox);

        panelSouth.add(lblBlankFour);
        panelSouth.add(btnLogin);
        panelSouth.add(lblBlankFive);

        //Implementing the action listener, so that the buttons are responsive
        btnSubmit.addActionListener(this);
        btnLogin.addActionListener(this);
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

        //view password
        if(checkBox.isSelected()){
            password.setEchoChar((char)0);
            confirmPassword.setEchoChar((char)0);
        }
        else{
            password.setEchoChar('*');
            confirmPassword.setEchoChar('*');
        }

        if (e.getActionCommand().equals("Submit")) {

            //char value to return passwordField
            char[] input = password.getPassword();
            char[] confirmation = confirmPassword.getPassword();

            //String to compare passwords
            String password1 = Arrays.toString(password.getPassword());
            String password2 = Arrays.toString(confirmPassword.getPassword());

            //if statement to project error's in case of empty fields
            if(userName.getText().isEmpty() || input.length == 0 || confirmation.length == 0 || firstName.getText().isEmpty()
            || lastName.getText().isEmpty() || email.getText().isEmpty()){
                if(userName.getText().isEmpty()){
                    lblErrorOne.setVisible(true);
                }else{
                    lblErrorOne.setVisible(false);
                }
                if(firstName.getText().isEmpty()){
                    lblErrorFour.setVisible(true);
                }else{
                    lblErrorFour.setVisible(false);
                }
                if(lastName.getText().isEmpty()){
                    lblErrorFive.setVisible(true);
                }else{
                    lblErrorFive.setVisible(false);
                }
                if(email.getText().isEmpty()){
                    lblErrorSix.setVisible(true);
                }else{
                    lblErrorSix.setVisible(false);
                }
                if(input.length == 0){
                    lblErrorTwo.setVisible(true);
                }
                else{
                    lblErrorTwo.setVisible(false);
                }
                if(confirmation.length == 0){
                    lblErrorThree.setVisible(true);
                }
                else{
                    lblErrorThree.setVisible(false);
                }
                return;
            }
            if(input.length < 5 || confirmation.length < 5){
                JOptionPane.showMessageDialog(null, "Password needs to be 5 characters or more");
                return;
            }
            if(input.length == confirmation.length && password1.equals(password2)){
                try{
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tableinfo?autoReconnect=true&useSSL=false", "root", "");
                    String un = userName.getText();
                    String fn = firstName.getText();
                    String ln = lastName.getText();
                    String em = email.getText();
                    String pd = password.getText();
                    nUser = un;

                    Statement stm = con.createStatement();

                    String sql= "INSERT INTO user" + "(Student_Number, FirstName, LastName, Email, Password)" + "VALUES('" +un+"','" +fn+"','" +ln+"','" +em+"', '"+pd+"')";
                    stm.executeUpdate(sql);
                    dispose();
                    Home home = new Home();
                    home.setGUI();
                    con.close();

                }catch(Exception ev){
                    System.out.println(ev.getMessage());
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Password does not match");
            }

        } else if (e.getActionCommand().equals("Already have an account? Login here.")) {
            this.dispose();
            Login login = new Login();
            login.setLogin();

        }
    }
    public static void main(String[] args) {

        new SignUp().setSignUp();
    }
}
