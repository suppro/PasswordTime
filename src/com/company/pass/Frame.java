package com.company.pass;

import java.awt.event.*;
import java.io.*;
import java.time.ZonedDateTime;
import java.util.*;

import javax.swing.*;

public class Frame extends JFrame {
    private static final String LENGTH_PASS = "0000000000000000000000";
    private static String changePass = "";
    private static long time = 0;
    // Массив элементов списка
    private DefaultComboBoxModel<String> cbModel;

    public Frame() throws IOException {
        super("PasswordTime");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        String line = null;
        ArrayList<String> listFile = FileList();
        // Модель данных списка
        cbModel = new DefaultComboBoxModel<String>();
        for (int i = 0; i < listFile.size(); i++)
            cbModel.addElement(listFile.get(i));
        JComboBox<String> cbFirst = new JComboBox<String>(cbModel);

        JButton button1 = new JButton("Принять");
        JLabel text = new JLabel("Выбрать пароль");
        JLabel text1 = new JLabel("Ввести пароль");
        JLabel text2 = new JLabel("00:00:00");
        JTextField smallField = new JTextField();
        JPanel contents = new JPanel();

        contents.setLayout(null);
        contents.add(text);
        contents.add (cbFirst);
        contents.add(button1);
        contents.add(text1);
        //contents.add(text2);
        contents.add(smallField);

        text.setBounds(5, 5,300,20);
        cbFirst.setBounds(5, 30,300,20);
        button1.setBounds(310, 30, 110, 20);
        text1.setBounds(5, 55,300,20);
        smallField.setBounds(5, 80, 415,20);
        smallField.setEnabled(false);
        //text2.setBounds(150, 120, 200, 16);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                smallField.setEnabled(true);
                cbFirst.setEnabled(false);
                changePass = (String)cbFirst.getSelectedItem();
            }
        });

        smallField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time = ZonedDateTime.now().toInstant().toEpochMilli() - time;
                if(changePass.equals(smallField.getText()))
                    JOptionPane.showMessageDialog(Frame.this, "Пароль введен за " + time + " мс");
                else
                    JOptionPane.showMessageDialog(Frame.this, "Пароль не совпадает");
            }
        });
        smallField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(smallField.getText().length() == 1)
                    time = ZonedDateTime.now().toInstant().toEpochMilli();
            }
        });
        setContentPane(contents);
        setSize(450, 200);
        setVisible(true);
    }
    public ArrayList<String> FileList() {
        ArrayList<String> listFile = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/home/anon/IdeaProjects/PasswordTime/src/com/company/pass/Pass.txt")))) {
            String strLine;
            while ((strLine = br.readLine()) != null) {
                listFile.add(strLine);
            }
        } catch (IOException e) {
            System.out.println("Ошибка");
        }
        return listFile;
    }
}

