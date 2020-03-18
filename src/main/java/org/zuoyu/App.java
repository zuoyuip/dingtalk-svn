package org.zuoyu;

import com.dingtalk.api.request.OapiRobotSendRequest.Markdown;
import com.taobao.api.ApiException;
import org.zuoyu.client.DingTalk;
import org.zuoyu.tools.Builder;
import org.zuoyu.tools.MarkdownUtil;

/**
 * App.
 *
 * @author zuoyu
 * @program dingtalk-svn
 * @create 2020-03-17 20:57
 **/
public class App {

  public static void main(String[] args) {
    String project = System.getProperty("project");
    String rev = System.getProperty("rev");
    String date = System.getProperty("date");
    String author = System.getProperty("author");
    String commit = System.getProperty("commit");
    String token = System.getProperty("token");
    MarkdownUtil.Markdown customMarkdown = Builder.of(MarkdownUtil.Markdown::new)
        .with(MarkdownUtil.Markdown::setProject, project)
        .with(MarkdownUtil.Markdown::setRev, rev)
        .with(MarkdownUtil.Markdown::setDate, date)
        .with(MarkdownUtil.Markdown::setAuthor, author)
        .with(MarkdownUtil.Markdown::setCommit, commit)
        .build();
    String markDownText = MarkdownUtil.toMarkDownText(customMarkdown);
    Markdown markdown = DingTalk.constructMarkdown("代码提交", markDownText);
    try {
      DingTalk.robotSendRequest(token, markdown);
    } catch (ApiException e) {
      e.printStackTrace();
    }
  }
}
