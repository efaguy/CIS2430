/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

/**
 *
 * @author Eric
 */
public class Textbook extends Book {
    
    private String subject;
    private String workbookISBN;

    public Textbook(String title, String author, int yearPubed, String ISBN, double price, String subject, String workbookISBN) 
    {
        super(title, author, yearPubed, ISBN, price);
        this.subject = subject;
        this.workbookISBN = workbookISBN;
    }
    
    /**
     *
     * @return
     */
    public String getSubject()
    {
        return this.subject;
    }
    
    public String getWorkbookISBN()
    {
        return this.workbookISBN;
    }
    
    @Override
    public String toString()
    {
        return ("Title: " + this.getTitle() + "\nAuthor(s): " + this.getAuthor() + "\nYear Published: " + getYear() + "\nISBN: " + getISBN() + "\nPrice: $" + this.getPrice() + "\nSubject: " + this.subject + "\nWorkbook ISBN: " + this.workbookISBN); 
    }
    
    public boolean equals(Textbook other)
    {
        return other.getTitle().equals(this.getTitle()) && other.getAuthor().equals(this.getAuthor()) && other.getYear() == this.getYear() && other.getPrice() == this.getPrice() && (other.subject.equals(this.subject) && other.workbookISBN.equals(this.workbookISBN));
    }

    
    
    
}
