<div class="panel panel-default">
    <div class="panel-heading">Profile</div>

    <div class="panel-body">
        <g:form method="post" class="form-horizontal" enctype="multipart/form-data" controller="user"
                action="updateProfile">
            <div class="form-group">
                <div class="control-label col-sm-4"><label class="pull-left">Firstname*</label></div>

                <div class="col-sm-8">
                    <input type="text" class="form-control pull-right" name="firstName"
                           placeholder="enter the firstname"/></div>
            </div>

            <div class="form-group">
                <div class="control-label col-sm-4"><label class="pull-left">Lastname*</label></div>

                <div class="col-sm-8">
                    <input type="text" class="form-control pull-right" name="lastName"
                           placeholder="enter the lastname"/></div>
            </div>

            <div class="form-group">
                <div class="control-label col-sm-4"><label class="pull-left">Username*</label></div>

                <div class="col-sm-8">
                    <input type="text" class="form-control pull-right" name="userName"
                           placeholder="enter the Username"/></div>
            </div>

            <div class="form-group">
                <div class="control-label col-sm-3"><label class="pull-left">Photo*</label></div>

                <div class="col-sm-9">
                    <input type="file" class=" pull-right" name="photo"/>
                </div>
            </div>

            <div class="form-group" style="padding-left:15px;padding-right:15px">
                <a href="user"><button type="submit" class="btn btn-info pull-right">Update</button></a>
            </div>
        </g:form>
    </div>
</div>
