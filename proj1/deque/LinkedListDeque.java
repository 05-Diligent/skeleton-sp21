package deque;

public class LinkedListDeque<T> {
    //创建节点
    public  class IntNode{
        public T item;
        public IntNode prev;
        public IntNode next;

        public IntNode(T i,IntNode p,IntNode n){
            item = i;
            prev = p;
            next = n;
        }
    }
    //空节点
    public IntNode sentinel;
    public int size;
    public LinkedListDeque(){
        sentinel = new IntNode(null,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    //第一个节点
    public LinkedListDeque(T n){
        sentinel = new IntNode(null,null,null);
        sentinel.next = new IntNode(n,sentinel,sentinel);
        sentinel.prev = sentinel.next;
        size++;
    }
    //添加在开头节点
    public void addFirst(T n){
        // sentinel.next = new IntNode(n,sentinel,sentinel.next);
        //  sentinel.next.prev = sentinel.next;
        sentinel.next.prev = new IntNode(n,sentinel,sentinel.next);
        sentinel.next = sentinel.next.prev;
        size++;
    }

    //添加在末尾节点
    public void addLast(T n){
        sentinel.prev.next = new IntNode(n,sentinel.prev,sentinel);
        sentinel.prev = sentinel.prev.next;
        size++;
    }
    //链表大小
    public int size(){
        return size;
    }

    //获得某个链表的数字
    public T get(int index){
        if(index > (size-1))
        {
            return null;
        }
        IntNode p =sentinel;
        for(int i=0; i <= index; i++)
        {
            p=p.next;
        }
        return p.item;
    }
    //用递归获得链表的某个数字
    public T getRecursiveHelp(IntNode p,int i){
        if(i==0)
        {
            return p.next.item;
        }
        p=p.next;
        i--;
        return getRecursiveHelp(p,i);
    }
    public T getRecursive(int index){
        if(index > (size-1))
        {
            return null;
        }
        IntNode p =sentinel;
        return getRecursiveHelp(p,index);
    }
    //移除开头结尾的
    public void removeFirst(){
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
    }
    public void removeLast(){
        sentinel.prev.prev.next = sentinel;
        sentinel.prev=sentinel.prev.prev;
        size--;
    }
    public void remove(int index){
        IntNode p = sentinel;
        IntNode q = sentinel;
        for(int i = 0; i <= index; i++)
        {
            p = p.next;
        }
        for(int i = 0; i <= index-1; i++)
        {
            q=q.next;
        }
        p.next.prev = q.next;
        q.next = p.next;
    }
    //打印链表
    public void printDeque(){
        IntNode p =sentinel;
        while(p.next != sentinel)
        {
            System.out.print(p.next.item + " ");
            p=p.next;
        }
        System.out.println();
    }
    //主函数
    public  static void main(String[] args){
        LinkedListDeque<Integer> L = new LinkedListDeque<>(1);
        L.addFirst(100);
        L.addFirst(200);
        L.addLast(10);
        L.addLast(20);

        System.out.println("length " + L.size());
        L.printDeque();
        System.out.println(L.get(0));
        System.out.println(L.get(2));
        System.out.println(L.get(4));
        System.out.println(L.getRecursive(0));
        System.out.println(L.getRecursive(2));
        System.out.println(L.getRecursive(4));
        L.remove(3);
        L.printDeque();

    }
}
