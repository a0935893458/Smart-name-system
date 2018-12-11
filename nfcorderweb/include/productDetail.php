<?php
if (!defined('WEB_ROOT')) {
	exit;
}

$product = getProductDetail($pdId, $catId);

// we have $pd_name, $pd_price, $pd_description, $pd_image, $cart_url
extract($product);
?> 
<table width="100%" border="0" cellspacing="0" cellpadding="10">
 <tr> 
  <td align="center"><img src="<?php echo $pd_image; ?>" border="0" alt="<?php echo $pd_name; ?>"></td>
  <td valign="middle">
<strong>商品名稱 :<?php echo $pd_name; ?></strong><br>
價格 : <?php echo displayAmount($pd_price); ?><br>
<?php
// if we still have this product in stock
// show the 'Add to cart' button
if ($pd_qty > 0) {
?>
<?php
if (isset($_SESSION['username']))
{
    if ($_SESSION['username'] != "")
	{
?>
<input type="button" name="btnAddToCart" value="加入購物車 &gt;" onClick="window.location.href='<?php echo $cart_url; ?>';" class="addToCartButton">
<<input type="button" name="btnAddToCart" value="交換物品" onClick="window.location.href='<?php echo $cart_url; ?>';" class="addToCartButton">
<?php
	}
}
?>


<?php
} else {
	echo '缺貨中';
}
?>
  </td>
 </tr>
 <tr align="left"> 
  <td colspan="2"><?php echo $pd_description; ?></td>
 </tr>
</table>
