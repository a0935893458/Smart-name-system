<?php

include 'db.php';

header('Content-type: text/xml');
// create a new XML document
$doc = new DomDocument('1.0', 'UTF-8');

$id=$_GET["myaccount"];
$pw=$_GET["name"];
$kp=$_GET["pic"];
$pn=$_GET["user"];

$sql = "insert into tbl_user (username, name, pic, myuser) values ('$id', '$pw', '$kp', '$pn')";
if(mysql_query($sql))
{
	// add root node
	$root = $doc->createElement('login');
	$root = $doc->appendChild($root);
	$child = $doc->createElement('result');
	$child = $root->appendChild($child);

	$value = $doc->createTextNode($id);
	$value =  $child->appendChild($value);
}
else
{
	// add root node
	$root = $doc->createElement('login');
	$root = $doc->appendChild($root);
	$child = $doc->createElement('result');
	$child = $root->appendChild($child);

	$value = $doc->createTextNode("-1");
	$value =  $child->appendChild($value);
}

	
$xml_string = $doc->saveXML();	//保存所有文檔
echo $xml_string;   			//儲存置資料庫

mysql_close($con);
?>

