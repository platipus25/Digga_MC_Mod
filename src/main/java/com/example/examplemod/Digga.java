package com.example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;


public class Digga extends ItemPickaxe {
    public Logger logger;

    public Digga(ToolMaterial material) {
        super(material);
        this.setCreativeTab(CreativeTabs.MISC);   // the item will appear on the Miscellaneous tab in creative

        //ResourceLocation
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        worldIn.getBlockState(pos);
        //worldIn.destroyBlock(pos, true);
        ArrayList<ItemStack> drops = this.dig(worldIn, pos, facing);
        this.pickup(player, drops);
        //player.inventory
        //player.addItemStackToInventory();
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

    private ArrayList<ItemStack> dig(World world, BlockPos start, EnumFacing facing){
        BlockPos currentPos = start;
        Block currentBlock;
        ArrayList<ItemStack> drops = new ArrayList<>();
        boolean bedrock = false;

        // need an pushable list


        int counter = 0;
        while(!bedrock){
            ArrayList<BlockPos> circle = this.circle(currentPos);
            for(BlockPos pos: circle){
                currentBlock = world.getBlockState(pos).getBlock();
                IBlockState state = world.getBlockState(pos);
                ItemStack drop = new ItemStack(currentBlock.getItemDropped(state, new Random(), 1));
                world.destroyBlock(pos, false);
                drops.add(drop);
            }


            currentPos = currentPos.down();
            if(currentPos.getY() < 1) bedrock = true;
            if(this.logger != null){
                this.logger.info(currentPos.getY());
            }
            if(++counter > 256) bedrock = true;
        }

        return drops;
    }

    private void pickup(EntityPlayer player, ArrayList<ItemStack> items){
        for(int i = items.size()-1; i >= 0; i--){
            ItemStack item = items.get(i);
            player.addItemStackToInventory(item);
        }
    }

    private ArrayList<BlockPos> circle(BlockPos center){
        ArrayList<BlockPos> list = new ArrayList<>();
        list.add(center);
        //__XX__
        //_XXXX_
        //_XCXX_
        //__XX__

        int[][] shape = {
                {2, 0},
                {2, 1},
                {1, -1},
                {1, 0},
                {1, 1},
                {1, 2},
                {0, -1},
                {0, 0},
                {0, 1},
                {0, 2},
                {-1, 0},
                {-1, 1}
        };


        for(int i = 0; i < shape.length; i++) {
            int[] coords = shape[i];
            list.add(center.add(coords[0], 0, coords[1]));
        }

        return list;
    }
}