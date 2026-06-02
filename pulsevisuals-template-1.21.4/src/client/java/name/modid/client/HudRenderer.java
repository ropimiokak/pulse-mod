package name.modid;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class HudRenderer implements HudRenderCallback {
    @Override
    public void onHudRender(GuiGraphics gui, float delta) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.options.hideGui || mc.player == null) return;

        // 1. Отрисовка текста FPS (Классика)
        String fpsText = "FPS: " + mc.getFps();
        gui.drawString(mc.font, fpsText, Modules.hudX, Modules.hudY, 0xFFFFFF);

        // 2. Отрисовка ArmorHUD (Броня по слотам)
        int ax = Modules.armorX;
        int ay = Modules.armorY;
        for (int i = 0; i < 4; i++) {
            ItemStack stack = mc.player.getInventory().armor.get(i);
            if (!stack.isEmpty()) {
                gui.renderItem(stack, ax + (i * 20), ay);
            }
        }

        // 3. Отрисовка PotionHUD (Список эффектов)
        int px = Modules.potionX;
        int py = Modules.potionY;
        mc.player.getActiveEffects().forEach(effect -> {
            String name = effect.getEffect().value().getDescriptionId();
            gui.drawString(mc.font, Component.literal(name), px, py, 0xFF00FF); // Розовый цвет эффектов
        });
        
        // 4. Координаты игрока (XYZ)
        String pos = String.format("XYZ: %.0f, %.0f, %.0f", mc.player.getX(), mc.player.getY(), mc.player.getZ());
        gui.drawString(mc.font, pos, 10, 200, 0x00FF00); // Зеленые координаты
    }
}
