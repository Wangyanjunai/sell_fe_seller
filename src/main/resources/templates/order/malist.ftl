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
                        <!--表格数据-->
                        <div class="col-md-12 column">
                            <table class="table table-bordered table-hover table-condensed">
                                <thead>
                                <tr>
                                    <th>订单id</th>
                                    <th>姓名</th>
                                    <th>手机号</th>
                                    <th>地址</th>
                                    <th>金额</th>
                                    <th>订单状态</th>
                                    <th>支付状态</th>
                                    <th>创建时间</th>
                                    <th colspan="3">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#list orderDTOPage.content as orderDTO>
                                        <tr class="success">
                                            <td>${orderDTO.orderId}</td>
                                            <td>${orderDTO.buyerName}</td>
                                            <td>${orderDTO.buyerPhone}</td>
                                            <td>${orderDTO.buyerAddress}</td>
                                            <td>${orderDTO.orderAmount}</td>
                                            <td>${orderDTO.getOrderStatusEnum().getMessage()}</td>
                                            <td>${orderDTO.getPayStatusEnum().getMessage()}</td>
                                            <td>${orderDTO.createTime}</td>
                                            <td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                                            <td>
                                                <#if orderDTO.getOrderStatusEnum().getMessage() =="新下单">
                                                    <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                                                </#if>
                                            </td>
                                            <td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">去支付</a></td>
                                        </tr>
                                    </#list>
                                </tbody>
                            </table>
                        </div>
                        <!--分页-->
                        <div class="col-md-12 column">
                            <ul class="pagination pull-right">
		                        <#if currentPage lte 1>
		                            <li class="disabled"><a href="#">上一页</a></li>
		                        <#else>
		                            <li><a href="/sell/seller/order/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
		                        </#if>
			                    <#list 1..orderDTOPage.totalPages as index>
			                        <#if currentPage == index>
			                            <li class="disabled"><a href="#">${index}</a></li>
			                        <#else>
			                            <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
			                        </#if>
			                    </#list>
		                        <#if currentPage gte orderDTOPage.totalPages>
		                            <li class="disabled"><a href="#">下一页</a></li>
		                        <#else>
		                            <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
		                        </#if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
