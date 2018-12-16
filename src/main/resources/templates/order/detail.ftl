<html>
    <#include "../common/header.ftl">
<body>
    <div id="wrapper" class="toggled">
        <#--边栏 sidebar-->
        <#include "../common/nav.ftl">
        <#--主体内容区域 content-->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                <#--订单数据-->
                    <div class="col-md-8 column">
                        <table class="table table-hover table-bordered">
                            <thead>
                            <tr>
                                <th style="text-align: center">
                                    订单id
                                </th>
                                <th style="text-align: center">
                                    订单总金额
                                </th>
                                <th style="text-align: center">
                                    订单状态
                                </th>
                                <th style="text-align: center">
                                    支付状态
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="success" style="text-align: center">
                                <td>
                                ${orderDTO.orderId}
                                </td>
                                <td>
                                ${orderDTO.orderAmount}
                                </td>
                                <td>
                                ${orderDTO.orderStatusEnum.message}
                                </td>
                                <td>
                                ${orderDTO.payStatusEnum.getMessage()}
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                <#--订单详情表数据-->
                    <div class="col-md-12 column">
                        <table class="table table-hover table-bordered">
                            <thead>
                            <tr>
                                <th style="text-align: center">商品id</th>
                                <th style="text-align: center">商品名称</th>
                                <th style="text-align: center">价格</th>
                                <th style="text-align: center">数量</th>
                                <th style="text-align: center">总额</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list orderDTO.orderDetailList as orderDetail>
                                <tr class="warning" style="text-align: center">
                                    <td>
                                        ${orderDetail.productId}
                                    </td>
                                    <td>
                                        ${orderDetail.productName}
                                    </td>
                                    <td>
                                        ${orderDetail.productPrice}
                                    </td>
                                    <td>
                                        ${orderDetail.productQuantity}
                                    </td>
                                    <td>
                                        ${orderDetail.productPrice * orderDetail.productQuantity}
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                <#--按钮操作-->
                    <div class="col-md-12 column">
                        <#if orderDTO.getOrderStatusEnum().code == 0>
                            <a type="button" class="btn btn-primary btn-default active" href="/sell/seller/order/finish?orderId=${orderDTO.orderId}">完结订单</a>
                            <a type="button" class="btn btn-default active btn-danger" href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消订单</a>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>