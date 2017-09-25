package com.ixxjd.spider;

import com.ixxjd.domain.Question;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author wb-ht244955
 * @Description
 * @Date 2017/9/25
 */
public class NewCoderQuestionPagePipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        Question question = (Question) resultItems.get("result");
        System.out.println(question.getTitle());
    }
}
