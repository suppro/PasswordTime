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
    private static String pathToFile = "/home/anon/IdeaProjects/PasswordTime/src/com/company/pass/Pass.txt";
    private DefaultComboBoxModel<String> cbModel;

    public Frame() throws IOException {
        super("PasswordTime");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ArrayList<String> listFile = FileList();

        cbModel = new DefaultComboBoxModel<String>();
        for (int i = 0; i < listFile.size(); i++)
            cbModel.addElement(listFile.get(i));
        JComboBox<String> cbFirst = new JComboBox<String>(cbModel);
        JButton button1 = new JButton("Принять");
        JButton button2 = new JButton("Добавить");
        JLabel text = new JLabel("Выбрать пароль");
        JLabel text1 = new JLabel("Ввести пароль");
        JLabel text3 = new JLabel("Добавить пароль");
        JTextField smallField = new JTextField();
        JTextField smallField1 = new JTextField();
        JPanel contents = new JPanel();

        contents.setLayout(null);
        contents.add(text);
        contents.add (cbFirst);
        contents.add(button1);
        contents.add(text1);
        contents.add(text3);
        contents.add(smallField1);
        contents.add(smallField);

        text.setBounds(5, 5,300,20);
        cbFirst.setBounds(5, 30,300,20);
        button1.setBounds(310, 30, 110, 20);
        text3.setBounds(5, 60,300,20);
        smallField1.setBounds(5, 85, 415, 20);
        button2.setBounds(310, 85, 110, 20);
        text1.setBounds(5, 115,300,20);
        smallField.setBounds(5, 140, 415,20);
        smallField.setEnabled(false);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                smallField.setEnabled(true);
                cbFirst.setEnabled(false);
                smallField1.setEnabled(false);
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

        smallField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String s = "\n" + smallField1.getText();
                try {
                    FileWriter writer = new FileWriter(pathToFile, true);
                    BufferedWriter bufferWriter = new BufferedWriter(writer);
                    bufferWriter.write(s);
                    bufferWriter.close();
                    cbFirst.addItem(s);
                }
                catch (IOException e) {
                    JOptionPane.showMessageDialog(Frame.this, e);
                }
                JOptionPane.showMessageDialog(Frame.this, "Пароль добавлен");
                smallField1.setText(null);
            }
        });

        setContentPane(contents);
        setSize(435, 210);
        setVisible(true);
    }
    public ArrayList<String> FileList() {
        ArrayList<String> listFile = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile)))) {
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

