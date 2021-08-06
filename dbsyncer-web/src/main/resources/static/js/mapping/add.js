function submit(data) {
    doPoster("/mapping/add", data, function (data) {
        if (data.success == true) {
            bootGrowl("新增驱动成功!", "success");
            doLoader('/mapping/page/edit?id=' + data.resultValue);
        } else {
            bootGrowl(data.resultValue, "danger");
        }
    });
}

// 绑定匹配相似表复选框事件
function bindAutoMatchTableCheckBoxClick(){
    $('#autoMatchTableSelect').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        labelHover: false,
        cursor: true
    });
}

$(function () {
    // 兼容IE PlaceHolder
    $('input[type="text"],input[type="password"],textarea').PlaceHolder();

    // 初始化select2插件
    $(".select-control").select2({
        width: "100%",
        theme: "classic"
    });

    // 绑定匹配相似表复选框事件
    bindAutoMatchTableCheckBoxClick();

    //保存
    $("#mappingSubmitBtn").click(function () {
        var $form = $("#mappingAddForm");
        if ($form.formValidate() == true) {
            var data = $form.serializeJson();
            submit(data);
        }
    });

    //返回
    $("#mappingBackBtn").click(function () {
        backIndexPage();
    });
})