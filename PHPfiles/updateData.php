<?php

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $id = $_POST["id"];
    $enro = $_POST["enro"];
    $name = $_POST["name"];
    $sem = $_POST["sem"];

    // Create connection
    $conn = mysqli_connect("localhost", "root", "", "test") or die("Connection failed: " . mysqli_connect_error());

    $sql = "update tblinfo set enro='$enro' , name='$name', sem=$sem where id=$id ";

    if (mysqli_query($conn, $sql)) {
        echo "Record updated successfully";
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($conn);
    }
    mysqli_close($conn);
}
