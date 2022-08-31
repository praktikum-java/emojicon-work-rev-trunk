package ru.practicum.emojicon.ui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.jetbrains.annotations.NotNull;
import ru.practicum.emojicon.engine.*;
import ru.practicum.emojicon.model.EmojiWorld;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class EmojiWorldMap implements Drawable, Controller {

    private final EmojiWorld world;

    private boolean visible = false;

    public EmojiWorldMap(EmojiWorld world) {
        this.world = world;
    }

    @Override
    public void handleKey(KeyStroke key) {
        if(key.getKeyType() == KeyType.Character && "MmЬь".contains(String.valueOf(key.getCharacter()))){
            visible = !visible;
        }
    }

    @Override
    public void setSelection(UUID... objectId) {
        //not implemented
    }

    @Override
    public @NotNull List<UUID> getSelection() {
        return Collections.emptyList(); //not implemented
    }

    @Override
    public void drawFrame(Frame someFrame) {
        if(!visible){
            return;
        }
        RootFrame frame = RootFrame.extend(someFrame);
        TerminalSize size = frame.getScreen().getTerminalSize();
        //map will be centered on any screen size with border 1
        Integer mapHeight = Math.min(size.getColumns(), size.getRows()) - 3;
        Integer mapWidth = mapHeight * 2; //console "square"
        TextGraphics graphics = frame.getScreen().newTextGraphics();

        Area area = new Area(size.getColumns() / 2 - mapWidth / 2, size.getRows() / 2 - mapHeight / 2,
                size.getColumns() / 2 + mapWidth / 2, size.getRows() / 2 + mapHeight / 2);
        area.move(0, -1);
        drawMap(new PixelFrame(frame, area.getLeft() + 1, area.getTop() + 1, area.getRight() - 1, area.getBottom() - 1));
        //draw box frame
        graphics.drawLine(area.getLeft(), area.getTop(), area.getRight(), area.getTop(), '─');
        graphics.drawLine(area.getLeft(), area.getBottom(), area.getRight(), area.getBottom(), '─');
        graphics.drawLine(area.getLeft(), area.getTop() + 1, area.getLeft(), area.getBottom() - 1, '│');
        graphics.drawLine(area.getRight(), area.getTop() + 1, area.getRight(), area.getBottom() - 1, '│');
        graphics.putString(area.getLeft(), area.getTop(), "┌");
        graphics.putString(area.getLeft(), area.getBottom(), "└");
        graphics.putString(area.getRight(), area.getTop(), "┐");
        graphics.putString(area.getRight(), area.getBottom(), "┘");

        String mapTitle = "═[ КАРТА ]═";
        graphics.putString(size.getColumns() / 2 - mapTitle.length() / 2, area.getTop(), mapTitle);
    }

    private void drawMap(PixelFrame frame) {

    }
}
