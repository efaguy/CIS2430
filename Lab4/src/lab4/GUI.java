/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Eric
 */
public class GUI extends JFrame {

    private JTextField textField;
    private JTextArea textArea;
    private int state = 0;
    private String title;
    private String author;
    private String ISBN;
    private String sYearPubed;
    private int yearPubed;
    private String sPrice;
    private double price;
    private String workbookISBN;
    private String subject;
    private String problems;
    private int numProblems;
    private String choice;
    private String type;
    private String output;
    private BookStore b = new BookStore();

    public GUI() {
        super("Bookstore");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        /* 
            TextField's produces an ActionEvent is triggered when focus is in 
            the text field and enter/return is pressed (not when letters are 
            added).
         */
        textField = new JTextField(25);
        textField.addActionListener((ActionEvent ae) -> {
            //textArea.setText(textField.getText());
            
            int c = 0;
            switch (state) {
                case 0:
                    choice = textField.getText();
                    textField.setText("");
                    try
                    {
                        c = Integer.parseInt(choice);
                    }
                    catch(NumberFormatException e)
                    {
                        textArea.setText("Please enter a number\n");
                        textArea.append("1. Add a book\n2. Print out information on each Book\n3. Print out all unique authors\n4. Print out the average book price, along with the total number of Books\n5. Print out all Textbook-Workbook pairs in the inventory\n6. Save the state of the inventory to a file\n7. Load the state of the inventory from a file\n8. Quit");
                        
                        state = 0;
                    }   
            switch (c) {
                case 1:
                    textArea.setText("Would you like to add a textbook, or workbook");
                    state = 1;
                    break;
                case 2:
                    textArea.setText(b.toString());
                    textArea.append("Press enter to return to main menu");
                    state = 10;
                    break;
                case 3:
                    textArea.setText(b.authors());
                    textArea.append("\nPress enter to return to main menu");
                    state = 10;
                    break;
                case 4:
                    textArea.setText(b.avgPrice());
                    textArea.append("\nPress enter to return to main menu");
                    state = 10;
                    break;
                case 5:
                    textArea.setText(b.pairs());
                    textArea.append("\n\nPress enter to return to main menu");
                    state = 10;
                    break;
                case 6:
                    textArea.setText("What filename do you want to save to: ");
                    state = 11;
                    break;
                case 7:
                    textArea.setText("What filename do you want to load from: ");
                    state = 12;
                    break;
                case 8:
                    setVisible(false);
                    dispose();
                    break;
                default:
                    textArea.setText("Please enter a valid choice\n");
                    textArea.append("1. Add a book\n2. Print out information on each Book\n3. Print out all unique authors\n4. Print out the average book price, along with the total number of Books\n5. Print out all Textbook-Workbook pairs in the inventory\n6. Save the state of the inventory to a file\n7. Load the state of the inventory from a file\n8. Quit");
                    state = 0;
                    break;
            }
break;
                case 1:
                    type = textField.getText();
                    textField.setText("");
                    if(!type.equalsIgnoreCase("textbook") && !type.equalsIgnoreCase("t") && !type.equalsIgnoreCase("workbook") && !type.equalsIgnoreCase("w"))
                    {
                        textArea.setText("Please enter either a textbook(t) or workbook(w).");
                        state = 1;
                    }
                    else
                    {
                        textArea.setText("Please enter the following information\n");
                        textArea.append("Title: ");
                        state = 2;
                    }   break;
                case 2:
                    title = textField.getText();
                    textField.setText("");
                    if(title.isEmpty())
                    {
                        textArea.setText("Please enter the following information\n");
                        textArea.append("Title: ");
                        textArea.append("\nPlease enter a title.\n");
                        state = 2;
                    }
                    else
                    {
                        textArea.setText("Please enter the following information\n");
                        textArea.append("Title: ");
                        textArea.append(title);
                        textArea.append("\nAuthor: ");
                        state = 3;
                    }   break;
                case 3:
                    author = textField.getText();
                    textField.setText("");
                    if(author.isEmpty())
                    {
                        textArea.setText("Please enter the following information\n");
                        textArea.append("Title: ");
                        textArea.append(title);
                        textArea.append("\nAuthor: ");
                        textArea.append("\nPlease enter a author.\n");
                        state = 3;
                    }
                    else
                    {
                        textArea.setText("Please enter the following information\n");
                        textArea.append("Title: ");
                        textArea.append(title);
                        textArea.append("\nAuthor: ");
                        textArea.append(author);
                        textArea.append("\nYear Published: ");
                        state = 4;
                    }   break;
                case 4:
                    sYearPubed = textField.getText();
                    textField.setText("");
                    try
                    {
                        yearPubed = Integer.parseInt(sYearPubed);
                        if(yearPubed < -2600 || yearPubed > 2017)
                        {
                            textArea.setText("Please enter the following information\n");
                            textArea.append("Title: ");
                            textArea.append(title);
                            textArea.append("\nAuthor: ");
                            textArea.append(author);
                            textArea.append("\nYear Published: ");
                            textArea.append("\nPlease enter a valid year.\n");
                            state = 4;
                        }
                        else
                        {
                            textArea.setText("Please enter the following information\n");
                            textArea.append("Title: ");
                            textArea.append(title);
                            textArea.append("\nAuthor: ");
                            textArea.append(author);
                            textArea.append("\nYear Published: ");
                            textArea.append(sYearPubed);
                            textArea.append("\nISBN: ");
                            state = 5;
                        }
                    }
                    catch(NumberFormatException e)
                    {
                        textArea.setText("Please enter the following information\n");
                        textArea.append("Title: ");
                        textArea.append(title);
                        textArea.append("\nAuthor: ");
                        textArea.append(author);
                        textArea.append("\nYear Published: ");
                        textArea.append("\nPlease enter a valid year.\n");
                        state = 4;
                    }   break;
                case 5:
                    ISBN = textField.getText();
                    textField.setText("");
                    for (int x1 = 0; x1 < ISBN.length(); x1++) {
                        if (!Character.isDigit(ISBN.charAt(x1))) {
                            textArea.setText("Please enter the following information\n");
                            textArea.append("Title: ");
                            textArea.append(title);
                            textArea.append("\nAuthor: ");
                            textArea.append(author);
                            textArea.append("\nYear Published: ");
                            textArea.append(sYearPubed);
                            textArea.append("\nISBN: ");
                            textArea.append("\nPlease enter a valid ISBN.\n");
                            state = 5;
                            return;
                        }
                    }
                    if(!(ISBN.length() == 10 || ISBN.length() == 13))
                    {
                        textArea.setText("Please enter the following information\n");
                        textArea.append("Title: ");
                        textArea.append(title);
                        textArea.append("\nAuthor: ");
                        textArea.append(author);
                        textArea.append("\nYear Published: ");
                        textArea.append(sYearPubed);
                        textArea.append("\nISBN: ");
                        textArea.append("\nPlease enter a valid ISBN.\n");
                        state = 5;
                    }
                    else
                    {
                        textArea.setText("Please enter the following information\n");
                        textArea.append("Title: ");
                        textArea.append(title);
                        textArea.append("\nAuthor: ");
                        textArea.append(author);
                        textArea.append("\nYear Published: ");
                        textArea.append(sYearPubed);
                        textArea.append("\nISBN: ");
                        textArea.append(ISBN);
                        textArea.append("\nPrice: ");
                        state = 6;
                    }
                    break;
                case 6:
                    sPrice = textField.getText();
                    textField.setText("");
                    try
                    {
                        price = Double.parseDouble(sPrice);
                        if(price <= 0)
                        {
                            textArea.setText("Please enter the following information\n");
                            textArea.append("Title: ");
                            textArea.append(title);
                            textArea.append("\nAuthor: ");
                            textArea.append(author);
                            textArea.append("\nYear Published: ");
                            textArea.append(sYearPubed);
                            textArea.append("\nISBN: ");
                            textArea.append(ISBN);
                            textArea.append("\nPrice: ");
                            textArea.append("\nPlease enter a valid price.\n");
                            state = 6;
                        }
                        else
                        {
                            textArea.setText("Please enter the following information\n");
                            textArea.append("Title: ");
                            textArea.append(title);
                            textArea.append("\nAuthor: ");
                            textArea.append(author);
                            textArea.append("\nYear Published: ");
                            textArea.append(sYearPubed);
                            textArea.append("\nISBN: ");                             
                            textArea.append(ISBN);
                            textArea.append("\nPrice: ");
                            textArea.append(sPrice);
                            if(type.equalsIgnoreCase("textbook") || type.equalsIgnoreCase("t"))
                            {
                                textArea.append("\nSubject: ");
                                state = 7;
                            }
                            else if(type.equalsIgnoreCase("workbook") || type.equalsIgnoreCase("w"))
                            {
                                textArea.append("\nNumber of problems: ");
                                state = 9;
                            }
                        }
                    }
                    catch(NumberFormatException e)
                    {
                        textArea.setText("Please enter the following information\n");
                        textArea.append("Title: ");
                        textArea.append(title);
                        textArea.append("\nAuthor: ");
                        textArea.append(author);
                        textArea.append("\nYear Published: ");
                        textArea.append(sYearPubed);
                        textArea.append("\nISBN: ");
                        textArea.append(ISBN);
                        textArea.append("\nPrice: ");
                        textArea.append("\nPlease enter a valid price.\n");
                        state = 6;
                    }   break;
                case 7:
                    subject = textField.getText();
                    textField.setText("");
                    if(subject.isEmpty())
                    {
                        textArea.setText("Please enter the following information\n");
                        textArea.append("Title: ");
                        textArea.append(title);
                        textArea.append("\nAuthor: ");
                        textArea.append(author);
                        textArea.append("\nYear Published: ");
                        textArea.append(sYearPubed);
                        textArea.append("\nISBN: ");
                        textArea.append(ISBN);
                        textArea.append("\nPrice: ");
                        textArea.append(sPrice);
                        textArea.append("\nSubject: ");
                        textArea.append("\nPlease enter a subject");
                        state = 7;
                    }
                    else
                    {
                        textArea.setText("Please enter the following information\n");
                        textArea.append("Title: ");
                        textArea.append(title);
                        textArea.append("\nAuthor: ");
                        textArea.append(author);
                        textArea.append("\nYear Published: ");
                        textArea.append(sYearPubed);
                        textArea.append("\nISBN: ");
                        textArea.append(ISBN);
                        textArea.append("\nPrice: ");
                        textArea.append(sPrice);
                        textArea.append("\nSubject: ");
                        textArea.append(subject);
                        textArea.append("\nWorkbook ISNB: ");
                        state = 8;
                    }
                    break;
                case 8:
                    workbookISBN = textField.getText();
                    textField.setText("");
                    for (int x2 = 0; x2 < workbookISBN.length(); x2++) {
                        if (!Character.isDigit(workbookISBN.charAt(x2))) {
                            textArea.setText("Please enter the following information\n");
                            textArea.append("Title: ");
                            textArea.append(title);
                            textArea.append("\nAuthor: ");
                            textArea.append(author);
                            textArea.append("\nYear Published: ");
                            textArea.append(sYearPubed);
                            textArea.append("\nISBN: ");                             
                            textArea.append(ISBN);
                            textArea.append("\nPrice: ");
                            textArea.append(sPrice);
                            textArea.append("\nSubject: ");
                            textArea.append(subject);
                            textArea.append("\nWorkbook ISNB: ");
                            textArea.append("\nPlease enter a valid ISBN.\n");
                            state = 8;
                            return;
                        }
                    }
                    if(!(workbookISBN.length() == 10 || workbookISBN.length() == 13))
                    {
                        textArea.setText("Please enter the following information\n");
                        textArea.append("Title: ");
                        textArea.append(title);
                        textArea.append("\nAuthor: ");
                        textArea.append(author);
                        textArea.append("\nYear Published: ");
                        textArea.append(sYearPubed);
                        textArea.append("\nISBN: ");
                        textArea.append(ISBN);
                        textArea.append("\nPrice: ");
                        textArea.append(sPrice);
                        textArea.append("\nSubject: ");
                        textArea.append(subject);
                        textArea.append("\nWorkbook ISNB: ");
                        textArea.append("\nPlease enter a valid ISBN.\n");
                        state = 8;
                    }
                    else
                    {
                        Book add = b.add("Textbook", title, author, yearPubed, ISBN, price, subject, workbookISBN, 0);
                        Textbook t = (Textbook)add;
                        if(add == null)
                        {
                            textArea.setText("This book already exists");
                            textArea.append("\n\nPress enter to return to main menu");
                            state = 10;
                        }
                        else
                        {
                            textArea.setText("Book added: \n\n");
                            textArea.append(t.toString());
                            textArea.append("\n\nPress enter to return to main menu");
                        }
                        state = 10;
                    }
                    break;
                case 9:
                    problems = textField.getText();
                    textField.setText("");
                    try
                    {
                        numProblems = Integer.parseInt(problems);
                        if(numProblems <= 0)
                        {
                            textArea.setText("Please enter the following information\n");
                            textArea.append("Title: ");
                            textArea.append(title);
                            textArea.append("\nAuthor: ");
                            textArea.append(author);
                            textArea.append("\nYear Published: ");
                            textArea.append(sYearPubed);
                            textArea.append("\nISBN: ");
                            textArea.append(ISBN);
                            textArea.append("\nPrice: ");
                            textArea.append(sPrice);
                            textArea.append("\nNumber of problems: ");
                            textArea.append("\nPlease enter a valid number");
                            state = 9;
                        }
                        else
                        {
                            Book add = b.add("Workbook", title, author, yearPubed, ISBN, price, "", "", numProblems);
                            Workbook w = (Workbook)add;
                            if(add == null)
                            {
                                textArea.setText("This book already exists");
                                textArea.append("\n\nPress enter to return to main menu");
                                state = 10;
                            }   
                            else
                            {
                                textArea.setText("Book added: \n\n");
                                textArea.append(w.toString());
                                textArea.append("\n\nPress enter to return to main menu");
                            }   
                            state = 10;
                        }
                    }
                    catch(NumberFormatException e)
                    {
                        textArea.setText("Please enter the following information\n");
                        textArea.append("Title: ");
                        textArea.append(title);
                        textArea.append("\nAuthor: ");
                        textArea.append(author);
                        textArea.append("\nYear Published: ");
                        textArea.append(sYearPubed);
                        textArea.append("\nISBN: ");
                        textArea.append(ISBN);
                        textArea.append("\nPrice: ");
                        textArea.append(sPrice);
                        textArea.append("\nNumber of problems: ");
                        textArea.append("\nPlease enter a valid number");
                        state = 9;
                    }   break;
                case 10:
                    textArea.setText("What would you like to do?\n");
                    textArea.append("1. Add a book\n2. Print out information on each Book\n3. Print out all unique authors\n4. Print out the average book price, along with the total number of Books\n5. Print out all Textbook-Workbook pairs in the inventory\n6. Save the state of the inventory to a file\n7. Load the state of the inventory from a file\n8. Quit");
                    state = 0;
                    break;
                case 11:
                    choice = textField.getText();
                    textField.setText("");
                    output = b.save(choice);
                    textArea.append(choice);
                    textArea.append("\n" + output);
                    textArea.append("\nPress enter to return to main menu");
                    state = 10;
                    break;
                case 12:
                    choice = textField.getText();
                    textField.setText("");
                    output = b.load(choice);
                    textArea.append(choice);
                    textArea.append("\n" + output);
                    textArea.append("\nPress enter to return to main menu");
                    state = 10;
                    break;
                default:
                    break;
            }
        });
        


        // TextArea should be used to store output
        textArea = new JTextArea(10, 10);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setText("What would you like to do?\n");
        textArea.append("1. Add a book\n2. Print out information on each Book\n3. Print out all unique authors\n4. Print out the average book price, along with the total number of Books\n5. Print out all Textbook-Workbook pairs in the inventory\n6. Save the state of the inventory to a file\n7. Load the state of the inventory from a file\n8. Quit");
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Add the created components to the Frame
        add(textField);
        add(scrollPane);
    }
}