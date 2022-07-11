<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <!--  JQUERY -->
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>

    <!--  BOOTSTRAP -->
    <link rel="stylesheet" type="text/css" href="jquery/bootstrap_3.3.0/css/bootstrap.min.css">
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>

    <!--  PAGINATION plugin -->
    <link rel="stylesheet" type="text/css" href="jquery/bs_pagination-master/css/jquery.bs_pagination.min.css">
    <script type="text/javascript" src="jquery/bs_pagination-master/js/jquery.bs_pagination.min.js" ></script>
    <script type="text/javascript" src="jquery/bs_pagination-master/localization/en.js"></script>
    <title>演示bs_pagination插件的使用</title>

    <script type="text/javascript">
        var rsc_bs_pag = {
            go_to_page_title: '跳转到',
            rows_per_page_title: '每页显示',
            current_page_label: '页',
            current_page_abbr_label: 'p.',
            total_pages_label: 'of',
            total_pages_abbr_label: '/',
            total_rows_label: 'of',
            rows_info_records: '记录',
            go_top_text: '首页',
            go_prev_text: '上一页',
            go_next_text: '下一页',
            go_last_text: '尾页'
        };
        $(function() {
            $("#demo_pag1").bs_pagination({
                currentPage:1,//当前页号,相当于pageNo

                rowsPerPage:10,//每页显示条数,相当于pageSize
                totalRows:1000,//总条数
                totalPages: 100,  //总页数,必填参数.

                visiblePageLinks:5,//最多可以显示的卡片数

                showGoToPage:true,//是否显示"跳转到"部分,默认true--显示
                showRowsPerPage:true,//是否显示"每页显示条数"部分。默认true--显示
                showRowsInfo:true,//是否显示记录的信息，默认true--显示

                //用户每次切换页号，都自动触发本函数;
                //每次返回切换页号之后的pageNo和pageSize
                onChangePage: function(event,pageObj) { // returns page_num and rows_per_page after a link has clicked
                    //js代码
                    alert(pageObj.currentPage);
                    alert(pageObj.rowsPerPage);
                }
            });

        });
    </script>
</head>
<body>
<!--  Just create a div and give it an ID -->

<div id="demo_pag1"></div>
</body>
</html>
