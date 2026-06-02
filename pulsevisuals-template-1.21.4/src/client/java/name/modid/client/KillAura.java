package name.modid;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.living.LivingEntity;

public class KillAura {
    public static void onTick(Minecraft client) {
        if (!Modules.killAura || client.player == null) return;
        
        for (Entity entity : client.level.entitiesForRendering()) {
            if (entity instanceof LivingEntity && entity != client.player && entity.isAlive()) {
                if (client.player.distanceTo(entity) <= Modules.auraRange) {
                    client.player.swing(net.minecraft.world.InteractionHand.MAIN_HAND);
                    client.gameMode.attack(client.player, entity);
                    break; 
                }
            }
        }
    }
}
