package com.ixxjd.onlineChat.handler;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.WxCpXmlOutMessage;
import me.chanjar.weixin.cp.bean.WxCpXmlOutNewsMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MenuHandler extends AbstractHandler {

    @Override
    public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage,
                                    Map<String, Object> context, WxCpService weixinService,
                                    WxSessionManager sessionManager) {

        String msg = String.format("type:%s, event:%s, key:%s",
                wxMessage.getMsgType(), wxMessage.getEvent(),
                wxMessage.getEventKey());
        if (WxConsts.BUTTON_VIEW.equals(wxMessage.getEvent())) {
            return null;
        }

        if (WxConsts.BUTTON_CLICK.equals(wxMessage.getEvent())) {
            WxCpXmlOutNewsMessage.Item item = new WxCpXmlOutNewsMessage.Item();
            item.setDescription("click event test");
            item.setPicUrl("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
            item.setTitle("这是一个测试");
            item.setUrl("http://www.baidu.com");
            return WxCpXmlOutMessage.NEWS().addArticle(item).fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName())
                    .build();
        }

        return WxCpXmlOutMessage.TEXT().content(msg)
                .fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName())
                .build();
    }

}
