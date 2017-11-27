package lab5;

import java.io.*;
import java.util.*;

public class BookStore {
    
        private HashMap<Integer, Book> books;
    
    public BookStore()
    {
        books = new HashMap();
    }
    
    public String add(String type, String title, String author, String year, String ISBN, String price, String subject, String workbookISBN, String problems)
    {
        int i;
        if(type.equalsIgnoreCase("Book"))
        {
            Book b;
            try
            {
                b = new Book(title, author, year, ISBN, price);
            }
            catch(BadInput ex)
            {
                return ex.getMessage();
            }
            
            for (Book curBook: books.values()) 
            {
                if(b.equals(curBook))
                {
                    return "double";
                }
            }
            i = b.hashCode();
            books.put(i, b);
            return null;
        }
        else if(type.equalsIgnoreCase("Textbook"))
        {
            Textbook t;
            try
            {
                t = new Textbook(title, author, year, ISBN, price, subject, workbookISBN);
            }
            catch(BadInput ex)
            {
                return ex.getMessage();
            }
            for (Book curBook: books.values()) 
            {
                if(t.equals(curBook))
                {
                    return "double";
                }
            }
            i = t.hashCode();
            books.put(i, t);
            return null;
        }
        else if(type.equalsIgnoreCase("Workbook"))
        {
            Workbook w;
            try
            {
                w = new Workbook(title, author, year, ISBN, price, problems);
            }
            catch(BadInput ex)
            {
                return ex.getMessage();
            }
            for (Book curBook: books.values()) 
            {
                if(w.equals(curBook))
                {
                    return "double";
                }
            }
            i = w.hashCode();
            books.put(i, w);
            return null;
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
        int i = 0;
        String store;
        store = "";
        if(books.isEmpty())
        {
            return "You do not have any books.\n";
        }
        for (Book curBook: books.values()) 
        {
            store += ("Book " + (i+1) + "\n");
            store += curBook.toString();
            store += "\n\n";
            i++;
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
        for (Book curBook: books.values())
        {
            totalPrice += curBook.getPrice();
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
        for (Book curBook: books.values())
        {
            if(!authors.contains(curBook.getAuthor()))
            {
                authors.add(curBook.getAuthor());
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
        for (Book curBook1: books.values())
        {
            for (Book curBook2: books.values())
            {
                if(curBook1 instanceof Textbook)
                {
                    Textbook t = (Textbook) curBook1;
                    if(curBook2 instanceof Workbook)
                    {
                        Workbook w = (Workbook) curBook2;
                        if(t.getWorkbookISBN().equals(w.getISBN()))
                        {
                            output += "Textbook: " +t.getTitle() + " <=> Workbook: " + w.getTitle() + "\n";
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
                for (Book curBook: books.values())
                {
                    if(curBook instanceof Textbook)
                    {
                        Textbook t = (Textbook) curBook;
                        bw.write("Textbook");
                        bw.newLine();
                        bw.write(t.getTitle());
                        bw.newLine();
                        bw.write(t.getAuthor());
                        bw.newLine();
                        bw.write(Integer.toString(t.getYear()));
                        bw.newLine();
                        bw.write(t.getISBN());
                        bw.newLine();
                        bw.write(Double.toString(t.getPrice()));
                        bw.newLine();
                        bw.write(t.getSubject());
                        bw.newLine();
                        bw.write(t.getWorkbookISBN());
                        bw.newLine();
                    }
                    else if(curBook instanceof Workbook)
                    {
                        Workbook w = (Workbook) curBook;
                        bw.write("Workbook");
                        bw.newLine();
                        bw.write(w.getTitle());
                        bw.newLine();
                        bw.write(w.getAuthor());
                        bw.newLine();
                        bw.write(Integer.toString(w.getYear()));
                        bw.newLine();
                        bw.write(w.getISBN());
                        bw.newLine();
                        bw.write(Double.toString(w.getPrice()));
                        bw.newLine();
                        bw.write(Integer.toString(w.getProblems()));
                        bw.newLine();
                    }
                    else
                    {
                        bw.write("Book");
                        bw.newLine();
                        bw.write(curBook.getTitle());
                        bw.newLine();
                        bw.write(curBook.getAuthor());
                        bw.newLine();
                        bw.write(Integer.toString(curBook.getYear()));
                        bw.newLine();
                        bw.write(curBook.getISBN());
                        bw.newLine();
                        bw.write(Double.toString(curBook.getPrice()));
                        bw.newLine();
                    }
                    
                }
                return "State saved successfully.\n";
            }
        }
        catch (IOException | NullPointerException ex)
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
        String yearPubed;
        String sPrice;
        String price;
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
                    ISBN = bw.readLine();
                    sPrice = bw.readLine();
                    subject = bw.readLine();
                    workbookISBN = bw.readLine();
                    Textbook t;
                    try
                    {
                        t = new Textbook(title, author, sYearPubed, ISBN, sPrice, subject, workbookISBN);
                    }
                    catch(BadInput ex)
                    {
                        books.clear();
                        return ex.getMessage();
                    }
                    i = t.hashCode();
                    books.put(i, t);
                }
                else if(bookType.equalsIgnoreCase("workbook"))
                {
                    title = bw.readLine();
                    author = bw.readLine();
                    sYearPubed = bw.readLine();
                    ISBN = bw.readLine();
                    sPrice = bw.readLine();
                    problems = bw.readLine();
                    numProblems = Integer.parseInt(problems);
                    Workbook w;
                    try
                    {
                        w = new Workbook(title, author, sYearPubed, ISBN, sPrice, problems);
                    }
                    catch(BadInput ex)
                    {
                        books.clear();
                        return "Invalid data.\n";
                    }
                    i = w.hashCode();
                    books.put(i, w);
                }
                else
                {
                    title = bw.readLine();
                    author = bw.readLine();
                    sYearPubed = bw.readLine();
                    ISBN = bw.readLine();
                    sPrice = bw.readLine();
                    Book b;
                    try
                    {
                        b = new Book(title, author, sYearPubed, ISBN, sPrice);
                    }
                    catch(BadInput ex)
                    {
                        books.clear();
                        return "Invalid data.\n";
                    }
                    i = b.hashCode();
                    books.put(i, b);
                }
            }
        }
        catch (FileNotFoundException ex)
        {
            return "Unable to find file with that name.\n";
        }
        catch (IOException | NullPointerException ex)
        {
            return "Error reading file.\n";
        }
    }
    
}
        
