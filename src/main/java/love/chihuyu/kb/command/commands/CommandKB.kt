package love.chihuyu.kb.command.commands

import love.chihuyu.kb.KB.Companion.plugin
import love.chihuyu.kb.command.Command
import love.chihuyu.kb.config.AmountData
import org.bukkit.command.CommandSender

object CommandKB : Command("kb") {
    override fun onCommand(sender: CommandSender, label: String, args: Array<out String>) {
        if (!sender.hasPermission("kb.command.kb") || args.isEmpty()) return

        if (args[0] == "edit") {
            try {
                AmountData.values["x"] = args[1].toDouble()
                AmountData.values["y"] = args[2].toDouble()
                AmountData.values["z"] = args[3].toDouble()
                AmountData.values["airx"] = args[4].toDouble()
                AmountData.values["airy"] = args[5].toDouble()
                AmountData.values["airz"] = args[6].toDouble()
                sender.sendMessage("§aKnockback successfully set.")
            } catch (e: Exception) {
                sender.sendMessage("§4Something went wrong! below the error: \n" + e.message)
            }
        } else if (args[0] == "load") {
            try {
                AmountData.values["x"] = plugin.config.getDouble("x")
                AmountData.values["y"] = plugin.config.getDouble("y")
                AmountData.values["z"] = plugin.config.getDouble("z")
                AmountData.values["airx"] = plugin.config.getDouble("airx")
                AmountData.values["airy"] = plugin.config.getDouble("airy")
                AmountData.values["airz"] = plugin.config.getDouble("airz")
                sender.sendMessage("§aKnockback successfully loaded.")
            } catch (e: Exception) {
                sender.sendMessage("§4Something went wrong! below the error: \n" + e.message)
            }
        }
    }

    override fun onTabComplete(sender: CommandSender, label: String, args: Array<out String>): List<String>? {
        TODO("Not yet implemented")
    }
}