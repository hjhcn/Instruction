<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="common/easyui_head.jsp" />
<script type="text/javascript">
	function openTab(title, url) {
		if ($('#tt').tabs('exists', title)) {
			$('#tt').tabs('close', title);
		}

		if (url) {
			var content = '<iframe scrolling="yes" frameborder="0"  src="' + url
					+ '" style="width:100%;height:100%;"></iframe>';
		} else {
			var content = '未实现';
		}
		$('#tt').tabs('add', {
			title : title,
			content : content,
			closable : true
		});
	}

	$(function() {
		$.ajaxSetup({
			cache : false
		//关闭AJAX相应的缓存
		});
	});
</script>
<style>
.nav-item {
	padding: 5 30 5 30;
	text-align: right;
}

a:link,a:visited {
	text-decoration: none;
	color: gray;
	font-size: 14px;
}

a:hover {
	text-decoration: underline;
	color: gray;
	font-size: 14px;
}

a.north:link,a.north:visited {
	text-decoration: none;
	color: black;
	font-size: 12px;
}

a.north:hover {
	text-decoration: underline;
	color: black;
	font-size: 12px;
}
</style>
<body class="easyui-layout">
	<div region="north" style="height:50px; overflow: hidden; padding-left: 20px;position: relative;">
		<div>
			<h2>生活网说明书管理后台</h2>
		</div>
		<div style="position:absolute;bottom: 2px;right: 5px; ">
			<span style="color:gray;">超级管理员:</span> <span style="color:gray;"><s:property
					value="#session.admin.username" /> </span> <a class="north" href="/" target="_blank">说明书前台</a> <a
				class="north" href="logout">退出登陆</a>
		</div>
	</div>
	<div region="west" split="true" style="width:200px;">
		<div id="aa" class="easyui-accordion" fit="true" border="false">
			<div title="说明书" selected="true">
				<div class="nav-item">
					<a href="javascript:openTab('说明书管理','ins_list_page')"><span>说明书管理</span> </a>
				</div>
				<div class="nav-item">
					<a href="javascript:openTab('创建说明书','ins_ins_page')"><span>说明书创建</span> </a>
				</div>
				<div class="nav-item">
					<a href="javascript:openTab('说明书统计','ins_statistic')"><span>说明书统计</span> </a>
				</div>
				<div class="nav-item">
					<a href="javascript:openTab('说明书导出','ins_exportPage')"><span>说明书导出</span> </a>
				</div>
			</div>
			<div title="品牌和分类">
				<div class="nav-item">
					<a href="javascript:openTab('分类管理','category_list')">分类管理</a>
				</div>
				<div class="nav-item">
					<a href="javascript:openTab('添加分类','category_addPage')">添加分类</a>
				</div>
				<div class="nav-item">
					<a href="javascript:openTab('品牌管理','brand_list')">品牌管理</a>
				</div>
			</div>
			<div title="积分管理">
				<div class="nav-item">
					<a href="javascript:openTab('积分详情','credit_list_log_page')">积分详情</a>
				</div>
				<div class="nav-item">
					<a href="javascript:openTab('积分规则','credit_list_rule_page')">积分规则</a>
				</div>
				<div class="nav-item">
					<a href="javascript:openTab('手动积分','credit_manual')">手动积分</a>
				</div>
			</div>
			<div title="文件管理">
				<div class="nav-item">
					<a href="javascript:openTab('上传文件管理','upload_list_page')">上传文件管理</a>
				</div>
			</div>
			<div title="用户管理">
				<div class="nav-item">
					<a href="javascript:openTab('用户管理','user_list_page')">用户信息管理</a>
				</div>
				<div class="nav-item">
					<a href="javascript:openTab('评论管理','comment_list_page')">评论管理</a>
				</div>
			</div>
			<div title="资讯管理">
				<div class="nav-item">
					<a href="javascript:openTab('资讯管理','news_list_page')">资讯管理</a>
				</div>
				<div class="nav-item">
					<a href="javascript:openTab('资讯发布','news_news_page')">资讯发布</a>
				</div>
			</div>
			<div title="热帖管理">
				<div class="nav-item">
					<a href="javascript:openTab('热帖管理','bbsThread_list')">热帖管理</a>
				</div>
				<div class="nav-item">
					<a href="javascript:openTab('热帖录入','bbsThread_addPage')">热帖录入</a>
				</div>
			</div>
		</div>
	</div>
	<div region="center">
		<div id="tt" class="easyui-tabs" fit="true" border="false"></div>

	</div>
</body>
</html>
