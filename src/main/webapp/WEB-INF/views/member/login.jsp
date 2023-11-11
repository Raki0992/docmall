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
      <h3 class="box-title">로그인</h3>
      </div>
      
      
      <form role="form" id="loginForm" method="post" action="/member/login">

  <div class="box-body">

    <div class="form-group row">
      <label for="mbsp_id" class="col-2">아이디</label>
        <div class="col-10">
      <input type="text" class="form-control" name ="mbsp_id" id="mbsp_id" placeholder="아이디 입력...">
        </div>
    </div>

    <div class="form-group row">
      <label for="mbsp_id" class="col-2">비밀번호</label>
        <div class="col-10">
      <input type="password" class="form-control" name ="mbsp_password" id="mbsp_password" placeholder="비밀번호 입력...">
        </div>
    </div>

    <div>
      <input id="loginSave" type="checkbox">
      <label for="loginSave" class="text__label">아이디 저장</label>
    </div>

  </div>
      
      <div class="box-footer">
      <button type="submit" class="btn btn-primary" id="btnlogin">로그인</button>
      </div>
<br>
      <div>
        <nav class="my-2 my-md-0 mr-md-3">
          <a href="/member/forget" id="btnIDPW" class="btn btn-primary" >아이디 비밀번호 찾기</a> |
          <a href="/member/join" class="btn btn-primary">회원가입</a> 
        </nav>
      </div>
    

      </form>
      </div>
  </div>

  <%@include file="/WEB-INF/views/comm/footer.jsp" %>

</div>

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
    