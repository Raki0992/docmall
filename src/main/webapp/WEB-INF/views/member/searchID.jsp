<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.101.0">
    <title>Pricing example · Bootstrap v4.6</title>

    <!-- Bootstrap core CSS -->
<%@include file="/WEB-INF/views/comm/plugin2.jsp" %>


    <!-- Favicons --> 



    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      a {
        margin-left: 15px;
        margin-right:  15px;
      }

    </style>

    <script>
      let msg = '${msg}';
      if(msg != "") {
        alert(msg);
      }
    </script>
    

  </head>
  <body>
    
<%@include file="/WEB-INF/views/comm/header.jsp" %>

<div class="container">
  <div class="text-center">
    <div class="box box-primary">
      <div class="box-header with-border">
      <h3 class="box-title">회원 정보 찾기</h3>
      </div>
      
      
 
<div class="container">
    <div class="mb-3 text-center row">
      <div class="col-sm-6">
        <h3>아이디 찾기</h3>
    <form id="searchForm" action="searchID" method="post">
          <div class="form-group row">
            <label for="mbsp_name" class="col-4">이름</label>
              <div class="col-8">
            <input type="text" class="form-control" name ="mbsp_name" id="mbsp_name" placeholder="이름 입력...">
              </div>
          </div>
          <div class="form-group row">
            <label for="mbsp_email" class="col-4">이메일</label>
          <div class="col-8">
        <input type="text" class="form-control" name ="mbsp_email" id="mbsp_email" placeholder="이메일 입력...">
          </div>
      </div>
      <div class="form-group row">
        <div class="col-sm-12 text-center">
          <button type="submit" class="btn btn-primary" id="btnlogin">아이디 찾기</button>
        </div>
      </div>
    </form>
  </div>
  <div class="col-sm-6">
    <h3>임시비밀번호 발급</h3>
    <form id="searchForm" action="/member/searchPW" method="post">
      <div class="form-group row">
        <label for="mbsp_id" class="col-4">아이디</label>
          <div class="col-8">
        <input type="text" class="form-control" name ="mbsp_id" id="mbsp_id" placeholder="아이디 입력...">
          </div>
      </div>
      <div class="form-group row">
        <label for="mbsp_email" class="col-4">이메일</label>
      <div class="col-8">
    <input type="text" class="form-control" name ="mbsp_email" id="mbsp_email" placeholder="이메일 입력...">
      </div>
  </div>
  <div class="form-group row">
    <div class="col-sm-12 text-center">
      <button type="submit" class="btn btn-primary" id="btnlogin">임시 비밀번호 발급</button>
    </div>
  </div>
</form>






    </div>
</div>
  <%@include file="/WEB-INF/views/comm/footer.jsp" %>



<%@include file="/WEB-INF/views/comm/plugin.jsp" %>
    
    <script>

      $(Document).ready(function() {
        $("#btnIDPW").on("click", function() {
          location.href = "/member/foget"
        });
      });


    </script>
  </body>
</html>
    