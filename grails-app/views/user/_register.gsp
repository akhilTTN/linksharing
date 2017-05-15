<div class="col-md-5 col-sm-5 pull-right">
    <div class="panel panel-default">
        <div class="panel-heading">Register</div>
        <div class="panel-body">
            <g:form method="post" class="form-horizontal" enctype="multipart/form-data" controller="login" action="register">
                <div class="form-group">
                    <div class="control-label col-sm-4"><label for="firstname" class="pull-left">Firstname*</label></div>
                    <div class="col-sm-8">
                        <input type="text" class="form-control pull-right" name="firstName" placeholder="enter the firstname" /></div>
                </div>
                <div class="form-group">
                    <div class="control-label col-sm-4"><label for="lastname" class="pull-left">Lastname*</label></div>
                    <div class="col-sm-8">
                        <input type="text" class="form-control pull-right" name="lastName" placeholder="enter the lastname" /></div>
                </div>
                <div class="form-group">
                    <div class="control-label col-sm-4"><label for="email" class="pull-left">Email*</label></div>
                    <div class="col-sm-8">
                        <input type="email" class="form-control pull-right" name="email" placeholder="enter the email" /></div>
                </div>
                <div class="form-group">
                    <div class="control-label col-sm-4"><label for="Username" class="pull-left">Username*</label></div>
                    <div class="col-sm-8">
                        <input type="text" class="form-control pull-right" name="username" placeholder="enter the Username" /></div>
                </div>
                <div class="form-group">
                    <div class="control-label col-sm-4"><label for="password" class="pull-left">Password*</label></div>
                    <div class="col-sm-8">
                        <input type="password" class="form-control pull-right" name="password" placeholder="enter the password" /></div>
                </div>
                <div class="form-group">
                    <div class="control-label col-sm-4"><label for="confirmPassword" class="pull-left">Confirm Password*</label></div>
                    <div class="col-sm-8">
                        <input type="password" class="form-control pull-right" name="confirmPassword" placeholder="enter the password again" /></div>
                </div>
                <div class="form-group">
                    <div class="control-label col-sm-3"><label for="document" class="pull-left">Photo*</label></div>
                    <div class="col-sm-9">
                         <input type="file" name="photo" />
                    </div>
                </div>
                <div class="form-group" style="padding-left:15px;padding-right:15px">
                    <a href="user"><button type="submit" class="btn btn-info pull-right">Register</button></a>
                </div>
            </g:form>
        </div>
    </div>
</div>