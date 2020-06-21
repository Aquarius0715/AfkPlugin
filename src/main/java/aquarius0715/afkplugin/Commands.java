package aquarius0715.afkplugin;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    AfkPlugin plugin;


    public Commands(AfkPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("mafk")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("You cannot this");
                return false;
            }
            if (args.length == 0) {
                Player player = (Player) sender;
                plugin.afkStats.putIfAbsent(player.getUniqueId(), false);

                if (!plugin.afkStats.get(player.getUniqueId())) {
                    plugin.playerMap.put(player.getUniqueId(), player.getLocation());
                    plugin.afkStats.put(player.getUniqueId(), true);
                    player.setGameMode(GameMode.SPECTATOR);
                    Bukkit.broadcastMessage(player.getDisplayName() + "さんが離席中になりました。");
                    player.sendMessage("あなたは離席中になりました。");
                    return true;
                } else {
                    player.teleport(plugin.playerMap.get(player.getUniqueId()));
                    player.setGameMode(GameMode.SURVIVAL);
                    plugin.afkStats.put(player.getUniqueId(), false);
                    Bukkit.broadcastMessage(player.getDisplayName() + "さんが離席解除になりました。");
                    sender.sendMessage("あなたは離席解除になりました。");
                    return true;
                }
            }
        }
        return false;
    }
}
