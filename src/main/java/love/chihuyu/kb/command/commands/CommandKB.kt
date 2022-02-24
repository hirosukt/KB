package love.chihuyu.kb.command.commands

import love.chihuyu.kb.KB.Companion.plugin
import love.chihuyu.kb.command.Command
import love.chihuyu.kb.config.AmountData
import org.bukkit.command.CommandSender

object CommandKB : Command("kb") {
    override fun onCommand(sender: CommandSender, label: String, args: Array<out String>) {
        if (args.isEmpty()) return

        fun load() {
            try {
                AmountData.values["x"] = plugin.config.getDouble("x")
                AmountData.values["y"] = plugin.config.getDouble("y")
                AmountData.values["z"] = plugin.config.getDouble("z")
                AmountData.values["airx"] = plugin.config.getDouble("airx")
                AmountData.values["airy"] = plugin.config.getDouble("airy")
                AmountData.values["airz"] = plugin.config.getDouble("airz")
                sender.sendMessage("§aKnockback successfully loaded.")
            } catch (e: Exception) {
                sender.sendMessage("§4Something went wrong! error is below : \n" + e.message)
                return
            }
        }

        if (args[0] == "set") {
            if (!sender.hasPermission("kb.command.set")) return
            try {
                plugin.config.set("x", args[1].toDouble())
                plugin.config.set("y", args[2].toDouble())
                plugin.config.set("z", args[3].toDouble())
                plugin.config.set("airx", args[4].toDouble())
                plugin.config.set("airy", args[5].toDouble())
                plugin.config.set("airz", args[6].toDouble())
                sender.sendMessage("§aKnockback successfully set.")
                load()
            } catch (e: Exception) {
                sender.sendMessage("§4Something went wrong! error is below : \n" + e.message)
                return
            }
        } else if (args[0] == "load") {
            if (!sender.hasPermission("kb.command.load")) return
            load()
        }
    }

    override fun onTabComplete(sender: CommandSender, label: String, args: Array<out String>): List<String> {
        return if (args.isNotEmpty()) {
            listOf("set", "load")
        } else {
            emptyList()
        }
    }
}