/**
 * system.js
 * author:liwz
 * create time 8.17
 * 1、select_subPop
 * 2、popUp弹出层
 */

$(function(){
    oSystem = {
        select_sub:function(){
            var aSelect = $(".basic_select"),
                aSelectDevice = $(".basic_select_device"),   
                aSelectEquipType = $(".basic_select_EqupType"),
                aSubItemEquipType = $(".sub_pop_EqupType span"),
                aSelectCutter = $(".basic_select_cutter"),
                aSubItemCutter = $(".sub_pop_cutter span"),
                aSubItemDevice = $(".sub_pop_device span"),
                aSubItem = $(".sub_pop span") ;
            
            aSelect.on("click",function(){
                var popUp = $(this).children(".sub_pop");
                if(popUp.is(":hidden")){
                    popUp.show();
                }
                return false;
            });

            aSelectEquipType.on("click",function(){
                var popUp = $(this).children(".sub_pop_EqupType");
                if(popUp.is(":hidden")){
                    popUp.show();
                }
                return false;
            });
            
            
            aSelectCutter.on("click",function(){
                var popUp = $(this).children(".sub_pop_cutter");
                if(popUp.is(":hidden")){
                    popUp.show();
                }
                return false;
            });
            
            aSelectDevice.on("click",function(){
                var popUp = $(this).children(".sub_pop_device");
                if(popUp.is(":hidden")){
                    popUp.show();
                }
                return false;
            });
            
            
            aSubItemEquipType.on("click",function(){
                var val = $(this).text(),
                oWrap = $(this).parents(".basic_select_EqupType").find(".basic_val_EqupType");
		        var idVal = $(this).attr("id");
		        oWrap.text(val);
		        $("#equipTypeId").val(idVal);
		        $(this).parent('.sub_pop_EqupType').hide();
		        return false;
            });
            aSubItemCutter.on("click",function(){
                var val = $(this).text(),
                oWrap = $(this).parents(".basic_select_cutter").find(".basic_val_cutter");
		        var idVal = $(this).attr("id");
		        oWrap.text(val);
		        $("#equitId").val(idVal);
		        $(this).parent('.sub_pop_cutter').hide();
		        return false;
             });
            
            
            aSubItemDevice.on("click",function(){
                var val = $(this).text(),
                        oWrap = $(this).parents(".basic_select_device").find(".basic_val_device");
                oWrap.text(val);
                $(this).parent('.sub_pop_device').hide();
                return false;
            });
            
            
            aSubItem.on("click",function(){
                var val = $(this).text(),
                        oWrap = $(this).parents(".basic_select").find(".basic_val");
                var idVal = $(this).attr("id");
                oWrap.text(val);
                $("#equitId").val(idVal);
                $(this).parent('.sub_pop').hide();
                return false;
            });
            $(document).on("click",function(){
                $(".sub_pop").hide();
                $(".sub_pop_EqupType").hide();
                $(".sub_pop_device").hide();
                //return false;
            })
        },
        popUp:function(){
            var _this = this,
                oPopBtn = $(".pop_btn"),
                oPopUp = $(".popUp"),
                oClose = $(".close_t"),
                oCloseUser = $(".close_t_user");
            oPopBtn.on("click",function(){
                if(oPopUp.is(":hidden")){
                    _this.show();
                }
            });
            oClose.on("click",function(){
                _this.hide();
            });
            oCloseUser.on("click",function(){
                _this.hideUserPop();
            });

        },
        hide:function(){
            var oMask = $("#mask"),
                    oPop = $(".popUp");
            oMask.hide();
            oPop.hide();
        },
        hideUserPop:function(){
            var oMask = $("#maskUser"),
                    oPop = $(".popUpUser");
            oMask.hide();
            oPop.hide();
        },
        show:function(){
            var oMask = $("#mask"),
                    oPop = $(".popUp");
            oMask.show();
            oPop.show();
        },
        showUserPop:function(){
            var oMask = $("#maskUser"),
                    oPop = $(".popUpUser");
            oMask.show();
            oPop.show();
        }
    };
    oSystem.select_sub();
    oSystem.popUp();
});