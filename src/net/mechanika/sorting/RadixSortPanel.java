package net.mechanika.sorting;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class RadixSortPanel extends SortPanel {
    private static final long serialVersionUID = 1L;
    private int redColumn = -1;
    private int blueColumn = -1;
    private int cyanColumn = -1;
    private int greenColumn = -1;
    final int RADIX = 10;

    public RadixSortPanel(String name, int sleepTime, int width, int height) {
        super(name, sleepTime, width, height);
    }

    @Override
    public void reset() {
        redColumn = -1;
        blueColumn = -1;
        greenColumn = -1;
        cyanColumn = -1;
    }

    @Override
    public void run() {
        try {

            boolean maxLength = false;
            int tmp = -1, placement = 1, digit;

            List<Integer>[] bucket = new ArrayList[RADIX];
            for (int i = 0; i < bucket.length; i++) {
                bucket[i] = new ArrayList<Integer>();
            }

            while (!maxLength) {
                maxLength = true;

                for (int i = 0; i < list.length; i++) {
                    tmp = list[i] / placement;
                    digit = tmp % 10;
                    bucket[digit].add(list[i]);
                    if (maxLength && tmp > 0) {
                        maxLength = false;
                    }
                    // cyanColumn = (i % 10) == 0 ? i : -1;
                    // repaint();
                    // Thread.sleep(2 * sleepTime);
                }

                // System.out.println("second loop");

                int a = 0;
                for (int b = 0; b < RADIX; b++) {
                    for (Integer i : bucket[b]) {
                        // list[a++] = count[b][i];
                        redColumn = a;
                        if (maxLength) {
                            greenColumn = a;
                        }
                        list[a++] = i;
                        repaint();
                        Thread.sleep(2 * sleepTime);
                    }
                    bucket[b].clear();
                }
                // greenColumn = a;
                // greenColumn--;
                redColumn = -1;
                repaint();
                Thread.sleep(4 * sleepTime);

                placement *= RADIX;
            }

            // for (int i = 0; i < list.length; i++) {
            //     greenColumn = i;
            //     repaint();
            //     Thread.sleep(sleepTime);
            // }
            redColumn = -1;
            cyanColumn = -1;
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
        if(blueColumn != -1) {
            g.setColor(Color.BLUE);
            g.fillRect(2 * BORDER_WIDTH + columnWidth * blueColumn, getHeight() - list[blueColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[blueColumn] * columnHeight);
            g.setColor(Color.BLACK);
            g.drawRect(2 * BORDER_WIDTH + columnWidth * blueColumn, getHeight() - list[blueColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[blueColumn] * columnHeight);
        }
        if(cyanColumn != -1) {
            g.setColor(Color.CYAN);
            g.fillRect(2 * BORDER_WIDTH + columnWidth * cyanColumn, getHeight() - list[cyanColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[cyanColumn] * columnHeight);
            g.setColor(Color.BLACK);
            g.drawRect(2 * BORDER_WIDTH + columnWidth * cyanColumn, getHeight() - list[cyanColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[cyanColumn] * columnHeight);
        }
    }
}
