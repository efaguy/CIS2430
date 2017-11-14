/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.io.*;
import java.util.*;

public class BookStore {
    
    private ArrayList<Book> books;
    
    public BookStore()
    {
        books = new ArrayList<>();
    }
    
    public void run()
    {
        Scanner s;
        s = new Scanner(System.in);
        String choice;
        int c;
        
        while(true)
        {
            while(true)
            {
                System.out.println("What would you like to do?");
                System.out.println("1. Add a book\n2. Print out information on each Book\n3. Print out all unique authors\n4. Print out the average book price, along with the total number of Books\n5. Print out all Textbook-Workbook pairs in the inventory\n6. Save the state of the inventory to a file\n7. Load the state of the inventory from a file\n8. Quit");
                choice = s.nextLine();
                try
                {
                    c = Integer.parseInt(choice);
                }    
                catch(NumberFormatException e)
                {
                    System.out.println("Please enter a number");
                    continue;
                }
                break;
            }
            System.out.print("\n");
            switch (c) {
                case 1:
                    add();
                    break;
                case 2:
                    print();
                    break;
                case 3:
                    authors();
                    break;
                case 4:
                    avgPrice();
                    break;
                case 5:
                    pairs();
                    break;
                case 6:
                    save();
                    break;
                case 7:
                    load();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Please enter a valid choice.");
                    break;
            }
        }
    }
    
    public void add()
    {
        Scanner s;
        s = new Scanner(System.in);
        String choice;
        String title;
        String author;
        String ISBN;
        String sYearPubed;
        int yearPubed;
        String sPrice;
        double price;
        String workbookISBN;
        String subject;
        String problems;
        int numProblems;
        
        while(true)
        {
            System.out.println("Would you like to add a textbook, or workbook");
            choice = s.nextLine();
            if(!choice.equalsIgnoreCase("textbook") && !choice.equalsIgnoreCase("t") && !choice.equalsIgnoreCase("workbook") && !choice.equalsIgnoreCase("w"))
            {
                System.out.println("Please enter either a textbook(t) or workbook(w).");
                continue;
            }
            break;
        }
        
        System.out.println("Please enter the following information");
        while(true)
        {
            System.out.print("Title: ");
            title = s.nextLine();
            if(title.isEmpty())
            {
                System.out.println("Please enter a title.");
                continue;
            }
            break;
        }
        while(true)
        {
            System.out.print("Author: ");
            author = s.nextLine();
            if(author.isEmpty())
            {
                System.out.println("Please enter a Author.");
                continue;
            }
            break;
        }
        while(true)
        {
            System.out.print("Year Published: ");
            sYearPubed = s.nextLine();
            try 
            {
                yearPubed = Integer.parseInt(sYearPubed);
            } 
            catch(NumberFormatException e) 
            {
                System.out.println("Please enter a valid year.");
                continue;
            }
            if(yearPubed < -2600 || yearPubed > 2017)
            {
                System.out.println("Please enter a valid year.");
                continue;
            }
            break;
        }
        loop: while(true)
        {
            System.out.print("ISBN: ");
            ISBN = s.nextLine();
            for(int i = 0;i < ISBN.length();i++)
            {
                if(!Character.isDigit(ISBN.charAt(i)))
                {
                    System.out.println("Please enter a valid ISBN.");
                    continue loop;
                }
            }
            if(!(ISBN.length() == 10 || ISBN.length() == 13))
            {
                System.out.println("Please enter a valid ISBN.");
                continue;
            }
            break;
        }
        while(true)
        {
            System.out.print("Price: ");
            sPrice = s.nextLine();
            try 
            {
                price = Double.parseDouble(sPrice);
            } 
            catch(NumberFormatException e) 
            {
                System.out.println("Please enter a valid price.");
                continue;
            }
            if(price < 0)
            {
                System.out.println("Please enter a valid price.");
                continue;
            }
            break;
        }
        if(choice.equalsIgnoreCase("textbook") || choice.equalsIgnoreCase("t"))
        {
            while(true)
            {
                System.out.print("Subject: ");
                subject = s.nextLine();
                if(subject.isEmpty())
                {
                    System.out.println("Please enter a subject.");
                    continue;
                }
                break;
            }
            loop: while(true)
            {
                System.out.print("Workbook ISBN: ");
                workbookISBN = s.nextLine();
                for(int i = 0;i < workbookISBN.length();i++)
                {
                    if(!Character.isDigit(workbookISBN.charAt(i)))
                    {
                        System.out.println("Please enter a valid ISBN.");
                        continue loop;
                    }
                }
                if(!(workbookISBN.length() == 10 || workbookISBN.length() == 13))
                {
                    System.out.println("Please enter a valid ISBN.");
                    continue;
                }
                break;
            }
            Textbook t;
            t = new Textbook(title, author, yearPubed, ISBN, price, subject, workbookISBN);
            for(int i = 0;i < books.size();i++)
            {
                if(t.equals(books.get(i)))
                {
                    System.out.println("This book already exists.\n");
                    return;
                }
            }
            books.add(t);
        }
        else if(choice.equalsIgnoreCase("workbook") || choice.equalsIgnoreCase("w"))
        {
            while(true)
            {
                System.out.print("Number of problems: ");
                problems = s.nextLine();
                try 
                {
                    numProblems = Integer.parseInt(problems);
                } 
                catch(NumberFormatException e) 
                {
                    System.out.println("Please enter a number.");
                    continue;
                }
                if(numProblems <= 0)
                {
                    System.out.println("Please enter a valid number of problems.");
                    continue;
                }
                break;
            }
            
            Workbook w;
            w = new Workbook(title, author, yearPubed, ISBN, price, numProblems);
            for(int i = 0;i < books.size();i++)
            {
                if(w.equals(books.get(i)))
                {
                    System.out.println("This book already exists.\n");
                    return;
                }
            }
            books.add(w);
        }
        System.out.print("\n");
    }
    
