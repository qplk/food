<%@include file='header.jsp'%>


<div class="container">



            <div class="jumbotron" style="background-color: #ffffff;">

                    <h2>Common information</h2>
                    <p>Username: <b>${user.username}</b></p>
                    <p>Gender: <b>
                        <c:if test="${user.gender == 1}"><c:out value="Female" /></c:if>
                        <c:if test="${user.gender == 0}"><c:out value="Male" /></c:if> </b></p>
                    <p>
                    <p>E-mail: <b>${user.email}</b></p>
                    <p>Phone number: <b>${user.phoneNumber}</b></p>
                        <a class="btn btn-default" href="${contextPath}/profile/updateInformation" role="button">Update information &raquo;</a></p>


                    <h2>Addresses</h2>

                    <c:if test="${empty user.addresses}">
                    <p><c:out value="You have not added any address yet" /></p>
                    </c:if>
                    <c:forEach var="address" items="${user.addresses}">
                <p><b><c:out value="${address.cityByAddressId.cityName}" /></b>, <c:out value="${address.street}" /> street, building <c:out value="${address.buildingNumber}" />, room <c:out value="${address.roomNumber}" /><br />
                    <c:if test="${not empty address.comment}">Comment: <c:out value="${address.comment}" /></c:if></p>
                        <p>
                         <form:form method="DELETE" action="${contextPath}/profile/deleteAddress/${address.addressId}">
                             <a class="btn btn-default" href="${contextPath}/profile/updateAddress/${address.addressId}" role="button">Update</a>
                             <button class="btn btn-default"type="submit">Delete</button></form:form></p>
                </c:forEach>
                    <a class="btn btn-default" href="${contextPath}/profile/addAddress" role="button">Add address &raquo;</a></p>
                </p>

                    <h2>Current orders</h2>



                <table class="table table-bordered">

                    <tr >
                        <th>
                            Order

                        </th>
                        <th>
                            Delivery time

                        </th>
                        <th>
                            Full price
                        </th>

                        <th>
                            Payment info
                        </th>
                        <th>
                            Status
                        </th>


                    </tr>
                <c:forEach var="order" items="${formed}">


                    <tr>
                        <td>
                            <c:forEach var="element" items="${order.orderElements}">

                                <p><b><c:out value="${element.food.foodName}"/></b> <c:out value="${element.food.price}"/> &#8381; &#xD7; <c:out value="${element.quantity}"/> item<c:if test="${element.quantity > 1}">s</c:if> = <c:out value="${element.elementPrice}"/>  &#8381;</p>


                            </c:forEach>
                        </td>

                        <td>
                            <fmt:formatDate value="${order.deliveryTime}" pattern="dd/MM H:mm (zzzz)" />
                        </td>
                        <td>
                            <c:out value="${order.fullPrice }"/> &#8381;
                        </td>
                        <td>
                            <c:out value="${order.paymentInfo}"/>
                        </td>
                        <td>
                            <c:out value="${order.status}"/>
                        </td>


                    </tr>





                </c:forEach>


                <c:forEach var="order" items="${delivering}">




                        <tr>
                            <td>
                                <c:forEach var="element" items="${order.orderElements}">

                                    <p><b><c:out value="${element.food.foodName}"/></b> <c:out value="${element.food.price}"/> &#8381; &#xD7; <c:out value="${element.quantity}"/> item<c:if test="${element.quantity > 1}">s</c:if> = <c:out value="${element.elementPrice}"/>  &#8381;</p>


                                </c:forEach>
                            </td>

                            <td>
                                <fmt:formatDate value="${order.deliveryTime}" pattern="dd/MM H:mm (zzzz)" />
                            </td>
                            <td>
                                <c:out value="${order.fullPrice }"/> &#8381;
                            </td>
                            <td>
                                <c:out value="${order.paymentInfo}"/>
                            </td>
                            <td>
                                <c:out value="${order.status}"/>
                            </td>


                        </tr>


                </c:forEach>
                </table>

                <c:if test="${empty formed and empty delivering}">

                    <p>No current orders</p>

                </c:if>

                <h2>Order history</h2>



                <table class="table table-bordered">

                    <tr >
                        <th>
                            Order

                        </th>
                        <th>
                            Delivery time

                        </th>
                        <th>
                            Full price
                        </th>

                        <th>
                            Payment info
                        </th>
                        <th>
                            Status
                        </th>


                    </tr>
                    <c:forEach var="order" items="${delivered}">


                        <tr>
                            <td>
                                <c:forEach var="element" items="${order.orderElements}">

                                    <p><b><c:out value="${element.food.foodName}"/></b> <c:out value="${element.food.price}"/> &#8381; &#xD7; <c:out value="${element.quantity}"/> item<c:if test="${element.quantity > 1}">s</c:if> = <c:out value="${element.elementPrice}"/>  &#8381;</p>


                                </c:forEach>
                            </td>

                            <td>
                                <fmt:formatDate value="${order.deliveryTime}" pattern="dd/MM H:mm (zzzz)" />
                            </td>
                            <td>
                                <c:out value="${order.fullPrice }"/> &#8381;
                            </td>
                            <td>
                                <c:out value="${order.paymentInfo}"/>
                            </td>
                            <td>
                                <c:out value="${order.status}"/>
                            </td>


                        </tr>





                    </c:forEach>





                </table>

                <c:if test="${empty delivered}">

                    <p>No history</p>

                </c:if>







    </div><!--/row-->
    <hr>
    <footer>
        <p>Copyright 2017</p>
    </footer>
</div><!--/.container-->
<%@include file='footer.jsp'%>

