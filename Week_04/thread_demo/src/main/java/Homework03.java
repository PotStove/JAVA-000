import java.util.concurrent.*;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 */
public class Homework03 {

    static Integer waitNotifyResult = 0;
    static void byWaitNotify(Object lock) throws InterruptedException {
        Runnable runnable = () -> {
            // synchronized already ensure main thread wait() first?
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            waitNotifyResult = sum();
            System.out.println("before notify, result = " + waitNotifyResult);
            //wait() and notify() must run in synchronized block
            synchronized (lock) {
                lock.notify();
            }
        };
        new Thread(runnable).start();
        synchronized (lock) {
            lock.wait();
        }
        System.out.println("waitNotifyResult result = " + waitNotifyResult);
    }

    static Integer joinResult = 0;
    static void byJoin() throws InterruptedException {
        Runnable runnable = () -> joinResult = sum();
        Thread subThread = new Thread(runnable);
        subThread.start();
        subThread.join();
        System.out.println("byJoin result = " + joinResult);
    }

    static void byExecutorService() throws ExecutionException, InterruptedException {
//        ExecutorService executorService = new ThreadPoolExecutor(
//                1,
//                1,
//                1,
//                TimeUnit.MINUTES,
//                new ArrayBlockingQueue<>(1)
//        );
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        long timer=System.currentTimeMillis();
        Future<Integer> future = executorService.submit(newCallableSum());
        System.out.println("byExecutorService result = " + future.get()
                + "; spent " + (System.currentTimeMillis() - timer) + "ms");
        // 若不shutdown则程序不会结束
        executorService.shutdown();
    }

    static void byFutureTask() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(newCallableSum());
        long timer=System.currentTimeMillis();
        new Thread(futureTask).start();
        System.out.println("byFutureTask result = " + futureTask.get()
                + "; spent " + (System.currentTimeMillis() - timer) + "ms");
    }

    static void byCompletableFuture() {
        CompletableFuture<Integer> completableFuture = CompletableFuture
                .supplyAsync(Homework03::sum);
        long timer=System.currentTimeMillis();
        // join抛出两种异常但不用捕获？
        Integer result = completableFuture.join();
        System.out.println("byCompletableFuture result = " + result
                + "; spent " + (System.currentTimeMillis() - timer) + "ms");
    }

    public static void main(String[] args) throws InterruptedException, java.util.concurrent.ExecutionException{
        
        System.out.println("task 1 start");
        byWaitNotify(new Object());
        System.out.println("task 1 end");
        System.out.println("task 2 start");
        byJoin();
        System.out.println("task 2 end");
        System.out.println("task 3 start");
        byExecutorService();
        System.out.println("task 3 end");
        System.out.println("task 4 start");
        byFutureTask();
        System.out.println("task 4 end");
        System.out.println("task 5 start");
        byCompletableFuture();
        System.out.println("task 5 end");
    }

    static Callable<Integer> newCallableSum(){
        // 从new callable，到lambda，到方法引用
        return Homework03::sum;
    }
    
    private static Integer sum() {
        return fibo(36);
    }
    
    private static int fibo(int a) {
        if ( a < 2) 
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
