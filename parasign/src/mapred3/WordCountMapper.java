package mapred3;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.*;
//import org.apache.hadoop.mapred.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


public class WordCountMapper extends Mapper<LongWritable, Text, Text, Text> {
   // private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
    
    int WMAX=1;
	
	/*
    * 
    * WMAX defined here
    * 
    * 
    * 
    * */
    
    
    @Override
    public void map(LongWritable key, Text value,
                    Mapper.Context context) throws IOException, InterruptedException {
      String line = value.toString();
      StringTokenizer pre=new StringTokenizer(line,"#");
      pre.nextToken();
      int count=Integer.parseInt(pre.nextToken());
      
      line=pre.nextToken();
      StringTokenizer tokenizer = new StringTokenizer(line);
     
      FileSplit fileSplit = (FileSplit)context.getInputSplit();
      String filename = fileSplit.getPath().getName();
      
      if(count<=WMAX)
      {
      
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
    	  
    	if(keyphrase.equals(filename)==false)  
    	{
    	word.set(keyphrase);
    	//String mapping1=filename+":"+1;
        //Text mapping= new Text(mapping1);
    	Text mapping= new Text(filename);
    	context.write(word, mapping );
    	
    	}
      }
    }
  }
}