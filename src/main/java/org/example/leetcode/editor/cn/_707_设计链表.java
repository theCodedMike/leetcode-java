package org.example.leetcode.editor.cn;
//你可以选择使用单链表或者双链表，设计并实现自己的链表。
//
// 单链表中的节点应该具备两个属性：val 和 next 。val 是当前节点的值，next 是指向下一个节点的指针/引用。 
//
// 如果是双向链表，则还需要属性 prev 以指示链表中的上一个节点。假设链表中的所有节点下标从 0 开始。 
//
// 实现 MyLinkedList 类： 
//
// 
// MyLinkedList() 初始化 MyLinkedList 对象。 
// int get(int index) 获取链表中下标为 index 的节点的值。如果下标无效，则返回 -1 。 
// void addAtHead(int val) 将一个值为 val 的节点插入到链表中第一个元素之前。在插入完成后，新节点会成为链表的第一个节点。 
// void addAtTail(int val) 将一个值为 val 的节点追加到链表中作为链表的最后一个元素。 
// void addAtIndex(int index, int val) 将一个值为 val 的节点插入到链表中下标为 index 的节点之前。如果 
//index 等于链表的长度，那么该节点会被追加到链表的末尾。如果 index 比长度更大，该节点将 不会插入 到链表中。 
// void deleteAtIndex(int index) 如果下标有效，则删除链表中下标为 index 的节点。 
// 
//
// 
//
// 示例： 
//
// 
//输入
//["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", 
//"deleteAtIndex", "get"]
//[[], [1], [3], [1, 2], [1], [1], [1]]
//输出
//[null, null, null, null, 2, null, 3]
//
//解释
//MyLinkedList myLinkedList = new MyLinkedList();
//myLinkedList.addAtHead(1);
//myLinkedList.addAtTail(3);
//myLinkedList.addAtIndex(1, 2);    // 链表变为 1->2->3
//myLinkedList.get(1);              // 返回 2
//myLinkedList.deleteAtIndex(1);    // 现在，链表变为 1->3
//myLinkedList.get(1);              // 返回 3
// 
//
// 
//
// 提示： 
//
// 
// 0 <= index, val <= 1000 
// 请不要使用内置的 LinkedList 库。 
// 调用 get、addAtHead、addAtTail、addAtIndex 和 deleteAtIndex 的次数不超过 2000 。 
// 
//
// Related Topics 设计 链表 👍 950 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

class MyLinkedList {
    private int len;
    private Node head;
    private Node tail;

    static class Node {
        int elem;
        Node prev;
        Node next;

        Node(int elem) {
            this.elem = elem;
            this.prev = null;
            this.next = null;
        }
    }
    public MyLinkedList() {
        this.len = 0;
        this.head = null;
        this.tail = null;
    }
    
    public int get(int index) {
        if (this.len == 0 || index < 0 || index >= this.len) {
            return -1;
        }
        Node p = this.head;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.elem;
    }
    
    public void addAtHead(int val) {
        Node new_node = new Node(val);
        if (this.len == 0) {
            this.head = new_node;
            this.tail = new_node;
        } else {
            this.head.prev = new_node;
            new_node.next = this.head;
            this.head = new_node;
        }
        this.len++;
    }
    
    public void addAtTail(int val) {
        Node new_node = new Node(val);
        if (this.len == 0) {
            this.head = new_node;
            this.tail = new_node;
        } else {
            new_node.prev = this.tail;
            this.tail.next = new_node;
            this.tail = new_node;
        }
        this.len++;
    }
    
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > this.len) {
            return;
        }
        if (index == 0) {
            this.addAtHead(val);
        } else if (index == this.len) {
            this.addAtTail(val);
        } else {
            Node new_node = new Node(val);
            Node prev = this.head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            Node next = prev.next;
            next.prev = new_node;
            new_node.next = next;
            new_node.prev = prev;
            prev.next = new_node;
            this.len++;
        }
    }

    public int deleteAtHead() {
        if (this.len == 0) {
            return -1;
        }
        Node head = this.head;
        this.head = head.next;
        this.len--;
        if (this.len == 0) {
            this.tail = null;
        }

        return head.elem;
    }

    public int deleteAtTail() {
        if (this.len == 0) {
            return -1;
        }
        Node tail = this.tail;
        this.tail = tail.prev;
        this.len--;
        if (this.len == 0) {
            this.head = null;
        }
        return tail.elem;
    }
    public int deleteAtIndex(int index) {
        if (this.len == 0 || index < 0 || index >= this.len) {
            return -1;
        }
        if (index == 0) {
            return this.deleteAtHead();
        } else if (index == this.len - 1) {
            return this.deleteAtTail();
        } else {
            Node del = this.head;
            for (int i = 0; i < index; i++) {
                del = del.next;
            }
            Node prev = del.prev;
            Node next = del.next;
            next.prev = prev;
            prev.next = next;
            this.len--;
            return del.elem;
        }
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
//leetcode submit region end(Prohibit modification and deletion)
