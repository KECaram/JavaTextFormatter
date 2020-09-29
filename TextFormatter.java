
package uml.info2970.textformatter;


import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
        
public class TextFormatter 
{
       
    public static void main(String[] args)
    {
        /* Declare scanner and promt/get width of formatted column */
         Scanner keyboard = new Scanner(System.in);
         int width = 0;
         do
         {
             System.out.print("Enter an output format width between 30 and 100 characters: ");
             width = keyboard.nextInt();
             
             if(width < 30 || width > 100)
                 System.out.println("Format width not to specifications. Try again.");
         }while(width < 30 || width > 100);
         
         
         int var = 1;//control variable
         String textIn = "";//file name string
         Scanner textinScanner = null;//file read scanner
         
         /* Loop to get the name of the file to read from */
         do
         {
            System.out.print("\nEnter the name of the file to read from: ");
            textIn = keyboard.next();
            File textinFile = new File(textIn);//read file object
            
            /* error checking to see if the file exists and can be read */
            if(!textinFile.exists())
            {
                System.out.println("The file " + textIn + " does not exist. Try again.");
            }
            else if (!textinFile.canRead())
            {
                System.out.println("The file " + textIn + " cannot be read. Try again.");
            }
            
            /* if the file exists and is readable create a scanner to read the file */
            else
            {
              try
              {
                textinScanner  = new Scanner(textinFile);
              }
              catch (FileNotFoundException e)
              {
                  System.out.println("Unexpected Error");
              }
              
              var = 0;
            }
            
         }while(var == 1);
         
         
         String textOut = "";//write file name string
         PrintWriter outWriter = null;//printwriter name
         var = 1;//control variable
         
          /* Loop to get the name of the file to write to */
         do
         {
            System.out.print("\nEnter the name of the file to write to: ");
            textOut = keyboard.next();
            File textoutFile = new File(textOut);
            
            /* Instructions on dealing with files that already exist */
            if(textoutFile.exists())
            {
                System.out.print("The file " + textOut + " already exists. Do you want to overwrite? (Y/N): ");
                String yesNo = keyboard.next();

                /* Overwrite file case */
                if(yesNo.equalsIgnoreCase("y"))
                {
                    try
                    {
                        outWriter = new PrintWriter(textoutFile);//create scanner to write
                    }
                    
                    catch (FileNotFoundException e)
                    {
                         System.out.println("Unexpected Error");
                    }
                    var = 0;
                }
                
                /* Don't overwrite file case */
                else if(yesNo.equalsIgnoreCase("n"))
                {
                    System.out.println("Ok. File will not be overwritten.");
                }
                
                /* Bad input case */
                else
                {
                    System.out.println("Invalid answer. Try again.");
                }
            }
            
            /* Create new file case */
            else
            {
                try
                {
                    outWriter = new PrintWriter(textoutFile);//create scanner to write
                }
                    
                catch (FileNotFoundException e)
                {
                    System.out.println("Unexpected Error");
                }
                var = 0;
            }
            
         }while(var == 1);
        
        /* Write to output file */
        outWriter.println("The formatted text is as follows...");
        
        int x = 0;//control variable
        
        /* loop to write a line the width of our desired column */
        for(x = 0; x < width; x++)
        {
            outWriter.print("*");
        }
        
        outWriter.println("\n");//spacing
        
        String line = textinScanner.next();//set starting string to first word in read file
        
        /* Loop to copy words from the read file, concatenate them into a single
           text line that is as long as the desired column width, and print the
           line to the write file */
        while(textinScanner.hasNext())
        {
            String word = textinScanner.next();
            
            /* Make sure that the line plus a new word and a space won't be greater
               than the desired column width */
            if(line.length() + word.length() + 1 > width)
            {
               outWriter.println(line);
               
               line = word;//if it is longer start the next line with the word that made it too long
            }
            
            /* if the line length isn't too long keep building onto the line */
            else
                line = line + " " + word;
        }
        
        /* if the last line didn't get printed because it didnt meet the length 
           perameters print whatever is left */
        if(line.length() > 0)
        {
            outWriter.println(line);
        }

        /* Close input/output streams*/
        outWriter.close();
        textinScanner.close();
        
        /* Write a success message to make sure the user knows the program ran successfully */
        System.out.println("\nYou have successfully written to " + textOut + ".");
    }
}
