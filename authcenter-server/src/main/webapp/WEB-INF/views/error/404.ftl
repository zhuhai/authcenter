<html>
<head>
    <title></title>
</head>
<body>
<div class="alert alert-block alert-success">
    <button type="button" class="close" data-dismiss="alert">
        <i class="ace-icon fa fa-times"></i>
    </button>
    <i class="ace-icon fa fa-check red"></i>
    页面跑外星去了
</div>
<script type="text/javascript">
    var scripts = [null,null];
    /*ace.load_ajax_scripts(scripts, function() {
        //inline scripts related to this page
    });*/
    $(".page-content-area").ace_ajax('loadScripts',scripts,null);
</script>
</body>
</html>