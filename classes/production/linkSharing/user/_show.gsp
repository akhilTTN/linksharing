<g:each in="${users}" var="user">
    <div class="row">
        <div class="col-sm-3">
            <pic:userImage id="${user.userId}"/>
        </div>

        <div class="col-sm-9 ">
            <div>
                %{--<a href="profile/${user.userId}"><label>${user.userFullName}</label></a>--}%
                <g:link action="profile" controller="user" params='["id": "${user.userId}"]'><label>${user.userFullName}</label></g:link>
                <div>@${user.userName}</div>
            </div>

            <div>
                <div class="col-md-5">
                    <div>subscription</div>
                    %{--<div>${user.subscriptionCount}</div>--}%
                    <ls:topicSubscriptionCount user="${user}"/>
                </div>

                <div class="col-md-2">
                    <div>Topics</div>

                    %{--<div>${user.topicCount}</div>--}%
                    <div><ls:topicCount user="${user}"/></div>
                </div>
            </div>
        </div>
    </div>
</g:each>

