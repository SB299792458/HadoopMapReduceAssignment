package mapred3;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.*;
//import org.apache.hadoop.mapred.*;
//import org.apache.hadoop.mapreduce.Reducer.*;
import org.apache.hadoop.mapreduce.*;

public class WordCountReducer extends Reducer<Text, Text, Text, Text> {

	int FMAX=3;
	
	 /*
     * 
     * FMAX defined here
     * 
     * 
     * 
     * 
     */
	
    @Override
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
    // String filename=""; 
    //int sum = 0;
    	int count=0;
    	String filelist="-> ";
      for (Text value : values) {
    	  count++;
    	  String line= value.toString();
    	  filelist=filelist + line +" , ";
    	  
    	  if(count==FMAX)
    		  break;
    	 
      }
      
     // String newline=filename+":"+sum;
      //System.out.println(newline);
      context.write(key, new Text(filelist));
      
    }
  }


