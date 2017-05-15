<div class="panel panel-default">
    <div class="panel-heading panel-heading-border">
        <label>Change password</label>
    </div>

    <div class="panel-body">
        <g:form class="form-inline myform" controller="user" action="updatePassword">
            <span>Old Password*</span>
            <input type="text" class="form-control pull-right" name="oldPassword"
                   placeholder="old password"/>
            <br><br>
            <span>New Password*</span>
            <input type="text" class="form-control pull-right" name="password"
                   placeholder="password"/>
            <br><br>
            <span>Confirm password*</span>
            <input type="text" class="form-control pull-right" name="confirmPassword"
                   placeholder="confirm password"/>
            <br><br>
            <input type="submit" class="form-control btn btn-info pull-right" value="update">
        </g:form>

    </div>
</div>