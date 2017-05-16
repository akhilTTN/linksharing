<g:each in="${resourceList}" var="resource">
    <div class="well myResource">
        <div class="col-sm-2">
            <!--<span class="glyphicon glyphicon-user "></span>-->
            <!-- %{--<img src="../images/user_image.jpg"/>--}% -->
            <pic:userImage id="${resource.createdBy.id}"/>
        </div>

        <div class="col-sm-10 ">
            <div class=" user_details">
                <label>${resource.createdBy.getName()}</label>&nbsp;&nbsp;&nbsp;<label>@${resource.createdBy.username}</label>
                %{--<a href="#" class="pull-right">${resource.topicName}</a>--}%
                <g:link action="show" class="pull-right" controller="topic"
                        params='["id": "${resource.topicID}"]'>${resource.topicName}</g:link>
            </div>

            <div class="">
                ${resource.desctiption}
            </div>
        </div>

        <g:if test="${session.user}">
        <div class="row">
            <div class="col-xs-offset-10">
                <a><ls:isResourceEditable id="${resource.resourceID}"/></a>
                <g:if test="${session.user.username == resource.createdBy.username}">
                    <g:link controller="resource" action="delete" params='["id":"${resource.resourceID}"]'>Delete</g:link>
                </g:if>
            </div>
        </div>
        </g:if>
        <div class="resourceEditDiv col-xs-offset-4" style="display: none">
            <g:form controller="resource" action="editResourceDescription">
                <textarea name="newDescription" id="123" cols="30" rows="5">${resource.desctiption}</textarea>
                <input type="hidden" name="resourceID" value="${resource.resourceID}">
                <input type="submit" value="Update">
            </g:form>
        </div>

        <div>
            <a href="https://www.facebook.com" style="margin-right:10px">
                <i id="social-fb" class="fa fa-facebook-square fa-2x social glyphsize"></i></a>
            <a href="https://twitter.com" style="margin-right:10px">
                <i id="social-tw" class="fa fa-twitter-square fa-2x social glyphsize"></i></a>
            <a href="https://plus.google.com" style="margin-right:10px">
                <i id="social-gp" class="fa fa-google-plus-square fa-2x social glyphsize"></i></a>
            <a href="mailto:bootsnipp@gmail.com" style="margin-right:10px">
                <i id="social-em" class="fa fa-envelope-square fa-2x social glyphsize"></i></a>
            <span class="pull-right">
                <g:if test="${session.user}">
                %{--<a href="/resource/download" class="operations" style="margin-right:10px">Download</a>--}%
                    <g:if test="${resource.linkResource()}">
                        <g:link action="download" controller="resource"
                                params='["id": "${resource.resourceID}"]'><label>Download</label></g:link>
                    </g:if>
                    <g:else>
                        <g:link controller="resource" action="newLink" params='["id": "${resource.resourceID}"]'
                                target="_blank">view full site</g:link>
                    </g:else>
                %{--<a href="#" class="operations" style="margin-right:10px">View Full Site</a>--}%
                    <span><ls:markAsRead resource="${resource}"/></span>
                </g:if>
                <g:link action="viewPost" controller="resource"
                        params='["id": "${resource.resourceID}"]'><label>View post</label></g:link>
            </span>
        </div>
    </div>
</g:each>
<g:paginate total="${resourceList.size()}" max="5"/>

