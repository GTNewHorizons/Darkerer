package glowredman.darkerer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gtnewhorizon.gtnhlib.config.ConfigException;
import com.gtnewhorizon.gtnhlib.config.ConfigurationManager;
import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;

import cpw.mods.fml.relauncher.FMLLaunchHandler;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.Name;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;
import cpw.mods.fml.relauncher.Side;

@TransformerExclusions("glowredman.darkerer.DarkererCore")
@MCVersion("1.7.10")
@Name("Darkerer")
public class DarkererCore implements IFMLLoadingPlugin, IEarlyMixinLoader {

    public static boolean enabled;

    static {
        if (FMLLaunchHandler.side() == Side.CLIENT) {
            try {
                ConfigurationManager.registerConfig(DarkererConfig.class);
            } catch (ConfigException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String[] getASMTransformerClass() {
        return null;
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {}

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    @Override
    public String getMixinConfig() {
        return "mixins.darkerer.early.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedCoreMods) {
        if (FMLLaunchHandler.side() == Side.CLIENT) {
            return Arrays.asList("MixinEntityRenderer", "MixinWorld", "MixinWorldProviderHell");
        }
        return Collections.emptyList();
    }
}
