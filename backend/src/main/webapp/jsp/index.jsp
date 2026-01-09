<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zxx">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ogani Template">
    <meta name="keywords" content="Ogani, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>AGRO Equipment Rental System</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/elegant-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nice-select.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.carousel.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/slicknav.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<!-- Page Preloader -->
<div id="preloder">
    <div class="loader"></div>
</div>

<!-- Header -->
<header class="header">
    <div class="header__top">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">

                </div>

            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">

            <div class="col-lg-6">
                <nav class="header__menu">
                    <ul>
                        <li class="active"><a href="index.jsp">Home</a></li>
                        <li><a href="shop-grid.jsp">Shop</a></li>
                        <li><a href="blog.jsp">Blog</a></li>
                        <li><a href="contact.jsp">Contact</a></li>
                    </ul>
                </nav>
            </div>
            <div class="col-lg-3">

            </div>
        </div>
    </div>
</header>

<!-- Hero Section -->
<section class="hero">
    <div class="container">
        <div class="row">

            <div class="col-lg-12">
                <div class="hero__item" style="background-image:url('${pageContext.request.contextPath}/img/hero/banner.jpg')">
                    <div class="hero__text">
                        <h2 style="color:green;">AGRO</h2>
                        <h2 style="color:white;">Equipment Rental System</h2>
                        <a href="${pageContext.request.contextPath}/shop-grid">
                            <i class="icon nav-icon" data-feather="globe"></i>
                            <span class="menu-item" data-key="Organization">SHOP</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Categories -->
<section class="categories">
    <div class="container">
        <h2 class="text-center"><b>Machines for Rent</b></h2>
        <div class="row">
            <c:forEach var="machine" items="${machines}">
                <div class="col-lg-3">
                    <div class="categories__item"
                         style="background-image:url('${pageContext.request.contextPath}/img/categories/${machine.image}')">
                        <h5><a href="#">${machine.name}</a></h5>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<!-- Footer -->
<footer class="footer spad">
    <div class="container text-center">
        <p>© <%= java.time.Year.now() %> AGRO Equipment Rental System</p>
    </div>
</footer>

<!-- JS -->
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/owl.carousel.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script src="${pageContext.request.contextPath}/js/index.js"></script>

<script>
    let ctx = '${pageContext.request.contextPath}';
</script>
</body>
</html>
