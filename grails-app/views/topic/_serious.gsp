<%@ page import="com.demo.linksharing.util.Seriousness" %>

<div class="col-md-5">
    <g:select id="seriousness" class="form-control" name="${subscription.id}" value="${subscription.seriousness}"
              from="${  Seriousness.values()}"
              onchange="changeSeriousness(this)" />
</div>



<script type="text/javascript">
    function changeSeriousness(element) {
// alert(element.name)
        console.log(element.name)
        console.log(element.value)
        jQuery.ajax({
            type:'POST',
            data:{'id': element.name, 'seriousness' : element.value},
            url:'/subscription/update',
        });
    }
</script>