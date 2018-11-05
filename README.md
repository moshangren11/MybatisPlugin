MybatisPlugin
=======
    在迭代到第6个版本，终于有时间来写一下使用教程了，1.0.6这个版本最大的变化在于界面美化与数据支持可存储可选择，可满足基础需求
    接下来就是修修改改，力求完善，如果有bug或者好的建议，希望大家能及时提出来。

## 开发插件的初衷
    完全是不想通过jar去生成文件，也不想集成到项目中，就想搞一个插件一劳永逸

## 如何找到插件
    在IDEA内：setting -> Plugins -> Browse Repositories，搜索 build mybatis File Plugin
    或者去web端插件库[下载离线版本](https://plugins.jetbrains.com/plugin/10765-build-mybatis-file-plugin)
    
## 如何使用插件
* 打开插件
  * 在IDEA内：setting -> Other Settings -> Mybatis Plugin
* 目录配置项
  * 删除当前目录配置页：点击按钮，可删除已显示的`目录配置项`
  * 目录配置页名称：
    * 给当前目录配置项设置一个名称，方便后续查找。比如：mybatis项目资源路径
    * 目录配置项包括：`目录配置页名称`、`java文件目录`、`资源文件目录`
    * 配置好各个配置后，点击apply确认使用该配置项，并且保存该配置项
    * 保存多个配置项后，可以在下拉列表进行选择使用。
    * 选择自己需要使用的配置项，点击Apply确认使用该配置项。
  * java文件目录：项目下的 src -> main -> java目录，主要存储mybatis生成的entity实体类文件和mapper接口文件
  * 资源文件目录：项目下的 src -> main -> resource目录，主要是存储mybatis映射xml文件
* 数据库配置项
  * 删除当前数据库配置页：点击按钮，可删除已显示的`数据库配置项`
  * 数据库配置页名称：
    * 给当前数据库配置项设置一个名称，方便后续查找。比如：mybatis项目数据库配置
    * 数据库配置项包括：`数据库配置页名称`、`数据库名称`、`ip`、`port`、`oracle/mysql`、`用户名`、`密码`
    * 配置好各个配置后，点击apply确认使用该配置项，并且保存该配置项
    * 保存多个配置项后，可以在下拉列表进行选择使用
    * 选择自己需要使用的配置项，点击Apply确认使用该配置项
  * 数据库名称：数据库名称
  * ip：数据库ip地址
  * port：数据库端口
  * oracle/mysql：数据库类型（目前仅支持oracle和mysql）
  * 用户名：数据库用户名
  * 密码：数据库密码
  * 连接测试：点击按钮，测试是否可以连接到数据库
* Apply：使用已显示配置，并保存或者更新数据
* 打开包名配置项：
  * Tools ->Build Mybatis File
  * 或者使用快捷键：Ctrl+Alt+9
* 包名配置项
  * 删除当前包名配置：点击按钮，可删除已显示的`包名配置项`
  * 包名设置页名称：
    * 给当前包名配置项设置一个名称，方便后续查找。比如：mybatis包路径设置
    * 目录配置项包括：`model包名`、`client包名`、`sqlmap包名`
    * 配置好各个配置后，直接数据表名，多个表名使用英文逗号隔开，点击创建
    * 创建成功后会保存该包名设置项
    * 选择自己需要使用的配置项，使用该包名配置项
  * model包名：mapper接口文件的包名，比如：com.test.mybatis.model
  * client包名：entity实体类文件和Example类文件的包名，比如：com.test.mybatis.client
  * sqlmap包名：mybatis映射xml文件的包名，比如：com.test.mybatis.sqlmap
## 注意事项
  * 建表时，字段名称建议用"_"分隔多个单词，比如:user_id，这样生成的entity，属性名称就会变成驼峰命名，即：userId
  * oracle中的nvarchar/nvarchar2，mybatis-generator会识别成Object型，建议不要用nvarchar2，改用varchar2
## 其他
个人博客：[https://www.ggwp.net.cn](https://www.ggwp.net.cn)
