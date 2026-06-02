package name.modid.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputConstants;
import name.modid.VisualsMenuScreen;
import name.modid.HudRenderer;
import name.modid.Modules;
import org.lwjgl.glfw.GLFW;

public class PulsevisualsClient implements ClientModInitializer {
    private static KeyBinding openMenuKey;

    @Override
    public void onInitializeClient() {
        // Регистрируем HUD-отрисовщик
        HudRenderCallback.EVENT.register(new HudRenderer());

        // Регистрируем кнопку открытия меню на Правый Шифт
        openMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.pulsevisuals.open",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            "category.pulsevisuals.main"
        ));

        // Логика тиков (AutoSprint и открытие меню)
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            // Открытие ClickGUI меню
            while (openMenuKey.wasPressed()) {
                client.setScreen(new VisualsMenuScreen());
            }

            // ИСПРАВЛЕНО ДЛЯ 1.21.4: Используем pressingForward вместо input.up
            if (Modules.autoSprint) {
                if (client.player.input.pressingForward && !client.player.isPassenger()) {
                    client.player.setSprinting(true);
                }
            }
        });
    }
}
