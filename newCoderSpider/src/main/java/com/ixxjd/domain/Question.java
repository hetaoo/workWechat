package com.ixxjd.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author hetaoo
 * @Description
 * @Date 2017/9/25
 */
public class Question implements Serializable {

    private static final long serialVersionUID = -5101347807942234212L;

    /**
     * 问题id
     */
    private String qId;

    /**
     * 标题
     */
    private String title;

    /**
     * 选项
     */
    private List<String> selections;

    /**
     * 答案
     */
    private String[] answers;

    public String getqId() {
        return qId;
    }

    public void setqId(String qId) {
        this.qId = qId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getSelections() {
        return selections;
    }

    public void setSelections(List<String> selections) {
        this.selections = selections;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }
}
