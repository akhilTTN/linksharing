<g:each in="${resourceList}" var="resource">
    <div class="well">
        <div class="col-sm-2">
            <!--<span class="glyphicon glyphicon-user "></span>-->
            <!-- %{--<img src="../images/user_image.jpg"/>--}% -->
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
                    <a href="#" class="operations" style="margin-right:10px">Download</a>
                    <a href="#" class="operations" style="margin-right:10px">View Full Site</a>
                    <span><ls:markAsRead resource="${resource}"/></span>
                </g:if>
                <g:link action="viewPost" controller="resource"
                        params='["id": "${resource.resourceID}"]'><label>View post</label></g:link>
            </span>
        </div>
    </div>
</g:each>

