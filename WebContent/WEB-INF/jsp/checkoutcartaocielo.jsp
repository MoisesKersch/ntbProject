<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="pt">
<body>

    <iframe id="vpos" name="vpos" width="800" height="550"></iframe>
    <form action="https://cieloecommerce.cielo.com.br/Transactional/Order/Index" method="post" target="_blank" tabindex="-1" id="cieloform">

        <input type="hidden" name="merchant_id" value="${form.merchantId}" tabindex="-1">
        <input type="hidden" name="order_number" value="${form.orderNumber}" tabindex="-1">
        <input type="hidden" name="soft_descriptor" value="${form.softDescriptor}" tabindex="-1">
        
        <input type="hidden" name="shipping_type" value="${form.shipping.type}" tabindex="-1">
        <input type="hidden" name="Shipping_Zipcode" value="${form.shipping.targetZipCode}" tabindex="-1">
        
        
        <!--  <input type="hidden" name="cart_1_name" value="Camiseta Branca" tabindex="-1">-->
        <input type="hidden" name="cart_1_unitprice"value="1000000" tabindex="-1">
        <input type="hidden" name="cart_1_quantity" value="1" tabindex="-1">
        <input type="hidden" name="cart_1_weight" value="100" tabindex="-1">
        <input type="hidden" name="cart_1_zipcode" value="20020080" tabindex="-1">
        <input type="hidden" name="cart_1_type" value="1" tabindex="-1">
        
        <c:set var="count" value="1" scope="page" />
        
        <c:forEach items="${form.cart.items}" var="item">
                        
                        <input type="hidden" name="cart_${count}_name" value="${item.name}" tabindex="-1">
                        
                        <c:out value="cart_${count}_name" />
                        <c:out value="${item.name}"/><br>
                    <%--     <input type="hidden" name="cart_${count + 1}_unitprice" value="${item.unitPrice}" tabindex="-1">
                        <c:out value="${item.unitPrice}"/><br>
                        <input type="hidden" name="cart_${count + 1}_quantity" value="${item.quantity}" tabindex="-1">
                        <c:out value="${item.quantity}"/><br>
                        <input type="hidden" name="cart_${count + 1}_weight" value="${item.weight}" tabindex="-1">
                        <c:out value="${item.weight}" /> <br>
                        <input type="hidden" name="cart_${count + 1}_zipcode" value="20020080" tabindex="-1">
                        <input type="hidden" name="cart_${count + 1}_type" value="${item.type}" tabindex="-1">
                        <c:out value="${item.type}"/> <br>--%>
                    
                    </c:forEach>
        
        
        <input type="hidden" name="shipping_1_name" value="Servico da Loja" tabindex="-1">
        <input type="hidden" name="shipping_1_price" value="500" tabindex="-1">
        <input type="hidden" name="shipping_2_name" value="Motoboy" tabindex="-1">
        <input type="hidden" name="shipping_2_price" value="800" tabindex="-1">
        <input type="hidden" name="Shipping_Address_Name" value="Av. Brigadeiro Faria Lima" tabindex="-1">
        <input type="hidden" name="Shipping_Address_Number" value="1461" tabindex="-1">
        <input type="hidden" name="Shipping_Address_Complement" value="" tabindex="-1">
        <input type="hidden" name="Shipping_Address_District" value="Jd. Paulista" tabindex="-1">
        <input type="hidden" name="Shipping_Address_City" value="Sao Paulo" tabindex="-1">
        <input type="hidden" name="Shipping_Address_State" value="SP" tabindex="-1">
        
        
        <input type="hidden" name="Customer_Name" value="${form.customer.fullName}" tabindex="-1">
        <input type="hidden" name="Customer_Email" value="${form.customer.email}" tabindex="-1">
        <input type="hidden" name="Customer_Identity" value="${form.customer.identity}" tabindex="-1">
        <input type="hidden" name="Customer_Phone" value="${form.customer.phone}" tabindex="-1">
        <input type="submit" value="Comprar" tabindex="-1">
        
    </form>

</body>

<script>
		//$("#cieloform").submit();
		
		 console.log('xxx');
		 var x = $("#cieloform").serialize() ;
		 console.log(x);
	</script>


