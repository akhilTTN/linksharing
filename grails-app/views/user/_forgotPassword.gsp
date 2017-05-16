<%@ page import="com.demo.linksharing.util.Visibility" %>


<div class="modal fade" id="forgetPassword" role="dialog" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <label class="modal-title">Forgot Password</label>
            </div>

            <div class="modal-body" style="margin-left: 15px;margin-right:15px;">
                <g:form method="post" class="form-horizontal" controller="login" action="forgotPassword">
                    <div class="form-group">
                        <div class="control-label col-sm-3"><label class="pull-left">Email*</label></div>

                        <div class="col-sm-9">
                            <g:textField type="email" class="form-control pull-right" name="email"
                                         placeholder="enter your email"/></div>
                    </div>
                    <div class="form-group" style="padding-left:15px;padding-right:15px">
                        <button type="submit" class="btn btn-info ">Send Mail</button>
                        <button type="button" class="btn btn-danger pull-right" data-dismiss="modal">cancel</button>
                    </div>
                </g:form>
            </div>
        </div>
    </div>
</div>

