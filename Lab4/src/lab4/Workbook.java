/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;


public class Workbook extends Book {

    private int numProblems;
    
    public Workbook(String title, String author, int yearPubed, String ISBN, double price, int numProblems) 
    {
        super(title, author, yearPubed, ISBN, price);
        this.numProblems = numProblems;
    }
    
    public int getProblems()
    {
        return this.numProblems;
    }
    
    @Override
    public String toString()
    {
        return ("Title: " + this.getTitle() + "\nAuthor(s): " + this.getAuthor() + "\nYear Published: " + getYear() + "\nISBN: " + getISBN() + "\nPrice: $" + this.getPrice() + "\nNumber of Problems: " + this.numProblems); 
    }
    
    public boolean equals(Workbook other)
    {
        return other.getTitle().equals(this.getTitle()) && other.getAuthor().equals(this.getAuthor()) && other.getYear() == this.getYear() && other.getPrice() == this.getPrice() && other.numProblems == this.numProblems;
    }
    
}
