<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="../common/easyui_head.jsp" />
<script>
var ACTION={DELETE:"DELETE", VERIFY:"VERIFY", UNVERIFY:"UNVERIFY"};
	$(function() {
		$("#data").datagrid({
			url : "json/ins_list_inses",
			<s:if test="uid>0">
			queryParams : {
				uid : <s:property value="uid"/>
			},
			</s:if>
			loadFilter : function(data) {
				return data.inses;
			},
			columns : [ [ {
				field : "ck",
				checkbox : true
			}, {
				title : "ID",
				field : "id",
				align : "center"
			}, {
				title : "文件类型",
				field : "fileType",
				align : "center",
				formatter: function(value,row,index){
					if(value.toUpperCase()=="PDF"){
						return "<span style='color:white;background-color:Crimson;font-size:12px;font-family: arial, helvetica, sans-serif;'>PDF</span>";
					}else{
						return "<span style='color:white;background-color:SteelBlue;font-size:12px;font-family: arial, helvetica, sans-serif;'>DOC</span>";
					}
				}
			}, {
				title : "标题",
				field : "title",
				formatter: function(value,row,index){
					return "<a href='/ins/"+row.id+".html' target='_blank'>"+value+"</a>";
				}
			}, {
				title : "上传用户",
				field : "username",
				align : "center",
				formatter: function(value,row,index){
					return row.user.username
				}
			}, {
				title : "分类",
				field : "cid",
				align : "center",
				formatter: function(value,row,index){
					return row.category.name
				}
			}, {
				title : "品牌",
				field : "bid",
				align : "center",
				formatter: function(value,row,index){
					if(row.brand!=null)
					return row.brand.name;
					else return "<span style='color:red;'>未指定</span>";
				}
			}, {
				title : "上传时间",
				field : "uploadTime",
				align : "center",
				sortable:true,
				formatter:function(value,row,index){
					return (new Date(value*1000)).pattern("yyyy-MM-dd HH:mm:ss");
				}
			}, {
				title : "更新时间",
				field : "updateTime",
				align : "center",
				sortable:true,
				formatter:function(value,row,index){
					return (new Date(value*1000)).pattern("yyyy-MM-dd HH:mm:ss");
				}
			}, {
				title : "状态",
				field : "status",
				sortable:true,
				formatter: function(value,row,index){
					switch (value) {
					case -1:
						return "<span style='color:gray;'>不正常</span>";
						break;
					case 0:
						return "<span style='color:red;'>未通过审核</span>";
						break;
					case 1:
						return "<span style='color:green;'>已通过审核</span>";
						break;
					}
				},
				align : "center"
			}]],
			pageSize:20
		}).datagrid("getPager").pagination({
		    //displayMsg:'当前显示第 {from} 条到第 {to} 条记录，共有 {total} 条记录',
		    showPageList:true,
		    //beforePageText:'当前第',
		    //afterPageText:'页，本页共 {pages}条记录',
		    //total:300,
		    pageSize:20,
		    pageList:[10,20,30,40,50,60,70,80,100,120,150,200,250,300],
		    buttons:[{  
	            iconCls:"icon-ok",
	            text:"审核通过",
	            handler:function(){
	            	operateIns(ACTION.VERIFY);
	            }  
	        },{  
	            iconCls:"icon-undo",
	            text:"审核不通过",
	            handler:function(){
	            	operateIns(ACTION.UNVERIFY);
	            }  
	        },{  
	            iconCls:"icon-cancel",
	            text:"删除",
	            handler:function(){
	            	$.messager.confirm("注意", "确定要删除选中说明书吗？删除后将不能恢复！", function(r){
	                    if (r){
	                    	operateIns(ACTION.DELETE);
	                    }
	                });
	            }  
	        },{  
	            iconCls:"icon-edit",
	            text:"编辑",
	            handler:function(){
	            	editIns();
	            }  
	        }]
		});
		
		
		
		
		$("#cid").combobox({
			valueField: "id",
			textField: "name",
			mode: "remote",
			formatter: function(row){
				var opts = $(this).combobox("options");
				var parentId=0;
				$.map($(this).combobox("getData"), function(item){
					if(item.id==row[opts.valueField])
						parentId=item.parentId;
                });
				if(parentId==0)
			       return "<strong>"+row[opts.textField]+"</strong>";
			    else 
			    	return "&nbsp&nbsp&nbsp&nbsp"+row[opts.textField];
			},
			loader:function(param,success,error){
	              $.getJSON("json/category_list_categorys",{},function(data){
	                var items = $.map(data.categorys, function(item){
	                		return {
	                        	id: item.id,
	                        	name: item.name,
	                        	parentId: item.parentId
	                        };
	                });
	                success(items);
	              });
			},
			onSelect:function(rec){
		        $.getJSON("json/category_brandsByCid_brands",{"cid":rec.id},function(data){
				   $("#bid").combobox("clear").combobox("loadData",data.brands);
		        });
			}
		});
		
		
		//初始化uid
		<s:if test="uid>0">
		  $("#uid").combobox("setValue",<s:property value="uid"/>).combobox("setText","<%=request.getParameter("username")%>");
		</s:if>

	});
	

	var userLoader = function(param,success,error){
        var q = param.q || "";
        if (q.length <= 1){return false}
        $.getJSON("json/user_list_userview",{"username": q},function(data){
                var items = $.map(data.userview.rows, function(item){
                    return {
                    	uid: item.uid,
                    	username: item.username,
                    };
                });
                success(items);
        });
    }
	
	var titleLoader = function(param,success,error){
        var q = param.q || "";
        if (q.length <= 1){return false}
        $.getJSON("json/ins_list_inses",{"search": q},function(data){
                var items = $.map(data.inses.rows, function(item){
                    return {
                    	title: item.title,
                    };
                });
                success(items);
        });
    }
	
	function operateIns(operate){
		var ids = "";
		var rows = $("#data").datagrid("getSelections");
		for ( var i = 0; i < rows.length; i++) {
			    var row = rows[i];
				if (i > 0)
					ids += ",";
				ids += row.id;
		}
		if (ids.length > 0) {
			$.getJSON("json/ins_operate_feedback", {
				"ids" : ids,
				"operate":operate
			}, function(data) {
				if (data.feedback > 0) {
					$("#data").datagrid("reload");
					$.messager.show({
						title : "成功",
						msg : "成功了" + data.feedback + "记录"
					});
				} else {
					$.messager.alert("操作没有成功", "操作没有成功");
				}
			});
		} else {
			$.messager.alert("出错了", "请至少选择一条有效");
		}
	}
	
	function editIns(){
		var rows = $("#data").datagrid("getSelections");
		if(rows.length<=0)$.messager.alert("出错了", "请选择一项有效");
		else if(rows.length>1)$.messager.alert("出错了", "只可以选择一项进行编辑");
		else{
			parent.openTab("编辑说明书("+rows[0].id+")","ins_ins?id="+rows[0].id);
		}
	}
	
	function search() {
		$("#data").data().datagrid.cache = null;
		var queryparams = $("#data").datagrid("options").queryParams;
		var startTS=Math.floor((new Date($("#startTimeStamp").datetimebox("getValue")).getTime() )/ 1000);
		var endTS=Math.floor((new Date($("#endTimeStamp").datetimebox("getValue")).getTime() )/ 1000);
		queryparams["tsRange.startTimeStamp"]=startTS;
		queryparams["tsRange.endTimeStamp"]=endTS;
		queryparams["search"]=$("#title").combobox("getValue");
		queryparams["uid"]=$("#uid").combobox("getValue");
		queryparams["cid"]=$("#cid").combobox("getValue");
		queryparams["bid"]=$("#bid").combobox("getValue");
		$("#data").datagrid("load");
	}
	 
