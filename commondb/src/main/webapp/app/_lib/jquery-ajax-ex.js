(function($) {
    var AjaxHelper;
    /**
	 * 定义AjaxHelper 类 本类通过Id,查询数据
	 */
    AjaxHelper = function(options) {
        var _self = this;
        _self.isDebugEnable = false;
        _self._constructor(options);
    };
    /**
	 * 注册对象
	 */
    window.AjaxHelper = AjaxHelper;

    /**
	 * 添加扩展方法
	 */
    $.extend(AjaxHelper.prototype, {
        /**
		* 构造器方法
		* 
		* @param options
		*/
        _constructor: function(options) {
            var _self = this;
            if (options) $.extend(_self, options);
            return _self;
        },
        _complete: function(data, textStatus, jqXHR) {
            var _self = this;
            if (_self.isDebugEnable) {
                console.log('ajax helper request complete --> ', data, textStatus, jqXHR);
            }
            /**
             * 对返回的状态代码进行检查 执行相关的代码检查，如果
             */
            if (data && data.code) {
                if (data.code == '90001') {
                    alert('你没有登录!<br/><a href="javascript: void(0);" onclick="javascript: location.reload(); return false;">点击登录</a>');
                    return false;
                }
                if (data.code == '90002') {
                    alert('你没有权限执行此操作!');
                    return false;
                }
            }
            return true;
        },
        ajax: function(options) {
            var _self = this;
            if (!options) {
                if (_self.isDebugEnable) {
                    console.log(' ajax helper request exception, options can not be null ');
                }
                return false;
            }
            // replace option success
            /**
			 * success 方法封装，在增加前增加一项_complete作前置处理
			 */
            var oldSuccess = options.success;
            options.success = function(data, textStatus, jqXHR) {
                var flag = _self._complete(data, textStatus, jqXHR);
                if (flag) {
                    if (oldSuccess && $.isFunction(oldSuccess)) {
                        //oldSuccess.call(this, data,textStatus,jqXHR);
                    }
                }
            };

            /**
			 * 执行Ajax方法之前
			 */
            var oldBeforeSend = options.beforeSend;
            options.beforeSend = function(XMLHttpRequest) {
                if (_self.loadingMsg) {
                    _self.loadingMsg.close();
                    _self.loadingMsg = null;
                }
                // _self.loadingMsg =
                // $.ligerDialog.waitting('请稍候...');
                if (oldBeforeSend && $.isFunction(oldBeforeSend)) {
                    oldBeforeSend.call(this, XMLHttpRequest);
                }
            };

            /**
			 * Ajax方法执行之后
			 */
            var oldComplete = options.complete;
            options.complete = function(XMLHttpRequest, textStatus) {
                /*
				* if( _self.loadingMsg ){
				* _self.loadingMsg.close(); }
				*/
                if (oldComplete && $.isFunction(oldComplete)) {
                    oldComplete.call(this, XMLHttpRequest, textStatus);
                }
            };

            var oldDataFilter = options.dataFilter;
            /**
			 * data : 返回的数据的原始类型(string) dataType :
			 * 返回的数据格式预期类型(一般为JSON)
			 */
            options.dataFilter = function(data, dataType) {
                if (_self.isDebugEnable) {
                    console.log('ajax helper request complete data filter --> [data=', data, '] [dataType=', dataType, ']');
                }
                if (oldDataFilter && $.isFunction(oldDataFilter)) {
                    return oldDataFilter.call(this, data, dataType);
                }
                return data;
            };

            /**
			* 发送至服务器的头信息
			*/
            var oldHeaders = options.headers;
            if (oldHeaders) {
                options.headers = $.extend(true, oldHeaders, {
                    'isAjax': 1
                });
            } else {
                options.headers = {
                    'isAjax': 1
                };
            }
            /*
			* if( options.data && !(options.data.isAjax) ){
			* options.data.isAjax = 1; }
			*/

            $.ajax($.extend(true, {
                type: 'POST',
                dataType: 'JSON',
                scriptCharset: 'UTF-8',
                async: true,
                timeout: 99999,
                statusCode: {
                    404 : function() {
                        alert('抱歉, 你所访问的页面找不到!');
                    },
                    500 : function() {
                        alert('抱歉, 服务器内部错误! 请稍候再试');
                    }
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    if (_self.isDebugEnable) console.error('ajax helper obtain an exception when ajax request', XMLHttpRequest, textStatus, errorThrow);
                    /*
											* 暂未实现error如何处理
											* if(textStatus ==
											* 'error'){}
											*/
                    if (options.error) {
                        if ($.isFunction(options.error)) {
                            options.error.call(this, XMLHttpRequest, textStatus, errorThrown);
                        }
                    }
                }
            },
            options));
        },
        post: function(options) {
            // var _self = this;
        },
        get: function(options) {
            // var _self = this;
        },
        quickRequest: function(url, params, fn, options) {
            var _self = this;
            var ajaxOpt = {
                url: url,
                data: params,
                success: fn
            };
            var opt = $.extend(true, ajaxOpt, options);
            _self.ajax(opt);
        },
        quickLoad: function(url, params, fn, options) {
            var _self = this;
            var ajaxOpt = {
                url: url,
                data: params,
                success: fn,
                headers: {
                    'Expect-Type': 'HTML'
                },
                dataType: 'html'
            };
            
            var opt = $.extend(true, ajaxOpt, options);
            _self.ajax(opt);
        }
    });

} (jQuery));

