package aquarius0715.afkplugin;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class AfkPlugin extends JavaPlugin {

    Map<UUID, Location> playerMap = new HashMap<UUID, Location>();
    Map<UUID, Boolean> afkStats = new HashMap<UUID, Boolean>();

    @Override
    public void onEnable() {
        getCommand("mafk").setExecutor(new Commands(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
