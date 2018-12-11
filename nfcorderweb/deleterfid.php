<?php

include 'db.php';

$id=$_GET["id"];

$sql="Delete from readrfid where myid= '$id'";

if (!mysql_query($sql, $con))
{
 die('Error: ' . mysql_error());
} 

header("Location: class.php");
die();

mysql_close($con);

echo "DELETE OK";

?>

