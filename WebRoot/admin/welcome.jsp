<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="common/head.jsp" />

<body>
	<div id="main-content">
		<!-- Main Content Section with everything -->
		<noscript>
			<!-- Show a notification if the user has disabled javascript -->
			<div class="notification error png_bg">
				<div>对不起，您的浏览器没有开启或者不支持 Javascript. 请开启javascript或者更新您的浏览器.</div>
		</noscript>
		<!-- Page Head -->
		<!-- 
    <ul class="shortcut-buttons-set">
      <li><a class="shortcut-button" href="ins_uploadPage"><span> <img src="resources/images/icons/pencil_48.png" alt="icon" /><br />
        说明书上传 </span></a></li>
      <li><a class="shortcut-button" href="ins_list"><span> <img src="resources/images/icons/paper_content_pencil_48.png" alt="icon" /><br />
        说明书管理</span></a></li>
      <li><a class="shortcut-button" href="#"><span> <img src="resources/images/icons/clock_48.png" alt="icon" /><br />
        后台事件 </span></a></li>
      <li><a class="shortcut-button" href="#messages" rel="modal"><span> <img src="resources/images/icons/comment_48.png" alt="icon" /><br />
        管理员消息 </span></a></li>
    </ul> -->
		<!-- End .shortcut-buttons-set -->
		<div class="clear"></div>
		<!-- End .clear -->

		<!-- End .content-box -->
		<div class="content-box column-left">
			<div class="content-box-header">
				<h3>管理员信息</h3>
			</div>
			<!-- End .content-box-header -->
			<div class="content-box-content">
				<div class="tab-content default-tab">
					<h4>
						<s:property value="#session.admin.username" />
					</h4>
					<p>
						角色: 超级管理员<br />未读短消息: <a href="#">0</a><br />本次登录时间:
						<s:property value="#session.admin.formatLoginTime" />
						<br />本次登录IP:
						<s:property value="#session.admin.loginIp" />
						<br />上次登录时间:
						<s:property value="#session.admin.formatLastTime" />
						<br />上次登录IP:
						<s:property value="#session.admin.loginIp" />
					</p>
				</div>
				<!-- End #tab3 -->
			</div>
			<!-- End .content-box-content -->
		</div>
		<!-- End .content-box -->
		<!--
		<div class="content-box column-right">
			<div class="content-box-header">
				<h3>统计信息</h3>
			</div>
			<div class="content-box-content">
				<div class="tab-content default-tab">
           <table>
            <thead>
              <tr>
                <th>统计</th>
                <th>说明书</th>
                <th>管理员上传</th>
                <th>用户上传</th>
                <th>访问</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>总数</td>
                <td>15657</td>
                <td>7117</td>
                <td>8540</td>
                <td>140072</td>
              </tr>
              <tr>
                <td>今日</td>
                <td>17</td>
                <td>7</td>
                <td>10</td>
                <td>1089</td>
              </tr>
              <tr>
                <td>本周</td>
                <td>165</td>
                <td>71</td>
                <td>94</td>
                <td>7107</td>
              </tr>
              <tr>
                <td>本月</td>
                <td>2564</td>
                <td>1411</td>
                <td>1153</td>
                <td>21367</td>
              </tr>
            </tbody>
          </table>
				</div>
				<!-- End #tab3 -->
	</div>
	<!-- End .content-box-content -->
	</div>
	-->
	<!-- End .content-box -->
	<div class="clear"></div>

	<s:include value="common/foot.jsp" />
	</div>
</body>
</html>