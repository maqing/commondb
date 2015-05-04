tomcat 需要设置 URIEncoding="utf-8" 因为url上传中文字符
 
和应用相关的前台文件放到了app下
tools放入和工具箱(砖头瓦块)相关的文件
footer.html,header.html,menu.html为公用文件

修改配置文件 struts_app.xml
添加相应的路径和包

HierSelectAction
生成层级属性选择页面
使用了功能强大的jstree控件 参考 http://www.jstree.com
在普通的树上加了3个plugin，checkbox plugin. json plugin 和 crrm plugin来实现 checkbox，数据载入和编辑

InputPageGenAction

生成输入的表单，传入要输入的元数据的名字(metaName)，和要生成的字段的名字(fieldsName)，
如果不指定要输入的字段的名字，那么改Meta对应的所有字段都生成，各个名字之间用|分割

IField 封装页面的元素的，和前台显示联系更紧密
IDataType 封装数据类型的，和数据库联系更紧密

SearchPageGenAction
生成查找页面，同时也兼顾查找结果集的显示
传入参数 ccondtions想作为查询条件的特征属性的名字，不输入的话默认为全部的特征属性
传入参数 hcondtions想作为查询条件的层级属性的名字，不输入的话默认为全部的层级属性
传入参数 fieldNames想作为查询条件的描述属性的名字，不输入的话默认为全部的描述属性，
		同时所有的描述属性也会在结果集的table中显示

