# componentProject
组件化 + MVP + MVVM




前言

组件化和插件化已经提出了很久了，到现在也是比较稳定的一种架构方案了，在三年前，组件化和插件提出来没多久，前公司就已经在项目中使用了，只是当时还只是菜鸟，没有资格参与到架构的建设中，只是在大佬搭好的架构中写一些业务代码。当时的做法基本上也和现在网上流行的大多数使用的方案是一致的。

最近花了半个月的时间自己从0到一的设计了一个完全组件化的架构的demo，当然里面有些使用的技术可能不是最合适的，但是我觉得对于要开始实现组价化或者学习组件多多少少都会有一些参考的价值。在搭建架构的前期，也参考了很多人的思想，但是当自己动手操作起来的时候，根本不是那么一回事，有些反而更加容易造成一些不必要的代码编写和更加容易产生bug，在开发阶段和发布阶段没有完全的统一，很容易出现问题，造成各种各样的问题。

比如说，现在网上很多方案都喜欢在gradle.properties 定义一个值，通过改变这个值来切换库模式和独立调试模式。这并没有什么问题。我们知道库模式是apply plugin: 'com.android.library' 而独立模式为apply plugin: 'com.android.application' 。举个例子，我们在平常开发的时候，有很多同学喜欢使用注解来减少findviewbyid。但是在库模式下你会发现这些会报错等问题。还有创建两份AndroidManifest。要维护两份清单文件，稍有不慎，在最后合并的时候就会出问题，这些都是隐藏的问题。

模块化，组件化，插件化的区别

我个人认为这几个都是一次次进化的过程。从开始无架构，所有代码都all in one 一快到我们把一些公共组件，业务抽取出来作为module，这些就是属于简单的模块化了。
只是说他们层次清晰了，但是耦合性还是很强。而组件化就是在模块化的基础上再次拆分，组件之间相互独立，可以独立运行，他们都是可以单独抽取出来作为SDK对外使用的。
插件化和组件化最大的区别就是插件化能够动态的修改，而组件化不行。

组件的划分

实现组件化不管是使用什么样的技术路径，都要考虑以下几个问题
代码隔离：怎么把一个完整的项目拆分成若干个有机的整体

组件单独调试：把项目拆分为若干个独立的有机整体，那么怎样把这些有机整体单独运行起来呢？

UI跳转：一个整体的项目，可以通过intent来进行跳转，但是组件之间是相互隔离的，怎么实现他们之间的跳转呢？包括数据的传递，UI的混合等等都是需要考虑的。

组件间按需依赖：在调试阶段，怎样把一个，两个...组件整合到一块来进行调试呢？那么项目开发完以后，怎么把所有的组件融合到一块呢？

组件的生命周期：我们的目标是可以做到对组件可以按需、动态的使用，因此就会涉及到组件加载、卸载和降维的生命周期。

组件间的通信：组件之间是没有相互依赖的，那怎么才能够让组件之间的数据实现共享？

资源隔离：组件之间相互独立，各个开发人员负责对应的模块，很容易出现资源命名出现相同，最后集成调试的时候出现冲突，这些又该怎么避免呢？
......

在这里提一下，组件化一个一时半会就能够完完全全实现的，他需要花很长的时间慢慢的进行拆分，也许今天设计的某个方案很不错，但是过一段时间以后，就会成为一个冗余代码块。

demo的架构

不同的项目划分的层都是不一样的，具体划分几层，怎么划分，完全取决于自己的项目结构，大小，公司开发人员的数量等。划分的粒度不宜过大，也不宜过小，合适自己的就是最好的。demo这样划分的目的是：我看过网上很多案例，module全部放出来到整个项目路径下，根本根本就分不清哪个是组件，哪个是公共组件，那些是第三方组件，很凌乱。

demo架构图

enter description here
企业微信截图_15917760546642.png

目前demo中直接划分为4层，分别是apps,component,basecomponent,baselib.
apps:主要包括宿主壳，调试壳。宿主壳和调试壳的本质是一样的，只是宿主壳是针对于所有的组件。而调试壳针对的某一个或几个组件。该层是不能有一点java代码，就只有一个AndroidManifest 和build.gradle

component：组件层，项目拆分出来的组件，全部放到该层中。这些组件都能够独立的运行小项目，组件之间不能有一点点的依赖关系。

basecomponent：该层主要放一些公共的组件，比如说多个组件共用某一个页面等。还可以放一些公共类，比如BaseActivity，BaseFragment等。具体在项目中放什么，取决于自己项目需要。但是要注意的是，千万不要什么都放，不然很容易造成该层的代码臃肿。放到该层的代码最好能够有人员review。

