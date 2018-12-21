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
                                    <th>修改时间</th>
                                    <th colspan="2">操作</th>
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
                                            <td>${orderDTO.getOrderStatusEnum().message}</td>
                                            <td>${orderDTO.getPayStatusEnum().message}</td>
                                            <td>${orderDTO.createTime}</td>
                                            <td>${orderDTO.updateTime}</td>
                                            <td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                                            <td>
                                                <#if orderDTO.getOrderStatusEnum().code == 0>
                                                    <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                                                </#if>
                                            </td>
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
			                    <#if orderDTOPage.totalPages gt 0>
                                    <#list 1..orderDTOPage.totalPages as index>
                                        <#if currentPage == index>
			                            <li class="disabled"><a href="#">${index}</a></li>
                                        <#else>
			                            <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                                        </#if>
                                    </#list>
                                <#else>
                                    <li class="disabled"><a href="#">${currentPage}</a></li>
			                    </#if>
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
        <#--弹窗-->
        <div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title" id="myModalLabel">【温馨提醒】</h4>
                    </div>
                    <div class="modal-body">你有新的订单</div>
                    <div class="modal-footer">
                        <button onclick="javascript:document.getElementById('notice').pause()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button onclick="location.reload()" type="button" class="btn btn-primary">查看新的订单</button>
                    </div>
                </div>
            </div>
        </div>

        <#--播放音乐-->
        <audio id="notice" loop="loop"><source src="/sell/mp3/song.mp3" type="audio/mpeg" /></audio>

        <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>

        <script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

        <script type="application/javascript">
            var websocket = null;
            if ("websocket" in window){
                   //开发环境（dev）：websocket = new WebSocket("wss://potato369.mynatapp.cc/sell/websocket");
                   //测试环境（test）： websocket = new WebSocket("wss://potato369.mynatapp.cc/sell/websocket");
                   //生产环境（prod）：
                    websocket = new WebSocket("wss://www.potato369.com:8090/sell/websocket");
            } else {
                alert("浏览器不支持WebSocket！！！");
            }
            websocket.onopen = function (ev) {
                console.log("建立连接！！！");
            }
            websocket.onclose = function (ev) {
                console.log("关闭连接！！！");
            }
            websocket.onmessage = function (ev) {
                console.log("收到消息：",ev.data);
                // 弹窗提醒，同时播放音乐
                $('#myModal').modal('show');
                document.getElementById("notice").play();
            }
            websocket.onerror = function (ev) {
                alert("WebSocket通讯发生错误！！！");
            }
            window.onbeforeunload = function (ev) {
                websocket.close();
            }
        </script>
    </body>
</html>
