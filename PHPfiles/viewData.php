<?php
             $con = mysqli_connect("localhost","root", "", "test") or die("Connection failed: " . mysqli_connect_error());
             $result= mysqli_query($con, "select * from tblinfo");
             $data=array();
             while($row = mysqli_fetch_object($result)){
                 $data['students'][] = array(
                     "id" =>$row->id,
                     "enro"=>$row->enro,
                     "name"=>$row->name,
                     "sem"=>$row->sem
                 );
             }
           
             echo json_encode($data);
             
        ?>

