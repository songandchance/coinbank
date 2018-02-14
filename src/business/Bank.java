/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Calendar;

/**
 *
 * @author John Sieber
 */
public class Bank {
    private int quarters, dimes, nickels, pennies;
    private double totalvalue;
    private String errmsg;

    public Bank(int q, int d, int n, int p) {
        this.errmsg = "";
        this.quarters = q;
        this.dimes = d;
        this.nickels = n;
        this.pennies = p;
        this.totalvalue = (q * .25) + (d * .1) + (n * .05) + (p * .01);
        
    }
           
    public double getTotalValue() {
        return this.totalvalue;
    }

    public String getErrorMsg() {
        return this.errmsg;
    }

    public void addQuarter() {
        this.errmsg = "";
        this.quarters +=1;
        this.totalvalue +=.25;
    }
    public void decQuarter() {
        this.errmsg = "";       
        if (this.quarters < 1)
        {
            this.errmsg = "you cannot have a negative amount of any coins.";

        }
        else
        {
            this.totalvalue -=.25;
            this.quarters -=1;
        }    
    } 

    
    public void addDime() {
        this.errmsg = "";
        this.dimes +=1;
        this.totalvalue +=.1;
    }
    public void decDime() {
        this.errmsg = "";       
        if (this.dimes < 1)
        {
            this.errmsg = "you cannot have a negative amount of any coins.";

        }
        else
        {
            this.totalvalue -=.1;
            this.dimes -=1;
        }    
    }     

    public void addNickel() {
        this.errmsg = "";
        this.nickels +=1;
        this.totalvalue +=.05;
    }
    public void decNickel() {
        this.errmsg = "";       
        if (this.nickels < 1)
        {
            this.errmsg = "you cannot have a negative amount of any coins.";

        }
        else
        {
            this.totalvalue -=.05;
            this.nickels -=1;
        }    
    }    
    
    public void addPenny() {
        this.errmsg = "";
        this.pennies +=1;
        this.totalvalue +=.01;
    }
    public void decPenny() {
        this.errmsg = "";       
        if (this.pennies < 1)
        {
            this.errmsg = "you cannot have a negative amount of any coins.";

        }
        else
        {
            this.totalvalue -=.01;
            this.pennies -=1;
        }    
    }     
    public int getPennies()
    {
        return this.pennies;
    }
    public int getNickels()
    {
        return this.nickels;
    } 
    public int getDimes()
    {
        return this.dimes;
    }
    public int getQuarters()
    {
        return this.quarters;
    }
    private void writelog() {
        try
        {
            PrintWriter out = new PrintWriter(new FileWriter("bank.txt"));
            out.println("Total number of quarters: " + this.quarters);
            out.println("Total number of dimes: " + this.dimes);
            out.println("Total number of nickels: " + this.nickels);
            out.println("Total number of pennies: " + this.pennies);
            out.close();
        }
        catch (IOException e)
        {
            this.errmsg = "Error writing log file: " + e.getMessage();
        }
    }
     
    public void saveBank() {
        writelog();
    }
}
