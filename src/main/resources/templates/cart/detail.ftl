<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf8"/>
    <meta id="viewport" name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1; user-scalable=no;"/>
    <link rel="Bookmark" href="/sell/favicon.ico"/>
    <link rel="Shortcut Icon" href="/sell/favicon.ico"/>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="/sell/css/listProd.css">
    <script type="text/javascript" src="/sell/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
    <script type="text/javascript" src="https://wx.gtimg.com/wxpay_h5/fingerprint2.min.1.5.1.js"></script>
    <title>【微信支付V2.0】MWEB支付实例购物车</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-condensed table-hover table-bordered">
                <thead>
                <tr>
                    <th>
                        名称
                    </th>
                    <th>
                        图片
                    </th>
                    <th>
                        数量
                    </th>
                    <th>
                        金额
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr class="info">
                    <td>
                    ${productInfo.productName}
                    </td>
                    <td>
                        <img height="100" width="100" src="${productInfo.productIcon}" class="img-circle"
                             alt="商品小图片">
                    </td>
                    <td>
                    ${cartDTO.productQuantity}
                    </td>
                    <td>
                    ${cartDTO.productQuantity * productInfo.productPrice}
                    </td>
                </tr>
                </tbody>
            </table>
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label for="inputBuyerName" class="col-sm-2 control-label">联系人</label>
                    <div class="col-sm-10">
                        <input type="hidden" id="inputProductId" value="${productInfo.productId}">
                        <input type="hidden" id="inputProductQuantity" value="${cartDTO.productQuantity}">
                        <input type="text" class="form-control" id="inputBuyerName"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputBuyerPhone" class="col-sm-2 control-label">联系电话</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="inputBuyerPhone"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputBuyerAddress" class="col-sm-2 control-label">联系地址</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputBuyerAddress"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="button" class="btn btn-default">立即支付</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript" charset="utf-8">
    $('.btn-default').click(function () {
        var cip = returnCitySN['cip'];
        var cname = returnCitySN['cname'];
        var productId = $('#inputProductId').val();
        var productQuantity = $('#inputProductQuantity').val();
        var buyerName = $('#inputBuyerName').val();
        var buyerPhone = $('#inputBuyerPhone').val();
        var buyerAddress = $('#inputBuyerAddress').val();
        var url = '/sell/payH5/create';
        var returnUrl = '/sell/payH5/index';
        var fp = new Fingerprint2();
        fp.get(function (result) {
            $.get(url, {
                        'productId': productId,
                        'buyerName': buyerName,
                        'buyerPhone': buyerPhone,
                        'buyerAddress': buyerAddress,
                        'productQuantity': productQuantity,
                        'cip': cip,
                        'cname': cname,
                        'returnUrl': returnUrl,
                        'fingerPrint': result
                    },
                    function (data) {
                        alert("Data Loaded: " + data);
                    });
        });
    });
</script>

</body>
</html>
