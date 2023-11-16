import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Main{
        public static final int COL_APP = 0;
        public static final int COL_Category = 1;
        public static final int COL_Rating = 2;


    public static void main(String[] args) {
        
        if(args.length<=0)
        {
            System.err.println("Missing book CSV");
            System.exit(1);
        }
        String fileName = args[0];
        System.out.printf("Processing %s%n", fileName);

        try(FileReader fr = new FileReader(fileName))
        {
            BufferedReader br = new BufferedReader(fr);
            int lines = 0;
            Map<String,List<Games>> Organised = br.lines()
            .skip(1)
            .map(row->row.trim().split(","))
            .map(fields->new Games(fields[COL_APP],fields[COL_Category],fields[COL_Rating]))
            .collect(Collectors.groupingBy(games -> games.getCategory().toUpperCase())); //Category is the key of the map

            for(String category : Organised.keySet())
            {
                List<Games> games = Organised.get(category);
                System.out.printf("%s", category);
                int count = 0;
                Map<String,Double> gameMap = new HashMap<>();

                double max_value = 0;
                double averageNum = 0;
                double min_value = 5;

                for(int i = 0 ; i<games.size();i++ )
                
                {   //if rating is >5 or not an integer, discard
                    
                    Games game = games.get(i);
                    Double rating = Double.parseDouble(game.getRating());
                    
                    List<Games> updatedGame = new ArrayList<>();
                    if(!rating.isNaN()|| rating <= 5.0)
                    {   
                        updatedGame.add(game);
                        // System.out.printf("\t%s\n",game.getApp());

                        // games.remove(i);
                        count += 1;
                    }

                    //Setting up the new Map without the NaN and ratings that are >5
                    for(Games upGame : updatedGame)
                    {
                        gameMap.put(upGame.getApp(),Double.parseDouble(upGame.getRating()));

                    }
                
                }

                for(double value: gameMap.values())
                    {
                        if(value>max_value)
                        {
                            max_value = value;
                        }
                    }

                    
                    for(double value: gameMap.values())
                    {
                        if(value<min_value)
                        {
                            min_value=value;
                        }
                    }

                    double total = 0;
                    for (double value: gameMap.values())
                    {   
                        total += value;
                    }

                    averageNum = total/gameMap.size();

                int size_of_game = games.size();
                int discardGame = size_of_game - count;
                System.out.println();
                System.out.print("\tHighest:");
                System.out.println();
                for(Entry<String, Double> entry: gameMap.entrySet()) {

                    // if give value is equal to value from entry
                    // print the corresponding key
                    if(entry.getValue() == max_value) {
                      System.out.printf("\t\t%s , %.1f\n", entry.getKey(),entry.getValue());
                      }
                }
                System.out.println();
                System.out.print("\tLowest:");
                System.out.println();
                for(Entry<String, Double> entry: gameMap.entrySet()) {

                    // if give value is equal to value from entry
                    // print the corresponding key
                    if(entry.getValue() == min_value) {
                      System.out.printf("\t\t%s , %.1f\n", entry.getKey(),entry.getValue());
                      }
                }
                
                System.out.printf("\tAverage: %.1f\n", averageNum);
                System.out.printf("\tCount: %d\n", gameMap.size());
                
                System.out.printf("\tDiscarded: %d\n",discardGame);
                System.out.println();
            }


        }

        catch(Exception e)
        {
            e.printStackTrace();
        }


    }
    

}
