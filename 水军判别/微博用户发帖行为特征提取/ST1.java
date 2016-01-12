package com.dingyang.apps.step1;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.StringTokenizer;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class ST1 {

  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, Text>{

    //private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
    private Text val = new Text();
   

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      String itr = new String(value.toString());
      String[] itr_s = itr.split("	");
      int len = itr_s.length;
      if(len>=20){
    	int flag=0;  
    	StringBuffer a = new StringBuffer();
        word.set(itr_s[3]);
        a.append("0"+itr_s[6]+"	");
        a.append("0"+itr_s[9]+"	");
        a.append("0"+itr_s[10]+"	");
        a.append("0"+itr_s[11]+"	");
        a.append("0"+itr_s[19]+"	");
        if(itr_s[15].length()==16){
	        a.append("0"+itr_s[15].substring(0, 4)
	        		+itr_s[15].substring(5, 7)
	        		+itr_s[15].substring(8, 10)
	        		+itr_s[15].substring(11, 13)
	        		+itr_s[15].substring(14, 16)+"	");
        }else{
        	flag=1;
        }
        a.append("0"+itr_s[2]+"	");
        a.append("0"+itr_s[16]+"	");
        a.append("$");

       
        val.set(a.toString());
        a.setLength(0);
        if(flag==0){
        	context.write(word,val);
        }
        
      
    }
  }
  }

  public static class IntSumReducer
       extends Reducer<Text,Text,Text,Text> {
    //private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<Text> values,
                       Context context
                       ) throws IOException, InterruptedException {
     
     /* for (Text val : values) {
    	  context.write(key,val);
      }*/
       Text tmp = new Text();
       String a = new String();
      
       HashMap<String, Integer> map2 = new HashMap<String, Integer>();
       for(Text val : values){
    	 
    	   a = val.toString();
    	   if(map2.get(a)==null){
    		  map2.put(a, 1);
    		  
    	   }
       }
      Iterator<String> iter =  map2.keySet().iterator();
      while(iter.hasNext()){
    	  tmp.set(iter.next());
    	  context.write(key, tmp);
      }
    }
  }
  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "weibo_data statistics");
    job.setJarByClass(ST1.class);
    job.setMapperClass(TokenizerMapper.class);
    //job.setCombinerClass(IntSumReducer.class);
    job.setReducerClass(IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}