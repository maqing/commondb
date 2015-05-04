﻿<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib  prefix="s" uri="/struts-tags" %>

<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>选择层级属性</title>
	<script src="../_lib/jquery-1.4.2.min.js" type="text/javascript"> </script>
	<script type="text/javascript" src="../_lib/jquery.cookie.js"></script>
	<script type="text/javascript" src="../_lib/jquery.hotkeys.js"></script>
	<script type="text/javascript" src="../jquery.jstree.js"></script>

	<style type="text/css">
	html, body { margin:0; padding:0; }
	body, td, th, pre, code, select, option, input, textarea { font-family:verdana,arial,sans-serif; font-size:12px; }
	.demo, .demo input, .jstree-dnd-helper, #vakata-contextmenu { font-size:12px; font-family:Verdana; }
	#container { width:780px; margin:10px auto; overflow:hidden; position:relative; }
	#demo { width:auto; height:400px; overflow:auto; border:1px solid gray; }

	#menu { height:30px; overflow:auto; }
	#text { margin-top:1px; }

	#alog { font-size:9px !important; margin:5px; border:1px solid silver; }
	</style>
</head>
<body>
<div id="container">

<div id="menu">

<input type="button" id="close" value="关闭" onclick="window.close();" style="display:block; float:right;"/>
<input type="button" id="ok" value="确定" onclick="set_parent_window();" style="display:block; float:right;"/>

</div>

<!-- the tree container (notice NOT an UL node) -->
<div id="demo" class="demo"></div>
<!-- JavaScript neccessary for the tree -->
<script type="text/javascript">

function getTextPath( node )
{
	var str = "";
	
	while(node.parentNode.parentNode.children[1] != undefined)
	{
		str = '\\' + $.trim($("#"+node.id + " a:first").text()) + str;
		node = node.parentNode.parentNode;
	}
	return str;
}

function set_parent_window()
{
	var checkedStr = "";
	var checkedId = "";
	$(".jstree-checked").each(function(i,n){//f.jstree-leaf
 		checkedStr += "<br/>" + getTextPath(n);
 		checkedId += "," + n.id.replace("node_","");
	})
	checkedId = checkedId.substring(1);
	checkedStr = checkedStr.substring(5);
	window.opener.$('#<s:property value="displayDiv"/>')[0].innerHTML = checkedStr;
	window.opener.$('#<s:property value="inputFieldId"/>')[0].value = checkedId;
	window.close();
}

 $.ajaxSetup({cache:false});
