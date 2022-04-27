package jdbc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Registrasi extends JFrame{
    public String no_mk, nama_mk, kelas, nip;

    Connector connector = new Connector();

    //DEKLARASI KOMPONEN
    JFrame window = new JFrame("Registrasi");

    JLabel lusername = new JLabel("Username  ");
    JTextField tfusername = new JTextField();
    JLabel lpassword = new JLabel("Password ");
    JTextField tfpassword = new JTextField();


    JButton btnTambahPanel = new JButton("Tambah");
    JButton btnLogin = new JButton("Login");

    public Registrasi() {
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
        window.add(btnTambahPanel);
        window.add(btnLogin);



//LABEL
        lusername.setBounds(5, 30, 120, 20);
        lpassword.setBounds(5,60,120,20);

//TEXTFIELD
        tfusername.setBounds(110, 30, 120, 20);
        tfpassword.setBounds(110, 60, 120, 20);


//BUTTON PANEL
        btnTambahPanel.setBounds(250, 30, 90, 20);
        btnLogin.setBounds(250,60,90,20);


        btnTambahPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int jumlah = 0;
                String username = tfusername.getText();
                Boolean usernameSama = false;
                try {
                    String data[][] = new String[100][3];
                    String querySelect = "select * from users";
                    PreparedStatement pstmtSelect = connector.koneksi.prepareStatement(querySelect);
                    connector.statement = connector.koneksi.createStatement();
                    ResultSet resultSet = pstmtSelect.executeQuery(querySelect);
                    while (resultSet.next()) {
                        data[jumlah][0] = String.valueOf(resultSet.getInt( "id"));
                        data[jumlah][1] = resultSet.getString( "username");
                        data[jumlah][2] = resultSet.getString("password");
                        if(data[jumlah] [1].equals(username)) {
                            usernameSama = true;
                            break;
                        }
                        jumlah++;
                    }

                    if(usernameSama == true) {
                        JOptionPane.showMessageDialog(window,"Username Telah Digunakan!");
                        tfusername.setText( "");
                        tfpassword.setText( "");
                    } else {
                        String queryInsert = "insert into users(username, password) values (?,?)";
                        PreparedStatement pstmtInsert = connector.koneksi.prepareStatement(queryInsert);
                        pstmtInsert.setString(1, getusername());
                        pstmtInsert.setString(2, getpassword());
                        connector.statement = connector.koneksi.createStatement();
                        pstmtInsert.executeUpdate();
                        JOptionPane.showMessageDialog(window, "Registrasi Berhasil!");
                    }

                } catch (Exception exception){
                    System.out.println(exception.getMessage());
                }
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Login login = new Login();
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