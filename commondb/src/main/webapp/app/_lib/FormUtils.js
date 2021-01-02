var FormUtils = {
		
		createLoadDiv : function() {
			var html = '<div id="_layer_">';
			html += '<div id="_MaskLayer_" style="filter: alpha(opacity=30); -moz-opacity: 0.3; opacity: 0.3;background-color: #000; width: 100%; height:99%; z-index: 999990; position: fixed;left: 0; top: 0; overflow: hidden; display: none"></div>';
			html += '<div id="_wait_" style="z-index: 999991; position: fixed; width:36px;height:36px; left:50%; margin-left:-18px;top:50%;margin-top:-50px; display: none"><center><img src="/images/loading-big.gif" /></center></div>';
			html += '</div>';
			return html;
		},
		
		layerShow : function(divHTML) {
			$('#_layer_').each(function(i,e){
				$(e).remove();
			})
			var addDiv = divHTML || FormUtils.createLoadDiv();
			$(addDiv).appendTo(document.body);
			$(window).resize(Position);
			Position();
			$("#_MaskLayer_").show();
			$("#_wait_").show();
			// 获取相对位置
			function Position() {
				// $("#_MaskLayer_").width($(window).width() - 20);
				var deHeight = screen.height;//$(document.body).height();
				var deWidth =  screen.width;//$(document.body).width();
				$("#_MaskLayer_").css({
					width : $(window).width() - 2 + "px",
					padding : "1px 1px 1px 1px"
				});
				$("#_wait_").css({
					left : (deWidth - $("#_wait_").width()) / 2 + "px",
					top : (deHeight - $("#_wait_").height() - 30) / 2 + "px"
				});
			}
		},
		layerHide : function() {
			$("#_MaskLayer_").hide();
			$("#_wait_").hide();
			$('#_layer_').each(function(i,e){
				$(e).remove();
			});
		}
}