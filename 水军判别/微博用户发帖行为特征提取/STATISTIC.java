package com.dingyang.apps.statistics;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class STATISTIC {

  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, Text>{


    private Text word = new Text();
    private Text val = new Text();

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      String a = new String(value.toString());
      String[] b = a.split("	");
        
        word.set(b[0]);
        val.set(a);
        context.write(word,val);
      
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
       Text value = new Text();
       String a = new String();
       int sum1=0,sum2=0,sum3=0,sum5=0;
       int[] sum4 = new int[6];
       
       HashMap<String, Integer> map = new HashMap<String, Integer>(){
    	   /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("0普通用户",1);
			put("0黄V",2);
	    	put("0蓝V",3);
	    	put("0付费会员",4);
	    	put("0达人",5);
	    	put("0",0);
   
    	   }
       };
       StringBuffer c = new StringBuffer();
       String ss = new String();
       HashMap<String, Integer> map2 = new HashMap<String, Integer>();
      
    	   map2.put("0626", 0);
    	   map2.put("0627", 0);
    	   map2.put("0628", 0);
    	   map2.put("0629", 0);
    	   map2.put("0630", 0);
    	   for(int j=7;j<10;j++){
    		   for(int i=1;i<31;i++){
        		   map2.put("0"+String.valueOf(j*100+i), 0);
        	   }
    	   }
    	   map2.put("0731", 0);
    	   map2.put("0831", 0);
    	   for(int i=1;i<32;i++){
    		   map2.put(String.valueOf(1000+i), 0);
    	   }
    	   map2.put("1101", 0);
    	   map2.put("1102", 0);
    	   map2.put("1103", 0);
    	   map2.put("1104", 0);
    	   
      

       HashMap<String, Integer> map3 = new HashMap<String, Integer>();
       int count_zf=0;
       for(Text val : values){
    	   count_zf++;
    	   a = val.toString();
    	   String[] b = a.split("	");
    	   if(b.length==10){
    		   
    		   
    		   map3.put(b[8], 0); 
    		   
    		   ss=b[1];
	    	   sum1+=Integer.parseInt(b[2]);
	    	   sum2+=Integer.parseInt(b[3]);
	    	   sum3+=Integer.parseInt(b[4]);
	    	   if(b[7].equals("01")){
	    		   sum5++;
	    	   }
	    	   if(map.get(b[5])!=null){
	    		   int index=map.get(b[5]);
	        	   sum4[index]++;
	    	   }
	    	   String tt=b[6].substring(5, 9);
	    	   if(map2.get(tt)!=null){
	    	   		map2.put(tt,map2.get(tt)+1);
	    	   }
    	   }
    	   
 
       }
       TreeMap<String, Integer> treemap = new TreeMap<String, Integer>();
       treemap.putAll(map2);
       Iterator<String> iter =  treemap.keySet().iterator();
       StringBuffer sb = new StringBuffer();
       int count_d=0;
       int sum_zf=0;
       while(iter.hasNext()){
    	  Integer tmp = treemap.get(iter.next());
    	  sum_zf+=tmp.intValue();
    	  if(tmp.equals(0)){}
    	  else{
    		  count_d++;
    	  }
     	  sb.append(String.valueOf(tmp.intValue())+",");
       }
       ///*
       long deta_t = 0;
       String deta_time = "~";
       if(map3.size()>=2){
    	   TreeMap<String, Integer> treemap2 = new TreeMap<String, Integer>();
    	   treemap2.putAll(map3);
    	   deta_t=Long.parseLong(treemap2.lastKey())-Long.parseLong(treemap2.firstKey());
    	   deta_time = String.valueOf((float)deta_t/count_zf/3600000);
       }
       
       //String sss1=treemap2.lastKey();
       //String sss2=treemap2.firstKey();
       
       //*/
       c.append(String.valueOf(map.get(ss))+"	"); //id类型
       c.append(String.valueOf(sum1)+"	"); 		//总被转发数
	   c.append(String.valueOf(sum2)+"	"); 		//总被评论数
	   c.append(String.valueOf(sum3)+"	"); 		//总被赞数
	   for(int i=1;i<6;i++){								
		   c.append(String.valueOf(sum4[i])+","); 	//被我转发的用户类型（各自）数目
	   }
	   c.append("	"+sb+"	");						//每天发帖数目
	   c.append(String.valueOf(count_d)+"	"); 	//活跃天数
	   c.append(String.valueOf(sum5)+"	"); 		//转发别人微博总数目
	   c.append(String.valueOf(count_zf)+"	"); 	//总发帖数
	   c.append(String.valueOf(sum_zf)+"	"); 	//总发帖数2
	   c.append(String.valueOf((float)sum5/count_zf)+"	"); //原创比
	   c.append(String.valueOf((float)sum2/count_zf)+"	"); //得评论比
	   c.append(String.valueOf((float)sum3/count_zf)+"	"); //得赞比
	   c.append(deta_time+"	"+"###"); //发帖平均间隔
	   value.set(c.toString());
	   context.write(key,value);
	   sb.setLength(0);
	   c.setLength(0);
    }
  }
  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "weibo_data statistics");
    job.setJarByClass(STATISTIC.class);
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