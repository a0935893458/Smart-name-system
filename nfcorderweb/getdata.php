<?php

include 'db.php';

header('Content-type: text/xml');

$myuser=$_GET["myuser"];

$us = mysql_query("select * from tbl_user where myuser='$myuser'", $con);
$rows_num = mysql_num_rows($us);

// create a new XML document
$doc = new DomDocument('1.0', 'UTF-8');

// add root node
$root = $doc->createElement('talk');
$root = $doc->appendChild($root);

while($data=mysql_fetch_row($us))
{
    $inner = $doc->createElement('item');
    $inner = $root->appendChild($inner);

    // add a child node for each field
    $child = $doc->createElement('id');
    $child = $inner->appendChild($child);
    $value = $doc->createTextNode("$data[0]");
    $value = $child->appendChild($value);

    $child = $doc->createElement('title');
    $child = $inner->appendChild($child);
    $value = $doc->createTextNode("$data[1]");
    $value = $child->appendChild($value);

    $child = $doc->createElement('content');
    $child = $inner->appendChild($child);
    $value = $doc->createTextNode("$data[2]");
    $value = $child->appendChild($value);
	
	$child = $doc->createElement('pic');
    $child = $inner->appendChild($child);
    $value = $doc->createTextNode("$data[3]");
    $value = $child->appendChild($value);

    $child = $doc->createElement('address');
    $child = $inner->appendChild($child);
    $value = $doc->createTextNode("$data[4]");
    $value = $child->appendChild($value);

}

$xml_string = $doc->saveXML();
echo $xml_string;

mysql_close($con);
?>

