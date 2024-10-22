# ThreadPoster
A small library implementing a convenient API for sending work to a thread and returning the result to the UI.

Under the hood, it uses standard Java multithreading features such as **ThreadPool** and **Handler**.

To start working, you need to do the following:

1. Create a global instance of the ThreadPoster class, for example in the Application class.

For Kotlin:

```kotlin
class App: Application() {
    companion object {
        val threadPoster: ThreadPoster = ThreadPoster()
    }
}
```

For Java:

```java
public class App extends Application {
    private final static ThreadPoster threadPoster = new ThreadPoster();

    public static ThreadPoster() {
        return threadPoster;
    }
}
```

2. To pass a Runnable object for execution to a background thread, you need to call the ThreadPoster.postToBackground(Runnable) method.

For Kotlin:

```kotlin
threadPoster.postToBackground { /* Your code */ }
```

For Java:

```java
threadPoster.postToBackground(() -> { /* Your code */ })
```

3. To return the execution result to the UI thread, you need to call the ThreadPoster.postToUI(Runnable) method.

For Kotlin:

```kotlin
threadPoster.postToUI { /* Your code */ }
```

For Java:

```java
threadPoster.postToUI(() -> { /* Your code */ })
```

You can also learn more about how the library works using the example presented in the **sample** module in this repo.