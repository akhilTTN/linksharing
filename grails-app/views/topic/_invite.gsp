<%@ page import="com.demo.linksharing.Topic" %>


<div class="modal fade" id="sendInvitation" role="dialog" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Send Invitation</h4>
            </div>

            <div class="modal-body" style="margin-left: 15px;margin-right:15px;">
                <g:form method="post" class="form-horizontal" controller="topic" action="shareTopic">
                    <div class="form-group">
                        <div class="control-label col-sm-3"><label class="pull-left">Email*</label></div>

                        <div class="col-sm-9">
                            <g:textField type="email" class="form-control pull-right" name="email"
                                         placeholder="enter email Id"/></div>
                    </div>


                    <div class="form-group">
                        <div class="control-label col-sm-3"><label class="pull-left">Topic*</label></div>

                        <div class="col-sm-9">
                            <g:select name="id" from="${Topic.findAllByCreatedBy(session.user)}" optionKey="id" optionValue="topicName" class="form-control pull-right"
                                      defaultLabel="default topic" id="id"/>
                        </div>
                    </div>

                    <div class="form-group" style="padding-left:15px;padding-right:15px">
                        <a href="user"><button type="submit" class="btn btn-info ">share</button></a>
                        <button type="button" class="btn btn-danger pull-right" data-dismiss="modal">cancel</button>
                    </div>
                </g:form>
            </div>
        </div>
    </div>
</div>
