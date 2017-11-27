/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Jesse
 */
public class GUI extends JFrame{

    private BookStore b = new BookStore();
    
    public GUI(){
        super("Bookstore");
        this.setSize(new Dimension(800,600));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2,2));
        
       
        
        JPanel topLeft = new JPanel();
        topLeft.setLayout(new GridLayout(2,2));
        JButton list = new JButton("List Books");
        JButton ua = new JButton("Unique authors");
        JButton ap = new JButton("Average price");
        JButton tw = new JButton("Textbook-Workbook pairs");
        topLeft.add(list);
        topLeft.add(ua);
        topLeft.add(ap);
        topLeft.add(tw);
        
        JPanel topRight = new JPanel();
        topRight.setLayout(new GridLayout(3,1));
        JButton addBook = new JButton("Add Book");
        JButton addtBook = new JButton("Add Textbook");
        JButton addwBook = new JButton("Add Workbook");
        topRight.add(addBook);
        topRight.add(addtBook);
        topRight.add(addwBook);
        
        JPanel bottomLeft = new JPanel();
        bottomLeft.setLayout(new GridLayout(1,1));
        JTextArea area = new JTextArea(1, 1);
        bottomLeft.add(area);
        area.setText("Please select an option\n");
        
        JPanel bottomRight = new JPanel();
        bottomRight.setLayout(new GridLayout(8,2));
        JTextField field1 = new JTextField(1);
        JTextField field2 = new JTextField(1);
        JTextField field3 = new JTextField(1);
        JTextField field4 = new JTextField(1);
        JTextField field5 = new JTextField(1);
        JTextField field6 = new JTextField(1);
        JTextField field7 = new JTextField(1);
        JTextField field8 = new JTextField(1);
        JLabel titlel = new JLabel("Title");
        JLabel authorl = new JLabel("Author");
        JLabel yearl = new JLabel("Year published");
        JLabel isbnl = new JLabel("ISBN");
        JLabel pricel = new JLabel("Price");
        JLabel subjectl = new JLabel("Subject(Textbook only)");
        JLabel wisbnl = new JLabel("Workbook ISBN(Textbook only)");
        JLabel nproblemsl = new JLabel("Num Problems(Workbook only)");
        bottomRight.add(titlel);
        bottomRight.add(field1);
        bottomRight.add(authorl);
        bottomRight.add(field2);
        bottomRight.add(yearl);
        bottomRight.add(field3);
        bottomRight.add(isbnl);
        bottomRight.add(field4);
        bottomRight.add(pricel);
        bottomRight.add(field5);
        bottomRight.add(subjectl);
        bottomRight.add(field6);
        bottomRight.add(wisbnl);
        bottomRight.add(field7);
        bottomRight.add(nproblemsl);
        bottomRight.add(field8);
        
        
        
        
        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener((ActionEvent ae) -> {
            setVisible(false);
            dispose();
        });
        JMenuItem save = new JMenuItem("Save As...");
        save.addActionListener((ActionEvent ae) -> {
            String output = (String)JOptionPane.showInputDialog(mainPanel,"Sava As", "Save", JOptionPane.PLAIN_MESSAGE, null, null, null);
            
            String result = b.save(output);
            area.setText(result);
        });
        JMenuItem load = new JMenuItem("Load File...");
        load.addActionListener((ActionEvent ae) -> {
            String output = (String)JOptionPane.showInputDialog(mainPanel,"Load File", "Load", JOptionPane.PLAIN_MESSAGE, null, null, null);
         
            String result = b.load(output);
            area.setText(result);
        });
        
        JMenu menu = new JMenu("File");
        menu.add(quit);
        menu.add(save);
        menu.add(load);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        
        this.setJMenuBar(menuBar);
        
        
        addBook.addActionListener((ActionEvent ae) -> {
            String add = b.add("Book", field1.getText(), field2.getText(), field3.getText(), field4.getText(), field5.getText(), "", "", "");
            if(add == null)
            {
                area.setText("Book added\n");
                field1.setText("");
                field2.setText("");
                field3.setText("");
                field4.setText("");
                field5.setText("");
                field6.setText("");
                field7.setText("");
                field8.setText("");
            }
            else if(add.equalsIgnoreCase("double"))
            {
                area.setText("That book already exists");
            }
            else
            {
                area.setText("Invalid ");
                area.append(add);
                area.append(" input.");
            }
        });
        
        addtBook.addActionListener((ActionEvent ae) -> {
            String add = b.add("Textbook", field1.getText(), field2.getText(), field3.getText(), field4.getText(), field5.getText(), field6.getText(), field7.getText(), "");
            if(add == null)
            {
                area.setText("Book added\n");
                field1.setText("");
                field2.setText("");
                field3.setText("");
                field4.setText("");
                field5.setText("");
                field6.setText("");
                field7.setText("");
                field8.setText("");
            }
            else if(add.equalsIgnoreCase("double"))
            {
                area.setText("That book already exists");
            }
            else
            {
                area.setText("Invalid ");
                area.append(add);
                area.append(" input.");
            }
        });
        addwBook.addActionListener((ActionEvent ae) -> {
            String add = b.add("Workbook", field1.getText(), field2.getText(), field3.getText(), field4.getText(), field5.getText(), "", "", field8.getText());
            if(add == null)
            {
                area.setText("Book added\n");
                field1.setText("");
                field2.setText("");
                field3.setText("");
                field4.setText("");
                field5.setText("");
                field6.setText("");
                field7.setText("");
                field8.setText("");
            }
            else if(add.equalsIgnoreCase("double"))
            {
                area.setText("That book already exists");
            }
            else
            {
                area.setText("Invalid ");
                area.append(add);
                area.append(" input.");
            }
        });
        
        list.addActionListener((ActionEvent ar) -> {
            area.setText(b.toString());
        });
        
        ua.addActionListener((ActionEvent ar) -> {
            area.setText(b.authors());
        });
        
        ap.addActionListener((ActionEvent ar) -> {
            area.setText(b.avgPrice());
        });
        
        tw.addActionListener((ActionEvent ar) -> {
            area.setText(b.pairs());
        });
        
        mainPanel.add(topLeft);
        mainPanel.add(topRight);
        mainPanel.add(bottomLeft);
        mainPanel.add(bottomRight);
        this.add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        area.setEditable(false);
        area.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(area);
        bottomLeft.add(scroll);
        

        
      
    }
    
}
