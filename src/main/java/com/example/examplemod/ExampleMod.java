package com.example.examplemod;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.apache.logging.log4j.Logger;

@Mod(modid = ExampleMod.MODID, name = ExampleMod.NAME, version = ExampleMod.VERSION)
public class ExampleMod
{
    public static final String MODID = "examplemod";
    public static final String NAME = "Example Mod";
    public static final String VERSION = "1.0";

    private static Logger logger;
    public static Digga digga_cheap;
    public static Digga digga_economy;
    public static Digga digga_expensive;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();

        Item.ToolMaterial TOOL_MATERIAL_CHEAP = EnumHelper.addToolMaterial("digga_cheap", 0, 3, 1.0f, 6.0f, 220);
        digga_cheap = new Digga(TOOL_MATERIAL_CHEAP);
        ResourceLocation resource = new ResourceLocation("examplemod","digga_cheap");
        digga_cheap.setRegistryName(resource);
        digga_cheap.setUnlocalizedName("Digga Lite");
        digga_cheap.logger = logger;

        ModelResourceLocation modelLocation = new ModelResourceLocation(resource, "textures_are_hard");
        //digga_cheap.setRegistryName(new ResourceLocation("examplemod","digga_cheap"));
        //ModelLoader.setCustomModelResourceLocation(digga_cheap, 0, modelLocation);
        //ModelBakery.registerItemVariants(digga_cheap, resource);


        //ModelLoader.registerItemVariants();

        Item.ToolMaterial TOOL_MATERIAL_ECON = EnumHelper.addToolMaterial("digga_econ", 1, 131, 4.0f, 6.0f, 220);
        digga_economy = new Digga(TOOL_MATERIAL_ECON);
        digga_economy.setRegistryName("digga_econ");
        digga_economy.setUnlocalizedName("Digga Economy");

        Item.ToolMaterial TOOL_MATERIAL_EXPENSIVE = EnumHelper.addToolMaterial("digga_expensive", 3, 1561, 8.0f, 6.0f, 220);
        digga_expensive = new Digga(TOOL_MATERIAL_EXPENSIVE);
        digga_expensive.setRegistryName("digga_expensive");
        digga_expensive.setUnlocalizedName("Digga Premium");



        ForgeRegistries.ITEMS.registerAll(digga_cheap, digga_economy, digga_expensive);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

        //Ingredient ingredient = Ingredient.fromItem(Items.DIAMOND);
        //NonNullList<Ingredient> list = NonNullList.from(Ingredient.fromItems(Items.DIAMOND, Items.FEATHER));
       // IRecipe DIGGA_ECONOMY = new ShapelessRecipes("digga", new ItemStack(digga_cheap), list);

        //ForgeRegistries.RECIPES.registerAll(DIGGA_ECONOMY);
        GameRegistry.addShapedRecipe(new ResourceLocation("examplemod:digga_cheap"), new ResourceLocation(""), new ItemStack(digga_cheap), new Object[]{
                "III",
                " I ",
                " I ",
                'I', Items.IRON_INGOT
        });

        GameRegistry.addShapedRecipe(new ResourceLocation("examplemod:digga_econ"), new ResourceLocation(""), new ItemStack(digga_economy), new Object[]{
                "DDD",
                " I ",
                " I ",
                'D', Items.DIAMOND,
                'I', Items.IRON_INGOT     // note carefully - 'I' not "I" !
        });

        GameRegistry.addShapedRecipe(new ResourceLocation("examplemod:digga_expensive"), new ResourceLocation(""), new ItemStack(digga_expensive), new Object[]{
                "DDD",
                " D ",
                " D ",
                'D', Items.DIAMOND
        });
    }


}
