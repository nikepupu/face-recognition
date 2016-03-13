import java.util.*;
import java.io.*;
import java.awt.*;

public class network
{
  private int[] inputLayer = new int[15360];
  private double[] hiddenLayer = new double[12];
  public double outputLayer;
  
  private double[][] weights1 = new double[15360][12];
  private double[] weights2 = new double[12];
  double alpha = 0.1;
  void train(LinkedList<img> list)
  {
    
    int num = 1000;
    int count;
    for(int n = 0; n < num; n++)
    {
      count = 0;
      for(int i = 0; i < list.size(); i++)
      {
        System.arraycopy(list.get(i).res, 0, inputLayer, 0, 15360);
      
        for(int j = 0; j < 12; j++)// hidden layer
        {
          double sum = 0;
          for(int k = 0; k < 15360; k++)
          {
            sum += inputLayer[k] * weights1[k][j];
          }
        
          hiddenLayer[j] = sum;
        }
          double sum = 0;
          for(int k = 0; k < 12; k++)
            sum += hiddenLayer[k] * weights2[k];
          sum = 1./(1. + Math.exp(-sum));
          outputLayer = sum;
          
        
        if (outputLayer > 0.5 && list.get(i).label == 1)
          count++;
        else if (outputLayer < 0.5 && list.get(i).label == 0)
          count++;
        // bp output-hidden
        double delta2;
        delta2 = -(list.get(i).label - outputLayer)*(1-outputLayer)*outputLayer;
        
        for(int j = 0; j < 12; j++)
            weights2[j] = weights2[j] + alpha*delta2*hiddenLayer[j];
        
        //hidden-input
        double[] delta1 = new double[12];
      
        for(int j = 0; j < 12; j++)
            delta1[j] += - delta2 * weights2[j] * (1-hiddenLayer[j])*hiddenLayer[j];
      
      
        for(int j = 0; j < 15360; j++)
          for(int k = 0; k < 12; k++)
            weights1[j][k] =weights1[j][k] + alpha * delta1[k] * inputLayer[j];
        
        }//for
      System.out.println(count);
    }//num of train
  }//train
  
  
  
}//class