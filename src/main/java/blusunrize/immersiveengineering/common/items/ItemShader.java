package blusunrize.immersiveengineering.common.items;

import java.util.List;
import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import blusunrize.immersiveengineering.api.IEApi;
import blusunrize.immersiveengineering.api.shader.IShaderItem;
import blusunrize.immersiveengineering.api.shader.ShaderCase;
import blusunrize.immersiveengineering.common.IEContent;
import blusunrize.immersiveengineering.common.IEVillagerTradeHandler.MerchantItem;
import blusunrize.immersiveengineering.common.util.ItemNBTHelper;
import blusunrize.immersiveengineering.common.util.Utils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemShader extends ItemIEBase implements IShaderItem
{
	public ItemShader()
	{
		super("shader", 1);
		this.icons = new IIcon[4];
		
		addShader("Rosequartz", 0, new int[]{65,35,35,255}, new int[]{230,180,180,255}, new int[]{240,205,205,255},new int[]{230,180,180,255}, null);
		addShader("Argo", 2, new int[]{45,45,45,255}, new int[]{220,220,220,255}, new int[]{220,120,35,255},new int[]{200,200,200,255}, null);
		addShader("Sunstrike", 1, new int[]{115,115,115,255}, new int[]{205,105,0,255}, new int[]{215,58,0,185},new int[]{215,58,0,185}, null);
		addShader("Locus", 2, new int[]{10,10,10,255}, new int[]{74,74,74,255}, new int[]{132,150,76,255},new int[]{74,74,74,255}, null);
		addShader("Felix", 1, new int[]{10,10,10,255}, new int[]{74,74,74,255}, new int[]{240,136,3,255},new int[]{74,74,74,255}, null);
		addShader("Sharkface", 2, new int[]{10,10,10,255}, new int[]{74,74,74,255}, new int[]{145,0,8,255},new int[]{74,74,74,255}, "immersiveengineering:shaders/revolver_shark");
		addShader("Dragon's Breath", 1, new int[]{25,25,25,255}, new int[]{51,63,43,255}, new int[]{138,138,138,255},new int[]{138,138,138,255}, "immersiveengineering:shaders/revolver_shark");
		addShader("Falconmoon", 3, new int[]{103,99,107,255}, new int[]{244,238,235,255}, new int[]{45,45,45,255},new int[]{244,238,235,255}, null);
		addShader("Sponsor", 0, new int[]{25,25,25,255}, new int[]{247,27,36,255}, new int[]{255,255,255,255},new int[]{170,170,170,255}, "immersiveengineering:shaders/revolver_sponsor");
		addShader("Magnum", 1, new int[]{86,56,44,255},new int[]{220,220,220,255},new int[]{160,160,160,255},new int[]{220,220,220,255}, null);

		addShader("StormFlower", 1, new int[]{39,52,39,255},new int[]{40,111,48,255},new int[]{75,146,85,255},new int[]{40,111,48,255}, null);
		addShader("Mil�", 2, new int[]{59,27,16,255},new int[]{103,0,4,255},new int[]{206,126,16,255},new int[]{103,0,4,255}, null);
		addShader("Trident", 2, new int[]{81,81,81,255},new int[]{168,168,168,255},new int[]{41,211,255,255},new int[]{175,175,175,255}, null);
		addShader("Chloris", 4, new int[]{56,50,42,255},new int[]{56,50,42,255},new int[]{136,250,190,255},new int[]{200,200,200,255}, null);
		addShader("Crescent Rose", 2, new int[]{20,20,20,255},new int[]{145,0,8,255},new int[]{8,8,8,255},new int[]{164,164,164,255}, null);
	}

	@Override
	public ShaderCase getShaderCase(ItemStack shader, ItemStack item, String shaderType)
	{
		String name = ItemNBTHelper.getString(shader, "shader_name");
		return IEApi.getShader(name, shaderType);
	}

	public void addShader(String name, int overlayType, int[] colour0, int[] colour1, int[] colour2, int[] colour3, String additionalTexture)
	{
		IEApi.registerShader_Revolver(name, overlayType, colour0, colour1, colour2, colour3, additionalTexture);
		//		NBTTagCompound tag = new NBTTagCompound();
		//		tag.setString("shader_name", name);
		//		tag.setInteger("shader_overlay", overlayType);
		//		tag.setIntArray("shader_colour0", colour0);
		//		tag.setIntArray("shader_colour1", colour1);
		//		tag.setIntArray("shader_colour2", colour2);
		//		tag.setIntArray("shader_colour3", colour3);
		//		if(additionalTexture!=null && !additionalTexture.isEmpty())
		//			tag.setString("shader_extraTexture", additionalTexture);
		//		IEApi.shaderList.add(tag);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean adv)
	{
	}
	@Override
	public String getItemStackDisplayName(ItemStack stack)
	{
		String s = "";
		if(ItemNBTHelper.hasKey(stack, "shader_name"))
			s = ": "+ItemNBTHelper.getString(stack, "shader_name");
		return super.getItemStackDisplayName(stack)+s;
	}


	@Override
	public WeightedRandomChestContent getChestGenBase(ChestGenHooks chest, Random random, WeightedRandomChestContent original)
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("shader_name", IEApi.shaderList.get(random.nextInt(IEApi.shaderList.size())));
		original.theItemId.setTagCompound(tag);
		return original;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		for(String key : IEApi.shaderList)
		{
			ItemStack s = new ItemStack(item);
			NBTTagCompound tag = new NBTTagCompound();
			tag.setString("shader_name", key);
			s.setTagCompound(tag);
			list.add(s);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		for(int i=0; i<3; i++)
			this.icons[i] = ir.registerIcon("immersiveengineering:shader_"+i);
		this.icons[3] = ir.registerIcon("immersiveengineering:shader_slot");
	}
	@Override
	public IIcon getIconFromDamageForRenderPass(int meta, int pass)
	{
		return icons[pass];
	}
	@Override
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}
	@Override
	public int getRenderPasses(int metadata)
	{
		return 3;
	}
	@Override
	public int getColorFromItemStack(ItemStack stack, int pass)
	{
		String name = ItemNBTHelper.getString(stack, "shader_name");
		if(IEApi.shaderCaseRegistry.containsKey(name))
		{
			ShaderCase sCase = IEApi.shaderCaseRegistry.get(name).get(0);
			int[] col = pass==0?sCase.getUnderlyingColour(): pass==1?sCase.getPrimaryColour(): sCase.getSecondaryColour();
			if(col!=null&&col.length>3)
				return (col[3]<<24)+(col[0]<<16)+(col[1]<<8)+col[2];
		}
		return super.getColorFromItemStack(stack, pass);
	}


	public static class ShaderMerchantItem extends MerchantItem
	{
		public ShaderMerchantItem()
		{
			super(IEContent.itemShader,1,1);
		}

		public ItemStack getItem(Random rand)
		{
			ItemStack s = Utils.copyStackWithAmount(this.item, 1);
			NBTTagCompound tag = new NBTTagCompound();
			tag.setString("shader_name", IEApi.shaderList.get(rand.nextInt(IEApi.shaderList.size())));
			s.setTagCompound(tag);
			return s;
		}
	}
}
