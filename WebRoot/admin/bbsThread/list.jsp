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
        <h3>热帖管理</h3>
        <ul class="content-box-tabs">
          <li><a href="#list" class="list default-tab">热帖</a></li>
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
                <th>ID</th>
                <th>标题</th>
                <th>链接</th>
                <th>优先级</th>
                <th width="80px">操作</th>
              </tr>
            </thead>
            <tfoot>
              <tr>
                <td colspan="3">
                  <div class="bulk-actions align-left">
                    <select name="dropdown">
                      <option value="option2">编辑</option>
                    </select>
                    <a class="button" href="#" id="bbsthread-set-apply">应用</a> 
                   <span class="red">注意:当标题中包含逗号','或竖线'|'时不可以编辑</span>
                  </div>
                  <!-- End .pagination -->
                  <div class="clear"></div>
                </td>
              </tr>
            </tfoot>
            <tbody>
                  <s:iterator value="bbsThreads" status="st" id="bbsThread">
                    <tr>
                      <td>
                        <input type="checkbox" name="check"/><input name="tid" type="hidden" value="<s:property escape="false"  value="#bbsThread.id"/>"/>
                      </td>
                      <td><s:property escape="false"  value="#bbsThread.id"/></td>
                      <td><input name="title" value='<s:property escape="false"  value="#bbsThread.title"/>'/></td>
                      <td><input name="link" value='<s:property escape="false"  value="#bbsThread.link"/>'/></td>
                      <td><input name="order" value='<s:property escape="false"  value="#bbsThread.order"/>'/></td>
                      <td>
                        <!-- Icons -->
                        <a href="#" title="删除" class="bbs-delete-link"><img src="resources/images/icons/cross.png" alt="删除" /></a> </td>
                    </tr>
                  </s:iterator>
            </tbody>
          </table>
        </div>
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
