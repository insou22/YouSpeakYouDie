package co.insou.ysyd;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main extends JavaPlugin implements Listener {

    private ConcurrentLinkedQueue<UUID> killQueue = new ConcurrentLinkedQueue<>();

    @Override
    public void onEnable() {
        super.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (UUID uuid : killQueue) {
                    Bukkit.getPlayer(uuid).setHealth(0);
                    killQueue.remove(uuid);
                }
            }
        }, 5, 5);
    }

    @EventHandler
    public void on(AsyncPlayerChatEvent event) {
        killQueue.add(event.getPlayer().getUniqueId());
    }

}
