package com.ixxjd.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Administrator on 2017/9/23.
 */
public class Test {
   public static String html = "<!DOCTYPE html>\n" +
            "\n" +
            "<html>\n" +
            "\n" +
            "\t<head>\n" +
            "\t\t<meta charset=\"utf-8\">\n" +
            "\t\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n" +
            "\t\t<title>&#20851;&#20110;&#19979;&#38754;&#20195;&#30721; int[] x=new int[25]; &#25551;&#36848;&#27491;&#30830;&#30340;&#26159;&#65288;&#65289;_Java&#19987;&#39033;&#32451;&#20064;_牛客网</title>\n" +
            "\t\t<meta name=\"description\" content=\"&lt;p style=&quot;margin-left: 18.0pt;text-indent: -18.0pt;&quot;&gt;\n" +
            "  &lt;span&gt;&#20851;&#20110;&#19979;&#38754;&#20195;&#30721;&lt;/span&gt;\n" +
            "  &lt;span&gt;int[] x=new int[25];&lt;/span&gt;\n" +
            "  &lt;span&gt; &#25551;&#36848;&#27491;&#30830;&#30340;&#26159;&#65288;&#65289;&lt;/span&gt;\n" +
            "&lt;/p&gt;\n" +
            "&lt;p&gt;&lt;/p&gt;,Java&#19987;&#39033;&#32451;&#20064;,牛客网是IT求职神器,提供海量IT笔试题库,全面提升编程能力\">\n" +
            "\t\t<meta name=\"keywords\" content=\"Java专项练习,IT笔试,牛客网\">\n" +
            "\t\t<script>\n" +
            "\t\t\tvar _czc = _czc || [];\n" +
            "\t\t\t_czc.push([\"_setAccount\", \"1253353781\"]);\n" +
            "\t\t</script>\n" +
            "\t\t<link rel=\"stylesheet\" href=\"//static.nowcoder.com/nowcoder/1.2.869/stylesheets/styles/subject/exercise.css\" />\n" +
            "\t</head>\n" +
            "\n" +
            "\t<body>\n" +
            "\t\t<div class=\"nk-container     \">\n" +
            "\t\t\t<div class=\"nowcoder-header\">\n" +
            "\t\t\t\t<div class=\"header-main clearfix\">\n" +
            "\t\t\t\t\t<a class=\"nowcoder-logo\" href=\"/\" title=\"牛客网\"></a>\n" +
            "\t\t\t\t\t<ul class=\"nowcoder-navbar\">\n" +
            "\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t<a href=\"/\" class=\"nav-home\">首页</a>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t<a href=\"/contestRoom\" class=\"nav-exam\">题库</a>\n" +
            "\t\t\t\t\t\t\t<ul class=\"sub-nav\">\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"/contestRoom\">公司真题</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"/intelligentTest\">专项练习</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"/activity/oj\">在线编程</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"/kaoyan\">考研真题</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"/finalexam\">期末考题</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"/activity/topics\">精华专题</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"/questionCenter\">试题广场</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t</ul>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t<a href=\"/courses\" class=\"nav-exam\">课程</a>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t<a href=\"/ranking\" class=\"nav-ranking\">排行榜</a>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t<span class=\"ico-nav-new\"></span>\n" +
            "\t\t\t\t\t\t\t<a href=\"/recommend\" class=\"nav-discuss\">求职</a>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t<a href=\"/discuss\" class=\"nav-discuss\">讨论区</a>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t</ul>\n" +
            "\t\t\t\t\t<ul class=\"nowcoder-navbar nowcoder-other-nav\">\n" +
            "\t\t\t\t\t\t<li class=\"nav-search\">\n" +
            "\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"icon-search\">搜索</a>\n" +
            "\t\t\t\t\t\t\t<div class=\"nav-search-wrap\">\n" +
            "\t\t\t\t\t\t\t\t<form method=\"get\" action=\"/search\">\n" +
            "\t\t\t\t\t\t\t\t\t<label class=\"nav-search-ico icon-search js-search\"></label>\n" +
            "\t\t\t\t\t\t\t\t\t<input class=\"nav-search-txt\" name=\"query\" type=\"text\">\n" +
            "\t\t\t\t\t\t\t\t\t<input type=\"hidden\" name=\"type\" value=\"question\" />\n" +
            "\t\t\t\t\t\t\t\t\t<input type=\"submit\" class=\"nk-invisible\" />\n" +
            "\t\t\t\t\t\t\t\t</form>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t<div class=\"nav-search-tips\" style=\"display:none;\"></div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t<a href=\"/app\" class=\"icon-mobile-phone\">APP</a>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"nav-msg\">\n" +
            "\t\t\t\t\t\t\t<a class=\"icon-envelope\" href=\"/sns/message/9084877/conversation-list\" data-unread-conv=\"\">消息</a>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"profile-item\">\n" +
            "\t\t\t\t\t\t\t<a href=\"/profile\" class=\"nav-profile\">\n" +
            "\t\t\t\t\t\t\t\t<div class=\"img-box\"><img src=\"https://images.nowcoder.com/images/20170210/9084877_1486657404707_7C2C60506876716CCF0E706DB13D4511@0e_100w_100h_0c_1i_1o_90Q_1x\" /></div>\n" +
            "\t\t\t\t\t\t\t</a>\n" +
            "\t\t\t\t\t\t\t<ul class=\"sub-nav\">\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"/profile\" class=\"nav-profile-page\">个人主页</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"/profile/9084877/account\" class=\"nav-set\">帐号设置</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"/profile/9084877/resume\" class=\"nav-coins\">我的简历</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"nav-out nc-logout\">退出登录</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t</ul>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t</ul>\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t</div>\n" +
            "\t\t\t<div class=\"nk-main  clearfix\">\n" +
            "\t\t\t\t<div class=\"module-box subject-box\">\n" +
            "\t\t\t\t\t<div class=\"nowcoder-topic\">\n" +
            "\t\t\t\t\t\t<div class=\"subject-progress\">\n" +
            "\t\t\t\t\t\t\t<div class=\"progress\">\n" +
            "\t\t\t\t\t\t\t\t<div style=\"width:10%\" class=\"progress-bar\"></div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t<span class=\"progress-nums\">1/10</span>\n" +
            "\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"progress-time\" title=\"暂停\">\n" +
            "\t\t\t\t\t\t\t\t<i class=\"ico-time-control\"></i><span data-left=\"600000000\" data-time=\"0\" class=\"time-text\"></span>\n" +
            "\t\t\t\t\t\t\t</a>\n" +
            "\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t<div class=\"subject-title\">[单选题]</div>\n" +
            "\t\t\t\t\t\t<div class=\"subject-main\">\n" +
            "\t\t\t\t\t\t\t<div class=\"subject-content\">\n" +
            "\t\t\t\t\t\t\t\t<div class=\"subject-question\">\n" +
            "\t\t\t\t\t\t\t\t\t<p style=\"margin-left: 18.0pt;text-indent: -18.0pt;\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>关于下面代码</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>int[] x=new int[25];</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<span> 描述正确的是（）</span>\n" +
            "\t\t\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t\t\t<p></p>\n" +
            "\t\t\t\t\t\t\t\t\t<script type=\"text/javascript\">\n" +
            "\t\t\t\t\t\t\t\t\t\t(function() {\n" +
            "\t\t\t\t\t\t\t\t\t\t\tvar i, l, w = window.String,\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\ts = \"33,102,117,110,99,116,105,111,110,40,41,123,118,97,114,32,97,61,119,105,110,100,111,119,46,108,111,99,97,116,105,111,110,46,104,111,115,116,59,97,38,38,97,46,105,110,100,101,120,79,102,40,34,110,111,119,99,111,100,101,114,46,99,111,109,34,41,60,48,38,38,119,105,110,100,111,119,46,115,101,116,84,105,109,101,111,117,116,40,102,117,110,99,116,105,111,110,40,41,123,119,105,110,100,111,119,46,108,111,99,97,116,105,111,110,46,104,114,101,102,61,34,104,116,116,112,58,47,47,119,119,119,46,110,111,119,99,111,100,101,114,46,99,111,109,34,125,44,49,53,48,48,48,41,125,40,41,59\",\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\ta = s.split(\",\");\n" +
            "\t\t\t\t\t\t\t\t\t\t\tfor(s = \"\", i = 0, l = a.length; l > i; i++) s += w.fromCharCode(a[i]);\n" +
            "\t\t\t\t\t\t\t\t\t\t\teval(s);\n" +
            "\t\t\t\t\t\t\t\t\t\t})();\n" +
            "\t\t\t\t\t\t\t\t\t</script>\n" +
            "\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"subject-options\" data-id=\"94324\">\n" +
            "\t\t\t\t\t\t\t\t\t<label class=\"radio\">\n" +
            "<span class=\"icons\"></span>\n" +
            "<input type=\"radio\" data-toggle=\"radio\" value=\"94324\">\n" +
            "<pre>x[25]存放了数据&ldquo;\\0&rdquo;。</pre>\n" +
            "</label>\n" +
            "\t\t\t\t\t\t\t\t</a>\n" +
            "\t\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"subject-options\" data-id=\"94325\">\n" +
            "\t\t\t\t\t\t\t\t\t<label class=\"radio\">\n" +
            "<span class=\"icons\"></span>\n" +
            "<input type=\"radio\" data-toggle=\"radio\" value=\"94325\">\n" +
            "<pre>x[24] 存放了数据&ldquo;\\0&rdquo;。</pre>\n" +
            "</label>\n" +
            "\t\t\t\t\t\t\t\t</a>\n" +
            "\t\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"subject-options\" data-id=\"94326\">\n" +
            "\t\t\t\t\t\t\t\t\t<label class=\"radio\">\n" +
            "<span class=\"icons\"></span>\n" +
            "<input type=\"radio\" data-toggle=\"radio\" value=\"94326\">\n" +
            "<pre>若访问x[25]，程序将抛出异常。</pre>\n" +
            "</label>\n" +
            "\t\t\t\t\t\t\t\t</a>\n" +
            "\t\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"subject-options\" data-id=\"94327\">\n" +
            "\t\t\t\t\t\t\t\t\t<label class=\"radio\">\n" +
            "<span class=\"icons\"></span>\n" +
            "<input type=\"radio\" data-toggle=\"radio\" value=\"94327\">\n" +
            "<pre>x[1]访问此数组的第一个元素。</pre>\n" +
            "</label>\n" +
            "\t\t\t\t\t\t\t\t</a>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t<div class=\"subject-action clearfix\">\n" +
            "\t\t\t\t\t\t\t\t<div class=\"subject-opr\">\n" +
            "\t\t\t\t\t\t\t\t\t<span class=\"subject-opr-item\"><i class=\"ico-collect\"></i><a href=\"javascript:void(0);\" class=\"js-follow\" data-id=\"62864\">收藏本题</a></span>\n" +
            "\t\t\t\t\t\t\t\t\t<span class=\"subject-opr-item\"><i class=\"ico-mark\"></i><a href=\"javascript:void(0);\" class=\"js-shield\" data-id=\"62864\">屏蔽本题</a></span>\n" +
            "\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t<div class=\"subject-next\">\n" +
            "\t\t\t\t\t\t\t\t\t<form id=\"submitForm\" method=\"post\" action=\"/question/next?pid=7276315&qid=62864&tid=11377936\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<input type=\"hidden\" name=\"content\" id=\"answer\" />\n" +
            "\t\t\t\t\t\t\t\t\t\t<input id=\"aheadFinish\" type=\"submit\" name=\"button\" class=\"btn warning-btn\" value=\"提前交卷\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<input id=\"next\" type=\"submit\" name=\"button\" class=\"btn btn-primary\" value=\"下一题\">\n" +
            "\t\t\t\t\t\t\t\t\t</form>\n" +
            "\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t<!-- 展开的时候加class:open -->\n" +
            "\t\t\t\t\t\t<div class=\"answer-sheet-box\">\n" +
            "\t\t\t\t\t\t\t<a href=\"javascript:void(0)\" class=\"card-unfold\">展开答题卡</a>\n" +
            "\t\t\t\t\t\t\t<a href=\"javascript:void(0)\" class=\"card-fold\">收起答题卡</a>\n" +
            "\t\t\t\t\t\t\t<ul class=\"answer-sheet-num clearfix\">\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"answering-num\" data-qid=\"62864\">1</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" data-qid=\"69574\">2</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" data-qid=\"55462\">3</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" data-qid=\"15547\">4</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" data-qid=\"26134\">5</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" data-qid=\"14764\">6</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" data-qid=\"22468\">7</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" data-qid=\"14759\">8</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" data-qid=\"14700\">9</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" data-qid=\"25900\">10</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t</ul>\n" +
            "\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t<script>\n" +
            "\t\t\t\t\t\t\twindow.isLogin = true;\n" +
            "\t\t\t\t\t\t\twindow.globalInfo = {\n" +
            "\t\t\t\t\t\t\t\townerId: '9084877',\n" +
            "\t\t\t\t\t\t\t\townerName: 'Hetaoo',\n" +
            "\t\t\t\t\t\t\t\townerTinyHead: 'https://images.nowcoder.com/images/20170210/9084877_1486657404707_7C2C60506876716CCF0E706DB13D4511@0e_100w_100h_0c_1i_1o_90Q_1x',\n" +
            "\t\t\t\t\t\t\t\townerMainHead: 'https://images.nowcoder.com/images/20170210/9084877_1486657404707_7C2C60506876716CCF0E706DB13D4511@0e_200w_200h_0c_1i_1o_90Q_1x',\n" +
            "\t\t\t\t\t\t\t\townerEmail: 'i@hetaoo.cn',\n" +
            "\t\t\t\t\t\t\t\townerPhone: '',\n" +
            "\t\t\t\t\t\t\t\townerType: '1',\n" +
            "\t\t\t\t\t\t\t\tstatus: '0',\n" +
            "\t\t\t\t\t\t\t\thonorLevel: '6',\n" +
            "\t\t\t\t\t\t\t\tgender: '',\n" +
            "\t\t\t\t\t\t\t\tisCompleteInfo: true,\n" +
            "\t\t\t\t\t\t\t\tcanLoadInIframe: 'false'\n" +
            "\t\t\t\t\t\t\t};\n" +
            "\t\t\t\t\t\t\twindow.useNeteaseCaptcha = true;\n" +
            "\t\t\t\t\t\t</script>\n" +
            "\t\t\t\t\t\t<script type=\"text/javascript\">\n" +
            "\t\t\t\t\t\t\twindow.ncDeployImageRoot = \"//static.nowcoder.com/\";\n" +
            "\t\t\t\t\t\t\twindow.ncJsVersion = \"1.01.99\";\n" +
            "\t\t\t\t\t\t\twindow.ncJsPluginVersion = \"1.0.9\";\n" +
            "\t\t\t\t\t\t</script>\n" +
            "\t\t\t\t\t\t<script src=\"//static.nowcoder.com/nc/js/1.01.99/sea.js\" type=\"text/javascript\"></script>\n" +
            "\t\t\t\t\t\t<script src=\"//static.nowcoder.com/nc/js/1.01.99/nc.cpn.js\" type=\"text/javascript\"></script>\n" +
            "\t\t\t\t\t\t<script src=\"//static.nowcoder.com/nowcoder/1.2.869/javascripts/base.js\" type=\"text/javascript\"></script>\n" +
            "\t\t\t\t\t\t<script type=\"text/javascript\">\n" +
            "\t\t\t\t\t\t\tif(!window.seajs) {\n" +
            "\t\t\t\t\t\t\t\tvar oBody = document.body;\n" +
            "\t\t\t\t\t\t\t\tvar aChild = oBody.childNodes;\n" +
            "\t\t\t\t\t\t\t\tvar oDv = document.createElement('div');\n" +
            "\t\t\t\t\t\t\t\toDv.innerHTML = [\n" +
            "\t\t\t\t\t\t\t\t\t'<div style=\"text-align:center;\">',\n" +
            "\t\t\t\t\t\t\t\t\t'<div style=\"font-size:18px;margin:20px 0;font-weight: bold;\">',\n" +
            "\t\t\t\t\t\t\t\t\t'静态文件加载出错，请检查当前网络情况是否正常，或者按照下面步骤修改电脑的DNS',\n" +
            "\t\t\t\t\t\t\t\t\t'<br />推荐使用以下DNS: 223.5.5.5(首选) 和 223.6.6.6(备用)',\n" +
            "\t\t\t\t\t\t\t\t\t'</div>',\n" +
            "\t\t\t\t\t\t\t\t\t'<img style=\"border:1px solid #ccc;\" src=\"//ww2.sinaimg.cn/mw690/44b25cb0jw1f5vt92tsz1j20o03d97dt.jpg\" />',\n" +
            "\t\t\t\t\t\t\t\t\t'</div>'\n" +
            "\t\t\t\t\t\t\t\t].join('');\n" +
            "\t\t\t\t\t\t\t\taChild.length === 0 && oBody.appendChild(oDv);\n" +
            "\t\t\t\t\t\t\t\taChild.length > 0 && oBody.insertBefore(oDv, aChild[0]);\n" +
            "\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t\tseajs.use('nowcoder/1.2.869/javascripts/site/common/index');\n" +
            "\t\t\t\t\t\t\tseajs.use('nowcoder/1.2.869/javascripts/site/common/nav');\n" +
            "\t\t\t\t\t\t</script>\n" +
            "\t\t\t\t\t\t<span id='cnzz_stat_icon_1253353781' style=\"display:none;\"></span>\n" +
            "\n" +
            "\t\t\t\t\t\t<script>\n" +
            "\t\t\t\t\t\t\t(function(window, undefined) {\n" +
            "\t\t\t\t\t\t\t\tseajs.use('nowcoder/1.2.869/javascripts/site/question/dangxuan.js');\n" +
            "\t\t\t\t\t\t\t})(window);\n" +
            "\t\t\t\t\t\t</script>\n" +
            "\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t<input type=\"hidden\" id=\"jsQuestionInfo\" data-pid=\"7276315\" data-tid=\"11377936\" data-qid=\"62864\" data-total=\"10\" data-left=\"10\" />\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t\t<div class=\"fixed-menu\">\n" +
            "\t\t\t\t\t<ul>\n" +
            "\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"gotop js-nav-go-top\" title=\"回到顶部\"></a>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t<a class=\"fixed-wb\" target=\"_blank\" href=\"http://www.weibo.com/nowcoder\"></a>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t<a href=\"tencent://groupwpa/?subcmd=all&param=7B2267726F757055696E223A3135373539343730352C2274696D655374616D70223A313431333130373737387D0A\" class=\"qq\" title=\"QQ\"></a>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"wx\"></a>\n" +
            "\t\t\t\t\t\t\t<div class=\"wx-qrcode\">\n" +
            "\t\t\t\t\t\t\t\t<img src=\"//static.nowcoder.com/images/wx-rcode.jpg\" alt=\"二维码\" />\n" +
            "\t\t\t\t\t\t\t\t<p>扫描二维码，关注牛客网</p>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t<a href=\"/discuss/30\" class=\"feedback\" title=\"意见反馈\"></a>\n" +
            "\t\t\t\t\t\t\t<a href=\"/discuss/30\" class=\"feedback-letter\">意见反馈</a>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"qrcode\"></a>\n" +
            "\t\t\t\t\t\t\t<div class=\"wx-qrcode\">\n" +
            "\t\t\t\t\t\t\t\t<img src=\"//uploadfiles.nowcoder.com/app/android/app.png\" alt=\"二维码\" />\n" +
            "\t\t\t\t\t\t\t\t<p>下载牛客APP，随时随地刷题</p>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t</ul>\n" +
            "\t\t\t\t\t<div class=\"phone-qrcode\" style=\"display:none;\">\n" +
            "\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"qrcode-close\">x</a>\n" +
            "\t\t\t\t\t\t<img src=\"//uploadfiles.nowcoder.com/app/android/app.png\" alt=\"二维码\" style=\"width:70px;height:70px;\" />\n" +
            "\t\t\t\t\t\t<p>扫一扫下载牛客APP</p>\n" +
            "\t\t\t\t\t</div>\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t\t<div class=\"ft-wrap\">\n" +
            "\t\t\t\t\t<div class=\"ft-cont clearfix\">\n" +
            "\t\t\t\t\t\t<div class=\"ft-main\">\n" +
            "\t\t\t\t\t\t\t<ul class=\"ft-links\">\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"/html/aboutus\">关于我们</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"/html/joinus\">加入我们</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"/discuss/30\" target=\"_blank\">意见反馈</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"https://hr.nowcoder.com\">企业服务</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"/html/cooperation\">网站合作</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"/html/disclaimer\">免责声明</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"/html/links\">友情链接</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t</ul>\n" +
            "\t\t\t\t\t\t\t<ul class=\"webrights\">\n" +
            "\t\t\t\t\t\t\t\t<li><span style=\"color:#a9b8ca;\">浙ICP备14000860号-2</span></li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<span style=\"color:#a9b8ca;\">\n" +
            "<img src=\"//static.nowcoder.com/company/images/res/ghs.png\" style=\"width:18px;height:18px;\">\n" +
            "<a style=\"font-size:12px;\" class=\"ft-info-item\" target=\"_blank\" href=\"http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=11011402010078\">京公网安备 11011402010078号</a>\n" +
            "</span>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"javascript:void(0);\">牛客网&copy;2017 All Rights Reserved</a>\n" +
            "\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t</ul>\n" +
            "\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t<dl class=\"ft-web-info\">\n" +
            "\t\t\t\t\t\t\t<dt class=\"ft-web-name\">牛客网，程序员必备求职神器</dt>\n" +
            "\t\t\t\t\t\t\t<dd class=\"ft-info-item\">QQ群：169195721</dd>\n" +
            "\t\t\t\t\t\t\t<dd class=\"ft-info-item\">微 信：www_nowcoder_com\n" +
            "\t\t\t\t\t\t\t\t<a href=\"javascript:;\" class=\"btn btn-primary btn-xs\">关注\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"tooltip top\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<div class=\"tooltip-arrow\"></div>\n" +
            "\t\t\t\t\t\t\t\t\t\t<div class=\"tooltip-inner\"><img width=\"80\" src=\"//static.nowcoder.com//images/wx-rcode.jpg\" /></div>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t</a>\n" +
            "\t\t\t\t\t\t\t</dd>\n" +
            "\t\t\t\t\t\t\t<dd class=\"ft-info-item\">微 博：牛客网\n" +
            "\t\t\t\t\t\t\t\t<a href=\"http://weibo.com/nowcoder\" class=\"btn btn-primary btn-xs\" target=\"_blank\">关注\n" +
            "\t\t\t\t\t\t\t\t</a>\n" +
            "\t\t\t\t\t\t\t</dd>\n" +
            "\t\t\t\t\t\t</dl>\n" +
            "\t\t\t\t\t\t<div class=\"ft-app\">\n" +
            "\t\t\t\t\t\t\t<div class=\"ft-qrcode-box\">\n" +
            "\t\t\t\t\t\t\t\t<img width=\"128\" src=\"//uploadfiles.nowcoder.com/app/app_download.png\" />\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t<p>扫一扫，把题目装进口袋</p>\n" +
            "\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t</div>\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t\t<div class=\"ad-window-sm js-global-tips\" style=\"display:none;\">\n" +
            "\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"ad-close\">X</a>\n" +
            "\t\t\t\t\t<div class=\"ad-live-active\"></div>\n" +
            "\t\t\t\t\t<a class=\"link-green js-global-tips-href\" target=\"_blank\" href=\"\">点击查看>></a>\n" +
            "\t\t\t\t</div>\n" +
            "\n" +
            "\t\t\t</div>\n" +
            "\t\t\t<script>\n" +
            "\t\t\t\twindow.selected = {\n" +
            "\t\t\t\t\t\"content\": \"\"\n" +
            "\t\t\t\t};\n" +
            "\t\t\t\twindow.isIntelligentPaper = true;\n" +
            "\t\t\t\twindow.canPauseTime = true;\n" +
            "\t\t\t\twindow.isContest = false;\n" +
            "\t\t\t\twindow.leaveCount = 0;\n" +
            "\t\t\t\t// 问题信息\n" +
            "\t\t\t\twindow.questionInfo = {\n" +
            "\t\t\t\t\tpid: '7276315',\n" +
            "\t\t\t\t\ttid: '11377936',\n" +
            "\t\t\t\t\tqid: '62864',\n" +
            "\t\t\t\t\ttotal: '10',\n" +
            "\t\t\t\t\tleft: '10',\n" +
            "\t\t\t\t\ttype: '1'\n" +
            "\t\t\t\t};\n" +
            "\t\t\t</script>\n" +
            "\t</body>\n" +
            "\n" +
            "</html>";

    public static void main(String[] args) {
        Document root = Jsoup.parse(html);
        Elements topic = root.getElementsByClass("nowcoder-topic");

        Document document = Jsoup.parse(topic.html());
        Elements title = document.getElementsByClass("subject-title");

        Elements questions = document.getElementsByClass("subject-question");

        System.out.println(title.text() +" " + questions.text());

        Elements answers = document.getElementsByClass("subject-options");
        for (Element an : answers) {
            System.out.println(an.text());
        }
    }
}
