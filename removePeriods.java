import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class removePeriods{
    public static void main(String args[]) throws  FileNotFoundException, IOException{
        File fileName = new File("trial.txt");
        try (var br = new BufferedReader(new FileReader("trial.txt"))){
            String line;
            FileWriter fw = new FileWriter("newTrial.txt");
            while((line = br.readLine()) != null){
                String s = removePeriod(line);
                
                fw.write(s);//write each line of html into the text file
            }
            fw.close();
        }
        // String s = "San Marco,5000.000,5+,450,https://www.immobiliare.it/annunci/94700524/";
        // s = removePeriod(s);
        // System.out.println(s);
    }

    public static String removePeriod(String line){
        String s = removeLink(line);
        String middle = ".";
        while(s.contains(middle)){
            int ind = 0;
            while(!((s.charAt(ind)) == '.')){
                ind++;
            }
            int midLoc = ind;
            s = s.substring(0,midLoc)+s.substring(midLoc+1);
        }
        s = addLink(s, line);
        return s;
        
    }
    public static String removeLink(String line){
        String s = line;
        String middle = "https";
        
         if(s.contains(middle)){
            int ind = 0;
            while(!((s.substring(ind,ind+5)).equals(middle))){
                ind++;
            }
            int midLoc = ind;
            s = s.substring(0,midLoc);
        }
        return s;
    }
    public static String addLink(String s, String line){
        //String ss  = s;
        String middle = "https";
        
         if(line.contains(middle)){
            int ind = 0;
            while(!((line.substring(ind,ind+5)).equals(middle))){
                ind++;
            }
            int midLoc = ind;
            String link = line.substring(midLoc);
            s = s + link+"\n";
        }
        return s;
    }
    
}