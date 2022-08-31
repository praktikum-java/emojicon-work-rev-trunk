package ru.practicum.emojicon.engine;

import com.googlecode.lanterna.TextColor;
import com.vdurmont.emoji.Emoji;

public class PixelFrame implements Frame {
    private final RootFrame rootFrame;
    private final int rootLeft;
    private final int rootTop;
    private final int rootRight;
    private final int rootBottom;
    private final int left;
    private final int top;
    private final int right;
    private final int bottom;

    private int posX;

    private int posY;

    private int rootPosX;

    private int rootPosY;

    private TextColor backgroundColor;

    private TextColor color;

    @Override
    public int getLeft() {
        return left;
    }

    @Override
    public int getTop() {
        return top;
    }

    @Override
    public int getRight() {
        return right;
    }

    @Override
    public int getBottom() {
        return bottom;
    }

    public PixelFrame(RootFrame rootFrame, int rootLeft, int rootTop, int rootRight, int rootBottom) {
        this.rootFrame = rootFrame;
        this.rootLeft = rootLeft;
        this.rootTop = rootTop;
        this.rootRight = rootRight;
        this.rootBottom = rootBottom;
        this.left = 0;
        this.top = 0;
        this.right = rootRight - rootLeft + 1;
        this.bottom = rootBottom - rootTop + 1;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    public TextColor getTransparentColor() {
        return backgroundColor;
    }

    @Override
    public TextColor getFillColor() {
        return backgroundColor;
    }

    @Override
    public TextColor getColor() {
        return color;
    }

    @Override
    public void setPosition(int x, int y) {
        //TODO
    }

    @Override
    public void setFillColor(TextColor fillColor) {
        this.backgroundColor = fillColor;
    }

    @Override
    public void setColor(TextColor color) {
        this.color = color;
    }

    @Override
    public Point paint() {
        //TODO
        return null;
    }

    @Override
    public Point draw(Character character) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public Point draw(Emoji emoji) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public Frame getRoot() {
        return rootFrame;
    }
}
