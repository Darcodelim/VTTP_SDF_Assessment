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

            Map<String,List<Games>> Organised = br.lines()
            .skip(1)
            .map(row->row.trim().split(","))
            .map(fields->new Games(fields[COL_APP],fields[COL_Category],fields[COL_Rating]))
            .collect(Collectors.groupingBy(games -> games.getCategory().toUpperCase())); //Category is the key of the map

            for(String category : Organised.keySet())
            {
                List<Games> games = Organised.get(category);
                System.out.printf("%s (%d)\n", category,games.size());
                int count = 0;
                Map<String,Double> gameMap = new HashMap<>();

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

                    Map.Entry<String,Double> maxEntry = null;
                    for(Map.Entry<String,Double> entry : gameMap.entrySet())
                    {
                        if(maxEntry == null || entry.getValue().compareTo(maxEntry.getValue())>0)
                        {
                            maxEntry = entry;
                        }
                    }
                    System.out.println(maxEntry.getKey());


                    // List<Map.Entry<String,Double>> gameList = new ArrayList<>(gameMap.entrySet());

                    // Collections.sort(gameList, new Comparator<Map.Entry<String,Double>>()
                    // {
                    //     @Override
                    //     public
                    //     int compare(Map.Entry<String,Double> rating1, Map.Entry<String,Double> rating2)
                    //     {
                    //         return rating1.getKey().compareTo(rating2.getKey());
                    //     }
                    // });

                    // for(Entry<String, Double> gameEntry : gameList)
                    // {   
                    //     System.out.println(gameEntry.getKey()+ " " + gameEntry.getValue());
                    // }
    


                

                    
                    // System.out.printf("\t%s\n",game.getApp());
                    // System.out.printf("\t%s\n", game.getRating());
                }
                int size_of_game = games.size();
                int discardGame = size_of_game - count;
                System.out.printf("Discarded: %d\n",discardGame);

                // for(String app: gameMap.keySet())
                // {

                //     System.out.println(app);
                //     System.out.println(gameMap.get(app));
                // }
            }


        }

        catch(Exception e)
        {
            e.printStackTrace();
        }


    }
}