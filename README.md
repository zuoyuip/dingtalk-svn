# dingtalk-svn

> 该项目为svn提交触发行为，向钉钉自定义机器人发布提交信息。
>
## 使用方法：

- SVN服务仓库下的`hooks`选项中中，定义了SVN提交中不同生命周期可执行的脚本。
这里使用`post-commit`。


- 将脚本内容写入：
```shell script
@echo off

::  POST-COMMIT HOOK
:: 
::  The post-commit hook is invoked after a commit.  Subversion runs
::  this hook by invoking a program (script, executable, binary, etc.)
:: named 'post-commit' (for which this file is a template) with the 
:: following ordered arguments:
::
::   [1] REPOS-PATH   (the path to this repository)
::   [2] REV          (the number of the revision just committed)
::

:: export LANG=zh_CN.UTF-8
:: OLDIFS=$IFS
:: IFS=$'\n'
:: SET LF=^

:: SVN版本库路径
SET REPOS=%1%

:: SVN版本号
SET REV=%2%

:: SVNLOOK
SET SVNLOOK=%SVNLOOK%


:: 提交的作者
FOR /F %%s in ('SVNLOOK author -r %REV% %REPOS%') do ( SET AUTHOR=%%s)


:: 提交的时间
SET TEMPDATE=%date:~0,10%-%date:~-1%-%time:~0,8%
SET FORMATDATE=%TEMPDATE: =%

:: 提交时的备注信息
@setlocal enableextensions enabledelayedexpansion

SET TEMPLOG=
FOR /F "tokens=*" %%i in ('SVNLOOK log -r %REV% %REPOS%') do (
    SET TEMPLOG=!TEMPLOG!'LINE'%%i
)
SET LOG=%TEMPLOG: ='SPACE'%

:: 当前脚本路径
SET BASEPATH=%C:\Program Files (x86)\VisualSVN Server\notification\%


cd %BASEPATH%

:: access_token
SET TOKEN="https://oapi.dingtalk.com/robot/send?access_token=abd8494d41a4da43315421771933fe64b0724aa2bfa9ef84e748150a38c22ccd"


:: 参数project：项目名（或项目路径）
:: 参数rev：版本
:: 参数date：时间
:: 参数author：提交者
:: 参数commit：说明
:: 参数token：access_token



java -jar  -Dproject=\"%REPOS%\" -Drev=\"%REV%\" -Ddate=\"%FORMATDATE%\" -Dauthor=\"%AUTHOR%\" -Dcommit=\"%LOG%\" -Dtoken=\"%TOKEN%\"  ./App.jar

exit
```

- 下载该[JAR文件](https://github.com/zuoyuip/dingtalk-svn/releases/download/v1.0/App.jar)，并放入当前目录下，即可执行。