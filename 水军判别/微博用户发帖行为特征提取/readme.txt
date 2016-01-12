首先这三个java文件是运行在后台上的数据处理程序。这三个代码完成（用于水军判别的）原始数据的feature提取功能。



ST1.java对原始数据有效字段分割、去重。
代码功能：
1、map方法：原始数据分割，分割成多个字段。
2、reduce方法：对分割好的数据行去重。
处理完成后是原始的未进行统计的feature。



STATISTIC.java是对原始的未统计的feature进行统计。
代码实现：
1、map：无功能。
2、reduce：对13个用户feature统计。
输出为统计好的13个feature。
这十三个feature见项目报告文档。



QC_wencheng.java是实现热门事件相关数据的去重。
