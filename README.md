#rgdx-audio
[zh-cn](https://github.com/Ryuu-64/rgdx-audio/blob/main/readme/README.zh-cn.md)

[![](https://jitpack.io/v/Ryuu-64/rgdx-audio.svg)](https://jitpack.io/#Ryuu-64/rgdx-audio)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/4d3d734a458549348f0cc973a2872fb7)](https://www.codacy.com/gh/Ryuu-64/rgdx-audio/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Ryuu-64/rgdx-audio&amp;utm_campaign=Badge_Grade)
[![](https://img.shields.io/badge/JDK-8+-green.svg)](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)

How to use?

```java
class SoundExecutorExample {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1, 1, 1, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(32),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        SoundExecutor soundManager = new SoundExecutor(threadPoolExecutor);
    }
}
```
