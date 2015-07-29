package mapred;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.*;
//import org.apache.hadoop.mapred.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


public class WordCountMapper extends Mapper<LongWritable, Text, Text, Text> {
   // private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    
    @Override
    public void map(LongWritable key, Text value,
                    Mapper.Context context) throws IOException, InterruptedException {
      String line = value.toString();
      StringTokenizer tokenizer = new StringTokenizer(line);
     
      FileSplit fileSplit = (FileSplit)context.getInputSplit();
      String filename = fileSplit.getPath().getName();
      
      
      while (tokenizer.hasMoreTokens()) {
    	  
    	String keyphrase;
      	String token=tokenizer.nextToken();
          if(token.length()!=0 && !Character.isLetter(token.charAt(0)) && !Character.isDigit(token.charAt(0)) )
				token=token.substring(1, token.length());
			if(token.length()!=0 && !Character.isLetter(token.charAt(token.length()-1)) && 
							!Character.isDigit(token.charAt(token.length()-1)))
				token=token.substring(0, token.length()-1);	
			token=token.trim();
			
			keyphrase=token;
			
			while(token.length()!=0 && Character.isUpperCase(token.charAt(0)))
			{
			if(tokenizer.hasMoreTokens())
				{
				token=tokenizer.nextToken();
				if(token.length()!=0 && !Character.isLetter(token.charAt(0)))
					token=token.substring(1, token.length());
				if(token.length()!=0 && !Character.isLetter(token.charAt(token.length()-1)))
					token=token.substring(0, token.length()-1);		
				token=token.trim();
				
				}
				else
					break;
			
			if(token.length()!=0 && Character.isUpperCase(token.charAt(0)))
				keyphrase=keyphrase + " " + token;	
			}
    	  
    	  
        word.set(keyphrase);
        String mapping1=filename+":"+1;
        Text mapping= new Text(mapping1);
        context.write(word, mapping );
      }
    }
  }

/*

public class WordCountMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable>
{
      //hadoop supported data types
      private final static IntWritable one = new IntWritable(1);
      private Text word = new Text();
    
      //map method that performs the tokenizer job and framing the initial key value pairs
      // after all lines are converted into key-value pairs, reducer is called.
      public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException
      {
            //taking one line at a time from input file and tokenizing the same
            String line = value.toString();
            StringTokenizer tokenizer = new StringTokenizer(line);
            
            
          //iterating through all the words available in that line and forming the key value pair
            while (tokenizer.hasMoreTokens())
            {	String keyphrase;
            	String token=tokenizer.nextToken();
                if(token.length()!=0 && !Character.isLetter(token.charAt(0)) && !Character.isDigit(token.charAt(0)) )
    				token=token.substring(1, token.length());
    			if(token.length()!=0 && !Character.isLetter(token.charAt(token.length()-1)) && 
    							!Character.isDigit(token.charAt(token.length()-1)))
    				token=token.substring(0, token.length()-1);	
    			token=token.trim();
    			
    			keyphrase=token;
    			
    			while(token.length()!=0 && Character.isUpperCase(token.charAt(0)))
				{
				if(tokenizer.hasMoreTokens())
					{
					token=tokenizer.nextToken();
					if(token.length()!=0 && !Character.isLetter(token.charAt(0)))
						token=token.substring(1, token.length());
					if(token.length()!=0 && !Character.isLetter(token.charAt(token.length()-1)))
						token=token.substring(0, token.length()-1);		
					token=token.trim();
					
					}
					else
						break;
				
				if(token.length()!=0 && Character.isUpperCase(token.charAt(0)))
					keyphrase=keyphrase + " " + token;	
				}
		
    			word.set(keyphrase);
    			//sending to output collector which inturn passes the same to reducer
                 output.collect(word, one);
            }
       }
}*/