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

public class DataCleaning {

    public static class Map extends MapReduceBase implements
            Mapper<Object, Text, Text, Text> {
    	
    	
        public void map(Object key, Text value,
                OutputCollector<Text, Text> output, Reporter reporter)
                throws IOException {
            String valueStr    = "";
            String line 	   = value.toString();
            String[] WeiboInfo = line.split("\t");
            int length         = WeiboInfo.length;
            StringBuffer content = new StringBuffer(WeiboInfo[10]);
            int count = 9;
           
         	while( content.indexOf("<a") != -1 && count > 0){
         		if(content.indexOf("</a>")+3 < content.length()-1
         				&& content.indexOf("<a") < content.indexOf("</a>")){
         			content.delete(content.indexOf("<a"), content.indexOf("</a>")+4);
         		}else if(content.indexOf("</a>")+3 == content.length()-1
         				&& content.indexOf("<a") < content.indexOf("</a>")){
         			content = new StringBuffer(content.substring(0, content.indexOf("<a")));
         			break;
         		}
         		count--;
         		 
         	}
        
         	count = 9;
         	while(content.indexOf("<img") != -1 && count > 0){
         		if(content.indexOf(">") < content.length()-1
         				&& content.indexOf("<img") < content.indexOf(">")){
         			content.delete(content.indexOf("<img"), content.indexOf(">")+1);
         		}else if(content.indexOf(">") == content.length()-1
         				&& content.indexOf("<img") < content.indexOf(">")){
         			content = new StringBuffer(content.substring(0, content.indexOf("<img")));
         			break;
         		}
         		count--;
         	}
         	
         	while(content.indexOf("//:")!=-1){
          		if (content.indexOf("//:")+2 < content.length()-1){
          			content.delete(content.indexOf("//:"), content.indexOf("//:")+3);
          		}else{
          			content = new StringBuffer(content.substring(0, content.indexOf("//:")));
          		}
          		
          	}
         	
            WeiboInfo[10] = content.toString();
            
            for(int m=1; m<length; m++){
            	 valueStr += WeiboInfo[m]+"	";
            }
            
             output.collect(new Text(WeiboInfo[4]),new Text(valueStr));
              
        }
        

    }
    
    public static class CleanMap extends MapReduceBase implements
    Mapper<Object, Text, Text, Text> {
    	
    	 public void map(Object key, Text value,
                 OutputCollector<Text, Text> output, Reporter reporter)
                 throws IOException {
             String valueStr    = "";
             String line 	    = value.toString();
             String[] WeiboInfo = line.split("\t");
             int length         = WeiboInfo.length;
             StringBuffer content = new StringBuffer(WeiboInfo[14]);
             int count = 9;
            
          	while( content.indexOf("<a") != -1 && count > 0){
          		if(content.indexOf("</a>")+3 < content.length()-1
          				&& content.indexOf("<a") < content.indexOf("</a>")){
          			content.delete(content.indexOf("<a"), content.indexOf("</a>")+4);
          		}else if(content.indexOf("</a>")+3 == content.length()-1
          				&& content.indexOf("<a") < content.indexOf("</a>")){
          			content = new StringBuffer(content.substring(0, content.indexOf("<a")));
          			break;
          		}
          		count--;
          		 
          	}
         
          	count = 9;
          	while(content.indexOf("<img") != -1 && count > 0){
          		if(content.indexOf(">") < content.length()-1
          				&& content.indexOf("<img") < content.indexOf(">")){
          			content.delete(content.indexOf("<img"), content.indexOf(">")+1);
          		}else if(content.indexOf(">") == content.length()-1
          				&& content.indexOf("<img") < content.indexOf(">")){
          			content = new StringBuffer(content.substring(0, content.indexOf("<img")));
          			break;
          		}
          		count--;
          	}
          	
          	while(content.indexOf("//:")!=-1){
           		if (content.indexOf("//:")+2 < content.length()-1){
           			content.delete(content.indexOf("//:"), content.indexOf("//:")+3);
           		}else{
           			content = new StringBuffer(content.substring(0, content.indexOf("//:")));
           		}
           		
           	}
          	
             WeiboInfo[14] = content.toString();
             
             for(int m=0; m<length; m++){
             	 valueStr += WeiboInfo[m]+"	";
             }
             
              output.collect(new Text(WeiboInfo[3]),new Text(valueStr));
               
         }
      
    }
    
    public static class CleanReduce extends MapReduceBase implements
    	Reducer<Text, Text, Text, Text> {
    	public void reduce(Text key, Iterator<Text> values,
    			OutputCollector<Text, Text> output, Reporter reporter)
    					throws IOException {  

			while(values.hasNext()){
	    		String str = values.next().toString();
	    		output.collect(new Text(str),new Text(""));
	    		}
	    	}
    }
    
   

    public static class Reduce extends MapReduceBase implements
            Reducer<Text, Text, Text, Text> {
        public void reduce(Text key, Iterator<Text> values,
                OutputCollector<Text, Text> output, Reporter reporter)
                throws IOException {  
     
	    	while(values.hasNext()){
	    		String str = values.next().toString();
	    		output.collect(new Text(str),new Text(""));
	    		}
	    	}
    }
  
    public static void main(String[] args) throws Exception {
        JobConf conf = new JobConf(DataCleaning.class);
        conf.setJobName("dataCleaning");

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);

        conf.setMapperClass(Map.class);
        conf.setReducerClass(Reduce.class);
        
        conf.setMapOutputKeyClass(Text.class);
        conf.setMapOutputValueClass(Text.class);
      
        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(conf, new Path(args[0]));
        Path tmpDir = new Path("/tempo");
        FileOutputFormat.setOutputPath(conf, tmpDir);
        
        JobConf conf1 = new JobConf(DataCleaning.class);
        conf1.setJobName("dataCleaning2");

        conf1.setOutputKeyClass(Text.class);
        conf1.setOutputValueClass(Text.class);

        conf1.setMapperClass(CleanMap.class);
        conf1.setReducerClass(CleanReduce.class);
        
        conf1.setMapOutputKeyClass(Text.class);
        conf1.setMapOutputValueClass(Text.class);
      
        conf1.setInputFormat(TextInputFormat.class);
        conf1.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(conf1, new Path("/tempo"));
        FileOutputFormat.setOutputPath(conf1, new Path("/data"));

        JobClient.runJob(conf);
        JobClient.runJob(conf1);
        
   
    }
}

