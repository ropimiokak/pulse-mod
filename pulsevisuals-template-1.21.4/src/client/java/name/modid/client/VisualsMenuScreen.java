package name.modid;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class VisualsMenuScreen extends Screen {
    private String draggingElement = null;

    public VisualsMenuScreen() { super(Component.literal("ClickGUI")); }

    @Override
    public boolean mouseClicked(double mX, double mY, int b) {
        if (b == 0) {
            if (mX >= Modules.hudX && mX <= Modules.hudX + 100 && mY >= Modules.hudY && mY <= Modules.hudY + 20) draggingElement = "HUD";
            else if (mX >= Modules.armorX && mX <= Modules.armorX + 50 && mY >= Modules.armorY && mY <= Modules.armorY + 50) draggingElement = "ARMOR";
            else if (mX >= Modules.potionX && mX <= Modules.potionX + 50 && mY >= Modules.potionY && mY <= Modules.potionY + 20) draggingElement = "POTION";
        }
        return super.mouseClicked(mX, mY, b);
    }

    @Override
    public boolean mouseDragged(double mX, double mY, int b, double dX, double dY) {
        if ("HUD".equals(draggingElement)) { Modules.hudX = (int)mX; Modules.hudY = (int)mY; }
        else if ("ARMOR".equals(draggingElement)) { Modules.armorX = (int)mX; Modules.armorY = (int)mY; }
        else if ("POTION".equals(draggingElement)) { Modules.potionX = (int)mX; Modules.potionY = (int)mY; }
        return super.mouseDragged(mX, mY, b, dX, dY);
    }

    @Override
    public boolean mouseReleased(double mX, double mY, int b) { draggingElement = null; return super.mouseReleased(mX, mY, b); }
}
