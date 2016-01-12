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
                $sql = "SELECT time_stamp FROM $chartname";
                $result = $conn->query($sql);

                if ($result->num_rows > 0) {
	//	if(){
                        $length=$result->num_rows;
                        $a=array();
                        $row=$result->fetch_assoc();
			$row=$result->fetch_assoc();
                        $i=1;
                        $count=2;             
                        for($i=1;$i<10&&$count<=$length;$i++){
				$ttt=$row["time_stamp"];
                                $tmp_time=$ttt+86400;
                                $tmp_num=0;
                                while($row["time_stamp"]<=$tmp_time){
                                        $tmp_num++;
					if($row=$result->fetch_assoc()){}
					else{
						break;
					}

                                }
                                $a[$i]=$tmp_num;
				//echo $a[$i];
                                $count+=$tmp_num;            
                        }
/*			$row=$result->fetch_assoc();
                        $row=$result->fetch_assoc();
              
		if( $tmp_time>$row["time_stamp"]){
			echo "false";}
		else{
		echo "true";
		}*/
		
                  echo json_encode($a);
                } else {
                        echo "0 results";
                }
                $conn->close();
        ?>
					
