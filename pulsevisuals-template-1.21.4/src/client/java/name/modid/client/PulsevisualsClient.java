package name.modid;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.KeyMapping;
import com.mojang.blaze3d.platform.InputConstants;
import org.lwjgl.glfw.GLFW;

public class PulsevisualsClient implements ClientModInitializer {
    private static KeyMapping openMenuKey;

    @Override
    public void onInitializeClient() {
        openMenuKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.pulsevisuals.open",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT, 
                "category.pulsevisuals.main"
        ));

        // Логика игровых тиков
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                while (openMenuKey.consumeClick()) {
                    client.setScreen(new VisualsMenuScreen());
                }
                
                // РАБОТА ФУНКЦИИ АВТО-СПРИНТ
                if (Modules.autoSprint) {
                    if (client.player.input.up && !client.player.isPassenger()) {
                        client.player.setSprinting(true);
                    }
                }
            }
        });

        // РЕГИСТРАЦИЯ НАШЕГО КАСТОМНОГО HUD ХАБА
        HudRenderCallback.EVENT.register(new HudRenderer());
    }
}
