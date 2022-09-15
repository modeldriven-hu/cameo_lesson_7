package hu.modeldriven.cameo;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class SimpleCopyApplication extends JFrame {
    private static final long serialVersionUID = 1L;

    private JLabel label;

    public SimpleCopyApplication() {
        setLayout(new FlowLayout());

        final JTextField textField = new JTextField("");
        textField.setText("Random gibberish");

        JButton button = new JButton("Copy");
        button.setName("myButton");

        this.label = new JLabel("");
        this.label.setName("myLabel");

        button.addActionListener((event) -> label.setText(textField.getText()));

        add(textField);
        add(button);
        add(label);

        pack();
    }

    public void setData(WhateverRecord whateverRecord){
        this.label.setText(whateverRecord.getName());
    }

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new SimpleCopyApplication();
                frame.setVisible(true);
            }
        });
    }
}
