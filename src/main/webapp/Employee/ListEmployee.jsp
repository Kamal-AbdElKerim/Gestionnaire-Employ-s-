<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.Employee" %>

<div class="affichag">

    <div class="group">
        <svg viewBox="0 0 24 24" aria-hidden="true" class="search-icon">
            <g>
                <path
                        d="M21.53 20.47l-3.66-3.66C19.195 15.24 20 13.214 20 11c0-4.97-4.03-9-9-9s-9 4.03-9 9 4.03 9 9 9c2.215 0 4.24-.804 5.808-2.13l3.66 3.66c.147.146.34.22.53.22s.385-.073.53-.22c.295-.293.295-.767.002-1.06zM3.5 11c0-4.135 3.365-7.5 7.5-7.5s7.5 3.365 7.5 7.5-3.365 7.5-7.5 7.5-7.5-3.365-7.5-7.5z"
                ></path>
            </g>
        </svg>
        <form method="get" action="addEmployee">
            <input
                    id="query"
                    class="input"
                    type="search"
                    placeholder="Search..."
                    name="searchbar"
                    value="${requestScope.searchbar != null ? requestScope.searchbar : '' }"
            />
            <button type="submit" style="display: none;"></button>
        </form>

        <button class="button_Count">
            <svg viewBox="0 0 24 24" width="24" height="24" stroke="currentColor" stroke-width="2" fill="none" stroke-linecap="round" stroke-linejoin="round" class="css-i6dzq1"><polygon points="13 2 3 14 12 14 11 22 21 10 12 10 13 2"></polygon></svg>
            <c:out value="${not empty sessionScope.employees ? sessionScope.employees.size() : 0}" />
        </button>

        <form class="filter" method="get" action="addEmployee">
            <input
                    id="query2"
                    class="input"
                    type="search"
                    placeholder="Departement..."
                    name="Departement"
                    value="${requestScope.Departement != null ? requestScope.Departement : '' }"
            />
            <input
                    id="query1"
                    class="input"
                    type="search"
                    placeholder="Poste..."
                    name="Poste"
                    value="${requestScope.Poste != null ? requestScope.Poste : '' }"
            />
            <button type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
        </form>
    </div>

    <div class="list">
        <c:if test="${not empty sessionScope.employees}">
            <c:forEach var="employee" items="${sessionScope.employees}">
                <!-- one user -->
                <div class="OnePost">
                    <div class="img">
                        <img src="assets/image/images.png" alt="" />
                    </div>
                    <div class="profile">
                        <h3><c:out value="${employee.name}" /></h3>
                        <p><c:out value="${employee.email}" /></p>
                        <div class="moreinfo">
                            <button class="button-33 more-info-btn" type="button"
                                    data-name="${employee.name}"
                                    data-email="${employee.email}"
                                    data-phone="${employee.phone}"
                                    data-department="${employee.department}"
                                    data-position="${employee.position}">
                                <i class="fa-regular fa-circle-question"></i>
                            </button>

                            <form action="UpdateEmployeeServlet" method="get">
                                <input type="hidden" name="employeeId" value="${employee.id}" />
                                <button class="button-33 " type="submit">
                                    <i class="fa-regular fa-pen-to-square"></i>
                                </button>
                            </form>
                            <form action="DeleteEmployeeServlet" method="post">
                                <input type="hidden" name="employeeId" value="${employee.id}" />
                                <button class="button-33 bg_denger" type="submit" onclick="return confirm('Are you sure you want to delete this employee?');">
                                    <i class="fa-solid fa-trash-can"></i>
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- one user -->
            </c:forEach>
        </c:if>
    </div>
</div>

<!-- The Modal -->
<div id="myModal" class="modal">
    <div class="modal-content">
        <div class="cookie-card">
            <span class="title"><span id="modal-name"></span></span>
            <span class="close">&times;</span>
            <p class="description">Email : <span id="modal-email"></span></p>
            <p class="description">Departement : <span id="modal-department"></span></p>
            <p class="description">Poste : <span id="modal-position"></span></p>
            <div class="actions">
                <button class="accept" id="accept">
                    <span id="modal-phone-display"></span>
                    <span><i class="fa-solid fa-phone-volume"></i></span>
                </button>
            </div>
        </div>
    </div>
</div>
