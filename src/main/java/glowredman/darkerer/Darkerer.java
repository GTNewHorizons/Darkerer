package glowredman.darkerer;

import net.minecraft.entity.player.EntityPlayer;

import org.apache.commons.lang3.ArrayUtils;

import com.gtnewhorizon.gtnhlib.eventbus.EventBusSubscriber;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.relauncher.Side;

@Mod(
    acceptedMinecraftVersions = "[1.7.10]",
    dependencies = "required-after:gtnhlib@[0.6.3,)",
    modid = Darkerer.MODID,
    name = "Darkerer",
    version = Tags.VERSION)
@EventBusSubscriber(side = Side.CLIENT)
public class Darkerer {

    public static final String MODID = "darkerer";

    public static boolean enabled;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent event) {
        if (event.phase == Phase.START) {
            return;
        }
        EntityPlayer player = FMLClientHandler.instance()
            .getClientPlayerEntity();
        enabled = player != null && !ArrayUtils.contains(DarkererConfig.dimBlocklist, player.dimension);
    }
}
