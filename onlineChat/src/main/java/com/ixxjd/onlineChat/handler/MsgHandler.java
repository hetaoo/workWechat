package com.ixxjd.onlineChat.handler;


import com.ixxjd.cache.RedisCache;
import com.ixxjd.domain.Question;
import com.ixxjd.onlineChat.builder.TextBuilder;
import com.ixxjd.onlineChat.utils.JsonUtils;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSession;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.WxCpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

/**
 * * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {

    @Autowired
    private RedisCache redisCache;

    private static String[] SELECTIONS = {"A：","B：","C：","D：","E：","F：","G：","H：","I："};

    @Override
    public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage,
                                    Map<String, Object> context, WxCpService weixinService,
                                    WxSessionManager sessionManager) {
        WxSession session = sessionManager.getSession(wxMessage.getFromUserName());
        String buttonType = (String)session.getAttribute("buttonType");

        // 在线答题
        if (!wxMessage.getMsgType().equals(WxConsts.XML_MSG_EVENT) && buttonType != null && "onlineQuestion".equals(buttonType)) {
            String result = "";
            StringBuffer buffer = new StringBuffer();;

            if ("q".equalsIgnoreCase(wxMessage.getContent())) {
                session.removeAttribute("buttonType");
                result = "退出成功！";
                buffer.append(result);
            } else {
                String qid = (String)session.getAttribute("qid");

                if (StringUtils.isNotBlank(qid)) {
                    String[] answers = StringUtils.removeEnd(wxMessage.getContent(), ",").split(",");

                    Question question = (Question)redisCache.get(qid);
                    String[] questionAnswers = question.getAnswers();

                    for (String questionAnswer:questionAnswers) {
                        for (String answer:answers) {
                            if (answer.equalsIgnoreCase(questionAnswer)) {
                                result = "恭喜你回答正确！";
                            }else {
                                result = "回答错误，正确答案是： " + Arrays.toString(questionAnswers);
                            }
                        }
                    }

                    question = (Question)redisCache.randomData();
                    // 存入session
                    session.setAttribute("qid",question.getqId());

                    buffer.append(result).append("\n");
                    buffer.append(question.getTitle()).append("\n");
                    int i = 0;
                    for (String selection : question.getSelections()) {
                        buffer.append(SELECTIONS[i++]);
                        buffer.append(selection).append("\n");
                    }
                    buffer.append("回复[q]退出答题");
                } else {
                    result = "session已过期";
                }
            }

            return new TextBuilder().build(buffer.toString(), wxMessage, weixinService);
        }

        //TODO 组装回复消息
        String content = "收到信息内容：" + JsonUtils.toJson(wxMessage);

        return new TextBuilder().build(content, wxMessage, weixinService);

    }
}
