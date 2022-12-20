plugins {
    val kotlinVersion = "1.7.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.13.2"
//    id("net.mamoe.mirai-logging-log4j") version "2.13.0-RC2"

// Kotlin添加对lombok注解支持   https://kotlinlang.org/docs/lombok.html
    kotlin("plugin.lombok") version "1.7.21"
    id("io.freefair.lombok") version "5.3.0"

}

group = "org.example"
version = "0.1.0"

repositories {
    maven("https://maven.aliyun.com/repository/public") // 阿里云国内代理仓库
    mavenCentral()
}
dependencies {
//    api("net.mamoe", "mirai-core", "2.7.0")

//    implementation("org.projectlombok:lombok:1.18.24")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.1")
    implementation("com.fasterxml.jackson.core:jackson-core:2.14.1")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.14.1")
    implementation("org.jsoup:jsoup:1.15.3")
    api(
        "net.mamoe",
        "mirai-logging-slf4j-logback",
        "2.13.2"
    ) // 在依赖 mirai-core 或 mirai-core-api 的前提下额外添加日志转接模块. 版本号相同
}