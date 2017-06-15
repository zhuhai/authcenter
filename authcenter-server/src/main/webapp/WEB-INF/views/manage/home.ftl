<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>主页</title>

    <meta name="description" content="overview &amp; stats" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="/static/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/static/css/font-awesome.min.css" />

    <!-- text fonts -->
    <link rel="stylesheet" href="/static/css/ace/ace-fonts.css" />
    <link rel="stylesheet" href="/static/css/jquery.gritter.css"/>
    <!-- ace styles -->
    <link rel="stylesheet" href="/static/css/ace/ace.min.css" id="main-ace-style" />

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="/static/css/ace/ace-part2.min.css" />
    <![endif]-->
    <link rel="stylesheet" href="/static/css/ace/ace-skins.min.css" />
    <link rel="stylesheet" href="/static/css/ace/ace-rtl.min.css" />

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="/static/css/ace/ace-ie.min.css" />
    <![endif]-->
    <!-- ace settings handler -->
    <script src="/static/js/ace/ace-extra.min.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="/static/js/html5shiv.min.js"></script>
    <script src="/static/js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="no-skin">
<!-- #section:basics/navbar.layout -->
<div id="navbar" class="navbar navbar-default">
    <script type="text/javascript">
        try{ace.settings.check('navbar' , 'fixed')}catch(e){}
    </script>

    <div class="navbar-container" id="navbar-container">
        <!-- #section:basics/sidebar.mobile.toggle -->
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler">
            <span class="sr-only">Toggle sidebar</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>

        <!-- /section:basics/sidebar.mobile.toggle -->
        <div class="navbar-header pull-left">
            <!-- #section:basics/navbar.layout.brand -->
            <a href="#" class="navbar-brand">
                <small><i class="fa fa-comments"></i>后台管理系统</small>
            </a>
        </div>

        <!-- #section:basics/navbar.dropdown -->
        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <!-- #section:basics/navbar.user_menu -->
                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="/static/avatars/user.jpg" alt="头像" />
                            <span class="user-info">
                                <small>欢迎您,</small>
                                <@shiro.principal property="userName"/>
                            </span>
                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>
                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="#/user/${user.id}/update">
                                <i class="ace-icon fa fa-cog"></i>
                                设置
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <i class="ace-icon fa fa-user"></i>
                                个人资料
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="/sso/logout">
                                <i class="ace-icon fa fa-power-off"></i>
                                退出
                            </a>
                        </li>
                    </ul><!-- /section:basics/navbar.user_menu -->
                </li>

            </ul>
        </div><!-- /section:basics/navbar.dropdown -->
    </div><!-- /.navbar-container -->
</div><!-- /section:basics/navbar.layout -->

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>

    <!-- #section:basics/sidebar -->
    <div id="sidebar" class="sidebar responsive">
        <script type="text/javascript">
            try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
        </script>
        <!--nav-list-->
        <ul class="nav nav-list">
            <#if menus??>
                <#list menus as menu>
                    <#if menu?? && menu.pid == 0>
                        <li class="">
                            <a href="#" data-url="${(menu.url)!}" class="dropdown-toggle">
                                <i class="menu-icon ${(menu.icon)!}"></i>
                                <span class="menu-text">${(menu.name)!}</span>
                                <b class="arrow fa fa-angle-down"></b>
                            </a>
                            <b class="arrow"></b>
                            <ul class="submenu">
                                <#list menus as subMenu>
                                    <#if subMenu?? && subMenu.pid == menu.id>
                                        <li class="">
                                            <a href="#${(subMenu.url)!}" data-url="${(subMenu.url)!}">
                                                <i class="menu-icon fa fa-caret-right"></i>
                                                <span class="menu-text">${(subMenu.name)!}</span>
                                            </a>
                                        </li>
                                    </#if>
                                </#list>

                            </ul>
                        </li>
                    </#if>

                </#list>
            </#if>


            <#--<c:forEach items="${menus}" var="menu">
                <li class="menus">
                    <a href="#${menu.url}" data-url="${menu.url}">
                        <i class="menu-icon fa ${menu.icon}"></i>
                        <span class="menu-text"> ${menu.name} </span>
                    </a>
                    <b class="arrow"></b>
                </li>
            </c:forEach>-->

        </ul><!-- /.nav-list -->

        <!-- #section:basics/sidebar.layout.minimize -->
        <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
            <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
        </div>

        <!-- /section:basics/sidebar.layout.minimize -->
        <script type="text/javascript">
            try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
        </script>
    </div>

    <!-- /section:basics/sidebar -->
    <div class="main-content">
        <!-- #section:basics/content.breadcrumbs -->
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="/">首页</a>
                </li>
            </ul><!-- /.breadcrumb -->

        </div>

        <!-- /section:basics/content.breadcrumbs -->
        <div class="page-content">

            <div class="page-content-area" data-ajax-content="true">

            </div><!-- /.page-content-area -->
        </div><!-- /.page-content -->
    </div><!-- /.main-content -->

    <div class="footer">
        <div class="footer-inner">
            <!-- #section:basics/footer -->
            <div class="footer-content">
                    <span class="bigger-120">
                        <span class="blue bolder">ChinaSea</span>
                        Application &copy; 2016~
                    </span>

            </div>

            <!-- /section:basics/footer -->
        </div>
    </div>

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->

<!-- basic scripts -->


<script type="text/javascript" src="/static/js/jquery-1.12.4.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
<script src="/static/js/excanvas.min.js"></script>
<![endif]-->

<script src="/static/js/jquery.gritter.min.js"></script>
<!-- ace scripts -->
<script src="/static/js/ace/ace-elements.min.js"></script>
<script src="/static/js/ace/ace.js"></script>
<script src="/static/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="/static/js/jqGrid/grid.locale-cn.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script type="text/javascript">
    //Load content via ajax
    $(function(){
        /*if('enable_ajax_content' in ace) {
            var options = {
                content_url: function(url) {
                    return url;
                },
                loading_text:'正在加载中...',
                default_url: '/index'//default url
            };
            ace.enable_ajax_content($, options)
        }*/


    });

</script>
</body>
</html>
