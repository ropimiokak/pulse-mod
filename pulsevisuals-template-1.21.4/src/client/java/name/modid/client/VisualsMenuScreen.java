package name.modid;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class VisualsMenuScreen extends Screen {
    public VisualsMenuScreen() { super(Component.literal("ClickGUI")); }

    @Override
    public void render(GuiGraphics gui, int mX, int mY, float delta) {
        super.render(gui, mX, mY, delta);
        // Рисуем кнопку KillAura
        gui.fill(50, 50, 150, 70, 0xFF222222);
        gui.drawString(this.font, Component.literal("KillAura"), 55, 55, 0xFFFFFF);

        // Если открыто подменю настроек - рисуем его
        if (Modules.showingSettings) {
            gui.fill(Modules.settingsX, Modules.settingsY, Modules.settingsX + 100, Modules.settingsY + 60, 0xFF333333);
            gui.drawString(this.font, Component.literal("Mode: " + Modules.auraMode), Modules.settingsX + 5, Modules.settingsY + 5, 0x00FF00);
            gui.drawString(this.font, Component.literal("Range: " + Modules.auraRange), Modules.settingsX + 5, Modules.settingsY + 25, 0xFFFFFF);
        }
    }

    @Override
    public boolean mouseClicked(double mX, double mY, int b) {
        // Правая кнопка (b == 1) на кнопку KillAura
        if (b == 1 && mX >= 50 && mX <= 150 && mY >= 50 && mY <= 70) {
            Modules.showingSettings = !Modules.showingSettings;
            Modules.settingsX = (int)mX;
            Modules.settingsY = (int)mY;
            return true;
        }
        // Левая кнопка для смены режима в подменю
        if (b == 0 && Modules.showingSettings) {
            Modules.auraMode = Modules.auraMode.equals("Legit") ? "FT" : "Legit";
        }
        return super.mouseClicked(mX, mY, b);
    }
}
