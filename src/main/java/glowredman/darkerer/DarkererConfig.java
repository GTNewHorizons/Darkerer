package glowredman.darkerer;

import com.gtnewhorizon.gtnhlib.config.Config;

@Config(modid = Darkerer.MODID)
@Config.Sync
public class DarkererConfig {

    @Config.Comment("How Darkerer should behave")
    @Config.DefaultEnum("NO_MIN_SKY_OR_BLOCK_LIGHT")
    public static Mode mode;

    @Config.Comment("Whether or not the Nether should be dark")
    @Config.DefaultBoolean(true)
    public static boolean nether;

    @Config.Comment("Whether or not the End should be dark")
    @Config.DefaultBoolean(false)
    public static boolean end;

    @Config.Comment("Whether or not the Twilight Forest should be dark")
    @Config.DefaultBoolean(false)
    public static boolean twilightForest;

    @Config.Comment("Attempts to remove the blue sky light that occurs when using mode NO_MIN_BLOCK_LIGHT or MOON_PHASE")
    @Config.DefaultBoolean(true)
    public static boolean removeBlue;

    @Config.Comment("A list of dimension ids in which Darkerer will be completely disabled")
    @Config.DefaultIntList({})
    public static int[] dimBlocklist;
}
