<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <a class="navbar-brand" href="frontoffice/inicio">Mi Panel</a>
                      
            <!-- Navbar-->
            <ul class="navbar-nav ml-auto ml-md-0">
                <li class="nav-item">
                      <a class="dropdown-item text-white" href="logout">Cerrar Sesión</a>                 
                </li>
            </ul>
        </nav>
        
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            
                            <a class="nav-link" href="inicio">
                                <div class="sb-nav-link-icon"><i class="fa fa-tachometer-alt"></i></div>
                                Inicio
                            </a>
                            <a class="nav-link" href="inicioFrontOffice">
                                <div class="sb-nav-link-icon"><i class="fa fa-tachometer-alt"></i></div>
                                Mi Panel
                            </a>
                            <a class="nav-link" href="LibrosFrontOffice">
                                <div class="sb-nav-link-icon"><i class=""></i></div>
                                libros
                            </a>
                            <a class="nav-link" href="frontoffice/libros?validados=0">
                                <div class="sb-nav-link-icon"><i class="fa fa-check-circle-o"></i></div>
                                Pendientes Validar
                            </a>
                            <a class="nav-link" href="frontoffice/CrearLibroFrontOffice">
                                <div class="sb-nav-link-icon"><i class="fa fa-plus-circle"></i></div>
                                Crear libro
                            </a>
                         </div>   
                    </div>       
                </nav>
            </div>
            
            
            <!-- CONTENIDO PRINCIPAL -->
             <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid">
            