package org.apache.hadoop.test;


public class Weibo{
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getCrawler_time() {
		return crawler_time;
	}

	public String getWeibo_id() {
		return weibo_id;
	}

	public void setWeibo_id(String weibo_id) {
		this.weibo_id = weibo_id;
	}

	public String getWeibo_content() {
		return weibo_content;
	}

	public void setWeibo_content(String weibo_content) {
		this.weibo_content = weibo_content;
	}

	
	public String getRetweet() {
		return isretweet;
	}

	public void setRetweet(String retweet) {
		this.isretweet = retweet;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getZhuan() {
		return zhuan;
	}

	public void setZhuan(String zhuan) {
		this.zhuan = zhuan;
	}

	public String getPing() {
		return ping;
	}

	public void setPing(String ping) {
		this.ping = ping;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getZan() {
		return zan;
	}

	public void setZan(String zan) {
		this.zan = zan;
	}

	public void setCrawler_time(String crawler_time) {
		this.crawler_time = crawler_time;
	}
	
	public String getR_user_type() {
		return r_user_type;
	}

	public void setR_user_type(String r_user_type) {
		this.r_user_type = r_user_type;
	}

	public String getR_weibo_content() {
		return r_weibo_content;
	}

	public void setR_weibo_content(String r_weibo_content) {
		this.r_weibo_content = r_weibo_content;
	}

	public String getR_user_id() {
		return r_user_id;
	}

	public void setR_user_id(String r_user_id) {
		this.r_user_id = r_user_id;
	}

	public String getR_nick_name() {
		return r_nick_name;
	}

	public void setR_nick_name(String r_nick_name) {
		this.r_nick_name = r_nick_name;
	}

	public String getR_weibo_id() {
		return r_weibo_id;
	}

	public void setR_weibo_id(String r_weibo_id) {
		this.r_weibo_id = r_weibo_id;
	}

	public String getR_zhuan() {
		return r_zhuan;
	}

	public void setR_zhuan(String r_zhuan) {
		this.r_zhuan = r_zhuan;
	}

	public String getR_ping() {
		return r_ping;
	}

	public void setR_ping(String r_ping) {
		this.r_ping = r_ping;
	}

	public String getR_zan() {
		return r_zan;
	}

	public void setR_zan(String r_zan) {
		this.r_zan = r_zan;
	}

	public String getR_url() {
		return r_url;
	}

	public void setR_url(String r_url) {
		this.r_url = r_url;
	}

	public String getR_time() {
		return r_time;
	}

	public void setR_time(String r_time) {
		this.r_time = r_time;
	}

	private String crawler_time;
	private String isretweet;
	private String user_id;
	private String nick_name;
	private String time;
	private String zhuan;
	private String ping;
	private String user_type;
	private String zan;
	private String weibo_id;
	private String weibo_content;
	private String r_user_id;
	private String r_nick_name;
	private String r_user_type;
	private String r_weibo_id;
	private String r_weibo_content;
	private String r_zhuan;
	private String r_ping;
	private String r_zan;
	private String r_url;
	private String r_time;
	
	public int compareTo(Weibo wb){
		
		if(user_id.compareTo(wb.getUser_id()) == 0 && time.compareTo(wb.getTime()) == 0){
			return 0;
		}
		return user_id.compareTo(wb.getUser_id());
	}
	
	public String toString(){
		
		String str = crawler_time+"\t"+user_id+"\t"+nick_name+"\t"+user_type+"\t"+time+"\t"
				+isretweet+"\t"+zhuan+"\t"+ping+"\t"+zan+"\t"+weibo_id+"\t"+weibo_content+"\t"
				+r_user_id+"\t"+r_nick_name+"\t"+r_user_type+"\t"+r_weibo_id+"\t"+r_weibo_content
				+"\t"+r_zhuan+"\t"+r_ping+"\t"+r_zan+"\t"+r_url+"\t"+r_time;
		return str;
		
	}

}
