package name.modid.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyBinding;
import net.minecraft.client.util.InputConstants;
import name.modid.VisualsMenuScreen;
import name.modid.KillAura;
import org.lwjgl.glfw.GLFW;

public class PulsevisualsClient implements ClientModInitializer {
    // Создаем переменную для клавиши
    public static KeyBinding openMenuKey;

    @Override
    public void onInitializeClient() {
        // Регистрируем клавишу (Right Shift)
        openMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.pulsevisuals.open",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_SHIFT, 
            "category.pulsevisuals.main"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // Проверка нажатия клавиши для открытия GUI
            if (openMenuKey.consumeClick()) {
                client.setScreen(new VisualsMenuScreen());
            }
            // Работа KillAura
            KillAura.onTick(client);
        });
    }
}
