import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.io.FileWriter;
import java.io.BufferedWriter;

// import java.io.FileInputStream;
// import java.io.FileOutputStream;
import java.io.IOException;
 


public class housingScanner{
    static int beforeLinkSize = 117;
    static String beforeLink = "<div class=\"nd-mediaObject__content in-card__content in-realEstateListCard__content\"><a class=\"in-card__title\" href=\"";
    static int afterLinkSize = 2;
    static String afterLink = "\" ";

    static int beforeNameSize = 7;
    static String beforeName = "title=\"";
    static int afterNameSize = 2;
    static String afterName = "\">";
    
    static int beforePriceSize = 208;
    static String beforePrice = "<ul class=\"nd-list nd-list--pipe in-feat in-feat--full in-feat__mainProperty in-realEstateListCard__features\"><li class=\"nd-list__item in-feat__item in-feat__item--main in-realEstateListCard__features--main\">";
    static int beforeFromPriceSize = 68;
    static String beforeFromPrice = "<div class=\"in-realEstateListCard__features--range\"><span>da </span>";
    static int afterPriceSize = 5;
    static String afterPrice = "</li>";

    static int beforeNumRoomsSize = 191;//18389
    static String beforeNumRooms = "<li class=\"nd-list__item in-feat__item\" aria-label=\"locali\"><div class=\"in-feat__data\"><svg viewBox=\"0 0 24 24\" class=\"nd-icon\"><use class=\"nd-icon__use\" xlink:href=\"#planimetry\"></use></svg>";
    static int afterNumRoomsSize = 3;
    static String afterNumRooms = "</d";
    
    static int beforeSizeSize = 197;
    static String beforeSize = "iv></li><li class=\"nd-list__item in-feat__item\" aria-label=\"superficie\"><div class=\"in-feat__data\"><svg viewBox=\"0 0 24 24\" class=\"nd-icon\"><use class=\"nd-icon__use\" xlink:href=\"#size\"></use></svg>";
    static int afterSizeSize = 5;
    static String afterSize = "<span";


    static int linkLength = 44;
    static Location linkLocation;
    static String link;
    static int nameLength;
    static Location nameLocation;
    static String name;
    static int priceLength = 7;
    static Location priceLocation;
    static String price;
    static int numRoomsLength;//1 or 2
    static Location numRoomsLocation;
    static String numRooms;
    static int sizeLength;//2 or 3
    static Location sizeLocation;
    static String size;
    // static int numBathsLength;
    // static Location numBathsLocation;
    // static String numBaths;

    int startLine = 0;
    int startIndex = 0;
    
    //public static void main(String args[]) throws FileNotFoundException, IOException{
    public void printPage(String fileName, String textName, String locationName) throws  FileNotFoundException, IOException{
        startIndex = 0;
            
            BufferedWriter writer1 = new BufferedWriter(new FileWriter(textName, true));
            writer1.write("");
            writer1.close();
            
            

            Location start = new Location(startIndex);
            //for(int i = 0; i < pageLength; i++){
            while(canFindNextListing(fileName, beforePrice, beforePriceSize, start)){
                
                BufferedWriter writer = new BufferedWriter(new FileWriter(textName, true));
                linkLocation = scan(fileName, beforeLink, beforeLinkSize, afterLink, afterLinkSize, start);
                
                //System.out.println(linkLocation.finalString);
                nameLocation = scan(fileName, beforeName, beforeNameSize, afterName, afterNameSize, linkLocation);
                nameLocation.finalString = scanForCityName(nameLocation.finalString, locationName);
                writer.append(nameLocation.finalString);
                writer.append(",");
                //System.out.println(nameLocation.finalString);
                priceLocation = scan(fileName, beforePrice, beforePriceSize, afterPrice, afterPriceSize, nameLocation);
                priceLocation.finalString = scanForPrice(priceLocation.finalString);
                writer.append(priceLocation.finalString);
                writer.append(",");
                //System.out.println(price);
                numRoomsLocation = scan(fileName, beforeNumRooms, beforeNumRoomsSize, afterNumRooms, afterNumRoomsSize, priceLocation);
                writer.append(numRoomsLocation.finalString);
                writer.append(",");
                //System.out.println(numRooms);
                sizeLocation = scan(fileName, beforeSize, beforeSizeSize, afterSize, afterSizeSize, numRoomsLocation);
                writer.append(sizeLocation.finalString);
                writer.append(",");
                writer.append(linkLocation.finalString);
                writer.append("\n");
                //System.out.println(numBaths);
                String row = nameLocation.finalString +" "+ priceLocation.finalString +" "+ numRoomsLocation.finalString +" "+ sizeLocation.finalString +" "+ linkLocation.finalString;
                System.out.println(row);
                //startLine = numBathsLocation.line;
                startIndex = sizeLocation.index;
                start = new Location(startIndex);
                writer.close();
            }
           

    }

