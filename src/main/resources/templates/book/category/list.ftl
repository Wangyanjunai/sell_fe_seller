<html>
    <#include "../../common/header.ftl">
<body>
    <div id="wrapper" class="toggled">
        <#--边栏 sidebar-->
        <#include "../../common/nav.ftl">
        <#--主体内容区域 content-->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <!--表格数据-->
                    <div class="col-md-12 column">
                        <table class="table table-bordered table-hover table-condensed">
                            <thead>
                            <tr>
                                <th>类目id</th>
                                <th>父级类目id</th>
                                <th>名称</th>
                                <th>类目类型</th>
                                <th>是否删除</th>
                                <th>创建时间</th>
                                <th>修改时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list bookCategoryPage.content as category>
                            <tr class="success">
                                <td>${category.categoryId}</td>
                                <td>${(category.parentCategoryId)!''}</td>
                                <td>${category.categoryName}</td>
                                <td>${category.categoryType}</td>
                                <td>
                                    <#if category.isDeleted == 0>
                                        否
                                        <#elseif category.isDeleted == 1>
                                        是
                                    </#if>
                                </td>
                                <td>${category.createTime}</td>
                                <td>${category.updateTime}</td>
                                <td><a href="/sell/book/category/index?categoryId=${category.categoryId}">修改</a></td>
                                <td>
                                    <#if category.isDeleted == 0>
                                        <a href="/sell/book/category/delete?categoryId=${category.categoryId}">删除</a>
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
                                <li><a href="/sell/book/category/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                            </#if>

                            <#if bookCategoryPage.totalPages gt 0>
                                <#list 1..bookCategoryPage.totalPages as index>
                                    <#if currentPage == index>
                                        <li class="disabled"><a href="#">${index}</a></li>
                                    <#else>
                                        <li><a href="/sell/book/category/list?page=${index}&size=${size}">${index}</a></li>
                                    </#if>
                                </#list>
                            <#else>
                                <li class="disabled"><a href="#">${currentPage}</a></li>
                            </#if>

                            <#if currentPage gte bookCategoryPage.totalPages>
                                <li class="disabled"><a href="#">下一页</a></li>
                            <#else>
                                <li><a href="/sell/book/category/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                            </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
