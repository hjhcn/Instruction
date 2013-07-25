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
        <h3>分类管理</h3>
        <ul class="content-box-tabs">
          <li><a href="#list" class="list default-tab">分类</a></li>
          <li><a href="#detail" class="detail">详情</a></li>
        </ul>
        <div class="clear"></div>
      </div>
    <!-- End .content-box-header -->
      <div class="content-box-content">
        <div class="tab-content default-tab" id="list">
          <table>
            <thead>
              <tr>
                <th>
                  <input class="check-all" type="checkbox" />
                </th>
                <th>一级分类</th>
                <th>二级分类</th>
                <th>三级分类</th>
                <th>说明书总数</th>
                <th width="80px">操作</th>
              </tr>
            </thead>
            <tfoot>
              <tr>
                <td colspan="3">
                  <div class="bulk-actions align-left">
                    <select name="dropdown">
                      <option value="option1">选择操作类型</option>
                      <option value="option2">编辑</option>
                      <option value="option3">删除</option>
                    </select>
                    <a class="button" href="#">应用</a> </div>
                    
                  <!-- End .pagination -->
                  <div class="clear"></div>
                </td>
              </tr>
            </tfoot>
            <tbody>
                  <s:iterator value="categorys" status="st" id="cat">
                    <tr>
                      <td>
                        <input type="checkbox" /><input name="cid" type="hidden" value="<s:property escape="false"  value="#cat.id"/>"/>
                      </td>
                      <td><s:if test="#cat.level==0"><s:property escape="false"  value="#cat.name"/></s:if></td>
                      <td><s:if test="#cat.level==1"><s:property escape="false"  value="#cat.name"/></s:if></td>
                      <td><s:if test="#cat.level==2"><s:property escape="false"  value="#cat.name"/></s:if></td>
                      <td><s:property escape="false"  value="#cat.count"/></td>
                      <td>
                        <!-- Icons -->
                        <a href="#" title="编辑" class="cat-edit-link"><img src="resources/images/icons/pencil.png" alt="编辑" /></a><!-- <a href="#" title="删除" class="cat-delete-link"><img src="resources/images/icons/cross.png" alt="删除" /></a>--> </td>
                    </tr>
                  </s:iterator>
            </tbody>
          </table>
        </div>
        <!-- End #tab1 -->
        <div class="tab-content" id="detail">
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
              <select id="parentId" name="parentId" class="small-input" disabled>
                  <option value="0">请选择产品类型
                  <s:iterator value="categorys" status="st" id="cat">
                      <option value="<s:property escape="false"  value="#cat.id"/>">
                        <s:if test="#cat.level>0">&nbsp;&nbsp;&nbsp;&nbsp;</s:if><s:property escape="false"  value="#cat.name"/>
                      </option>
                  </s:iterator>
              </select>
              <span class="input-notification" id="parentId-notification"></span>
            </p>
            <p>
              <input type="button" class="button" id="cat-edit-sub" value="提交" /><input id="act-id" type="hidden"/>
            </p>
            <p>
              <div id="brands" class="brands">
              </div>
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
