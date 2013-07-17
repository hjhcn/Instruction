<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="../common/easyui_head.jsp" />
<script>
	$(function() {
		$("#data").datagrid({
			url : "json/credit_listLog_creditLogs",
			<s:if test="uid!=0">
			queryParams : {
				uid : <s:property value="uid"/>
			},
			</s:if>
			loadFilter : function(data) {
				return data.creditLogs;
			},
			columns : [ [ {
				field : "ck",
				checkbox : true
			}, {
				title : "id",
				field : "id",
				width : 50,
				align : "center"
			}, {
				title : "用户名",
				field : "username",
				align : "center",
				formatter: function(value,row,index){
					if (row.user){
						return row.user.username;
					} else {
						return value;
					}
				}
			}, {
				title : "来福币",
				field : "credit",
				sortable:true,
				align : "center"
			}, {
				title : "记录产生时间",
				field : "createTime",
				width : 145,
				align : "center",
				sortable:true,
				formatter:function(value,row,index){
					return (new Date(value*1000)).pattern("yyyy-MM-dd HH:mm:ss");
				}

			}, {
				title : "来福币交易时间",
				field : "dateline",
				width : 145,
				align : "center",
				sortable:true,
				formatter:function(value,row,index){
					return (new Date(value*1000)).pattern("yyyy-MM-dd HH:mm:ss");
				}
			}, {
				title : "来福币同步状态",
				field : "isSync",
				formatter: function(value,row,index){
					if (value==0){
						return "<span style='color:red;'>同步来福币失败</span>";
					} else if (value==1){
						return "<span style='color:green;'>同步来福币成功</span>";
					}
				},
				sortable:true,
				width : 100
			}, {
				title : "来福币交易原因",
				field : "description",
				formatter: function(value,row,index){
					return "<span title='"+row.description+"'>"+row.description+"</span>";
				}
			}, {
				title : "欣网返回信息",
				field : "syncMessage",
				formatter: function(value,row,index){
					if (row.isSync==0){
						return "<span style='color:red;'>"+value+"</span>";
					} else if (row.isSync==1){
						return "<span style='color:green;'>"+value+"</span>";
					}
				}
			}, {
				title : "订单号",
				field : "orderNumber",
				sortable:true
			} ] ],
			pageSize:20
		}).datagrid("getPager").pagination({
		    showPageList:true,
		    pageSize:20,
		    pageList:[10,20,30,40,50,60,70,80,100,120,150,200,250,300],
		    buttons:[{  
	            iconCls:"icon-do",
	            text:"再次请求来福币",
	            handler:function(){
	            	var ids="";
	            	var rows = $("#data").datagrid("getSelections");  
	                for(var i=0; i<rows.length; i++){
	                    var row = rows[i]; 
	                	if(row.isSync==0){ 
		                    if(i>0)ids+=",";
		                    ids+=row.id;
	                	}
	                }
	                if(ids.length>0){
	                	$.getJSON("json/credit_reCredit_feedback?time="
								+ new Date().getTime() / 1000, {
							"ids" : ids
						}, function(data) {
							if (data.feedback > 0) {
								$("#data").datagrid("reload");
								$.messager.show({   
                                    title: "成功",  
                                    msg: "成功了"+data.feedback+"记录"  
                                }); 
							} else {
				                $.messager.alert("操作没有成功", "操作没有成功"); 
							}
						});
	                }else{
	                	$.messager.alert("出错了", "请选择同步失败记录"); 
	                }
	            }  
	        }]
		});
		
		
		$("#credit-rule").combogrid({
			panelWidth: 500,  
            idField: "crid",  
            textField: "name",  
            url: "json/credit_listRule_creditRules", 
            loadFilter : function(data) {
				return data.creditRules;
			} ,
            columns: [[  
                {field:'id',title:'规则ID',width:20,align:'center'},  
                {field:'name',title:'名称',width:50},  
                {field:'description',title:'描述',width:120}
            ]],  
            fitColumns: true
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
	
	function search() {
		$("#data").data().datagrid.cache = null;
		var queryparams = $("#data").datagrid("options").queryParams;
		queryparams.uid = $("#uid").combobox("getValue");
		queryparams.startTimeStamp = new Date($("#startTimeStamp").datetimebox("getValue"))
				.getTime() / 1000;
		queryparams.endTimeStamp = new Date($("#endTimeStamp").datetimebox("getValue")).getTime() / 1000;
		var selectRule = $("#credit-rule").combogrid("grid").datagrid("getSelected");
		if(selectRule!=null){
			queryparams.crid = selectRule.id;
		}else{
			queryparams.crid=0;
		}
		$("#data").datagrid("load");
	}
	 
</script>
<body>
	<div id="tb" style="padding:5px;height:auto">
		<div>
			用户: <input class="easyui-combobox" id="uid" style="width:100px"
				data-options="loader:userLoader,mode: 'remote',valueField: 'uid',textField: 'username',panelHeight:'auto'">
			来福币原因：<select id="credit-rule" style="width:250px"></select> 开始日期: <input
				class="easyui-datetimebox" style="width:160px" id="startTimeStamp"
				value="<s:bean name="instruction.util.Time" id="time"><s:property escape="false"  value="#time.timeFormat(startTimeStamp,'MM/dd/yyyy hh:mm:ss')"/></s:bean>">
			截止日期: <input class="easyui-datetimebox" style="width:160px" id="endTimeStamp"
				value="<s:bean name="instruction.util.Time" id="time"><s:property escape="false"  value="#time.timeFormat(endTimeStamp,'MM/dd/yyyy hh:mm:ss')"/></s:bean>">
			<a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search" type="sub"
				plain="true">搜索</a>
		</div>
	</div>
	<table id="data" pagination="true" fit="true" data-options="toolbar:'#tb'" border="no">
	</table>
</body>
</html>