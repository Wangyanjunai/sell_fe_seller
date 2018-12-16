<!DOCTYPE html>
<html>
<head>
    <title>支付宝当面条码支付</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="../../css/pay.css">
</head>
<body text="#000" style="background-color: #fff;margin-left: 0;margin-top: 4px;">
<div id="main">
    <div id="head">
        <dl class="alipay_link">
            <a target="_blank" href="http://www.alipay.com"><span>支付宝首页</span></a>|
            <a target="_blank" href="https://b.alipay.com/home.htm"><span>商家服务</span></a>|
            <a target="_blank" href="http://help.alipay.com/support/index_sh.htm"><span>帮助中心</span></a>
        </dl>
        <span class="title">支付宝 当面付2.0 条码支付接口</span>
    </div>
    <div class="cashier-nav">
        <ol>
            <li class="current">1、确认信息 →</li>
            <li>2、点击确认 →</li>
            <li class="last">3、确认完成</li>
        </ol>
    </div>
    <form name="alipayment" action="f2fpay" method="post" target="_blank">
        <div id="body" style="clear: left">
            <dl class="content">

                <dt>商户订单号：</dt>
                <dd>
                    <span class="null-star">*</span>
                    <input style="color: #f00; " id="WIDout_trade_no" size="30" name="outTradeNo" readonly="readonly" />
                    <span>商户网站订单系统中唯一订单号，必填</span>
                </dd>

                <dt>订单名称：</dt>
                <dd>
                    <span class="null-star">*</span>
                    <input id="WIDout_subject" size="30" name="subject" />
                    <span>必填</span>
                </dd>

                <dt>订单描述：</dt>
                <dd>
                    <span class="null-star">*</span>
                    <input id="WIDout_body" size="30" name="body" />
                    <span>可填</span>
                </dd>

                <dt>付款金额：</dt>
                <dd>
                    <span class="null-star">*</span>
                    <input id="WIDout_totalAmount" size="30" name="totalAmount" />
                    <span>必填,单位为元，精确到小数点后2位</span>
                </dd>

                <dt>不可打折金额：</dt>
                <dd>
                    <span class="null-star">*</span>
                    <input id="WIDout_undiscountableAmount" size="30" name="undiscountableAmount" />
                    <span>可填,用于标识不可打折金额，单位为元，精确到小数点后2位</span>
                </dd>

                <dt>付款条码：</dt>
                <dd>
                    <span class="null-star">*</span>
                    <input id="WIDout_authCode" size="30" name="authCode" />
                    <span>必填</span>
                </dd>

                <dt></dt>
                <dd>
						<span class="new-btn-login-sp">
							<button class="new-btn-login" type="submit" style="text-align: center;">确 认</button>
						</span>
                </dd>
            </dl>
        </div>
    </form>
    <div id="foot">
        <ul class="foot-ul">
            <li><font class="note-help">如果您点击“确认”按钮，即表示您同意该次的执行操作。 </font></li>
            <li>支付宝版权所有 2011-2015 ALIPAY.COM</li>
        </ul>
    </div>
</div>
<script type="text/javascript">
    /**
     * randomWord 产生任意长度随机字母数字组合
     * randomFlag-是否任意长度 min-任意长度最小位[固定位数] max-任意长度最大位
     * xuanfeng 2014-08-28
     */
    function randomWord(randomFlag, min, max) {
        var str = "", range = min, arr = [
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z' ];

        // 随机产生
        if (randomFlag) {
            range = Math.round(Math.random() * (max - min)) + min;
        }
        for (var i = 0; i < range; i++) {
            pos = Math.round(Math.random() * (arr.length - 1));
            str += arr[pos];
        }
        return str;
    }
    function GetDateNow() {
        var vNow = new Date();
        var sNow = "";
        sNow += String(vNow.getFullYear());
        sNow += String(vNow.getMonth() + 1);
        sNow += String(vNow.getDate());
        sNow += String(vNow.getHours());
        sNow += String(vNow.getMinutes());
        sNow += String(vNow.getSeconds());
        sNow += String(vNow.getMilliseconds());
        document.getElementById("WIDout_trade_no").value = sNow + randomWord(false,17);
    }
    GetDateNow();
</script>
</body>
</html>