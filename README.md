# Samba Wrapper

This Android library (aar) provides a simple wrapper around the Portal Network client Samba (https://github.com/meldsun0/samba) to allow for easy integration into Android applications.

## Usage

1. Add the dependency to your `build.gradle.kts` file:

```kotlin   
dependencies {
    implementation("com.github.biafra23:SambaWrapper:e2e14bba9a")   { 
        exclude(group = "net.java.dev.jna", module = "jna")
    }
    implementation("net.java.dev.jna:jna:5.17.0@aar")
}
```

At the moment  this exclusion is necessary because otherwise  necessary JNA native libs are missing at runtime when using the jitpack build version of SambaWrapper. When you build the library yourself, you can remove this dependency and the exclusion.

2. Add the following permissions to your `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

3. Delete Samba database files if they exist in your app's data directory. This is necessary to avoid issues with the Samba database when initializing the wrapper.

```kotlin
 val sambaDir = filesDir.absolutePath + "/samba"
        if (File(sambaDir).exists()) {
            File(sambaDir).deleteRecursively()
        }

```
3. Initialize and start Samba in a separate Thread:

```kotlin
Thread({
    val options = arrayOf(
        "--data-path=$filesDirPath/samba/",
//                        "--disable-json-rpc-server",
        "--disable-rest--server",
        "--disable-metric--server"
    )
    sambaSDK = Samba.init(options)
}).start()
```
4. Use the Samba SDK to interact with the Portal Network

```kotlin

// get block header by block number
val result = sambaSDK?.historyAPI()?.get()?.getBlockHeaderByBlockNumber("$blockNumber")?.get()
result?.let { header ->
    // get block body by block hash
    val blockBody = sambaSDK?.historyAPI()?.get()?.getBlockBodyByBlockHash(header.blockHash)
    val body = blockBody?.get()
    // get transactions in the block body
    body?.transactions?.forEach { tx ->
        logger.info("Transaction: $tx")
    }
}
```

You can find an example app using this wrapper here: https://github.com/biafra23/AndroidPortal