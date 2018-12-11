<?php

if (!defined('WEB_ROOT')
    || !isset($_GET['step']) || (int)$_GET['step'] != 2
	|| $_SERVER['HTTP_REFERER'] != 'http://' . $_SERVER['HTTP_HOST'] . $_SERVER['PHP_SELF'] . '?step=1') {
	exit;
}

$errorMessage = '&nbsp;';

$requiredField = array('txtShippingFirstName', 'txtShippingLastName', 'txtShippingAddress1', 'txtShippingPhone', 'txtShippingState',  'txtShippingCity', 'txtShippingPostalCode',
                       'txtPaymentFirstName', 'txtPaymentLastName', 'txtPaymentAddress1', 'txtPaymentPhone', 'txtPaymentState', 'txtPaymentCity', 'txtPaymentPostalCode');
					   
if (!checkRequiredPost($requiredField)) {
	$errorMessage = 'Input not complete';
}
					   

$cartContent = getCartContent();

//sql
//$_POST['txtShippingFirstName'];

?>
<table width="550" border="0" align="center" cellpadding="10" cellspacing="0">
    <tr> 
        <td>Step 2 Of 3 : 確認訂單 </td>
    </tr>
</table>
<p id="errorMessage"><?php echo $errorMessage; ?></p>
<form action="<?php echo $_SERVER['PHP_SELF']; ?>?step=3" method="post" name="frmCheckout" id="frmCheckout">
<?php 
if ($_POST['optPayment'] == 'paypal') {
?>
    <table width="550" border="0" align="center" cellpadding="10" cellspacing="0">
        <tr> 
            <td align="center"><strong>:: IMPORTANT NOTE :: </strong></td>
        </tr>
        <tr> 
            <td><p>Before clicking the &quot;Confirm Order&quot; button open a 
                    new browser window and go to <a href="https://developer.paypal.com" target="_blank">https://developer.paypal.com</a> 
                    then login using this username and password :<br>
                    Email : armanpi@phpwebcommerce.com<br>
                    Password : phpwebco<br>
                    <br>
                    After you click on the &quot;Confirm Order&quot; button below 
                    you will be redirected to paypal website. On the paypal checkout 
                    page use these info to login and complete the checkout process 
                    :<br>
                    Email : testme@phpwebcommerce.com <br>
                    Password : phpwebco </p>
                <p>By the way, please don't change the password or delete the 
                    test email okay :-)</p></td>
        </tr>
    </table>
    <p>&nbsp;</p>
<?php
}
?>
    <table width="550" border="0" align="center" cellpadding="5" cellspacing="1" class="infoTable">
        <tr class="infoTableHeader"> 
            <td colspan="3">購物項目</td>
        </tr>
        <tr class="label"> 
            <td>項目</td>
            <td>單價</td>
            <td>總數</td>
        </tr>
        <?php
