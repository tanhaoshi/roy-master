package roy.application.master.test;

class ArrayWithLockOrder{

    private static long num_locks = 0;

    private long lock_order;

    private int[] arr;

    public ArrayWithLockOrder(int[] a)

    {

        arr = a;

        synchronized(ArrayWithLockOrder.class) {//-----------------------------------------这里

            num_locks++;             // 锁数加 1。

            lock_order = num_locks;  // 为此对象实例设置唯一的 lock_order。

        }

    }

    public long lockOrder()

    {

        return lock_order;

    }

    public int[] array()

    {

        return arr;

    }

}

class SomeClass implements Runnable {

    public int sumArrays(ArrayWithLockOrder a1,

                         ArrayWithLockOrder a2) {

        int value = 0;

        ArrayWithLockOrder first = a1;       // 保留数组引用的一个

        ArrayWithLockOrder last = a2;        // 本地副本。

        int size = a1.array().length;

        if (size == a2.array().length)

        {

            if (a1.lockOrder() > a2.lockOrder())  // 确定并设置对象的锁定

            {                                     // 顺序。

                first = a2;

                last = a1;

            }

            synchronized(first) {              // 按正确的顺序锁定对象。

                synchronized(last) {

                    int[] arr1 = a1.array();

                    int[] arr2 = a2.array();

                    for (int i=0; i<size; i++)

                        value += arr1[i] + arr2[i];

                }

            }

        }

        return value;

    }

    public void run() {

        //...

    }

}