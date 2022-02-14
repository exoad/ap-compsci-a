```java
public String[] removeWordsOfLength(String[] arr, int length) {
  int len = arr.length;
  for(String i : arr) {
    if(i.length() == length)
      len--;
  }
  String[] temp = new String[len];
  len = 0;
  for(int i = 0; i < arr.length; i++) {
    if(arr[i].length != length) {
      temp[len] = arr[i];
      len++;
    }
  }
  return temp;
}
```
