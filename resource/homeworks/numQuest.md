Consider the following instance variable and method.
private List&lt;Integer&gt; nums;
```java
/** Precondition: nums.size > 0
 */
public void numQuest() {
    int k = 0;
    Integer zero = new Integer(0);
    while (k & lt; nums.size()) {
        if (nums.get(k).equals(zero))
            nums.remove(k);
        k++;
    }
}
```
Assume that List nums initially contains the following Integer values.

[0, 0, 4, 2, 5, 0, 3, 0]

What will List nums contain as a result of executing numQuest?
A) [0, 0, 4, 2, 5, 0, 3, 0]
B) [4, 2, 5, 3]
C) [0, 0, 0, 0, 4, 2, 5, 3]
D) [3, 5, 2, 4, 0, 0, 0, 0]
E) [0, 4, 2, 5, 3]

`Answer : B`
