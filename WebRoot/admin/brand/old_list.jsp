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
        <h3>品牌管理</h3>
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
                <th>品牌常用名称</th>
                <th>品牌英文名</th>
                <th>品牌中文名</th>
                <th>说明书总数</th>
                <th width="80px">操作</th>
              </tr>
            </thead>
            <tfoot>
              <tr>
                <td colspan="6">
                  <div class="bulk-actions align-left">
                    <select name="dropdown">
                      <option value="option1">选择操作类型</option>
                      <option value="option2">编辑</option>
                      <option value="option3">删除</option>
                    </select>
                    <a class="button" href="#">应用</a> </div>
                  
                  <div class="pagination">
                     <s:bean name="org.apache.struts2.util.Counter" id="counter">
                         <s:param name="first" value="brands.pageindex.startindex" />
                         <s:param name="last" value="brands.pageindex.endindex" />
                         <s:if test="brands.pageindex.startindex>1"><a href="brand_list?cid=<s:property value="cid"/>&bid=<s:property value="bid"/>&search=<s:property value="search"/>&pageNum=1&pageSize=<s:property value="pageSize"/>" class="number">1</a><s:if test="brands.pageindex.startindex>2"><span class="omission">...</span></s:if></s:if>
                         <s:iterator>
                           <a href="brand_list?cid=<s:property value="cid"/>&bid=<s:property value="bid"/>&search=<s:property value="search"/>&pageNum=<s:property value="current-1"/>&pageSize=<s:property value="pageSize"/>" class="number
                            <s:if test="brands.currentpage==current-1"> current</s:if>
                            "><s:property value="current-1"/></a>
                         </s:iterator>
                         <s:if test="brands.pageindex.endindex<brands.totalpage"><s:if test="brands.totalpage-brands.pageindex.endindex>1"><span class="omission">...</span></s:if><a href="brand_list?cid=<s:property value="cid"/>&bid=<s:property value="bid"/>&search=<s:property value="search"/>&pageNum=<s:property value="brands.totalpage"/>&pageSize=<s:property value="pageSize"/>" class="number"><s:property value="brands.totalpage"/></a></s:if>
                     </s:bean>
                  </div>  
                  <!-- End .pagination -->
                  <div class="clear"></div>
                </td>
              </tr>
            </tfoot>
            <tbody>
                  <s:iterator value="brands.records" status="st" id="brand">
                    <tr>
                      <td>
                        <input type="checkbox" /><input name="bid" type="hidden" value="<s:property escape="false"  value="#brand.id"/>"/>
                      </td>
                      <td><s:property escape="false"  value="#brand.name"/></td>
                      <td><s:property escape="false"  value="#brand.nameEn"/></td>
                      <td><s:property escape="false"  value="#brand.nameZh"/></td>
                      <td><s:property escape="false"  value="#brand.count"/></td>
                      <td>
                        <!-- Icons -->
                        <a href="#" title="编辑" class="brand-edit-link"><img src="resources/images/icons/pencil.png" alt="编辑" /></a> <a href="#" title="删除" class="brand-delete-link"><img src="resources/images/icons/cross.png" alt="删除" /></a>  </td>
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
              <label>品牌常用名</label>
              <input class="text-input small-input" type="text" name="name" id="name"/>
              <span class="input-notification" id="name-notification"></span>
            </p>
            <p>
              <label>品牌英文名</label>
              <input class="text-input small-input" type="text" name="nameEn" id="nameEn"/>
              <span class="input-notification" id="nameEn-notification"></span>
            </p>
            <p>
              <label>品牌中文名</label>
              <input class="text-input small-input" type="text" name="nameZh" id="nameZh"/>
              <span class="input-notification" id="nameZh-notification"></span>
            </p>
            <p>
              <input type="button" class="button" id="brand-edit-sub" value="提交" /><input id="act-id" type="hidden"/>
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
