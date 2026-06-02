package name.modid;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HudRenderer implements HudRenderCallback {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public void onHudRender(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        Minecraft client = Minecraft.getInstance();
        
        if (client.options.hideGui || client.player == null) return;

        int backgroundColor = 0xD50A111E; 
        int activeKeyColor = 0x9000FFFF; 

        int hX = Modules.hudX;
        int hY = Modules.hudY;

        // 1. ТОП БАР
        String serverType = client.isSingleplayer() ? "Singleplayer" : "Multiplayer";
        // ИСПРАВЛЕНО: заменено на client.getFps()
        String watermarkText = "pulse visuals  |  " + client.getUser().getName() + "  |  " + client.getFps() + " fps  |  " + serverType;
        int watermarkWidth = client.font.width(watermarkText);
        
        guiGraphics.fill(hX, hY, hX + 10 + watermarkWidth, hY + 17, backgroundColor);
        // ИСПРАВЛЕНО: убран несовместимый параметр false в конце
        guiGraphics.drawString(client.font, watermarkText, hX + 5, hY + 5, 0xFFFFFFFF);

        // 2. ЧАСЫ
        String timeText = "Time: " + LocalTime.now().format(TIME_FORMAT);
        int timeWidth = client.font.width(timeText);
        
        guiGraphics.fill(hX, hY + 22, hX + 10 + timeWidth, hY + 39, backgroundColor);
        guiGraphics.drawString(client.font, timeText, hX + 5, hY + 26, 0xFFFFFFFF);

        // 3. KEYSTROKES (КЛАВИШИ)
        int keySize = 18;
        int gap = 3;
        int kX = hX;
        int kY = hY + 44;

        // W
        boolean wPressed = client.options.keyUp.isDown();
        guiGraphics.fill(kX + keySize + gap, kY, kX + (keySize * 2) + gap, kY + keySize, wPressed ? activeKeyColor : backgroundColor);
        guiGraphics.drawCenteredString(client.font, "W", kX + keySize + gap + (keySize / 2), kY + 5, wPressed ? 0xFF000000 : 0xFFFFFFFF);

        // A
        boolean aPressed = client.options.keyLeft.isDown();
        guiGraphics.fill(kX, kY + keySize + gap, kX + keySize, kY + (keySize * 2) + gap, aPressed ? activeKeyColor : backgroundColor);
        guiGraphics.drawCenteredString(client.font, "A", kX + (keySize / 2), kY + keySize + gap + 5, aPressed ? 0xFF000000 : 0xFFFFFFFF);

        // S
        boolean sPressed = client.options.keyDown.isDown();
        guiGraphics.fill(kX + keySize + gap, kY + keySize + gap, kX + (keySize * 2) + gap, kY + (keySize * 2) + gap, sPressed ? activeKeyColor : backgroundColor);
        guiGraphics.drawCenteredString(client.font, "S", kX + keySize + gap + (keySize / 2), kY + keySize + gap + 5, sPressed ? 0xFF000000 : 0xFFFFFFFF);

        // D
        boolean dPressed = client.options.keyRight.isDown();
        guiGraphics.fill(kX + (keySize * 2) + (gap * 2), kY + keySize + gap, kX + (keySize * 3) + (gap * 2), kY + (keySize * 2) + gap, dPressed ? activeKeyColor : backgroundColor);
        guiGraphics.drawCenteredString(client.font, "D", kX + (keySize * 2) + (gap * 2) + (keySize / 2), kY + keySize + gap + 5, dPressed ? 0xFF000000 : 0xFFFFFFFF);

        // SPACE
        boolean spacePressed = client.options.keyJump.isDown();
        guiGraphics.fill(kX, kY + (keySize * 2) + (gap * 2), kX + (keySize * 3) + (gap * 2), kY + (keySize * 2) + (gap * 2) + 12, spacePressed ? activeKeyColor : backgroundColor);
        guiGraphics.drawCenteredString(client.font, "SPACE", kX + ((keySize * 3 + gap * 2) / 2), kY + (keySize * 2) + (gap * 2) + 2, spacePressed ? 0xFF000000 : 0xFFFFFFFF);

        // 4. СПИСОК АКТИВНЫХ МОДУЛЕЙ
        int listX = kX + (keySize * 3) + (gap * 2) + 10;
        int listY = kY;
        
        guiGraphics.fill(listX, listY, listX + 80, listY + 15, backgroundColor);
        guiGraphics.drawString(client.font, "Модули", listX + 4, listY + 4, 0x9000FFFF);
        
        int offset = 18;
        if (Modules.autoSprint) {
            guiGraphics.fill(listX, listY + offset, listX + 80, listY + offset + 14, backgroundColor);
            guiGraphics.drawString(client.font, "> AutoSprint", listX + 6, listY + offset + 3, 0xFFFFFFFF);
            offset += 16;
        }
        if (Modules.jumpCircles) {
            guiGraphics.fill(listX, listY + offset, listX + 80, listY + offset + 14, backgroundColor);
            guiGraphics.drawString(client.font, "> JumpCircles", listX + 6, listY + offset + 3, 0xFFFFFFFF);
            offset += 16;
        }
        if (Modules.esp) {
            guiGraphics.fill(listX, listY + offset, listX + 80, listY + offset + 14, backgroundColor);
            guiGraphics.drawString(client.font, "> ESP", listX + 6, listY + offset + 3, 0xFFFFFFFF);
        }
    }
}
