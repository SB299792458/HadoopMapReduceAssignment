package mapred;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat;
//import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

//import org.apache.hadoop.mapred.lib.*;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.util.*;


public class WordCount extends Configured implements Tool {
	
	  public static void main(String args[]) throws Exception {
	    int res = ToolRunner.run(new WordCount(), args);
	    System.exit(res);
	  }

	  public int run(String[] args) throws Exception {
	    Path inputPath = new Path(args[0]);
	    Path outputPath = new Path(args[1]);
	    Configuration conf = getConf();
	    Job job = new Job(conf, this.getClass().toString());
	    FileInputFormat.addInputPath(job, inputPath);
	    FileOutputFormat.setOutputPath(job, outputPath);

	    job.setJobName("WordCount");
	    job.setJarByClass(WordCount.class);
	    job.setInputFormatClass(TextInputFormat.class);
	    job.setOutputFormatClass(TextOutputFormat.class);
	    job.setMapOutputKeyClass(Text.class);
	    job.setMapOutputValueClass(Text.class);
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(Text.class);

	    job.setMapperClass(WordCountMapper.class);
	    job.setCombinerClass(WordCountReducer.class);
	    job.setReducerClass(WordListGenerator.class);

	    
	    //////////////////////////////////////////////////
	    job.waitForCompletion(true);
	   return 0;
}
}