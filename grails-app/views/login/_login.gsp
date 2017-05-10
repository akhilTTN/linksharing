<div class="col-xs-5 pull-right">
    <div class="panel panel-default">
        <div class="panel-heading">Login</div>
        <div class="panel-body">
            <g:form controller="login" action="loginHandler" class="form-horizontal">
                <div class="form-group">
                    <div class="control-label col-sm-3"><label class="pull-left">Email/Username*</label></div>
                    <div class="col-sm-9">
                        <input type="text" class="form-control pull-right" name="username" placeholder="enter the email/username" /></div>
                </div>
                <div class="form-group">
                    <div class="control-label col-sm-3"><label class="pull-left">Password*</label></div>
                    <div class="col-sm-9">
                        <input type="password" class="form-control pull-right" name="password" placeholder="enter the password" /></div>
                </div>
                <div class="form-group" style="padding-left:15px;padding-right:15px">
                    <a href="forgorPassword">forgot passowrd</a>
                    <a href="user"><button type="submit" class="btn btn-info pull-right">Login</button></a>
                </div>
            </g:form>
        </div>
    </div>
</div>