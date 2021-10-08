<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        $id = $_POST['id'];
        
        // Create connection
        $conn = mysqli_connect("localhost", "root", "", "test") or die("Connection failed: " . mysqli_connect_error());

        $sql = "delete from tblinfo where id=$id";

        if (mysqli_query($conn, $sql)) {
            echo "Data Deleted successfully";
        } else {
            echo "Error: " . $sql . "<br>" . mysqli_error($conn);
        }

        mysqli_close($conn);
}
?>