baselib:这一层主要放的是一些第三方的东西，比如说我们封装的网络请求，数据库，多媒体等等。

组件的创建

新组件的来源基本来自于两方面：第一：由于旧的组件过于臃肿，从旧组件中继续拆分出来形成新的组件。第二：新的功能作为一个新的组件添加进来。

无论是那种形式创建组件，在加入新的组件之前一定要明确该组件是做什么，是否需要与原有组件交互，是否存在与原有组件共用部分。其次拆分的粒度一定要保持好不宜太大，也不宜太小，具体的需要根据项目和开发人员决定。

新组件中架构的选择：目前比较比较常用的基本就是MVC，MVP，MVVM这三种，具体选择哪种取决于个人，我个人还是比较推荐MVVM的。为什么呢？

大家有没有发现，很多人升级了Android studio以后（具体从哪个版本开始，我也忘记了，我的是直接3.0 - > 3.5）很多东西都变成了AndroidX了，而这个AndroidX就是Google 提出的JetPack中一部分，同时还提供了LiveData + ViewModel + DataBinding来让我们快速的实现这个MVVM架构，同时数据的生命周期也就是ViewModel的生命周期和activity的生命周期系统直接帮我们处理好了，这对于我们来说便利了许多。

当然有些同学就是喜欢presenter，也是可以使用MVP的，这里推荐一个插件给大家，在创建MVP架构是事半功倍。GitHub地址：https://github.com/JessYanCoding/MVPArms/blob/master/MVPArms.md

在demo中，组件component_one 中实现一个一个mvp的例子，在component_two中实现额一个MVVP的例子，有需要的可以参考。

组件化开发

组件化开发往往会有多个开发人员，每个人的编码风格，命名风格都是不一样的，所以最好能够统一。很多人都知道阿里巴巴推出了一份编码格式的文档，但是我觉得没几个人去看，看了也记不住。所以这里推荐一个插件给大家:Alibaba Java Coding Guidelines 。AS上直接File -- > Settings -- > Plugins 直接进行搜索安装。这样在编码过程中哪里不合理，都会有提示出来。

代码隔离

前面说的，组件之间是相互隔离，都是相互独立的一个个体，组件A的存在与不存在都不能够影响到组件B，组件在单独调试中，即使访问不了其他组件，那也不能闪退，所以说这个边界一定要处理好。

组件单独调试

现在网上很多文章都是通过这样的形式来切换库模式和调试模式的。
在gradle.properties 中定义一个常量 isDebug ,true 为调试模式，false为库模式，然后通过这个常量在各个build.gradle中进行判断

if (isDebug.toBoolean()) {
apply plugin: 'com.android.application'
} else {
apply plugin: 'com.android.library'
}

sourceSets {
main {
if (isDebug.toBoolean()) {
manifest.srcFile 'src/main/manifest/AndroidManifest.xml'
} else {
manifest.srcFile 'src/main/AndroidManifest.xml'
}
}
}

基本都是这样写的，这样写也是没有问题的，demo的前期也试着这样写，但是后面发生的问题实在是多的不想解决。
第一：在调试模式下开发，如果使用了ButterKnife等第三方库注解来替代findViewById(),，换成apply plugin: 'com.android.library' 模式下，这些都是编译不过的，还得修改。

第二：一个组件要维护两份AndroidManifest文件，这个代价非常的大，稍有出错，那排查也是很麻烦的事情

所以通过这种方式进行切换模式在小一点的项目还行得通，但是在大的项目中，根本就不行。在demo中，只有一种模式，就是apply plugin: 'com.android.library' 而我们可以通过给module添加一个壳来实现调试，这样开发，基本就是调试和集成的时候都是library，出现问题，也能够及时的处理，不会等到最后才发现。

在demo中，壳程序
enter description here

壳程序中只有一个build.gradle和AndroidManifest两个文件，在AndroidManifest只需要添加一个入口，而所有的壳程序都是 apply plugin: 'com.android.application' 这样在组件还是library的模式下一样可以对组件进行调试。
这样做的好处就是可以百分百的在library模式下开发组件，不会出现切换模式时所产生的一系列问题。可靠性大大的增强。

多组件调试（组件按需依赖）

在demo中每个壳程序的build.gradle

dependencies {
implementation project(path: ':component:app')
implementation project(path: ':component:component_one')
implementation project(path: ':component:component_two')
}

需要调试几个组件就依赖几个组件。

UI跳转

