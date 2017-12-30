/*H-ui.admin.js v2.0 date:15:42 2014-11-24 by:guojunhui*/
/*获取顶部选项卡总长度*/
function tabNavallwidth(){
	var taballwidth=0,
		$tabNav = $(".acrossTab"),
		$tabNavWp = $(".Hui-tabNav-wp"),
		$tabNavitem = $(".acrossTab li"),
		$tabNavmore =$(".Hui-tabNav-more");
	if (!$tabNav[0]){return;}
	$tabNavitem.each(function(index, element) {
        taballwidth+=Number(parseFloat($(this).width()+60));
    });
	$tabNav.width(taballwidth+25);
	var w = $tabNavWp.width();
	if(taballwidth+25>w){
		$tabNavmore.show();
	}
	else{
		$tabNavmore.hide();
		$tabNav.css({left:0});
	}
}


/*=======================================================*/
$(function(){
	/*菜单处于当前状态*/
    var webSite ="";
    var loc=location.href;var url = loc.replace(webSite,"");
    $(".treeview-menu ul li").each(function(){
		var current = $(this).find("a");
		$(this).removeClass("current");
		if(url == $(current[0]).attr("href")){
			$(this).addClass("current");
		}
	});
	
	$("#nav-toggle").click(function(){
		$(".Hui-aside").slideToggle();	
	});	
	$(".treeview-menu dd li a").click(function(){
		if($(window).width()<768){
			$(".Hui-aside").slideToggle();
		}
	});
	
	$(".dislpayArrow a").click(function(){
		if($(".Hui-aside").is(":hidden")){
			$(".Hui-aside").show();$(this).removeClass("open");
			$(".Hui-article,.dislpayArrow").css({"left":"200px"});
		}else{
			$(this).addClass("open");
			$(".Hui-aside").hide();
			$(".Hui-article,.dislpayArrow").css({"left":"0"});
		}
	});

	/*选项卡导航*/
	var topWindow=$(window.parent.document);
	$(".sidebar-menu .treeview-menu a").click(function(){
		var bStop=false;
		var bStopIndex=0;
		var _href=$(this).attr('_href');
		var _titleName=$(this).html();
		var show_navLi=topWindow.find("#min_title_list li");
		show_navLi.each(function() {
			if($(this).find('span').attr("data-href")==_href)
			{
				bStop=true;
				bStopIndex=show_navLi.index($(this));
				return false;	
			}
		});
		if(!bStop){
			creatIframe(_href,_titleName);
			min_titleList();	
		}
		else{
			show_navLi.removeClass("active").eq(bStopIndex).addClass("active");
			var iframe_box=topWindow.find("#iframe_box");
			iframe_box.find(".show_iframe").hide().eq(bStopIndex).show();	
		}
	});
	min_titleList()
	function min_titleList(){
		var show_nav=topWindow.find("#min_title_list");
		var aLi=show_nav.find("li");
	};
	function creatIframe(href,titleName){
		var show_nav=topWindow.find('#min_title_list');
		show_nav.find('li').removeClass("active")
		var iframe_box=topWindow.find('#iframe_box');
		show_nav.append('<li class="active"><span class="menu-title"  data-href="'+href+'">'+titleName+'</span><i></i><em></em></li>');
		tabNavallwidth();
		var iframeBox=iframe_box.find('.show_iframe')
		iframeBox.hide();
		iframe_box.append('<div class="show_iframe"><div class="loading"></div><iframe frameborder="0" src="'+href+'"></iframe></div>');
		var showBox=iframe_box.find('.show_iframe:visible')
		showBox.find('iframe').hide().load(function(){
			showBox.find('.loading').hide();	
			$(this).show()
		});
	}

	var num=0;
	var oUl=$("#min_title_list");
	var hide_nav=$("#Hui-tabNav");
	//切换多窗口
	$(document).on("click","#min_title_list li",function(){
		var bStopIndex=$(this).index();
		var iframe_box=$("#iframe_box");
		$("#min_title_list li").removeClass("active").eq(bStopIndex).addClass("active");
		iframe_box.find(".show_iframe").hide().eq(bStopIndex).show();		
	});
	//关闭窗口
	$(document).on("click","#min_title_list li i",function(){
		var aCloseIndex=$(this).parents("li").index();
		$(this).parent().remove();
		$('#iframe_box').find('.show_iframe').eq(aCloseIndex).remove();	
		num==0?num=0:num--;
		tabNavallwidth();
	});
	// 菜单操作
	$(document).on("click",".contextMenuPlugin li",function(){

		var clikeIndex = parseInt($(this).attr('targetwi'));
		var sign = parseInt($(this).attr('sign'));
		// var aCloseIndex=$(this).parents("li").index();

		//sign  0:刷新,1:当前窗口,2:右边窗口,3:左边窗口,4:全部窗口
		switch(sign){
			case 0:{
				var iframe = $('#iframe_box').find('.show_iframe').eq(clikeIndex).children('iframe');
				$(iframe).attr('src', $(iframe).attr('src'));
			}break;
			case 1:{
				$('#min_title_list li').eq(clikeIndex).remove();
				$('#iframe_box').find('.show_iframe').eq(clikeIndex).remove();
				num==0?num=0:num--;
			}break;
			case 2:{

				$('#min_title_list li').each(function(){
					var Index=$(this).index();
					if (Index > clikeIndex) {
						$(this).remove();
						$('#iframe_box').find('.show_iframe').eq(Index).remove();
						num==0?num=0:num--;
					}


				});

			}break;
			case 3:{
				$('#min_title_list li').each(function(){
					var Index=$(this).index();
					if (Index <  clikeIndex && Index !=0) {
						$(this).remove();
						$('#iframe_box').find('.show_iframe').eq(Index).remove();
						num==0?num=0:num--;
					}


				});
			}break;
			case 4:{
				$('#min_title_list li').each(function(){
					var Index=$(this).index();
					if (Index !=0) {
						$(this).remove();
						$('#iframe_box').find('.show_iframe').eq(Index).remove();
						num==0?num=0:num--;
					}

				});
			}break;
		}
		tabNavallwidth();
	});


	$(document).on("dblclick","#min_title_list li",function(){
		var aCloseIndex=$(this).index();
		var iframe_box=$("#iframe_box");
		if(aCloseIndex>0){
			$(this).remove();
			$('#iframe_box').find('.show_iframe').eq(aCloseIndex).remove();	
			num==0?num=0:num--;
			$("#min_title_list li").removeClass("active").eq(aCloseIndex-1).addClass("active");
			iframe_box.find(".show_iframe").hide().eq(aCloseIndex-1).show();
			tabNavallwidth();
		}else{
			return false;
		}
	});
	tabNavallwidth();
	
	$('#js-tabNav-next').click(function(){
		num==oUl.find('li').length-1?num=oUl.find('li').length-1:num++;
		toNavPos()
	});
	$('#js-tabNav-prev').click(function(){
		num==0?num=0:num--;
		toNavPos();
	});
	
	function toNavPos(){
		oUl.stop().animate({'left':-num*100},100)
	}

	/*jQuery("#Hui-tabNav").slide({mainCell:".Hui-tabNav-wp #min_title_list li",prevCell:"#js-tabNav-prev",nextCell:"#js-tabNav-next",autoPage:false,effect:"leftLoop",vis:10,pnLoop:false,trigger:"click"});*/
 
});

