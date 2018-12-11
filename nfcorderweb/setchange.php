<?php

include 'db.php';

$id=$_GET["id"];  //取得ID

$sql="update myclass set noworder = '0'";   //更新Myclass 的 noworder 為 0

if (!mysql_query($sql, $con))
{
 die('Error: ' . mysql_error());
} 

$sql="update myclass set noworder = '1' where id= '$id'";  //更新Myclass 的 noworder 中id相等的 轉為1 

if (!mysql_query($sql, $con))
{
 die('Error: ' . mysql_error());
} 

header("Location: change.php");  // 回到設定點名頁面
die();

mysql_close($con);

echo "OK";

?>

