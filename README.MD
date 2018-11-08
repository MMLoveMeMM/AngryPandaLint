如何使用这个工程?<br>
lintjar为lintAPI 24版本支持的module自定义的lint开发<br>
lintjar25为lintAPI 25版本支持的module自定义的lint开发<br>
lintAPI 24版本和lintAPI 25版本类和接口都有所变更,lintAPI 24网上资源相对较多,lintAPI 25开发难度相对较低<br>

编译lintjar和lintjar25 是通过gradle assemble分别进行编译,编译过后生成lintjar.jar和lintjar25.jar<br>
如果使用lintAPI 24版本的,将lintjar.jar直接拷贝到用户目录下的.android/lint目录下,如果.android没有lint<br>
目录,手动创建一个lint目录,将文件拷贝进去.<br>
然后在工程根目录下终端中执行gradlew lint命令行,等待片刻,将会在app/build/report下面生成lint_result.html<br>
报告,打开报告就可以看到所有的注意事项;<br>
另外一个办法就是就是再新建一个android module,参考工程中的lintaar,在lintaar修改build.gradle文件将lintjar.jar<br>
引入到module,然后再通过app工程的build.gradle引入lintaar module :<br>
compile project(path: ":lintaar")<br>
这样在编译app工程的时候就会执行lint检查,同样生成html文件报告.<br>
该工程默认情况下是默认的lint和自定义的lint并存的,两个都会检查代码是否符合规范.<br>
<br>
<br>
美团技术后面看看,大致思路都是差不多,主要想借鉴他们plugin那一块的实现方式<br>

