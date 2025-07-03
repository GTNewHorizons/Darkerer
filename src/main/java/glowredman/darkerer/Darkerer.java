package glowredman.darkerer;

import java.util.Map;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.network.NetworkCheckHandler;
import cpw.mods.fml.relauncher.Side;

@Mod(
    acceptedMinecraftVersions = "[1.7.10]",
    dependencies = "required-after:gtnhlib@[0.6.3,);required-after:unimixins",
    modid = Darkerer.MODID,
    name = "Darkerer",
    version = Tags.VERSION)
public class Darkerer {

    public static final String MODID = "darkerer";

    @NetworkCheckHandler
    public boolean checkRemoteVersion(Map<String, String> remoteMods, Side remoteSide) {
        if (remoteSide == Side.CLIENT) {
            // Darker must be present on the client
            return remoteMods.containsKey(MODID);
        }
        // Darker can be present on the server but doesn't have to be
        return true;
    }

}
