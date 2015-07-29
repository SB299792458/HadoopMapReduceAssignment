package mapred;

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
	int WMAX=2;
	//IntWritable count=new IntWritable(0);
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
	    	//  if(count.get()<WMAX.get() && count.get()!=-1)
	    	//  {
	    	  tm.put(e, wordname);
	    	//  count.set(count.get()+1);
	    	//  }
	    	 /* else
	    	  {
	    		  int temp=count.get();
	    		  count.set(-1);
	    		  tm.put(e, wordname);
	    		  tm.remove(tm.firstKey());
	    		  count.set(temp);
	    	 }*/
			}
    	  }
    	  
     }

	   for(element e : tm.descendingKeySet())
	   	{
		   String str=String.valueOf(e.val)+e.keyphrase;
		   output.collect(key, new Text(str));
	   	}
	 
	   
	   
	   
     }
	  
    }	


