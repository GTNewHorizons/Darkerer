package glowredman.darkerer;

import net.minecraft.entity.player.EntityPlayer;

import org.apache.commons.lang3.ArrayUtils;

import com.gtnewhorizon.gtnhlib.eventbus.EventBusSubscriber;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.relauncher.Side;

@EventBusSubscriber(side = Side.CLIENT)
public class DarkererEventHandler {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent event) {
        if (event.phase == Phase.START) {
            return;
        }
        EntityPlayer player = FMLClientHandler.instance()
            .getClientPlayerEntity();
        DarkererCore.enabled = player != null && !ArrayUtils.contains(DarkererConfig.dimBlocklist, player.dimension)
            && DarkererConfig.mode != Mode.DISABLED;
    }

}
