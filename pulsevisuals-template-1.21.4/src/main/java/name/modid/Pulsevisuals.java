package name.modid;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Pulsevisuals implements ClientModInitializer {
    private static KeyBinding openMenuKey;

    @Override
    public void onInitializeClient() {
        // Регистрируем кнопку открытия меню на Правый Шифт
        openMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.pulsevisuals.open",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT, 
                "category.pulsevisuals.main"
        ));

        // Проверяем нажатие каждый тик игры
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                while (openMenuKey.wasPressed()) {
                    // Открываем наше меню
                    client.setScreen(new VisualsMenuScreen());
                }
            }
        });
    }
}
