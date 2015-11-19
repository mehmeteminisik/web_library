$(document).ready(function() {
    $(".input_form").hide();
    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };

    $("#button_add").click(function() {
        $(".input_form").hide();
        $("#addForm").show();
        $("#info_added").hide();
        $("#submit_add").click(function(){
            //$("#add:has(#info_added)").hide();
            var data=$("#add").serializeObject();
            $.ajax({
                url: "adder",
                type: "POST",
                dataType: 'JSON',
                data: {bookData: JSON.stringify(data)},
                success: function (data) {
                    $("#info_added").show();
                },
                error:function(data,status,er) {
                    alert("error: "+data+" status: "+status+" er:"+er);
                }
            });
        });
    });

    $("#button_find").click(function(){
        $(".input_form").hide();
        $("#findForm").show();
    });

    $("#button_delete").click(function(){
        $(".input_form").hide();
        $("#deleteForm").show();
        $("#info_deleted").hide();

        $("#submit_delete").click(function(){
            var data=$("#delete").serializeObject();
            $.ajax({
                url: "deleter",
                type: "POST",
                dataType: 'JSON',
                data: {bookData: JSON.stringify(data)},
                success: function (data) {
                    console.log(data);
                    $("#deleteForm").hide();
                    $("#sp").remove();

                    var span = $("<div id='sp' style='float:left;padding:50px 50px'><span class='label label-info'/></div>");
                    span.text(data);
                    $("#commands").after(span);
                },
                error:function(data,status,er) {
                    alert("Error deleting book! Book with this ISBN don`t exist!");
                }
            });
        });
    });
});