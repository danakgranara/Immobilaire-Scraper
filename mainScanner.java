import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.URL;

public class mainScanner{
    public static void main(String args[]) throws  FileNotFoundException, IOException{
        String locationName;
        int startPageNum;
        int endPageNum;
        String site1;
        String site;
        String textName;
        //user input: first page link, second page link, start page num, end page num
        // System.out.println("what is the link for page 1 of the listings you would like to scrape: ");
        // System.out.println("what is the link for page 2 of the listings you would like to scrape: ");
        // System.out.println("what is the page you would like to start scraping from: ");
        // System.out.println("what is the last page you would like to scrape: ");
        File links = new File("scrapingLinks.txt");
        try (var br = new BufferedReader(new FileReader(links))){
            locationName = br.readLine();
            site1 = br.readLine();
            site = br.readLine();
            String start = br.readLine();
            startPageNum = Integer.parseInt(start);
            String end = br.readLine();
            endPageNum = Integer.parseInt(end);
            textName = br.readLine();
        }


        // startPageNum = 1;
        // endPageNum = 2;
        // site1 = "https://www.immobiliare.it/vendita-appartamenti/venezia/?idMZona[]=168&idMZona[]=170&idMZona[]=169&idMZona[]=10401";
        // site  = "https://www.immobiliare.it/vendita-appartamenti/venezia/?pag=2&idMZona[]=168&idMZona[]=170&idMZona[]=169&idMZona[]=10401";

        housingScanner hs = new housingScanner();
        String htmlName = "htmlPage.txt";
        

        int indexPageinLink = findPageInLink(site);

        for(int page = startPageNum; page < endPageNum+1; page++){//broken pages: 32,50
            System.out.println("page "+page);
            if(page == 1){
                site = site1;
            }else{   
                site = replacePageNum(site, page, indexPageinLink);
            }

            var url = new URL(site);
            try (var br = new BufferedReader(new InputStreamReader(url.openStream()))){
                String line;
                FileWriter fw = new FileWriter("htmlPage.txt");
                while((line = br.readLine()) != null){
                    fw.write(line);//write each line of html into the text file
                }
                fw.close();
                hs.printPage(htmlName, textName, locationName);
                //hs.printPageNoName(htmlName, textName);
            }

         }
    }


    public static String replacePageNum(String url, int num, int index){
        String newUrl;
        String start = url.substring(0, index);
        String end = url.substring(index+1);
        Integer numb = num;
        String pageNum = numb.toString();
        newUrl = start+pageNum+end;
        return newUrl;
    }

    public static int findPageInLink(String link){
        String beforePageNum = "pag=";
        int beforeLength = 4;
        int index = 0;
        if(link.contains(beforePageNum)){
            for(int i = 0; i < link.length()-6; i++){
                if(link.substring(i,i+beforeLength).equals(beforePageNum)){
                    index = i+beforeLength;
                }
            }
        }   
        return index;
    }
}