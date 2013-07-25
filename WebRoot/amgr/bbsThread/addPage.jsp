<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="../common/head.jsp"/>

<body>
  <div id="main-content">
    <div id="loading"></div>
    <!-- Main Content Section with everything -->
    <noscript>
    <!-- Show a notification if the user has disabled javascript -->
      <div class="notification error png_bg">
      <div>对不起，您的浏览器没有开启或者不支持 Javascript. 请开启javascript或者更新您的浏览器.</div>
    </noscript>
   
    <!-- End .clear -->
    <div class="content-box">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <h3>新增热帖</h3>
        <ul class="content-box-tabs">
          <li><a href="#upload" class="list default-tab">新增</a></li>
        </ul>
        <div class="clear"></div>
      </div>
    <!-- End .content-box-header -->
      <div class="content-box-content">
        <div class="tab-content default-tab" id="upload">
          <form action="#" method="post">
            <fieldset>
            <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
            <p>
              <label>标题</label>
              <input class="text-input small-input" type="text" name="title" id="title"/>
              <span class="input-notification" id="title-notification"></span>
            </p>
            <p>
              <label>链接</label>
              <input class="text-input small-input" type="text" name="link" id="link"/>
              <span class="input-notification" id="link-notification"></span>
            </p>
            <p>
              <label>优先级</label>
              <input class="text-input small-input" type="text" name="order" id="order"/>
              <span class="input-notification" id="order-notification"></span>
            </p>
            <p>
              <input type="button" class="button" id="bbsThread-add-sub" value="提交" />
            </p>
            </fieldset>
            <div class="clear"></div>
            <!-- End .clear -->
          </form>
        </div>
        <!-- End #tab2 -->
      </div>
      <!-- End .content-box-content -->
    </div>
    <!-- End .content-box -->
    
    <s:include value="../common/foot.jsp"/>

  </div>
  <!-- End #main-content -->
</body>
</html>
