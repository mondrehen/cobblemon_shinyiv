package com.shinyiv;

import com.cobblemon.mod.common.api.event.PokemonSpawnCallback;
import com.cobblemon.mod.common.api.pokemon.Pokemon;
import com.cobblemon.mod.common.api.pokemon.stats.Stats;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;

import net.fabricmc.api.ModInitializer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.SpawnReason;
import net.minecraft.util.math.random.Random;

public class ShinyIvMod implements ModInitializer {

    @Override
    public void onInitialize() {
        // 当任何 PokémonEntity 被生成到世界时触发回调
        PokemonSpawnCallback.EVENT.register((pokemonEntity, world, spawnReason) -> {
            Pokemon mon = pokemonEntity.getPokemon();
            // 只处理闪光宝可梦
            if (!mon.isShiny()) return;

            Random random = world.getRandom();
            int idx = random.nextInt(Stats.values().length);
            switch (idx) {
                case 0 -> mon.getIvs().setHp(39);
                case 1 -> mon.getIvs().setAttack(39);
                case 2 -> mon.getIvs().setDefense(39);
                case 3 -> mon.getIvs().setSpAttack(39);
                case 4 -> mon.getIvs().setSpDefense(39);
                case 5 -> mon.getIvs().setSpeed(39);
            }
        });
    }
}
