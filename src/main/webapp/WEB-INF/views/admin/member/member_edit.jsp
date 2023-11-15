<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 2 | Starter</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  
  <%@include file="/WEB-INF/views/admin/include/plugin1.jsp" %>
  
</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <!-- Main Header -->
  <%@include file="/WEB-INF/views/admin/include/header.jsp" %>
  
  <!-- Left side column. contains the logo and sidebar -->
  <%@include file="/WEB-INF/views/admin/include/nav.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Page Header
        <small>Optional description</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
        <li class="active">Here</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content container-fluid">

      <div class="text-center">
        <div class="box box-primary">
          <div class="box-header with-border">
          <h3 class="box-title">회원수정</h3>
          </div>
          
          

      <div class="row">
					<div class="col-md-12">
						<div class="box box-primary">
							<div class="box-header with-border">
								<!--  mt-5 : margin top 위쪽 여백 -->
								<h3 class="box-title mt-5">Member Edit</h3>
                <!-- 페이지 Criteria 사용-->
                <form id="actionForm" action="" method="get">
                 	<input type="hidden" name="pageNum" id="pageNum" value="${cri.pageNum}" />
		            <input type="hidden" name="amount" id="amount" value="${cri.amount}" />
		            <input type="hidden" name="type" id="type" value="${cri.type}" />
		            <input type="hidden" name="keyword" id="keyword" value="${cri.keyword}" />
                </form>
							</div>

							<form role="form" method="post" action="/admin/member/member_edit">
								<div class="box-body">
									<div class="form-group row">
                    <div class="col-md-3">
                    	  <input type="hidden" name="pageNum" id="pageNum" value="${cri.pageNum}" />
		                  <input type="hidden" name="amount" id="amount" value="${cri.amount}" />
		                  <input type="hidden" name="type" id="type" value="${cri.type}" />
		                  <input type="hidden" name="keyword" id="keyword" value="${cri.keyword}" />
                    </div>
									</div>

                  <div class="form-group row">
										<label for="title" class="col-md-2 col-form-label">아이디</label>
										<input type="hidden" name="mbsp_id" value="${MemberVO.mbsp_id }">
                    <div class="col-md-4">
                      <input type="text" class="form-control" id="mbsp_id" name="mbsp_id"	value="${MemberVO.mbsp_id }">
                    </div>

                    <label for="title" class="col-md-2 col-form-label">이름</label> 
                    <div class="col-md-4">
                      <input type="text" class="form-control" id="mbsp_name" name="mbsp_name"	value="${MemberVO.mbsp_name }">
                    </div>
                  </div>

                  <div class="form-group row">
										<label for="title" class="col-md-2 col-form-label">이메일</label> 
                    <div class="col-md-4">
                      <input type="text" class="form-control" id="mbsp_email" name="mbsp_email"	value="${MemberVO.mbsp_email }">
                    </div>

                    <label for="title" class="col-md-2 col-form-label">주소</label> 
                    <div class="col-md-4">
                      <input type="text" class="form-control" id="mbsp_addr" name="mbsp_addr"	value="${MemberVO.mbsp_addr }">
                    </div>

                    <label for="title" class="col-md-2 col-form-label">전화번호</label> 
                    <div class="col-md-4">
                      <input type="text" class="form-control" id="mbsp_phone" name="mbsp_phone"	value="${MemberVO.mbsp_phone }">
                    </div>
                  </div>



                  <div class="form-group row">
										<label for="title" class="col-md-2 col-form-label">마지막 접속 일자</label> 
                    <div class="col-md-4">
                      <fmt:formatDate value="${memberVO.mbsp_lastlogin }" pattern="yyyy-MM-dd" />
                    </div>
                  </div>
                  
					</div>
				<div class="box-footer">
                  <div class="form-group">
                    <ul class="uploadedlist"></ul>
                  </div>
                  <div class="text-center">
                    <button type="submit" class="btn btn-primary">회원수정</button>
                    <button type="reset" class="btn btn-primary">취소</button>
                  </div>
				</div>
			</form>

				</div>
					</div>
						</div>
          
          
          </div>
      </div>

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Main Footer -->
  <%@include file="/WEB-INF/views/admin/include/footer.jsp" %>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Create the tabs -->
    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
      <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
      <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
    </ul>
    <!-- Tab panes -->
    <div class="tab-content">
      <!-- Home tab content -->
      <div class="tab-pane active" id="control-sidebar-home-tab">
        <h3 class="control-sidebar-heading">Recent Activity</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript:;">
              <i class="menu-icon fa fa-birthday-cake bg-red"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

                <p>Will be 23 on April 24th</p>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

        <h3 class="control-sidebar-heading">Tasks Progress</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript:;">
              <h4 class="control-sidebar-subheading">
                Custom Template Design
                <span class="pull-right-container">
                    <span class="label label-danger pull-right">70%</span>
                  </span>
              </h4>

              <div class="progress progress-xxs">
                <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

      </div>
      <!-- /.tab-pane -->
      <!-- Stats tab content -->
      <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
      <!-- /.tab-pane -->
      <!-- Settings tab content -->
      <div class="tab-pane" id="control-sidebar-settings-tab">
        <form method="post">
          <h3 class="control-sidebar-heading">General Settings</h3>

          <div class="form-group">
            <label class="control-sidebar-subheading">
              Report panel usage
              <input type="checkbox" class="pull-right" checked> 
            </label>

            <p>
              Some information about this general settings option
            </p>
          </div>
          <!-- /.form-group -->
        </form>
      </div>
      <!-- /.tab-pane -->
    </div>
  </aside>
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
  immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<%@include file="/WEB-INF/views/admin/include/plugin2.jsp" %>
<script src="/bower_components/ckeditor/ckeditor.js"></script>
<script>
  $(document).ready(function() {

  });
</script>
</body>
</html>