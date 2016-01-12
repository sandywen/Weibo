<?php
$servername = "localhost";
$username = "root";
$password = "123456";
$dbname = "DINGYANG_MYSQL";
$chartname ="shuijunjieuo";
$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error) {
	die("Connection failed: " . $conn->connect_error);
} 
$conn->query("SET NAMES UTF8");
$sql = "SELECT * FROM $chartname";
$result = $conn->query($sql);
$featrue_array_0=array();
$featrue_array_1=array();
$num_shuijun=0;
$num_feishuijun=0;
for($i=0;$i<=13;$i++){
	$strtmp="f".strval($i);
	$featrue_array_0[$strtmp]=0;
	$featrue_array_1[$strtmp]=0;
	
}
if ($result->num_rows > 0) {
	$num_rows=$result->num_rows;
	while( $row=$result->fetch_assoc()){
		if($row["iis"]==0){
			$num_shuijun++;
			for($i=1;$i<=13;$i++){
				$strtmp="f".strval($i);
				$featrue_array_0[$strtmp]+=$row[strval($i)];
			}
		}
		elseif($row["iis"]==1){
			$num_feishuijun++;
			for($i=1;$i<=13;$i++){
				$strtmp="f".strval($i);
				$featrue_array_1[$strtmp]+=$row[strval($i)];
			}
		}
	}
	for($i=1;$i<=13;$i++){
		$strtmp="f".strval($i);
		$featrue_array_0[$strtmp]/=$num_rows;
		$featrue_array_1[$strtmp]/=$num_rows;
	}

 	$strtmp="f".strval(0);
	$featrue_array_0[$strtmp]=$num_shuijun;
	$featrue_array_1[$strtmp]=$num_feishuijun;
	
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
array_push($jsonstr,$featrue_array_0);
array_push($jsonstr,$featrue_array_1);
echo json_encode($jsonstr);		
$conn->close();
?>

