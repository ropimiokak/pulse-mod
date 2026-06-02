package name.modid;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class VisualsMenuScreen extends Screen {
    private String draggingElement = null;

    public VisualsMenuScreen() { super(Component.literal("ClickGUI")); }

    @Override
    public boolean mouseClicked(double mX, double mY, int b) {
        if (b == 0) {
            // Проверка попадания мышки в области элементов
            if (isOver(mX, mY, Modules.hudX, Modules.hudY, 100, 20)) draggingElement = "HUD";
            else if (isOver(mX, mY, Modules.armorX, Modules.armorY, 80, 20)) draggingElement = "ARMOR";
            else if (isOver(mX, mY, Modules.potionX, Modules.potionY, 100, 20)) draggingElement = "POTION";
            else if (isOver(mX, mY, Modules.fpsX, Modules.fpsY, 50, 20)) draggingElement = "FPS";
            else if (isOver(mX, mY, Modules.xyzX, Modules.xyzY, 100, 20)) draggingElement = "XYZ";
        }
        return super.mouseClicked(mX, mY, b);
    }

    private boolean isOver(double mX, double mY, int x, int y, int w, int h) {
        return mX >= x && mX <= x + w && mY >= y && mY <= y + h;
    }

    @Override
    public boolean mouseDragged(double mX, double mY, int b, double dX, double dY) {
        if ("HUD".equals(draggingElement)) { Modules.hudX = (int)mX; Modules.hudY = (int)mY; }
        else if ("ARMOR".equals(draggingElement)) { Modules.armorX = (int)mX; Modules.armorY = (int)mY; }
        else if ("POTION".equals(draggingElement)) { Modules.potionX = (int)mX; Modules.potionY = (int)mY; }
        else if ("FPS".equals(draggingElement)) { Modules.fpsX = (int)mX; Modules.fpsY = (int)mY; }
        else if ("XYZ".equals(draggingElement)) { Modules.xyzX = (int)mX; Modules.xyzY = (int)mY; }
        return super.mouseDragged(mX, mY, b, dX, dY);
    }

    @Override
    public boolean mouseReleased(double mX, double mY, int b) { draggingElement = null; return super.mouseReleased(mX, mY, b); }
}
