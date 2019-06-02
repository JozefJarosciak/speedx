package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzab;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class Tasks {

    /* renamed from: com.google.android.gms.tasks.Tasks$1 */
    class C34691 implements Runnable {
        final /* synthetic */ zzh aDT;
        final /* synthetic */ Callable zzclc;

        C34691(zzh zzh, Callable callable) {
            this.aDT = zzh;
            this.zzclc = callable;
        }

        public void run() {
            try {
                this.aDT.setResult(this.zzclc.call());
            } catch (Exception e) {
                this.aDT.setException(e);
            }
        }
    }

    interface zzb extends OnFailureListener, OnSuccessListener<Object> {
    }

    private static final class zza implements zzb {
        private final CountDownLatch zzalc;

        private zza() {
            this.zzalc = new CountDownLatch(1);
        }

        public void await() throws InterruptedException {
            this.zzalc.await();
        }

        public boolean await(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.zzalc.await(j, timeUnit);
        }

        public void onFailure(@NonNull Exception exception) {
            this.zzalc.countDown();
        }

        public void onSuccess(Object obj) {
            this.zzalc.countDown();
        }
    }

    private static final class zzc implements zzb {
        private final zzh<Void> aDN;
        private Exception aDS;
        private final int aDU;
        private int aDV;
        private int aDW;
        private final Object zzail = new Object();

        public zzc(int i, zzh<Void> zzh) {
            this.aDU = i;
            this.aDN = zzh;
        }

        private void zzchl() {
            if (this.aDV + this.aDW != this.aDU) {
                return;
            }
            if (this.aDS == null) {
                this.aDN.setResult(null);
                return;
            }
            zzh zzh = this.aDN;
            int i = this.aDW;
            zzh.setException(new ExecutionException(i + " out of " + this.aDU + " underlying tasks failed", this.aDS));
        }

        public void onFailure(@NonNull Exception exception) {
            synchronized (this.zzail) {
                this.aDW++;
                this.aDS = exception;
                zzchl();
            }
        }

        public void onSuccess(Object obj) {
            synchronized (this.zzail) {
                this.aDV++;
                zzchl();
            }
        }
    }

    private Tasks() {
    }

    public static <TResult> TResult await(@NonNull Task<TResult> task) throws ExecutionException, InterruptedException {
        zzab.zzata();
        zzab.zzb((Object) task, (Object) "Task must not be null");
        if (task.isComplete()) {
            return zzb(task);
        }
        Object zza = new zza();
        zza(task, zza);
        zza.await();
        return zzb(task);
    }

    public static <TResult> TResult await(@NonNull Task<TResult> task, long j, @NonNull TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        zzab.zzata();
        zzab.zzb((Object) task, (Object) "Task must not be null");
        zzab.zzb((Object) timeUnit, (Object) "TimeUnit must not be null");
        if (task.isComplete()) {
            return zzb(task);
        }
        Object zza = new zza();
        zza(task, zza);
        if (zza.await(j, timeUnit)) {
            return zzb(task);
        }
        throw new TimeoutException("Timed out waiting for Task");
    }

    public static <TResult> Task<TResult> call(@NonNull Callable<TResult> callable) {
        return call(TaskExecutors.MAIN_THREAD, callable);
    }

    public static <TResult> Task<TResult> call(@NonNull Executor executor, @NonNull Callable<TResult> callable) {
        zzab.zzb((Object) executor, (Object) "Executor must not be null");
        zzab.zzb((Object) callable, (Object) "Callback must not be null");
        Task zzh = new zzh();
        executor.execute(new C34691(zzh, callable));
        return zzh;
    }

    public static <TResult> Task<TResult> forException(@NonNull Exception exception) {
        Task zzh = new zzh();
        zzh.setException(exception);
        return zzh;
    }

    public static <TResult> Task<TResult> forResult(TResult tResult) {
        Task zzh = new zzh();
        zzh.setResult(tResult);
        return zzh;
    }

    public static Task<Void> whenAll(Collection<? extends Task<?>> collection) {
        if (collection.isEmpty()) {
            return forResult(null);
        }
        for (Task task : collection) {
            if (task == null) {
                throw new NullPointerException("null tasks are not accepted");
            }
        }
        Task zzh = new zzh();
        zzb zzc = new zzc(collection.size(), zzh);
        for (Task task2 : collection) {
            zza(task2, zzc);
        }
        return zzh;
    }

    public static Task<Void> whenAll(Task<?>... taskArr) {
        return taskArr.length == 0 ? forResult(null) : whenAll(Arrays.asList(taskArr));
    }

    private static void zza(Task<?> task, zzb zzb) {
        task.addOnSuccessListener(TaskExecutors.aDO, (OnSuccessListener) zzb);
        task.addOnFailureListener(TaskExecutors.aDO, (OnFailureListener) zzb);
    }

    private static <TResult> TResult zzb(Task<TResult> task) throws ExecutionException {
        if (task.isSuccessful()) {
            return task.getResult();
        }
        throw new ExecutionException(task.getException());
    }
}
