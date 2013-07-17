<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="../common/head.jsp"/>
<script type="text/javascript"  src="ckeditor/ckeditor.js"></script>
<script type="text/javascript">
jQuery(function($){
	editor=CKEDITOR.replace("content");
	listUploadPic();
});
</script>
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
        <h3>新闻发布</h3>
        <ul class="content-box-tabs">
          <li><a href="#tab1" class="default-tab">发布</a></li>
        </ul>
        <div class="clear"></div>
      </div>
    <!-- End .content-box-header -->
      <div class="content-box-content">
        <div class="tab-content default-tab" id="tab1">
          <form action="#" method="post">
            <fieldset>
            <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
            <p>
              <label>标题</label>
              <input class="text-input medium-input" type="text" name="title" id="title"/>
              <span class="input-notification" id="title-notification"></span>
              <!-- Classes for input-notification: success, error, information, attention -->
            </p>
            <p>
              <label>封面</label>
              <input id="ufid" type="hidden"/>
              <input id="filePageNum" type="hidden" value="1"/>
              <div id="cover">
                  <ul id="selectable">
                  </ul>
                  <div class="clear"></div>
                  <div><a href="javascript:void(0)" id="next-page" class="hide">加载下一页</a></div>
              </div>
            </p>
            <p>
              <label>简介</label>
			  <textarea cols="80" id="description" name="description" rows="10"  onkeyup="this.value = this.value.substring(0, 200)"></textarea>
              <span class="input-notification" id="description-notification"></span><br>
            </p>
            <p>
              <label>内容</label>
			  <textarea cols="80" id="content" name="content" rows="10"></textarea>
              <span class="input-notification" id="content-notification"></span><br>
            </p>
            <p>
              <input type="checkbox" name="status"/>是否直接审核通过
            </p>
            <p>
              <input type="button" class="button" id="news-add-sub" value="提交" />
            </p>
            </fieldset>
            <div class="clear"></div>
            <!-- End .clear -->
          </form>
        </div>
        <!-- End #tab1 -->
      </div>
      <!-- End .content-box-content -->
    </div>
    <!-- End .content-box -->
    
    <s:include value="../common/foot.jsp"/>

  </div>
  <!-- End #main-content -->
</body>
</html>
