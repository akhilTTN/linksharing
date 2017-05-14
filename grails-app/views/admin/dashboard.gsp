<%@ page import="com.demo.linksharing.util.Visibility" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Admin Dashboard</title>
    <meta name="layout" content="main"/>
</head>

<body>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading clearfix">
            %{--<div class="row"  style="padding:0px;margin: 0px">--}%
            <div class="col-md-6 ">
                <h4>Users</h4>
            </div>

            <div class="col-md-6">
                <div class="col-md-6">
                    <g:select name="visibility" from="${Visibility.values()}" class="form-control"
                              defaultLabel="Visibility" id="visibility"/>
                </div>
                <div class="col-md-6">
                <g:form class="search-form" controller="topic" action="search">
                    <div class="form-group has-feedback">
                        %{--<label class="sr-only">Search</label>--}%
                        <g:hiddenField name="id" value=""/>
                        <g:hiddenField name="max" value="10"/>
                        <g:hiddenField name="offset" value="0"/>
                        <input style="border-radius: 20px" type="text" class="form-control" name="q" id="q"
                               placeholder="search">
                        <span class="glyphicon glyphicon-search form-control-feedback"></span>
                    </div>
                </g:form>
                </div>
            </div>
            %{--</div>--}%
        </div>

        %{--<div class="panel-body">--}%
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>Id</th>
                <th>Username</th>
                <th>Email</th>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Active</th>
                <th>Manage</th>
                %{--<th>Update</th>--}%
            </tr>
            </thead>
            <tbody>
            <g:each in="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.active}</td>
                    <td>Activate</td>
                    %{--<td><button class="btn btn-success" >update</button></td>--}%
                </tr></g:each>
            </tbody>
        </table>
        %{--</div>--}%
    </div>
</div>

</body>
</html>
