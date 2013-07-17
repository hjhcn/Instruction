<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>${param.title}说明书下载在线查看大全-苏州生活网说明书频道</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="${param.keywords}说明书下载,说明书在线查看,说明书有奖上传,3C资讯,说明书,说明书网" />
<meta name="description"
	content="${param.description}苏州生活网说明书频道,致力于打造最好最全的说明书电子文档下载、在线查阅、分享平台，并为用户提供最新最快的3C资讯内容服务。" />
<link href="/css/normal.css?0701" rel="stylesheet" />
<link href="/css/index.css?v=053101" rel="stylesheet" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<script src="/js/jquery.js"></script>
<script src="/js/head.js?20130701"></script>
<script type="text/javascript" src="/js/swfobject.js"></script>
<script src="/js/index.js?v=20130701"></script>
</head>
<body>
	<div class="header">


		<!--标志区域导航开始-->
		<div class="logo_nav">
			<div class="logo_nav_1" id="WT_top">
				<div class="logoarea">
					<a href="/"><img src="/css/images/head/logo.png?" width="130px" style="padding-left: 20px"></script>
					</a>
					<div class="weather clearfix"></div>
				</div>
				<!--主要导航区域-->
				<div class="m_nav">
					<div class="m_nav_top">
						<a href="http://health.139life.com/login_login.action" class="set_01" target="_blank"><b>医</b>
						</a>| <a href="http://health.139life.com/login_login.action" target="_blank">挂号</a> <a
							href="http://health.139life.com/hmsg_RecommendDoctorList.action?d=1350021384695"
							target="_blank">医导</a> <a href="http://food.139life.com" target="_blank" class="set_02"><b>食</b>
						</a>| <a href="http://food.139life.com/index.php?dir=eat&mod=download" target="_blank">优惠</a> <a
							href="http://food.139life.com/index.php?dir=eat&mod=search" target="_blank">商户</a> <a
							href="http://house.139life.com/default.aspx" target="_blank" class="set_03"><b> 住</b>
						</a>| <a href="http://house.139life.com/house/projectList.aspx?c=1&district=0&street=0&pt="
							target="_blank">楼盘</a> <a href="http://house.139life.com/Cms/CmsItemsPage.aspx?category=3"
							target="_blank">资讯</a> <a href="http://car.139life.com" target="_blank" class="set_04"><b>行</b>
						</a>| <a href="http://car.139life.com/index.php?dir=car&mod=searchoils" target="_blank">油价</a> <a
							href="http://car.139life.com/index.php?dir=car&mod=pinpai&do=py" target="_blank">车型</a> <a
							href="http://read.139life.com/magazine/" target="_blank" class="set_05"><b> 文</b>
						</a>| <a oldhref="http://read.139life.com/magazine/"
							href="http://read.139life.com/magazine/tree.php?act=sort&p_id=6" target="_blank">人物</a> <a
							oldhref="http://edu.139life.com/"
							href="http://read.139life.com/magazine/tree.php?act=sort&p_id=5" target="_blank">财经</a> <a
							href="http://mall.12580life.com/" target="_blank" class="set_06"><b> 购</b>
						</a>| <a href="http://mall.12580life.com/pc_l7r81258.htm" target="_blank">数码</a> <a
							href="http://mall.12580life.com/pc_ztbz3562.htm" target="_blank">居家</a> <a
							href="http://fun.139life.com/" target="_blank" class="set_07"><b> 闲</b>
						</a>| <a href="http://photo.139life.com/" target="_blank">摄影</a> <a
							href="http://movie.139life.com/index.php?dir=activity&mod=movie" target="_blank">电影</a> <a
							href="http://www.139life.com/index.php?dir=activity&mod=zsyx" target="_blank" class="set_08">手机版</a>
					</div>
					<!--移动专区导航-->
					<div class="m_nav_main clearfix">
						<dl class="nv_0 nv_1" onmouseover="this.className='nv_0 nv_1 nv_1_cursor'"
							onmouseout="this.className='nv_0 nv_1'">
							<dt>
								<a href="http://www.js.10086.cn/" target="_blank" class="blue">移动</a>
							</dt>
							<dd>
								<a href="http://www.js.10086.cn/tariffZone/index.html" target="_blank">品牌资费</a> <a
									href="http://www.js.10086.cn/group/index/index.html" target="_blank"> 动力100</a> <a
									href="http://service.js.10086.cn/index.jsp#home" target="_blank">网上营业厅</a><br /> <a
									href="http://www.js.10086.cn/mall/" target="_blank">手机卖场</a> <a
									href="http://211.138.195.171/gat/" target="_blank">关爱通</a> <a href="http://suzhou.wxcs.cn/"
									target="_blank">无线城市</a>
							</dd>
						</dl>

						<dl class="nv_0 nv_2" onmouseover="this.className='nv_0 nv_2 nv_1_cursor nv_1_cursor2'"
							onmouseout="this.className='nv_0 nv_2'">
							<dt>
								<span>专区</span>
							</dt>
							<dd>
								<a href="http://net.139life.com" target="_blank" style="font-weight: bold;">宽带</a> <a
									href="http://net.139life.com/index.php?dir=activity&mod=kdzq&do=wlanarea" target="_blank">wlan</a>
								<a href="http://net.139life.com/index.php?dir=activity&mod=kdzq&do=kdarea" target="_blank">查询</a>
								<a href="http://pc.139life.com/" target="_blank">客户端</a> <a href="http://home.139life.com"
									target="_blank" style="font-weight: bold;">家庭</a> <a
									href="http://home.139life.com/index.php?dir=activity&mod=jiating&do=zhanghu"
									target="_blank">办理</a> <a href="http://phone.139life.com/139files/index.php"
									target="_blank">G3</a> <a href="http://phone.139life.com/g3php/phoneInfo.php"
									target="_blank">机型</a>
							</dd>
						</dl>

						<dl class="nv_0 nv_3" onmouseover="this.className='nv_0 nv_3 nv_1_cursor nv_1_cursor3'"
							onmouseout="this.className='nv_0 nv_3'">
							<dt>
								<span>特色</span>
							</dt>
							<dd>
								<a href="http://xq.139life.com/" target="_blank">有奖答题</a> <a href="http://sms.139life.com/"
									target="_blank"> 说明书</a><br /> <a href="http://shequ.139life.com/" target="_blank">苏城社区</a>
								<a href="http://xinnongcun.139life.com/" target="_blank">和谐水乡</a>
							</dd>
						</dl>

						<dl class="nv_0 nv_4" onmouseover="this.className='nv_0 nv_4 nv_1_cursor nv_1_cursor4'"
							onmouseout="this.className='nv_0 nv_4'">
							<dt>
								<span>互动</span>
							</dt>
							<dd>
								<a href="http://bbs.139life.com/data/html/set.html" target="_blank">论坛</a> <a
									href="http://www.139life.com/index.php?dir=activity " target="_blank">活动专区</a> <a
									href="http://weibo.com/139life" target="_blank">微博</a> <a
									href="http://www.139life.com/html/help/sitemap.html" target="_blank">网站助手</a>
							</dd>
						</dl>
						<dl class="noe_cursor">
							<dt>
								<a href="http://v.139life.com" target="_blank">VIP视频</a>
							</dt>
						</dl>
					</div>
					<!--移动专区导航结束-->
				</div>
				<!--主要导航区域结束-->
			</div>
		</div>


		<div class="login-search">
			<div class="login">
				<div class="login-contain">
					<div class='userinfo <s:if test="userSession==null">hide</s:if>'>
						<div class='avatar'>
							<div class='icon'>
								<img width='60px' height='60px'
									src='http://www.139life.com/uc_server/avatar.php?uid=<s:property escape="false"  value="userSession.uid"/>&size=middle'>
							</div>
						</div>
						<div class='detail'>
							<p class='username'>
								<s:property escape="false" value="userSession.username" />
								, 欢迎您！ <a href="/notification_listAuth" title="通知中心" id="notification">通知</a>
							</p>
							<p class='lfb'>
								来福币：<span id='lfb'>-</span><img src='../css/images/lfb.png'>
							</p>
							<p class='btn'>
								<div id='logout' class='user-action'>安全退出</div>
								<div class="i-upload">
									<a href="/ins_uploadPageAuth" title="我来上传说明书">上传说明书</a>
								</div>
							</p>
							<p class="clear"></p>
						</div>
					</div>

					<div class="login-form <s:if test="userSession!=null">hide</s:if>">
						<div class="input">
							<form id="loginform" method="post"
								action="http://www.139life.com/loginapi.php?a=login&loginsubmit=yes&aappid=8">
								<input class="username gray" type="text" value="手机号码/邮箱" readonly="readonly" /> <input
									name="username" class="hide" type="text" /> <input class="password gray" type="text"
									value="10086密码" readonly="readonly" /> <input name="password" class="hide" id="password"
									type="password" value="" /> <input name="s" type="hidden" value="other" /> <input
									name="isforward" type="hidden" value="this" />
							</form>
							<div class="resinfo"></div>
						</div>
						<div class="button">
							<div class="login-btn"></div>
							<a href="http://service.js.10086.cn/obsh.html#MMFW_MMCZ" target="_blank"><div
									class="forget">忘记密码？</div> </a>
						</div>
					</div>
				</div>
			</div>
			<div class="search">
				<div class="input">
					<input name="keyword" id="keyword" value="<s:property value="search"/>" />
					<div class="btn" id="search-btn"></div>
				</div>
				<div class="hot">热门搜索: 三星9100 小米手机 海尔洗衣机 联想Y470</div>
				<%
					if (!(request.getRequestURI()).equals("/index.jsp")) {
				%><div class="line"></div>
				<%
					}
				%>
			</div>
		</div>

	</div>