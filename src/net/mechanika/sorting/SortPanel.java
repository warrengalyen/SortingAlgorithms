package net.mechanika.sorting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class SortPanel extends JPanel implements Runnable {
    private static final long serialVersionUID = 1L;
    protected static final int BORDER_WIDTH = 10;
    private Dimension preferredDimension;
    protected int size;
    protected int[] list;
    protected int sleepTime;
    private String name;

    public SortPanel(String name, int sleepTime, int width, int height) {
        preferredDimension = new Dimension(width, height);
        this.name = name;
        this.sleepTime = sleepTime;
        setBackground(Color.BLACK);
    }

    public void setList(int[] list) {
        reset();
        this.size = list.length;
        this.list = java.util.Arrays.copyOf(list, size);
        setBackground(Color.BLACK);
    }

    @Override
    public Dimension getPreferredSize() {
        return preferredDimension;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw border
        g.setColor(Color.WHITE);
        g.drawRect(BORDER_WIDTH, BORDER_WIDTH, getWidth() - 2 * BORDER_WIDTH, getHeight() - 2 * BORDER_WIDTH);

        // draw title
        Font nameFont = new Font(Font.MONOSPACED, Font.BOLD, 18);
        FontMetrics nameFontMetric = getFontMetrics(nameFont);
        g.setColor(Color.BLACK);
        g.fillRect((getWidth() - nameFontMetric.stringWidth(name)) / 2, 0, nameFontMetric.stringWidth(name),BORDER_WIDTH + nameFontMetric.getAscent() / 3);
        g.setColor(Color.WHITE);
        g.setFont(nameFont);
        g.drawString(name, (getWidth() - nameFontMetric.stringWidth(name)) / 2, BORDER_WIDTH + nameFontMetric.getAscent() / 3);
    }

    @Override
    public abstract void run();

    public abstract void reset();
}