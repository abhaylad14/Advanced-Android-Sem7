<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        $enro = $_POST['enro'];
        $name = $_POST['name'];
        $sem = $_POST['sem'];
        
        // Create connection
        $conn = mysqli_connect("localhost", "root", "", "test") or die("Connection failed: " . mysqli_connect_error());

        $sql = "INSERT INTO tblinfo (enro, name, sem) VALUES ('$enro', '$name', $sem)";

        if (mysqli_query($conn, $sql)) {
            echo "Data Added successfully";
        } else {
            echo "Error: " . $sql . "<br>" . mysqli_error($conn);
        }

        mysqli_close($conn);
}
?>

