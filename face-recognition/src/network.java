import java.util.*;


public class network
{
  private double[] hiddenLayer = new double[6];
  public double outputLayer;
  
  private double[][] weights1 = new double[15360][6];
  private double[] weights2 = new double[6];
  double alpha = 0.00003;
  void train(LinkedList<img> list)
  {
    int num = 300;
    int count;
   
    for(int n = 0; n < num; n++)
    {
      for(int i = 0; i < list.size(); i++)
      {
      
        for(int j = 0; j < 6; j++)// hidden layer
        {
          double sum = 0;
          for(int k = 0; k < 15360; k++)
            sum += list.get(i).res[k] * weights1[k][j];
          
          sum = 1.0/(1.0 + Math.exp(-sum));
          hiddenLayer[j] = sum;
        }
          double sum = 0;
          for(int k = 0; k < 6; k++)
            sum += hiddenLayer[k] * weights2[k];
        
        
          sum = 1.0/(1.0 + Math.exp(-sum));
        outputLayer = sum;
        
        // bp output-hidden
        double delta2;
        delta2 = outputLayer-list.get(i).label;
      
        
        for(int j = 0; j < 6; j++)
            weights2[j] -= alpha*delta2*hiddenLayer[j];
        
        //hidden-input
        double[] delta1 = new double[6];
        for(int j = 0; j < 6; j++)
            delta1[j] += -delta2 * weights2[j] * (1-hiddenLayer[j])*hiddenLayer[j];
      
        
       
        for(int j = 0; j < 15360; j++)
          for(int k = 0; k < 6; k++)
            weights1[j][k] -= alpha * delta1[k] * list.get(i).res[j];
          
        
        }//for1
    
      count = 0;
      int c1 = 0, c2 = 0;
      double error = 0;
      for(int i = 0; i < list.size(); i++)
      {
        for(int j = 0; j < 6; j++)// hidden layer
        {
          double sum = 0;
          for(int k = 0; k < 15360; k++)
            sum += list.get(i).res[k] * weights1[k][j];
          
          sum = 1.0/(1.0 + Math.exp(-sum));
          hiddenLayer[j] = sum;
        }
        double sum = 0;
        for(int k = 0; k < 6; k++)
          sum += hiddenLayer[k] * weights2[k];
        
        
        sum = 1.0/(1.0 + Math.exp(-sum));
        outputLayer = sum;
        error += (sum - list.get(i).label)*(sum - list.get(i).label);
        if(outputLayer >= 0.5 && list.get(i).label == 1){
          count++;
          c1++;
        }
        else if (outputLayer < 0.5 && list.get(i).label == 0){
          c2++;
          count++;
        }
        
      }//for2

      System.out.println(count+" error is: " + error/list.size() + "male: " + c1 + " female: " + c2);
    
    }//num of train
  }//train
  
}//class