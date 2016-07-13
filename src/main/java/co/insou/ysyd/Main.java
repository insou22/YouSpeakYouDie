package co.insou.ysyd;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.getPlayer().setHealth(0);
        event.getPlayer().sendMessage(ChatColor.RED + "You lost");
    }
}
