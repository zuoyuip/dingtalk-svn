# dingtalk-svn

> 该项目为svn提交触发行为，向钉钉自定义机器人发布提交信息。
>
## 使用方法：

- SVN服务仓库下的`hooks`目录中，定义了SVN提交中不同生命周期可执行的脚本。
这里使用`post-commit.tmpl`。

- 复制`post-commit.tmpl`并重命名为`post-commit`。
```shell script
cp post-commit.tmpl post-commit
```

- 将脚本内容写入：
```shell script
#!/bin/sh

# POST-COMMIT HOOK
#
# The post-commit hook is invoked after a commit.  Subversion runs
# this hook by invoking a program (script, executable, binary, etc.)
# named 'post-commit' (for which this file is a template) with the 
# following ordered arguments:
#
#   [1] REPOS-PATH   (the path to this repository)
#   [2] REV          (the number of the revision just committed)
#   [3] TXN-NAME     (the name of the transaction that has become REV)
#

export LANG=zh_CN.UTF-8
# OLDIFS=$IFS
# IFS=$'\n'

# SVN版本库路径
REPOS="$1"

# SVN版本号
REV="$2"

# 事物名称
TXN_NAME="$3"

# SVNLOOK
SVNLOOK=/usr/bin/svnlook  


# 提交的作者
AUTHOR=$($SVNLOOK author -r $REV $REPOS)

# 提交的时间
DATE=$($SVNLOOK date -r $REV $REPOS)

# 修改的目录集合   
CHANGEDDIRS=$($SVNLOOK dirs-changed $REPOS)

# 提交时的备注信息
LOG=$($SVNLOOK log -r $REV $REPOS)


# 当前脚本路径
BASEPATH=$(cd `dirname $0`; pwd)


cd $BASEPATH

./notification.sh $REPOS $REV $TXN_NAME $AUTHOR $LOG $CHANGEDDIRS

# IFS=$OLDIFS
exit 0
```
- 同时在该目录下创建`notification.sh`文件，写入脚本内容：
```shell script
#!/bin/sh

# access_token
TOKEN="https://oapi.dingtalk.com/robot/send?access_token=xxx"

# SVN版本库路径
REPOS="$1"

# SVN版本号
REV="$2"

# 事物名称
TXN_NAME="$3"

# 提交的作者
AUTHOR="$4"

# 提交的时间
DATE=$(date "+%Y-%m-%d/%A/%H:%M:%S")

# 提交时的备注信息
LOG="$5"

# 修改的目录集合   
CHANGEDDIRS="$6"

# 参数project：项目名（或项目路径）
# 参数rev：版本
# 参数date：时间
# 参数author：提交者
# 参数commit：说明
# 参数token：access_token


# 当前脚本路径
BASEPATH=$(cd `dirname $0`; pwd)

cd $BASEPATH

java -jar -Dproject=\"$REPOS\" -Drev=\"$REV\" -Ddate=\"$DATE\" -Dauthor=\"$AUTHOR\" -Dcommit=\"$LOG\" -Dtoken=\"$TOKEN\" App.jar

exit 0
```

- 下载该[JAR文件](https://github.com/zuoyuip/dingtalk-svn/releases/download/v1.0/App.jar)，并放入当前目录下，即可执行。