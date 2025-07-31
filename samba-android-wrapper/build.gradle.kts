plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    publishing {
        singleVariant("release") {}
    }
    namespace = "com.jaeckel.sambawrapper"
    compileSdk = 36

    defaultConfig {
//        applicationId = "com.jaeckel.sambawrapper"
        minSdk = 34
//        targetSdk = 36
//        versionCode = 1
//        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
//    kotlinOptions {
//        jvmTarget = "11"
//    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))

    coreLibraryDesugaring(libs.desugar.jdk.libs)

    implementation(libs.androidx.core.ktx)
    //implementation(libs.kethereum.rpc)
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.12.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")
//    implementation("org.apache.tuweni:tuweni-bytes:2.3.1")
//    implementation("io.tmio:tuweni-bytes:2.4.2")
//    implementation("io.tmio:tuweni-crypto:2.4.2")
//    implementation("io.tmio:tuweni-units:2.4.2")
//    implementation("io.tmio:tuweni-rlp:2.4.2")
    implementation("io.projectreactor:reactor-core:3.6.7")
    implementation("io.netty:netty-all:4.1.115.Final")
//    implementation("tech.pegasys.teku.internal:bytes:24.8.0") {
//        exclude(group = "org.apache.tuweni", module = "tuweni-bytes")
//    }

//    implementation("com.github.biafra23.samba:core:cda73d39aa") {
//    implementation("com.github.biafra23.samba:core:956fbf2df0") {
//    implementation("com.github.biafra23.samba:core:a4a85b158e") {
//    implementation("samba:core:1.0-SNAPSHOT") { // mavenLocal
//    implementation("com.github.biafra23.samba:core:main-SNAPSHOT") {
    api("com.github.biafra23.samba:core:6e2ae82806") {
        // use copy in libs folder to remove calls to LogManager.getLogger() which uses reflection
        exclude("tech.pegasys.discovery", "discovery")
        exclude("tech.pegasys.teku.internal", "async")
        exclude("tech.pegasys.teku.internal", "infrastructure-restapi")
        exclude("tech.pegasys.teku.internal", "json")
        exclude("tech.pegasys.teku.internal", "crypto")
        exclude("tech.pegasys.teku.internal", "ssz")

        exclude("org.hyperledger.besu.internal", "algorithms")
        exclude("org.hyperledger.besu.internal", "crypto")
        exclude("org.hyperledger.besu.internal", "core")

        // exclusion needed to prevent log4j from initialising and not being able to parse the log pattern
        exclude(group = "org.apache.logging.log4j", module = "log4j-core")

        // exclusions needed to prevent duplicate classes
        exclude(group = "org.apache.logging.log4j", module = "log4j-slf4j-impl")
        exclude(group = "org.apache.logging.log4j", module = "log4j-slf4j2-impl")

        exclude(group = "org.apache.tuweni", module = "tuweni-rlp")
        exclude(group = "org.apache.tuweni", module = "tuweni-crypto")
        exclude(group = "org.apache.tuweni", module = "tuweni-bytes")
        exclude(group = "org.apache.tuweni", module = "tuweni-units")
        exclude(group = "org.apache.tuweni", module = "tuweni-io")

        exclude(group = "io.tmio", module = "tuweni-rlp")
        exclude(group = "io.tmio", module = "tuweni-crypto")
        exclude(group = "io.tmio", module = "tuweni-bytes")
        exclude(group = "io.tmio", module = "tuweni-units")
        exclude(group = "io.tmio", module = "tuweni-io")

//        exclude(group = "org.bouncycastle", module = "bcprov-jdk18on")
//        exclude(group = "org.bouncycastle", module = "bcutil-jdk18on")

        // exclude oshi-core to prevent duplicate classes
        exclude(group = "net.java.dev.jna", module = "jna")
        exclude(group = "net.java.dev.jna", module = "jna-platform")

        exclude(group = "org.rocksdb", module = "rocksdbjni")

        // These need to be excluded here because the libs in
        // ./libs depend on them so we have to have them further down this file.
        exclude(group = "com.google.guava", module = "guava")
        exclude(group = "org.slf4j", module = "slf4j-reload4j")
//        exclude(group = "io.javalin", module = "javalin")
//        exclude(group = "org.thymeleaf", module = "thymeleaf")
//        exclude(group = "org.meldsun.utp:utp-core", module = "1.0-SNAPSHOT")
        exclude(group = "tech.pegasys", module = "jc-kzg-4844")
    }
    api("tech.pegasys.discovery:discovery:develop") {
//    implementation("com.github.biafra23:discovery:master-SNAPSHOT") {
//    implementation("tech.pegasys:discovery:25.4.0")
//    implementation("com.github.Consensys:discovery:25.4.0") {
//        exclude(group = "org.bouncycastle", module = "bcprov-jdk18on")
        exclude(group = "org.apache.tuweni", module = "tuweni-rlp")
        exclude(group = "org.apache.tuweni", module = "tuweni-crypto")
        exclude(group = "org.apache.tuweni", module = "tuweni-bytes")
        exclude(group = "org.apache.tuweni", module = "tuweni-units")
        exclude(group = "org.apache.tuweni", module = "tuweni-io")
        exclude(group = "io.tmio", module = "tuweni-rlp")
        exclude(group = "io.tmio", module = "tuweni-crypto")
        exclude(group = "io.tmio", module = "tuweni-bytes")
        exclude(group = "io.tmio", module = "tuweni-units")
        exclude(group = "io.tmio", module = "tuweni-io")
    }
//    implementation("com.github.meldsun0:utp:1.0-SNAPSHOT") {
//        exclude(group = "org.apache.tuweni", module = "tuweni-rlp")
//        exclude(group = "org.apache.tuweni", module = "tuweni-crypto")
//        exclude(group = "org.apache.tuweni", module = "tuweni-bytes")
//        exclude(group = "org.apache.tuweni", module = "tuweni-units")
//        exclude(group = "org.apache.tuweni", module = "tuweni-io")
//    }

    implementation("org.bouncycastle:bcprov-jdk18on:1.78.1")
    implementation("org.bouncycastle:bcutil-jdk18on:1.78.1")

    // SLF4J API
    implementation( "org.slf4j:slf4j-api:2.0.16")
//    implementation("org.slf4j:slf4j-simple:2.0.16")
//    implementation("com.arcao:slf4j-timber:3.1@aar")
    implementation("com.jakewharton.timber:timber:4.7.1")
//    implementation("org.apache.logging.log4j:log4j-to-slf4j:2.24.2")
//    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.24.2")
    implementation("org.apache.logging.log4j:log4j-core:2.24.2")
    implementation("com.github.tony19:logback-android:3.0.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("net.java.dev.jna:jna:4.4.0@aar")
    implementation("io.projectreactor:reactor-core:3.5.0")
//    implementation("org.rocksdb:rocksdbjni:9.10.0")
//    implementation("io.maryk.rocksdb:rocksdb-android:9.7.3")
    implementation("io.maryk.rocksdb:rocksdb-android:9.10.1")
    // NonSerializableMemoizingSupplier stopped implementing java.util.function.Supplier in 21.0
    implementation("com.google.guava:guava:33.4.0-android")
//    implementation("com.google.guava:guava:33.4.0-jre")

    implementation("io.javalin:javalin:6.2.0")
    implementation("io.javalin:javalin-rendering:5.6.5")
    implementation("org.thymeleaf:thymeleaf:3.1.2.RELEASE")
    implementation("org.webjars:swagger-ui:5.17.14")

    implementation("com.github.biafra23:trueblocks-kotlin:main-SNAPSHOT") {
        exclude(group = "ch.qos.logback", module = "logback-classic")
    }
//    implementation("com.github.biafra23:ipfs-api-kotlin:master-SNAPSHOT")
    implementation("com.github.komputing.kethereum:model:0.86.0")
    implementation("com.github.komputing.khex:core:1.1.2")
    implementation("com.github.komputing.khex:extensions:1.1.2")

    implementation("io.consensys.tuweni:tuweni-bytes:2.7.0") {
//        exclude(group = "org.apache.tuweni", module = "tuweni-bytes")
//        exclude(group = "io.tmio", module = "tuweni-bytes")
    }
    implementation("tech.pegasys.teku.internal:unsigned:24.8.0") {
        exclude(group = "org.apache.logging.log4j", module = "log4j-slf4j-impl")
    }
    implementation("org.hyperledger.besu:evm:25.2.1") {
        exclude(group = "org.apache.tuweni", module = "tuweni-bytes")
        exclude(group = "org.apache.tuweni", module = "tuweni-rlp")
        exclude(group = "org.apache.tuweni", module = "tuweni-crypto")
        exclude(group = "org.apache.tuweni", module = "tuweni-units")
        exclude(group = "org.apache.tuweni", module = "tuweni-io")
        exclude(group = "io.tmio", module = "tuweni-bytes")
        exclude(group = "io.tmio", module = "tuweni-rlp")
        exclude(group = "io.tmio", module = "tuweni-crypto")
        exclude(group = "io.tmio", module = "tuweni-units")
        exclude(group = "io.tmio", module = "tuweni-io")

        exclude(group = "net.java.dev.jna", module = "jna")
        exclude(group = "net.java.dev.jna", module = "jna-platform")

        exclude(group = "tech.pegasys", module = "jc-kzg-4844")
        exclude(group = "tech.pegasys.discovery", module = "discovery")

        exclude("org.hyperledger.besu.internal", "algorithms")
        exclude("org.hyperledger.besu.internal", "crypto")
        exclude("org.hyperledger.besu.internal", "core")
    }
    implementation("org.hyperledger.besu:plugin-api:25.2.1") {
        exclude(group = "org.apache.tuweni", module = "tuweni-bytes")
        exclude(group = "org.apache.tuweni", module = "tuweni-rlp")
        exclude(group = "org.apache.tuweni", module = "tuweni-crypto")
        exclude(group = "org.apache.tuweni", module = "tuweni-units")
        exclude(group = "org.apache.tuweni", module = "tuweni-io")
        exclude(group = "io.tmio", module = "tuweni-bytes")
        exclude(group = "io.tmio", module = "tuweni-rlp")
        exclude(group = "io.tmio", module = "tuweni-crypto")
        exclude(group = "io.tmio", module = "tuweni-units")
        exclude(group = "io.tmio", module = "tuweni-io")

        exclude(group = "net.java.dev.jna", module = "jna")
        exclude(group = "net.java.dev.jna", module = "jna-platform")

        exclude(group = "tech.pegasys", module = "jc-kzg-4844")
        exclude(group = "tech.pegasys.discovery", module = "discovery")

        exclude("org.hyperledger.besu.internal", "algorithms")
        exclude("org.hyperledger.besu.internal", "crypto")
        exclude("org.hyperledger.besu.internal", "core")
    }

    implementation("org.hyperledger.besu:besu-datatypes:25.2.1") {
        exclude(group = "org.apache.tuweni", module = "tuweni-bytes")
        exclude(group = "org.apache.tuweni", module = "tuweni-rlp")
        exclude(group = "org.apache.tuweni", module = "tuweni-crypto")
        exclude(group = "org.apache.tuweni", module = "tuweni-units")
        exclude(group = "org.apache.tuweni", module = "tuweni-io")
        exclude(group = "io.tmio", module = "tuweni-bytes")
        exclude(group = "io.tmio", module = "tuweni-rlp")
        exclude(group = "io.tmio", module = "tuweni-crypto")
        exclude(group = "io.tmio", module = "tuweni-units")
        exclude(group = "io.tmio", module = "tuweni-io")

        exclude(group = "net.java.dev.jna", module = "jna")
        exclude(group = "net.java.dev.jna", module = "jna-platform")

        exclude(group = "tech.pegasys", module = "jc-kzg-4844")
        exclude(group = "tech.pegasys.discovery", module = "discovery")

        exclude("org.hyperledger.besu.internal", "algorithms")
        exclude("org.hyperledger.besu.internal", "crypto")
        exclude("org.hyperledger.besu.internal", "core")
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                // Applies the component for the release build variant.
                from(components["release"]) // For Android libraries, components are named after build variants

                // --- Artifact Details (POM information) ---
                groupId = "com.jaeckel.samba"
                artifactId = "samba-android-wrapper"
                version = "1.0.0-SNAPSHOT"

                // More POM customization (optional)
                pom {
                    name.set("Samba Android Wrapper") // User-friendly name
                    description.set("An Android wrapper for samba-core to make it easier to use on Android.")
                    url.set("https.github.com/biafra23/samba-android-wrapper")

                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("biafra23")
                            name.set("Dirk Jäckel")
                            email.set("github@dirk.jaeckel.name")
                        }
                    }
                    scm {
                        connection.set("scm:git:git://github.com/biafra23/samba-android-wrapper.git")
                        developerConnection.set("scm:git:ssh://github.com:biafra23/samba-android-wrapper.git")
                        url.set("https.github.com/biafra23/samba-android-wrapper/tree/main")
                    }
                }
            }
        }
    }
}