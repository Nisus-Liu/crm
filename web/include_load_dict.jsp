<%--
  Created by IntelliJ IDEA.
  User: Nisus
  Date: 2017/12/7
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<script type="text/javascript">

    // 生成option列赋值指定select标签内
    // loadType: String, 指定dict_type_code, 获取对应类别的分类列表
    // toId: String,指定生成下拉框给谁
    // optionKey: String,选项的value, 封装数据用
    // optionValue: String,选项显示内容
    // selectedId: String, 需要选中的id, 实现效果: 当前输入哪一类, 哪一项就被选中.
    function loadSelectBox(ajaxUrl,loadType, toId, optionValue, optionText, selectedId) {
        // 设置要添加的节点
        var $toid = $("#" + toId);
        var opts = '';
        // 设置选项提示行
        opts = opts + '<option value="">--请-选-择--</option>';
        alert('${pageContext.request.contextPath}/baseDict');
//        alert(ajaxUrl);
        // ajax获取列表
        $.post(
            // '${pageContext.request.contextPath}/baseDict',
            ajaxUrl,
            {dictTypeCode: loadType},
            function (data) {
                // jquery的遍历
                // 1. 遍历目标
                // 2. 回调函数(索引, 遍历到的元素)
                $.each(data, function (i, ele) {
                    var val = ele[optionValue];
                    var txt = ele[optionText];

                    // 设置是否选中
                    var selectAttr = '';
                    if (ele['dictId'] == selectedId) {
                        selectAttr = 'selected="selected"';
                    }


                    var newOpt = '<option '+ selectAttr +' value="' + val + '">' + txt + '</option>';

                    opts = opts + newOpt;

                });
                // 最终处理好的html添加至指定节点
                $toid.append($(opts));
            },
            'json'
        )



    }


</script>
</head>
<body>

</body>
</html>
