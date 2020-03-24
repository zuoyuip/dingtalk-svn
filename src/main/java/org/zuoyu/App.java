package org.zuoyu;

import com.dingtalk.api.request.OapiRobotSendRequest.Markdown;
import com.taobao.api.ApiException;
import org.zuoyu.client.DingTalk;
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
        String project = System.getProperty("project").replaceAll("\"", "");
        String rev = System.getProperty("rev").replaceAll("\"", "");
        String date = System.getProperty("date").replaceAll("\"", "");
        String author = System.getProperty("author").replaceAll("\"", "");
        String commit = System.getProperty("commit").replaceAll("\"", "");
        String token = System.getProperty("token").replaceAll("\"", "");
        String title = String.format("%s-%s", "代码提交", author);
        MarkdownUtil.Markdown customMarkdown = new MarkdownUtil.Markdown();
        customMarkdown.setProject(project);
        customMarkdown.setRev(rev);
        customMarkdown.setDate(date);
        customMarkdown.setAuthor(author);
        customMarkdown.setCommit(commit);
        String markDownText = MarkdownUtil.toMarkDownText(customMarkdown);
        Markdown markdown = DingTalk.constructMarkdown(title, markDownText);
        try {
            DingTalk.robotSendRequest(token, markdown);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

//  {
//    System.out.println("project:\t" + project);
//    System.out.println("rev:\t" + rev);
//    System.out.println("date:\t" + date);
//    System.out.println("author:\t" + author);
//    System.out.println("commit:\t" + commit);
//    System.out.println("token:\t" + token);
//    System.out.println("title:\t" + title);
//    System.out.println("markDownText:\t" + markDownText);
//  }
}
