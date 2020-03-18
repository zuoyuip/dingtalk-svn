package org.zuoyu.tools;

import java.io.File;

/**
 * Markdown生成.
 *
 * @author zuoyu
 * @program dingtalk-svn
 * @create 2020-03-17 21:22
 **/
public class MarkdownUtil {

  /**
   * 提交说明的格式
   */
  private static final String COMMENT_PREFIX_FORMAT = "Comment：";

  private static final int COMMENT_LENGTH = 8;


  /**
   * 获取MarkDown文本
   *
   * @param markdown - Markdown对象
   * @return 文本类型
   */
  public static String toMarkDownText(MarkdownUtil.Markdown markdown) {
    return markdown.getProject() + markdown.getRev() + markdown.getDate() + markdown.getAuthor()
        + markdown.getCommit();
  }

  /**
   * Markdown数据模型
   */
  public static class Markdown {

    /**
     * 项目名称
     */
    private String project;

    /**
     * 提交版本
     */
    private String rev;

    /**
     * 提交时间
     */
    private String date;

    /**
     * 提交作者
     */
    private String author;

    /**
     * 提交信息
     */
    private String commit;


    public String getProject() {
      return "".equals(project) ? null : project;
    }

    public void setProject(String project) {
      if ("".equals(project)) {
        return;
      }
      int index = project.lastIndexOf(File.separator);
      int length = project.length();
      this.project = "# **项目名称**：" + project.substring(++index, length) + "\n";
    }

    public String getRev() {
      return "".equals(rev) ? null : rev;
    }

    public void setRev(String rev) {
      if ("".equals(rev)) {
        return;
      }
      this.rev = "> - #### **版本次数**：" + rev + "\n";
    }

    public String getDate() {
      return "".equals(date) ? null : date;
    }

    public void setDate(String date) {
      if ("".equals(date)) {
        return;
      }
      String[] dataSplit = date.split("/");
      String dateTime = String.format("%s %s %s", dataSplit[0], dataSplit[1], dataSplit[2]);
      this.date = "> - #### **提交时间**：" + dateTime + "\n";
    }

    public String getAuthor() {
      return "".equals(author) ? null : author;
    }

    public void setAuthor(String author) {
      if ("".equals(author)) {
        return;
      }
      this.author = "> - #### **提交人员**：" + author + "\n";
    }

    public String getCommit() {
      return "".equals(commit) ? null : commit;
    }

    public void setCommit(String commit) {
      if ("".equals(commit)) {
        return;
      }
      if (commit.contains(COMMENT_PREFIX_FORMAT)) {
        String comment = commit.substring(COMMENT_LENGTH + 1);
        this.commit = "> - #### **提交说明**：" + comment;
        return;
      }
      this.commit = "> - #### **提交说明**：" + commit + "（该提交说明不规范！）";
    }
  }
}
