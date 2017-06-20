<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
    <link rel="stylesheet" href="/static/css/ui.jqgrid.css" />
    <link rel="stylesheet" href="/static/css/bootstrap-multiselect.css"/>
</head>
<body>
    <div class="search-header">
        <form id="queryForm">
            <table class="table-condensed">

            </table>
        </form>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="well well-sm">
                <@shiro.hasPermission name="auth:user:create">
                    <a id="addButton" role="button" class="btn btn-primary btn-sm no-border" data-toggle="modal" data-target="#create-user-modal">添加</a>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="auth:user:update">
                    <a id="editButton" role="button" class="btn btn-purple btn-sm no-border" data-toggle="modal">修改</a>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="auth:user:delete">
                    <a id="delButton" role="button" class="btn btn-danger btn-sm no-border">删除</a>
                </@shiro.hasPermission>
            </div>
            <table id="grid-table"></table>
            <div id="grid-pager"></div>
            <!-- Modal -->
            <div id="create-user-modal" class="modal fade" tabindex="-1" role="dialog">
                <div class="modal-dialog">
                    <form  id="createForm" class="form-horizontal" action="#">
                        <div class="modal-content">
                            <div class="modal-header no-padding">
                                <div class="table-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        <span class="white">&times;</span>
                                    </button>
                                    <p>添加用户</p>
                                </div>
                            </div>
                            <div class="modal-body" style="max-height: 600px;overflow-y: scroll;">
                                <div class="container-fluid">
                                    <div class="form-group">
                                        <label for="userName" class="col-xs-12 col-sm-3 control-label">用户名</label>
                                        <div class="col-xs-12 col-sm-7">
                                            <div class="clearfix">
                                                <input type="text" id="userName" class="form-control" name="userName">
                                            </div>

                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="password" class="col-xs-12 col-sm-3 control-label">密码</label>
                                        <div class="col-xs-12 col-sm-7">
                                            <div class="clearfix">
                                                <input type="password" id="password" name="password" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="password" class="col-xs-12 col-sm-3 control-label">确认密码</label>
                                        <div class="col-xs-12 col-sm-7">
                                            <div class="clearfix">
                                                <input type="password" name="repassword" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="realName" class="col-xs-12 col-sm-3 control-label">姓名</label>
                                        <div class="col-xs-12 col-sm-7">
                                            <div class="clearfix">
                                                <input type="text" id="realName" name="realName" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="phone" class="col-xs-12 col-sm-3 control-label">手机号</label>
                                        <div class="col-xs-12 col-sm-7">
                                            <div class="clearfix">
                                                <input type="text" id="phone" name="phone" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="email" class="col-xs-12 col-sm-3 control-label">邮箱</label>
                                        <div class="col-xs-12 col-sm-7">
                                            <div class="clearfix">
                                                <input type="text" id="email" name="email" class="form-control">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="organizationId" class="col-xs-12 col-sm-3 control-label">部门</label>
                                        <div class="col-xs-12 col-sm-7">
                                            <div class="clearfix">
                                                <select class="form-control" name="organizationId" id="organizationId">
                                                    <option value="">请选择</option>
                                                    <#if organizationList??>
                                                        <#list organizationList as organization>
                                                            <option value="${organization.id}">${organization.name}</option>
                                                        </#list>
                                                    </#if>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="roleIds" class="col-xs-12 col-sm-3 control-label">角色</label>
                                        <div class="col-xs-12 col-sm-7">
                                            <div class="clearfix">
                                                <select class="form-control multiselect" name="roleIds" id="roleIds" multiple="multiple">
                                                    <#if roleList??>
                                                        <#list roleList as role>
                                                            <option value="${role.id}">${role.name}</option>
                                                        </#list>
                                                    </#if>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="sex" class="col-xs-12 col-sm-3 control-label">性别</label>
                                        <div class="radio">
                                            <label>
                                                <input name="sex" type="radio" class="ace" value="0" checked/>
                                                <span class="lbl">男&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                            </label>
                                            <label>
                                                <input name="sex" type="radio" class="ace" value="1"/>
                                                <span class="lbl">女</span>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="status" class="col-xs-12 col-sm-3 control-label">状态</label>
                                        <div class="radio">
                                            <label>
                                                <input name="status" type="radio" class="ace" value="1" checked/>
                                                <span class="lbl">正常</span>
                                            </label>
                                            <label>
                                                <input name="status" type="radio" class="ace" value="0"/>
                                                <span class="lbl">锁定</span>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="modal-footer no-margin-top">
                                <div class="text-center">
                                    <button id="submitAddButton" type="button" class="btn btn-primary no-border">
                                        <i class="ace-icon fa fa-floppy-o bigger-125"></i>
                                        保存
                                    </button>
                                    <button class="btn btn-pink no-border" data-dismiss="modal">
                                        <i class="ace-icon fa fa-times bigger-125"></i>
                                        取消
                                    </button>
                                </div>
                            </div>
                        </div><!-- /.modal-content -->
                    </form>
                </div><!-- /.modal-dialog -->
            </div>

            <div id="edit-user-modal" class="modal fade" tabindex="-1" role="dialog">
                <div class="modal-dialog">
                    <form  id="updateForm" class="form-horizontal" action="#">
                        <input type="hidden" id="userId">
                        <div class="modal-content">
                            <div class="modal-header no-padding">
                                <div class="table-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        <span class="white">&times;</span>
                                    </button>
                                    <p>修改用户</p>
                                </div>
                            </div>
                            <div class="modal-body" style="max-height: 600px;overflow-y: scroll;">
                                <div class="container-fluid">
                                    <div class="form-group">
                                        <label for="edit_userName" class="col-xs-12 col-sm-3 control-label">用户名</label>
                                        <div class="col-xs-12 col-sm-7">
                                            <div class="clearfix">
                                                <input type="text" id="edit_userName" class="form-control" name="edit_userName" disabled="disabled">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="edit_password" class="col-xs-12 col-sm-3 control-label">密码</label>
                                        <div class="col-xs-12 col-sm-7">
                                            <div class="clearfix">
                                                <input type="password" id="edit_password" name="edit_password" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="edit_repassword" class="col-xs-12 col-sm-3 control-label">确认密码</label>
                                        <div class="col-xs-12 col-sm-7">
                                            <div class="clearfix">
                                                <input type="password" id="edit_repassword" name="edit_repassword" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="edit_realName" class="col-xs-12 col-sm-3 control-label">姓名</label>
                                        <div class="col-xs-12 col-sm-7">
                                            <div class="clearfix">
                                                <input type="text" id="edit_realName" name="edit_realName" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="edit_phone" class="col-xs-12 col-sm-3 control-label">手机号</label>
                                        <div class="col-xs-12 col-sm-7">
                                            <div class="clearfix">
                                                <input type="text" id="edit_phone" name="edit_phone" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="edit_email" class="col-xs-12 col-sm-3 control-label">邮箱</label>
                                        <div class="col-xs-12 col-sm-7">
                                            <div class="clearfix">
                                                <input type="text" id="edit_email" name="edit_email" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="edit_organizationId" class="col-xs-12 col-sm-3 control-label">部门</label>
                                        <div class="col-xs-12 col-sm-7">
                                            <div class="clearfix">
                                                <select class="form-control" name="edit_organizationId" id="edit_organization">
                                                    <option value="">请选择</option>
                                                    <#if organizationList??>
                                                        <#list organizationList as organization>
                                                            <option value="${organization.id}">${organization.name}</option>
                                                        </#list>
                                                    </#if>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="edit_roleIds" class="col-xs-12 col-sm-3 control-label">角色</label>
                                        <div class="col-xs-12 col-sm-7">
                                            <div class="clearfix">
                                                <select class="form-control multiselect" name="edit_roleIds" id="edit_roleIds" multiple="multiple">
                                                    <#if roleList??>
                                                        <#list roleList as role>
                                                            <option value="${role.id}">${role.name}</option>
                                                        </#list>
                                                    </#if>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="edit_sex" class="col-xs-12 col-sm-3 control-label">性别</label>
                                        <div class="radio">
                                            <label>
                                                <input name="edit_sex" type="radio" class="ace sex" value="0"/>
                                                <span class="lbl">男&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                            </label>
                                            <label>
                                                <input name="edit_sex" type="radio" class="ace sex" value="1"/>
                                                <span class="lbl">女</span>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="edit_status" class="col-xs-12 col-sm-3 control-label">状态</label>
                                        <div class="radio">
                                            <label>
                                                <input name="edit_status" type="radio" class="ace status" value="1"/>
                                                <span class="lbl">正常</span>
                                            </label>
                                            <label>
                                                <input name="edit_status" type="radio" class="ace status" value="0"/>
                                                <span class="lbl">锁定</span>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="modal-footer no-margin-top">
                                <div class="text-center">
                                    <button id="updateBtn" type="button" class="btn btn-primary no-border">
                                        <i class="ace-icon fa fa-floppy-o bigger-125"></i>
                                        修改
                                    </button>
                                    <button class="btn btn-pink no-border" data-dismiss="modal">
                                        <i class="ace-icon fa fa-times bigger-125"></i>
                                        取消
                                    </button>
                                </div>
                            </div>
                        </div><!-- /.modal-content -->
                    </form>
                </div><!-- /.modal-dialog -->
            </div>
        </div><!-- /.col -->
    </div>
    <script type="text/javascript" src="/static/js/system/user.js"></script>
</body>
</html>
