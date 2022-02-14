<h1>#1</h1>

```java
public Reservation requestRoom(String guestName) {
  for(int i = 0; i < rooms.length; i++) {
    if(room[i] == null) {
      room[i] = new Reservation(guestName, i);
      retur room[i];
    }
  }
  waitList.add(guestname);
  return null;
}
```

<h1>#2</h1>

```java
public Reservation cancelAndReassign(Reservation res) {
  Reservation someone = null;
  if(!waitList.isEmpty()) {
    someone = new Reservation((String) waitList.get(0), res.getRoomNumber());
    waitList.remove(0);
  }
  rooms[i] = someone;
  return someone;
}
```
