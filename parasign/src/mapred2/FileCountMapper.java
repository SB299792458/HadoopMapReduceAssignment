package mapred2;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapred.MapReduceBase;
//import org.apache.hadoop.mapreduce.Mapper;
import org.mortbay.log.Log;
import org.apache.hadoop.mapred.*;

public class FileCountMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text>
{
	 private Text word = new Text();

	    
	    @Override
	    public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException
	      {
	    	String line = value.toString();
	    	
	    	StringTokenizer tokenizer = new StringTokenizer(line, "->");
	    	String wordphrase = tokenizer.nextToken().trim();
	    	
	    	line=tokenizer.nextToken();
	    	tokenizer = new StringTokenizer(line, ",");
	    	int count=tokenizer.countTokens();
	    	while(tokenizer.hasMoreTokens())
	    	{
	    		//split filename and tf value for every entry
	    		String toSplit=tokenizer.nextToken();
	    		StringTokenizer t2=new StringTokenizer(toSplit,":");
	  
	    		if(t2.countTokens()==2)
	    		{
	    		String filename=t2.nextToken().trim();//stores filename
	    		String valstring=t2.nextToken().trim();//stores tf value
	    		word.set(filename);//set filename as key value now
    			int val=Integer.parseInt(valstring);
    			double val2=val * Math.log10(30/count);
    			String fileLine=wordphrase + ":" + String.valueOf(val2);
    			//System.out.println(fileLine);
    			output.collect(word, new Text(fileLine) );
    			
	    		}
	    		else
	    			break;
	    			
	    	}
	    	 
	    }

}
