<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 날짜, 금액, 시간 등... -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

    <table class="table table-sm" style="border: 1px solid black;">
        <caption style="display: table-caption;text-align: center;color: red;font-weight: bold;">[주문상세정보]</caption>
    <thead>
        
      <tr>
        <th scope="col">주문번호</th>
        <th scope="col">상품코드</th>
        <th scope="col">상품이미지</th>
        <th scope="col">상품명</th>
        <th scope="col">주문수량</th>
        <th scope="col">주문금액</th>
        <th scope="col">비고</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <c:forEach items="${orderProductList }" var="OrderDetailProductVO">
        <th scope="row">${OrderDetailProductVO.orderDetailVO.ord_code}</th>
        <td>${OrderDetailProductVO.orderDetailVO.pro_num}</td>
        <td><img src="/admin/order/imageDisplay?dateFolderName=${OrderDetailProductVO.productVO.pro_up_folder}&fileName=${OrderDetailProductVO.productVO.pro_img}"></td>
        <td>${OrderDetailProductVO.productVO.pro_name}</td>
        <td>${OrderDetailProductVO.orderDetailVO.dt_amount}</td>
        <td>${OrderDetailProductVO.productVO.pro_price * OrderDetailProductVO.orderDetailVO.dt_amount}</td>
        <td><button type="button" name="btn_order_delete" class="btn btn-danger" data-ord_code="${OrderDetailProductVO.orderDetailVO.ord_code}" data-pro_num="${OrderDetailProductVO.orderDetailVO.pro_num}">Delete</button></td>
      </tr>
        </c:forEach>
    </tbody>
  </table>

</html>