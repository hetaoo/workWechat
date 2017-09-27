package com.ixxjd.spider;

import com.ixxjd.cache.RedisCache;
import com.ixxjd.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author hetaoo
 * @Description 将结果存入缓存
 * @Date 2017/9/25
 */
@Component
public class NewCoderQuestionPagePipeline implements Pipeline {

    @Autowired
    private RedisCache redisCache;

    @Override
    public void process(ResultItems resultItems, Task task) {
        Question question = resultItems.get("result");
        redisCache.set(question.getqId(),question);
    }
}
