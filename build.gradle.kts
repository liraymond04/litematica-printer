plugins {
    id("java")
}

subprojects {
    apply<JavaPlugin>()

    repositories {
        mavenLocal()
        mavenCentral()
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_16
        targetCompatibility = JavaVersion.VERSION_16
    }
}

val archives_base_name: String by project
val mod_version: String by project

val buildAll = tasks.create("buildAll") {
    dependsOn(":implementation:v1_17:build")
    dependsOn(":implementation:v1_18:build")
    dependsOn(":implementation:v1_19:build")
    dependsOn(":implementation:v1_20_1:build")
    doLast {
        println("Copying files...")
        file("implementation/v1_20_1/build/libs/v1_20_1.jar").copyTo(file("build/${archives_base_name}-1.20.1-${mod_version}.jar"), true)
        file("implementation/v1_19/build/libs/v1_19.jar").copyTo(file("build/${archives_base_name}-1.19-${mod_version}.jar"), true)
        file("implementation/v1_18/build/libs/v1_18.jar").copyTo(file("build/${archives_base_name}-1.18-${mod_version}.jar"), true)
        file("implementation/v1_17/build/libs/v1_17.jar").copyTo(file("build/${archives_base_name}-1.17-${mod_version}.jar"), true)
    }
}
