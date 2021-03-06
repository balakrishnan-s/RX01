import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by abhinav.sharma on 10/13/2016.
 */
public class Main {

    private Observable myObservable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Hello World -- RX 1");
//            subscriber.onError(new Throwable("Oh Fuck something is wrong"));
            subscriber.onNext("Hello World -- RX 2");
            subscriber.onNext("Hello World -- RX 3");
            subscriber.onCompleted();
        }
    });

    private Observer myObserver = new Observer<String>() {
        @Override
        public void onCompleted() {
            System.out.println("myObserver -- completed" + "   ~~~ From Observer callback");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("Fishy...!!" + "   ~~~ From Observer callback");
            e.printStackTrace();
        }

        @Override
        public void onNext(String o) {
            System.out.println(o + "   ~~~ From Observer callback");
        }
    };

    private Subscriber mySubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            System.out.println("mySubscriber -- completed");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("Fishy...!!");
            e.printStackTrace();
        }

        @Override
        public void onNext(String o) {
            System.out.println(o);
        }
    };

    public static void main(String[] args) {
        System.out.println("Hello World -- Normal");
        System.out.println(" ----- xxxx ----- ");
        Main m = new Main();

        m.myObservable.subscribe(m.mySubscriber);
        m.myObservable.subscribe(m.myObserver);
    }
}
