这个文件夹包括网站的前端、后台的所有代码。

1、index.html:主页代码。
   tables.php:info tables网页代码。
   user_analysis.html：User Analysis网页代码。
   echart_.html :Heat curve 网页代码。
   path_anlysis :Path Analysis网页代码。
2、.php文件
后台数据处理文件。
下面列出html与php调用关系：
index 不调用php
tables.php调用search.php文件进行数据库访问，返回数据库的数据。
user_analysis.html调用means_shuijunfeatrue.php
echrat_.html调用be_time_get.php
path_analysis.html调用id_rid_get.php
3、其他文件夹都是css或者其他图标制作的辅助工具代码。