$numItem  = count($cartContent);
$subTotal = 0;
for ($i = 0; $i < $numItem; $i++) {
	extract($cartContent[$i]);
	$subTotal += $pd_price * $ct_qty;
?>
        <tr class="content"> 
            <td class="content"><?php echo "$ct_qty x $pd_name"; ?></td>
            <td align="right"><?php echo displayAmount($pd_price); ?></td>
            <td align="right"><?php echo displayAmount($ct_qty * $pd_price); ?></td>
        </tr>
        <?php
}
?>
        <tr class="content"> 
            <td colspan="2" align="right">子項目</td>
            <td align="right"><?php echo displayAmount($subTotal); ?></td>
        </tr>
        <tr class="content"> 
            <td colspan="2" align="right">運費</td>
            <td align="right"><?php echo displayAmount($shopConfig['shippingCost']); ?></td>
        </tr>
        <tr class="content"> 
            <td colspan="2" align="right">總數</td>
            <?php $cost = $shopConfig['shippingCost'] + $subTotal;?>
			<td align="right"><?php echo displayAmount( $cost * 0.9);?>(優惠九折))</td>
        </tr>
    </table>
    <p>&nbsp;</p>
    <table width="550" border="0" align="center" cellpadding="5" cellspacing="1" class="infoTable">
        <tr class="infoTableHeader"> 
            <td colspan="2">寄件資料</td>
        </tr>
        <tr> 
            <td width="150" class="label">姓</td>
            <td class="content"><?php echo $_POST['txtShippingFirstName']; ?>
                <input name="hidShippingFirstName" type="hidden" id="hidShippingFirstName" value="<?php echo $_POST['txtShippingFirstName']; ?>"></td>
        </tr>
        <tr> 
            <td width="150" class="label">名字</td>
            <td class="content"><?php echo $_POST['txtShippingLastName']; ?>
                <input name="hidShippingLastName" type="hidden" id="hidShippingLastName" value="<?php echo $_POST['txtShippingLastName']; ?>"></td>
        </tr>
        <tr> 
            <td width="150" class="label">地址1</td>
            <td class="content"><?php echo $_POST['txtShippingAddress1']; ?>
                <input name="hidShippingAddress1" type="hidden" id="hidShippingAddress1" value="<?php echo $_POST['txtShippingAddress1']; ?>"></td>
        </tr>
        <tr> 
            <td width="150" class="label">地址2</td>
            <td class="content"><?php echo $_POST['txtShippingAddress2']; ?>
                <input name="hidShippingAddress2" type="hidden" id="hidShippingAddress2" value="<?php echo $_POST['txtShippingAddress2']; ?>"></td>
        </tr>
        <tr> 
            <td width="150" class="label">電話</td>
            <td class="content"><?php echo $_POST['txtShippingPhone'];  ?>
                <input name="hidShippingPhone" type="hidden" id="hidShippingPhone" value="<?php echo $_POST['txtShippingPhone']; ?>"></td>
        </tr>
        <tr> 
            <td width="150" class="label">國家</td>
            <td class="content"><?php echo $_POST['txtShippingState']; ?> <input name="hidShippingState" type="hidden" id="hidShippingState" value="<?php echo $_POST['txtShippingState']; ?>" ></td>
        </tr>
        <tr> 
            <td width="150" class="label">城市</td>
            <td class="content"><?php echo $_POST['txtShippingCity']; ?>
                <input name="hidShippingCity" type="hidden" id="hidShippingCity" value="<?php echo $_POST['txtShippingCity']; ?>" ></td>
        </tr>
        <tr> 
            <td width="150" class="label">郵遞區號</td>
            <td class="content"><?php echo $_POST['txtShippingPostalCode']; ?>
                <input name="hidShippingPostalCode" type="hidden" id="hidShippingPostalCode" value="<?php echo $_POST['txtShippingPostalCode']; ?>"></td>
        </tr>
    </table>
    <p>&nbsp;</p>
    <table width="550" border="0" align="center" cellpadding="5" cellspacing="1" class="infoTable">
        <tr class="infoTableHeader"> 
            <td colspan="2">付款資料</td>
        </tr>
        <tr> 
            <td width="150" class="label">姓</td>
            <td class="content"><?php echo $_POST['txtPaymentFirstName']; ?>
                <input name="hidPaymentFirstName" type="hidden" id="hidPaymentFirstName" value="<?php echo $_POST['txtPaymentFirstName']; ?>"></td>
        </tr>
        <tr> 
            <td width="150" class="label">名字</td>
            <td class="content"><?php echo $_POST['txtPaymentLastName']; ?>
                <input name="hidPaymentLastName" type="hidden" id="hidPaymentLastName" value="<?php echo $_POST['txtPaymentLastName']; ?>"></td>
        </tr>
        <tr> 
            <td width="150" class="label">地址1</td>
            <td class="content"><?php echo $_POST['txtPaymentAddress1']; ?>
                <input name="hidPaymentAddress1" type="hidden" id="hidPaymentAddress1" value="<?php echo $_POST['txtPaymentAddress1']; ?>"></td>
        </tr>
        <tr> 
            <td width="150" class="label">地址2</td>
            <td class="content"><?php echo $_POST['txtPaymentAddress2']; ?> <input name="hidPaymentAddress2" type="hidden" id="hidPaymentAddress2" value="<?php echo $_POST['txtPaymentAddress2']; ?>"> 
            </td>
        </tr>
        <tr> 
            <td width="150" class="label">電話</td>
            <td class="content"><?php echo $_POST['txtPaymentPhone'];  ?> <input name="hidPaymentPhone" type="hidden" id="hidPaymentPhone" value="<?php echo $_POST['txtPaymentPhone']; ?>"></td>
        </tr>
        <tr> 
            <td width="150" class="label">國家</td>
            <td class="content"><?php echo $_POST['txtPaymentState']; ?> <input name="hidPaymentState" type="hidden" id="hidPaymentState" value="<?php echo $_POST['txtPaymentState']; ?>" ></td>
        </tr>
        <tr> 
            <td width="150" class="label">城市</td>
            <td class="content"><?php echo $_POST['txtPaymentCity']; ?>
                <input name="hidPaymentCity" type="hidden" id="hidPaymentCity" value="<?php echo $_POST['txtPaymentCity']; ?>"></td>
        </tr>
        <tr> 
            <td width="150" class="label">郵遞區號</td>
            <td class="content"><?php echo $_POST['txtPaymentPostalCode']; ?>
                <input name="hidPaymentPostalCode" type="hidden" id="hidPaymentPostalCode" value="<?php echo $_POST['txtPaymentPostalCode']; ?>"></td>
        </tr>
    </table>
    <p>&nbsp;</p>
    <table width="550" border="0" align="center" cellpadding="5" cellspacing="1" class="infoTable">
      <tr>
        <td width="150" class="infoTableHeader">Payment Method </td>
        <td class="content"><?php echo $_POST['optPayment'] == 'paypal' ? 'Paypal' : '轉帳'; ?>
          <input name="hidPaymentMethod" type="hidden" id="hidPaymentMethod" value="<?php echo $_POST['optPayment']; ?>" />
        </tr>
    </table>
    <p>&nbsp;</p>
    <p align="center"> 
        <input name="btnBack" type="button" id="btnBack" value="修改資料" onClick="window.location.href='checkout.php?step=1';" class="box">
        &nbsp;&nbsp; 
        <input name="btnConfirm" type="submit" id="btnConfirm" value="確認訂單" class="box">
</form>
<p>&nbsp;</p>
