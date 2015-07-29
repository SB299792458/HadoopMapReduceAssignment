/*package mapred;


import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat;
import org.apache.hadoop.mapred.*;
//import org.apache.hadoop.mapred.lib.output.FileOutputFormat;

public class WordCount2 {


	static class MultiFileOutput extends MultipleTextOutputFormat<Text, Text> {
        protected String generateFileNameForKeyValue(Text key, Text value,String name) {
                return key.toString();
        }
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Path outputPath1 = new Path(args[2]);
		    
		   // Configuration conf2 = getConf();
		   // Configuration temp=new Configuration(conf2);   
		   // Job job2 = new Job(conf2, this.getClass().toString());
		    JobConf job2 = new JobConf(getConf(), WordCount.class);
		    
		    job2.setJobName("FileCount");
		    job2.setJarByClass(WordCount.class);
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
		    FileInputFormat.addInputPath(job2, new Path (args[1]));
		    FileOutputFormat.setOutputPath(job2, outputPath1);

		    JobClient.runJob(job2);
		    //return 0;
		    //return job2.waitForCompletion(true) ? 0 : 1;
		  
		  }


	}


*/