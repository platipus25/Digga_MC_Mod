package com.example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;


public class Digga extends ItemPickaxe {

    public Digga(ToolMaterial material) {
        super(material);
        this.setCreativeTab(CreativeTabs.MISC);   // the item will appear on the Miscellaneous tab in creative

        //ResourceLocation
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        worldIn.getBlockState(pos);
        //worldIn.destroyBlock(pos, true);
        this.dig(worldIn, pos, facing);


        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

    private void dig(World world, BlockPos start, EnumFacing facing){
        BlockPos currentPos = start;
        Block currentBlock = world.getBlockState(start).getBlock();
        boolean bedrock = false;

        // need an pushable list

        int counter = 0;
        while(!bedrock){
            currentBlock = world.getBlockState(currentPos).getBlock();
            IBlockState state = world.getBlockState(currentPos);
            Item drop = currentBlock.getItemDropped(state, new Random(), 5);
            world.destroyBlock(currentPos, false);


            currentPos = currentPos.down();

            if(currentPos.getZ() < 2) bedrock = true;
            if(++counter > 100) bedrock = true;
        }
    }
}