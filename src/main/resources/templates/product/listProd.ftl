<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf8"/>
    <meta id="viewport" name="viewport"
          content="width=device-width; initial-scale=1.0; maximum-scale=1; user-scalable=no;"/>
    <link rel="Bookmark" href="/sell/favicon.ico"/>
    <link rel="Shortcut Icon" href="/sell/favicon.ico"/>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="/sell/css/listProd.css">
    <script type="text/javascript" src="/sell/js/jquery-1.9.1.min.js"></script>
<#--<script type="text/javascript" src="http://pv.sohu.com/cityjson?ie=utf-8"></script>-->
    <title>【微信支付V2.0】MWEB支付实例</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column color-rect-border">
          <#list productInfoPage.content as productInfo>
              <form class="form-horizontal" role="form">
                  <div class="form-group">
                      <input type="hidden" name="productId" value="${productInfo.productId}" class="form-control"
                             id="inputProductId"
                             readonly/>
                      <label for="inputProductName" class="col-sm-2 control-label">名称（Name）</label>
                      <div class="col-sm-10">
                          <input type="text" name="productName" value="${productInfo.productName}" class="form-control"
                                 id="inputProductName"
                                 readonly/>
                      </div>
                  </div>
                  <div class="form-group">
                      <label for="inputProductPrice" class="col-sm-2 control-label">价格（Price 元）</label>
                      <div class="col-sm-10">
                          <input type="number" name="productPrice" value="${productInfo.productPrice}"
                                 class="form-control"
                                 id="inputProductPrice" readonly/>
                      </div>
                  </div>
                  <div class="form-group">
                      <label for="inputProductIcon" class="col-sm-2 control-label">图片（Icon）</label>
                      <input type="hidden" name="productIcon" value="${productInfo.productIcon}" class="form-control"
                             id="inputProductIcon"
                             readonly/>
                      <div class="col-md-12 column">
                          <img height="100" width="100" src="${productInfo.productIcon}" class="img-circle">
                      </div>
                  </div>
                  <div class="form-group">
                      <label for="inputProductDescription" class="col-sm-2 control-label">描述（Description）</label>
                      <div class="col-sm-10">
                          <input type="text" name="productDescription" value="${productInfo.productDescription}"
                                 class="form-control"
                                 id="inputProductDescription" readonly/>
                      </div>
                  </div>
                  <div class="form-group">
                      <label for="inputProductQuantity" class="col-sm-2 control-label">数量（Quantity 份）</label>
                      <div class="col-sm-10">
                          <input type="number" name="productQuantity" value="1" class="form-control"
                                 id="inputProductQuantity"/>
                      </div>
                  </div>
                  <div class="form-group">
                      <label for="inputCategoryType" class="col-sm-2 control-label">类目（Category）</label>
                      <div class="col-sm-10">
                          <#list productCategoryList as productCategory>
                              <#if (productInfo.categoryType)?? && productInfo.categoryType == productCategory.categoryType>
                                  <input type="text" name="categoryName" value="${productCategory.categoryName}"
                                         class="form-control"
                                         id="inputCategoryName" readonly/>
                              </#if>
                          </#list>
                          <input type="hidden" name="categoryType" value="${productInfo.categoryType}"
                                 class="form-control"
                                 id="inputCategoryType"/>
                      </div>
                  </div>
                  <div class="form-group">
                      <div class="col-sm-offset-2 col-sm-10">
                          <div class="operation">
                              <a class="btn-green">添加到购物车</a>
                          <#--<button type="submit" class="btn-green">添加到购物车</button>-->
                          </div>
                      </div>
                  </div>
              </form>
          </#list>
        </div>
    </div>
    <!--分页-->
    <div class="col-md-12 column">
        <ul class="pagination pull-right">
                <#if currentPage lte 1>
                    <li class="disabled"><a href="#">上一页</a></li>
                <#else>
                    <li><a href="/sell/payH5/index?page=${currentPage - 1}&size=${size}">上一页</a></li>
                </#if>
                <#list 1..productInfoPage.totalPages as index>
                    <#if currentPage == index>
                        <li class="disabled"><a href="#">${index}</a></li>
                    <#else>
                        <li><a href="/sell/payH5/index?page=${index}&size=${size}">${index}</a></li>
                    </#if>
                </#list>
                <#if currentPage gte productInfoPage.totalPages>
                    <li class="disabled"><a href="#">下一页</a></li>
                <#else>
                    <li><a href="/sell/payH5/index?page=${currentPage + 1}&size=${size}">下一页</a></li>
                </#if>
        </ul>
    </div>
</div>
<#--<script type="text/javascript" src="https://wx.gtimg.com/wxpay_h5/fingerprint2.min.1.5.1.js"></script>-->
<#--<script type="text/javascript" src="http://wxpay.wxutil.com/css/zepto.min.js"></script>-->
<script type="text/javascript" charset="UTF-8">
    $('.operation').click(function () {
        // $(this).findRule('form').attr('method','POST').attr('action','/sell/buyer/order/addToCart').submit();
        $(this).parents('form[role="form"]').attr('method', 'post').attr('action', '/sell/buyer/order/addToCart').submit();
        // var cip = returnCitySN['cip'];
        // var cname = returnCitySN['cname'];
        // var productId = $('#inputProductId').val();
        // var url = '/sell/payH5/create';
        // var returnUrl = '/sell/payH5/index';
        // var fp = new Fingerprint2();
        // fp.get(function (result) {
        //     $.ajax({
        //         type: 'GET',
        //         dataType: 'json',
        //         url: url,
        //         data: {
        //             'productId': productId,
        //             'cip': cip,
        //             'cname': cname,
        //             'returnUrl': returnUrl,
        //             'fingerPrint': result
        //         },
        //         success: function (data) {
        //             if (data.errmsg == '') {
        //                 $('#getBrandWCPayRequest').attr("href", data.url);
        //             } else {
        //                 alert(data.errmsg);
        //             }
        //         },
        //         error: function () {
        //             alert('error');
        //         }
        //     });
        //     // fp.get(function(result) {
        //     //     $.getJSON(url+"?productId="+productId+"&cip="+cip+"&cname="+cname+"&returnUrl="+returnUrl+"&fingerPrint"+result, function(data){
        //     //         if(data.errmsg == ''){
        //     //             $('#getBrandWCPayRequest').attr("href",data.url);
        //     //         }else{
        //     //             alert(data.errmsg);
        //     //         }
        //     //     });
        // });
    });
</script>

</body>
</html>
