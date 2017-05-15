<%@ page import="com.demo.linksharing.util.Visibility" %>


<div class="modal fade" id="topicCreate" role="dialog" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <label class="modal-title">Create Topic</label>
            </div>

            <div class="modal-body" style="margin-left: 15px;margin-right:15px;">
                <g:form method="post" class="form-horizontal" controller="topic" action="save">
                    <div class="form-group">
                        <div class="control-label col-sm-3"><label class="pull-left">Name*</label></div>

                        <div class="col-sm-9">
                            <g:textField type="text" class="form-control pull-right" name="topicName"
                                   placeholder="enter the topic name"/></div>
                    </div>
                    <div class="form-group">
                        <div class="control-label col-sm-3"><label class="pull-left">Visibility*</label>
                        </div>

                        <div class="col-sm-9">
                            <g:select name="visibility" from="${Visibility.values()}" class="form-control pull-right"
                                      defaultLabel="Visibility" id="visibility"/>

                        </div>
                    </div>

                    <div class="form-group" style="padding-left:15px;padding-right:15px">
                        <button type="submit" class="btn btn-info ">save</button>
                        <button type="button" class="btn btn-danger pull-right" data-dismiss="modal">cancel</button>
                    </div>
                </g:form>
            </div>
        </div>
    </div>
</div>

