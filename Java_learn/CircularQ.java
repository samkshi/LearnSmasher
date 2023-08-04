public class CircularQ {
    private int[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    CircularQ(int capacity) {
        this.capacity = capacity;
        queue = new int[capacity];
        front = rear = -1;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void enqueue(int item) {
        if (isFull()) {
            System.out.println("Queue is full. Cannot enqueue.");
            return;
        }

        if (isEmpty()) {
            front = rear = 0;
        } else {
            rear = (rear + 1) % capacity;
        }

        queue[rear] = item;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return -1;
        }

        int item = queue[front];
        if (front == rear) {
            front = rear = -1;
        } else {
            front = (front + 1) % capacity;
        }

        size--;
        return item;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.print("Queue: ");
        int i = front;
        do {
            System.out.print(queue[i] + " ");
            i = (i + 1) % capacity;
        } while (i != (rear + 1) % capacity);
        System.out.println();
    }

    public static void main(String[] args) {
        CircularQ cq = new CircularQ(5);

        cq.enqueue(8);
        cq.enqueue(45);
        cq.enqueue(34);
        cq.display(); // Output: Queue: 8 45 34

        cq.dequeue();
        cq.display(); // Output: Queue: 45 34

        cq.enqueue(73);
        cq.enqueue(65);
        cq.enqueue(50);
        cq.display(); // Output: Queue: 45 34 73 65 50

        cq.enqueue(75); // Output: Queue is full. Cannot enqueue.
    }
}
