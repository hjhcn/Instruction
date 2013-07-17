<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="common/head.jsp"/>
   <div class="main">
     <div class="login-feedback"><s:property value="loginUser.feedback"/>
       <s:if test="loginUser.feedback<0">
           账户密码错，请重新输入！
       </s:if>
       <s:else>
           登录成功，正在跳转...
           <s:property value="loginUser.sync"/>
           <script>location.href='index';</script>
       </s:else>
     </div>
   </div>
   <s:include value="common/foot.jsp"/>
  </body>
</html>