/**
 * 扩展Jquery 方法，提供快速访问方法
 */
$.extend({
    quickRequest: function(url, params, fn, options) {
        var _self = this;
        if (!_self.ajaxHelper) {
            _self.ajaxHelper = new AjaxHelper();
        }
        var ajaxHelper = _self.ajaxHelper;
        var theForm;
        if (options.theForm) {
            theForm = $(options.theForm);
        } else {
            theForm = $('#theForm');
        }
        params = $.isEmptyObject(params) ? theForm.serialize() : params;
        ajaxHelper.quickRequest(url, params, fn, options);
    },
    convertData2List: function(datas, table, targetTable) {
        // 获取JQuery对象
        // var _self = this;
        var $table = $(table);
        var $targetTable = $(targetTable);
        if ($table.length > 0) {
            var thList = $table.find('tr th');
            if (!datas) return;
            $targetTable.empty();
            $.each(datas,
            function(i, obj) {
                var tr = $('<tr/>');
                $.each(thList,
                function(index, thObj) {
                    var $thObj = $(thObj);
                    var td = $('<td/>');
                    var div = $('<div/>');
                    var convertKey = $(thObj).attr('cmpKey');
                    // 判断要解析的数据类型
                    var cmpType = $thObj.attr('cmpType') ? $thObj.attr('cmpType') : 'data';
                    if (cmpType == 'data') {
                        div.append(obj[convertKey] ? obj[convertKey] : '');
                    } else if (cmpType == 'select') {

                    } else {
                        var input = $('<input type="' + cmpType + '" />');
                        if ($thObj.attr('cmpName')) input.attr('name', $thObj.attr('cmpName'));
                        if (convertKey) {
                            input.attr('value', obj[convertKey]);
                            var inputId = convertKey;
                            if ($thObj.attr('cmpPrefix')) {
                                inputId = $thObj.attr('cmpPrefix') + inputId + '';
                            }
                            input.attr('id', inputId);
                        }
                        div.append(input);
                    }
                    td.append(div);
                    if ($thObj.attr('width')) {
                        td.attr('width', $thObj.attr('width'));
                    }
                    tr.append(td);
                });
                $targetTable.append(tr);
            });
        }
    }
});

/**
 * 扩展Jquery方法
 */