    public void printPageNoName(String fileName, String textName) throws  FileNotFoundException, IOException{ 
        
        startIndex = 0;
            
            BufferedWriter writer1 = new BufferedWriter(new FileWriter(textName, true));
            writer1.write("");
            writer1.close();
            
            

            Location start = new Location(startIndex);
            //for(int i = 0; i < pageLength; i++){
            while(canFindNextListing(fileName, beforePrice, beforePriceSize, start)){
                
                BufferedWriter writer = new BufferedWriter(new FileWriter(textName, true));
                linkLocation = scan(fileName, beforeLink, beforeLinkSize, afterLink, afterLinkSize, start);
                
                //System.out.println(linkLocation.finalString);
                 nameLocation = scan(fileName, beforeName, beforeNameSize, afterName, afterNameSize, linkLocation);
                // nameLocation.finalString = scanForCityName(nameLocation.finalString);
                // writer.append(nameLocation.finalString);
                // writer.append(",");
                //System.out.println(nameLocation.finalString);
                priceLocation = scan(fileName, beforePrice, beforePriceSize, afterPrice, afterPriceSize, nameLocation);
                priceLocation.finalString = scanForPrice(priceLocation.finalString);
                writer.append(priceLocation.finalString);
                writer.append(",");
                //System.out.println(price);
                numRoomsLocation = scan(fileName, beforeNumRooms, beforeNumRoomsSize, afterNumRooms, afterNumRoomsSize, priceLocation);
                writer.append(numRoomsLocation.finalString);
                writer.append(",");
                //System.out.println(numRooms);
                sizeLocation = scan(fileName, beforeSize, beforeSizeSize, afterSize, afterSizeSize, numRoomsLocation);
                writer.append(sizeLocation.finalString);
                writer.append(",");
                //System.out.println(size);
                // numBathsLocation = scan(fileName, beforeNumBaths, beforeNumBathsSize, afterBaths, afterBathsSize, sizeLocation);
                // writer.append(numBathsLocation.finalString);
                // writer.append(",");
                writer.append(linkLocation.finalString);
                writer.append("\n");
                //System.out.println(numBaths);
                String row = priceLocation.finalString +" "+ numRoomsLocation.finalString +" "+ sizeLocation.finalString +" "+ linkLocation.finalString;
                System.out.println(row);
                //startLine = numBathsLocation.line;
                startIndex = sizeLocation.index;
                start = new Location(startIndex);
                writer.close();
            }
        // } catch(IOException e){
        //     System.out.println("file not found.");
        //     e.printStackTrace();
        // }
           

    }

    public boolean canFindNextListing(String fileName, String before, int beforeSize,  Location loc) throws FileNotFoundException{
        try{
            boolean canFind = true;
            File f = new File(fileName);
            Scanner scanner = new Scanner(f);
            String s = "";
            s = scanner.nextLine();
            int ind = loc.index;
            while(!(s.substring(ind, ind+beforeSize).equals(before))){
                if(ind+beforeSize >= s.length()){
                    //System.out.println("false");
                    return false;
                }else{
                    ind++;
                }                
            }
            //System.out.println("true");
            return canFind;
        }catch(FileNotFoundException e) {
            System.out.println("file not found.");
            Location beforeLocation = new Location(0);
            return false;
        }
    }

