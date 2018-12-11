<?php

$base=$_REQUEST['image'];
$filename=$_REQUEST['filename'];

echo $filename;

// base64 encoded utf-8 string

$binary=base64_decode($base);  //編碼圖片

// binary, utf-8 bytes

//header('Content-Type: bitmap; charset=utf-8');

// print($binary);

//$theFile = base64_decode($image_data);

$file = fopen("./upload/".$filename, 'wb'); //放置在upload資料夾

fwrite($file, $binary);

fclose($file);

//echo '<img src=test.jpg>';

?>