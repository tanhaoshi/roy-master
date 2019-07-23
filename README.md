# roy-master
track every bit of android 

because our company has encrypted the file , but the project , the folder , is not encrypted . so I wanted to use the project to record every
bit of android learning . so the project is just for recording my mistakes and learning experiences.


THE RECORDER

JAVA 中接口与抽象类的使用方式与区别.
http://www.importnew.com/12399.html

Android 一些常见的算法
<!-- https://www.jianshu.com/p/9648e8dd5bdb -->

Android 一些面试上及需要学习的知识重点
<!-- http://www.importnew.com/27326.html#comment-763344 -->

Android Studio Lint工具的使用 及解释
<!-- https://blog.csdn.net/luzhenyuxfcy/article/details/79398761 -->

JAVA synchronized 正确用法
<!-- https://www.jianshu.com/p/d53bf830fa09 -- >

Android 粘性问题 即粘性广播,粘性解释即,注册立即发送。

Android 位运算问题
<!-- https://www.jianshu.com/p/5f41b3cc1909 -->

Android 透明度问题
//设置颜色透明度
https://blog.csdn.net/pengguichu/article/details/72956372


一些面试题
LaunchMode运用的一些场景
一.LaunchMode总共分为4种模式. standTask 普通模式 signalTask 单栈任务模式  signalTop 顶栈模式  singleInstance创建新栈模式
              1.standard  普通模式 每次创建Activity的时候都会创建一个新的activity进入栈中.
              2.signalTask 单栈模式 每次创建Activity的时候会检测栈中是否存在该activity如果存在的话 会将该activity前面的所有activity给finish然后将其置于顶栈中。
              3.signalTop  栈顶复用模式 每次创建的时候会检测该activity是否存在顶栈 如果不存在的话 将从新创建 如果存在的话将调用onNewIntent方法.
              4.创建新栈模式 创建该activity的时候会重新创建一个新的栈来专门用来存放该activity 在出栈的时候和另外三个不同。
