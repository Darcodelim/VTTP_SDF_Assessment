package task02.src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
    int port_no;
    String serverName;
   
        if(args.length<=0)
        {
            port_no = 3000;

        }

        else if(args.length == 1)
        {
            port_no = Integer.parseInt(args[0]);
        }

        else
        {
            port_no = Integer.parseInt(args[1]);
            serverName = args[0];
        }

        try
        
    {
        final Socket sock = new Socket("localhost", port_no);
     final InputStreamReader isr = new InputStreamReader(sock.getInputStream());
      final BufferedReader br = new BufferedReader(isr);
      final OutputStreamWriter osw = new OutputStreamWriter(sock.getOutputStream());
      final BufferedWriter bw = new BufferedWriter(osw);

      final Console cons = System.console();

      boolean stop = false;
      while (!stop) {
         String line = cons.readLine("> ");
         line = line.trim();
         stop = "exit".equals(line);

         bw.write(line + "\n");
         bw.flush();



         String Serverline;
         int count = 0;
         int itemCount = 0;
         double budget;
        List<Product> dataBase= new ArrayList<>();
        
         while((Serverline = br.readLine()) != null)
         {
             System.out.printf(">>Output: %s\n", Serverline);
             

             
             String[] serverArray = Serverline.split(" ",2);

             if(serverArray[0].equals("budget:"))
             {
                budget = Double.parseDouble(serverArray[1]);
             }
             
             if(serverArray[0].equals("item_count:") )
             {
                 itemCount = Integer.parseInt(serverArray[1]);
             }

            if(serverArray[0].equals("prod_start") )
             {
                 Product product = new Product();

                if(serverArray[0].equals("prod_id: ") )
                {
                 product.setiD(serverArray[1]); 
                
                }
                else if(serverArray[0].equals("title: "))
                {
                    product.setProductName(serverArray[1]);
                }

                else if(serverArray[0].equals("price: "))
                {
                    product.setPrice(Integer.parseInt(serverArray[1]));
                }

                
             }

            


            //  // 4 columns: prod_id,title,price,rating
            //  String[][] Products = new String[itemCount][4];
             

            //  for(int i = 0; i<itemCount;i++)
            //  {
            //         if(serverArray[0].equals("prod_id:"))
            //         {   
            //             Products[i][0] = serverArray[1];
                           
            //         }

            //         else if(serverArray[0].equals("title: "))
            //         {Products[i][1] = serverArray[1];}

            //         else if(serverArray[0].equals("price: "))
            //         {Products[i][2] = serverArray[1];}

            //         else if(serverArray[0].equals("rating: "))
            //         {
                        
            //             Products[i][3] =serverArray[1];
                        
            //         }
            //         System.out.println(Products[i][0]);

            //  }

                // bw.write("client_end"+"\n");
                // bw.flush();
         }

         






      }

      // Close the stream
      bw.flush();
      osw.flush();
      osw.close();
      isr.close();
      sock.close();
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }


      
   }
}

