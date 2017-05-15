<div class="panel panel-default">
    %{--<div class="panel-heading">--}%
    <div class="panel-body">
        <div class="row">
            <div class="col-sm-2">
                %{--<pic:userImage id="${detailedPost.c}"--}%
            </div>

            <div class="col-sm-10 ">
                <div class=" user_details">
                    <label>${detailedPost.fullName}</label>
                    <a href="#" class="pull-right">Grails</a>
                </div>

                <div class=" user_details">
                    <label>${detailedPost.username}</label>
                    <label class="pull-right user_details">
                        ${detailedPost.updated}
                    </label>
                </div>

                <div class=" pull-right">
                    <g:each in="${(1..detailedPost.ratings)}">
                        <span class="glyphicon glyphicon-heart"></span>
                    </g:each>
                %{-- <span class="glyphicon glyphicon-heart"></span>
                 <span class="glyphicon glyphicon-heart"></span>
                 <span class="glyphicon glyphicon-heart"></span>
                 <span class="glyphicon glyphicon-heart"></span>--}%
                </div>

            </div>
        </div>

        %{--</div>--}%

        %{--<div class="panel-body">--}%
        <p>${detailedPost.description}</p>
        %{--Grails is a powerful web framework, for the Java platform aimed at multiplying developers’
        productivity thanks to a Convention-over-Configuration, sensible defaults and opinionated APIs.
        It integrates smoothly with the JVM, allowing you to be immediately productive whilst providing
        powerful features, including integrated ORM, Domain-Specific Languages, runtime and compile-time
        meta-programming and Asynchronous programming.--}%


        <div class="row">
            <a href="https://www.facebook.com">
                <i id="social-fb" class="fa fa-facebook-square fa-2x social glyphsize"></i></a>
            <a href="https://twitter.com">
                <i id="social-tw" class="fa fa-twitter-square fa-2x social glyphsize"></i></a>
            <a href="https://plus.google.com">
                <i id="social-gp" class="fa fa-google-plus-square fa-2x social glyphsize"></i></a>
            <a href="mailto:bootsnipp@gmail.com">
                <i id="social-em" class="fa fa-envelope-square fa-2x social glyphsize"></i></a>
            <span class="pull-right">
                <a href="#" class="operations">Delete</a>
                <a href="#" class="operations">Edit</a>
                <g:if test="${detailedPost.isLinkResource()}">
                    <g:link controller="resource" action="newLink" params='["id": "${resource.resourceID}"]' target="_blank">view full site</g:link>
                </g:if>
                <g:else>
                    <g:link action="download" class="pull-right" controller="resource"
                            params='["id": "${detailedPost.resourceID}"]'>Download</g:link>
                </g:else>
            %{--<a href="#" class="operations">Download</a>--}%
            </span>
        </div>
    </div>
</div>