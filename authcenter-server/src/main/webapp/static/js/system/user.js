var scripts = [null,'/static/js/common.js','/static/js/bootstrap-multiselect.js','/static/js/sha1.js','/static/js/bootbox.min.js',null];
$(".page-content-area").ace_ajax('loadScripts',scripts,function(){
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";
//resize to fit page size
    $(window).on('resize.jqGrid', function () {
        $(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width() );
    });
//resize on sidebar collapse/expand
    var parent_column = $(grid_selector).closest('[class*="col-"]');
    $(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
        if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
            //setTimeout is for webkit only to give time for DOM changes and then redraw!!!
            setTimeout(function() {
                $(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
            }, 0);
        }
    });

    $(grid_selector).jqGrid({
        jsonReader:{
            page: "currentPage",
            total: "totalPage"
        },
        url:"/user/list",
        datatype: "json",
        mtype:"GET",
        height: 310,
        colNames:['编号','用户名','头像','姓名','性别','手机','邮箱','部门','角色','是否锁定','创建时间','sex','status','organizationId'],
        colModel:[
            {
                key:true,
                name:'id',
                index:'id',
                width:40
            },
            {
                name:'userName',
                width:90,
                sortable:false
            },
            {
                name:'avatar',
                width:40,
                sortable:false,
                align:'center',
                formatter:function (cellvalue, options, rowObject) {
                    var imgSrc = '';
                    if (cellvalue) {
                        imgSrc = '<img src="'+cellvalue+'" width="30px" height="30px"/>';
                    }
                    return imgSrc;
                }
            },
            {
                name:'realName',
                width:90,
                sortable:false
            },
            {
                name:'sex',
                width:50,
                sortable:false,
                formatter:function (cellvalue, options, rowObject) {
                    var screenName = '';
                    switch (cellvalue) {
                        case 0:
                            screenName = '男';
                            break;
                        case 1:
                            screenName = '女';
                            break;
                        default:
                            screenName = '';
                    }
                    return screenName;
                }
            },
            {
                name:'phone',
                width:100,
                sortable:false
            },
            {
                name:'email',
                width:100,
                sortable:false
            },
            {
                name:'organization.name',
                width:90,
                sortable:false
            },
            {
                name:'roles',
                width:90,
                sortable:false,
                formatter:function (cellvalue, options, rowObject) {
                    var roles = rowObject.roles;
                    var roleNames = '';
                    if (roles instanceof Array) {
                        for(var i = 0; i < roles.length; i++) {
                            roleNames += roles[i].name;
                            if (i < roles.length-1) {
                                roleNames += ',';
                            }
                        }
                    }

                    return roleNames;
                }
            },
            {
                name:'status',
                align:'center',
                width:50,
                sortable:false,
                formatter: function (cellvalue,options,rowObject) {
                    var screenValue = '';
                    switch (cellvalue) {
                        case 1:
                            screenValue = '正常';
                            break;
                        case 0:
                            screenValue = '<span style="color:red">锁定</span>';
                            break;
                        default :
                            screenValue = '正常';
                    }
                    return screenValue;
                }
            },
            {
                name:'createTime',
                index:'create_time',
                width:120,
                sortable:true

            },
            {
                name:'sex',
                hidden:true
            },
            {
                name:'status',
                hidden:true
            },
            {
                name:'organization.id',
                hidden:true
            }

        ],
        //rownumbers: true,
        //rownumWidth: 30,
        viewrecords : true,
        rowNum:10,
        rowList:[10,20,30],
        pager : pager_selector,
        altRows: true,
        //第一个参数表示是否显示排序图标，第二个参数表示图标排序方式，vertical:垂直，horizontal：水平
        //第三个参数指单击功 能，true：单击列可排序，false：单击图标排序。
        viewsortcols:[true,'vertical',true],
        multiselect: true,
        multiselectWidth:30,
        //multikey: "ctrlKey",
        multiboxonly: true,
        loadComplete : function() {
            var table = this;
            setTimeout(function(){
                styleCheckbox(table);
                updateActionIcons(table);
                updatePagerIcons(table);
                enableTooltips(table);
            }, 0);
        },
        sortorder: "desc",
        autowidth: true

    });
//trigger window resize to make the grid get the correct size
    $(window).triggerHandler('resize.jqGrid');




    $(".multiselect").multiselect({
        enableFiltering: true,
        buttonClass: 'btn btn-white btn-primary',
        buttonWidth:'100%',
        templates: {
            button: '<button type="button" class="multiselect dropdown-toggle" data-toggle="dropdown"></button>',
            ul: '<ul class="multiselect-container dropdown-menu"></ul>',
            filter: '<li class="multiselect-item filter"><div class="input-group"><span class="input-group-addon"><i class="fa fa-search"></i></span><input class="form-control multiselect-search" type="text"></div></li>',
            filterClearBtn: '<span class="input-group-btn"><button class="btn btn-default btn-white btn-grey multiselect-clear-filter" type="button"><i class="fa fa-times-circle red2"></i></button></span>',
            li: '<li><a href="javascript:void(0);"><label></label></a></li>',
            divider: '<li class="multiselect-item divider"></li>',
            liGroup: '<li class="multiselect-item group"><label class="multiselect-group"></label></li>'
        }
    });

//添加用户
    $("#submitAddButton").click(function(){
        $("#createForm").submit();
    });

    $("#createForm").validate({
        errorElement:'div',
        errorClass:'help-block',
        //使多选框验证生效
        //ignore:':hidden:not(".multiselect")',
        rules:{
            userName:{
                required:true
            },
            password:{
                required:true,
                rangelength:[6,18]
            },
            repassword:{
                required:true,
                equalTo:"#password"
            },
            email:{
                email:true
            }/*,
            organizationId:{
                required:true
            },
            roleIds:{
                required:true
            }*/
        },
        messages:{
            userName:{
                required:'请输入用户名'
            },
            password:{
                required:'请输入密码',
                rangelength:'密码长度为6~18位'
            },
            repassword:{
                required:"请再次输入密码",
                equalTo:"两次密码输入不一致"
            },
            email:{
                email:'电子邮件格式错误'
            }/*,
            organizationId:{
                required:'请选择部门'
            },
            roleIds:{
                required:'请选择角色'
            }*/

        },
        highlight:function(e){
            $(e).closest(".form-group").addClass("has-error");
        },
        success:function(e){
            $(e).closest(".form-group").removeClass("has-error");
            $(e).remove();
        },
        errorPlacement:function(error,element){
            if(element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
                var controls = element.closest('div[class*="col-"]');
                if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
                else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
            } else if(element.is('.select2')) {
                error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
            } else if(element.is('.chosen-select')) {
                error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
            } else {
                error.insertAfter(element.parent());
            }
        },
        submitHandler:function(form){
            var userName = $("#userName").val();
            var password = CryptoJS.SHA1($("#password").val())+"";
            var email = $("#email").val();
            var organizationId = $("#organizationId").val();
            var roleIds = $("#roleIds").val();
            if (roleIds == null) {
                roleIds = new Array();
            }
            var sex = $("input[name='sex']:checked").val();
            var status = $("input[name='status']:checked").val();
            var realName = $("#realName").val();
            var phone = $("#phone").val();
            /*if(!roleIds) {
                alertErrorNotice("请选择角色！");
                return;
            }*/
            $.ajax({
                url:'/user/create',
                type:'post',
                data:{userName:userName,password:password,email:email,organizationIds:organizationId,roleIds:roleIds.toString(),sex:sex,status:status},
                dataType:'json',
                success:function(result) {
                    if (result && result.code == 1) {
                        $("#create-user-modal").modal('hide');
                        $(grid_selector).jqGrid().trigger("reloadGrid");
                    } else if (result.code == 0){
                        alertErrorNotice("系统繁忙，请稍后再试！");
                    } else if (result.code == 10002) {
                        alertErrorNotice("用户名已存在！");
                    }
                },
                error:function() {
                    alertErrorNotice("系统异常，添加失败！");
                }
            });
        }

    });


//模态框关闭，清空表单内容
    $("#create-user-modal").on('hidden.bs.modal',function(){
        $("#createForm")[0].reset();
        $("#roleIds").multiselect('refresh');
    });

//修改用户
    $("#editButton").click(function(){
        $("#updateForm")[0].reset();
        $("#role").multiselect('deselectAll',false);
        $("#role").multiselect('updateButtonText');
        //var id = $(grid_selector).jqGrid('getGridParam','selrow');
        var userIds = $(grid_selector).jqGrid('getGridParam','selarrrow');
        if(userIds.length != 1){
            alertErrorNotice("请选择需要修改的一条数据！");
        }else{
            var rowData = $(grid_selector).jqGrid('getRowData',userIds);
            var organization = rowData['organization.name'];
            var roleNames = rowData.roles.split(",");
            var sex = rowData.sex;
            var status = rowData.status;
            var organizationId = rowData['organization.id'];
            $("#edit_userName").val(rowData.userName);
            $("#userId").val(rowData.id);
            $("#edit_realName").val(rowData.realName);
            $("#edit_email").val(rowData.email);
            $("#edit_phone").val(rowData.phone);
            $("#edit_organization").val(organizationId);
            for(var i=0;i<roleNames.length;i++){
                $("#edit_roleIds option").each(function(){
                    if($(this).text() == roleNames[i]) {
                        $("#edit_roleIds").multiselect('select',$(this).val());
                    }
                });
            }
            $(".sex").each(function () {
                if ($(this).val() == sex) {
                    $(this).attr("checked","checked");
                } else {
                    $(this).removeAttr("checked");
                }
            });
            $(".status").each(function () {
                if ($(this).val() == status) {
                    $(this).attr("checked","checked");
                } else {
                    $(this).removeAttr("checked");
                }
            });

            $("#edit-user-modal").modal('toggle');

        }

        //var name = $(grid_selector).jqGrid('getCell',id,'userName');

    });
    $("#edit-user-modal").on('hidden.bs.modal',function(){
        $("#updateForm")[0].reset();
        $("#edit_roleIds").multiselect('refresh');

    });

    $("#updateBtn").click(function(){
        $("#updateForm").submit();
    });
    $("#updateForm").validate({
        errorElement:'div',
        errorClass:'help-block',
        rules:{
            edit_userName:{
                required:true
            },
            /*organizationId:{
                required:true
            },
            roleIds:{
                required:true
            },*/
            edit_email:{
                email:true
            },
            edit_password:{
                rangelength:[6,18]
            },
            edit_repassword:{
                equalTo:"#edit_password"
            }
        },
        messages:{
            edit_userName:{
                required:'请输入用户名'
            },
            edit_email:{
                email:'电子邮件格式错误'
            },
            edit_password:{
                rangelength:"密码长度为6~18位"
            },
            edit_repassword:{
                equalTo:"两次密码输入不一致"
            }
           /* organizationId:{
                required:'请选择部门'
            },
            roleIds:{
                required:'请选择角色'
            }*/
        },
        highlight:function(e){
            $(e).closest(".form-group").addClass("has-error");
        },
        success:function(e){
            $(e).closest(".form-group").removeClass("has-error");
            $(e).remove();
        },
        errorPlacement:function(error,element){
            if(element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
                var controls = element.closest('div[class*="col-"]');
                if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
                else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
            } else if(element.is('.select2')) {
                error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
            } else if(element.is('.chosen-select')) {
                error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
            } else {
                error.insertAfter(element.parent());
            }
        },
        submitHandler:function(form){
            var userId = $("#userId").val();
            var realName = $("#edit_realName").val();
            var organizationId = $("#edit_organization").val();
            var roleIds = $("#edit_roleIds").val();
            if (roleIds == null) {
                roleIds = new Array();
            }
            var password = $("#edit_password").val();
            var email = $("#edit_email").val();
            var phone = $("#edit_phone").val();
            var sex = $("input[name='edit_sex']:checked").val();
            var status = $("input[name='edit_status']:checked").val();
            /*if(!roleIds){
                alertErrorNotice("请选择角色！");
                return;
            }*/
            $.ajax({
                url:'/user/update',
                type:'post',
                dataType:'json',
                data:{id:userId,realName:realName,password:password,phone:phone,email:email,organizationIds:organizationId,roleIds:roleIds.toString(),sex:sex,status:status},
                success:function(result){
                    if(result && result.code == 1){
                        $("#edit-user-modal").modal('hide');
                        $(grid_selector).jqGrid().trigger('reloadGrid');
                    }else {
                        alertErrorNotice("系统繁忙，请稍后再试！");
                    }
                },
                error:function(){
                    alertErrorNotice("系统异常，修改失败！")
                }
            });
        }
    });

    /**
     * 删除用户
     */
    $("#delButton").click(function(){
        var ids = $(grid_selector).jqGrid('getGridParam','selarrrow');
        console.log(ids);
        if(ids.length == 0) {
            alertErrorNotice("请至少选择一条数据！");
        } else {
            bootbox.setLocale("zh_CN");
            /*bootbox.confirm('确定要删除吗？',function(result){
                if (result) {
                    $.ajax({
                        url:'/user/delete',
                        type:'POST',
                        dataType:'json',
                        data:{ids:ids.toString()},
                        success:function(result){
                            if (result && result.code == 1) {
                                alertSuccessNotice("删除成功",1000);
                                $(grid_selector).jqGrid().trigger('reloadGrid');
                            }else{
                                alertErrorNotice("系统繁忙，请稍后再试！");
                            }
                        },
                        error:function(){
                            alertErrorNotice("系统异常，锁定失败！")
                        }
                    });
                }
            });*/
            bootbox.confirm({
                title:'<i class="fa fa-lightbulb-o"></i> 提示',
                message:"确定要删除吗？",
                buttons:{
                    confirm:{
                        label:'<i class="fa fa-check"></i> 确认',
                        className:"btn-success no-border"
                    },
                    cancel:{
                        label:'<i class="fa fa-times"></i> 取消',
                        className:"btn-danger no-border"
                    }
                },
                callback:function(result) {
                    if (result) {
                        $.ajax({
                            url:'/user/delete',
                            type:'POST',
                            dataType:'json',
                            data:{ids:ids.toString()},
                            success:function(result){
                                if (result && result.code == 1) {
                                    alertSuccessNotice("删除成功",1000);
                                    $(grid_selector).jqGrid().trigger('reloadGrid');
                                }else{
                                    alertErrorNotice("系统繁忙，请稍后再试！");
                                }
                            },
                            error:function(){
                                alertErrorNotice("系统异常，锁定失败！")
                            }
                        });
                    }
                }
            });

        }
    });




});
