/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

import java.util.Objects;

/**
 *
 * @author Eric
 */
public class Textbook extends Book {
    
    private String subject;
    private String workbookISBN;

    public Textbook(String title, String author, String yearPubed, String ISBN, String price, String subject, String workbookISBN) throws BadInput
    {
        super(title, author, yearPubed, ISBN, price);
        if(subject.isEmpty())
        {
            throw new BadInput("subject");
        }
        for(int i = 0;i < workbookISBN.length();i++)
        {
            if(!Character.isDigit(workbookISBN.charAt(i)))
            {
                throw new BadInput("Workbook ISBN");
            }
        }
        if(!(workbookISBN.length() == 10 || workbookISBN.length() == 13))
        {
            throw new BadInput("Workbook ISBN");
        }
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
    
    @Override
    public int hashCode()
    {
        int hash = super.hashCode();
        
        int temp = this.subject.hashCode();
        hash += temp;
        temp = this.workbookISBN.hashCode();
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
        if(!super.equals(obj))
        {
            return false;
        }
        Textbook other = (Textbook) obj;
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        if (!Objects.equals(this.workbookISBN, other.workbookISBN)) {
            return false;
        }
        return true;
    }

 

    
    
    
}
