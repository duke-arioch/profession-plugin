package org.sandcast.profession.plugin.util;

import java.util.Optional;
import java.util.Set;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Crops;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Tree;
import static org.sandcast.profession.plugin.action.BlockSets.GROWABLE_CROPS;

public class BlockUtil {

    public static TreeSpecies getTreeSpecies(Block block) {
        TreeSpecies ts;
        if (block.getType() == Material.LOG || block.getType() == Material.LOG_2 || block.getType() == Material.WOOD) {
            MaterialData mData = block.getState().getData();
            if (mData instanceof Tree) {
                ts = ((Tree) mData).getSpecies();
            } else //(LOG_2 not using Tree::MaterialData)
            {
                ts = mData.getData() == 0 ? TreeSpecies.ACACIA : TreeSpecies.DARK_OAK;
            }
        } else {
            ts = null;
        }
        return ts;
    }

    public static Optional<CropState> cropState(Block block) {
        final CropState cropState;
        if (block != null && block.getType() != null && GROWABLE_CROPS.contains(block.getType()) && block.getState() != null) {
            Crops crops = (Crops) block.getState().getData();
            cropState = crops.getState();
        } else {
            cropState = null;
        }
        return Optional.ofNullable(cropState);
    }

    public static boolean rightClick(Object e) {
        if (e instanceof PlayerInteractEvent) {
            return ((PlayerInteractEvent) e).getAction().equals(Action.RIGHT_CLICK_BLOCK);
        } else {
            return false;
        }
    }

    public static boolean isUsing(Object e) {
        return false;
    }

    public static boolean isMaterial(Set<Material> materials, Block block) {
        return false;
    }

    public static boolean isMaterialSimilar(Set<Material> materials, Block block) {
        return false;
    }
}
