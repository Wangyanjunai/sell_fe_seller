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
						<form role="form" method="post" action="/sell/book/category/save">
							<div class="form-group">
								 <label>类目名称</label><input name="categoryName" type="text" class="form-control" value="${(bookCategory.categoryName) !''}" />
							</div>
							<div class="form-group">
								<label>父级类目</label>
								<select name="parentCategoryId" class="form-control" <#if (bookCategory.parentCategoryId)?? && bookCategory.parentCategoryId == 0> disabled="disabled"</#if>>
									<option value="0" <#if (bookCategory.parentCategoryId)?? && bookCategory.parentCategoryId == 0> selected</#if>>不存在</option>
									<option value="100" <#if (bookCategory.parentCategoryId)?? && bookCategory.parentCategoryId == 100> selected</#if>>出版</option>
									<option value="200" <#if (bookCategory.parentCategoryId)?? && bookCategory.parentCategoryId == 200> selected</#if>>男版</option>
									<option value="300" <#if (bookCategory.parentCategoryId)?? && bookCategory.parentCategoryId == 300> selected</#if>>女版</option>
								</select>
							</div>
							<div class="form-group">
								 <label>类目类型</label>
								 <input name="categoryType" type="number" class="form-control" value="${(bookCategory.categoryType) !''}" <#if (bookCategory.parentCategoryId)?? && bookCategory.parentCategoryId == 0>disabled="disabled"</#if>/>
							</div>
							<div class="form-group">
								<label>是否删除</label>
								<select name="isDeleted" class="form-control">
									<option value="0" <#if (bookCategory.isDeleted)?? && bookCategory.isDeleted == 0> selected</#if>>否</option>
									<option value="1" <#if (bookCategory.isDeleted)?? && bookCategory.isDeleted == 1> selected</#if>>是</option>
								</select>
							</div>
							<input hidden type="number" name="categoryId" value="${(bookCategory.categoryId) !''}">
							<button type="submit" class="btn btn-default">提交</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
