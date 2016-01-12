package org.apache.hadoop.test;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

public class KeyWords {
	
	public static class Map extends MapReduceBase implements
    Mapper<Object, Text, Text, Text> {
	
	
		 public void map(Object key, Text value,
		         OutputCollector<Text, Text> output, Reporter reporter)
		         throws IOException {
		     
		     String line 	    = value.toString();
		     String[] WeiboInfo = line.split("\t");
		     String content     = "";
		     
		    
		 }
	 }
	  public static class Reduce extends MapReduceBase implements
     Reducer<Text, Text, Text, Text> {
		  public void reduce(Text key, Iterator<Text> values,
				  OutputCollector<Text, Text> output, Reporter reporter)
						  throws IOException {  
			 
			  }
			  
//			  output.collect(new Text(key),new Text(nums[0]+"	"+nums[1]));
		 // }
	  }
	  
	  public static void main(String[] args) throws Exception {
	        JobConf conf = new JobConf(DataCleaning.class);
	        conf.setJobName("Event");

	        conf.setOutputKeyClass(Text.class);
	        conf.setOutputValueClass(Text.class);

	        conf.setMapperClass(Map.class);
	        conf.setReducerClass(Reduce.class);
	        
	        conf.setMapOutputKeyClass(Text.class);
	        conf.setMapOutputValueClass(Text.class);
	      
	        conf.setInputFormat(TextInputFormat.class);
	        conf.setOutputFormat(TextOutputFormat.class);

	        FileInputFormat.setInputPaths(conf, new Path(args[0]));
	        Path tmpDir = new Path("/kangshifuEvent");
	        FileOutputFormat.setOutputPath(conf, tmpDir);
	        	      
	        JobClient.runJob(conf);   
	    }


}
