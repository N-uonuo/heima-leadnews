<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Hello World!</title>
</head>
<body>

<#-- list 数据的展示 -->
<b>展示list中的stu数据:</b>
<br>
<br>
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
    </tr>

    <#if stus??>
        <#list stus as stu>
            <#if stu.name='小红'>
                <tr style="color: red">
                    <td>${stu_index}</td>
                    <td>${stu.name}</td>
                    <td>${stu.age}</td>
                    <td>${stu.money}</td>
                </tr>
            <#else>
                <tr>
                    <td>${stu_index}</td>
                    <td>${stu.name}</td>
                    <td>${stu.age}</td>
                    <td>${stu.money}</td>
                </tr>

            </#if>

        </#list>
    </#if>
</table>
<hr>

<#-- Map 数据的展示 -->
<b>map数据的展示：</b>
<br/><br/>
<a href="###">方式一：通过map['keyname'].property</a><br/>
输出stu1的学生信息：<br/>
姓名：${stuMap['stu1'].name}<br/>
年龄：${stuMap['stu1'].age}<br/>
<br/>
<a href="###">方式二：通过map.keyname.property</a><br/>
输出stu2的学生信息：<br/>
姓名：${stuMap.stu2.name}<br/>
年龄：${stuMap.stu2.age}<br/>

<br/>
<a href="###">遍历map中两个学生信息：</a><br/>
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
    </tr>
    <#list stuMap?keys as key>
        <tr>
            <td>${key_index +1}</td>
            <td>${stuMap[key].name}</td>
            <td>${stuMap[key].age}</td>
            <td>${stuMap[key].money}</td>
        </tr>
    </#list>
</table>
<hr>


stu集合的大小：${stus?size}<br/>

<#--stu集合是否为空：${stus?empty}<br/>-->

<hr>

日期的格式化：
${date?datetime}<br/>
${date?string("yyyy-MM-dd HH:mm:ss")}
<hr>

内置函数c：
${point?c}<br/>

将json字符串转换为json对象：
<#assign jsonStr = '{"name":"张三","age":20}'/>
<#assign jsonObj = jsonStr?eval/>

</body>
</html>