    public void avgPrice()
    {
        if(books.isEmpty())
        {
            System.out.println("You do not have any books\n");
            return;
        }
        double totalPrice = 0;
        double avgPrice;
        String aPrice;
        for (Book book : books) 
        {
            totalPrice += book.getPrice();
        }
        avgPrice = totalPrice/books.size();
        aPrice = String.format("%.2f", avgPrice);
        System.out.println("There are " + books.size() + " books with and average price of $" + aPrice + "\n");
    }
    
    public void authors()
    {
        if(books.isEmpty())
        {
            System.out.println("You do not have any books\n");
            return;
        }
        ArrayList<String> authors = new ArrayList<>();
        System.out.println("Authors: ");
        for (Book book : books) 
        {
            if(!authors.contains(book.getAuthor()))
            {
                authors.add(book.getAuthor());
            }
        }
        for (String author : authors) {
            System.out.println(author);
        }
        System.out.print("\n");
    }
    
    
    @Override
    public String toString()
    {
        String store;
        store = "";
        for(int i = 0;i < books.size();i++)
        {
            store += ("\nBook " + (i+1) + "\n");
            store += books.get(i).toString();
            store += "\n";
        }
        return store;
    }

    public void pairs() 
    {
        if(books.isEmpty())
        {
            System.out.println("You do not have any books\n");
            return;
        }
        for(Book book1 : books)
        {
            for(Book book2 : books)
            {
                if(book1 instanceof Textbook)
                {
                    Textbook t = (Textbook) book1;
                    if(book2 instanceof Workbook)
                    {
                        Workbook w = (Workbook) book2;
                        if(t.getWorkbookISBN().equals(w.getISBN()))
                        {
                            System.out.print("Textbook: " +t.getTitle() + " ¡=¿ Workbook: ");
                            System.out.println(book2.getTitle());
                        }
                    }
                }
            }
        }
        System.out.print("\n");
    }

