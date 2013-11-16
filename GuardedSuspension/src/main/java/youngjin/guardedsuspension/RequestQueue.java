package youngjin.guardedsuspension;

import java.util.LinkedList;
import java.util.Queue;

/**
 * synchronized는 무엇을 지키고 있는가?
 * - queue 필드
 */
public class RequestQueue {
    // 왜 final로 했나? queue 메모리 주소를 변경하지 못하게 하기 위해서...
    private final Queue<Request> queue = new LinkedList<Request>();

    // 가드 조건이 충족이 되면 request는 remove하고 그렇지 않을 경우에는 wait()을 하게 됨
    public synchronized Request getRequest() {

        // 왜 wait()는 while안에 있어야 하나?
        while (queue.peek() == null) {  // 가드 조건
            try {
                System.out.println("waiting...");
                wait();
            } catch (InterruptedException e) {
            }
        }
        return queue.remove();
    }

    //
    public synchronized void putRequest(Request request) {
        queue.offer(request); //insert
        System.out.println("notifyAll...");
        notifyAll();

    }
}
