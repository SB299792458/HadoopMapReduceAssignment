package mapred;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.*;
//import org.apache.hadoop.mapred.*;
//import org.apache.hadoop.mapreduce.Reducer.*;
import org.apache.hadoop.mapreduce.*;

public class WordCountReducer extends Reducer<Text, Text, Text, Text> {

    @Override
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
     String filename=""; 
    int sum = 0;
      for (Text value : values) {
    	  String line= value.toString();
    	  filename=line.substring(0,line.indexOf(":"));
    	  int val=Integer.parseInt(line.substring(line.indexOf(":")+1));
        sum += val;
      }
      
      String newline=filename+":"+sum;
      //System.out.println(newline);
      context.write(key, new Text(newline));
      
    }
  }


