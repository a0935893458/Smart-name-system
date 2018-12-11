<?php
include 'db.php';
date_default_timezone_set("Asia/Taipei");

header('Content-type: text/xml');
// create a new XML document
$doc = new DomDocument('1.0', 'UTF-8');


$id=$_GET["user"];

$rdate=date( 'Y-m-d H:i:s' );

$sql = "SELECT * FROM myclass where noworder = '1'";
$us = mysql_query($sql);

$mclass=mysql_fetch_row($us);

$sql = "insert into nfcorder (uid, mclass, rdate) values ('$id', '$mclass[1]', '$rdate')";
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


	
$xml_string = $doc->saveXML();
echo $xml_string;

mysql_close($con);
?>

