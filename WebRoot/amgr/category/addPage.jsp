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
        <h3>新增分类</h3>
        <ul class="content-box-tabs">
          <li><a href="#upload" class="list default-tab">分类</a></li>
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
              <label>分类名</label>
              <input class="text-input small-input" type="text" name="name" id="name"/>
              <span class="input-notification" id="name-notification"></span>
            </p>
            <p>
              <label>父分类</label>
              <select id="parentId" name="parentId" class="small-input">
                  <option value="0">顶级分类
                  <s:iterator value="categorys" status="st" id="cat">
                      <option value="<s:property escape="false"  value="#cat.id"/>">
                        <s:if test="#cat.level>0">&nbsp;&nbsp;&nbsp;&nbsp;</s:if><s:property escape="false"  value="#cat.name"/>
                      </option>
                  </s:iterator>
              </select>
              <span class="input-notification" id="parentId-notification"></span>
            </p>
            <p>
              <input type="button" class="button" id="cat-add-sub" value="提交" />
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
