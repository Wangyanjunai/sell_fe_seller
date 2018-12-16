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
                    <div class="col-md-12 column">
                        <form id="uploadForm" role="form" action="/sell/book/info/upload" method="POST" enctype="multipart/form-data">
	                        <div class="progress active progress-striped">
	                            <div class="progress-bar progress-success"></div>
	                        </div>
                            <div class="form-group" <#if (bookInfo.bookId) ?? && bookInfo.bookId != ''> style="display: none" </#if>>
                                <label for="inputFile">选择电子书：</label><input name="file" type="file" id="inputFile" />
                            </div>
                            <button type="submit" class="btn btn-default">上传</button>
                        </form>
                        <form id="addForm" role="form" action="/sell/book/info/add" method="POST">
                            <div class="form-group">
                                <label for="inputFileName">文件名称：</label>
                                <input type="text" value="${bookInfo.fileName !''}" name="fileName" placeholder="文件名称" class="form-control" id="inputFileName" />
                            </div>
                            <div class="form-group">
                                <label for="inputAuthor">作者：</label>
                                <input type="text" value="${bookInfo.author !''}" name="author" placeholder="作者" class="form-control" id="inputAuthor" />
                            </div>
                            <div class="form-group">
                                <label for="inputTitle">标题：</label>
                                <input type="text" value="${bookInfo.title !''}" name="title" placeholder="标题" class="form-control" id="inputTitle" />
                            </div>
                            <div class="form-group">
                                <label for="inputPublish">出版社：</label>
                                <input type="text" value="${bookInfo.publisher !''}" name="publish" placeholder="出版社" class="form-control" id="inputPublish" />
                            </div>
                            <div class="form-group">
                                <label for="inputCover">封面图片：</label>
                                <input type="text" value="${bookInfo.cover !''}" name="cover" placeholder="封面图片" class="form-control" id="inputCover" />
                            </div>
                            <button type="submit" class="btn btn-default">添加</button>
                        </form>
                    </div>
                </div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/sell/js/common/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="http://blueimp.github.io/jQuery-File-Upload/js/jquery.fileupload.js"></script>
    <script type="text/javascript" src="/sell/js/model/upload.js"></script>
</body>
</html>