$.fn.extend({
    disableFormContent: function(flag) {
        var _self = this;
        var currentFlag = (!flag) ? true: false;
        _self.find('*').attr('disabled', currentFlag);
        _self.find('.subbo1, .subbo2').attr('disabled', false);
    },
    quickLoad: function(url, param, fn, options) {
        var _self = this;
        var theForm;
        if (options.theForm) {
        	//theForm = $("#" + options.theForm);
            theForm = $(options.theForm);
        } else {
            theForm = $('#theForm');
        }
        param = $.isEmptyObject(param) ? theForm.serialize() : param;
        _self.quickLoadBase(url, param, fn, options);
    },
    quickLoadBase: function(url, params, fn, options) {
        var _self = this;
        if (!$.ajaxHelper) {
            $.ajaxHelper = new AjaxHelper();
        }
        var ajaxHelper = $.ajaxHelper;

        var off = url.indexOf(" ");
        var selector = null;
        if (off >= 0) {
            selector = url.slice(off, url.length);
            url = url.slice(0, off);
        }

        if (!options) options = {};
        options.complete = function(jqXHR, status, responseText) {
            // Store the response as specified by the jqXHR object
            responseText = jqXHR.responseText;
            // 判断登录状态
           try {
               var obj = jQuery.parseJSON(responseText);
               if (obj.resultCode == "-1") {
                   window.top.showLoginDialog();
                   Utils.LayerHide();
                   return;
               } else if (obj.resultCode = "0") {
                   Utils.LayerHide();
                   Utils.LayerShow(Utils.createMsgDiv(obj.resultMsg));
                   return;
               }
           } catch(e) {}

            // If successful, inject the HTML into all the matched
            // elements
            if (jqXHR.isResolved()) {
                // #4825: Get the actual response in case
                // a dataFilter is present in ajaxSettings
                jqXHR.done(function(r) {
                    responseText = r;
                });

                /*
                 * 判定是否存在目标DiV,如果存在，就将数据分别同步到不同的目标DIV
                 */
                if (options.targetContent && options.targetPage) {
                    var contentDiv = $('<div/>');
                    contentDiv.html(responseText);
                    if ($(contentDiv).find('#' + options.targetContent).length > 0) {
                        var $tbody = $(contentDiv).find('#' + options.targetContent);
                        $('#' + options.targetContent).empty();
                        $('#' + options.targetContent).append($tbody.html());
                    }
                    if ($(contentDiv).find('#msgObj').length > 0) {
                        var $resultMsgObj = $(contentDiv).find('#msgObj');
                        if ($.trim($resultMsgObj.text()) != "") {
                            $('#resultMsg').slideDown();
                        }
                    }

                    if ($(contentDiv).find("#" + options.targetPage).length > 0) {
                        var $page = $(contentDiv).find("#" + options.targetPage);
                        $("#" + options.targetPage).empty();
                        $("#" + options.targetPage).append($page.html());
                    }
                    contentDiv.remove();
                } else if (options.targetContent) {
                    var contentDiv = $('<div/>');
                    contentDiv.html(responseText);
                    if ($(contentDiv).find('#' + options.targetContent).length > 0) {
                        var $tbody = $(contentDiv).find('#' + options.targetContent);
                        $('#' + options.targetContent).empty();
                        $('#' + options.targetContent).append($tbody.html());
                    }
                    if ($("#contentPage").length > 0) {
                        $("#contentPage").empty();
                    }
                    if ($(contentDiv).find('#msgObj').length > 0) {
                        var $resultMsgObj = $(contentDiv).find('#msgObj');
                        if ($.trim($resultMsgObj.text()) != "") {
                            $('#resultMsg').slideDown();
                        }
                    }
                    contentDiv.remove();
                } else {
                    // See if a selector was specified
                    _self.html(selector ?
                    // Create a dummy div to hold the results
                    jQuery("<div>")
                    // inject the contents of the document in, removing
                    // the scripts
                    // to avoid any 'Permission Denied' errors in IE
                    .append(responseText.replace(rscript, ""))

                    // Locate the specified elements
                    .find(selector) :

                    // If not, just inject the full result
                    responseText);
                }
            }

            if (fn) {
                _self.each(fn, [responseText, status, jqXHR]);
            }
        };
        ajaxHelper.quickLoad(url, params, fn, options);
    }
});