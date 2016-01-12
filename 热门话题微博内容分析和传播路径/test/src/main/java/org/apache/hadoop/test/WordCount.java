package org.apache.hadoop.test;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
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

public class WordCount {

    public static class Map extends MapReduceBase implements
            Mapper<Object, Text, Text, Text> {
        private Weibo weibo = new Weibo();

        public void map(Object key, Text value,
                OutputCollector<Text, Text> output, Reporter reporter)
                throws IOException {
            String line 	   = value.toString();
            String[] WeiboInfo = line.split("\t");
            if (WeiboInfo.length >= 8){
            	 weibo.setCrawler_time(WeiboInfo[0]);
             	if(WeiboInfo[2].equals("0")){
             		weibo.setRetweet("0");
             	}else{
             		weibo.setRetweet("1");
             		weibo.setR_user_id(WeiboInfo[17]);
             		weibo.setR_nick_name(WeiboInfo[18]);
             		weibo.setR_user_type(WeiboInfo[19]);
             		weibo.setR_weibo_id(WeiboInfo[20]);
             		weibo.setR_weibo_content(WeiboInfo[21]);
             		weibo.setR_zhuan(WeiboInfo[22]);
             		weibo.setR_ping(WeiboInfo[23]);
             		weibo.setR_zan(WeiboInfo[24]);
             		weibo.setR_url(WeiboInfo[25]);
             		weibo.setR_time(WeiboInfo[28]);
             	}	
             	weibo.setUser_id(WeiboInfo[3]);
             	weibo.setNick_name(WeiboInfo[4]);
             	weibo.setUser_type(WeiboInfo[6]);
             	weibo.setWeibo_id(WeiboInfo[7]);
             	weibo.setWeibo_content(WeiboInfo[8]);
             	weibo.setZhuan(WeiboInfo[9]);
             	weibo.setPing(WeiboInfo[10]);
             	weibo.setZan(WeiboInfo[11]);
             	weibo.setTime(WeiboInfo[15]);
             	output.collect(new Text(weibo.getWeibo_id()),new Text(weibo.toString()));       
            }
              
        }

    }

    public static class Reduce extends MapReduceBase implements
            Reducer<Text, Text, Text, Text> {
        public void reduce(Text key, Iterator<Text> values,
                OutputCollector<Text, Text> output, Reporter reporter)
                throws IOException {  
        	String weiboStr 	       = "";
        	String maxCrawlerTime      = "";
        	String maxCrawlerTimeWeibo = null;
        	String[] weibo = null ;
        	while(values.hasNext()){
        		weiboStr = values.next().toString();
        		weibo    = weiboStr.split("\t");
        		if(weibo[0].compareTo(maxCrawlerTime) > 0){
        			maxCrawlerTime      = weibo[0];
        			maxCrawlerTimeWeibo = weiboStr;   			
        		}
        	}
        	output.collect(new Text(maxCrawlerTimeWeibo),new Text(""));
        }
    }
    

    public static void main(String[] args) throws Exception {
        JobConf conf = new JobConf(WordCount.class);
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
        Path tmpDir = new Path("/weiboContentResult");
        FileOutputFormat.setOutputPath(conf, tmpDir);

        JobClient.runJob(conf);
           
    }
}
