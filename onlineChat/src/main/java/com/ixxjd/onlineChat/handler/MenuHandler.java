package com.ixxjd.onlineChat.handler;

import com.ixxjd.cache.RedisCache;
import com.ixxjd.domain.Question;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSession;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpMessage;
import me.chanjar.weixin.cp.bean.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.WxCpXmlOutMessage;
import me.chanjar.weixin.cp.bean.WxCpXmlOutNewsMessage;
import me.chanjar.weixin.cp.bean.messagebuilder.TextCardBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MenuHandler extends AbstractHandler {

    @Autowired
    private RedisCache redisCache;

    private static String[] SELECTIONS = {"A：","B：","C：","D：","E：","F：","G：","H：","I："};

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
            if (wxMessage.getEventKey().equals("onlineQuestion")) {
                WxSession session = sessionManager.getSession(wxMessage.getFromUserName());
                session.setAttribute("buttonType","onlineQuestion");

                Question question = (Question)redisCache.randomData();
                // 存入session
                session.setAttribute("qid",question.getqId());

                StringBuffer buffer = new StringBuffer();
                buffer.append(question.getTitle()).append("\n");
                int i = 0;
                for (String selection : question.getSelections()) {
                    buffer.append(SELECTIONS[i++]);
                    buffer.append(selection).append("\n");
                }

                buffer.append("回复[q]退出答题");

                return WxCpXmlOutMessage.TEXT().content(buffer.toString())
                        .fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName())
                        .build();

            }
        }

        return WxCpXmlOutMessage.TEXT().content(msg)
                .fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName())
                .build();
    }

}
