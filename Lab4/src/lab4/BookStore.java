/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import java.io.*;
import java.util.*;

public class BookStore {
    
        private HashMap<Integer, Book> books;
    
    public BookStore()
    {
        books = new HashMap();
    }
    
    public Book add(String type, String title, String author, int year, String ISBN, double price, String subject, String workbookISBN, int problems)
    {
        int i;
        if(type.equalsIgnoreCase("Textbook"))
        {
            Textbook t;
            t = new Textbook(title, author, year, ISBN, price, subject, workbookISBN);
            for(int x = 0;x < books.size();x++)
            {
                if(t.equals(books.get(x)))
                {
                    return null;
                }
            }
            i = books.size();
            books.put(i, t);
            return t;
        }
        else if(type.equalsIgnoreCase("Workbook"))
        {
            Workbook w;
            w = new Workbook(title, author, year, ISBN, price, problems);
            for(int x = 0;x < books.size();x++)
            {
                if(w.equals(books.get(x)))
                {
                    return null;
                }
            }
            i = books.size();
            books.put(i, w);
            return w;
        }
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        String store;
        store = "";
        if(books.isEmpty())
        {
            return "You do not have any books.\n";
        }
        for(int i = 0;i < books.size();i++)
        {
            store += ("Book " + (i+1) + "\n");
            store += books.get(i).toString();
            store += "\n\n";
        }
        return store;
    }
    
    public String avgPrice()
    {
        if(books.isEmpty())
        {
            return "You do not have any books.\n";
        }
        double totalPrice = 0;
        double avgPrice;
        String aPrice;
        for (int i = 0; i < books.size(); i++)
        {
            totalPrice += books.get(i).getPrice();
        }
        avgPrice = totalPrice/books.size();
        aPrice = String.format("%.2f", avgPrice);
        return "There are " + books.size() + " books with and average price of $" + aPrice + "\n";
    }
    
    public String authors()
    {
        String output = "Authors: \n";
        if(books.isEmpty())
        {
            return "You do not have any books.\n";
        }
        ArrayList<String> authors = new ArrayList<>();
        for (int i = 0; i < books.size(); i++)
        {
            if(!authors.contains(books.get(i).getAuthor()))
            {
                authors.add(books.get(i).getAuthor());
            }
        }
        for (String author : authors) 
        {
            output += author;
            output += "\n";
        }
        return output;
    }
   
    public String pairs()
    {
        boolean pair = false;
        String output = "";
        if(books.isEmpty())
        {
            return "You do not have any books.";
        }
        for(int i = 0;i < books.size();i++)
        {
            for(int x = 0;x < books.size();x++)
            {
                if(books.get(i) instanceof Textbook)
                {
                    Textbook t = (Textbook) books.get(i);
                    if(books.get(x) instanceof Workbook)
                    {
                        Workbook w = (Workbook) books.get(x);
                        if(t.getWorkbookISBN().equals(w.getISBN()))
                        {
                            output += "Textbook: " +t.getTitle() + " <=> Workbook: " + w.getTitle();
                            pair = true;
                        }
                    }
                }
            }
        }
        if(pair)
        {
            return output;
        }
        return "No matches found.";
    }
    
    public String save(String filename)
    {
        try 
        {
            FileWriter fw = new FileWriter(filename);
            try (BufferedWriter bw = new BufferedWriter(fw)) 
            {
                for(int i = 0;i < books.size();i++)
                {
                    if(books.get(i) instanceof Textbook)
                    {
                        bw.write("Textbook");
                        bw.newLine();
                        bw.write(books.get(i).getTitle());
                        bw.newLine();
                        bw.write(books.get(i).getAuthor());
                        bw.newLine();
                        bw.write(Integer.toString(books.get(i).getYear()));
                        bw.newLine();
                        bw.write(books.get(i).getISBN());
                        bw.newLine();
                        bw.write(Double.toString(books.get(i).getPrice()));
                        bw.newLine();
                        bw.write(((Textbook) books.get(i)).getSubject());
                        bw.newLine();
                        bw.write(((Textbook) books.get(i)).getWorkbookISBN());
                        bw.newLine();
                    }
                    else if(books.get(i) instanceof Workbook)
                    {
                        bw.write("Workbook");
                        bw.newLine();
                        bw.write(books.get(i).getTitle());
                        bw.newLine();
                        bw.write(books.get(i).getAuthor());
                        bw.newLine();
                        bw.write(Integer.toString(books.get(i).getYear()));
                        bw.newLine();
                        bw.write(books.get(i).getISBN());
                        bw.newLine();
                        bw.write(Double.toString(books.get(i).getPrice()));
                        bw.newLine();
                        bw.write(Integer.toString(((Workbook) books.get(i)).getProblems()));
                        bw.newLine();
                    }
                    
                }
                return "State saved successfully.\n";
            }
        }
        catch (IOException ex)
        {
            return "Error writing to file\n";
        }
    }
    
    public String load(String filename)
    {
        books.clear();
    
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
        int i;
        try
        {
            FileReader fw = new FileReader(filename);
            BufferedReader bw = new BufferedReader(fw);
            while(true)
            {
                bookType = bw.readLine();
                if(bookType == null)
                {
                    bw.close();
                    return "State loaded successfully.\n";
                }
                if(bookType.equalsIgnoreCase("textbook"))
                {
                    title = bw.readLine();
                    author = bw.readLine();
                    sYearPubed = bw.readLine();
                    yearPubed = Integer.parseInt(sYearPubed);
                    ISBN = bw.readLine();
                    sPrice = bw.readLine();
                    price = Double.parseDouble(sPrice);
                    subject = bw.readLine();
                    workbookISBN = bw.readLine();
                    i = books.size();
                    books.put(i, new Textbook(title, author, yearPubed, ISBN, price, subject, workbookISBN));
                }
                else if(bookType.equalsIgnoreCase("workbook"))
                {
                    title = bw.readLine();
                    author = bw.readLine();
                    sYearPubed = bw.readLine();
                    yearPubed = Integer.parseInt(sYearPubed);
                    ISBN = bw.readLine();
                    sPrice = bw.readLine();
                    price = Double.parseDouble(sPrice);
                    problems = bw.readLine();
                    numProblems = Integer.parseInt(problems);
                    i = books.size();
                    books.put(i, new Workbook(title, author, yearPubed, ISBN, price, numProblems));
                }
            }
        }
        catch (FileNotFoundException ex)
        {
            return "Unable to find file with that name.\n";
        }
        catch (IOException ex)
        {
            return "Error reading file.\n";
        }
    }
    
    /* public   void print() {
    if(books.isEmpty())
    {
    System.out.println("You do not have any books\n");
    return;
    }
    System.out.println(tString() + "\n");
    }*/
}
        
