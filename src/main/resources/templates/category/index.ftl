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
					<div class="col-md-12 column">
						<form role="form" method="post" action="/sell/seller/category/save">
							<div class="form-group">
								 <label>名称</label><input name="categoryName" type="text" class="form-control" value="${(productCategory.categoryName) !''}" />
							</div>
							<div class="form-group">
								 <label>类目类型</label>
								 <input name="categoryType" type="number" class="form-control" value="${(productCategory.categoryType) !''}" />
							</div>
							<div class="form-group">
								<label>是否删除</label>
								<select name="isDeleted" class="form-control">
									<option value="0" <#if (productCategory.isDeleted)?? && productCategory.isDeleted == 0> selected</#if>>否</option>
									<option value="1" <#if (productCategory.isDeleted)?? && productCategory.isDeleted == 1> selected</#if>>是</option>
								</select>
							</div>
							<input hidden type="number" name="categoryId" value="${(productCategory.categoryId) !''}">
							<button type="submit" class="btn btn-default">提交</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
