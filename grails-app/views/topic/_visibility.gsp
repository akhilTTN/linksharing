<%@ page import="com.demo.linksharing.util.Visibility" %>

<div class="col-lg-4">
    <g:select id="seriousness" class="form-control" name="${topic.id}" value="${topic.visibility}"
              from="${Visibility.values()}"
              onchange="changeVisibility(this)" />
</div>



<script type="text/javascript">
    function changeVisibility(element) {
// alert(element.name)
        console.log(element.name)
        console.log(element.value)
        jQuery.ajax({
            type:'POST',
            data:{'id': element.name, 'seriousness' : element.value},
            url:'/topic/updateVisibility',
        });
    }
</script>