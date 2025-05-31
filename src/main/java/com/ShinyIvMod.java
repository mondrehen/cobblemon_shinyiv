package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.cobblemon.mod.common.api.pokemon.stats.Stats;   // ← 真正的枚举，太恶心了，找了一万年...

public class ShinyIvMod implements ModInitializer {
    @Override
    public void onInitialize() {

        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (!(entity instanceof PokemonEntity poke)) return;

            Pokemon mon = poke.getPokemon();
            if (!mon.getShiny()) return;                  // 1.6.1 用 getShiny()

            var ivs  = mon.getIvs();                      // 已是可变对象
            var stat = Stats.values()[world.getRandom().nextInt(6)];
            ivs.set(stat, 39);                           // 直接突破 31
        });
    }
}
