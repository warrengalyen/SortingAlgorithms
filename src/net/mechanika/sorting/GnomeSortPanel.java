package net.mechanika.sorting;

import java.awt.Color;
import java.awt.Graphics;

public class GnomeSortPanel extends SortPanel {
    private static final long serialVersionUID = 1L;
    private int redColumn = -1;
    private int greenColumn = -1;

    public GnomeSortPanel(String name, int sleepTime, int width, int height) {
        super(name, sleepTime, width, height);
    }

    @Override
    public void reset() {
        redColumn = -1;
        greenColumn = 1;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < list.length;) {
                if (list[i - 1] <= list[i]) {
                    if (greenColumn <= ++i) {
                        greenColumn = (i < list.length) ? i : i - 1;
                        redColumn = -1;
                        repaint();
                    }
                } else {
                    Thread.sleep(3 * sleepTime);
                    repaint();
                    redColumn = i;
                    repaint();
                    Thread.sleep(4 * sleepTime);
                    int tempVal = list[i];
                    list[i] = list[i - 1];
                    list[i - 1] = tempVal;
                    --i;
                    if (i == 0) {
                        redColumn = -1;
                        i = 1;
                    }
                }
                repaint();
            }
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
        for (int i = (greenColumn == -1 ? 0 : greenColumn); i < list.length; i++) {
            g.setColor(Color.WHITE);
            g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
            g.setColor(Color.BLACK);
            g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
        }
        for (int i = 0; i <= greenColumn; i++) {
            g.setColor(Color.GREEN);
            g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
            g.setColor(Color.BLACK);
            g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
        }
        if(redColumn != -1) {
            g.setColor(Color.RED);
            g.fillRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight);
            g.setColor(Color.BLACK);
            g.drawRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight);
        }
    }
}
