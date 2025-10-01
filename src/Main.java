import java.io.*;
import java.util.Collections;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.lang.Integer;

enum sort{
    QUAL,WANT,AVG
}

public class Main {
    public static void main(String[] args) {
        Scanner inScn=new Scanner(System.in);
        System.out.println("sort by quality (QUAL), want (WANT), or average (AVG)");
        String inp=inScn.nextLine();
        sort srt;
        switch (inp) {
            case "quality", "qual", "QUAL" -> {
                System.out.println("sorting by quality");
                srt = sort.QUAL;
            }
            case "want", "WANT" -> {
                System.out.println("sorting by want");
                srt = sort.WANT;
            }
            case "average", "avg", "AVG" -> {
                System.out.println("sorting by average");
                srt = sort.AVG;
            }
            case null, default -> {
                System.out.println("invalid input, defaulting to average");
                srt = sort.AVG;
            }
        }

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

            PriorityQueue<Movie> pq=new PriorityQueue<>(Collections.reverseOrder());

            for(int i=0;i<lineNum;i++){
                scn.useDelimiter(",");
                String name=scn.next();
                scn.useDelimiter(" ");
                scn.next();
                double qual=scn.nextDouble();
                double want=scn.nextDouble();
                String tempStr=scn.nextLine();
                int watched=Integer.parseInt(tempStr.stripLeading());
                Movie tempmv=new Movie(name,qual,want,watched,srt);
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
    Integer priority;

    public Movie(String nname,double qqual,double wwant,int wwatched, sort srt){
        name=nname;
        qual=qqual;
        want=wwant;
        avg=(qual+(want*2))/3;
        watched=(wwatched!=0);
        double temp;
        if(srt==sort.QUAL){
            temp=qqual*100;
        }
        else if(srt==sort.WANT){
            temp=wwant*100;
        }
        else{
            temp=avg*1000;
        }
        priority=(int)temp;
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