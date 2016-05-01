package co.insou.ysyd;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main extends JavaPlugin implements Listener {

    // I saw them doing getters and setters like this in MassiveCore
    // MassiveCore is massive so it must know right
    // "If we only want one instance of our Main class, why not make it a singleton"

    private static Main plugin;
    public static Main getPlugin() { return plugin; }
    public static void setPlugin(Main plugin) { Main.plugin = plugin; }

    private static ConcurrentLinkedQueue<UUID> killQueue;
    public static ConcurrentLinkedQueue<UUID> getKillQueue() { return killQueue; }
    public static void setKillQueue(ConcurrentLinkedQueue<UUID> killQueue) { Main.killQueue = killQueue; }

    @Override
    public void onEnable() {
        setPlugin(this);
        onEnhancedEnable();
    }

    public static void onEnhancedEnable() {
        setKillQueue(new ConcurrentLinkedQueue<UUID>());
        Main.getPlugin().getServer().getPluginManager().registerEvents(Main.getPlugin(), Main.getPlugin());
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
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
        onEnhanced(event);
    }

    public static void onEnhanced(AsyncPlayerChatEvent event) {
        getKillQueue().add(event.getPlayer().getUniqueId());
    }

}
