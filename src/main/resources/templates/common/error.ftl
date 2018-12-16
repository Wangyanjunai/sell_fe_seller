<html>
    <#include "../common/header.ftl">
    <body>
    <div id="wrapper" class="toggled">
            <#--边栏 sidebar-->
        <#include "../common/nav.ftl">
            <#--主体内容区域 content-->
        <div id="page-content-wrapper">
            <div class="container">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <div class="alert alert-dismissable alert-danger">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                            <h4>
                                错误！
                            </h4><strong>${msg}</strong><a href="${url}" class="alert-link">3s后自动跳转</a>
                        </div>
                    </div>
                </div>
            </div>
            <script type="application/javascript">
                setTimeout("location.href='${url}'",3000);
            </script>
        </div>
    </div>
    </body>
</html>