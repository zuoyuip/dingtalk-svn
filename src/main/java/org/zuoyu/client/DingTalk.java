package org.zuoyu.client;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;

/**
 * 钉钉WebHook接口.
 *
 * @author zuoyu
 * @program dingtalk-svn
 * @create 2020-03-17 20:57
 **/
public class DingTalk {

  public static final String MARKDOWN = "markdown";

  /**
   * 生成Markdown
   *
   * @param title - 标题
   * @param text - 内容
   * @return com.dingtalk.api.request.OapiRobotSendRequest.Markdown
   */
  public static OapiRobotSendRequest.Markdown constructMarkdown(String title, String text) {
    OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
    markdown.setTitle(title);
    markdown.setText(text);
    return markdown;
  }


  /**
   * 向钉钉Webhook发送数据
   * @param accessToken - access_token
   * @param markdown - markdown格式文本
   * @return - com.dingtalk.api.response.OapiRobotSendResponse
   * @throws ApiException - 请求异常
   */
  public static OapiRobotSendResponse robotSendRequest(String accessToken,
      OapiRobotSendRequest.Markdown markdown) throws ApiException {
    DingTalkClient client = new DefaultDingTalkClient(accessToken);
    OapiRobotSendRequest request = new OapiRobotSendRequest();
    request.setMsgtype(MARKDOWN);
    request.setMarkdown(markdown);
    return client.execute(request);
  }
}
