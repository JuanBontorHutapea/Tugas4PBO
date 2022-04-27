package jdbc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame{

    Connector connector = new Connector();

    //DEKLARASI KOMPONEN
    JFrame window = new JFrame("Login");

    JLabel lusername = new JLabel("Username  ");
    JTextField tfusername = new JTextField();
    JLabel lpassword = new JLabel("Password ");
    JTextField tfpassword = new JTextField();


    JButton btnLogin = new JButton("Login");
    JButton btnRegister = new JButton("Register");

    public Login() {
        window.setLayout(null);
        window.setSize(550,200);
        //  window.setDefaultCloseOperation(3);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setResizable(false);


        // setDefaultCloseOperation(EXIT_ON_CLOSE);

//ADD COMPONENT
        window.add(lusername);
        window.add(tfusername);
        window.add(tfpassword);
        window.add(lpassword);
        window.add(btnLogin);
        window.add(btnRegister);



//LABEL
        lusername.setBounds(5, 30, 120, 20);
        lpassword.setBounds(5,60,120,20);

//TEXTFIELD
        tfusername.setBounds(110, 30, 120, 20);
        tfpassword.setBounds(110, 60, 120, 20);


//BUTTON PANEL
        btnLogin.setBounds(250, 30, 90, 20);
        btnRegister.setBounds(250,60,90,20);


        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int jumlah = 0;
                String username = tfusername.getText();
                String password = tfpassword.getText();
                Boolean berhasilLogin = false;
                try {
                    String data[][] = new String[100][3];
                    String query = "select * from users";
                    PreparedStatement pstmtSelect = connector.koneksi.prepareStatement(query);
                    connector.statement = connector.koneksi.createStatement();
                    ResultSet resultSet = pstmtSelect.executeQuery(query);
                    while (resultSet.next()) {
                        data[jumlah][0] = String.valueOf(resultSet.getInt("id"));
                        data[jumlah][1] = resultSet.getString("username");
                        data[jumlah][2] = resultSet.getString("password");
                        if((data[jumlah][1].equals(username)) && (data[jumlah][2].equals(password)));
                            berhasilLogin = true;
                            break;
                        }
                        jumlah++;

                    if(berhasilLogin == true) {
                        JOptionPane.showMessageDialog(window,"Login Berhasil!");
                    } else {
                        JOptionPane.showMessageDialog(window,"Login Gagal!");
                    }

                } catch (Exception exception){
                    System.out.println(exception.getMessage());
                }
            }
        });

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Registrasi registrasi = new Registrasi();
            }
        });
    }



    public String getusername(){
        return tfusername.getText();
    }


    public String getpassword() {
        return tfpassword.getText();
    }

}