import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try{
            File olist=new File("MOVEIS.txt");
            FileWriter nlist=new FileWriter("temp.txt", true);





            olist.close();
            nlist.close();
        } catch (IOException e){
            System.out.println("no file");
            e.printStackTrace();
        }
    }
}