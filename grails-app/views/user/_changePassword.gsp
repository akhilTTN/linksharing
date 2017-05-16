<div class="panel panel-default">
    <div class="panel-heading">Change Password</div>

    <div class="panel-body">
        <g:form method="post" class="form-horizontal" controller="user"
                action="updatePassword">
            <div class="form-group">
                <div class="control-label col-sm-4"><label class="pull-left">Old Password*</label></div>

                <div class="col-sm-8">
                    <input type="text" class="form-control pull-right" name="oldPassword"
                           placeholder="enter Old Password"/></div>
            </div>

            <div class="form-group">
                <div class="control-label col-sm-4"><label class="pull-left">New Password*</label></div>

                <div class="col-sm-8">
                    <input type="text" class="form-control pull-right" name="password"
                           placeholder="enter new Password"/></div>
            </div>

            <div class="form-group">
                <div class="control-label col-sm-4"><label class="pull-left">Confirm Password*</label></div>

                <div class="col-sm-8">
                    <input type="text" class="form-control pull-right" name="confirmPassword"
                           placeholder="enter new Password again"/></div>
            </div>

            <div class="form-group" style="padding-left:15px;padding-right:15px">
                <a href="user"><button type="submit" class="btn btn-info pull-right">Update</button></a>
            </div>
        </g:form>
    </div>
</div>
