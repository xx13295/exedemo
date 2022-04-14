# java 打包 exe 程序的 demo

    这里主要使用了 maven 插件 

    仓库地址： https://github.com/fvarrui/JavaPackager

    JavaPackager提供了 两种 打包的方式 一种是exe 另一种是msi

    exe打包需要下载 Inno Setup ---> https://github.com/jrsoftware/issrc

    msi打包需要下载 WiX  --->  https://github.com/wixtoolset/wix3/releases/tag/wix3112rtm

    这里主要记录使用Inno Setup打包exe

## 准备工作 

    需要安装打包软件Inno Setup  

    下载地址：https://jrsoftware.org/isinfo.php

    安装Inno Setup  完毕后需要添加环境变量 保证 cmd 可以直接用到命令 iscc
    
    其他参考资料：https://max.book118.com/html/2017/1116/140244357.shtm
    

## 相关 maven 配置

    添加配置后 直接 mvn clean package 完事

```
   <properties>
        <java.version>1.8</java.version>
        <my.logo>src/main/resources/assets/windows/logo.ico</my.logo>
        <my.license>src/main/resources/assets/windows/License.txt</my.license>
    </properties>
            
            
            
    <plugin>
        <groupId>io.github.fvarrui</groupId>
        <artifactId>javapackager</artifactId>
        <version>1.6.6</version>
        <executions>
            <execution>
                <id>bundle-jre</id>
                <phase>package</phase>
                <goals>
                    <goal>package</goal>
                </goals>
                <configuration>
                    <!-- 主程序 -->
                    <mainClass>io.springboot.exe.Application</mainClass>
                    <!-- 是否需要打包运行环境 true|false -->
                    <bundleJre>true</bundleJre>
                    <jrePath>C:\Program Files\Java\jre1.8.0_131</jrePath>
                    <!-- 打包成安装包 true|false -->
                    <generateInstaller>true</generateInstaller>
                    <!--  <administratorRequired>true|false</administratorRequired>-->
                    <platform>windows</platform>
                    <!-- 可以携带外部资源打包 需要在resources/assets/windows 下不同平台对应即可-->

                    <name>ExeDemo</name>
                    <organizationName>wxm</organizationName>
                    <organizationUrl>https://springboot.io</organizationUrl>
                    <version>1.0.0</version>
                    <licenseFile>${my.license}</licenseFile>
                    <iconFile>${my.logo}</iconFile>
                    <!--
                        其他平台类似与底下的 winconfig配置类似 具体可以到github仓库查看
                    <linuxConfig> </linuxConfig>
                    <macConfig> </macConfig>
                    -->

                    <winConfig>
                        <!--https://github.com/fvarrui/JavaPackager/blob/master/docs/windows-specific-properties.md-->
                        <generateMsi>false</generateMsi>
                        <exeCreationTool>launch4j</exeCreationTool>
                        <icoFile>${my.logo}</icoFile>
                        <!-- 底下注释的没卵用插件作者默认的模板iss.vtl里没写-->
                        <!--
                         <productVersion>1.0.0.1</productVersion>
                         <fileVersion>1.0.0.2</fileVersion>
                         <copyright>Copyright ©2022-2099 wxm</copyright>
                         -->
                        <setupLanguages>
                            <china>compiler:Languages\china.isl</china>
                            <english>compiler:Default.isl</english>
                        </setupLanguages>

                        <disableDirPage>false</disableDirPage>
                        <disableFinishedPage>false</disableFinishedPage>
                        <disableRunAfterInstall>false</disableRunAfterInstall>
                        <!--<disableProgramGroupPage>false</disableProgramGroupPage>-->
                        <!--<disableWelcomePage>false</disableWelcomePage>-->
                        <createDesktopIconTask>true</createDesktopIconTask>
                    </winConfig>
                </configuration>

            </execution>

        </executions>
    </plugin>


```
