<%@ page contentType="text/html; charset=UTF-8" %>
<!-- 我的资料 -->
<div class="modal fade" id="myInformation" role="dialog">
    <div class="modal-dialog" role="document" style="width: 30%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">我的资料</h4>
            </div>
            <div class="modal-body">
                <div style="position: relative; left: 40px;">
                    姓名：<b>张三</b><br><br>
                    登录帐号：<b>zhangsan</b><br><br>
                    组织机构：<b>1005，市场部，二级部门</b><br><br>
                    邮箱：<b>zhangsan@bjpowernode.com</b><br><br>
                    失效时间：<b>2017-02-14 10:10:10</b><br><br>
                    允许访问IP：<b>127.0.0.1,192.168.100.2</b>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script>
    jQuery(function ($) {
        $("#changePwdBtn").click(function () {

            let oldPwd = $("#oldPwd").val();
            let newPwd = $("#newPwd").val();
            let confirmPwd = $("#confirmPwd").val();

            if (!oldPwd) {
                alert("请输入原密码！");
                return ;
            }

            if (!newPwd) {
                alert("请输入新原密码！");
                return ;
            }

            if (!confirmPwd) {
                alert("请输入确认密码！");
                return ;
            }

            if(newPwd != confirmPwd) {
                alert("两次密码输入不一致，请重新输入！");

                $("#newPwd,#confirmPwd").val("");

                return ;
            }

            $.ajax({
                url: "/user/changePwd.do",
                type: "post",
                data: {
                    oldPwd: oldPwd,
                    newPwd // 相当于newPwd: newPwd
                },
                success(data) {
                    if (data.success) {
                        location = "/user/logout.do";
                    }
                    if (data.msg) {
                        alert(data.msg);
                    }
                }
            })
        })
    })
</script>

<!-- 修改密码的模态窗口 -->
<div class="modal fade" id="editPwdModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 70%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">修改密码</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="oldPwd" class="col-sm-2 control-label">原密码</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="oldPwd" style="width: 200%;">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="newPwd" class="col-sm-2 control-label">新密码</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="newPwd" style="width: 200%;">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="confirmPwd" class="col-sm-2 control-label">确认密码</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="confirmPwd" style="width: 200%;">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button id="changePwdBtn" type="button" class="btn btn-primary">更新</button>
            </div>
        </div>
    </div>
</div>

<!-- 退出系统的模态窗口 -->
<div class="modal fade" id="exitModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 30%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">离开</h4>
            </div>
            <div class="modal-body">
                <p>您确定要退出系统吗？</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal"
                        onclick="window.location.href='/user/logout.do';">确定
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 顶部 -->
<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
    <div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">
        动力云客 &nbsp;<span style="font-size: 12px;">SSM小练习</span></div>
    <div style="position: absolute; top: 15px; right: 15px;">
        <ul>
            <li class="dropdown user-dropdown">
                <a href="javascript:void(0)" style="text-decoration: none; color: white;" class="dropdown-toggle"
                   data-toggle="dropdown">
                    <span class="glyphicon glyphicon-user"></span> ${loginUser.name} <span class="caret"></span>
                </a>
                <ul class="dropdown-menu" style="width:120px;min-width:120px;right:-15px;left: auto;">
                    <li><a href="javascript:void(0)" data-toggle="modal" data-target="#myInformation"><span
                            class="glyphicon glyphicon-file"></span> 我的资料</a></li>
                    <li><a href="javascript:void(0)" data-toggle="modal" data-target="#editPwdModal"><span
                            class="glyphicon glyphicon-edit"></span> 修改密码</a></li>
                    <li><a href="javascript:void(0);" data-toggle="modal" data-target="#exitModal"><span
                            class="glyphicon glyphicon-off"></span> 退出</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>
