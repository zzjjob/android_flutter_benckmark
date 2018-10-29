
#include <jni.h>
#include <android/log.h>
#include <sys/time.h>

int isPrime(long n) {
    if (n < 2) return 0;

    for (long i = 2; i * i <= n; i++) {
        if (n % i == 0) return 0;
    }

    return 1;
}

static long getNthPrimeNumber(int n) {
    int counter = 0;

    for (long i = 1;; i++) {
        if (isPrime(i))
            counter++;

        if (counter == n) {
            return i;
        }
    }
}

static long currentTimeMillis() {
    struct timeval tv;
    gettimeofday(&tv, NULL);
    return tv.tv_sec * 1000 + tv.tv_usec / 1000;
}

// Warmup for at least 100ms. Discard result.
static void warmup() {
    long startTime = currentTimeMillis();
    long elapsed = 0;

    while (elapsed < 100) {
        getNthPrimeNumber(100);
        elapsed = currentTimeMillis() - startTime;
    }
}

static long performancePrime(int nth, int count, int nth_no) {
    warmup();


    for (int j = 0; j < count; j ++) {
        int n = nth * (j + 1);

        long startTime = currentTimeMillis();
        long nThPrimeNumber = 0;

        for (int i = 0; i < count; i++) {
            nThPrimeNumber = getNthPrimeNumber(n);
        }

        long elapsed = currentTimeMillis() - startTime;

        __android_log_print(ANDROID_LOG_ERROR, "[benchmark] CPP",
                "%dth prime number:(ms)\t%ld\t%d x\t %f",
                    n, nThPrimeNumber, count, ((double)elapsed/count));
    }

    //20w th prime * 10 count
    {
            int n = nth_no;

            long startTime = currentTimeMillis();
            long nThPrimeNumber = 0;

            for (int i = 0; i < count; i++) {
                nThPrimeNumber = getNthPrimeNumber(n);
            }

            long elapsed = currentTimeMillis() - startTime;

            __android_log_print(ANDROID_LOG_ERROR, "[benchmark] CPP",
                    "%dth prime number:(ms)\t%ld\t%d x\t %f",
                        n, nThPrimeNumber, count, ((double)elapsed/count));
        }
    return 1L;
}
/*
extern "C" JNIEXPORT jlong JNICALL
Java_com_nabinbhandari_flutterbenchmark_TestActivity_nativePerformancePrime(JNIEnv *env,
                                                                             jclass type,
                                                                             jint nth,
                                                                             jint count,
                                                                             jint nth_no) {
    return performancePrime(nth, count, nth_no);
}

extern "C" JNIEXPORT jlong JNICALL
Java_com_nabinbhandari_flutterbenchmark_TestActivity_nativePerformanceHavlak(JNIEnv *env,
                                                                              jclass type) {

    runHavlakTest();

    return 1L;
}

extern "C" JNIEXPORT jlong JNICALL
Java_com_nabinbhandari_flutterbenchmark_TestActivity_nativeGetNthPrime(JNIEnv *env,
                                                                             jclass type,
                                                                             jint nth) {
    return getNthPrimeNumber(nth);
}
*/