demo中，组件之间是独立的，他们之间的跳转只要是通过ARouter 来进行页面之间的跳转的。
Android原生已经支持AndroidManifest去管理App跳转，为什么还需要这个路由来进行跳转呢？
我个人理解是这样的
第一：使用显示intent 进行跳转，会导致组件之间的耦合很严重，背离了我们组件化的目的了
第二：使用隐式跳转，可以是可以，但是并不是特别符合组件化开发，由于需要在AndroidManifest进行注册，管理起来比较麻烦，出了问题，比较难排查，在加上每个加上了Scheme的Activity都可以被打开，会存在一定风险。
第三：使用路由有降级功能，就是说即使页面不存在，也可以跳转到到另外一个页面做一些提示，用户体验更好
第四：路由有拦截功能，可以在跳转之前做一些判断，从而决定是否需要拦截。减少了不必要的代码编写。

ARouter

具体可以参考官方文档：https://github.com/alibaba/ARouter/blob/master/README_CN.md
ARouter的原理：
1、通过apt技术，利用反射注解编译生成类，封装目标界面的类信息。
2、初始化时，把编译生成的类通过key-value的方式存储到ARouter中。
3、操着着通过key拿到对应的类信息。
4、把操作者的信息和目标界面信息进行结合关联起来，实现跳转。

组件的生命周期

组件之间是相互独立的，每一个单独的组件都是可以独立运行的。而一个应用application只有一个，如果在每一个组件都设置一个application，显然是不合理的。但是如果组件单独运行，需要初始化一些第三方组件的东西，该怎么弄呢？在demo中，主要是通过反射的形式。

我们在每一个组件的AndroidManifest文件中添加

<meta-data
  android:name="com.noahedu.component_one.ComponentOneApp"
  android:value="ModuleConfig" />
然后通过getPackageManager().getApplicationInfo 来获取到meta-data 值，在通过反射拿到对应的实例对象，回调对应的接口。具体可以参考basecomponent_common中的appproxy包下的实现方式。当然，现在网上也有很多通过注解的形式能够自动注入的形式实现，看自己的选择了。

组件间通信

组件间通信的方式有很多，比如SharedPreferences ，文件，广播，EventBus，AIDL这些都是可以的。但是SharedPreferences ，文件 在线程安全方面并不好，同时改，同时取，很容易出现问题。

现在网上也有很多方案用的是EventBus来进行数据传递，EventBus有一个不太好的地方就是有点难找订阅方写在哪里。当然主要看自己项目怎么选择。如果选择使用EventBus来说为数据传递，那么这里推荐一个插件 Eventbus3 Intellij Plugin 这个插件可以快速定位出发送方和订阅方位置。

demo中使用的方案是将接口下层到base层

定义接口

public interface IGetComponOneDataService {

    void setData(String str);

    String getData();

}
public class ServiceFactory {
    private static ServiceFactory instace = null;
    private IGetComponOneDataService iGetComponOneDataService;

    public static ServiceFactory getInstance(){
        if(null == instace){
            synchronized (ServiceFactory.class){
                if(null == instace){
                    instace = new ServiceFactory();
                }
            }
        }
        return instace;
    }

    public void setService(IGetComponOneDataService service){
        this.iGetComponOneDataService = service;
    }


    public IGetComponOneDataService getiGetComponOneDataService(){
        return iGetComponOneDataService;
    }

}
然后各个有需要的组件可以实现IGetComponOneDataService 接口，通过暴露出来的接口来获取数据。

这种方案如果维护不好很容易导致代码臃肿，什么代码都往下沉，所以选择这种方案，需要合理的review

资源隔离

组件之间都是不同的开发人员维护，就很容易在资源上命名出现相同的，最后合包的时候就出现冲突。所以在命名上面就要有一个规则。

给 Module 内的资源名增加前缀, 避免资源名冲突

resourcePrefix "${project.name.toLowerCase().replaceAll("-", "_")}_"
相当于在各个组件命名中加一个前缀，如果没有添加，则会有提示出来。这样就避免了资源冲突的问题了。

组件间依赖

gradle升级到3.0+ 以后，依赖就是使用implementation,api ,runtionOnly,compileOnly 进行依赖控制了

implementtation 短依赖。比如依赖module A 那么就是只能用到module A里面的东西。module A 依赖其他的第三方都不能使用。

api 长依赖。和implementation相反，依赖了module A ，那么module A依赖的第三方也都能使用。

runtimeOnly ：只有大包成apk的时候才会被大包进去。

compileOnly: 只有在本地编译时有效，大包不会打进去。

组件维护

组件维护

