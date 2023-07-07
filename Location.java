public class Location{
    //int line;
    int index;
    int length;
    String finalString;

    public Location(int index){
        //this.line = line;
        this.index = index;
        this.length = 0;
        this.finalString = "";
    }
    public Location(int index, int length, String finalString){
        //this.line = line;
        this.index = index;
        this.length = length;
        this.finalString = finalString;
    }
}