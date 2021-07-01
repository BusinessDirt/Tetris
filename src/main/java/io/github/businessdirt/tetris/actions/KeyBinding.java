package io.github.businessdirt.tetris.actions;

import javax.swing.*;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;

public class KeyBinding {

    private static JLabel listener = new JLabel();

    public KeyBinding(int keyCode, AbstractAction action) {
        this(keyCode, action, getModifier(keyCode));
    }

    private KeyBinding(int key, AbstractAction action, int modifier) {
        listener.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key, modifier), key);
        listener.getActionMap().put(key, action);
    }

    private static int getModifier(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_CONTROL:
                return InputEvent.CTRL_DOWN_MASK;
            case KeyEvent.VK_SHIFT:
                return InputEvent.SHIFT_DOWN_MASK;
            case KeyEvent.VK_ALT:
                return InputEvent.ALT_DOWN_MASK;
            default:
                return 0;
        }
    }

    public static JLabel getKeyListener() {
        return listener;
    }
}
