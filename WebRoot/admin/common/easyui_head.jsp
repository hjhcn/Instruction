<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="resources/easyui/themes/gray/easyui.css" />
<link rel="stylesheet" type="text/css" href="resources/easyui/themes/icon.css" />
<script type="text/javascript" src="resources/easyui/jquery.min.js"></script>
<script type="text/javascript" src="resources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="resources/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="resources/easyui/plugins/datagrid-detailview.js"></script>
<script type="text/javascript">
	$(function() {
		$.ajaxSetup({
			cache : false
		//关闭AJAX相应的缓存
		});
	});

	Date.prototype.pattern = function(fmt) {
		var o = {
			"M+" : this.getMonth() + 1, //月份        
			"d+" : this.getDate(), //日        
			"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时        
			"H+" : this.getHours(), //小时        
			"m+" : this.getMinutes(), //分        
			"s+" : this.getSeconds(), //秒        
			"q+" : Math.floor((this.getMonth() + 3) / 3), //季度        
			"S" : this.getMilliseconds()
		//毫秒        
		};
		var week = {
			"0" : "\u65e5",
			"1" : "\u4e00",
			"2" : "\u4e8c",
			"3" : "\u4e09",
			"4" : "\u56db",
			"5" : "\u4e94",
			"6" : "\u516d"
		};
		if (/(y+)/.test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		}
		if (/(E+)/.test(fmt)) {
			fmt = fmt.replace(RegExp.$1,
					((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f" : "\u5468")
							: "")
							+ week[this.getDay() + ""]);
		}
		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(fmt)) {
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k])
						.substr(("" + o[k]).length)));
			}
		}
		return fmt;
	}
</script>

<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}
</style>
<script type="text/javascript">
	var JSESSIONID = "${pageContext.session.id}";
</script>
<script type="text/javascript" src="resources/scripts/swfobject.js"></script>
<script type="text/javascript" src="resources/scripts/uploadify.js"></script>
<title>生活网说明书管理后台</title>
</head>