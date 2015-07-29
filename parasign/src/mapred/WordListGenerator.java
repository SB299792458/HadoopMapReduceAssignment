package mapred;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Reducer.Context;

import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class WordListGenerator extends Reducer<Text, Text, Text, Text> {

	 public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		 		 {
				 int count=0;
				 String tfList= "->";
				 
				 
				 for (Text value : values) {
					 String line= value.toString();
			    	 String filename=line.substring(0,line.indexOf(":"));
			    	 int val=Integer.parseInt(line.substring(line.indexOf(":")+1));
					 
			    //	 double val2= (double)val * count;
					 
					 tfList=tfList+ filename+":"+String.valueOf(val)+",";
			      }
				 //StringTokenizer counter= new StringTokenizer(tfList,",");
				 //count=counter.countTokens();
				 
				 context.write(key, new Text(tfList));
			    
			 }
	 }
	 }
  

/*public class WordListGenerator extends MapReduceBase implements Reducer<Text, IntWritable, Text, Text > {

	 public void reduce(Text key, Iterator<IntWritable> values,
	  OutputCollector<Text, Text> output, Reporter reporter)throws IOException
	 {
		 int count=0;
		 String tfList= "";
		
		 		
		while (values.hasNext())
         {
			 tfList=tfList+ values.next().get() +",";
	     }
		 
		StringTokenizer st=new StringTokenizer(",");
		count=st.countTokens();
		
         output.collect(key,new Text(tfList));
	 }
}
*/