package com.company.pass;

import javax.swing.JFrame;
import java.io.*;

public class Main {
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
    }
}
