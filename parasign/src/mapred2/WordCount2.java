package mapred2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat;
import org.apache.hadoop.mapred.*;
//import org.apache.hadoop.mapred.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordCount2 extends Configured implements Tool {


	static class MultiFileOutput extends MultipleTextOutputFormat<Text, Text> {
        protected String generateFileNameForKeyValue(Text key, Text value,String name) {
                return key.toString();
        }
}


    public static void main(String[] args) throws Exception
    {
          // this main function will call run method defined above.
      int res = ToolRunner.run(new Configuration(), new WordCount2(),args);
          System.exit(res);
    }
	public int run(String[] args)throws Exception {
		
		Path outputPath1 = new Path(args[1]);
		    
		    JobConf job2 = new JobConf(getConf(), WordCount2.class);
		    
		    job2.setJobName("FileCount");
		    job2.setJarByClass(WordCount2.class);
		    //job2.setInputFormatClass(TextInputFormat.class);
		    //job2.setInputFormat(TextInputFormat.class);
		    
		    //JobConf jobbconf = new JobConf(conf2,WordCount.class);
		    job2.setOutputFormat(MultiFileOutput.class);
			
		    // job2.setOutputFormatClass(TextOutputFormat.class);
		  //  job2.setMapOutputKeyClass(Text.class);
		  //  job2.setMapOutputValueClass(Text.class);
		    job2.setOutputKeyClass(Text.class);
		    job2.setOutputValueClass(Text.class);

		    job2.setMapperClass(FileCountMapper.class);
		    //job2.setCombinerClass(FileCountReducer.class);
		    job2.setReducerClass(FileCountReducer.class);
		    FileInputFormat.addInputPath(job2, new Path (args[0]));
		    FileOutputFormat.setOutputPath(job2, outputPath1);

		    JobClient.runJob(job2);
		    return 0;
		    //return job2.waitForCompletion(true) ? 0 : 1;
		  
		  }


	}