$(function () {
	// Settings up the tree - using $(selector).jstree(options);
	// All those configuration options are documented in the _docs folder
	$("#demo")
		.jstree({ 
			// the list of plugins to include
			"plugins" : [ "themes", "json_data", "ui", "crrm", "dnd", "search",  "hotkeys", "contextmenu","checkbox" ],
			// Plugin configuration

			// I usually configure the plugin that handles the data first - in this case JSON as it is most common 
			"json_data" : { 
				// I chose an ajax enabled tree - again - as this is most common, and maybe a bit more complex
				// All the options are the same as jQuery's except for `data` which CAN (not should) be a function
				"ajax" : {
					// the URL to fetch the data
					"url" : "/app/tool/getHierData.ac?attrId=<s:property value="attrId"/>",
					// this function is executed in the instance's scope (this refers to the tree instance)
					// the parameter is the node being loaded (may be -1, 0, or undefined when loading the root nodes)
					"data" : function (n) { 
						// the result is fed to the AJAX request `data` option
						return { 
							"operation" : "get_children", 
							"id" : n.attr ? n.attr("id").replace("node_","") : 1 
						}; 
					},
					"async" : true, 
        			"async_data": function(){ return {"ts" : new Date().getTime()} } 
        		}
			},
			// Configuring the search plugin
			"search" : {
				// As this has been a common question - async search
				// Same as above - the `ajax` config option is actually jQuery's object (only `data` can be a function)
				"ajax" : {
					"url" : "./server.php",
					// You get the search string as a parameter
					"data" : function (str) {
						return { 
							"operation" : "search", 
							"search_str" : str 
						}; 
					}
				}
			},
			// Using types - most of the time this is an overkill
			// Still meny people use them - here is how
			"types" : {
				// I set both options to -2, as I do not need depth and children count checking
				// Those two checks may slow jstree a lot, so use only when needed
				"max_depth" : -2,
				"max_children" : -2,
				// I want only `drive` nodes to be root nodes 
				// This will prevent moving or creating any other type as a root node
				"valid_children" : [ "drive" ],
				"types" : {
					// The default type
					"default" : {
						// I want this type to have no children (so only leaf nodes)
						// In my case - those are files
						"valid_children" : "none",
						// If we specify an icon for the default type it WILL OVERRIDE the theme icons
						"icon" : {
							"image" : "./file.png"
						}
					},
					// The `folder` type
					"folder" : {
						// can have files and other folders inside of it, but NOT `drive` nodes
						"valid_children" : [ "default", "folder" ],
						"icon" : {
							"image" : "./folder.png"
						}
					},
					// The `drive` nodes 
					"drive" : {
						// can have files and folders inside, but NOT other `drive` nodes
						"valid_children" : [ "default", "folder" ],
						"icon" : {
							"image" : "./root.png"
						},
						// those options prevent the functions with the same name to be used on the `drive` type nodes
						// internally the `before` event is used
						"start_drag" : false,
						"move_node" : false,
						"delete_node" : false,
						"remove" : false
					}
				}
			},
			// For UI & core - the nodes to initially select and open will be overwritten by the cookie plugin

			// the UI plugin - it handles selecting/deselecting/hovering nodes
			"ui" : {
				// this makes the node with ID node_4 selected onload
				"initially_select" : [
				<s:iterator value="selectNodeArr"  status="nodeStatus">
  					"node_<s:property/>"<s:if test="!#nodeStatus.last">,</s:if>
				</s:iterator>
				
				]
			},
			// the core plugin - not many options here
			"core" : { 
				// just open those two nodes up
				// as this is an AJAX enabled tree, both will be downloaded from the server
				//"initially_open" : [ "node_2" , "node_3" ] 
			}
		})
		.bind("create.jstree", function (e, data) {
			$.post(
				"/app/tool/manageHier.ac", 
				{ 
					"operation" : "create_node", 
					"id" : data.rslt.parent.attr("id").replace("node_",""), 
					"position" : data.rslt.position,
					"title" : data.rslt.name,
					"type" : "folder"//data.rslt.obj.attr("rel")
				}, 
				function (r) {
					if(r.status) {
						$(data.rslt.obj).attr("id", "node_" + r.id);
					}
					else {
						$.jstree.rollback(data.rlbk);
					}
				}
			);
		})
		.bind("remove.jstree", function (e, data) {
			$.post(
				"/app/tool/manageHier.ac", 
				{ 
					"operation" : "remove_node", 
					"id" : data.rslt.obj.attr("id").replace("node_","")
				}, 
				function (r) {
					if(!r.status) {
						$.jstree.rollback(data.rlbk);
					}
				}
			);
		})
		.bind("rename.jstree", function (e, data) {
			$.post(
				"/app/tool/manageHier.ac", 
				{ 
					"operation" : "rename_node", 
					"id" : data.rslt.obj.attr("id").replace("node_",""),
					"title" : data.rslt.new_name
				}, 
				function (r) {
					if(!r.status) {
						$.jstree.rollback(data.rlbk);
					}
				}
			);
		})
		.bind("move_node.jstree", function (e, data) {
			$.post(
				"./server.php", 
				{ 
					"operation" : "move_node", 
					"id" : data.rslt.o.attr("id").replace("node_",""), 
					"ref" : data.rslt.np.attr("id").replace("node_",""), 
					"position" : data.rslt.cp,
					"title" : data.rslt.name,
					"copy" : data.rslt.cy ? 1 : 0
				}, 
				function (r) {
					if(!r.status) {
						$.jstree.rollback(data.rlbk);
					}
					else {
						$(data.rslt.oc).attr("id", "node_" + r.id);
						if(data.rslt.cy && oc.children("UL").length) {
							data.inst.refresh(data.rslt.oc);
						}
					}
					$("#analyze").click();
				}
			);
		})
		.bind("loaded.jstree", function (event, data) {
			
			//$.jstree._focused().open_all();
			$.jstree._focused();
			
		});
});
</script>




</div>

</body>
</html>