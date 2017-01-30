package org.sandcast.profession.plugin.action;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Material;
import static org.bukkit.Material.*;

public class BlockSets {

    public static final Set<Material> TILLABLE = new HashSet<>(Arrays.asList(new Material[]{GRASS, DIRT}));
    public static final Set<Material> TREEWOOD = new HashSet<>(Arrays.asList(new Material[]{LOG, LOG_2}));
    public static final Set<Material> TREE = new HashSet<>(Arrays.asList(new Material[]{LOG, LOG_2, LEAVES, LEAVES_2}));
    public static final Set<Material> HOES = new HashSet<>(Arrays.asList(new Material[]{WOOD_HOE, STONE_HOE, IRON_HOE, GOLD_HOE, DIAMOND_HOE}));
    public static final Set<Material> PLANTABLE_CROPS = new HashSet<>(Arrays.asList(new Material[]{CROPS, SEEDS, BEETROOT_SEEDS, CARROT_ITEM, POTATO_ITEM, MELON_SEEDS, PUMPKIN_SEEDS}));
    public static final Set<Material> GROWABLE_CROPS = new HashSet<>(Arrays.asList(new Material[]{CROPS, CARROT, POTATO, PUMPKIN_STEM, MELON_STEM}));
    public static final Set<Material> EDIBLE_CROPS = new HashSet<>(Arrays.asList(new Material[]{CROPS, BEETROOT, PUMPKIN, MELON_BLOCK, CARROT, POTATO}));
}
