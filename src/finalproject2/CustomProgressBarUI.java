package finalproject2;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class CustomProgressBarUI extends BasicProgressBarUI {
    @Override
    protected Color getSelectionBackground() {
        return Color.black; // 設置文字顏色
    }

    @Override
    protected Color getSelectionForeground() {
        return Color.black; // 設置文字顏色
    }
}
