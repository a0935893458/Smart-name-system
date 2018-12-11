<?php session_start(); ?>
<?php

include 'db.php';
$class=$_POST['keyword'];
$sql="INSERT INTO  `nfcorder`.`myclass` (`mclass` ,`noworder`)VALUES ('$class',  '0');";   //更新Myclass 的 noworder 為 0

if (!mysql_query($sql, $con))
{
 die('Error: ' . mysql_error());
} 

header("Location: index.php");  // 回到設定點名頁面
die();

mysql_close($con);

echo "OK";

?>