随着项目需求的增加，module会越来越来，对于开发人员来说，维护起来会越来越困难，特别是人手不足的情况下，所以说针对组件的划分，一定要合理。太大，那没有意义，太小，成本也大。所以粒度要合理。从需求上来看，如果某个需求相对独立，和已有的需求关联不大，那么完全可以作为一个单独组件。具体需要从需求上分析。

apk体积的优化

参考：https://blog.csdn.net/cchp1234/article/details/77750428

enter description here
企业微信截图_1591777839721.png
原因：
1、屏幕适配问题，增加多种资源文件和图片
2、机型适配问题
3、Android 版本兼容问题
4、各种开发框架，第三方lib引入
5、炫酷的UI、UE效果
6、冗余代码等

assets 优化

1、字体文件：可以使用字体资源文件编辑神器Glyphs进行压缩删除不需要的字符从而减少APK的大小。
2、WEB页面：可以考虑使用7zip压缩工具对该文件进行压缩，在正式使用的时候解压。
3、某些图片：可以使用tinypng进行图片压缩， 目前tinypng已经支持png和jpg图片、.9图的压缩。
4、将缓存文件放到服务端，通过网络下载。
5、对无用的音频文件删除。

res优化

1、对于一些不必要的设备尺寸，不必要全部（主要看产品需求）；
2、对资源文件，主要是图片资源进行压缩，压缩工具是 ImageOptim；
3、一些UI效果可以使用代码渲染替代图片资源；
4、资源文件的复用，比如两个页面的背景图片相同底色不同，就可以复用背景图片，设置不同的底色；
5、使用 VectorDrawable 和 SVG 图片来替换原有图片。如果提升 minSdkVersion 效果会更好，minSdkVersion 21 以上直接使用矢量图，可以不用为了兼容低版本而另外生成 .png 图片文件。使用SVG不用考虑屏幕适配问题，体积非常小;
6、如果raw文件夹下有音频文件，尽量不要使用无损的音频格式，比如wav。可以考虑相比于mp3同等质量但文件更小的opus音频格式。
7、能不用图片的就不用图片，可以使用shape代码实现。
8、使用 WEBP。较大 png、jpg文件转化为 webp 文件，这个 AS 自带，在图片（单个）或包含图片的文件夹（批量）上右击选择 Convert to WebP 即可。Webp 无损图片比 PNG 图片的 size 小 26%。Webp 有损图片在同等 SSIM（结构化相似）质量下比 JPEG 小 25-34% 。无损Webp支持透明度（透明通道）只占22%额外的字节。如果可以接受有损RGB压缩，有损Webp也支持透明度，通常比PNG文件size小3倍。

帧动画优化

一个帧动画会有多张图片组成，那么很容易导致apk的体积增大。这里推荐使用SVGA来展示动画。
SVGA具体使用参考：https://github.com/svga/SVGAPlayer-Android/blob/master/readme.zh.md

Heading

强烈推荐：利用AndResGuard资源压缩打包工具 这个打包工具和java代码混淆有点类似 只是AndResGuard 混淆的是资源文件而已

使用方法：https://github.com/shwenzhang/AndResGuard

lib目录优化

1、减少不必要的.so文件。比如一些第三方SDK要求引入.so文件，通常还很大。
2、选择性删除 arm64-v8a、armeabi-v7a、armeabi、x86下的so文件：

mips / mips64: 极少用于手机可以忽略
x86 / x86_64: x86 架构的手机都会包含由 Intel 提供的称为 Houdini 的指令集动态转码工具，实现对 arm.so 的兼容，再考虑 x86 1% 以下的市场占有率，x86 相关的 .so 也是可以忽略的
armeabi: ARM v5 这是相当老旧的一个版本，缺少对浮点数计算的硬件支持，在需要大量计算时有性能瓶颈
armeabi-v7a: ARM v7 目前主流版本
arm64-v8a: 64 位支持。注意：arm64-v8a是可以向下兼容的，但前提是你的项目里面没有arm64-v8a的文件夹。如果你有两个文件夹armeabi和arm64-v8a两个文件夹，armeabi里面有a.so 和 b.so,arm64-v8a里面只有a.so，那么arm64-v8a的手机在用到b的时候发现有arm64-v8a的文件夹，发现里面没有b.so，就报错了，所以这个时候删掉arm64-v8a文件夹，这个时候手机发现没有适配arm64-v8a，就会直接去找armeabi的so库，所以要么你别加arm64-v8a,要么armeabi里面有的so库，arm64-v8a里面也必须有。

最终解决：只用armeabi，其余像 mips, x86, armeabi-v7a, arm64-v8a都删掉。手机发现没有适配arm64-v8a，就会直接去找armeabi的 so 库。

其他

对于其他无用文件，代码，及时清理.......................

demo地址
