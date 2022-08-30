package ru.practicum.emojicon.ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.screen.Screen;
import ru.practicum.emojicon.engine.Frame;
import ru.practicum.emojicon.engine.RootFrame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EmojiSplashCatFace extends EmojiSplash {

    private static final String KITTY_0 = "" +
            "          .__....._             _.....__,\n" +
            "            .\": o :':         ;': o :\".\n" +
            "            `. `-' .'.       .'. `-' .'\n" +
            "              `---'             `---'\n" +
            "\n" +
            "    _...----...      ...   ...      ...----..._\n" +
            " .-'__..-\"\"'----    `.  `\"`  .'    ----'\"\"-..__`-.\n" +
            "'.-'   _.--\"\"\"'       `-._.-'       '\"\"\"--._   `-.`\n" +
            "'  .-\"'                  :                  `\"-.  `\n" +
            "  '   `.              _.'\"'._              .'   `\n" +
            "        `.       ,.-'\"       \"'-.,       .'\n" +
            "          `.                           .'\n" +
            "            `-._                   _.-'\n" +
            "                `\"'--...___...--'\"`";

    private static final String BANNER_0 = "" +
            "\n" +
            "▄███▄   █▀▄▀█ ████▄   ▄▄▄▄▄ ▄█ ▄█▄    ████▄    ▄   \n" +
            "█▀   ▀  █ █ █ █   █ ▄▀  █   ██ █▀ ▀▄  █   █     █  \n" +
            "██▄▄    █ ▄ █ █   █     █   ██ █   ▀  █   █ ██   █ \n" +
            "█▄   ▄▀ █   █ ▀████  ▄ █    ▐█ █▄  ▄▀ ▀████ █ █  █ \n" +
            "▀███▀      █          ▀      ▐ ▀███▀        █  █ █ \n" +
            "          ▀                                 █   ██ \n" +
            "                                                   \n";

    private TextImage catImageCache;


    public EmojiSplashCatFace(long hideTimeoutMsec, Runnable onClose) {
        super(hideTimeoutMsec, onClose);
    }

    @Override
    public void drawSplash(Frame someFrame) {
        RootFrame frame = RootFrame.extend(someFrame);
        Screen screen = frame.getScreen();
        TextGraphics text = screen.newTextGraphics();
        TextImage imageCat = getCatImage();
        TerminalPosition lt = new TerminalPosition((screen.getTerminalSize().getColumns() / 2) - (imageCat.getSize().getColumns() / 2), 2);
        text.drawImage(lt, imageCat);
    }

    private TextImage getCatImage() {
        if(catImageCache != null){
            return catImageCache;
        }
        List<List<TextCharacter>> charMatrix = new ArrayList<>();
        Arrays.stream(KITTY_0.split("\n")).map(String::chars).forEach(line -> {
            charMatrix.add(line.mapToObj(ch -> TextCharacter.fromCharacter((char) ch)).flatMap(Arrays::stream).collect(Collectors.toList()));
        });
        int maxColumns = 0;
        for (List<TextCharacter> line : charMatrix) {
            maxColumns = Math.max(maxColumns, line.size());
        }
        int maxRows = charMatrix.size();
        catImageCache = new BasicTextImage(new TerminalSize(maxColumns, maxRows));
        catImageCache.setAll(TextCharacter.fromCharacter(' ')[0]);
        for (int row = 0; row < charMatrix.size(); row++) {
            List<TextCharacter> line = charMatrix.get(row);
            for (int column = 0; column < line.size(); column++) {
                catImageCache.setCharacterAt(column, row, line.get(column));
            }
        }
        return catImageCache;
    }
}
