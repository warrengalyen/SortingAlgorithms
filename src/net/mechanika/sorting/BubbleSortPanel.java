package net.mechanika.sorting;

import java.awt.Color;
import java.awt.Graphics;

public class BubbleSortPanel extends SortPanel {
    private static final long serialVersionUID = 1L;
    private int redColumn = -1;
    private int greenColumn = -1;

    public BubbleSortPanel(String name, int sleepTime, int width, int height) {
        super(name, sleepTime, width, height);
    }

    @Override
    public void reset() {
        redColumn = -1;
        greenColumn = -1;
    }

    @Override
    public void run() {
        try {
            boolean needNextPass = true;
            for (int i = 1; i < list.length && needNextPass; i++) {
                needNextPass = false;
                for (int j = 0; i < list.length - i; i++) {
                    redColumn = j;
                    repaint();
                    Thread.sleep(4 * sleepTime);
                    if (list[j] > list[j + 1]) {
                        redColumn = j + 1;
                        int temp = list[j];
                        list[j] = list[j + 1];
                        list[j + 1] = temp;
                        repaint();
                        Thread.sleep(4 * sleepTime);
                        needNextPass = true;
                    }
                }
                greenColumn = size - i;
            }
            greenColumn = 0;
            redColumn = -1;
        } catch (InterruptedException e) {
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int columnWidth = (getWidth() - 4 * BORDER_WIDTH) / size;
        int columnHeight = (getHeight() - 4 * BORDER_WIDTH) / size;
        for (int i = 0; i < (greenColumn == -1 ? list.length : greenColumn); i++) {
            g.setColor(Color.WHITE);
            g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
            g.setColor(Color.BLACK);
            g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
        }
        if (greenColumn != -1) {
            for (int i = greenColumn; i < list.length; i++) {
                g.setColor(Color.GREEN);
                g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
                g.setColor(Color.BLACK);
                g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
            }
        }
        if (redColumn != -1) {
            g.setColor(Color.RED);
            g.fillRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight);
            g.setColor(Color.BLACK);
            g.drawRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight);
        }
    }
}
