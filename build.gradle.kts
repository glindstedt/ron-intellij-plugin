import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.grammarkit.tasks.GenerateLexer
import org.jetbrains.grammarkit.tasks.GenerateParser

plugins {
    idea
    kotlin("jvm") version "1.3.60"
    id("org.jetbrains.intellij") version "0.4.16"
    id("org.jetbrains.grammarkit") version "2019.3"
}

group = "org.ronplugin"
version = "0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testCompile("junit", "junit", "4.12")
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version = "2019.3"
    setPlugins("PsiViewer:193-SNAPSHOT")
}
configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

val generatedSourcesDir = "src/gen"
val grammarDir = "src/grammar"

idea {
    module {
        generatedSourceDirs.add(file(generatedSourcesDir))
    }
}

val generateLexer = task<GenerateLexer>("generateRonLexer"){
    source = "${grammarDir}/ron.flex"
    targetDir = "${generatedSourcesDir}/org/ronplugin"
    targetClass = "RonLexer"
    purgeOldFiles = true
}

val generateParser = task<GenerateParser>("generateRonParser") {
    source = "${grammarDir}/ron.bnf"
    targetRoot = generatedSourcesDir
    pathToParser = "/org/ronplugin/parser/RonParser.java"
    pathToPsiRoot = "/org/ronplugin/psi"
    purgeOldFiles = true
}

tasks {
    compileKotlin {
        dependsOn(generateLexer, generateParser)
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    clean {
        doFirst {
            delete(generatedSourcesDir)
        }
    }
}

sourceSets {
    main {
        java.srcDirs(generatedSourcesDir)

        withConvention(KotlinSourceSet::class) {
            kotlin.srcDir("src/main/kotlin")
        }
    }
}

tasks.getByName<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
//    changeNotes("""
//      Add change notes here.<br>
//      <em>most HTML tags may be used</em>""")
}