<%--

    <iframe id="vpos" name="vpos" width="800" height="550"></iframe>
    <form id="formcielo"
                action="https://cieloecommerce.cielo.com.br/Transactional/Order/Index"
                method="post" target="vpos" tabindex="-1">
                            
                    <input type="hidden" name="merchant_id" value="${form.merchantId}" tabindex="-1">
                    <c:out value="${form.merchantId}" /> <br>
                    <input type="hidden" name="order_number" value="${form.orderNumber}" tabindex="-1"> 
                    <c:out value="${form.orderNumber}" /><br>
                    <input type="hidden" name="soft_descriptor" value="${form.softDescriptor}" tabindex="-1">
                    <c:out value="${form.softDescriptor}" /><br>
                    
                    <c:set var="count" value="1" scope="page" />
                    
                    <c:forEach items="${form.cart.items}" var="item">
                        
                        <input type="hidden" name="cart_${count + 1}_name" value="${item.name}" tabindex="-1">
                        <c:out value="cart_${count}_name" />
                        <c:out value="${item.name}"/><br>
                        <input type="hidden" name="cart_${count + 1}_unitprice" value="${item.unitPrice}" tabindex="-1">
                        <c:out value="${item.unitPrice}"/><br>
                        <input type="hidden" name="cart_${count + 1}_quantity" value="${item.quantity}" tabindex="-1">
                        <c:out value="${item.quantity}"/><br>
                        <input type="hidden" name="cart_${count + 1}_weight" value="${item.weight}" tabindex="-1">
                        <c:out value="${item.weight}" /> <br>
                        <input type="hidden" name="cart_${count + 1}_zipcode" value="20020080" tabindex="-1">
                        <input type="hidden" name="cart_${count + 1}_type" value="${item.type}" tabindex="-1">
                        <c:out value="${item.type}"/> <br>
                    
                    </c:forEach>
                    
                    <c:set var="countSh" value="0" scope="page" />
                    
                    <c:forEach items="${form.shipping.services}" var="service">
                    
                        <input type="hidden" name="shipping_${countSh + 1}_name" value="${service.name}" tabindex="-1">
                        <c:out value="shipping_${countSh + 1}_name" /> <br>
                        <c:out value="${service.name}" /> <br>
                        <input type="hidden" name="shipping_${countSh + 1}_price" value="${service.price}" tabindex="-1">
                        <c:out value="${service.price}" /> <br>
                        
                    </c:forEach>
                    
                    <input type="hidden" name="shipping_type" value="${form.shipping.type}" tabindex="-1">
                    <c:out value="${form.shipping.type}" /> <br>
                    
                    <input type="hidden" name="Shipping_Zipcode" value="${form.shipping.targetZipCode}" tabindex="-1">
                    <c:out value="${form.shipping.targetZipCode}" /> <br>
                    
                    <input type="hidden" name="Shipping_Address_Name" value="${form.shipping.address.street}" tabindex="-1">
                    <c:out value="${form.shipping.address.street}" /> <br>
                    
                    <input type="hidden" name="Shipping_Address_Number" value="${form.shipping.address.number}" tabindex="-1">
                    <c:out value="${form.shipping.address.number}" /> <br>
                    
                    <input type="hidden" name="Shipping_Address_Complement" value="${form.shipping.address.complement}" tabindex="-1">
                    <c:out value="${form.shipping.address.complement}" /> <br>
                    
                    <input type="hidden" name="Shipping_Address_District" value="${form.shipping.address.district}" tabindex="-1">
                    <c:out value="${form.shipping.address.district}" /> <br>
                    
                    <input type="hidden" name="Shipping_Address_City" value="${form.shipping.address.city}" tabindex="-1">
                    <c:out value="${form.shipping.address.city}" /> <br>
                    
                    <input type="hidden" name="Shipping_Address_State" value="${form.shipping.address.state}" tabindex="-1">
                    <c:out value="${form.shipping.address.state}" /> <br>
                    
                    <input type="hidden" name="Customer_Name" value="${form.customer.fullName}" tabindex="-1">
                    <c:out value="${form.customer.fullName}" /> <br> 
                    <input type="hidden" name="Customer_Email" value="${form.customer.email}" tabindex="-1">
                    <c:out value="${form.customer.email}" /> <br> 
                    <input type="hidden" name="Customer_Identity" value="${form.customer.identity}" tabindex="-1">
                    <c:out value="${form.customer.identity}" /> <br> 
                    <input type="hidden" name="Customer_Phone" value="${form.customer.phone}" tabindex="-1"> 
                    <c:out value="${form.customer.phone}" />
                    
                    <input type="submit" value="Comprar" tabindex="-1">


 --%>

</html>
