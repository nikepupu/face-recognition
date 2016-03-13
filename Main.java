import java.util.*;
import java.io.*;
import java.awt.*;

public class Main
{
  static LinkedList<img> list = new LinkedList<img>();
	public static void main(String[] args)
	{	
		String line = null;
    File folder1 = new File("./img/female");
    File folder2 = new File("./img/male");
    int numOfFiles = 0;
    
    numOfFiles += getFile(folder1, 0);
    numOfFiles += getFile(folder2, 1);
    network net = new network();
    net.train(list);
  
		
	}//Main
  
  public static int getFile(File folder, int type)
  {
    File[] listOfFiles = folder.listFiles();
    Scanner scanner;
    int i = 0, j = 0;
    for (File file : listOfFiles)
    {

      try{
        scanner = new Scanner(file);
        i = 0;
        list.add(new img() );
        while(scanner.hasNextInt())
          list.getLast().res[i++] = scanner.nextInt();
        
      }
      catch(IOException exp) {}
      list.getLast().label = type;
      j++;
      
      
    }
    
    return j;
  }
  

}
