/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;


public class Workbook extends Book {

    private int numProblems;
    
    public Workbook(String title, String author, String yearPubed, String ISBN, String price, String numProblems) throws BadInput 
    {
        super(title, author, yearPubed, ISBN, price);
        int problems;
        try
        {
            problems = Integer.parseInt(numProblems);
        }
        catch(NumberFormatException ex)
        {
            throw new BadInput("number of problems");
        }
        this.numProblems = problems;
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
        Workbook other = (Workbook) obj;
        if (this.numProblems != other.numProblems) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode()
    {
        int hash = super.hashCode();
        
        int temp = Integer.hashCode(this.numProblems);
        hash += temp;
        return hash;
    }
    
}
