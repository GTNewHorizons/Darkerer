package glowredman.darkerer;

import com.gtnewhorizon.gtnhlib.config.Config;

@Config(modid = Darkerer.MODID)
public class DarkererConfig {

    @Config.Comment("""
        How Darkerer should behave
         EVERYWHERE: complete darkness at night, except near block light sources (torches, lava, etc.)
         ONLY_INSIDE: a small amount of light remains at the surface, even during the night
         MOON_PHASE: like ONLY_INSIDE but the remaining light depends on the moon phase
         DISABLED: completely disabled like vanilla""")
    @Config.DefaultEnum("EVERYWHERE")
    public static Mode mode;

    @Config.Comment("Whether or not the Nether should be dark")
    @Config.DefaultBoolean(true)
    public static boolean darkNether;

    @Config.Comment("Whether or not the End should be dark")
    @Config.DefaultBoolean(false)
    public static boolean darkEnd;

    @Config.Comment("Whether or not the Twilight Forest should be dark")
    @Config.DefaultBoolean(false)
    public static boolean darkTwilightForest;

    @Config.Comment("Attempts to remove the blue sky light that occurs when using mode EVERYWHERE or MOON_PHASE")
    @Config.DefaultBoolean(true)
    public static boolean removeBlueSkyLight;

    @Config.Comment("A list of dimension ids in which Darkerer will be completely disabled")
    @Config.DefaultIntList({})
    public static int[] dimBlocklist;
}
