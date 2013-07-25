<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="common/head.jsp"/>
<body id="login">
<div id="login-wrapper" class="png_bg">
  <div id="login-top">
    <h1>苏州移动说明书后台管理系统</h1>
    <!-- Logo (221px width) -->
    <div class="logo" title="苏州移动说明书后台管理系统">苏州移动说明书后台管理系统</div>
  </div>
  <!-- End #logn-top -->
  <div id="login-content">
    <form action="login" method="post" target="_top">
      <div class="notification information png_bg">
        <div>
          <s:if test="feedback==0||feedback==-501">
           登录系统后，才能进行管理
          </s:if>
          <s:elseif test="feedback==-502">
           账户名不存在 
          </s:elseif>
          <s:elseif test="feedback==-503">
           密码错误 
          </s:elseif>
          <s:else>
           登录系统后，才能进行管理
          </s:else>
        </div>
      </div>
      <p>
        <label>账&nbsp;&nbsp;&nbsp;&nbsp;号：</label>
        <input class="text-input" type="text" name="username"/>
      </p>
      <div class="clear"></div>
      <p>
        <label>密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
        <input class="text-input" type="password" name="password"/>
      </p>
      <div class="clear"></div>
     
      <div class="clear"></div>
      <p>
        <input class="button" type="submit" value="登录" />
      </p>
    </form>
  </div>
  <!-- End #login-content -->
</div>
<!-- End #login-wrapper -->
</body>
</html>
