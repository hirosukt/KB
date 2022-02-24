package love.chihuyu.kb

import love.chihuyu.kb.command.commands.CommandKB
import love.chihuyu.kb.config.AmountData
import love.chihuyu.kb.utils.PlayerUtil.isGround
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerVelocityEvent
import org.bukkit.plugin.java.JavaPlugin

class KB : JavaPlugin(), Listener {

    companion object {
        lateinit var plugin: JavaPlugin
    }

    init {
        plugin = this
    }

    override fun onEnable() {
        saveResource("config.yml", false)

        AmountData.values["x"] = config.getDouble("x")
        AmountData.values["y"] = config.getDouble("y")
        AmountData.values["z"] = config.getDouble("z")
        AmountData.values["airx"] = config.getDouble("airx")
        AmountData.values["airy"] = config.getDouble("airy")
        AmountData.values["airz"] = config.getDouble("airz")

        server.pluginManager.registerEvents(this, this)

        CommandKB.register()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    fun on(e: PlayerVelocityEvent) {
        if (e.player.lastDamageCause !is EntityDamageByEntityEvent) return

        val damager = (e.player.lastDamageCause as? EntityDamageByEntityEvent)?.damager ?: return

        val x = damager.location.direction.x * (AmountData.values["x"] ?: fixKBConfig("x", .75))
        val y = AmountData.values["y"] ?: fixKBConfig("y", .33)
        val z = damager.location.direction.z * (AmountData.values["z"] ?: fixKBConfig("z", .75))
        val airx = damager.location.direction.x * (AmountData.values["airx"] ?: fixKBConfig("airx", .65))
        val airy = AmountData.values["airy"] ?: fixKBConfig("airy", .25)
        val airz = damager.location.direction.z * (AmountData.values["airz"] ?: fixKBConfig("airz", .65))

        e.player.velocity = if (e.player.isGround()) {
            e.velocity.setX(x).setY(y).setZ(z)
        } else {
            e.velocity.setX(airx).setY(airy).setZ(airz)
        }
    }

    fun fixKBConfig(path: String, value: Double): Double {
        config.set(path, value)
        return value
    }
}