<h1>a</h1>

```java
public void forwardMoveBlocked() {
  if((facingright && pos == hall.length - 1) || (!facingRight && pos == 0)) 
    return true;
  return false;
}
```

<h1>b</h1>

```java
private void move() {
  if(hall[pos] > 0) 
    hall[pos]--;
  else if(hall[pos] == 0) {
    if(facingRight)
      pos++;
    else if(forwardMoveBlocked()) 
      facingRight = false;
    else
      pos--;
  }
}
```

<h1>c</h1>

```java
public int clearHall() {
  int i = 0;
  while(!hallisClear()) {
    move();
    i++;
  }
  return i;
}
```
