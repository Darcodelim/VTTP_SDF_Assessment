package task02.src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

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
         while((Serverline = br.readLine()) != null)
         {
             System.out.printf(">>Output: %s\n", Serverline);
             String[] array = Serverline.split(" ");
             if(array[0].equals("item_count:") )
             {
                itemCount = Integer.parseInt(array[1]);
                System.out.println(itemCount);
             }
             else if(array[0].equals("prod_end"))
             {
                count+=1;
             }

             else if(count == itemCount)
            {
                break;
            }
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

