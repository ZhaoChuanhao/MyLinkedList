package test;

/**
 * @author chuanhao.zhao@hand-china.com
 * @version 1.0
 * @name MyLinkedListTest
 * @description 不带虚拟头结点的链表
 * @date 2018/10/31
 */
public class MyLinkedListTest<T> {

    private class Node{
        private T data;
        private Node next;

        private Node(T data, Node next){
            this.data = data;
            this.next = next;
        }

        private Node(T data){
            this(data, null);
        }

        private Node(){
            this(null, null);
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private Node head;
    private int size;

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 向链表的指定位置插入节点
    public void add(int index, T data){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Illegal index!");
        }
        if (index == 0){
            head = new Node(data, head);
            size++;
        }else {
            Node prev = head;
            for (int i = 0; i < index - 1; i++){
                prev = prev.next;
            }

            /*Node node = new Node(data);
            node.next = prev.next;
            prev.next = node;*/

            // 新建一个Node节点，让该节点的next指针指向head，然后让head指向该节点
            // 完成插入头结点的操作，和上面三行代码结果相同
            prev.next = new Node(data, prev.next);
            size++;
        }
    }

    // 重载添加方法，如果不写添加元素的下标，则默认添加头节点
    public void add(T data){
        addFirst(data);
    }

    // 向链表头插入新的头节点
    public void addFirst(T data){
        add(0, data);
    }

    // 向链表尾插入尾节点
    public void addLast(T data){
        add(size, data);
    }

    // 获取下标为index的元素
    public T get(int index){
        Node node = head;
        for (int i = 0; i < index; i++){
            node = node.next;
        }
        return node.data;
    }

    // 获取第一个元素
    public T getFirst(){
        return get(0);
    }

    // 获取最后一个元素
    public T getLast(){
        return get(size - 1);
    }

    // 修改下标为inde的元素
    public void set(int index, T data){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Set failed. Illegal index!");
        }
        Node node = head;
        for (int i = 0; i < index; i++){
            node = node.next;
        }
        node.data = data;
    }

    // 删除链表中下标为index的元素，返回删除的元素
    public T remove(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Remove failed. Illegal index!");
        }

        Node node = head;
        if (index == 0){
            head = head.next;
        }else {
            Node prev = head;
            for (int i = 0; i < index - 1; i++){
                prev = prev.next;
            }
            node = prev.next;
            prev.next = node.next;
        }
        node.next = null;
        size--;
        return node.data;
    }

    // 删除链表中该元素的节点
    public void remove(T data){
        if (data == null){
            return;
        }

        Node prev = head;
        Node node = head.next;
        if (data.equals(head.data)){
            head = head.next;
            prev.next = null;
            size--;
        }else {
            while (node != null){
                if (data.equals(node.data)){
                    prev.next = node.next;
                    node.next = null;
                    size--;
                    break;
                }
                prev = prev.next;
                node = node.next;
            }
        }
    }

    // 删除链表头结点，返回删除的元素
    public T removeFirst(){
        return remove(0);
    }

    // 删除链表尾节点，返回删除的元素
    public T removeLast(){
        return remove(size - 1);
    }

    // 查找链表中是否包含该元素
    public boolean contains(T data){
        if (data == null){
            return false;
        }
        Node node = head;
        while (node != null){
            if (data.equals(node.data)){
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        Node node = head;
        while (node != null){
            s.append(node + "->");
            node = node.next;
        }
        s.append("null");
        return s.toString();
    }
}
