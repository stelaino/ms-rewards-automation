# 简介
通过Selenium模拟Chrome浏览器使用必应"电脑搜索"和"移动设备搜索"操作，获取Microsoft Rewards

![image.png](doc\image\daily search details.png)
# 运行环境
## JDK
使用Java 8，其它版本预计也兼容（无推荐，作者使用[Alibaba Dragonwell](https://dragonwell-jdk.io/#/index)）
## ChromeDriver
查看当前Chrome版本（地址栏填入"chrome://version/"）后[自取](https://googlechromelabs.github.io/chrome-for-testing/last-known-good-versions-with-downloads.json)对应版本，关于ChromeDriver请查阅[GoogleChromeLabs](https://github.com/GoogleChromeLabs/chrome-for-testing)
# 使用
- 驱动行选择ChromeDriver驱动
- Cookie行填入访问Cookie
- 执行即执行搜索的次数
- "确认"后应用启动电脑和移动设备端模拟指定次数

![image.png](doc\image\app window.png)

![image.png](doc\image\demonstrate the effect.png)

# 备注
**Cookie获取:** 自行使用必应搜索，获取服务器请求中Header中的Cookie值

![image.png](doc\image\acquisition of cookies.png)