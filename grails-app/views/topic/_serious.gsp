<%@ page import="com.demo.linksharing.util.Seriousness" %>

<div class="col-md-6">
    <g:select id="seriousness" class="form-control" name="${subscription.id}" value="${subscription.seriousness}"
              from="${Seriousness.values()}"
              onchange="changeSeriousness(this)"/>
</div>



<script type="text/javascript">
    function changeSeriousness(element) {
        alert(element.value + "  " + element.name)
        console.log(element.name)
        console.log(element.value)
        $.ajax({
            url: '/subscription/update',
            type: 'POST',
            data: {
                id: element.name,
                seriousness: element.value
            },
            success: function () {
                location.reload()
            }
        });
    }
</script>