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

public class EventProbe {
	 public static class Map extends MapReduceBase implements
     Mapper<Object, Text, Text, Text> {
	
	
		 public void map(Object key, Text value,
		         OutputCollector<Text, Text> output, Reporter reporter)
		         throws IOException {
		     
		     String line 	    = value.toString();
		     String[] WeiboInfo = line.split("\t");
		     String content     = "";
		     if(WeiboInfo[4].equals("0") && (WeiboInfo[9].indexOf("阅兵") != -1 
		    		 || WeiboInfo[9].indexOf("抗日战争胜利70周年") !=-1 || WeiboInfo[9].indexOf("反法西斯胜利70周年") !=-1  
		    		 || WeiboInfo[9].indexOf("反法西斯战争胜利70周年") !=-1)){
		    	 content = "1	0";
		     }
		     if(WeiboInfo[4].equals("1") && (WeiboInfo[14].indexOf("阅兵") != -1 
		    		 || WeiboInfo[14].indexOf("抗日战争胜利70周年") !=-1 || WeiboInfo[14].indexOf("反法西斯胜利70周年") !=-1  
		    		 || WeiboInfo[14].indexOf("反法西斯战争胜利70周年") !=-1)){
		    	 content = "0	1";
		     }
		    
		     if(!content.equals("")){
		    	 output.collect(new Text(WeiboInfo[0]), new Text(content));
		     }		     
		 }  
	 }
	 
	  public static class Reduce extends MapReduceBase implements
      Reducer<Text, Text, Text, Text> {
		  public void reduce(Text key, Iterator<Text> values,
				  OutputCollector<Text, Text> output, Reporter reporter)
						  throws IOException {  
			  int[] nums = new int[2];
			  String[] array = null;
			  while(values.hasNext()){
				  String str = values.next().toString();
				  array = str.split("\t");
				  nums[0] += Integer.parseInt(array[0]);
				  nums[1] += Integer.parseInt(array[1]);
			  }
			  
			  output.collect(new Text(key),new Text(nums[0]+"	"+nums[1]+"	"+(nums[0]+nums[1])));
		  }
	  }
	  
	  public static class AnalyseMap extends MapReduceBase implements
	     Mapper<Object, Text, Text, Text> {
		
		
			 public void map(Object key, Text value,
			         OutputCollector<Text, Text> output, Reporter reporter)
			         throws IOException {
			     
			     String line 	    = value.toString();
			     String[] WeiboInfo = line.split("\t");
			     String content     = "";
			     if(WeiboInfo[4].equals("0") && (WeiboInfo[9].indexOf("阅兵") != -1 
			    		 || WeiboInfo[9].indexOf("抗日战争胜利70周年") !=-1 || WeiboInfo[9].indexOf("反法西斯胜利70周年") !=-1  
			    		 || WeiboInfo[9].indexOf("反法西斯战争胜利70周年") !=-1)){
			    	 content = WeiboInfo[0]+"	"+WeiboInfo[9]+"		";
			    	 output.collect(new Text(WeiboInfo[3]), new Text(content));
			     }
			     if(WeiboInfo[4].equals("1") && (WeiboInfo[14].indexOf("阅兵") != -1 
			    		 || WeiboInfo[14].indexOf("抗日战争胜利70周年") !=-1 || WeiboInfo[14].indexOf("反法西斯胜利70周年") !=-1  
			    		 || WeiboInfo[14].indexOf("反法西斯战争胜利70周年") !=-1)){
			    	 content = WeiboInfo[0]+"	"+WeiboInfo[14]+"	"+WeiboInfo[10];
			    	 output.collect(new Text(WeiboInfo[19]), new Text(content));
			     }			    	     
			 }  
		 }
		 
		  public static class AnalyseReduce extends MapReduceBase implements
	      Reducer<Text, Text, Text, Text> {
			  public void reduce(Text key, Iterator<Text> values,
					  OutputCollector<Text, Text> output, Reporter reporter)
							  throws IOException {  
				  
				  while(values.hasNext()){
					  String str = values.next().toString();
					  output.collect(new Text(key),new Text(str));
				  }			  				  
			  }
		  }
	  
	  
	  public static void main(String[] args) throws Exception {
	        JobConf conf = new JobConf(DataCleaning.class);
	        conf.setJobName("Event1");
 
	        conf.setOutputKeyClass(Text.class);
	        conf.setOutputValueClass(Text.class);

	        conf.setMapperClass(Map.class);
	        conf.setReducerClass(Reduce.class);
	        
	        conf.setMapOutputKeyClass(Text.class);
	        conf.setMapOutputValueClass(Text.class);
	      
	        conf.setInputFormat(TextInputFormat.class);
	        conf.setOutputFormat(TextOutputFormat.class);

	        FileInputFormat.setInputPaths(conf, new Path("/data"));
	        Path tmpDir = new Path("/yuebingEvent1");
	        FileOutputFormat.setOutputPath(conf, tmpDir);
	        
	        JobConf conf1 = new JobConf(DataCleaning.class);
	        conf1.setJobName("Event2");
 
	        conf1.setOutputKeyClass(Text.class);
	        conf1.setOutputValueClass(Text.class);

	        conf1.setMapperClass(AnalyseMap.class);
	        conf1.setReducerClass(AnalyseReduce.class);
	        
	        conf1.setMapOutputKeyClass(Text.class);
	        conf1.setMapOutputValueClass(Text.class);
	      
	        conf1.setInputFormat(TextInputFormat.class);
	        conf1.setOutputFormat(TextOutputFormat.class);

	        FileInputFormat.setInputPaths(conf1, new Path("/data"));
	        FileOutputFormat.setOutputPath(conf1, new Path("/yuebingEvent2"));
	        	      
	        JobClient.runJob(conf);  
	        JobClient.runJob(conf1);  
	    }

}
