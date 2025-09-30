import java.io.*;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.lang.Integer;

public class Main {
    public static void main(String[] args) {
        try{
            Scanner scn=new Scanner(new File("MOVEIS.txt"));
            Scanner numScn=new Scanner(new File("MOVEIS.txt"));
            int lineNum=0;
            String currLine=numScn.nextLine();
            while(numScn.hasNextLine() && !Objects.equals(currLine, "END OF LIST")){
                lineNum++;
                currLine=numScn.nextLine();
            }
            numScn.close();
            System.out.println(lineNum+" movies to be queued");

            PriorityQueue<Movie> pq=new PriorityQueue<>();

            for(int i=0;i<lineNum;i++){
                scn.useDelimiter(",");
                String name=scn.next();
                scn.useDelimiter(" ");
                scn.next();
                double qual=scn.nextDouble();
                double want=scn.nextDouble();
                String tempStr=scn.nextLine();
                int watched=Integer.valueOf(tempStr.stripLeading());
                Movie tempmv=new Movie(name,qual,want,watched);
                pq.add(tempmv);
            }
            scn.close();

            while(!pq.isEmpty()){
                Movie temp=pq.remove();
                System.out.println(temp.toString());
            }
        } catch (IOException e){
            System.out.println("no file");
            e.printStackTrace();
        }
    }
}

class Movie implements Comparable<Movie>{
    String name;
    double qual;
    double want;
    Double avg;
    boolean watched;
    double priority;

    public Movie(String nname,double qqual,double wwant,int wwatched){
        name=nname;
        qual=qqual;
        want=wwant;
        avg=(qual*(want*2))/3;
        watched=(wwatched!=0);
    }

    public String toString(){
        return "Name: "+name+" quality: "+qual+" want: "+want+" average: "+avg+" watched: "+watched;
    }

    public int compareTo(Movie mv) {
        return this.priority.compareTo(mv.priority);
    }
}

/*File olist=new File("MOVEIS.txt");
BufferedReader reader=new BufferedReader(new FileReader(olist));
File nlist=new File("temp.txt");
BufferedWriter writer=new BufferedWriter(new FileWriter(nlist));
reader.close();
writer.close();*/