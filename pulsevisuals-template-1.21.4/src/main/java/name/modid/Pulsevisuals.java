package name.modid;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

// ВНИМАНИЕ: Если твой файл называется PulseVisuals.java,
// то вместо ExampleMod ниже напиши PulseVisuals
public class ExampleMod implements ClientModInitializer {
    private static KeyBinding openMenuKey;

    @Override
    public void onInitializeClient() {
        // Привязка функции к Правому Шифту
        openMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.pulsevisuals.open",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT, 
                "category.pulsevisuals.main"
        ));

        // Проверка нажатия кнопки каждый тик
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                while (openMenuKey.wasPressed()) {
                    client.setScreen(new VisualsMenuScreen());
                }
            }
        });
    }
}
