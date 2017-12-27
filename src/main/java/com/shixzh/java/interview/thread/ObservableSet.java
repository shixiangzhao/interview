package com.shixzh.java.interview.thread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ObservableSet<E> extends ForwardingSet<E> {

    private final List<SetObserver<E>> observers = new ArrayList<SetObserver<E>>();

    //如果存在大量写操作，CopyOnWriteArrayList性能会大受影响，这里只是遍历却是很好的应用案例
    private final List<SetObserver<E>> observersCopyOnWrite = new CopyOnWriteArrayList<SetObserver<E>>();

    public ObservableSet(Set<E> set) {
        super(set);
    }
/*
    public void addObserver(SetObserver<E> observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }
*/

    public void addObserver(SetObserver<E> observer) {
        observersCopyOnWrite.add(observer);
    }
/*
    public boolean removeObserver(SetObserver<E> observer) {
        synchronized (observers) {
            return observers.remove(observer);
        }
    }
*/

    public boolean removeObserver(SetObserver<E> observer) {
        return observersCopyOnWrite.remove(observer);
    }


/*
    private void notifyElementAdded(E element) {
        synchronized (observers) {
            for (SetObserver<E> observer : observers) {
                //同步区域调用外来方法，容易造成死锁，如GUI的工具箱
                observer.added(this, element);
            }
        }
    }
*/
/*
    private void notifyElementAdded(E element) {
        List<SetObserver<E>> snapshot = null;
        synchronized (observers) {
            //给observers列表拍张快照，没有锁也可以安全的遍历它
            snapshot = new ArrayList<SetObserver<E>>(observers);
        }
        for (SetObserver<E> observer : snapshot) {
            observer.added(this, element);
        }
    }
*/

    private void notifyElementAdded(E element) {
        for (SetObserver<E> observer : observersCopyOnWrite) {
            observer.added(this, element);
        }
    }

    @Override
    public boolean add(E element) {
        boolean added = super.add(element);
        if (added) {
            notifyElementAdded(element);
        }
        return added;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (E element : c) {
            result |= add(element);
        }
        return result;
    }

    public static void main(String[] args) {
        //testObservableSet1();
        //testObservableSet2();
        testObservableSet3();
    }

    public static void testObservableSet1() {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<Integer>());
        set.addObserver(new SetObserver<Integer>() {

            public void added(ObservableSet<Integer> s, Integer e) {
                System.out.println(e);
            }
        });

        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }

    //java.util.ConcurrentModificationException at 23
    public static void testObservableSet2() {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<Integer>());
        set.addObserver(new SetObserver<Integer>() {

            public void added(ObservableSet<Integer> s, Integer e) {
                System.out.println(e);
                if (e == 23)
                    s.removeObserver(this);
            }
        });

        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }

    //遇到死锁问题，s.removeObserver企图获得锁，但是主线程已经有锁了
    public static void testObservableSet3() {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<Integer>());
        set.addObserver(new SetObserver<Integer>() {

            public void added(ObservableSet<Integer> s, Integer e) {
                System.out.println(e);
                if (e == 23) {
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    final SetObserver<Integer> observer = this;
                    try {
                        executor.submit(new Runnable() {

                            public void run() {
                                s.removeObserver(observer);
                            }
                        }).get();
                    } catch (InterruptedException | ExecutionException e1) {
                        e1.printStackTrace();
                    } finally {
                        executor.shutdown();
                    }
                }
            }
        });

        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }
}

class ForwardingSet<E> {

    public ForwardingSet(Set<E> set) {

    }

    public boolean addAll(Collection<? extends E> c) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean add(E element) {
        // TODO Auto-generated method stub
        return true;
    }
}

interface SetObserver<E> {

    public void added(ObservableSet<E> observableSet, E element);

}
