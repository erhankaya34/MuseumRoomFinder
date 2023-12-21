/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package museumvisitplanner;

/**
 *
 * @author erhan
 */
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class BSTApp extends JFrame implements ActionListener {

    private BST bst;
    private LinkedList<String> artAndArtistsNames;
    private JTextField textField;
    private JTextArea textArea;
    private JLabel imageLabel;

    public BSTApp() {
        super("BST App");

        bst = new BST();
        bst.insert(20);
        bst.insert(10);
        bst.insert(25);
        bst.insert(5);
        bst.insert(15);
        bst.insert(22);
        bst.insert(30);
        bst.insert(21);
        bst.insert(24);
        bst.insert(28);
        bst.insert(35);

        artAndArtistsNames = new LinkedList<String>();
        
        artAndArtistsNames.add("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        artAndArtistsNames.add("Hoca Ali Rıza - Lakeside (R102)");
        artAndArtistsNames.add("Şeker Ahmet Paşa - Pomegranates and Quinces (R102)");
        artAndArtistsNames.add("Osman Hamdi Bey - Turtle Trainer (R103)");
        artAndArtistsNames.add("İbrahim Çallı - Üsküdar (R201)");
        artAndArtistsNames.add("Bedri Rahmi Eyüboğlu - Tophane (R204)");
        artAndArtistsNames.add("Mahmut Cuda - Sara (R204)");
        artAndArtistsNames.add("Feyhaman Duran - Mr. Celaleddin Arif (R202)");
        artAndArtistsNames.add("Fikret Mualla - Jazz Orchestra (R203)");
        artAndArtistsNames.add("Nazmi Ziya Güran - Street View (R203)");
        artAndArtistsNames.add("Nuri İyem - Three Beauties (R203)");
        artAndArtistsNames.add("Namık İsmail - Woman Lying on Cedar (R201)");
        artAndArtistsNames.add("Hale Asaf - Self Portrait (R102)");
        artAndArtistsNames.add("Abidin Dino - Long Walk (R206)");
        artAndArtistsNames.add("İbrahim Balaban - Blend (R205)");
        artAndArtistsNames.add("Nurullah Berk - Ironing Woman (R103)");
        artAndArtistsNames.add("Avni Arbaş - Equestrian (R206)");
        artAndArtistsNames.add("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

        // UI Componentlerini tanımlıyoruz.
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        getContentPane().add(panel);
       
        JLabel label = new JLabel("Enter a room name ");

        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        JPanel roomNamePanel = new JPanel();
        roomNamePanel.setMaximumSize(new Dimension(200, 50));
        roomNamePanel.setLayout(new BorderLayout());
        textField = new JTextField();
        roomNamePanel.add(textField);

        panel.add(roomNamePanel);

        JPanel roomNumberPanel = new JPanel();
        roomNumberPanel.setMaximumSize(new Dimension(200, 80));
        roomNumberPanel.setLayout(new BorderLayout());


        JButton button = new JButton("Enter");
        button.addActionListener(this);
        roomNumberPanel.add(button);
        panel.add(roomNumberPanel);

        JButton displayArtButton = new JButton("Display Artworks");
        displayArtButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                String listElements = "";
                for (String element : artAndArtistsNames) {
                    listElements += element + "\n";
                }
                textArea.setText(listElements);
                
            }
        });

        displayArtButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(displayArtButton);

        //text area
        JPanel textAreaPanel = new JPanel();
        textAreaPanel.setSize(new Dimension(500, 100));
        textAreaPanel.setLayout(new BorderLayout());
        textArea = new JTextArea(10, 20);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        textAreaPanel.add(scrollPane, BorderLayout.CENTER);
        panel.add(textAreaPanel);

        BufferedImage image = null;
        String imageUrl = "https://drive.google.com/uc?export=download&id=1hMFUv5Ace4Ub6FxcWxm7ZJLelJRkTa1x";

        try {
            String userInput = textField.getText().trim();
            imageUrl = "https://drive.google.com/uc?export=download&id=1hMFUv5Ace4Ub6FxcWxm7ZJLelJRkTa1x";
            
            URL url = new URL(imageUrl);

            image = ImageIO.read(url);
           
            int newWidth = (int) (image.getWidth() * 0.8);
            int newHeight = (int) (image.getHeight() * 0.8);
            Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();
            g2d.drawImage(scaledImage, 0, 0, null);
            g2d.dispose();
        
        } catch (IOException e) {
            
            

        }
        
        
        imageLabel = new JLabel(new ImageIcon(image));
        
        
        JPanel imagePanel = new JPanel();
        imagePanel.add(imageLabel);
        
      
        panel.add(imagePanel);
        
 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String roomName = textField.getText();
        int roomNumber = getRoomNumber(roomName);
        if (roomNumber == -1) {
            textArea.append("Room not found\n");
            
            

            

        } else {
            List<String> path = bst.getPathTo(roomNumber);
            textArea.append("Path to room " + roomName + ": " + String.join(" -> ", path) + " | END\n");

            // Load the new image based on the room name
            String imageUrl = getImageUrl(roomName);
            BufferedImage image = null;
            try {
                URL url = new URL(imageUrl);
                image = ImageIO.read(url);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

           
            if (image != null) {
                int newWidth = (int) (image.getWidth() * 0.8);
                int newHeight = (int) (image.getHeight() * 0.8);
                Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = image.createGraphics();
                g2d.drawImage(scaledImage, 0, 0, null);
                g2d.dispose();
                imageLabel.setIcon(new ImageIcon(image));
            }
        }
        textField.setText("");
    }

    private String getImageUrl(String roomName) {
        switch (roomName) {
            case "F1":
                return "https://drive.google.com/uc?export=download&id=1hMFUv5Ace4Ub6FxcWxm7ZJLelJRkTa1x";
            case "F2":
                return "https://drive.google.com/uc?export=download&id=1uSQhGc-D5An21rBOSlLYzodYkGTktVZi";
            case "R101":
                return "https://drive.google.com/uc?export=download&id=1mulq0Tv63Da3B-lJh4zdnSobQWfXfNpR";
            case "R102":
                return "https://drive.google.com/uc?export=download&id=1e45ypwc-JQSGNOv9HFFOch1Uij48arWW";
            case "R103":
                return "https://drive.google.com/uc?export=download&id=1418ZHRdfb9uSHoSKHjPQs_3SK1vDd1Sp";
            case "R201":
                return "https://drive.google.com/uc?export=download&id=1TONjYIB6ME0Tg6z4-dDeA3hS06dyd6l5";
            case "R202":
                return "https://drive.google.com/uc?export=download&id=1xwra1PI3LG1gUfw-kM69Y-50ISLbG-iQ";
            case "R203":
                return "https://drive.google.com/uc?export=download&id=19PHZdeOATdqRmM2xJwucF2lQFNo3RCsT";
            case "R204":
                return "https://drive.google.com/uc?export=download&id=1FwLPoeFwfnsczSErqXWxCkbgzNqmDBMh";
            case "R205":
                return "https://drive.google.com/uc?export=download&id=1hZPqOqR0YVM-f1K4lrnN8M8auhK8HHBG";
            case "R206":
                return "https://drive.google.com/uc?export=download&id=1XxFbReegiZCa8QTP0wVb97tOaDw7olt0";
            default:
                return null;
        }
    }
    
    

    private int getRoomNumber(String roomName) {
        switch (roomName) {
            case "F1":
                return 20;
            case "R101":
                return 10;
            case "F2":
                return 25;
            case "R102":
                return 5;
            case "R103":
                return 15;
            case "R201":
                return 22;
            case "R204":
                return 30;
            case "R202":
                return 21;
            case "R203":
                return 24;
            case "R205":
                return 28;
            case "R206":
                return 35;
            default:
                return -1;
        }
        
    }

    public static void main(String[] args) {
        new BSTApp();
    }

}