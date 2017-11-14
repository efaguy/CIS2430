/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;


public class Book {
    
    private String title;
    private String author;
    private int yearPubed;
    private String ISBN;
    private double price;

    
    public Book(String title, String author, int yearPubed, String ISBN, double price)
    {
        this.title = title;
        this.author = author;
        this.yearPubed = yearPubed;
        this.ISBN = ISBN;
        this.price = price;
    }
    
    public String getTitle()
    {
        return this.title;
    }
    
    public String getAuthor()
    {
        return this.author;
    }
    
    public int getYear()
    {
        return this.yearPubed;
    }
    
    public double getPrice()
    {
        return this.price;
    }
    
    public String getISBN()
    {
        return this.ISBN;
    }

    @Override
    public String toString()
    {
        return ("Title: " + title + "\nAuthor(s): " + author + "\nYear Published: " + this.yearPubed + "\nISBN: " + ISBN + "\nPrice: $" + this.price); 
    }
    
    public boolean equals(Book other)
    {
        return other.title.equals(this.title) && other.author.equals(this.author) && other.yearPubed == this.yearPubed && other.price == this.price;
    }
}
   
