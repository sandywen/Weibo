<!DOCTYPE html>
<?php header('Content-Type:text/html;charset=utf-8');?>
<meta http-equiv="Content-Type"content="text/html;charset=utf-8"/>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Lumino - Tables</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/datepicker3.css" rel="stylesheet">
<link href="css/bootstrap-table.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">

<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->

</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><span>Lumino</span>Admin</a>
				<ul class="user-menu">
					<li class="dropdown pull-right">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> User <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
							<li><a href="#"><span class="glyphicon glyphicon-cog"></span> Settings</a></li>
							<li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
						</ul>
					</li>
				</ul>
			</div>
							
		</div><!-- /.container-fluid -->
	</nav>
		
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<form role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search">
			</div>
		</form>
		<ul class="nav menu">
			<li><a href="index.html"><span class="glyphicon glyphicon-dashboard"></span> Home</a></li>
			<li><a href="tables.html"><span class="glyphicon glyphicon-list-alt"></span> Info tables</a></li>
			<li><a href="user_analysis.html"><span class="glyphicon glyphicon-pencil"></span> User Analysis</a></li>
			<li><a href="echart_.html"><span class="glyphicon glyphicon-stats"></span> Heat Curve</a></li>
			<li><a href="path_analysis.html"><span class="glyphicon glyphicon-th"></span> Path Analysis</a></li>
			<!--<li><a href="panels.html"><span class="glyphicon glyphicon-info-sign"></span> Alerts &amp; Panels</a></li>-->
			<!--<li class="parent ">
				<a href="#">
					<span class="glyphicon glyphicon-list"></span> Dropdown <span data-toggle="collapse" href="#sub-item-1" class="icon pull-right"><em class="glyphicon glyphicon-s glyphicon-plus"></em></span> 
				</a>
				<ul class="children collapse" id="sub-item-1">
					<li>
						<a class="" href="#">
							<span class="glyphicon glyphicon-share-alt"></span> Sub Item 1
						</a>
					</li>
					<li>
						<a class="" href="#">
							<span class="glyphicon glyphicon-share-alt"></span> Sub Item 2
						</a>
					</li>
					<li>
						<a class="" href="#">
							<span class="glyphicon glyphicon-share-alt"></span> Sub Item 3
						</a>
					</li>
				</ul>
			</li>-->
			<li role="presentation" class="divider"></li>
			<li><a href="login.html"><span class="glyphicon glyphicon-user"></span> Login Page</a></li>
		</ul>
		<div class="attribution">Template by <a href="http://www.medialoot.com/item/lumino-admin-bootstrap-template/">Medialoot</a></div>
	</div><!--/.sidebar-->
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">			
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
				<li class="active">Tables</li>
			</ol>
		</div><!--/.row-->
		
		<div class="row">
			 <div class="col-lg-12">
                                <h1 class="page-header">Event-related User Information</h1>
                           <p>  <h4> You can input key words of a event,and click "submit",you will see the result in the follow table.</h4> </p>
                        </div>
		</div><!--/.row-->
				
		
		<div class="row">
			<div class="col-lg-12">
				<!--<div class="panel panel-default">
					<div class="paneli-heading">Advanced Table</div>
					<div class="panel-body">
						<table data-toggle="table" data-url="tables/data1.json"  data-show-refresh="true" data-show-toggle="true" data-show-columns="true" data-search="true" data-select-item-name="toolbar1" data-pagination="true" data-sort-name="name" data-sort-order="desc">
						    <thead>
						    <tr>
						        <th data-field="state" data-checkbox="true" >Item ID</th>
						        <th data-field="id" data-sortable="true">Item ID</th>
						        <th data-field="name"  data-sortable="true">Item Name</th>
						        <th data-field="price" data-sortable="true">Item Price</th>
						    </tr>
						    </thead>
						</table>
					</div>
				</div>-->
				<form class="form-search" action="search.php">
				        <input class="input-medium search-query" type="text" name="tmp"/> <button type="submit" class="btn">submit</button>
				</form>
	
				<table class="table table-bordered">
				<caption>Datachart</caption>

			
				<title></title>
				   <!--	<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
				   	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
				   	<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>-->
				      
                              <!-- <thead>
				      <tr>
					<th>id</th>
					 <th>time</th>
					 <th>uid</th>
					 <th>content</th>
					<th>uid_r</th>
				      </tr>
				</thead>-->
				
				<?php
				$servername = "localhost";
				$username = "root";
				$password = "123456";
				$dbname = "DINGYANG_MYSQL";
				$chartname = $_GET["tmp"];
				$str="userinfo";
				if(!strcmp($chartname,$str)){
				echo "<thead>";
                                     echo " <tr>";

                                       echo " <th>id</th>";
                                       echo " <th>user_id</th>";
                                       echo " <th>user_type</th>";
                                       echo " <th>n_day_bzf</th>";
                                       echo " <th>n_d_bp</th>";
                                       echo " <th>n_d_bzan</th>";

                                       echo " <th>bwozhuan_ut</th>";
                                       echo " <th>mtft</th>";
                                       echo " <th>n_d_zft</th>";
                                       echo " <th>active_d</th>";
                                       echo " <th>z_ft</th>";
                                       echo " <th>z_ft2</th>";
                                       echo " <th>zf_b</th>";
                                       echo " <th>depingb</th>";
                                       echo " <th>dezanbi</th>";
                                       echo " <th>ft_jg</th>";
                                       echo " <th>end</th>";
                                    
                                     echo " </tr>";
                                 echo " </thead>";
				}else{
      				echo "<thead>";
                                      echo " <tr>";
                                       echo " <th>id</th>";
                                       echo " <th>time</th>";
                                       echo " <th>uid</th>";
                                       echo " <th>content</th>";
				       echo " <th>uid_r</th>";
				      echo "</tr>";
				echo "</thead>";

				}
				echo "<tbody>";


				$conn = new mysqli($servername, $username, $password, $dbname);

				if ($conn->connect_error) {
				    die("Connection failed: " . $conn->connect_error);
				} 
				$conn->query("SET NAMES UTF8");
				$sql = "SELECT * FROM $chartname where id < 100";
				$result = $conn->query($sql);

				if ($result->num_rows > 0) {
                                   if(!strcmp($chartname,$str)){

				    while($row = $result->fetch_assoc()) {

				      echo "<tr>";
					echo "<td>".$row["id"]."</td>";
					echo "<td>".$row["uid"]."</td>";

					echo "<td>".$row["ut"]."</td>";

					echo "<td>".$row["ndbzf"]."</td>";

					echo "<td>".$row["ndbp"]."</td>";
					echo "<td>".$row["ndbz"]."</td>";
					echo "<td>".$row["bwzut"]."</td>";
					echo "<td>".$row["mtft"]."</td>";
					echo "<td>".$row["ndzzfb"]."</td>";
					echo "<td>".$row["ad"]."</td>";
					echo "<td>".$row["zf"]."</td>";
					echo "<td>".$row["zf2"]."</td>";
					echo "<td>".$row["zfb"]."</td>";
					echo "<td>".$row["dpb"]."</td>";
					echo "<td>".$row["dzb"]."</td>";
					echo "<td>".$row["fj"]."</td>";
					echo "<td>".$row["end"]."</td>";
				      echo "</tr>";
				    
				    }
				}else{
				 while($row = $result->fetch_assoc()) {

                                      echo "<tr>";
                                        echo "<td>".$row["id"]."</td>";
                                        echo "<td>".$row["time"]."</td>";

                                        echo "<td>".$row["uid"]."</td>";

                                        echo "<td>".$row["content"]."</td>";

                                        echo "<td>".$row["uid_r"]."</td>";
                                      echo "</tr>";
					}
					}

				} else {
				    echo "0 results";
				}
				$conn->close();
				?>
				</tbody>
				</table>
			</div>
		</div><!--/.row-->	
			
		
		
	</div><!--/.main-->

	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/chart.min.js"></script>
	<script src="js/chart-data.js"></script>
	<script src="js/easypiechart.js"></script>
	<script src="js/easypiechart-data.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<script src="js/bootstrap-table.js"></script>
	<script>
		!function ($) {
			$(document).on("click","ul.nav li.parent > a > span.icon", function(){		  
				$(this).find('em:first').toggleClass("glyphicon-minus");	  
			}); 
			$(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		}(window.jQuery);

		$(window).on('resize', function () {
		  if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
		})
		$(window).on('resize', function () {
		  if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
		})
	</script>	
</body>

</html>
