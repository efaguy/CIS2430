/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

import java.util.Objects;


public class Book {
    
    private String title;
    private String author;
    private int yearPubed;
    private String ISBN;
    private double price;

    
    public Book(String title, String author, String yearPubed, String ISBN, String sprice) throws BadInput
    {
        int year;
        double dPrice;

        if(title.isEmpty())
        {
            throw new BadInput("title");
        }
        if(author.isEmpty())
        {
            throw new BadInput("author");
        }
        try
        {
            year = Integer.parseInt(yearPubed);
        }
        catch(NumberFormatException ex)
        {
            throw new BadInput("year");
        }
        if(year < -600 || year > 2017)
        {
            throw new BadInput("year");
        }
        for(int i = 0;i < ISBN.length();i++)
        {
            if(!Character.isDigit(ISBN.charAt(i)))
            {
                throw new BadInput("ISBN");
            }
        }
        if(!(ISBN.length() == 10 || ISBN.length() == 13))
        {
            throw new BadInput("ISBN");
        }
        try
        {
            dPrice = Double.parseDouble(sprice);
        }
        catch(NumberFormatException ex)
        {
            throw new BadInput("price");
        }
        if(dPrice <= 0)
        {
            throw new BadInput("price");
        }
        this.title = title;
        this.author = author;
        this.yearPubed = year;
        this.ISBN = ISBN;
        this.price = dPrice;
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
    
    @Override
    public int hashCode()
    {
        int hash = 0;
        
        int temp = this.title.hashCode();
        hash += temp;
        temp = this.author.hashCode();
        hash += temp;
        temp = Integer.hashCode(this.yearPubed);
        hash += temp;
        temp = this.ISBN.hashCode();
        hash += temp;
        temp = Double.hashCode(price);
        hash += temp;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Book other = (Book) obj;
        if(this.hashCode() != other.hashCode())
        {
            return false;
        }
        if (this.yearPubed != other.yearPubed) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.ISBN, other.ISBN)) {
            return false;
        }
        return true;
    }
}
   
