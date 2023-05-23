# rgdx-audio

[![](https://jitpack.io/v/Ryuu-64/rgdx-audio.svg)](https://jitpack.io/#Ryuu-64/rgdx-audio)[![Codacy Badge](https://app.codacy.com/project/badge/Grade/4d3d734a458549348f0cc973a2872fb7)](https://www.codacy.com/gh/Ryuu-64/rgdx-audio/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Ryuu-64/rgdx-audio&amp;utm_campaign=Badge_Grade)[![](https://img.shields.io/badge/JDK-8+-green.svg)](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)

## 我为什么需要这个库？

解决 libGdx Android 中音频导致程序卡顿问题。

### 其他解决方案

#### AsynchronousAndroidAudio 

如 [libgdx Audio Wiki](https://libgdx.com/wiki/audio/audio) 所述，同时播放多个 Sound 会导致程序卡顿，可以通过 AsynchronousAndroidAudio 修复，但这使得 sound id 不能获取：

```java
/** A performance oriented implementation of the {@link AndroidAudio} interface.
 * 
 * Sounds are played on a separate thread. This avoids waiting for sound ids on methods that can potentially lock main thread for
 * considerable amount of time, especially when playing several sounds at the same time. The limitation of this approach is that
 * methods that require a sound id are not supported. */
```

#### Oboe

如 [libgdx Audio Wiki](https://libgdx.com/wiki/audio/audio) 所述，Oboe 能解决音频问题，但是 libGDX-Oboe aar 的大小有 10 多 MB，对于有的应用这显然是无法接受的。 

## 如何使用?

1. 创建 `SoundExecutor` 

   ```java
   ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
       1, 1, 1, TimeUnit.MILLISECONDS,
       new ArrayBlockingQueue<>(32),
       new ThreadPoolExecutor.DiscardOldestPolicy()
   );
   
   SoundExecutor soundExecutor = new SoundExecutor(threadPoolExecutor);
   ```

2. 选择想要的 API 进行播放

   ```java
   soundExecutor.play(sound);
   ```

## 详细

### workQueue  大小

不推荐使用 `Executors.newFixedThreadPool` 等方法，这样会使得 `workQueue` 的大小为 `Integer.MAX_VALUE` 可能产生内存溢出问题。应当在能满足需求的情况下使得 `workQueue` 尽量小。

### ThreadPoolExecutor 策略

在默认情况下，`ThreadPoolExecutor` 采用 `ThreadPoolExecutor.AbortPolicy` ，当 `workQueue` 满后添加任务 `AbortPolicy` 将抛出 `RejectedExecutionException`。若想避免直接抛出异常，需要进行背压(Backpressure)设置。可为 `ThreadPoolExecutor` 设置其他的 Policy，例如 `ThreadPoolExecutor.DiscardOldestPolicy` ，当向  `workQueue` 添加新任务，且 `workQueue` 满时将会丢弃最旧的任务。

