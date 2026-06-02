package name.modid.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.KeyBinding;
import net.minecraft.client.util.InputConstants;
import name.modid.VisualsMenuScreen;
import name.modid.HudRenderer;
import name.modid.KillAura;
import org.lwjgl.glfw.GLFW;

public class PulsevisualsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new HudRenderer());

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            KillAura.onTick(client);
        });
    }
}
