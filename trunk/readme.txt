1.安装maven ，设置MAVEN_HOME，并且MAVEN_HOME\bin到Path
2.当前目录下执行mvn clean install 然后等待build success出现
3.mvn eclipse:clean eclipse:eclipse 生成eclipse项目配置文件
4.在eclipse中import这个项目