<script type="text/javascript">
    function onBridgeReady(){
        WeixinJSBridge.invoke(
                'getBrandWCPayRequest', {
                    "appId":"${payResponse.appId}",           //appId：公众号名称，由商户传入appId
                    "timeStamp":"${payResponse.timeStamp}",   //timeStamp：时间戳，自1970年以来的秒数timeStamp
                    "nonceStr":"${payResponse.nonceStr}",     //nonceStr：随机串nonceStr
                    "package":"${payResponse.packAge}",       //package：统一支付返回的preparid
                    "signType":"${payResponse.signType}",     //signType微信签名方式：MD5
                    "paySign":"${payResponse.paySign}"        //paySign：微信签名paySign
                },
                function(res){
                    //alert("res.err_msg==" + res.err_msg);
                    // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                    if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                        alert('支付成功');
                    } else if(res.err_msg == "get_brand_wcpay_request:cancel") {
                        alert('支付过程中用户取消');
                    } else if(res.err_msg == "get_brand_wcpay_request:fail") {
                        alert('支付失败');
                    } else {
                        alert('未知异常');
                    }
                    location.href = "${returnUrl}";
                }
        );
    }
    if (typeof WeixinJSBridge == "undefined"){
        if( document.addEventListener ){
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        }else if (document.attachEvent){
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    }else{
        onBridgeReady();
    }
</script>