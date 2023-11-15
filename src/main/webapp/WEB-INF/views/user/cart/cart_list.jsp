<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!-- 날짜, 금액, 시간 등... -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    </style>

<script>
  let msg = '${msg}';
  if(msg == 'success') {
    alert("회원정보가 수정됨");
  }
</script>
    

  </head>
  <body>
    
<%@include file="/WEB-INF/views/comm/header.jsp" %>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
<p>장바구니</p>
</div>

<div class="container">

  <table class="table table-striped">
    <thead>
      <tr>
        <th scope="col"><input type="checkbox"></th>
        <th scope="col">상품</th>
        <th scope="col">상품명</th>
        <th scope="col">판매가</th>
        <th scope="col">수량</th>
        <th scope="col">할인</th>
        <th scope="col">남은수량</th>
        <th scope="col">합계</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${cart_list }" var="CartDTOList" >
      <tr>
        <td><input type="checkbox" name="cart_code" value="${CartDTOList.cart_code }"></td>
        <td><img width=70% height="70" src="/user/cart/imageDisplay?dateFolderName=${CartDTOList.pro_up_folder }&fileName=s_${CartDTOList.pro_img }"></td>
        <td>${CartDTOList.pro_name }</td>
        <td><span id="unitPrice">${CartDTOList.pro_price }</span></td>
        <td><input type="number" name="cart_amount" value="${CartDTOList.cart_amount }" style="width: 35px;">
          <button type="button" name="btn_cart_amount_change" class="btn btn-warning">변경</button> </td>
        <td><span id="unitDiscount">${CartDTOList.pro_discount * 1/100 }</span></td>
        <td>${CartDTOList.cart_amount } </td>
        <td><span class="unitTotalprice" id="unitTotalprice">${(CartDTOList.pro_price - (CartDTOList.pro_price * (CartDTOList.pro_discount * 1/100))) * CartDTOList.cart_amount }</span></td>
        <td>
          <button type="button" name="btn_ajax_cart_del" class="btn btn-danger">삭제(ajax)</button>
          <button type="button" name="btn_nonajax_cart_del" class="btn btn-danger">삭제(non-ajax)</button>
        </td>
      </tr>
    </c:forEach>
    </tbody>
    <tfoot>
      <tr>
        <td colspan="12"><button type="button" class="btn btn-danger">선택삭제</button></td>
      </tr>
        <td colspan="12" style="text-align: right;">
        최종결제금액 : <span id="cart_total_price">${cart_total_price}</span>
        </td>

        <tr>
          <td colspan="12" style="text-align: center;">
            <button type="button" id="btn_order" class="btn btn-success">주문하기</button>
          </td>
        </tr>
    </tfoot>
  </table>

  <%@include file="/WEB-INF/views/comm/footer.jsp" %>
</div>

<%@include file="/WEB-INF/views/comm/plugin.jsp" %>
<!-- 카테고리 메뉴 자바스크립트 작업 -->
<script src="/js/category_menu.js"></script>

<script>
$(document).ready(function() {

  // 장바구니 목록에서 변경 클릭시
  $("button[name='btn_cart_amount_change']").on("click", function() {

    let cur_btn_change = $(this);

    let cart_amount = $(this).parent().find("input[name='cart_amount']").val();
    console.log("수량", cart_amount);

    let cart_code = $(this).parent().parent().find("input[name='cart_code']").val();
    console.log("장바구니코드", cart_code);

    $.ajax({
      url: '/user/cart/cart_amount_change',
      type: 'post',
      data: {cart_code : cart_code, cart_amount : cart_amount},
      dataType: 'text',
      success: function(result) {
        if(result == 'success') {
          alert("수량이 변경 되었습니다.");

          // 합계금액 계산작업
          // 금액 = (판매가 - (판매가 * 할인율)) * 수량
          let unitPrice = cur_btn_change.parent().parent().find("span#unitPrice").text();
          let unitDiscount = cur_btn_change.parent().parent().find("span#unitDiscount").text();

          // 장바구니 코드별 단위금액
          let unitTotalprice = cur_btn_change.parent().parent().find("span#unitTotalprice");
          unitTotalprice.text((unitPrice - (unitPrice * unitDiscount)) * cart_amount);

          // 전체주문금액
          fn_cart_sum_price();
        
        }
      }
    })
  });

  // 장바구니 삭제
  $("button[name='btn_ajax_cart_del']").on("click", function() {

    if(!confirm("장바구니 상품을 삭제하시겠습니까?")) return;

    let cur_btn_delete = $(this); // 선택된 버튼태그의 위치를 참조
    let cart_code = $(this).parent().parent().find("input[name='cart_code']").val();
    // console.log("장바구니코드", cart_code);

    $.ajax({
      url: '/user/cart/cart_list_del',
      type: 'post',
      data: {cart_code : cart_code},
      dataType: 'text',
      success: function(result) {
        if(result == "success") {
          alert("장바구니 상품이 삭제되었습니다.");

          cur_btn_delete.parent().parent().remove();  // 삭제된 장바구니 데이터행 제거

          // 전체주문금액
          fn_cart_sum_price();
        }
      }
    });
  });

  // 장바구니 삭제(non-ajax)
  $("button[name='btn_nonajax_cart_del']").on("click", function() {

    if(!confirm("장바구니 상품을 삭제하시겠습니까?")) return;

    let cart_code = $(this).parent().parent().find("input[name='cart_code']").val();
    location.href = "/user/cart/cart_list_del?cart_code=" + cart_code;
  });

  // 주문정보페이지
  $("button[id='btn_order']").on("click", function() {
    location.href ="/user/order/order_info";
  });

}); //-----------

// 전체주문금액
function fn_cart_sum_price() {
  let sumPrice = 0;
            $(".unitTotalprice").each(function() {
              sumPrice += Number($(this).text());
            });
            $("#cart_total_price").text(sumPrice);
}


</script>
  </body>
</html>
    