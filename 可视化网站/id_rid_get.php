<?php
$servername = "localhost";
$username = "root";
$password = "123456";
$dbname = "DINGYANG_MYSQL";
$chartname = $_GET["q"];
$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error) {
	die("Connection failed: " . $conn->connect_error);
} 
$conn->query("SET NAMES UTF8");
$sql = "SELECT * FROM $chartname where id<5000";
$result = $conn->query($sql);
$phparray=array();
$num=0;
$MAX_single_root=0;
$num_single_root=0;
$MAX_one_child=0;
$num_one_child=0;
$MAX_two_child=50;
$num_two_child=0;
if ($result->num_rows > 0) {
	while( $row=$result->fetch_assoc()){
		if(!array_key_exists($row["uid"],$phparray)){
			if(strcmp($row["uid_r"],"\r")==0||$row["uid_r"]==''){
				$phparray[$row["uid"]]="0";

			}else{
				$phparray[$row["uid"]]=$row["uid_r"];
			}

		}
	}
}else{
	echo "0 results";

}
class point {
	public $uid="";
	public $uid_r="";
	public $depth=0;
	public $circle=false;
	public $category=2;
}
$jsonstr=array();
foreach($phparray as $x=>$x_value){
	if(!array_key_exists($x_value,$phparray)&&$x_value!="0"){
		$phparray[$x_value]="0";
	}
}
$num_be_infered=array();
$num_be_infered=array_count_values($phparray);
foreach($phparray as $x=>$x_value){
	$str=$x;
	$num=-1;
	$circle_flag=false;
	$category_flag=false;
	$map=array();
	$map[$x]="0";
	$circle_root="";
	$i=0;
	if(in_array($x,$phparray)){
		$category_flag=true;
	}
	while(array_key_exists($str,$phparray)&&$i<10){
		$num++;$i++;
		if(array_key_exists($phparray[$str],$map)){
			$circle_flag=true;

			break;
		}elseif(strcmp($phparray[$str],"0")!=0){
			$map[$phparray[$str]]="0";
		}
		$str=$phparray[$str];
	}
	if(strcmp($x,$x_value)!=0){
		$p= new point();
		$p->uid=strval($x);
		$p->uid_r=strval($x_value);
		$p->depth=$num;
		$p->circle=false;
		if($x_value=="0"){
			if(!array_key_exists($x,$num_be_infered)){
				$num_single_root++;
				if($num_single_root<$MAX_single_root){
					$p->category=2;
					array_push($jsonstr,$p);
				}
			}
			else{
					switch ($num_be_infered[$x])
					{
						case 1:
							$num_one_child++;
							if($num_one_child<$MAX_one_child){
							$p->category=2;
							array_push($jsonstr,$p);
							$point_yezi=array_search($x,$phparray);
							$y= new point();
							$y->uid=strval($point_yezi);
							$y->uid_r=strval($x);
							$y->depth=1;
							$y->circle=false;
							$y->category=0;
							array_push($jsonstr,$y);

							}

							break;
						default:
							$p->category=2;
							array_push($jsonstr,$p);
					}

			}
		}
		elseif($category_flag==true){
			$p->category=1;
			array_push($jsonstr,$p);

		}else{
			if($num_be_infered[$x_value]>1){
				$p->category=0;
				array_push($jsonstr,$p);

			}
		}
		if($circle_flag==true){
			$p= new point();
			$p->uid=strval($x_value);
			$p->uid_r="0";
			$p->depth=0;
			$p->circle=true;
			$p->category=2;
			array_push($jsonstr,$p);
		}


	}
}
echo json_encode($jsonstr);		
$conn->close();
?>

