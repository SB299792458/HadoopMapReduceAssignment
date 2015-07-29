/*package mapred3;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class WordCountCombiner extends Reducer<Text, Text, Text, Text> {

	int WMAX=3;
	
	
     * 
     * WMAX defined here
     * 
     * 
     * 
     * 
     
	
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
*/