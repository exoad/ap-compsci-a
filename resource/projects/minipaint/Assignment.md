# Assignment Details

Create a mini paint program that allows the user to draw a line on the screen based upon their mouse clicks. You will need the following import statements.

```java
import javax.swing.*;
import java.awt.Color.*;
import java.awt.*;
import java.awt.event.*;
```

In addition, you will need to add the following lines of code to your driver class so that the graphics show up properly on my Mac. You will need to import swing in your driver class to do so.

```java
try {
    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
} catch (Exception e) {
    e.printStackTrace();
}
```

Your program must also allow the user to select between a minimum of 4 colors and have at least one other option besides just drawing lines.
