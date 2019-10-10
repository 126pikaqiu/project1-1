# Project 1: 斗兽棋

![](https://cloud.githubusercontent.com/assets/6532225/18883792/44b26bbe-8517-11e6-8f14-896c01d77724.png)

## 项目目标

本项目中，同学们需要使用 Java 编程语言，结合课堂知识、lab 内容，实现一个简单的、可以在命令行下玩的魔塔小游戏，并按要求编写一份开发文档，介绍你的实现方式、开发思路等内容。

关于项目有任何疑问，可以在微信群提问

## 项目参考

### Project 1 样例：

> Project 1 只要求完成命令行版的斗兽棋。
> Project 2 将完成图形化界面。

在 Project 1 中，同学们需要完成的项目样例是 `example` 文件夹下的 `MagicTower.jar` 。

运行方式：

1. 下载至自己电脑中。
2. 在 `example` 文件夹中打开命令行。
3. 运行命令 `java -jar MagicTower.jar` 。

## 项目实现

### 一、地图的读取和输出

魔塔的基本要素是地图。我们首先需要完成地图的读取，并在命令行中输出地图。

#### 地图读取

魔塔的地图横十三列，纵十三行。

我们通过读取地图文件的方式来载入地图。地图文件都在map文件夹中，以第3层地图为例

##### 地形地图（2.txt）：

```
bbbbbbbbbbbbb
bbsdaqssanbqb
bnnbgbbbgbbfb
bpagadoodaaab
bubbfbsbsbbub
bmmbappbsbqab
boobaaabaeaib
bbfvaajbgbbsb
bbasdbbbgbbgb
bbafadarrraab
bbebubbbbdbhb
btapapbmpabvb
bbbbbbbbbbbbb
```

| 字母   | a    | b    | c    | d    | e    | f    |F    | g    | h    | i    | j    |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- |---- | ---- | ---- | ---- | ---- |
| 含义   | 空地   | 墙壁   | 绿史莱姆 | 红史莱姆 | 黑史莱姆 | 蝙蝠 | 大蝙蝠   | 骷髅人   | 骷髅士兵 | 上楼 | 下楼 |
| 地图输出   |    | 墙   | 绿 | 红 | 黑 | 蝙 | 大  | 骷   | 兵 | 上 | 下 |
| 输出颜色   |    | 紫   | 绿 | 红 | 黑 | 黑 | 黑  | 黑   | 黑 | 黑 | 黑 |

| 字母   | k   | l    | m    | n    | o    | p    |  q  |   r  | s    | 
| ---- | ---- | ---- | ---- | ---- | ---- | ---- |---- | ---- | ---- |
| 含义   | 攻击商店   | 防御商店   | 攻击宝石 | 防御宝石 | 小血瓶 | 中血瓶 | 大血瓶   | 黄钥匙   |黄门 |
| 地图输出   | 商   | 商   | 攻 | 防 | 瓶 | 瓶 | 瓶   | 钥   |门 |
| 输出颜色   | 红   | 蓝   | 红 | 蓝 | 红 | 蓝 | 黄   | 黄   |黄 |

| 字母   |t    | u    | v    | w    | x    | y    | z    | 
| ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | 
| 含义   |  蓝钥匙 | 蓝门 |红钥匙   | 红门   | 魔王 | 最终宝物 | 石头人|
| 地图输出   | 钥   | 门   |    钥   | 门   | 魔    | 宝 | 石 |
| 输出颜色   | 蓝   | 蓝   |   红   | 红    | 黑 | 黑 | 黑 |  
   



##### 地图读取方式：

以第三层地图为例，我们将 `map文件夹` 放到项目根目录下(在 IntelliJ IDEA 中，放到与 `src` 同级的目录下)。

首先，使用 `Scanner` 类来读取文件：

```java
Scanner scanner = new Scanner(new File("map\2.txt"));
```

然后，使用 `Scanner` 的 `nextLine()` 方法获取文件内容：

```java
String firstLine = scanner.nextLine();
```

执行完这行语句后，`firstLine` 的值为字符串 `"bbbbbbbbbbbbb"` ，即地形地图的第一行。

然后，我们再次使用 `nextLine()` 方法：

```java
String secondLine = scanner.nextLine();
```

这样我们就得到了地形地图的第二行，`secondLine` 的值是字符串 `"bbsdaqssanbqb"` 。

以此类推，我们可以获取到 `2.txt` 中的所有数据。使用相同的方法可以再获得其他层中的数据。

最后，根据得到的数据，我们可以将地图输出到命令行中。

#### 地图输出

##### 地图输出样例：

 ![](https://cloud.githubusercontent.com/assets/6532225/18883810/5cf840c2-8517-11e6-804c-2c0d016973ed.png)

> 注：图片截取自 IntelliJ ，推荐同学们在写代码时使用 IntelliJ 编写、运行程序。
> ##### 地图输出规则：

1. 地形输出：玩家不在地方，按前面表格输出，玩家所在地输出"勇"

##### 地图对齐：

注意全角空格和半角空格
### 二、游戏逻辑

#### 游戏规则

**魔塔的地图**
本次project魔塔的地图横十三列，纵十三行，共五层。

**魔塔的怪物**
本次project魔塔的怪物共九种，他们的名称，字符，攻击，防御，血量，击杀所得金钱，见下表

| 字母   |  c    | d    | e    | f    |F    | g    | h    | x    | z    |
| ---- | ---- | ---- | ---- | ---- |---- | ---- | ---- | ---- | ---- |
| 含义   | 绿史莱姆 | 红史莱姆 | 黑史莱姆 | 蝙蝠 | 大蝙蝠   | 骷髅人   | 骷髅士兵 | 魔王 | 石头人 |
| 地图输出   |绿 | 红 | 黑 | 蝙 | 大  | 骷   | 兵 | 魔 | 石 |
| 输出颜色   | 绿 | 红 | 黑 | 黑 | 黑  | 黑   | 黑 | 黑 | 黑 |
| 血量   | 50 | 80 | 200| 100|200   | 120   | 200 | 350| 70 |
| 攻击   | 20 | 30 | 45 | 35 | 60  | 70  | 100 | 150 | 60 |
| 防御   | 1 | 1 | 15 | 5 | 25  | 0   | 5 | 25 | 50 |
| 金钱   | 1 |2 | 5 | 3 | 8 | 5   | 8 | 20 | 8 |

**物品说明**
钥匙：指定颜色的钥匙开指定颜色的门,没有指定颜色的钥匙则无法进行开门操作
攻击宝石：拾取+2攻击力
防御宝石：拾取+2防御力
血瓶：恢复生命值，其中小血瓶  +50 生命，中血瓶  +100 生命，大血瓶  +250 生命

**人物属性说明**
生命值：角色生存的基本，为零时死亡，初始为1000，通过拾取血瓶可提升血量。
攻击力：对敌人造成伤害的能力，初始为10，通过商人处购买和拾取攻击宝石可提升攻击能力。
防御力：抵挡敌人攻击的能力，初始为10，通过商人处购买和拾取防御宝石可提升防御能力。
金币：可以在商人处购买攻击力与防御力，初始为0，击杀怪物获得金钱。

**人物属性说明**
生命值：角色生存的基本，为零时死亡
攻击力：对敌人造成伤害的能力
防御力：抵挡敌人攻击的能力
金币：可以在商人处购买攻击力与防御力

**战斗说明**
魔塔中的战斗采取我打你一下，你打我一下的回合制战斗模式，直到一方死亡。
每次攻击造成的伤害为：攻击方的攻击力-防御方的防御力
当主角的攻击力小于等于怪物的防御力，
或者预计损失超过当前生命值时，将判定主角无法击败怪物，此时主角无法向怪物发起战斗

> **注：**
>
> 本项目中，**魔塔胜负判定**只要求完成：
>
> 1. 取得最终宝物即获得胜利；



#### 游戏指令

在命令行中，我们无法使用鼠标来控制，需要通过读取键盘输入来控制勇士的移动。

wasd代表上下左右，f:调查怪物信息

有时按照规则可能不能执行指令，这时需要打印出不能执行的原因，让玩家重新输入指令。

样例：

 ![](https://cloud.githubusercontent.com/assets/6532225/18883835/77369402-8517-11e6-8960-3b52d76dacaa.png)

### 三、游戏功能

#### 帮助

玩家可以在游戏中任何时候输入`help`查看帮助。

样例：

![](https://cloud.githubusercontent.com/assets/6532225/18883902/baa6862a-8517-11e6-8dca-3f26bd755a50.png)

#### 退出

玩家可以输入`exit`退出游戏。

#### 重新开始

玩家可以输入`restart`来重新开始游戏。

#### 悔棋

玩家可以输入`undo`来悔棋。

样例：

![](https://cloud.githubusercontent.com/assets/6532225/18883920/cd567f00-8517-11e6-9f88-f543dc55b5af.png)

#### 取消悔棋

玩家可以输入`redo`来取消悔棋。

样例 (接上图)：

![](https://cloud.githubusercontent.com/assets/6532225/18883921/cdfea0cc-8517-11e6-9583-ba9e82458656.png)

## 项目评分

本项目满分100分，包括三个部分：基础部分、进阶部分和综合评价部分。其中，基础部分70分，进阶部分10分，综合评价部分20分。 

### 基础部分

| 基础部分                       | 分数   |
| -------------------------- | ---- |
| 游戏地图：游戏能够读取地图并输出初始地图       | 10   |
| 游戏地图：地图输出格式始终正确            | 5    |
| 游戏指令：分辨指令格式的对错             | 2    |
| 游戏指令：正确识别上下左右移动        | 3    |
| 游戏功能：帮助、退出                 | 2    |
| 游戏功能：重新开始                  | 3    |
| 游戏规则：玩家不能撞墙            | 3    |
| 游戏规则：玩家能按照规则移动至相邻空地        | 2    |
| 游戏规则：玩家能拾取血瓶，攻击防御宝石              | 3    |
| 游戏规则：玩家能在商人处正确购买攻击力和防御力 | 2    |
| 游戏规则：玩家能拾取钥匙，正确开门             | 5    |
| 游戏规则：正确实现上下楼逻辑         | 5    |
| 游戏规则：玩家能按f查看魔塔中怪物的信息    | 5    |
| 游戏规则：玩家能按规则正确消灭魔塔中的怪物          | 5    |
| 游戏规则：胜负判断          | 5    |
| 游戏运行总体正常，符合相关描述，不会异常退出     | 10   |
| 总分                         | 70   |

### 进阶部分

| 进阶部分            | 分数   |
| --------------- | ---- |
| 游戏功能：悔棋         | 6    |
| 游戏功能：取消悔棋       | 4    |
| 总分              | 10   |

### 综合评价部分

| 综合评价部分                                   | 分数   |
| ---------------------------------------- | ---- |
| 设计文档（包括但不限于程序结构设计与分析，主要函数的功能，简要描述如何使用你的程序，编程中遇到的问题和解决策略） | 8    |
| 代码风格（包括但不限于命名规范，缩进与换行，代码可读性）             | 6    |
| 面试情况（能否清晰地解释程序结构，能否回答助教的问题等）             | 5    |
| 意见与建议（包括但不限于对PJ1，PJ2，Lab，课堂），可随设计文档一起提交  | 1    |
| 总分                                       | 20   |

## 项目提交与面试

### 提交截止时间

本次课程项目提交截止时间为 **2016 年 11 月 12 日 23:59**。

建议同学们在截止时间前一周就将项目基本完成，以防来不及完成，或者来不及修正突然发现的 bug 。

### 提交方式

请提交源代码、文档。源代码应以项目的形式提交。如有必要可以提交其他材料。

在截止时间之前将提交材料压缩并上传到：

```
ftp://10.142.141.33/classes/19/191 程序设计(陈荣华)/WORK_UPLOAD/project1
```

压缩包请重命名为：`学号 + 姓名`。 如 `16302010001陈雷远.rar`，`16302010029谢东方.zip`。

如果发现之前提交的文件有问题，可以重新上传压缩包。ftp不允许删除或者覆盖文件，需要上传一个新的压缩包，命名格式为：`学号 + 姓名 + 次数`， 如`16302010002李云帆2.zip`。

### 迟交惩罚

每迟交一天，最终得分扣除20%。

如：11月14日 00:01 AM 提交，扣除20%，11月16日 11:30 PM 提交，扣除60%。

> 注：如果提交多次，以面试时选择的提交文件的提交时间为准。评分亦以此文件为准。

### 面试注意事项

本次课程项目面试时间为**2016年 11月 14日 下午第三四节课**，地点为机房，即Lab课组织面试。如有调整会提前通知。 

原则上面试时不允许现场Debug，请确保你的程序能正常运行。

### 抄袭惩罚

**严禁任何形式的抄袭。**

助教将检查每个同学的代码、文档等材料，如有发现抄袭现象，将严肃处理。

抄袭同学零分处理。被抄袭同学将视情况作出惩罚。