    public void save() 
    {
        Scanner s;
        s = new Scanner(System.in);
        String filename;
        System.out.print("What filename would you like to use to save: ");
        filename = s.nextLine();
        try {
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            
            for (Book book : books) 
            {
                if(book instanceof Textbook)
                {
                    bw.write("Textbook");
                    bw.newLine();
                    bw.write(book.getTitle());
                    bw.newLine();
                    bw.write(book.getAuthor());
                    bw.newLine();
                    bw.write(Integer.toString(book.getYear()));
                    bw.newLine();
                    bw.write(book.getISBN());
                    bw.newLine();
                    bw.write(Double.toString(book.getPrice()));
                    bw.newLine();
                    bw.write(((Textbook) book).getSubject());
                    bw.newLine();
                    bw.write(((Textbook) book).getWorkbookISBN());
                    bw.newLine();
                }
                else if(book instanceof Workbook)
                {
                    bw.write("Workbook");
                    bw.newLine();
                    bw.write(book.getTitle());
                    bw.newLine();
                    bw.write(book.getAuthor());
                    bw.newLine();
                    bw.write(Integer.toString(book.getYear()));
                    bw.newLine();
                    bw.write(book.getISBN());
                    bw.newLine();
                    bw.write(Double.toString(book.getPrice()));
                    bw.newLine();
                    bw.write(Integer.toString(((Workbook) book).getProblems()));   
                    bw.newLine();
                }
                
            }
            System.out.println("State saved successfully.\n");
            bw.close();
        } 
        catch (IOException ex) 
        {
           System.out.println("Error writing to file\n");
        }
    }

    public void load() 
    {
        books.clear();
        Scanner s;
        s = new Scanner(System.in);
        String filename;
        String bookType;
        String title;
        String author;
        String ISBN;
        String sYearPubed;
        int yearPubed;
        String sPrice;
        double price;
        String workbookISBN;
        String subject;
        String problems;
        int numProblems;
        System.out.print("What filename would you like to load from: ");
        filename = s.nextLine();
        try 
        {
            FileReader fw = new FileReader(filename);
            BufferedReader bw = new BufferedReader(fw);
            while(true)
            {
                bookType = bw.readLine();
                if(bookType == null)
                {
                    System.out.println("State loaded successfully.\n");
                    bw.close();
                    break;
                }
                if(bookType.equalsIgnoreCase("textbook"))
                {
                    title = bw.readLine();
                    author = bw.readLine();
                    sYearPubed = bw.readLine();
                    yearPubed = Integer.parseInt(sYearPubed);
                    ISBN = bw.readLine();
                    sPrice = bw.readLine();
                    price= Double.parseDouble(sPrice);
                    subject = bw.readLine();
                    workbookISBN = bw.readLine();
                    books.add(new Textbook(title, author, yearPubed, ISBN, price, subject, workbookISBN));
                }
                else if(bookType.equalsIgnoreCase("workbook"))
                {
                    title = bw.readLine();
                    author = bw.readLine();
                    sYearPubed = bw.readLine();
                    yearPubed = Integer.parseInt(sYearPubed);
                    ISBN = bw.readLine();
                    sPrice = bw.readLine();
                    price= Double.parseDouble(sPrice);
                    problems = bw.readLine();
                    numProblems = Integer.parseInt(problems);
                    books.add(new Workbook(title, author, yearPubed, ISBN, price, numProblems));
                }
            }
        } 
        catch (FileNotFoundException ex) 
        {
           System.out.println("Unable to find file with that name.\n");
        } 
        catch (IOException ex) 
        {
           System.out.println("Error reading file.\n");
        }
    }

    public void print() {
        if(books.isEmpty())
        {
            System.out.println("You do not have any books\n");
            return;
        }
        System.out.println(toString() + "\n");
    }
}
        