</script>
<body>
	<div id="tb" style="padding:5px;height:auto;">
		<div>
			用户: <input class="easyui-combobox" id="uid" style="width:100px"
				data-options="loader:userLoader,mode: 'remote',valueField: 'uid',textField: 'username',panelHeight:'auto'">
			标题: <input class="easyui-combobox" id="title" style="width:200px"
				data-options="loader:titleLoader,mode: 'remote',valueField: 'title',textField: 'title',panelHeight:'auto'">
			分类: <input id="cid" editable="false"></input> 品牌: <input class="easyui-combobox" id="bid"
				editable="false" data-options="mode: 'remote',valueField: 'id',textField: 'name'"> 开始日期:
			<input class="easyui-datetimebox" style="width:160px" id="startTimeStamp"
				value="<s:bean name="instruction.util.Time" id="time"><s:property escape="false"  value="#time.timeFormat(tsRange.startTimeStamp,'MM/dd/yyyy hh:mm:ss')"/></s:bean>">
			截止日期: <input class="easyui-datetimebox" style="width:160px" id="endTimeStamp"
				value="<s:bean name="instruction.util.Time" id="time"><s:property escape="false"  value="#time.timeFormat(tsRange.endTimeStamp,'MM/dd/yyyy hh:mm:ss')"/></s:bean>">
			<a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search" type="sub"
				plain="true">搜索</a>
		</div>
	</div>
	<table id="data" fit="true" pagination="true" data-options="toolbar:'#tb'" border="no">
	</table>

</body>
</html>