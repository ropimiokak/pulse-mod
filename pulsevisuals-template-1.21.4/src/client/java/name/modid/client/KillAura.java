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
package name.modid;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.living.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;

public class KillAura {
    public static void onTick(Minecraft client) {
        if (!Modules.killAura || client.player == null) return;
        
        for (Entity entity : client.level.entitiesForRendering()) {
            if (entity instanceof LivingEntity && entity != client.player && entity.isAlive()) {
                // Фильтр целей
                if (Modules.auraPlayers && !(entity instanceof Player)) continue;
                if (Modules.auraMobs && !(entity instanceof Monster)) continue;

                if (client.player.distanceTo(entity) <= Modules.auraRange) {
                    // Логика режимов
                    if (Modules.auraMode.equals("Legit")) {
                        client.player.swing(net.minecraft.world.InteractionHand.MAIN_HAND);
                        client.gameMode.attack(client.player, entity);
                    } else {
                        // Обходы для FT/ST (пакетная атака)
                        client.gameMode.attack(client.player, entity);
                        client.player.swing(net.minecraft.world.InteractionHand.MAIN_HAND);
                    }
                    break;
                }
            }
        }
    }
}
