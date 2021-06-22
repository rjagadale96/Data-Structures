//Space O(n)
// for put operation time is O(1) and overall TIme comlexity is O(n)

 class MyHashMap<K, V> {
 
  private class Entry<K, V> {
    private K key;
    private V value;
    private Entry<K, V> next;

    public Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public K getKey() {
      return this.key;
    }

    public V getValue() {
      return this.value;
    }

    public void setValue(V value) {
      this.value = value;
    }

    @Override
    public String toString() {
      Entry<K,V> temp = this;
      StringBuilder sb = new StringBuilder();
      while (temp != null) {
        sb.append(temp.key + " -> " + temp.value + ",");
        temp = temp.next;
      }
      return sb.toString();
    }

  }

  private final int SIZE = 5;

  private Entry<K, V> table[];

  public MyHashMap() {
    table = new Entry[SIZE];
  }

  public void put(K key, V value) {
    int hash = key.hashCode() % SIZE;
    Entry<K, V> e = table[hash];

    if (e == null) {
      table[hash]= new Entry<K, V>(key, value);
    } else {
      while (e.next != null) {
        if (e.getKey() == key) {
          e.setValue(value);
          return;
        }
        e = e.next;
      }

      if (e.getKey() == key) {
        e.setValue(value);
        return;
      }

      e.next = new Entry<K, V>(key, value);
    }
  }

  public V get(K key) {
    int hash = key.hashCode() % SIZE;
    Entry<K, V> e = table[hash];

    if (e == null) {
      return null;
    }

    while (e != null) {
      if (e.getKey() == key) {
        return e.getValue();
      }
      e = e.next;
    }

    return null;
  }

  public Entry<K, V> remove(K key) {
    int hash = key.hashCode() % SIZE;
    Entry<K, V> e = table[hash];

    if (e == null) {
      return null;
    }

    if (e.getKey() == key) {
      table[hash] = e.next;
      e.next = null;
      return e;
    }

    Entry<K, V> prev = e;
    e = e.next;

    while (e != null) {
      if (e.getKey() == key) {
        prev.next = e.next;
        e.next = null;
        return e;
      }
      prev = e;
      e = e.next;
    }

    return null;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < SIZE; i++) {
      if (table[i] != null) {
        sb.append(i + " " + table[i] + "\n");
      } else {
        sb.append(i + " " + "null" + "\n");
      }
    }

    return sb.toString();
  }

}



public class HashTable {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    MyHashMap<String, String> myHashMap = new MyHashMap<String, String>();
    myHashMap.put("A", "B");
    myHashMap.put("E", "F");
    myHashMap.put("H", "P");
    myHashMap.put("P", "2");
    myHashMap.put("1",  "G");
    myHashMap.put("2", "6");
    myHashMap.put("3", "2");
    myHashMap.put("4", "4");
    myHashMap.put("1",  "H");


//    System.out.println(myHashMap);
//    System.out.println(myHashMap.get("A"));
//    System.out.println(myHashMap.remove("A"));
    myHashMap.remove("2");
    System.out.println(myHashMap);
  }

}
