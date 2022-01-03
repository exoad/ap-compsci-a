import static java.lang.System.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {
    private static abstract class BubbleSort {
        private BubbleSort() {}

        
    }

    private static abstract class SelectionSort {
        private SelectionSort() {}


    }

    private static abstract class InsertionSort {
        private InsertionSort() {}

    }

    private static class GUI implements ActionListener {
        private static final JButton bubbleSort, selectionSort, insertionSort;

        @Override
        public boid actionPerformed(ActionEvent e) {
            
        }
    }

    private static class Rectangles {
        private final int value;
        public Rectangles(int value) {
            assert value != null;
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    }

    public Window() {

    }
}
