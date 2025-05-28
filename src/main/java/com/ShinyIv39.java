package com.shinyiv;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.api.pokemon.Pokemon;
import com.cobblemon.mod.common.api.pokemon.stats.Stats;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;

public class ShinyIv39 implements ModInitializer {
    @Override public void onInitialize() {
        // 任何实体第一次加载到世界都会触发
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (!(entity instanceof PokemonEntity pokeEntity)) return;

            Pokemon mon = pokeEntity.getPokemon();
            if (!mon.isShiny()) return;                    // 只处理闪光
            var ivs  = mon.getIvs();
            var stat = Stats.values()[world.getRandom().nextInt(6)];
            ivs.set(stat, 39);                             // 突破 31
            mon.setIvs(ivs);
        });
    }
}
