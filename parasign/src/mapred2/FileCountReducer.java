package mapred2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.TreeMap;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SortedMapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
//import org.apache.hadoop.mapreduce.Reducer;
//import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapred.*;
class element{
	
	double val;
	String keyphrase;
	element(double val2 ,String  keyphrase2)
	{
		val=val2;
		keyphrase=keyphrase2;
	}


}

class MyComparator implements Comparator<element>{
	
		@Override
		public int compare(element arg0, element arg1) {
			
			if(arg0.val > arg1.val)
				return 1;
			else if(arg0.val > arg1.val)
				return -1;
			else
			{
				return arg0.keyphrase.compareTo(arg1.keyphrase);
			}

		}
	}

public class FileCountReducer extends MapReduceBase  implements Reducer<Text, Text, Text, Text> {
	
	@Override
	public void reduce(Text key, Iterator<Text> arg1,
			OutputCollector<Text, Text> output, Reporter arg3) throws IOException {
	
	String wordname=""; 
   
     TreeMap<element,String> tm= new TreeMap<element,String>(new MyComparator()); 
     
     //SortedMapWritable stm=new SortedMapWritable();
     while(arg1.hasNext()) {
    	 Text value = arg1.next();
    	  String line= value.toString();
    	
    	  if(line.length()>0)
    	  {
		  if(line.indexOf(":")!=-1)
		  	{
			  wordname=line.substring(0,line.indexOf(":"));
			  double val=Double.parseDouble(line.substring(line.indexOf(":")+1));
			  
			//  System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
	    	//  System.out.println(line);
			  
	    	  element e= new element(val,wordname);
	    	  tm.put(e, wordname);
	    	
			}
    	  }
    	  
     }

     int count=1;
	   for(element e : tm.descendingKeySet())
	   	{
		   //below line to check the decreasing orders
		  // String str=String.valueOf(e.val)+e.keyphrase;
		  String str="#"+count+"#"+e.keyphrase;
		   output.collect(key, new Text(str));
		   count++;
	   	}
	 
	   
	   
	   
     }
	  
    }	


