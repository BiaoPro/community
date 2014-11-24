$(document).ready(function(){
	// 底部链接modal生成
	link();
	// 导航条hover下滑
	hoverOpen();
	// 回到顶部
	$(window).scroll(function(event) {
		var showHightOffset = $(".main-container").offset();
		if(showHightOffset==null){
			return;
		}
		var showHight = showHightOffset.top;
		var scrollTop = $(window).scrollTop(); 
		if(scrollTop > showHight) {
			$(".scrollTop").fadeIn(200);
		}else {
			$(".scrollTop").fadeOut(200);
		}
	});

	// 回到顶部变速效果
	$(".scrollTop").click(function(event) {
			var T = $(window).scrollTop();
			var Z = T;

			var t = setInterval(function(){
				if(T <= 0) {
					clearInterval(t);
				}else{
					T = parseInt((T/Z)*100) > 0 ? (T - parseInt((T/Z)*100) ): T - 1;
					$(window).scrollTop(T);
				}
			},10);
			return false;
	});


	//导航条hover下滑函数
	function hoverOpen(){
		$("li.dropdown").hover(function() {
			$(this).addClass('open')
			.children('a').eq(0).css("background","#E56745");
		}, function() {
			$(this).removeClass("open")
			.children('a').eq(0).css("background","");
		});
	}

// 底部链接modal生成函数
function link(){
	var $link = $('footer aside a') ;
	var linkOne = ['中共天河区委员会','http://www.thnet.gov.cn/','天河区人大','http://renda.thnet.gov.cn','天河区人民政府','http://www.thnet.gov.cn/','人民政协天河区委员会','http://zhengxie.thnet.gov.cn','中共天河区纪律检查委员会','http://jiwei.thnet.gov.cn/'],
	linkTwo = ['区委组织部','http://www.thdjw.cn','区委宣传部' ,'http://xchb.thnet.gov.cn/','天河报社','http://www.thnet.gov.cn/','区委统一战线工作部','http://www.thnet.gov.cn/','区委老干部局','http://thlg.thnet.gov.cn','区干部保健局','http://thgbbj.thnet.gov.cn/','区机构编制委员会办公室','http://bianban.thnet.gov.cn','区直属机关委员会','http://www.thnet.gov.cn/','区委党校','http://dangxiao.thnet.gov.cn','天河科技园管委会','http://new.thstp.cn/','区地方志编纂委员会办公室','http://thq.gd-info.gov.cn/','人力资源和社会保障局','http://ldbz.thnet.gov.cn/','区政府侨务和外事办公室','http://qwws.thnet.gov.cn/','区档案局','http://dangan.thnet.gov.cn','区机关事务管理局','http://jgswj.thnet.gov.cn','区出租屋管理工作领导小组办公室','http://chuzuwu.thnet.gov.cn/' ],
	linkThr = ['区人民武装部','http://www.thnet.gov.cn/','区委政法委','http://zhengfa.thnet.gov.cn/' ,'区政府法制办公室','http://www.thnet.gov.cn/','市公安局天河区分局','http://gongan.thnet.gov.cn/','区司法局' ,'http://sifa.thnet.gov.cn/','区人民法院','http://www.gzthfy.gov.cn' ,'区人民检察院','http://jiancha.thnet.gov.cn/' ,'区民政局','http://minzheng.thnet.gov.cn/'],
	linkFou = ['区总工会' ,'http://gonghui.thnet.gov.cn','共青团天河区委' ,'http://tqw.thnet.gov.cn' ,'区妇女联合会','http://fulian.thnet.gov.cn','区科学技术协会','http://kexie.thnet.gov.cn','区归国华侨联合会','http://www.thnet.gov.cn/','区工商业联合会、区总商会','http://gshlian.thnet.gov.cn/','区文学艺术界联合会','http://flac.thnet.gov.cn/','天河区慈善会' ,'http://thcs.thnet.gov.cn' ,'区残疾人联合会','http://canlian.thnet.gov.cn','中国国际贸易促进委员会广州天河区支会','http://ccpit.thnet.gov.cn' ],
	linkFiv = [ '区发展和改革局（粮食局、物价局）','http://fagai.thnet.gov.cn','区经济贸易局（对外贸易经济合作局、区旅游局）','http://jingmao.thnet.gov.cn' ,'区财政局','http://caizheng.thnet.gov.cn', '区交通局','http://jiaotong.thnet.gov.cn','区审计局','http://shenji.thnet.gov.cn','区统计局' ,'http://tj.thnet.gov.cn','区食品药品监督管理局','http://th.gzfda.gov.cn/' ,'区安全生产监督管理局','http://ajj.thnet.gov.cn/','区协作办公室' ,'http://gonghui.thnet.gov.cn','区供销合作社联合社','http://gxl.thnet.gov.cn/','市工商行政管理局天河分局','http://www.gzaic.gov.cn:8811/thfj/' ,'市天河区国家税务局','http://www.gd-n-tax.gov.cn/portal/site/site/portal/gdgz/tianhe/index.jsp','市天河区地方税务局' ,'http://gonghui.thnet.gov.cn','市质量技术监督局天河分局','http://gonghui.thnet.gov.cn','市烟草专卖局天河分局','http://gonghui.thnet.gov.cn'] ,
	linkSix = ['区科技和信息化局（区知识产权局）','http://www.thst.gov.cn/','区教育局','http://www.tianhe.org.cn/','区教育局教研室','http://www.thjy.org' ,'区文化广电新闻出版局（区版权局）','http://wenhua.thnet.gov.cn/' ,'区体育发展中心（体育局）','http://tiyu.thnet.gov.cn/' ,'区卫生局','http://weisheng.thnet.gov.cn/' ,'区卫生监督所' ,'http://thwjs.thnet.gov.cn/','区疾病预防控制中心' ,'http://jibingyufang.thnet.gov.cn/','区人口和计划生育局','http://jisheng.thnet.gov.cn/' ,'区妇幼保健院' ,'http://www.gzthfy.com','区中医医院（区中心医院）' ,'http://zxyy.thnet.gov.cn/','天河区人民医院','http://hszhyy.thnet.gov.cn/'],
	linkSev = ['区城改办','http://chenggai.thnet.gov.cn/lgbib/index.jsp','区环境保护局','http://epb.thnet.gov.cn/','区建设和水务局','http://jianshe.thnet.gov.cn/','区建设工程质量安全监督站' ,'http://jsza.thnet.gov.cn' ,'区农业和园林局' ,'http://nlsl.thnet.gov.cn/','区城市管理局' ,'http://csgl.thnet.gov.cn/' ,'区民防办（人防办）','http://renfang.thnet.gov.cn' ,'市国土资源和房屋管理局天河区分局' ,'http://tianhe.laho.gov.cn','市规划局天河分局','http://guihua.thnet.gov.cn' ,'市城市管理综合执法局天河分局','http://gzthcg.thnet.gov.cn/','区广州火车东站地区管理委员会办公室','http://dzdq.thnet.gov.cn/'],
	linkEig = ['沙河街','http://shahe.thnet.gov.cn/' ,'五山街','http://wushan.thnet.gov.cn/','员村街','http://yuancun.thnet.gov.cn/','车陂街','http://chebei.thnet.gov.cn/' ,'石牌街','http://shipai.thnet.gov.cn/','天河南街','http://tianhenan.thnet.gov.cn/','林和街','http://linhe.thnet.gov.cn/','沙东街','http://shadong.thnet.gov.cn','兴华街','http://xinghua.thnet.gov.cn','棠下街','http://tangxia.thnet.gov.cn/','天园街','http://tianyuan.thnet.gov.cn/','冼村街' ,'http://xiancun.thnet.gov.cn/','猎德街' ,'http://liede.thnet.gov.cn/','元岗街' ,'http://yuangang.thnet.gov.cn/' ,'黄村街' ,'http://huangcun.thnet.gov.cn/','龙洞街','http://longdong.thnet.gov.cn/','长兴街','http://changxing.thnet.gov.cn','凤凰街','http://fenghuang.thnet.gov.cn/','前进街','http://qianjin.thnet.gov.cn/','新塘街','http://xintang.thnet.gov.cn/','珠吉街' ,'http://zhuji.thnet.gov.cn/' ] ;

	$link.click(function(){
		switch(this.innerHTML){
			case '五套班子' : 
			linkModal('五套班子',linkOne);
			break;
			case '区委/区政府系统':
			linkModal('区委/区政府系统',linkTwo);
			break;
			case '政法系统':
			linkModal('政法系统',linkThr);
			break;
			case '人民团体' :
			linkModal('人民团体',linkFou);
			break;
			case '计划/经济/商业系统' :
			linkModal('计划/经济/商业系统',linkFiv);
			break;
			case '科教文体卫系统' :
			linkModal('科教文体卫系统',linkSix);
			break;
			case '城建/城管系统' :
			linkModal('城建/城管系统',linkSev);
			break;
			case '街道办事处' :
			linkModal('街道办事处',linkEig);
			break;
			default:
			break;
		}
	});
}
//底部导航条链接modal生成
function linkModal(title,num){
	var $oBody = $('.modal-body');	
	var $oH = $('.modal-header h3');

	$oBody.empty();

	$oBody.append("<ul></ul>");
	$oH.html(title);

	var $oUl = $(".modal-body ul");

	for(var i = 0; i < num.length; i=i+2){
		var str = "<li><a title='" + num[i] + "' href='"+ num[i+1] +"'target='_blank'>"+ num[i] +"</a></li>"
		$oUl.append(str);
	}
}
});