    public Location scan(String fileName, String before, int beforeSize, String after, int afterSize, Location loc) throws FileNotFoundException{
        //scan until you find before, store its location(beforeLocation)
        //scan until you find after, store its location
        //difference between before and after is length of string being scanned for
        try{
            //int beforeLine = 0;
            int beforeIndex = 0;
            String finalString;
            //String fileName = "muranoBurano_page1.txt";
            File f = new File(fileName);
            Scanner scanner = new Scanner(f);
            //get scanner to the line
            String s = "";
            s = scanner.nextLine();
            //System.out.println("test1");
            //int i;
            int ind = loc.index;
            // for(i = 0; i < loc.line; i++){
            //     //System.out.println("line"+i);
            //     s = scanner.nextLine();
            //     //System.out.println("line "+i+":"+s);
            //     beforeLine++;
            // }
            //System.out.println("Starting at line "+beforeLine+", index "+ind);
            //System.out.println("test2");
            //make that line a string
            
            //if line is too short, go to next, until a long enough line to check through is available
            // while((s.length()-1)<(ind+beforeSize)){
            //     //System.out.println("line "+beforeLine+":"+s);
            //     //System.out.println("line "+beforeLine+ " is "+s.length()+" long. The string being compared is "+(ind+beforeSize)+" long");
            //     s = scanner.nextLine();
            //     ind = 0;
            //     beforeLine++;
            // }
            //System.out.println("test3");
            //System.out.println("line "+beforeLine+":"+s);
            //check through the line
            while(!(s.substring(ind, ind+beforeSize).equals(before))){
                //if there is not enough line left to check for the substring, go to next possible line
                // while((ind+beforeSize) >= (s.length()-1)){
                //     s = scanner.nextLine();//if its not in that line, go to beginning of next line
                //     ind = 0;
                //     //System.out.println("String is not in line "+beforeLine);
                //     beforeLine++;
                //     //
                // }
                ind++;//go thru every substring
                
            }
            beforeIndex = ind;
            //System.out.println("Before string was found at line "+beforeLine+", index "+beforeIndex+". The string was found at index "+(beforeIndex+beforeSize));
            
            scanner.close();
            //System.out.println("test4: finding length of the After String");
            int afterIndex = beforeIndex+beforeSize;//startlooking for the after string at the end of the before string
            //System.out.println("Looking for "+after);
            while(!(s.substring(afterIndex, afterIndex+afterSize).equals(after))){
                afterIndex++;
            }
            //int afterLine = beforeLine;
            //System.out.println("After string was found at line "+afterLine+", index "+afterIndex);
            
            int middleLength = afterIndex - (beforeIndex+beforeSize);
            finalString = s.substring(beforeIndex+beforeSize, beforeIndex+beforeSize+middleLength);
            Location beforeLocation = new Location(afterIndex+afterSize, middleLength, finalString);
            //System.out.print("found: "+finalString+", ");
            return beforeLocation;
        } catch(FileNotFoundException e) {
            System.out.println("file not found.");
            Location beforeLocation = new Location(0);
            return beforeLocation;
        }
    }


    public static String scanForCityName(String longName, String locationName){
        //String newName = longName;
        String end = ", " + locationName;
        int endLength = 9;
        String start = ", ";
        int startLength = 2;
        if(longName.contains(end)){//if it contains , Venezia / if it doesnt contain it, it will just keep whole name
            int ind = longName.length()-endLength;
            while(!(longName.substring(ind, (ind+endLength)).equals(end))){
                ind--;
            }
            int endLoc = ind;
            String newName = longName.substring(0, endLoc);

            if(newName.contains(start)){
                //work backwards from endLoc to find start
                ind--;
                while(!(longName.substring(ind, (ind+startLength)).equals(start))){
                    ind--;
                }
                int startLoc = ind;

                longName = longName.substring((startLoc+startLength), endLoc);
            }
        }
        return longName;
    }
    public static String removeDiv(String s){
        String div1 = ",00</div>";
        if(s.contains(div1)){
            //replace it with ","
            s = s.replace(div1,"");
        }
        String div2 = "</div>";
        if(s.contains(div2)){
            //replace it with ","
            s = s.replace(div2,"");
        }
        return s;
    }
    public static String scanForFromPrice(String longName){
        String newName = longName;
        if(newName.contains(beforeFromPrice)){
            newName = newName.substring(beforeFromPriceSize);
        }
        return newName;
    }
    public static String removeEuro(String longName){
        String newName = longName;
        String euro = "€";
        if(newName.contains(euro)){
            newName = newName.substring(2);
        }
        return newName;
    }
     public static String scanForPrice(String longName){
        String newName = scanForFromPrice(longName);
        //String finalName = "";
        String end = "/mese";
        int endLength = 5;
        String middle = ".";
        int middleLength = 1;
        if(newName.contains(end)){//if it contains , /mese
            int ind = 0;
            while(!(newName.substring(ind, (ind+endLength)).equals(end))){
                ind++;
            }
            int endLoc = ind;
            //work backwards from endLoc to find start
            ind--;
            
            int startLoc = 0;

            newName = newName.substring((startLoc), endLoc);
        }
        //System.out.println(newName);
        if(newName.contains(middle)){//get rid of period
            int ind = 0;
            while(!((newName.charAt(ind)) == '.')){
                ind++;
            }
            int midLoc = ind;
            newName = newName.substring(0,midLoc)+newName.substring(midLoc+1);
        }
        if(newName.contains(middle)){
            int ind = 0;
            while(!((newName.charAt(ind)) == '.')){
                ind++;
            }
            int midLoc = ind;
            newName = newName.substring(0,midLoc)+newName.substring(midLoc+1);
        }
        //System.out.println(finalName);
        newName = removeEuro(newName);
        newName = removeDiv(newName);
        return newName;
    }
    
}