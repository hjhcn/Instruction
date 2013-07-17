<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="../common/easyui_head.jsp" />
<script>
	var editedIndex = [];
	var editIndex = undefined;
	$(function() {
		$("#data").datagrid({
			url : "json/brand_list_brands",
			loadFilter : function(data) {
				return data.brands;
			},
			columns : [ [ {
				field : "ck",
				checkbox : true
			}, {
				field : "id",
				hidden : true
			}, {
				title : "常用名",
				field : "name",
				align : "center",
				editor : {
					type : "text"
				}
			}, {
				title : "中文名",
				field : "nameZh",
				editor : {
					type : "text"
				}
			}, {
				title : "英文名",
				field : "nameEn",
				align : "center",
				editor : {
					type : "text"
				}
			}, {
				title : "说明书总数",
				field : "count",
				align : "center"
			} ] ],
			pageNumber : 1,
			pageSize : 20,
			toolbar : [ {
				iconCls : "icon-cancel",
				text : "删除",
				handler : function() {
					delBrand();
				}
			}, '-', {
				iconCls : "icon-ok",
				text : "确定",
				handler : function() {
					ok();
				}
			}, {
				iconCls : "icon-undo",
				text : "撤销",
				handler : function() {
					reject();
				}
			}, {
				iconCls : "icon-save",
				text : "保存",
				handler : function() {
					save();
				}
			}, '-', {
				iconCls : "icon-add",
				text : "添加",
				handler : function() {
					addBrand();
				}
			} ],
			onClickRow : function(index) {
				if (editIndex != index) {
					if (endEditing()) {
						$('#data').datagrid('selectRow', index).datagrid('beginEdit', index);
						editIndex = index;
						editedIndex.push(index);
					} else {
						$('#data').datagrid('selectRow', editIndex);
					}
				}
			}
		}).datagrid("getPager").pagination({
			showPageList : true,
			pageSize : 20,
			buttons : [ {
				iconCls : "icon-cancel",
				text : "删除",
				handler : function() {
					delBrand();
				}
			} ]
		});
	});

	function endEditing() {
		if (editIndex == undefined) {
			return true
		}
		if ($("#data").datagrid("validateRow", editIndex)) {
			$("#data").datagrid("endEdit", editIndex);
			editIndex = undefined;
			return true;
		} else {
			alert("validateRow");
			return false;
		}
	}
	function ok() {
		if (endEditing()) {
			$("#data").datagrid("acceptChanges");
		}
	}
	function reject() {
		$("#data").datagrid("rejectChanges");
		editIndex = undefined;
	}
	function save() {
		var rows = $("#data").datagrid("getRows");
		var param = {};
		for ( var i = 0; i < editedIndex.length; i++) {
			param["brandInputs[" + i + "].id"] = rows[editedIndex[i]].id;
			param["brandInputs[" + i + "].name"] = rows[editedIndex[i]].name;
			param["brandInputs[" + i + "].nameZh"] = rows[editedIndex[i]].nameZh;
			param["brandInputs[" + i + "].nameEn"] = rows[editedIndex[i]].nameEn;
		}
		$.ajax({
			url : "json/brand_edit_feedback",
			data : param,
			success : function(data) {
				if (data.feedback > 0) {
					$.messager.show({
						title : "成功",
						msg : "修改成功" + data.feedback + "条记录"
					});
					editedIndex = [];
					editIndex = undefined;
				} else {
					$.messager.show({
						title : "操作没有成功",
						msg : "操作没有成功"
					});
					$("#data").datagrid("reload");
				}
			}
		});
	}

	function delBrand() {
		var row = $("#data").datagrid("getSelected");
		if (row != null) {
			$.getJSON("/admin/json/brand_delete_feedback?time=" + new Date().getTime() / 1000, {
				"bid" : row.id
			}, function(data) {
				if (data.feedback > 0) {
					$("#data").datagrid("reload");
					$.messager.show({
						title : "成功",
						msg : "成功删除了'" + row.name + "'"
					});
				} else {
					$.messager.alert("操作没有成功", "操作没有成功");
				}
			});
		} else {
			$.messager.alert("出错了", "请选择一个品牌再点击删除");
		}
	}
	function addBrand() {
		$("#dlg").dialog("open").dialog("setTitle", "品牌管理");
	}
	function saveBrand() {
		$("#fm").form("submit", {
			url : "json/brand_add_feedback",
			onSubmit : function() {
				return $(this).form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')'); 
				if (data.feedback == -802) {
					$.messager.show({
						title : "出错了",
						msg : "该常用名已经存在"
					});
				} else {
					$("#dlg").dialog("close"); // close the dialog
					$("#data").datagrid("reload"); // reload the user data
				}
			}
		});
	}
</script>
<body>
	<table id="data" pagination="true" fit="true" data-options="rownumbers:true,singleSelect:true"
		border="no"></table>
	<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
		closed="true" buttons="#dlg-buttons">
		<div class="ftitle">添加品牌</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>常用名:</label> <input name="brand.name" class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>中文名:</label> <input name="brand.nameZh" class="easyui-validatebox">
			</div>
			<div class="fitem">
				<label>英文名:</label> <input name="brand.nameEn">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:saveBrand()" class="easyui-linkbutton" iconCls="icon-ok">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
</body>