package glowredman.darkerer;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;

@LateMixin
public class DarkererLateMixins implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.darkerer.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        if (loadedMods.contains("TwilightForest")) {
            return Collections.singletonList("MixinWorldProviderTwilightForest");
        }
        return Collections.emptyList();
    }
}
