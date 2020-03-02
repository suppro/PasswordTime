package com.company.pass;

import javax.swing.JFrame;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void AddPass(){
        System.out.println("Enter new pass");
        Scanner in = new Scanner(System.in);
        String newPass = in.nextLine();
        try(FileWriter writer = new FileWriter("Pass.txt", true))
        {
            writer.write(newPass);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                try {
                    new Frame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        /*

        Menu(passList);
        long time = 0;
        String passwordIn = "";
        String password = "6a621819b";

        Scanner in = new Scanner(System.in);
        System.out.println("Enter then input password");
        if((System.in.read()) > -1) {
            time = ZonedDateTime.now().toInstant().toEpochMilli();
            passwordIn = in.nextLine();
            time = ZonedDateTime.now().toInstant().toEpochMilli() - time;
        }
        if(passwordIn.equals(password)){
            System.out.print("Пароль введен за " + time + " мс");
        }
        else {
            System.out.println(passwordIn);
            System.out.println("Пароль введен неверно, закрытие программы.");
            System.exit ( 0 );
        }

         */
    }
}
