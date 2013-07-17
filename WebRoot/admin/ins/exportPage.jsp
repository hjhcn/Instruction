<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="../common/head.jsp"/>
<script type="text/javascript">
  jQuery(function($){
	 $('#startTime').datepicker({dateFormat:"mm/dd/yy",changeMonth: true,changeYear: true});
	 $('#endTime').datepicker({dateFormat:"mm/dd/yy",changeMonth: true,changeYear: true});
  });
</script>  

<body>
<div id="main-content">
<!-- Main Content Section with everything -->
    <noscript>
    <!-- Show a notification if the user has disabled javascript -->
      <div class="notification error png_bg">
      <div>对不起，您的浏览器没有开启或者不支持 Javascript. 请开启javascript或者更新您的浏览器.</div>
    </noscript>
   
    <div class="content-box">
      <div class="content-box-header">
        <h3>导出数据</h3>
        <div class="clear"></div>
      </div>
      <!-- End .content-box-header -->
      <div class="content-box-content">
         <div>
            <form action="ins-dataExport" name="export-filter" id="export-filter-form" method="get">
             起止日期: <input id="startTime" name="startTime" type="text" value="<s:bean name="instruction.util.Time" id="time"><s:property escape="false"  value="#time.timeFormat(startTime,'MM/dd/yyyy')"/></s:bean>"/> ~ <input name="endTime" id="endTime" type="text" value="<s:bean name="instruction.util.Time" id="time"><s:property escape="false"  value="#time.timeFormat(endTime,'MM/dd/yyyy')"/></s:bean>"/>
             <a class="button" href="#" id="export-filter">导出数据</a>
            </form>
         </div>
         <div class="clear"></div>
         
      </div>
      <!-- End .content-box-content -->
    </div>
    <!-- End .content-box -->
    
    <s:include value="../common/foot.jsp"/>
</div>
</body>
</html>